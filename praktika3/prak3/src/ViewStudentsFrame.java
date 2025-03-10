import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStudentsFrame extends JFrame {
    private JComboBox<String> courseComboBox;
    private JTextArea studentsArea;

    public ViewStudentsFrame() {
        setTitle("Список студентов");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JButton showStudentsButton = new JButton("Показать студентов");

        studentsArea = new JTextArea();
        studentsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentsArea);

        showStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = (String) courseComboBox.getSelectedItem();
                if (courseName != null) {

                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);
                    if (course != null) {

                        studentsArea.setText("");
                        for (Student student : course.getStudents()) {
                            Enrollment enrollment = student.getEnrollment(course);
                            if (enrollment != null) {
                                studentsArea.append("Студент: " + student.getName() +
                                        ", Прогресс: " + enrollment.getProgress() + "%\n");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите курс!");
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(courseLabel);
        topPanel.add(courseComboBox);
        topPanel.add(showStudentsButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void updateCourseComboBox() {
        courseComboBox.removeAllItems();
        for (Course course : MainFrame.courses) {
            courseComboBox.addItem(course.getName());
        }
    }
}