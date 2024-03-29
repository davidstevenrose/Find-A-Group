package io.github.davidstevenrose;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField usernameField;

  @FXML
  private Label errorLabel;

  // fix later with database after testing
  static ArrayList<User> users = new ArrayList<>();

  /**
   * This method is run when the LoginScreen is loaded, it is used to load all of the users, groups,
   * and meetings into their respective ArrayLists.
   *
   * @author Cameron
   */
  @FXML
  void initialize() {
    // Load the users, allGroups, and allMeetings ArrayLists
    TextFileManager.loadAll(
        users, MainScreenController.allGroups, MainScreenController.allMeetings);
  }

  /**
   * This method allows the user to log into their account and will change the scene to the primary
   * scene.
   *
   * @param event The mouse click event
   */
  @FXML
  void loginClicked(MouseEvent event) throws IOException {
    boolean validLogin = false;
    String username = usernameField.getText();
    String password = passwordField.getText();


    // Creating user to hold login user
    User userToLogIn = new User();
    // Checking if the login matches any on record
    for (User u : users) {
      if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
        validLogin = true;
        // Setting userToLogIn to be the user logging in
        userToLogIn = u;
      }
    }
    if (!validLogin) {
      // Displaying message for user
      errorLabel.setText("Invalid Username or Password");
      Main.fadeAway(errorLabel);
    } else {
      // setting the user in the controller
      MainScreenController.currentUser = userToLogIn;

      // Creating the new scene
      Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
      Scene primaryScreen = new Scene(primaryScreenParent);

      // Getting the stage
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Setting stage
      window.setScene(primaryScreen);
      window.show();
    }
  }

  @FXML
  void createAccountClicked(MouseEvent event) throws IOException {
    // Creating the new scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    // Getting the stage
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Setting the stage
    window.setScene(primaryScreen);
    window.show();
  }
}