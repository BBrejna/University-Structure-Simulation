package tools;

import uni.Course;
import uni.DidacticEmployee;
import uni.Employee;
import uni.Student;

import java.util.ArrayList;
import java.util.Objects;

public class CourseSearcher implements Searcher<Course, Course> {
    public String getPropertyValue(Course course, String mode) {
        String value = null;
        if (Objects.equals(mode, "name")) value = course.getName();
        else if (Objects.equals(mode, "ECTS")) value = Integer.toString(course.getECTS());
        else if (Objects.equals(mode, "courseCode")) value = course.getCourseCode();

        return value;
    }
    private ArrayList<Course> searchByProperty(ArrayList<Course> courses, String mode, String keyWord) {
        ArrayList<Course> answer = new ArrayList<>();
        courses.forEach(course -> {
//            System.out.println(mode + getPropertyValue(course, mode) + keyWord);
            if (Objects.equals(getPropertyValue(course, mode), keyWord)) {
                answer.add(course);
            }
        });
        return answer;
    }
    @Override
    public ArrayList<Course> search(ArrayList<Course> courses, String mode, String keyWord) {
        ArrayList<Course> answer = switch(mode) {
            case "name", "ECTS", "courseCode" -> searchByProperty(courses, mode, keyWord);
            default -> new ArrayList<>();
        };
        return answer;
    }

    public ArrayList<Course> search(ArrayList<Course> courses, DidacticEmployee lecturer) {
        ArrayList<Course> answer = new ArrayList<>();
        courses.forEach(course -> {
            if (course.getLecturer().equals(lecturer)) {
                answer.add(course);
            }
        });
        return answer;
    }
}
