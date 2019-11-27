package io.github.davidstevenrose;

import java.io.IOException;
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

public class CreateAccountController {

  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField usernameField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField confirmEmailField;

  @FXML
  private Label errorLabel;

  @FXML
  private Button createAccountButton;

  @FXML
  private Button cancelAccountButton;

  /**
   * Creates a new User object and saves it to the text file.
   *
   * @param event the event that called this method
   * @throws IOException thrown when fxml file to be loaded is not found
   * @author drose
   */
  @FXML
  void createAccountButtonClicked(MouseEvent event) throws IOException {
    // get user input data and set as local variables
    errorLabel.setVisible(true);
    String username = usernameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();
    String email = emailField.getText();
    String confirmEmail = confirmEmailField.getText();

    // check if information added correctly
    if (!password.equals(confirmPassword)) {
      errorLabel.setText("Password do not match");
    } else if (!email.equals(confirmEmail)) {
      errorLabel.setText("Emails do not match");
    } else if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
      // write to database
      TextFileManager.addUserToFile(new User(username, password, email));

      //this stub is obsolete because the static field 'users' is
      // initialized to an empty list every time the scene changes. - David
      //LoginController.users.add(new User(username, password, email));

      // Creating scene
      Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
      Scene primaryScreen = new Scene(primaryScreenParent);

      // Getting the stage
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Setting stage and displaying
      window.setScene(primaryScreen);
      window.show();
    } else {
      passwordField.setStyle("-fx-base:mediumvioletred");
      confirmPasswordField.setStyle("-fx-base:mediumvioletred");
      emailField.setStyle("-fx-base:mediumvioletred");
      confirmEmailField.setStyle("-fx-base:mediumvioletred");
      usernameField.setStyle("-fx-base:mediumvioletred");
      errorLabel.setText("Please complete all fields");
    }
  }

  @FXML
  void cancelAccountButtonClicked(MouseEvent event) throws IOException {

    // Creating scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    // Getting the stage
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Setting stage and displaying
    window.setScene(primaryScreen);
    window.show();
  }
}
