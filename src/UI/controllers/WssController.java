package UI.controllers;

import UI.controllers.popups.SearchCoursesPopupController;
import UI.controllers.popups.SearchPeoplePopupController;
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
import tools.*;
import uni.*;

import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

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
            String coursesString = "";
            for (Course course : student.getCourses()) {
                coursesString += "\n\t"+course;
            }
            String[] studentInfo = new String[]{
                    "Index number: "+student.getIndexNumber(),
                    "ECTS: "+student.getNumOfECTS(),
                    "Term number: "+student.getTermNumber(),
                    "Degree: "+(student.isOn1Degree() ? "1" : "2"),
                    "Daily: "+(student.isOnDaily() ? "true" : "false"),
                    "Erasmus: "+(student.isInErasmus() ? "true" : "false"),
                    "Courses: "+coursesString,
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
            String coursesString = "";
            for (Course course : didacticEmployee.getCourses()) {
                coursesString += "\n\t"+course;
            }
            infoArray.add("Publications: "+didacticEmployee.getPublicationsNumber());
            infoArray.add("Courses: "+coursesString);
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
            String salaryECTS = "";
            if (person instanceof Student student) {
                role = "Student";
                salaryECTS = String.valueOf(student.getNumOfECTS());
            }
            else if (person instanceof DidacticEmployee didacticEmployee) {
                role = "Didactic";
                salaryECTS = String.valueOf(didacticEmployee.getSalary());
            }
            else if (person instanceof AdministrationEmployee administrationEmployee) {
                role = "Administration";
                salaryECTS = String.valueOf(administrationEmployee.getSalary());
            }
            peopleTable.getItems().add(new PeopleTableElement(person.getName(), person.getlastName(), String.valueOf(person.getAge()), person.getPesel(), person.getGender(), salaryECTS, role));
            peopleTable.getSortOrder().clear();
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
            coursesTable.getSortOrder().clear();
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

    public void onSearchPeopleButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/views/popups/SearchPeoplePopupView.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();

        SearchPeoplePopupController popupController = loader.getController();

        int searchMode = popupController.displayPopup(popupStage, root);
        String keyWord = popupController.getKeyWord();
        System.out.println(searchMode+" "+keyWord);

        ArrayList<Person> searchResult = new ArrayList<>();

        HashMap<Integer, String> modeMap = new HashMap<Integer, String>() {
            {
                put(2, "lastName");
                put(3, "firstName");
                put(4, "indexNumber");
                put(5, "termNumber");
                put(6, "courseName");
                put(7, "job");
                put(8, "seniority");
                put(9, "salary");
                put(10, "publicationsNumber");
                put(11, "overtimeAmount");
            }
        };

        String mode = "";
        switch (searchMode) {
            case 0:
                for (Person person : HashSetsHolder.getInstance().getPeople()) {
                    if (person instanceof Student student) {
                        searchResult.add(student);
                    }
                }
                break;
            case 1:
                for (Person person : HashSetsHolder.getInstance().getPeople()) {
                    if (person instanceof Employee employee) {
                        searchResult.add(employee);
                    }
                }
                break;
            case 2:
            case 3:
                mode = modeMap.get(searchMode);
                searchResult.addAll(new StudentSearcher().search(people, mode, keyWord));
                searchResult.addAll(new EmployeeSearcher().search(people, mode, keyWord));
                break;
            case 4:
            case 5:
            case 6:
                mode = modeMap.get(searchMode);
                searchResult.addAll(new StudentSearcher().search(people, mode, keyWord));
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                mode = modeMap.get(searchMode);
                searchResult.addAll(new EmployeeSearcher().search(people, mode, keyWord));
                break;
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return;
        }
        fillPeopleTable(searchResult);
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

    public void onSearchCoursesButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/views/popups/SearchCoursesPopupView.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();

        SearchCoursesPopupController popupController = loader.getController();

        int searchMode = popupController.displayPopup(popupStage, root);
        String keyWord = popupController.getKeyWord();
        DidacticEmployee lecturer = popupController.getLecturer();
        System.out.println(searchMode+" "+keyWord+" "+lecturer);

        ArrayList<Course> searchResult = new ArrayList<>();

        HashMap<Integer, String> modeMap = new HashMap<Integer, String>() {
            {
                put(0, "name");
                put(1, "ECTS");
                put(2, "courseCode");
            }
        };

        String mode = "";
        switch (searchMode) {
            case 0:
            case 1:
            case 2:
                mode = modeMap.get(searchMode);
                searchResult.addAll(new CourseSearcher().search(courses, mode, keyWord));
                break;
            case 3:
                if (lecturer == null) {
                    System.out.println("LECTURER HASN'T BEEN CHOSEN");
                    return;
                }
                searchResult.addAll(new CourseSearcher().search(courses, lecturer));
                break;
            default:
                System.out.println("WRONG CRITERIA NUMBER. RETURNING!");
                return;
        }
        fillCoursesTable(searchResult);
    }

    public void onSortCoursesButtonClicked(ActionEvent actionEvent) {
        ArrayList<Course> sortResult = courses.sort(Comparator.comparing(Course::getECTS)
                .thenComparing(Course::getLecturerLastName));
        fillCoursesTable(sortResult);
    }
}
