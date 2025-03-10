import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import  java.util.Map;

public class Enrollment {
    private Student student;
    private Course course;
    private Map<String, Integer> assignmentGrades;
    private int progress;
    private int attendance;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.assignmentGrades = new HashMap<>();
        this.progress = 0;
        this.attendance = 0;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void addAssignmentGrade(String assignmentName, int grade) {
        assignmentGrades.put(assignmentName, grade);
        updateProgress();
    }

    public int getAssignmentGrade(String assignmentName) {
        return assignmentGrades.getOrDefault(assignmentName, 0);
    }

    private void updateProgress() {
        if (assignmentGrades.isEmpty()) {
            progress = 0;
            return;
        }

        int totalGrades = 0;
        for (int grade : assignmentGrades.values()) {
            totalGrades += grade;
        }

        progress = totalGrades / assignmentGrades.size();
    }

    public int getProgress() {
        return progress;
    }

    public void setAttendance(int attendance) {
        if (attendance >= 0 && attendance <= 100) {
            this.attendance = attendance;
        } else {
            throw new IllegalArgumentException("Посещаемость должна быть от 0 до 100%");
        }
    }

    public int getAttendance() {
        return attendance;
    }
}