package UI.controllers.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchPeoplePopupController {
    public TextField textFieldKeyWord;
    @FXML
    private ToggleGroup toggleSearchCriteria;
    private Stage stage;
    int answer=0;
    String keyWord="";

    public String getKeyWord() {
        return keyWord;
    }
    public int displayPopup(Stage stage, Parent root) {
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose searching option");

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

    public void submitButton(ActionEvent actionEvent) {
        RadioButton chosen = (RadioButton) toggleSearchCriteria.getSelectedToggle();
        int i = 0;
        for (; i < toggleSearchCriteria.getToggles().size(); i++) {
            RadioButton current = (RadioButton) toggleSearchCriteria.getToggles().get(i);
            if (chosen.equals(current)) {
                break;
            }
        }
        answer=i;
        keyWord = textFieldKeyWord.getText();
        closeWindow();
    }
}
