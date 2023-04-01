package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class GUI extends Application {

    private static Scene scene;
    private static final int width = 960;
    private static final int height = 540;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("startscene"), width, height);
        //scene = new Scene(loadFXML("adminfinancescene"), width, height);
        stage.setResizable(false);
        stage.setTitle("Мебельный салон");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}