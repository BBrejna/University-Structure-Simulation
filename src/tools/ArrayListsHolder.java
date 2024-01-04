package tools;

import uni.Course;
import uni.Person;

import java.util.ArrayList;

public class ArrayListsHolder {
    private static final ArrayListsHolder instance = new ArrayListsHolder();
    public static ArrayListsHolder getInstance() { return instance; }
    private ArrayListsHolder() {
        people = new ArrayList<>();
        courses = new ArrayList<>();
    }

    private ArrayList<Person> people;
    private ArrayList<Course> courses;

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people.clear();
        this.people.addAll(people);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);
    }
}
