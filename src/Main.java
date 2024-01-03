import tools.*;
import uni.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Serializer<Course> serializerCourse = new Serializer<>();
        Serializer<Person> serializerPeople = new Serializer<>();
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        people.add(new Student("Bartosz", "Brejna", "123456789", 19.8, "Male", 280490, 1, true, true));
        people.add(new Student("Aleksander", "Janic", "987654321", 3.5, "Male", 280123, 1, true, true));
        people.add(new Student("Michal", "Strugarek", "123789456", 10, "Male", 280456, 1, true, true));
        people.add(new DidacticEmployee("Martin", "Tabakow", "000111222", 40, "Male", "Lecturer", 15, 5000));
        people.add(new DidacticEmployee("Ulyana", "Yarka", "777888999", 35, "Female", "Lecturer", 12, 4500));
        people.add(new AdministrationEmployee("Gal", "Anonim", "123123123", 45, "Male", "Secretary", 12, 43999, 30));

        courses.add(new Course("PSiO", 5, "PSiO-sem1-2023", (DidacticEmployee) people.get(3)));

//        people = serializerPeople.readFromFile("people.txt");
//        courses = serializerCourse.readFromFile("courses.txt");

        ((Student) people.get(0)).addCourse(courses.get(0));
        courses.get(0).addStudent((Student) people.get(1));

        Writer writer = new Writer();
        writer.write(new StudentSearcher().search(people, "courseName", "PSiO"));
        writer.write(new CourseSearcher().search(courses, "courseCode", "uniqueCode"));
        writer.write(new EmployeeSearcher().search(people, "overtimeAmount", "30"));
        writer.writeStudents(people);
        writer.writeEmployees(people);

//        courses.get(0).startCourse(people);
//        courses.get(0).finishCourse(people);
        writer.writeStudents(people);
        writer.write(courses);

        serializerCourse.saveToFile(courses, "courses.txt");
        serializerPeople.saveToFile(people, "people.txt");

//        System.out.println(courses.get(0).getStudents());
//        Student tmp = new Student("Bartosz", "Brejna", "12345678", 19.8, "Male", 280490, 1, true, true);
//        System.out.println(people.contains(tmp));
    }
}