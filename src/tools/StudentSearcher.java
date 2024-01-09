package tools;

import uni.Person;
import uni.Student;

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
    public MyHashSet<Student> searchByProperty(MyHashSet<Student> students, String mode, String keyWord) {
        MyHashSet<Student> answer = new MyHashSet<Student>();
        students.forEach(student -> {

            if (Objects.equals(getPropertyValue(student, mode), keyWord)) {
               answer.add(student);
            }
        });
        return answer;
    }
    public MyHashSet<Student> searchByCourseName(MyHashSet<Student> students, String keyWord) {
        MyHashSet<Student> answer = new MyHashSet<Student>();
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
    public MyHashSet<Student> search(MyHashSet<Person> people, String mode, String keyWord) {
        MyHashSet<Student> students = new MyHashSet<>();
        people.forEach(person -> {
            if (person instanceof Student) students.add((Student) person);
        });

        MyHashSet<Student> answer = switch (mode) {
            case "lastName", "firstName", "indexNumber", "termNumber" -> searchByProperty(students, mode, keyWord);
            case "courseName" -> searchByCourseName(students, keyWord);
            default -> new MyHashSet<Student>();
        };
        return answer;
    }
}
