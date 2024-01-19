package UI.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tools.*;
import uni.Course;
import uni.DidacticEmployee;
import uni.Person;
import uni.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IoController {

    public TextField textFieldStudentKeyWord;
    public TextField textFieldCourseKeyWord;

    public void initialize() {
        ControllersHandler.getInstance().setIoController(this);
    }
    public void prepareToUse() {
        turnOffStackPaneChildren(addDeleteFilesIoStackPane);
        addDeleteFilesIoButtons.setVisible(false);

        setCurrentMainButton(null);

        disableSecondarySection();
    }

    MyHashSet<Person> people = HashSetsHolder.getInstance().getPeople();
    MyHashSet<Course> courses = HashSetsHolder.getInstance().getCourses();
    public static Serializer<Course> serializerCourse = new Serializer<>();
    public static Serializer<Person> serializerPeople = new Serializer<>();

    public StackPane addRoleSpecificInfoStackPane;
    public VBox addStudentSpecificInfo;
    public VBox addEmployeeSpecificInfo;
    public StackPane addEmployeeSpecificInfoStackPane;
    public VBox addDidacticSpecificInfo;
    public VBox addAdministrationSpecificInfo;
    public VBox inputCourse;
    public StackPane addingStackPane;
    public Button deleteStudentButton;
    public Button deleteEmployeeButton;
    public Button deleteCourseButton;
    public VBox deleteVBox;
    public StackPane InputStackPane;
    public StackPane deletingStackPane;
    public VBox deletePerson;
    public StackPane deleteRoleSpecificStackPane;
    public VBox deleteStudentSpecificRadioButtons;
    public VBox deleteEmployeeSpecificRadioButtons;
    public RadioButton PersonLastNameRadioButton;
    public VBox deleteCourse;
    public ComboBox<DidacticEmployee> lecturerComboBox;
    @FXML
    private VBox inputPerson;
    @FXML
    private Button addDidacticButton;
    @FXML
    private Button addAdministrationButton;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private VBox inputVBox;
    @FXML
    private StackPane addDeleteFilesIoStackPane;
    @FXML
    private Button addingButton;
    @FXML
    private Button deletingButton;
    @FXML
    private Button filesIoButton;
    @FXML
    private VBox addDeleteFilesIoButtons;
    @FXML
    private HBox addButtonBox;
    @FXML
    private HBox deleteButtonBox;
    @FXML
    private HBox filesIoButtonBox;
    @FXML
    private ToggleGroup toggleStudentCriteria;
    @FXML
    private ToggleGroup toggleCourseCriteria;

    private Button currentMainButton = null;
    private Button currentSecondaryButton = null;
    private void setCurrentMainButton(Button newButton) {
        if (currentMainButton != null) {
            currentMainButton.setDisable(false);
        }
        currentMainButton = newButton;
        if (currentMainButton != null) {
            currentMainButton.setDisable(true);
        }
    }
    private void setCurrentSecondaryButton(Button newButton) {
        if (currentSecondaryButton != null) {
            currentSecondaryButton.setDisable(false);
        }
        currentSecondaryButton = newButton;
        if (currentSecondaryButton != null) {
            currentSecondaryButton.setDisable(true);
        }
    }

    private void disableSecondarySection() {
        turnOffStackPaneChildren(InputStackPane);
        setCurrentSecondaryButton(null);
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        restartAddDeleteFilesIoSection();
        addButtonBox.setVisible(true);
        setCurrentMainButton(addingButton);

        disableSecondarySection();
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        restartAddDeleteFilesIoSection();
        deleteButtonBox.setVisible(true);
        setCurrentMainButton(deletingButton);

        disableSecondarySection();
    }

    public void filesIoButtonClicked(ActionEvent actionEvent) {
        restartAddDeleteFilesIoSection();
        filesIoButtonBox.setVisible(true);
        setCurrentMainButton(filesIoButton);

        disableSecondarySection();
    }

    public void addStudentModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(addStudentButton);
        inputVBox.setVisible(true);

        turnOffStackPaneChildren(addingStackPane);
        inputPerson.setVisible(true);
        inputCourse.setManaged(false);

        turnOffStackPaneChildren(addRoleSpecificInfoStackPane);
        addStudentSpecificInfo.setVisible(true);
    }

    public void addDidacticModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(addDidacticButton);
        inputVBox.setVisible(true);

        turnOffStackPaneChildren(addingStackPane);
        inputPerson.setVisible(true);
        inputCourse.setManaged(false);

        turnOffStackPaneChildren(addRoleSpecificInfoStackPane);
        addEmployeeSpecificInfo.setVisible(true);

        turnOffStackPaneChildren(addEmployeeSpecificInfoStackPane);
        addDidacticSpecificInfo.setVisible(true);
    }
    public void addAdministrationModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(addAdministrationButton);
        inputVBox.setVisible(true);

        turnOffStackPaneChildren(addingStackPane);
        inputPerson.setVisible(true);
        inputCourse.setManaged(false);

        turnOffStackPaneChildren(addRoleSpecificInfoStackPane);
        addEmployeeSpecificInfo.setVisible(true);

        turnOffStackPaneChildren(addEmployeeSpecificInfoStackPane);
        addAdministrationSpecificInfo.setVisible(true);
    }

    public void addCourseModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(addCourseButton);
        inputVBox.setVisible(true);

        turnOffStackPaneChildren(addingStackPane);
        inputPerson.setManaged(false);
        inputCourse.setVisible(true);
    }

    private void restartAddDeleteFilesIoSection() {
        turnOffStackPaneChildren(addDeleteFilesIoStackPane);
        addDeleteFilesIoButtons.setVisible(true);
    }
    private void turnOffStackPaneChildren(StackPane elem) {
        for (Node children : elem.getChildren()) {
            children.setVisible(false);
            children.setManaged(true);
        }
    }




    public void addCourse() {
        System.out.println("JD");
    }
    public void addPerson() {
        System.out.println("XD");
    }

    public void deleteStudentModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(deleteStudentButton);
        deleteVBox.setVisible(true);
        inputVBox.setManaged(false);

        turnOffStackPaneChildren(deletingStackPane);
        deletePerson.setVisible(true);

        PersonLastNameRadioButton.setSelected(true);
        turnOffStackPaneChildren(deleteRoleSpecificStackPane);
        deleteStudentSpecificRadioButtons.setVisible(true);
        deleteEmployeeSpecificRadioButtons.setManaged(false);
    }

    public void deleteEmployeeModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(deleteEmployeeButton);
        deleteVBox.setVisible(true);
        inputVBox.setManaged(false);

        turnOffStackPaneChildren(deletingStackPane);
        deletePerson.setVisible(true);

        PersonLastNameRadioButton.setSelected(true);
        turnOffStackPaneChildren(deleteRoleSpecificStackPane);
        deleteEmployeeSpecificRadioButtons.setVisible(true);
        deleteStudentSpecificRadioButtons.setManaged(false);
    }

    public void deleteCourseModeButtonClicked(ActionEvent actionEvent) {
        setCurrentSecondaryButton(deleteCourseButton);
        deleteVBox.setVisible(true);
        inputVBox.setManaged(false);

        turnOffStackPaneChildren(deletingStackPane);
        deleteCourse.setVisible(true);
        deletePerson.setManaged(false);


        ObservableList<DidacticEmployee> availableLecturers = FXCollections.observableArrayList();
        /* SET lecturerComboBox */
        for (Person person : HashSetsHolder.getInstance().getPeople()) {
            if (person instanceof DidacticEmployee) {
                availableLecturers.add((DidacticEmployee) person);
            }
        }
        lecturerComboBox.setItems(availableLecturers);
    }

    private int getChosenRadioId(ToggleGroup group) {
        int i = 0;
        for (;i < group.getToggles().size(); i++) {
            if (group.getSelectedToggle().equals(group.getToggles().get(i))) {
                break;
            }
        }
        return i;
    }

    public void deletePerson(ActionEvent actionEvent) {
        System.out.println("DELETE XD");
        boolean deletingStudent = currentSecondaryButton == deleteStudentButton;
        int selectedId = getChosenRadioId(toggleStudentCriteria);
        String keyWord = textFieldStudentKeyWord.getText();
        HashMap<Integer, String> modeMap = new HashMap<Integer, String>() {
            {
                put(0, "lastName");
                put(1, "firstName");
                put(2, "indexNumber");
                put(3, "termNumber");
                put(4, "courseName");
                put(5, "job");
                put(6, "seniority");
                put(7, "salary");
                put(8, "publicationsNumber");
                put(9, "overtimeAmount");
            }
        };

        String mode = modeMap.get(selectedId);

        System.out.println(deletingStudent+" "+mode+" "+keyWord);

        Searcher<? extends Person, Person> searcher = new StudentSearcher();
        if (!deletingStudent) {
            searcher = new EmployeeSearcher();
        }

        ArrayList<Person> searchResult = new ArrayList<Person>(searcher.search(people, mode, keyWord));
        System.out.println(searchResult);

        searchResult.forEach(HashSetsHolder.getInstance()::remove);
        if (deletingStudent) {
            searchResult.forEach(person -> {
                Student student = (Student) person;
                ArrayList<Course> studentCourses = new ArrayList<>(student.getCourses());
                studentCourses.forEach(student::removeCourse);
            });
        }
        else {
            searchResult.forEach(person -> {
                if (person instanceof DidacticEmployee didacticEmployee) {
                    ArrayList<Course> lecturerCourses = new ArrayList<>(didacticEmployee.getCourses());
                    lecturerCourses.forEach(didacticEmployee::removeCourse);
                }
            });
        }
        int deletedCount = searchResult.size();
        showAlert("DELETED "+deletedCount+" "+(deletedCount > 1 ? "PEOPLE" : "PERSON")+"!");
    }

    public void deleteCourse(ActionEvent actionEvent) {
        System.out.println("DELETE JD");
        int selectedId = getChosenRadioId(toggleCourseCriteria);
        String keyWord = textFieldCourseKeyWord.getText();
        DidacticEmployee lecturer = lecturerComboBox.getSelectionModel().getSelectedItem();

        HashMap<Integer, String> modeMap = new HashMap<Integer, String>() {
            {
                put(0, "name");
                put(1, "ECTS");
                put(2, "courseCode");
                put(3, "lecturer");
            }
        };
        String mode = modeMap.get(selectedId);
        CourseSearcher searcher = new CourseSearcher();
        ArrayList<Course> searchResult = new ArrayList<>();

        if (selectedId == 3) {
            if (lecturer == null) {
                System.out.println("LECTURER HASN'T BEEN CHOSEN");
                return;
            } else {
                searchResult.addAll(searcher.search(courses, lecturer));
            }
        }
        else {
            searchResult.addAll(searcher.search(courses, mode, keyWord));
        }
        System.out.println(searchResult);

        searchResult.forEach(HashSetsHolder.getInstance()::remove);
        searchResult.forEach(course -> {
            ArrayList<Student> courseStudents = new ArrayList<>(course.getStudents());
            courseStudents.forEach(course::removeStudent);
        });

        int deletedCount = searchResult.size();
        showAlert("DELETED "+deletedCount+" "+(deletedCount > 1 ? "COURSES" : "COURSE")+"!");
    }

    private void showAlert(String infoString) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(infoString);
        alert.showAndWait();
    }
    public void readFromFile(ActionEvent actionEvent) {
        HashSetsHolder.getInstance().setPeople(serializerPeople.readFromFile("people.txt"));
        HashSetsHolder.getInstance().setCourses(serializerCourse.readFromFile("courses.txt"));
        showAlert("DATA READ FROM FILES!");
    }

    public void safeToFile(ActionEvent actionEvent) {
        serializerCourse.saveToFile(courses, "courses.txt");
        serializerPeople.saveToFile(people, "people.txt");
        showAlert("DATA WRITTEN TO FILES!");
    }
}
