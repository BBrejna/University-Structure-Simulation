package UI.controllers.tableElements;

public class PeopleTableElement {
    String name;
    String lastName;
    String age;
    String pesel;
    String gender;
    String salaryECTS;
    String role;

    public PeopleTableElement(String name, String lastName, String age, String pesel, String gender, String salaryECTS, String role) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.pesel = pesel;
        this.gender = gender;
        this.salaryECTS = salaryECTS;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getPesel() {
        return pesel;
    }

    public String getGender() {
        return gender;
    }

    public String getSalaryECTS() {
        return salaryECTS;
    }

    public String getRole() {
        return role;
    }
}
