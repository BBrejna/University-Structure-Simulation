package uni;

import tools.ObserverSubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Course extends ObserverSubject<CourseState> implements Serializable {
    private String name;
    private int ECTS;
    private String courseCode;
    private DidacticEmployee lecturer;
    private ArrayList<Student> students;
    private CourseState courseState;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Course otherCourse = (Course) obj;
        return Objects.equals(courseCode, otherCourse.courseCode);
    }
    @Override
    public int hashCode() {
        int result = courseCode != null ? courseCode.hashCode() : 0;
        return result;
    }
    public Course() {
        name = "Test";
        ECTS = -1;
        courseCode = "Test";
        lecturer = null;
        students = new ArrayList<>();
        courseState = new CourseState(courseCode);
    }
    public Course(String name, int ECTS, String courseCode, DidacticEmployee lecturer, ArrayList<Student> students) {
        this.name = name;
        this.ECTS = ECTS;
        this.courseCode = courseCode;
        this.lecturer = lecturer;
        this.students = students;
        courseState = new CourseState(courseCode);
    }
    public Course(String name, int ECTS, String courseCode, DidacticEmployee lecturer) {
        this.name = name;
        this.ECTS = ECTS;
        this.courseCode = courseCode;
        this.lecturer = lecturer;
        this.students = new ArrayList<Student>();
        courseState = new CourseState(courseCode);
    }
    public Course(String name, int ECTS, String courseCode) {
        this.name = name;
        this.ECTS = ECTS;
        this.courseCode = courseCode;
        this.lecturer = null;
        this.students = new ArrayList<Student>();
        courseState = new CourseState(courseCode);
    }

    public void startCourse() {
        if (!courseState.isStarted()) {
            courseState.setStarted(true);
            notifyObservers(courseState);
        } else {
            System.out.println("Error: course hasn't started yet");
        }
    }
    public void finishCourse() {
        if (courseState.isStarted() && !courseState.isFinished()) {
            courseState.setFinished(true);
            notifyObservers(courseState);
        } else {
            System.out.println("Error: course is already finished or hasn't started yet");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public DidacticEmployee getLecturer() {
        return lecturer;
    }
    public String getLecturerLastName() {
        return lecturer.getlastName();
    }

    public void setLecturer(DidacticEmployee lecturer) {
        if (lecturer == this.lecturer) return;

        if (this.lecturer != null) this.lecturer.removeCourse(this);

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.DidacticEmployee")) {
            System.out.println("Called setLecturer from "+Thread.currentThread().getStackTrace()[2].getClassName());
            lecturer.addCourse(this);
        }

        this.lecturer = lecturer;
    }
    public void removeLecturer() {
        if (this.lecturer == null) return;

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.DidacticEmployee")) {
            System.out.println("Called removeLecturer from "+Thread.currentThread().getStackTrace()[2].getClassName());
            lecturer.removeCourse(this);
        }

        this.lecturer = null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students.forEach(this::removeStudent);
        students.forEach(this::addStudent);
    }

    public void addStudent(Student student) {
        if (this.students.contains(student)) return;

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Student")) {
            System.out.println("Called addStudent from "+Thread.currentThread().getStackTrace()[2].getClassName());
            student.addCourse(this);
        }
        students.add(student);
    }
    public void removeStudent(Student student) {
        if (!this.students.contains(student)) return;

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Student")) {
            System.out.println("Called addStudent from "+Thread.currentThread().getStackTrace()[2].getClassName());
            student.removeCourse(this);
        }
        students.remove(student);
    }

    public CourseState getCourseState() {
        return courseState;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", ECTS=" + ECTS +
                ", courseCode='" + courseCode + '\'' +
                ", lecturer={" + lecturer + "}" +
//                ", students=" + students +
                '}';
    }
}
