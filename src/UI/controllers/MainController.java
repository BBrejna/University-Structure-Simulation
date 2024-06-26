package UI.controllers;

import UI.controllers.popups.CloseConfirmPopupController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button ioButton;
    @FXML
    private Button manageCoursesButton;
    @FXML
    private Button wssButton;
    @FXML
    private Parent ioContent;
    @FXML
    private Parent manageCoursesContent;
    @FXML
    private Parent wssContent;

    private Button disabledButton;
    private Parent currentContent;

    private void changeView(Button newButton, Parent newContent) {
        disabledButton.setDisable(false);
        disabledButton.getStyleClass().clear();
        disabledButton.getStyleClass().addAll("button","RoleButton");
        disabledButton = newButton;
        disabledButton.setDisable(true);
        disabledButton.getStyleClass().add("activeButton");

        currentContent.setVisible(false);
        currentContent.setManaged(false);
        currentContent = newContent;
        currentContent.setVisible(true);
        currentContent.setManaged(true);
    }

    public void showIoContent(ActionEvent actionEvent) {
        changeView(ioButton, ioContent);
        ControllersHandler.getInstance().getIoController().prepareToUse();
    }

    public void showManageCoursesContent(ActionEvent actionEvent) {
        changeView(manageCoursesButton, manageCoursesContent);
        ControllersHandler.getInstance().getManageCoursesController().prepareToUse();
    }

    public void showWssContent(ActionEvent actionEvent) {
        changeView(wssButton, wssContent);
        ControllersHandler.getInstance().getWssController().prepareToUse();
    }

    public void initialize() {
        ControllersHandler.getInstance().setMainController(this);

        disabledButton = ioButton;
        disabledButton.getStyleClass().add("activeButton");
        currentContent = ioContent;
    }

    public boolean handleCloseButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/views/popups/CloseConfirmPopupView.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();

        CloseConfirmPopupController popupController = loader.getController();

        return (popupController.displayPopup(popupStage, root));

    }
}
