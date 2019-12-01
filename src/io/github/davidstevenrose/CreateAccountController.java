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
    //hot fix - David
    if (!password.matches(MainScreenController.P_WORD_REGEX)) {
      errorLabel.setText("Your password needs at least 7 characters");
      Main.fadeAway(errorLabel);
      return;
    }
    if (!email.matches(MainScreenController.EMAIL_REGEX)) {
      errorLabel.setText("Your email is an invalid address");
      Main.fadeAway(errorLabel);
      return;
    }
    //Stub code ===============================
    // check if information added correctly
    if (!password.equals(confirmPassword)) {
      errorLabel.setText("Password do not match");
      Main.fadeAway(errorLabel);
    } else if (!email.equals(confirmEmail)) {
      errorLabel.setText("Emails do not match");
      Main.fadeAway(errorLabel);
    } else if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
      //if username is taken - David
      for (User u : LoginController.users) {
        if (u.getUsername().equals(username)) {
          errorLabel.setText("That username is already taken");
          Main.fadeAway(errorLabel);
          return;
        }
      }
      // write to text file
      TextFileManager.addUserToFile(new User(username, password, email));

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
      Main.fadeAway(errorLabel);
    }
    //end stub code =========================
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
