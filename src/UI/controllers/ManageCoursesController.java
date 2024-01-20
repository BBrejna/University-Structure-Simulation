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

public class ManageCoursesController extends AbstractController {

    public void initialize() {
        ControllersHandler.getInstance().setManageCoursesController(this);
    }

    public void prepareToUse() {
        turnOffStackPaneChildren(studentDidacticCourseStackPane);
        studentDidacticCourseButtons.setVisible(false);

        setCurrentMainButton(null);

        disableSecondarySection(MainStackPane);
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

    private void restartStudentDidacticCourseButtons() {
        turnOffStackPaneChildren(studentDidacticCourseStackPane);
        studentDidacticCourseButtons.setVisible(true);
    }

    public void addToCourseButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        addToCourseButtonBox.setVisible(true);
        setCurrentMainButton(addToCourseButton);
        
        disableSecondarySection(MainStackPane);
    }

    public void removeFromCourseButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        removeFromCourseButtonBox.setVisible(true);
        setCurrentMainButton(removeFromCourseButton);

        disableSecondarySection(MainStackPane);
    }

    public void courseManagementButtonClicked(ActionEvent actionEvent) {
        restartStudentDidacticCourseButtons();
        courseButtonBox.setVisible(true);
        setCurrentMainButton(courseManagementButton);

        disableSecondarySection(MainStackPane);
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
    public void addStudentModeButtonClicked() {
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

    public void addLecturerModeButtonClicked() {
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

    public void removeStudentModeButtonClicked() {
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

    public void removeDidacticModeButtonClicked() {
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
        Course course = courseComboBox.getSelectionModel().getSelectedItem();
        Student student = studentComboBox.getSelectionModel().getSelectedItem();
        DidacticEmployee lecturer = lecturerComboBox.getSelectionModel().getSelectedItem();

        if (addStudentCourseButton == currentSecondaryButton) {
            if (course == null || student == null) {
                System.out.println("COURSE OR STUDENT HASN'T BEEN CHOSEN");
                return;
            }
            course.addStudent(student);
            showAlert("Student added to the course!");
            addStudentModeButtonClicked();
        }
        else if (addLecturerCourseButton == currentSecondaryButton) {
            if (course == null || lecturer == null) {
                System.out.println("COURSE OR LECTURER HASN'T BEEN CHOSEN");
                return;
            }
            course.setLecturer(lecturer);
            showAlert("Lecturer assigned to the course!");
            addLecturerModeButtonClicked();
        }
        else if (removeStudentCourseButton == currentSecondaryButton) {
            if (course == null || student == null) {
                System.out.println("COURSE OR STUDENT HASN'T BEEN CHOSEN");
                return;
            }
            course.removeStudent(student);
            showAlert("Student removed from the course!");
            removeStudentModeButtonClicked();
        }
        else if (removeLecturerCourseButton == currentSecondaryButton) {
            if (course == null || lecturer == null) {
                System.out.println("COURSE OR LECTURER HASN'T BEEN CHOSEN");
                return;
            }
            course.removeLecturer();
            showAlert("Lecturer removed from the course!");
            removeDidacticModeButtonClicked();
        }
    }

    public void startFinishCourse(ActionEvent actionEvent) {
        Course course = courseManageComboBox.getSelectionModel().getSelectedItem();
        if (course == null) {
            System.out.println("COURSE HASN'T BEEN CHOSEN");
        } else {
            if (startCourseButton.isDisabled()) {
                course.startCourse();
                showAlert("Course has been started!");
            } else {
                course.finishCourse();
                showAlert("Course has been finished!");
            }
        }
        loadStartFinishCombo(true);
    }
}
