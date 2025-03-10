import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInstructorFrame extends JFrame {
    public CreateInstructorFrame() {
        setTitle("Создание преподавателя");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Имя преподавателя:");
        JTextField nameField = new JTextField();

        JLabel scheduleLabel = new JLabel("Расписание:");
        JTextField scheduleField = new JTextField();

        JButton createButton = new JButton("Создать");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String schedule = scheduleField.getText();
                if (!name.isEmpty() && !schedule.isEmpty()) {
                    Instructor instructor = new Instructor(name, schedule);
                    MainFrame.instructors.add(instructor);
                    JOptionPane.showMessageDialog(null, "Преподаватель создан: " + instructor.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(scheduleLabel);
        panel.add(scheduleField);
        panel.add(createButton);

        add(panel);
    }
}