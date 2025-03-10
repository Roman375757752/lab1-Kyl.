import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeAssignmentFrame extends JFrame {
    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> assignmentComboBox;
    private JTextField gradeField;

    public GradeAssignmentFrame() {
        setTitle("Выставление оценок");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));


        JLabel studentLabel = new JLabel("Выберите студента:");
        studentComboBox = new JComboBox<>();
        updateStudentComboBox();

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JLabel assignmentLabel = new JLabel("Выберите задание:");
        assignmentComboBox = new JComboBox<>();

        JLabel gradeLabel = new JLabel("Оценка:");
        gradeField = new JTextField();

        JButton gradeButton = new JButton("Выставить оценку");

        gradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = (String) studentComboBox.getSelectedItem();
                String courseName = (String) courseComboBox.getSelectedItem();
                String assignmentName = (String) assignmentComboBox.getSelectedItem();
                String gradeText = gradeField.getText();

                if (studentName != null && courseName != null && assignmentName != null && !gradeText.isEmpty()) {
                    try {
                        int grade = Integer.parseInt(gradeText);

                        Student student = MainFrame.students.stream()
                                .filter(s -> s.getName().equals(studentName))
                                .findFirst()
                                .orElse(null);
                        Course course = MainFrame.courses.stream()
                                .filter(c -> c.getName().equals(courseName))
                                .findFirst()
                                .orElse(null);
                        Assignment assignment = course.getAssignments().stream()
                                .filter(a -> a.getName().equals(assignmentName))
                                .findFirst()
                                .orElse(null);

                        if (student != null && course != null && assignment != null) {
                            Enrollment enrollment = student.getEnrollment(course);
                            if (enrollment != null) {
                                enrollment.addAssignmentGrade(assignment.getName(), grade);
                                JOptionPane.showMessageDialog(null, "Оценка выставлена!");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Оценка должна быть числом!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
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


        panel.add(studentLabel);
        panel.add(studentComboBox);
        panel.add(courseLabel);
        panel.add(courseComboBox);
        panel.add(assignmentLabel);
        panel.add(assignmentComboBox);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(gradeButton);

        add(panel);
    }

    private void updateStudentComboBox() {
        studentComboBox.removeAllItems();
        for (Student student : MainFrame.students) {
            studentComboBox.addItem(student.getName());
        }
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