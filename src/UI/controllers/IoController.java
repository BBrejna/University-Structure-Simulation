package UI.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tools.HashSetsHolder;
import tools.MyHashSet;
import tools.Serializer;
import uni.Course;
import uni.DidacticEmployee;
import uni.Person;

import java.util.ArrayList;

public class IoController {

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

    private Button currentMainButton = null;
    private Button currentSecondaryButton = null;
    private void setCurrentMainButton(Button newButton) {
        if (currentMainButton != null) {
            currentMainButton.setDisable(false);
        }
        currentMainButton = newButton;
        currentMainButton.setDisable(true);
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

    public void deletePerson(ActionEvent actionEvent) {
        System.out.println("DELETE XD");
    }

    public void deleteCourse(ActionEvent actionEvent) {
        System.out.println("DELETE JD");
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
