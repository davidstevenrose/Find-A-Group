package io.github.davidstevenrose;

import static javafx.util.Duration.seconds;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that holds the main method.
 *
 * <h1>Some Thoughts</h1>
 *
 * <p>Our SRS specifies that we must direct the client to the profile page after logon, but we have
 * no fxml for the profile page (Statement 13). We also must revisit the 'group finding' mechanism
 * the client will be using. I.E. should the user only search with up to four group tags?
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
    primaryStage.setTitle("2sday's Find A Group");
    primaryStage.setScene(new Scene(root, 650, 500));
    primaryStage.show();
  }

  /**
   * This method will take in a node value (fx:id) and apply the fadeTransition to it. This means
   * that the node will gradually become 100% transparent over the course of (6) seconds and the
   * animation will not repeat itself unless the action is called multiple times.
   *
   * <p>This method is in main to save everyone long lines of code but also because it doesn't
   * pertain to any controller per se, it will be used multiple times in multiple classes. Putting
   * it in Main just seems like an easy way for re-usable code.
   *
   * @param node the node which you wish to have the fade to transparent animation applied to
   * @author Nicholas Hansen
   */
  static void fadeAway(Node node) {
    FadeTransition fadeTransition = new FadeTransition();
    fadeTransition.setNode(node);
    fadeTransition.setDuration(seconds(6));
    fadeTransition.setFromValue(1);
    fadeTransition.setToValue(0);
    fadeTransition.setAutoReverse(false);
    fadeTransition.setCycleCount(1);
    fadeTransition.play();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
