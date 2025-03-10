import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPerformanceFrame extends JFrame {
    private JComboBox<String> courseComboBox;
    private JTextArea performanceArea;

    public ViewPerformanceFrame() {
        setTitle("Просмотр успеваемости");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel courseLabel = new JLabel("Выберите курс:");
        courseComboBox = new JComboBox<>();
        updateCourseComboBox();

        JButton showPerformanceButton = new JButton("Показать успеваемость");

        performanceArea = new JTextArea();
        performanceArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(performanceArea);

        showPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = (String) courseComboBox.getSelectedItem();
                if (courseName != null) {
                    Course course = MainFrame.courses.stream()
                            .filter(c -> c.getName().equals(courseName))
                            .findFirst()
                            .orElse(null);

                    if (course != null) {
                        performanceArea.setText("");
                        for (Student student : course.getStudents()) {
                            Enrollment enrollment = student.getEnrollment(course);
                            if (enrollment != null) {
                                performanceArea.append("Студент: " + student.getName() +
                                        ", Прогресс: " + enrollment.getProgress() + "%" +
                                        ", Посещаемость: " + enrollment.getAttendance() + "%\n");
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
        topPanel.add(showPerformanceButton);

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