import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAssignmentGradesFrame extends JFrame {
    private JComboBox<String> courseComboBox;
    private JComboBox<String> assignmentComboBox;
    private JTextArea gradesArea;

    public ViewAssignmentGradesFrame() {
        setTitle("Просмотр оценок за задания");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JLabel assignmentLabel = new JLabel("Выберите задание:");
        assignmentComboBox = new JComboBox<>();

        JButton showGradesButton = new JButton("Показать оценки");

        gradesArea = new JTextArea();
        gradesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradesArea);

        showGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = (String) courseComboBox.getSelectedItem();
                String assignmentName = (String) assignmentComboBox.getSelectedItem();

                if (courseName != null && assignmentName != null) {
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);
                    Assignment assignment = course.getAssignments().stream()
                            .filter(a -> a.getName().equals(assignmentName))
                            .findFirst()
                            .orElse(null);

                    if (course != null && assignment != null) {
                        gradesArea.setText("");
                        for (Student student : course.getStudents()) {
                            Enrollment enrollment = student.getEnrollment(course);
                            if (enrollment != null) {
                                int grade = enrollment.getAssignmentGrade(assignment.getName());
                                gradesArea.append("Студент: " + student.getName() +
                                        ", Оценка: " + grade + "\n");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите курс и задание!");
                }
            }
        });

        courseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = (String) courseComboBox.getSelectedItem();
                if (courseName != null) {
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);
                    if (course != null) {
                        updateAssignmentComboBox(course);
                    }
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(courseLabel);
        topPanel.add(courseComboBox);
        topPanel.add(assignmentLabel);
        topPanel.add(assignmentComboBox);
        topPanel.add(showGradesButton);

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


    private void updateAssignmentComboBox(Course course) {
        assignmentComboBox.removeAllItems();
        for (Assignment assignment : course.getAssignments()) {
            assignmentComboBox.addItem(assignment.getName());
        }
    }
}