import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private List<Enrollment> enrollments;

    public Student(String name) {
        this.name = name;
        this.enrollments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void enrollInCourse(Course course) {
        Enrollment enrollment = new Enrollment(this, course);
        enrollments.add(enrollment);
        course.addStudent(this);
    }

    public Enrollment getEnrollment(Course course) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().equals(course)) {
                return enrollment;
            }
        }
        return null;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}