import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Instructor> instructors = new ArrayList<>();

    public MainFrame() {
        setTitle("Система управления курсами");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1, 10, 10));

        JButton createStudentButton = new JButton("Создать студента");
        JButton createCourseButton = new JButton("Создать курс");
        JButton createInstructorButton = new JButton("Создать преподавателя");
        JButton createAssignmentButton = new JButton("Создать задание");
        JButton enrollStudentButton = new JButton("Записать студента на курс");
        JButton viewStudentsButton = new JButton("Показать список студентов");
        JButton gradeAssignmentButton = new JButton("Выставить оценку");
        JButton viewPerformanceButton = new JButton("Просмотр успеваемости");
        JButton setAttendanceButton = new JButton("Выставление посещаемости");
        JButton viewAssignmentGradesButton = new JButton("Просмотр оценок за задания");
        JButton searchStudentButton = new JButton("Поиск студента");
        JButton searchCourseButton = new JButton("Поиск курса");

        createStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateStudentFrame().setVisible(true);
            }
        });

        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateCourseFrame().setVisible(true);
            }
        });

        createInstructorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateInstructorFrame().setVisible(true);
            }
        });

        createAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAssignmentFrame().setVisible(true);
            }
        });

        enrollStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnrollStudentFrame().setVisible(true);
            }
        });

        viewStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsFrame().setVisible(true);
            }
        });

        gradeAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GradeAssignmentFrame().setVisible(true);
            }
        });

        viewPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPerformanceFrame().setVisible(true);
            }
        });

        setAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetAttendanceFrame().setVisible(true);
            }
        });

        viewAssignmentGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAssignmentGradesFrame().setVisible(true);
            }
        });

        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchStudentFrame().setVisible(true);
            }
        });

        searchCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchCourseFrame().setVisible(true);
            }
        });

        panel.add(createStudentButton);
        panel.add(createCourseButton);
        panel.add(createInstructorButton);
        panel.add(createAssignmentButton);
        panel.add(enrollStudentButton);
        panel.add(viewStudentsButton);
        panel.add(gradeAssignmentButton);
        panel.add(viewPerformanceButton);
        panel.add(setAttendanceButton);
        panel.add(viewAssignmentGradesButton);
        panel.add(searchStudentButton);
        panel.add(searchCourseButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}