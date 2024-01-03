package tools;

import uni.Employee;
import uni.Student;

import java.util.ArrayList;

public class Writer {
    public static void write(ArrayList<?> content) {
        if (!content.isEmpty()) {
            System.out.println("Writing:");
            content.forEach(System.out::println);
        } else {
            System.out.println("Empty list, nothing to write!");
        }
    }
    public static void writeEmployees(ArrayList<?> content) {
        ArrayList<Employee> employees = new ArrayList<>();
        content.forEach(object -> {
            if (object instanceof Employee)
                employees.add((Employee) object);
        });
        write(employees);
    }
    public static void writeStudents(ArrayList<?> content) {
        ArrayList<Student> students = new ArrayList<>();
        content.forEach(object -> {
            if (object instanceof Student)
                students.add((Student) object);
        });
        write(students);
    }
}
