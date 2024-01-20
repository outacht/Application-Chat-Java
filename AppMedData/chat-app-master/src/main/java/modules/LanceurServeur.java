package modules;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LanceurServeur extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(@org.jetbrains.annotations.NotNull Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ServerForm.fxml"))));
        primaryStage.setTitle("Serveur");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
