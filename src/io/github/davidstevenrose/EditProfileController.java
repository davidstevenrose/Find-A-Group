package io.github.davidstevenrose;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This is the controller class for the edit profile screen. In this screen, users
 * will be able to edit their username, email, and password. This class features a lengthy if-else
 * chain that will check to make sure that the user has not left any fields blank, as well as if the
 * passwords are correct and match.
 *
 * @author Nicholas Hansen + Darian
 */
public class EditProfileController {

  @FXML
  private Group editingFields;

  @FXML private PasswordField newPassword;

  @FXML private PasswordField confirmPassword;

  @FXML private PasswordField oldPassword;

  @FXML private Label passwordError;

  @FXML private TextField userEmail;

  @FXML private TextField editUsernameField;

  @FXML
  void cancelChanges(MouseEvent event) throws IOException {
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(primaryScreen);
    window.show();
  }

  @FXML
  void submitChanges(MouseEvent event) throws IOException {

    // Setting up variables so things are easier to read and write
    String oldPassField = oldPassword.getText();
    String newPassField = newPassword.getText();
    String confirmPassField = confirmPassword.getText();
    String userField = editUsernameField.getText();
    String emailField = userEmail.getText();

    // If ALL the text fields are empty, this occurs
    if (oldPassField.isEmpty()
        && confirmPassField.isEmpty()
        && newPassField.isEmpty()
        && userField.isEmpty()
        && emailField.isEmpty()) {
      passwordError.setText("Fields cannot be empty!");
      passwordError.setVisible(true);
      newPassword.setStyle("-fx-border-color: blue; -fx-border-width: 2");
      confirmPassword.setStyle("-fx-border-color: blue; -fx-border-width: 2");
      userEmail.setStyle("-fx-border-color: blue; -fx-border-width: 2");
      editUsernameField.setStyle("-fx-border-color: blue; -fx-border-width: 2");
      oldPassword.setStyle("-fx-border-color: blue; -fx-border-width: 2");
    }
    // If the two old passwords match, but there is no new password, the following occurs:
    // Note the program is still comparing the values to the current user's passwords.
    else if (oldPassField.matches(MainScreenController.currentUser.getPassword())
        && confirmPassField.matches(MainScreenController.currentUser.getPassword())
        && (!oldPassField.isEmpty() && !confirmPassField.isEmpty())) {
      passwordError.setVisible(true);
      passwordError.setText("Set a new password.");
      newPassword.setStyle("-fx-border-color: purple; -fx-border-width: 2");
    }
    // If any of the fields are empty and the submit button is pressed, the following occurs:
    else if (oldPassField.isEmpty()
        || confirmPassField.isEmpty()
        || newPassField.isEmpty()
        || userField.isEmpty()
        || emailField.isEmpty()) {
      // These statements make sure that the styles are reset each time it's called
      // So the user knows what appropriate fields are still empty.
      newPassword.setStyle("");
      confirmPassword.setStyle("");
      userEmail.setStyle("");
      editUsernameField.setStyle("");
      oldPassword.setStyle("");
      if (oldPassField.isEmpty()) {
        // Checks if the old password field is empty
        oldPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      }
      if (confirmPassField.isEmpty()) {
        // Checks if the confirm password field is empty
        confirmPassword.setStyle("-fx-border-color: red;-fx-border-width: 2");
      }
      if (newPassField.isEmpty()) {
        // Checks if the new password field is empty
        newPassword.setStyle("-fx-border-color: red;-fx-border-width: 2");
      }
      if (userField.isEmpty()) {
        // Checks if the username field is empty
        editUsernameField.setStyle("-fx-border-color: red; -fx-border-width: 2");
      }
      if (emailField.isEmpty()) {
        // Checks if the email field is empty
        userEmail.setStyle("-fx-border-color: red; -fx-border-width: 2");
      }
      // Displays the error message with indication of what's missing.
      passwordError.setVisible(true);
      passwordError.setText("These fields cannot be blank.");
    }

    // If the old password does not match the confirm but the fields are not empty, the following
    // occurs:
    // Note that it is still checking the current user's passwords.
    else if (!oldPassField.matches(confirmPassField) && !confirmPassField.matches(oldPassField)) {
      passwordError.setVisible(true);
      passwordError.setText("Passwords do not match.");
      oldPassword.setStyle("-fx-border-color: green;-fx-border-width: 2");
      confirmPassword.setStyle("-fx-border-color: green;-fx-border-width: 2");
      newPassword.setStyle("");
    }

    // If the two old passwords don't match the current user's password but are filled out
    // The following occurs
    else if (!oldPassField.matches(MainScreenController.currentUser.getPassword())
        || !confirmPassField.matches(MainScreenController.currentUser.getPassword())
        || (!oldPassField.matches(MainScreenController.currentUser.getPassword())
            && !confirmPassField.matches(MainScreenController.currentUser.getPassword()))) {
      passwordError.setText("Invalid Password(s)");
      passwordError.setVisible(true);
      // Determine which one does not match the current user's password
      if (!oldPassField.matches(MainScreenController.currentUser.getPassword())) {
        oldPassword.setStyle("-fx-border-color: purple; -fx-border-width: 2");
      }
      if (!confirmPassField.matches(MainScreenController.currentUser.getPassword())) {
        confirmPassword.setStyle("-fx-border-color: purple; -fx-border-width: 2");
      }
      newPassword.setStyle("");
    }

    // Note that I changed equals to matches, as a=b in terms of .equals for strings.
    // Matches will force the program to compare them letter by letter due to it being a regex.
    if (oldPassField.matches(MainScreenController.currentUser.getPassword())
        && confirmPassField.matches(MainScreenController.currentUser.getPassword())
        && !newPassword.getText().isEmpty()
        && !editUsernameField.getText().isEmpty()
        && !userEmail.getText().isEmpty()) {
      MainScreenController.currentUser.setPassword(newPassword.getText());
      MainScreenController.currentUser.setEmail(userEmail.getText());
      MainScreenController.currentUser.setUsername(editUsernameField.getText());

      // Saving the changes to users.txt file
      TextFileManager.editUser(LoginController.users);

      Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
      Scene primaryScreen = new Scene(primaryScreenParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      window.setScene(primaryScreen);
      window.show();
    }
  }

  @FXML
  void initialize() {
    userEmail.setText(MainScreenController.currentUser.getEmail());
    editUsernameField.setText(MainScreenController.currentUser.getUsername());
  }
}
