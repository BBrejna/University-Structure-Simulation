package UI.controllers;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public abstract class AbstractController {
    protected void showAlert(String infoString) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(infoString);
        alert.showAndWait();
    }
    protected int getChosenRadioId(ToggleGroup group) {
        int i = 0;
        for (;i < group.getToggles().size(); i++) {
            if (group.getSelectedToggle().equals(group.getToggles().get(i))) {
                break;
            }
        }
        return i;
    }
    protected void turnOffStackPaneChildren(StackPane elem) {
        for (Node children : elem.getChildren()) {
            children.setVisible(false);
            children.setManaged(true);
        }
    }
    protected Button currentMainButton = null;
    protected Button currentSecondaryButton = null;
    protected void setCurrentMainButton(Button newButton) {
        if (currentMainButton != null) {
            currentMainButton.setDisable(false);
        }
        currentMainButton = newButton;
        if (currentMainButton != null) {
            currentMainButton.setDisable(true);
        }
    }
    protected void setCurrentSecondaryButton(Button newButton) {
        if (currentSecondaryButton != null) {
            currentSecondaryButton.setDisable(false);
        }
        currentSecondaryButton = newButton;
        if (currentSecondaryButton != null) {
            currentSecondaryButton.setDisable(true);
        }
    }

    protected void disableSecondarySection(StackPane elem) {
        turnOffStackPaneChildren(elem);
        setCurrentSecondaryButton(null);
    }

    public static boolean isNumeric(String str, boolean mode) {
        if (str == null) return false;
        if (!mode && str.contains(".")) return false;
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean isInt(String str) {
        return isNumeric(str, false);
    }
    public static boolean isNumeric(String str) {
        return isNumeric(str, true);
    }
    public static boolean isPositive(String str) {
        if (!isNumeric(str)) return false;
        double strD = Double.parseDouble(str);
        return strD >= 0;
    }
    public static boolean isPositiveInt(String str) {
        if (!isInt(str)) return false;
        int strI = Integer.parseInt(str);
        return strI >= 0;
    }

}
