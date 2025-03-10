import java.util.ArrayList;
import java.util.List;

class Course {
    private String name;
    private String description;
    private Instructor instructor;
    private List<Student> students;
    private List<Assignment> assignments;

    public Course(String name, String description, Instructor instructor) {
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

}