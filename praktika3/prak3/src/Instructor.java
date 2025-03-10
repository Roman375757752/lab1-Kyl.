import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String name;
    private List<Course> courses;
    private String schedule;

    public Instructor(String name, String schedule) {
        this.name = name;
        this.schedule = schedule;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}