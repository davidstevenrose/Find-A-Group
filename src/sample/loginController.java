package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

  @FXML private PasswordField passwordField;

  @FXML private TextField usernameField;

  @FXML private Button loginButton;

  @FXML private Button createAccountButton;

  @FXML private Label errorLabel;

  @FXML private Label confirmPasswordLabel;

  @FXML private PasswordField confirmPasswordField;

  /**
   * This method allows the user to log into their account and will change the scene to the primary
   * scene
   *
   * @param event
   */
  @FXML
  void loginClicked(MouseEvent event) throws IOException {
    // Creating the new scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    // Getting the stage
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    window.setScene(primaryScreen);
    window.show();
  }
}
