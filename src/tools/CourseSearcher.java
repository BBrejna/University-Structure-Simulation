package tools;

import uni.Course;
import uni.DidacticEmployee;

import java.util.Objects;

public class CourseSearcher implements Searcher<Course, Course> {
    public String getPropertyValue(Course course, String mode) {
        String value = null;
        if (Objects.equals(mode, "name")) value = course.getName();
        else if (Objects.equals(mode, "ECTS")) value = Integer.toString(course.getECTS());
        else if (Objects.equals(mode, "courseCode")) value = course.getCourseCode();

        return value;
    }
    private MyHashSet<Course> searchByProperty(MyHashSet<Course> courses, String mode, String keyWord) {
        MyHashSet<Course> answer = new MyHashSet<>();
        courses.forEach(course -> {
//            System.out.println(mode + getPropertyValue(course, mode) + keyWord);
            if (Objects.equals(getPropertyValue(course, mode), keyWord)) {
                answer.add(course);
            }
        });
        return answer;
    }
    @Override
    public MyHashSet<Course> search(MyHashSet<Course> courses, String mode, String keyWord) {
        MyHashSet<Course> answer = switch(mode) {
            case "name", "ECTS", "courseCode" -> searchByProperty(courses, mode, keyWord);
            default -> new MyHashSet<>();
        };
        return answer;
    }

    public MyHashSet<Course> search(MyHashSet<Course> courses, DidacticEmployee lecturer) {
        MyHashSet<Course> answer = new MyHashSet<>();
        courses.forEach(course -> {
            if (course.getLecturer().equals(lecturer)) {
                answer.add(course);
            }
        });
        return answer;
    }
}
