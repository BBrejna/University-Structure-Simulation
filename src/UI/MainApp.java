package UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            Platform.exit();
            System.exit(0);
        });
//        stage.getIcons().add(new Image("/resources/icon.png"));
        stage.setTitle("University");
        stage.setScene(scene);
        stage.show();
    }
}