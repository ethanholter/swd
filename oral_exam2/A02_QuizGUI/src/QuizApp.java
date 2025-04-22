import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Main class for the QuizGUI application.
 */
public class QuizApp extends Application {

    /**
     * Main method for the QuizGUI application. Typically unused but provides redundancy.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates FXML window and displays it to the user.
     * @param primaryStage the stage to display the FXML window on
     */
    @Override
    public void start(Stage primaryStage){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("QuizLayout.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        primaryStage.setTitle("Get Smart With Ethan");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
