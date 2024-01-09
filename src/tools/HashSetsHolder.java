package tools;

import uni.Course;
import uni.Person;

import java.util.ArrayList;

public class HashSetsHolder {
    private static final HashSetsHolder instance = new HashSetsHolder();
    public static HashSetsHolder getInstance() { return instance; }
    private HashSetsHolder() {
        people = new MyHashSet<>();
        courses = new MyHashSet<>();
    }

    private MyHashSet<Person> people;
    private MyHashSet<Course> courses;

    public MyHashSet<Person> getPeople() {
        return people;
    }

    public void setPeople(MyHashSet<Person> people) {
        this.people.clear();
        this.people.addAll(people);
    }

    public MyHashSet<Course> getCourses() {
        return courses;
    }

    public void setCourses(MyHashSet<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);
    }
}
