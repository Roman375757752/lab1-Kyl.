import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAttendanceFrame extends JFrame {
    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;
    private JTextField attendanceField;

    public SetAttendanceFrame() {
        setTitle("Выставление посещаемости");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel studentLabel = new JLabel("Выберите студента:");
        studentComboBox = new JComboBox<>();
        updateStudentComboBox();

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JLabel attendanceLabel = new JLabel("Посещаемость (%):");
        attendanceField = new JTextField();

        JButton setAttendanceButton = new JButton("Выставить посещаемость");

        setAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = (String) studentComboBox.getSelectedItem();
                String courseName = (String) courseComboBox.getSelectedItem();
                String attendanceText = attendanceField.getText();

                if (studentName != null && courseName != null && !attendanceText.isEmpty()) {
                    try {
                        int attendance = Integer.parseInt(attendanceText);
                        Student student = MainFrame.students.stream()
                                .filter(s -> s.getName().equals(studentName))
                                .findFirst()
                                .orElse(null);
                        Course course = MainFrame.courses.stream()
                                .filter(c -> c.getName().equals(courseName))
                                .findFirst()
                                .orElse(null);

                        if (student != null && course != null) {
                            Enrollment enrollment = student.getEnrollment(course);
                            if (enrollment != null) {
                                enrollment.setAttendance(attendance);
                                JOptionPane.showMessageDialog(null, "Посещаемость выставлена!");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Посещаемость должна быть числом!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });

        panel.add(studentLabel);
        panel.add(studentComboBox);
        panel.add(courseLabel);
        panel.add(courseComboBox);
        panel.add(attendanceLabel);
        panel.add(attendanceField);
        panel.add(setAttendanceButton);

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
}