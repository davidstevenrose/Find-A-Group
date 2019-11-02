package io.github.davidstevenrose;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that holds the main method.
 * <h1>Main Controller</h1>
 * <p>On application startup, this class will load the log-in fxml file and controller.
 * </p>
 */
public class Main extends Application {

  /**
   * Field added by Cameron.
   */
  private String fieldOne;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
    primaryStage.setTitle("2sday's Find A Group");
    primaryStage.setScene(new Scene(root, 675, 600));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
