package UI.controllers;

import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tools.HashSetsHolder;
import uni.Course;
import uni.DidacticEmployee;
import uni.Person;
import uni.Student;

public class ManageCoursesController {

    public void initialize() {
        ControllersHandler.getInstance().setManageCoursesController(this);
    }

    public void prepareToUse() {
        turnOffStackPaneChildren(studentDidacticCourseStackPane);
        studentDidacticCourseButtons.setVisible(false);

        setCurrentMainButton(null);

        disableSecondarySection();
    }

    public Button addToCourseButton;
    public Button removeFromCourseButton;
    public Button courseManagementButton;
    public VBox studentDidacticCourseButtons;
    public StackPane studentDidacticCourseStackPane;
    public HBox addToCourseButtonBox;
    public HBox removeFromCourseButtonBox;
    public HBox courseButtonBox;
    public Button addStudentCourseButton;
    public Button addLecturerCourseButton;
    public Button removeStudentCourseButton;
    public Button removeLecturerCourseButton;
    public Button startCourseButton;
    public Button finishCourseButton;
    public StackPane MainStackPane;
    public VBox addToCourse;
    public ComboBox<Student> studentComboBox;
    public ComboBox<DidacticEmployee> lecturerComboBox;
    public Label addStudentCourseTextBox;
    public ComboBox<Course> courseComboBox;
    public Button addDeleteButton;
    public VBox manageCourse;
    public ComboBox<Course> courseManageComboBox;
    public Button startFinishButton;

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
        turnOffStackPaneChildren(MainStackPane);
        setCurrentSecondaryButton(null);
    }

    private void restartStudentDidacticCourseButtons() {
        turnOffStackPaneChildren(studentDidacticCourseStackPane);
        studentDidacticCourseButtons.setVisible(true);
    }
    private void turnOffStackPaneChildren(StackPane elem) {
        for (Node children : elem.getChildren()) {
            children.setVisible(false);
            children.setManaged(true);
        }
    }

    public void addToCourseButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        addToCourseButtonBox.setVisible(true);
        setCurrentMainButton(addToCourseButton);
        
        disableSecondarySection();
    }

    public void removeFromCourseButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        removeFromCourseButtonBox.setVisible(true);
        setCurrentMainButton(removeFromCourseButton);

        disableSecondarySection();
    }

    public void courseManagementButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        courseButtonBox.setVisible(true);
        setCurrentMainButton(courseManagementButton);

        disableSecondarySection();
    }


    private void loadCourses(ComboBox<Course> elem) {
        ObservableList<Course> availableCourses = FXCollections.observableArrayList();
        /* SET lecturerComboBox */
        availableCourses.addAll(HashSetsHolder.getInstance().getCourses());

        elem.setItems(availableCourses);
    }

    // mode 1 jak student ma być w kursie, 0 jak ma nie byc
    private void loadStudents(ComboBox<Student> elem, Course course, boolean mode) {
        ObservableList<Student> availableStudents = FXCollections.observableArrayList();
        for (Person person : HashSetsHolder.getInstance().getPeople()) {
            if (person instanceof Student student) {
                if (mode && student.getCourses().contains(course)) {
                    availableStudents.add(student);
                }
                if (!mode && !student.getCourses().contains(course)) {
                    availableStudents.add(student);
                }
            }
        }
        elem.setItems(availableStudents);
    }
    private void loadLecturers(ComboBox<DidacticEmployee> elem, Course course, boolean mode) {
        ObservableList<DidacticEmployee> availableLecturers = FXCollections.observableArrayList();
        if (mode) {
            availableLecturers.add(course.getLecturer());
        } else {
            for (Person person : HashSetsHolder.getInstance().getPeople()) {
                if (person instanceof DidacticEmployee didacticEmployee) {
                    if (!didacticEmployee.equals(course.getLecturer())) {
                        availableLecturers.add(didacticEmployee);
                    }

                }
            }
        }
        elem.setItems(availableLecturers);
    }
    public void courseChosenInCombo(ActionEvent actionEvent) {
        Course currentCourse = courseComboBox.getSelectionModel().getSelectedItem();
        if (currentCourse == null) return;
        boolean mode = currentMainButton == removeFromCourseButton;
        if (currentSecondaryButton == addStudentCourseButton || currentSecondaryButton == removeStudentCourseButton) {
            loadStudents(studentComboBox, currentCourse, mode);
        } else {
            loadLecturers(lecturerComboBox, currentCourse, mode);
        }
    }
    private void clearAddComboBoxes() {
        courseComboBox.getItems().clear();
        studentComboBox.getItems().clear();
        lecturerComboBox.getItems().clear();
        courseManageComboBox.getItems().clear();
    }
    public void addStudentModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(addStudentCourseButton);
        loadCourses(courseComboBox);

        turnOffStackPaneChildren(MainStackPane);
        addToCourse.setVisible(true);

        studentComboBox.setManaged(true);
        studentComboBox.setVisible(true);
        lecturerComboBox.setManaged(false);
        lecturerComboBox.setVisible(false);
        addStudentCourseTextBox.setText("Student:");

        addDeleteButton.setText("Add");
    }

    public void addLecturerModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(addLecturerCourseButton);
        loadCourses(courseComboBox);

        turnOffStackPaneChildren(MainStackPane);
        addToCourse.setVisible(true);

        studentComboBox.setManaged(false);
        studentComboBox.setVisible(false);
        lecturerComboBox.setManaged(true);
        lecturerComboBox.setVisible(true);
        addStudentCourseTextBox.setText("Lecturer:");

        addDeleteButton.setText("Add");
    }

    public void removeStudentModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(removeStudentCourseButton);
        loadCourses(courseComboBox);

        turnOffStackPaneChildren(MainStackPane);
        addToCourse.setVisible(true);

        studentComboBox.setManaged(true);
        studentComboBox.setVisible(true);
        lecturerComboBox.setManaged(false);
        lecturerComboBox.setVisible(false);
        addStudentCourseTextBox.setText("Student:");

        addDeleteButton.setText("Delete");
    }

    public void removeDidacticModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(removeLecturerCourseButton);
        loadCourses(courseComboBox);

        turnOffStackPaneChildren(MainStackPane);
        addToCourse.setVisible(true);

        studentComboBox.setManaged(false);
        studentComboBox.setVisible(false);
        lecturerComboBox.setManaged(true);
        lecturerComboBox.setVisible(true);
        addStudentCourseTextBox.setText("Lecturer:");

        addDeleteButton.setText("Delete");
    }

    private void loadStartFinishCombo(boolean mode) {
        ObservableList<Course> availableCourses = FXCollections.observableArrayList();
        for (Course course : HashSetsHolder.getInstance().getCourses()) {
            if (mode && !course.getCourseState().isStarted()) {
                availableCourses.add(course);
            }
            if (!mode && course.getCourseState().isStarted() && !course.getCourseState().isFinished()) {
                availableCourses.add(course);
            }
        }
        courseManageComboBox.setItems(availableCourses);
    }

    public void startModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(startCourseButton);

        turnOffStackPaneChildren(MainStackPane);
        manageCourse.setVisible(true);
        addToCourse.setManaged(false);

        startFinishButton.setText("Start");
        loadStartFinishCombo(true);
    }

    public void finishModeButtonClicked(ActionEvent actionEvent) {
        clearAddComboBoxes();
        setCurrentSecondaryButton(finishCourseButton);

        turnOffStackPaneChildren(MainStackPane);
        addToCourse.setManaged(false);
        manageCourse.setVisible(true);

        startFinishButton.setText("Finish");
        loadStartFinishCombo(false);
    }

    public void addDeleteStudentLecturer(ActionEvent actionEvent) {
    }

    public void startFinishCourse(ActionEvent actionEvent) {
    }
}
