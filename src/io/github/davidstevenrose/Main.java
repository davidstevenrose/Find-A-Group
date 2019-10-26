package io.github.davidstevenrose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that holds the main method.
 * <h1>Main Controller</h1>
 * <p>On application startup, this class will load the log-in fxml file and controller.
 *<br />
 * <i>Note: all non-java source files should be put into the resource folder.</i>
 * </p>
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../../../res/loginPage.fxml"));
        primaryStage.setTitle("2'sday Find-A-Group");
        primaryStage.setScene(new Scene(root, 300, 275));
        //the log-in page is not resizable. bool literal should be stored as a constant.
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
