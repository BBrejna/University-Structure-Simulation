package UI.controllers.tableElements;

public class PeopleTableElement {
    String name;
    String lastName;
    String pesel;
    String gender;
    String role;

    public PeopleTableElement(String name, String lastName, String pesel, String gender, String role) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.gender = gender;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }
}
