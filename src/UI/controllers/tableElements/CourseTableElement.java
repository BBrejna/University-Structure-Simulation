package UI.controllers.tableElements;

public class CourseTableElement {
    private String name;
    private String ects;
    private String courseCode;
    private String lecturer;
    private String state;

    public CourseTableElement(String name, String ects, String courseCode, String lecturer, String state) {
        this.name = name;
        this.ects = ects;
        this.courseCode = courseCode;
        this.lecturer = lecturer;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getEcts() {
        return ects;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getState() {
        return state;
    }
}
