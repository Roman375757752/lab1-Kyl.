import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAssignmentFrame extends JFrame {
    private JComboBox<String> courseComboBox;

    public CreateAssignmentFrame() {
        setTitle("Создание задания");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JLabel nameLabel = new JLabel("Название задания:");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Описание задания:");
        JTextField descriptionField = new JTextField();

        JButton createButton = new JButton("Создать");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = (String) courseComboBox.getSelectedItem();
                String assignmentName = nameField.getText();
                String assignmentDescription = descriptionField.getText();

                if (courseName != null && !assignmentName.isEmpty() && !assignmentDescription.isEmpty()) {
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);

                    if (course != null) {
                        Assignment assignment = new Assignment(course, assignmentName, assignmentDescription);
                        course.addAssignment(assignment);
                        JOptionPane.showMessageDialog(null, "Задание создано для курса: " + course.getName());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });

        panel.add(courseLabel);
        panel.add(courseComboBox);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(createButton);

        add(panel);
    }

    private void updateCourseComboBox() {
        courseComboBox.removeAllItems();
        for (Course course : MainFrame.courses) {
            courseComboBox.addItem(course.getName());
        }
    }
}