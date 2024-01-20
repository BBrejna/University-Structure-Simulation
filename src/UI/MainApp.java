package UI;

import UI.controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setOnCloseRequest(e -> {
            e.consume();
            try {
                if (((MainController) loader.getController()).handleCloseButtonClick()) {
                    Platform.exit();
                    System.exit(0);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        stage.getIcons().add(new Image("/UI/resources/icon.png"));
        stage.setTitle("University");
        stage.setScene(scene);
        stage.show();
    }
}