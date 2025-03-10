import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCourseFrame extends JFrame {
    private JTextField courseNameField;
    private JTextArea courseInfoArea;

    public SearchCourseFrame() {
        setTitle("Поиск курса");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel courseNameLabel = new JLabel("Введите название курса:");
        courseNameField = new JTextField(20);
        courseNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton searchButton = new JButton("Найти");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));

        topPanel.add(courseNameLabel);
        topPanel.add(courseNameField);
        topPanel.add(searchButton);

        courseInfoArea = new JTextArea();
        courseInfoArea.setEditable(false);
        courseInfoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(courseInfoArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                if (!courseName.isEmpty()) {
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);

                    if (course != null) {
                        courseInfoArea.setText("");
                        courseInfoArea.append("Название курса: " + course.getName() + "\n");
                        courseInfoArea.append("Описание: " + course.getDescription() + "\n");
                        courseInfoArea.append("Преподаватель: " + course.getInstructor().getName() + "\n");
                        courseInfoArea.append("Студенты:\n");
                        for (Student student : course.getStudents()) {
                            courseInfoArea.append("- " + student.getName() + "\n");
                        }
                        courseInfoArea.append("Задания:\n");
                        for (Assignment assignment : course.getAssignments()) {
                            courseInfoArea.append("- " + assignment.getName() + ": " + assignment.getDescription() + "\n");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Курс не найден!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Введите название курса!");
                }
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }
}