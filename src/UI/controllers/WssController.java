package UI.controllers;

import UI.controllers.popups.SortPeoplePopupController;
import UI.controllers.tableElements.CourseTableElement;
import UI.controllers.tableElements.PeopleTableElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tools.HashSetsHolder;
import tools.MyHashSet;
import tools.Writer;
import uni.*;

import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class WssController {
    MyHashSet<Person> people = HashSetsHolder.getInstance().getPeople();
    MyHashSet<Course> courses = HashSetsHolder.getInstance().getCourses();
    public TableView<CourseTableElement> coursesTable;
    public TableView<PeopleTableElement> peopleTable;
    public Button sortPeopleButton;

    public void initialize() {
        ControllersHandler.getInstance().setWssController(this);
        peopleTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! peopleTable.getSelectionModel().isEmpty()) ) {
                PeopleTableElement rowData = peopleTable.getSelectionModel().getSelectedItem();
                showPersonInfo(rowData.getPesel());
            }
        });
    }
    private void showPersonInfo(String pesel) {
        Person myPerson = null;
        for (Person person : HashSetsHolder.getInstance().getPeople()) {
            if (pesel.equals(person.getPesel())) {
                myPerson = person;
            }
        }
        if (myPerson == null) return;
        String infoContent = "";
        String[] personInfo = new String[]{
                "First name: "+myPerson.getName(),
                "Last name: "+myPerson.getlastName(),
                "PESEL: "+myPerson.getPesel(),
                "Age: "+myPerson.getAge(),
                "Gender: "+myPerson.getGender()
        };
        ArrayList<String> infoArray = new ArrayList<>(Arrays.asList(personInfo));
        if (myPerson instanceof Student student) {
            String[] studentInfo = new String[]{
                    "Index number: "+student.getIndexNumber(),
                    "ECTS: "+student.getNumOfECTS(),
                    "Term number: "+student.getTermNumber(),
                    "Degree: "+(student.isOn1Degree() ? "1" : "2"),
                    "Daily: "+(student.isOnDaily() ? "true" : "false"),
                    "Erasmus: "+(student.isInErasmus() ? "true" : "false"),
            };
            infoArray.addAll(Arrays.asList(studentInfo));
        }
        if (myPerson instanceof Employee employee) {
            String[] employeeInfo = new String[]{
                    "Job: "+employee.getJob(),
                    "Seniority: "+employee.getSeniority(),
                    "Salary: "+employee.getSalary(),
            };
            infoArray.addAll(Arrays.asList(employeeInfo));
        }
        if (myPerson instanceof DidacticEmployee didacticEmployee) {
            infoArray.add("Publications: "+didacticEmployee.getPublicationsNumber());
        }
        if (myPerson instanceof AdministrationEmployee administrationEmployee) {
            infoArray.add("Overtime: "+administrationEmployee.getOvertimeAmount()+"h");
        }
        String infoText = "";
        for (String info : infoArray) {
            if (!infoText.isEmpty()) infoText+="\n";
            infoText+=info;
        }
        showAlert(infoText);
    }
    public void prepareToUse() {
//        restartAddDeleteFilesIoSection();
//
//        disableSecondarySection();
        restartTables();
    }

    private void showAlert(String infoString) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(infoString);
        alert.showAndWait();
    }

    private void fillPeopleTable(ArrayList<Person> content) {
        peopleTable.getItems().clear();
        for (Person person : content) {
            String role = "";
            if (person instanceof Student) role = "Student";
            else if (person instanceof DidacticEmployee) role = "Didactic";
            else if (person instanceof AdministrationEmployee) role = "Administration";
            peopleTable.getItems().add(new PeopleTableElement(person.getName(), person.getlastName(), String.valueOf(person.getAge()), person.getPesel(), person.getGender(), role));
        }
    }
    private void fillPeopleTable(MyHashSet<Person> content) {
        fillPeopleTable(new ArrayList<>(content));
    }

    private void fillCoursesTable(ArrayList<Course> content) {
        coursesTable.getItems().clear();


        for (Course course : content) {
            String courseState = "NOT STARTED";
            if (course.getCourseState().isStarted() && !course.getCourseState().isFinished()) {
                courseState = "STARTED";
            }
            if (course.getCourseState().isFinished()) {
                courseState = "FINISHED";
            }
            coursesTable.getItems().add(new CourseTableElement(course.getName(), String.valueOf(course.getECTS()), course.getCourseCode(), course.getLecturerInfo(), courseState));
        }
    }
    private void fillCoursesTable(MyHashSet<Course> content) {
        fillCoursesTable(new ArrayList<>(content));
    }
    private void restartTables() {
        fillPeopleTable(HashSetsHolder.getInstance().getPeople());
        fillCoursesTable(HashSetsHolder.getInstance().getCourses());
    }

    public void onClearPeopleFiltersButtonClicked(ActionEvent actionEvent) {
        restartTables();
    }

    public void onSearchPeopleButtonClicked(ActionEvent actionEvent) {
    }

    public void onSortPeopleButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/views/popups/SortPeoplePopupView.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();

        SortPeoplePopupController popupController = loader.getController();

        int sortMode = popupController.displayPopup(popupStage, root);
//        return (popupController.displayPopup(popupStage, root));

        ArrayList<Person> sortResult = null;

        switch (sortMode) {
            case 1:
                sortResult = people.sort(Comparator.comparing(Person::getlastName));
                break;
            case 2:
                sortResult = people.sort(Comparator.comparing(Person::getlastName)
                        .thenComparing(Person::getName));
                break;
            case 3:
                sortResult = people.sort(Comparator.comparing(Person::getlastName)
                        .thenComparing(Person::getAge, Comparator.reverseOrder()));
                break;
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return;
        }
        fillPeopleTable(sortResult);
    }

    public void onClearCoursesFiltersButtonClicked(ActionEvent actionEvent) {
        restartTables();
    }

    public void onSearchCoursesButtonClicked(ActionEvent actionEvent) {
    }

    public void onSortCoursesButtonClicked(ActionEvent actionEvent) {
        ArrayList<Course> sortResult = courses.sort(Comparator.comparing(Course::getECTS)
                .thenComparing(Course::getLecturerLastName));
        fillCoursesTable(sortResult);
    }
}
