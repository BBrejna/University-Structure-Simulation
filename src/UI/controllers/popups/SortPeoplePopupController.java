package UI.controllers.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SortPeoplePopupController {
    private Stage stage;
    int answer=0;


    public int displayPopup(Stage stage, Parent root) {
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose sorting option");

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



    @FXML
    private void initialize(){

    }


    public void onMode1Chosen(ActionEvent actionEvent) {
        answer = 1;
        closeWindow();
    }

    public void onMode2Chosen(ActionEvent actionEvent) {
        answer = 2;
        closeWindow();
    }

    public void onMode3Chosen(ActionEvent actionEvent) {
        answer = 3;
        closeWindow();
    }
}