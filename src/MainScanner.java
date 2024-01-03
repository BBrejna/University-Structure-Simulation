import tools.*;
import uni.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MainScanner {
    public static Serializer<Course> serializerCourse = new Serializer<>();
    public static Serializer<Person> serializerPeople = new Serializer<>();

    public static void main(String[] args) { //todo add observer to MainScanner
        Scanner scanner = new Scanner(System.in);

        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1. Add student");
            System.out.println("2. Add didactic employee");
            System.out.println("3. Add administration employee");
            System.out.println("4. Add course");
            System.out.println("5. Add student to a course");
            System.out.println("6. Remove student from a course");
            System.out.println("7. Assign didactic employee to a course");
            System.out.println("8. Remove didactic employee from a course");
            System.out.println("9. Save to files");
            System.out.println("10. Read from files");
            System.out.println("11. Write courses");
            System.out.println("12. Write students");
            System.out.println("13. Write employees");
            System.out.println("14. Write all");
            System.out.println("15. Search courses");
            System.out.println("16. Search students");
            System.out.println("17. Search employees");
            System.out.println("18. Sort people");
            System.out.println("19. Sort courses");
            System.out.println("20. Delete courses");
            System.out.println("21. Delete students");
            System.out.println("22. Delete employees");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addStudent(scanner, people);
                    break;
                case 2:
                    addDidacticEmployee(scanner, people);
                    break;
                case 3:
                    addAdministrationEmployee(scanner, people);
                    break;
                case 4:
                    addCourse(scanner, courses);
                    break;
                case 5:
                    addStudentToCourse(scanner, people, courses);
                    break;
                case 6:
                    removeStudentFromCourse(scanner, people, courses);
                    break;
                case 7:
                    assignDidacticToCourse(scanner, people, courses);
                    break;
                case 8:
                    removeDidacticFromCourse(scanner, courses);
                    break;
                case 9:
                    serializerCourse.saveToFile(courses, "courses.txt");
                    serializerPeople.saveToFile(people, "people.txt");
                    break;
                case 10:
                    people = serializerPeople.readFromFile("people.txt");
                    courses = serializerCourse.readFromFile("courses.txt");
                    break;
                case 11:
                    Writer.write(courses);
                    break;
                case 12:
                    Writer.writeStudents(people);
                    break;
                case 13:
                    Writer.writeEmployees(people);
                    break;
                case 14:
                    Writer.write(courses);
                    Writer.write(people);
                    break;
                case 15:
                    Writer.write(searchCourses(scanner, people, courses));
                    break;
                case 16:
                    Writer.write(searchStudents(scanner, people));
                    break;
                case 17:
                    Writer.write(searchEmployees(scanner, people));
                    break;
                case 18:
                    sortPeople(scanner, people);
                    break;
                case 19:
                    sortCourses(scanner, courses);
                    break;
                case 20:
                    courses.removeAll(searchCourses(scanner, people, courses));
                    break;
                case 21:
                    people.removeAll(searchStudents(scanner, people));
                    break;
                case 22:
                    people.removeAll(searchEmployees(scanner, people));
                    break;
                case 0:
                    System.out.println("EXITING!");
                    return;
                default:
                    System.out.println("Incorrect option, try again!");
            }

        }
    }

    private static void sortCourses(Scanner scanner, ArrayList<Course> courses) {
        courses.sort(Comparator.comparing(Course::getECTS)
                .thenComparing(Course::getLecturerLastName));
        Writer.write(courses);
    }

    private static void sortPeople(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Choose criteria:");
        System.out.println("1. Last name");
        System.out.println("2. Last name, then first name");
        System.out.println("3. Last name, then age");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                people.sort(Comparator.comparing(Person::getlastName));
                break;
            case 2:
                people.sort(Comparator.comparing(Person::getlastName)
                        .thenComparing(Person::getName));
                break;
            case 3:
                people.sort(Comparator.comparing(Person::getlastName)
                        .thenComparing(Person::getAge).reversed());
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return;
        }
        Writer.write(people);
    }

    private static ArrayList<Employee> searchEmployees(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Choose criteria:");
        System.out.println("1. Last name");
        System.out.println("2. First name");
        System.out.println("3. Job");
        System.out.println("4. Seniority");
        System.out.println("5. Salary");
        System.out.println("6. Number of publications");
        System.out.println("7. Amount of overtime");

        int option = scanner.nextInt();
        scanner.nextLine();

        String mode = "";
        switch (option) {
            case 1:
                mode = "lastName";
            case 2:
                if (mode.isEmpty()) mode = "firstName";
            case 3:
                if (mode.isEmpty()) mode = "job";
            case 4:
                if (mode.isEmpty()) mode = "seniority";
            case 5:
                if (mode.isEmpty()) mode = "salary";
            case 6:
                if (mode.isEmpty()) mode = "publicationsNumber";
            case 7:
                if (mode.isEmpty()) mode = "overtimeAmount";

                System.out.println("Insert key word:");
                String keyWord = scanner.nextLine();

                return new EmployeeSearcher().search(people, mode, keyWord);
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return new ArrayList<>();
        }

    }

    private static ArrayList<Student> searchStudents(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Choose criteria:");
        System.out.println("1. Last name");
        System.out.println("2. First name");
        System.out.println("3. Index number");
        System.out.println("4. Term number");
        System.out.println("5. Course name");

        int option = scanner.nextInt();
        scanner.nextLine();

        String mode = "";
        switch (option) {
            case 1:
                mode = "lastName";
            case 2:
                if (mode.isEmpty()) mode = "firstName";
            case 3:
                if (mode.isEmpty()) mode = "indexNumber";
            case 4:
                if (mode.isEmpty()) mode = "termNumber";
            case 5:
                if (mode.isEmpty()) mode = "courseName";

                System.out.println("Insert key word:");
                String keyWord = scanner.nextLine();

                return new StudentSearcher().search(people, mode, keyWord);
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return new ArrayList<>();
        }
    }

    private static ArrayList<Course> searchCourses(Scanner scanner, ArrayList<Person> people, ArrayList<Course> courses) {
        System.out.println("Choose criteria:");
        System.out.println("1. Course name");
        System.out.println("2. Course ECTS");
        System.out.println("3. Course code");
        System.out.println("4. Lecturer");

        int option = scanner.nextInt();
        scanner.nextLine();

        CourseSearcher searcher = new CourseSearcher();
        ArrayList<Course> searched = null;
        String mode = "";
        switch (option) {
            case 1:
                mode = "name";
            case 2:
                if (mode.isEmpty()) mode = "ECTS";
            case 3:
                if (mode.isEmpty()) mode = "courseCode";

                System.out.println("Insert key word:");
                String keyWord = scanner.nextLine();
                searched = searcher.search(courses, mode, keyWord);
                break;
            case 4:
                System.out.println("Choose lecturer:");
                ArrayList<DidacticEmployee> lecturers = new ArrayList<>();
                int iterator = 1;
                for (Person person : people) {
                    if (person instanceof DidacticEmployee) {
                        lecturers.add((DidacticEmployee) person);
                        System.out.println(iterator + ". " + person.getName() + " " + person.getlastName());
                        iterator += 1;
                    }
                }

                int optionLecturer = scanner.nextInt();
                scanner.nextLine();

                if (optionLecturer < 1 || iterator <= optionLecturer) {
                    System.out.println("Wrong id, stopping funcion!");
                    return new ArrayList<>();
                }
                searched = searcher.search(courses, lecturers.get(optionLecturer-1));

                break;
            default:
                System.out.println("WRONG OPTION CHOOSEN. ENDING FUNCION!");
                return new ArrayList<>();
        }
        return searched;
    }

    private static void removeStudentFromCourse(Scanner scanner, ArrayList<Person> people, ArrayList<Course> courses) {
        System.out.println("Choose course:");
        int iterator = 1;
        for (Course course : courses) {
            System.out.println(iterator + ". " + course.getName() + ", " + course.getECTS() + " ECTS");
            iterator += 1;
        }
        int optionCourse = scanner.nextInt();
        scanner.nextLine();

        if (optionCourse < 1 || iterator <= optionCourse) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }
        Course aktCourse = courses.get(optionCourse-1);

        System.out.println("Choose student:");
        ArrayList<Student> students = new ArrayList<>();
        iterator = 1;
        for (Person person : people) {
            if (person instanceof Student && aktCourse.getStudents().contains(person)) {
                students.add((Student) person);
                System.out.println(iterator + ". " + person.getName() + " " + person.getlastName() + " " + ((Student) person).getIndexNumber());
                iterator += 1;
            }
        }

        int optionStudent = scanner.nextInt();
        scanner.nextLine();

        if (optionStudent < 1 || iterator <= optionStudent) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }

        aktCourse.removeStudent(students.get(optionStudent-1));
    }

    private static void addStudentToCourse(Scanner scanner, ArrayList<Person> people, ArrayList<Course> courses) {
        System.out.println("Choose course:");
        int iterator = 1;
        for (Course course : courses) {
            System.out.println(iterator + ". " + course.getName() + ", " + course.getECTS() + " ECTS");
            iterator += 1;
        }
        int optionCourse = scanner.nextInt();
        scanner.nextLine();

        if (optionCourse < 1 || iterator <= optionCourse) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }
        Course aktCourse = courses.get(optionCourse-1);

        System.out.println("Choose student:");
        ArrayList<Student> students = new ArrayList<>();
        iterator = 1;
        for (Person person : people) {

            if (person instanceof Student && !aktCourse.getStudents().contains(person)) {
                students.add((Student) person);
                System.out.println(iterator + ". " + person.getName() + " " + person.getlastName() + " " + ((Student) person).getIndexNumber());
                iterator += 1;
            }
        }

        int optionStudent = scanner.nextInt();
        scanner.nextLine();

        if (optionStudent < 1 || iterator <= optionStudent) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }

        aktCourse.addStudent(students.get(optionStudent-1));
    }

    private static void addAdministrationEmployee(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Input:");
        System.out.println("\tFirst name:");
        String firstName = scanner.nextLine();

        System.out.println("\tLast name:");
        String lastName = scanner.nextLine();

        System.out.println("\tPesel:");
        String pesel = scanner.nextLine();

        System.out.println("\tAge:");
        double age = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\tGender:");
        String gender = scanner.nextLine();

        System.out.println("\tJob:");
        String job = scanner.nextLine();

        System.out.println("\tSeniority:");
        int seniority = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tSalary:");
        int salary = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tOvertime Amount:");
        int overtimeAmount = scanner.nextInt();
        scanner.nextLine();

        Person tmp = new AdministrationEmployee(firstName, lastName, pesel, age, gender, job, seniority, salary, overtimeAmount);
        if (people.contains(tmp)) {
            System.out.println("Person with given PESEL already exists!");
        } else {
            people.add(tmp);
        }
    }

    private static void addDidacticEmployee(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Input:");
        System.out.println("\tFirst name:");
        String firstName = scanner.nextLine();

        System.out.println("\tLast name:");
        String lastName = scanner.nextLine();

        System.out.println("\tPesel:");
        String pesel = scanner.nextLine();

        System.out.println("\tAge:");
        double age = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\tGender:");
        String gender = scanner.nextLine();

        System.out.println("\tJob:");
        String job = scanner.nextLine();

        System.out.println("\tSeniority:");
        int seniority = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tSalary:");
        int salary = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tPublications number:");
        int publicationsNumber = scanner.nextInt();
        scanner.nextLine();

        Person tmp = new DidacticEmployee(firstName, lastName, pesel, age, gender, job, seniority, salary, publicationsNumber);
        if (people.contains(tmp)) {
            System.out.println("Person with given PESEL already exists!");
        } else {
            people.add(tmp);
        }
    }

    public static void addStudent(Scanner scanner, ArrayList<Person> people) {
        System.out.println("Input:");
        System.out.println("\tFirst name:");
        String firstName = scanner.nextLine();

        System.out.println("\tLast name:");
        String lastName = scanner.nextLine();

        System.out.println("\tPesel:");
        String pesel = scanner.nextLine();

        System.out.println("\tAge:");
        double age = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\tGender:");
        String gender = scanner.nextLine();

        System.out.println("\tIndex number:");
        int indexNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tTerm number:");
        int termNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tIs on 1Degree:");
        boolean isOn1Degree = (scanner.nextInt() > 0);
        scanner.nextLine();

        System.out.println("\tIs on daily studies:");
        boolean isOnDailyStudies = (scanner.nextInt() > 0);
        scanner.nextLine();

        System.out.println("\tIs on Erasmus:");
        boolean isOnErasmus = (scanner.nextInt() > 0);
        scanner.nextLine();

        Person tmp = new Student(firstName, lastName, pesel, age, gender, indexNumber, termNumber, isOnErasmus, isOn1Degree, !isOn1Degree, isOnDailyStudies, !isOnDailyStudies);
        if (people.contains(tmp)) {
            System.out.println("Person with given PESEL or indexNumber already exists!");
        } else {
            people.add(tmp);
        }
    }
    public static void addCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.println("Input:");
        System.out.println("\tCourse name:");
        String name = scanner.nextLine();

        System.out.println("\tECTS number:");
        int ectsNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\tUnique course code:");
        String courseCode = scanner.nextLine();

        Course tmp = new Course(name, ectsNumber, courseCode);

        if (courses.contains(tmp)) {
            System.out.println("Course with given courseCode already exists!");
        } else {
            courses.add(tmp);
        }
    }
    private static void assignDidacticToCourse(Scanner scanner, ArrayList<Person> people, ArrayList<Course> courses) {
        System.out.println("Choose course:");
        int iterator = 1;
        for (Course course : courses) {
            System.out.println(iterator + ". " + course.getName() + ", " + course.getECTS() + " ECTS");
            iterator += 1;
        }
        int optionCourse = scanner.nextInt();
        scanner.nextLine();

        if (optionCourse < 1 || iterator <= optionCourse) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }
        Course aktCourse = courses.get(optionCourse-1);

        System.out.println("Choose lecturer:");
        ArrayList<DidacticEmployee> lecturers = new ArrayList<>();
        iterator = 1;
        for (Person person : people) {
            if (person instanceof DidacticEmployee && !person.equals(aktCourse.getLecturer())) {
                lecturers.add((DidacticEmployee) person);
                System.out.println(iterator + ". " + person.getName() + " " + person.getlastName());
                iterator += 1;
            }
        }

        int optionLecturer = scanner.nextInt();
        scanner.nextLine();

        if (optionLecturer < 1 || iterator <= optionLecturer) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }

        aktCourse.setLecturer(lecturers.get(optionLecturer-1));

    }
    public static void removeDidacticFromCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.println("Choose course:");
        int iterator = 1;
        for (Course course : courses) {
            System.out.println(iterator + ". " + course.getName() + ", " + course.getECTS() + " ECTS. lecturer = "+course.getLecturer());
            iterator += 1;
        }
        int optionCourse = scanner.nextInt();
        scanner.nextLine();

        if (optionCourse < 1 || iterator <= optionCourse) {
            System.out.println("Wrong id, stopping funcion!");
            return;
        }

        courses.get(optionCourse-1).removeLecturer();
    }
}
