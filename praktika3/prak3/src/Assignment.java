class Assignment {
    private Course course;
    private String name;
    private String description;

    public Assignment(Course course, String name, String description) {
        this.course = course;
        this.name = name;
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void setGrade(Student student, int grade) {
        Enrollment enrollment = student.getEnrollment(course);
        if (enrollment != null) {
            enrollment.addAssignmentGrade(this.name, grade);
        }
    }
}