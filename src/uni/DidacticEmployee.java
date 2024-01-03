package uni;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class DidacticEmployee extends Employee implements Serializable {
    private int publicationsNumber;
    private ArrayList<Course> courses;

    public DidacticEmployee() {
        this.publicationsNumber = 0;
        this.courses = new ArrayList<>();
    }
    public DidacticEmployee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary) {
        super(name, lastName, pesel, age, gender, job, seniority, salary);
        this.publicationsNumber = 0;
        this.courses = new ArrayList<>();
    }

    public DidacticEmployee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary, int publicationsNumber) {
        super(name, lastName, pesel, age, gender, job, seniority, salary);
        this.publicationsNumber = publicationsNumber;
        this.courses = new ArrayList<>();
    }
    public DidacticEmployee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary, int publicationsNumber, ArrayList<Course> courses) {
        super(name, lastName, pesel, age, gender, job, seniority, salary);
        this.publicationsNumber = publicationsNumber;
        setCourses(courses);
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses.forEach(this::removeCourse);
        courses.forEach(this::addCourse);
    }
    public void addCourse(Course course) {
        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Course")) {
            System.out.println("Called addCourse from "+Thread.currentThread().getStackTrace()[2].getClassName());
            course.setLecturer(this);
        }
        courses.add(course);
    }
    public void removeCourse(Course course) {
        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Course")) {
            System.out.println("Called addCourse from "+Thread.currentThread().getStackTrace()[2].getClassName());
            course.removeLecturer();
        }
        courses.remove(course);
    }
    public int getPublicationsNumber() {
        return publicationsNumber;
    }

    public void setPublicationsNumber(int publicationsNumber) {
        if (publicationsNumber < 0) publicationsNumber = 0;
        this.publicationsNumber = publicationsNumber;
    }

    @Override
    public String toString() {
        return "DidacticEmployee{" +
                "publicationsNumber=" + publicationsNumber +
                "} " + super.toString();
    }
}
