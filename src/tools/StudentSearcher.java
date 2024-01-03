package tools;

import uni.Person;
import uni.Student;

import java.util.ArrayList;
import java.util.Objects;

public class StudentSearcher implements Searcher<Student, Person> {
    public String getPropertyValue(Student student, String mode) {
        String value = null;
        if (Objects.equals(mode, "lastName")) value = student.getlastName();
        else if (Objects.equals(mode, "firstName")) value = student.getName();
        else if (Objects.equals(mode, "indexNumber")) value = Integer.toString(student.getIndexNumber());
        else if (Objects.equals(mode, "termNumber")) value = Integer.toString(student.getTermNumber());

        return value;
    }
    public ArrayList<Student> searchByProperty(ArrayList<Student> students, String mode, String keyWord) {
        ArrayList<Student> answer = new ArrayList<Student>();
        students.forEach(student -> {

            if (Objects.equals(getPropertyValue(student, mode), keyWord)) {
               answer.add(student);
            }
        });
        return answer;
    }
    public ArrayList<Student> searchByCourseName(ArrayList<Student> students, String keyWord) {
        ArrayList<Student> answer = new ArrayList<Student>();
        students.forEach(student -> {
            student.getCourses().forEach(course -> {
                if (Objects.equals(course.getName(), keyWord)) {
                    answer.add(student);
                }
            });
        });
        return answer;
    }

    @Override
    public ArrayList<Student> search(ArrayList<Person> people, String mode, String keyWord) {
        ArrayList<Student> students = new ArrayList<>();
        people.forEach(person -> {
            if (person instanceof Student) students.add((Student) person);
        });

        ArrayList<Student> answer = switch (mode) {
            case "lastName", "firstName", "indexNumber", "termNumber" -> searchByProperty(students, mode, keyWord);
            case "courseName" -> searchByCourseName(students, keyWord);
            default -> new ArrayList<Student>();
        };
        return answer;
    }
}
