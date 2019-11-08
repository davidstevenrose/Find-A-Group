package io.github.davidstevenrose;

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

/**
 * This is the controller class for the edit profile screen
 * @author Nicholas Hansen + Darian
 */
public class EditProfileController {

  @FXML private PasswordField newPassword;

  @FXML private Button btnSubmit;

  @FXML private Button returnProfile;

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
    if (oldPassword.getText().isEmpty()
            && confirmPassword.getText().isEmpty()
            && newPassword.getText().isEmpty()) {
      passwordError.setText("Please input your password to continue!");
      oldPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      confirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      passwordError.setVisible(true);
    } else if (oldPassword.getText().isEmpty()
            || confirmPassword.getText().isEmpty()
            || newPassword.getText().isEmpty()) {
      passwordError.setText("Fields cannot be empty");
      passwordError.setVisible(true);
      oldPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      confirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
    }
    if (!oldPassword.getText().equals(confirmPassword.getText())) {
      passwordError.setText("Passwords do not match!");
      passwordError.setVisible(true);
      oldPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      confirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
      newPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
    }
    if (editUsernameField.getText().isEmpty()
            && userEmail.getText().isEmpty()
            && newPassword.getText().isEmpty()) {
      passwordError.setText("Fields cannot be empty");
      passwordError.setVisible(true);
      editUsernameField.setStyle("-fx-border-color: red; -fx-border-width: 2");
      userEmail.setStyle("-fx-border-color: red; -fx-border-width: 2");
      newPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
    } else if (editUsernameField.getText().isEmpty()
            || userEmail.getText().isEmpty()
            || newPassword.getText().isEmpty()) {
      passwordError.setText("Fields cannot be empty");
      passwordError.setVisible(true);
      editUsernameField.setStyle("-fx-border-color: red; -fx-border-width: 2");
      userEmail.setStyle("-fx-border-color: red; -fx-border-width: 2");
      newPassword.setStyle("-fx-border-color: red; -fx-border-width: 2");
    }
    if (oldPassword.getText().equals(MainScreenController.currentUser.getPassword())
            && confirmPassword.getText().equals(MainScreenController.currentUser.getPassword())
            && !newPassword.getText().isEmpty()
            && !editUsernameField.getText().isEmpty()
            && !userEmail.getText().isEmpty()) {

      MainScreenController.currentUser.setPassword(newPassword.getText());
      MainScreenController.currentUser.setEmail(userEmail.getText());
      MainScreenController.currentUser.setUsername(editUsernameField.getText());

      Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
      Scene primaryScreen = new Scene(primaryScreenParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      window.setScene(primaryScreen);
      window.show();
    } else {
      passwordError.setText("Fields Cannot be empty!");
      passwordError.setVisible(true);
    }
  }

  @FXML
  void initialize() {
    userEmail.setText(MainScreenController.currentUser.getEmail());
    editUsernameField.setText(MainScreenController.currentUser.getUsername());
  }
}