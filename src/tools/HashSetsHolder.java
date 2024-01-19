package tools;

import uni.Course;
import uni.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class HashSetsHolder {
    private static final HashSetsHolder instance = new HashSetsHolder();
    public static HashSetsHolder getInstance() { return instance; }
    private HashSetsHolder() {
        people = new MyHashSet<>();
        courses = new MyHashSet<>();
        mapCourseCodeToCourse = new HashMap<>();
        mapPeselToPerson = new HashMap<>();
    }

    private MyHashSet<Person> people;
    private MyHashSet<Course> courses;

    private HashMap<String, Course> mapCourseCodeToCourse;
    private HashMap<String, Person> mapPeselToPerson;

    public HashMap<String, Person> getMapPeselToPerson() {
        return mapPeselToPerson;
    }

    public MyHashSet<Person> getPeople() {
        return people;
    }

    public void setPeople(MyHashSet<Person> people) {
        this.people.clear();
        this.people.addAll(people);

        if (!people.isEmpty()) {
            people.forEach(object -> {
                mapPeselToPerson.put(object.getPesel(), object);
            });
        }
    }

    public boolean add(Person person) {
        boolean succeeded = people.add(person);
        if (succeeded) {
            mapPeselToPerson.put(person.getPesel(), person);
        }
        return succeeded;
    }

    public boolean remove(Person person) {
        boolean succeeded = people.remove(person);
        if (succeeded) {
            mapPeselToPerson.remove(person.getPesel());
        }
        return succeeded;
    }

    public MyHashSet<Course> getCourses() {
        return courses;
    }

    public void setCourses(MyHashSet<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);

        if (!courses.isEmpty()) {
            courses.forEach(object -> {
                mapCourseCodeToCourse.put(object.getCourseCode(), object);
            });
        }
    }

    public boolean add(Course course) {
        boolean succeeded = courses.add(course);
        if (succeeded) {
            mapCourseCodeToCourse.put(course.getCourseCode(), course);
        }
        return succeeded;
    }

    public boolean remove(Course course) {
        boolean succeeded = courses.remove(course);
        if (succeeded) {
            mapCourseCodeToCourse.remove(course.getCourseCode());
        }
        return succeeded;
    }

    public HashMap<String, Course> getMapCourseCodeToCourse() { return mapCourseCodeToCourse; }
}
