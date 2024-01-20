package UI.controllers.popups;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tools.HashSetsHolder;
import uni.DidacticEmployee;
import uni.Person;

public class SearchCoursesPopupController {
    public TextField textFieldKeyWord;
    public ComboBox<DidacticEmployee> lecturerComboBox;
    @FXML
    private ToggleGroup toggleSearchCriteria;
    private Stage stage;
    int answer=0;
    String keyWord="";
    DidacticEmployee lecturer=null;

    public DidacticEmployee getLecturer() {
        return lecturer;
    }

    public String getKeyWord() {
        return keyWord;
    }
    public int displayPopup(Stage stage, Parent root) {
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose searching option");

        loadLecturers();

        stage.setOnCloseRequest(e -> {
            e.consume();
            answer=-1;
            closeWindow();
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/UI/resources/icon.png"));
        stage.showAndWait();
        return answer;
    }

    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }

    private void loadLecturers() {
        ObservableList<DidacticEmployee> availableLecturers = FXCollections.observableArrayList();
        for (Person person : HashSetsHolder.getInstance().getPeople()) {
            if (person instanceof DidacticEmployee didacticEmployee) {
                availableLecturers.add(didacticEmployee);
            }
        }
        lecturerComboBox.setItems(availableLecturers);
    }

    public void submitButton(ActionEvent actionEvent) {
        RadioButton chosen = (RadioButton) toggleSearchCriteria.getSelectedToggle();
        int i = 0;
        for (; i < toggleSearchCriteria.getToggles().size(); i++) {
            RadioButton current = (RadioButton) toggleSearchCriteria.getToggles().get(i);
            if (chosen.equals(current)) {
                break;
            }
        }
        lecturer = lecturerComboBox.getSelectionModel().getSelectedItem();
        answer=i;
        keyWord = textFieldKeyWord.getText();
        closeWindow();
    }
}
