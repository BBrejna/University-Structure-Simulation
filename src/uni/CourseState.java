package uni;

import java.io.Serializable;

public class CourseState implements Serializable {
    private String courseCode;
    private boolean isStarted;
    private boolean isFinished;
    public CourseState(String courseCode) {
        isStarted = false;
        isFinished = false;
        this.courseCode = courseCode;
    }

    public CourseState(String courseCode, boolean isStarted, boolean isFinished) {
        this.isStarted = isStarted;
        this.isFinished = isFinished;
        this.courseCode = courseCode;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "CourseState{" +
                "courseCode='" + courseCode + '\'' +
                ", isStarted=" + isStarted +
                ", isFinished=" + isFinished +
                '}';
    }
}
