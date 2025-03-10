import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStudentFrame extends JFrame {
    public CreateStudentFrame() {
        setTitle("Создание студента");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Имя студента:");
        JTextField nameField = new JTextField();

        JButton createButton = new JButton("Создать");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    Student student = new Student(name);
                    MainFrame.students.add(student);
                    JOptionPane.showMessageDialog(null, "Студент создан: " + student.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Введите имя студента!");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(createButton);

        add(panel);
    }
}