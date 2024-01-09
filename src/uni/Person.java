package uni;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {
    protected String name;
    protected String lastName;
    protected String pesel;
    protected double age;
    protected String gender;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person otherPerson = (Person) obj;
        return Objects.equals(pesel, otherPerson.pesel);
    }
    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        return result;
    }

    public Person() {
        name = "Test";
        lastName = "Test";
        pesel = "Test";
        age = 0;
        gender = "Test";
    }
    public Person(String name, String lastName, String pesel, double age, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
