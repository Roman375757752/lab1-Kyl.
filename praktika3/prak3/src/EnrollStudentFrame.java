import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnrollStudentFrame extends JFrame {
    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;

    public EnrollStudentFrame() {
        setTitle("Запись студента на курс");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));


        JLabel studentLabel = new JLabel("Выберите студента:");
        studentComboBox = new JComboBox<>();
        updateStudentComboBox();


        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JButton enrollButton = new JButton("Записать");

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = (String) studentComboBox.getSelectedItem();
                String courseName = (String) courseComboBox.getSelectedItem();

                if (studentName != null && courseName != null) {
                    Student student = MainFrame.students.stream()
                            .filter(s -> s.getName().equals(studentName))
                            .findFirst()
                            .orElse(null);
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);

                    if (student != null && course != null) {
                        student.enrollInCourse(course);
                        JOptionPane.showMessageDialog(null, "Студент " + studentName + " записан на курс " + courseName);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите студента и курс!");
                }
            }
        });

        panel.add(studentLabel);
        panel.add(studentComboBox);
        panel.add(courseLabel);
        panel.add(courseComboBox);
        panel.add(enrollButton);

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