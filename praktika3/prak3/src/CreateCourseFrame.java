import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCourseFrame extends JFrame {
    private JComboBox<String> instructorComboBox;

    public CreateCourseFrame() {
        setTitle("Создание курса");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Название курса:");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Описание курса:");
        JTextField descriptionField = new JTextField();

        JLabel instructorLabel = new JLabel("Выберите преподавателя:");
        instructorComboBox = new JComboBox<>();
        updateInstructorComboBox();

        JButton createButton = new JButton("Создать");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                String instructorName = (String) instructorComboBox.getSelectedItem();

                if (!name.isEmpty() && !description.isEmpty() && instructorName != null) {

                    Instructor instructor = MainFrame.instructors.stream()
                            .filter(i -> i.getName().equals(instructorName))
                            .findFirst()
                            .orElse(null);

                    if (instructor != null) {
                        Course course = new Course(name, description, instructor);
                        MainFrame.courses.add(course);
                        JOptionPane.showMessageDialog(null, "Курс создан: " + course.getName());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(instructorLabel);
        panel.add(instructorComboBox);
        panel.add(createButton);

        add(panel);
    }

    private void updateInstructorComboBox() {
        instructorComboBox.removeAllItems();
        for (Instructor instructor : MainFrame.instructors) {
            instructorComboBox.addItem(instructor.getName());
        }
    }
}