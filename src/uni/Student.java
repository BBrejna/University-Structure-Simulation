package uni;

import tools.HashSetsHolder;
import tools.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Student extends Person implements Serializable, Observer<CourseState> {
    private int indexNumber;
    private int termNumber;
    private ArrayList<Course> courses;
    private boolean inErasmus;
    private boolean isOn1Degree;
    private boolean isOn2Degree;
    private boolean isOnDaily;
    private boolean isOnRemote;
    private int numOfECTS = 0;

    public int getNumOfECTS() {
        return numOfECTS;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
//        if (! super.equals(obj)) return false;

        Student otherStudent = (Student) obj;
        return super.equals(obj) || Objects.equals(indexNumber, otherStudent.indexNumber);
    }
    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = (int)(1e6+3) * result + indexNumber;
        return result;
    }

    public Student() {
        indexNumber = 0;
        termNumber = 0;
        this.courses = new ArrayList<Course>();

    }
    public Student(String name, String lastName, String pesel, double age, String gender, int indexNumber, int termNumber) {
        super(name, lastName, pesel, age, gender);
        this.indexNumber = indexNumber;
        this.termNumber = termNumber;
        this.courses = new ArrayList<Course>();
    }

    public Student(String name, String lastName, String pesel, double age, String gender, int indexNumber, int termNumber, boolean isOn1Degree, boolean isOnDaily) {
        super(name, lastName, pesel, age, gender);
        this.indexNumber = indexNumber;
        this.termNumber = termNumber;
        this.isOn1Degree = isOn1Degree;
        this.isOnDaily = isOnDaily;
        this.courses = new ArrayList<Course>();
    }
    public Student(String name, String lastName, String pesel, double age, String gender, int indexNumber, int termNumber, ArrayList<Course> courses) {
        super(name, lastName, pesel, age, gender);
        this.indexNumber = indexNumber;
        this.termNumber = termNumber;
        setCourses(courses);
    }

    public Student(String name, String lastName, String pesel, double age, String gender, int indexNumber, int termNumber, ArrayList<Course> courses, boolean inErasmus, boolean isOn1Degree, boolean isOn2Degree, boolean isOnDaily, boolean isOnRemote) {
        super(name, lastName, pesel, age, gender);
        this.indexNumber = indexNumber;
        this.termNumber = termNumber;
        setCourses(courses);
        this.inErasmus = inErasmus;
        this.isOn1Degree = isOn1Degree;
        this.isOn2Degree = isOn2Degree;
        this.isOnDaily = isOnDaily;
        this.isOnRemote = isOnRemote;
    }

    public Student(String name, String lastName, String pesel, double age, String gender, int indexNumber, int termNumber, boolean inErasmus, boolean isOn1Degree, boolean isOn2Degree, boolean isOnDaily, boolean isOnRemote) {
        super(name, lastName, pesel, age, gender);
        this.indexNumber = indexNumber;
        this.termNumber = termNumber;
        this.courses = new ArrayList<>();
        this.inErasmus = inErasmus;
        this.isOn1Degree = isOn1Degree;
        this.isOn2Degree = isOn2Degree;
        this.isOnDaily = isOnDaily;
        this.isOnRemote = isOnRemote;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses.forEach(this::removeCourse);
        courses.forEach(this::addCourse);
    }
    public void addCourse(Course course) {
        course = HashSetsHolder.getInstance().getMapCourseCodeToCourse().get(course.getCourseCode());

        if (this.courses.contains(course)) return;

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Course")) {
            System.out.println("Called addCourse from "+Thread.currentThread().getStackTrace()[2].getClassName());
            course.addStudent(this);
        }
        courses.add(course);
        course.addObserver(this);
    }
    public void removeCourse(Course course) {
        course = HashSetsHolder.getInstance().getMapCourseCodeToCourse().get(course.getCourseCode());

        if (!this.courses.contains(course)) return;

        if (! Thread.currentThread().getStackTrace()[2].getClassName().equals("uni.Course")) {
            System.out.println("Called removeCourse from "+Thread.currentThread().getStackTrace()[2].getClassName());
            course.removeStudent(this);
        }

        courses.remove(course);
        course.removeObserver(this);
    }
    public boolean isInErasmus() {
        return inErasmus;
    }

    public void setInErasmus(boolean inErasmus) {
        this.inErasmus = inErasmus;
    }

    public boolean isOn1Degree() {
        return isOn1Degree;
    }

    public void setOn1Degree(boolean on1Degree) {
        isOn1Degree = on1Degree;
    }

    public boolean isOn2Degree() {
        return isOn2Degree;
    }

    public void setOn2Degree(boolean on2Degree) {
        isOn2Degree = on2Degree;
    }

    public boolean isOnDaily() {
        return isOnDaily;
    }

    public void setOnDaily(boolean onDaily) {
        isOnDaily = onDaily;
    }

    public boolean isOnRemote() {
        return isOnRemote;
    }

    public void setOnRemote(boolean onRemote) {
        isOnRemote = onRemote;
    }

    @Override
    public String toString() {
        return "Student{" +
                "indexNumber=" + indexNumber +
                ", termNumber=" + termNumber +
                ", ECTS amount=" + numOfECTS +
//                ", courses=" + courses +
                ", inErasmus=" + inErasmus +
                ", isOn1Degree=" + isOn1Degree +
                ", isOn2Degree=" + isOn2Degree +
                ", isOnDaily=" + isOnDaily +
                ", isOnRemote=" + isOnRemote +
                "} " + super.toString();
    }

    @Override
    public void updateState(CourseState courseState) {
        String courseCode = courseState.getCourseCode();
//        System.out.println(lastName+courseCode+courseState.isFinished());
        if (courseState.isFinished()) {
            Course tmpCourse = null;
            for (Course course : courses) {
                if (Objects.equals(course.getCourseCode(), courseCode)) {
                    tmpCourse = course;
                    break;
                }
            }
            if (tmpCourse != null) {
                removeCourse(tmpCourse);
                tmpCourse.removeObserver(this);
                numOfECTS += tmpCourse.getECTS();

                System.out.println("I'm " + name + " " + lastName + " and I've just finished course " + courseCode);
            }
        }
        else if (courseState.isStarted()) {
            System.out.println("I'm "+name+" "+lastName+" and I've just started course "+courseCode);
        }
    }
}
