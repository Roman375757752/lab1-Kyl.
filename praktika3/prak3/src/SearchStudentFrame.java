import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchStudentFrame extends JFrame {
    private JTextField studentNameField;
    private JTextArea studentInfoArea;

    public SearchStudentFrame() {
        setTitle("Поиск студента");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel studentNameLabel = new JLabel("Введите имя студента:");
        studentNameField = new JTextField(20);
        studentNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton searchButton = new JButton("Найти");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));

        topPanel.add(studentNameLabel);
        topPanel.add(studentNameField);
        topPanel.add(searchButton);

        studentInfoArea = new JTextArea();
        studentInfoArea.setEditable(false);
        studentInfoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(studentInfoArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                if (!studentName.isEmpty()) {
                    Student student = MainFrame.students.stream()
                            .filter(s -> s.getName().contains(studentName))
                            .findFirst()
                            .orElse(null);

                    if (student != null) {
                        studentInfoArea.setText("");
                        studentInfoArea.append("Имя студента: " + student.getName() + "\n");
                        studentInfoArea.append("Курсы и успеваемость:\n");
                        for (Enrollment enrollment : student.getEnrollments()) {
                            studentInfoArea.append("- Курс: " + enrollment.getCourse().getName() +
                                    ", Прогресс: " + enrollment.getProgress() + "%" +
                                    ", Посещаемость: " + enrollment.getAttendance() + "%\n");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Студент не найден!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Введите имя студента!");
                }
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }
}