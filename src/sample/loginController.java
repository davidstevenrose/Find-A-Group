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
import java.util.ArrayList;

public class loginController {

  @FXML private PasswordField passwordField;

  @FXML private TextField usernameField;

  @FXML private Button loginButton;

  @FXML private Button createAccountButton;

  @FXML private Label errorLabel;

  // fix later with database after testing
  public static ArrayList<User> users = new ArrayList<>();

  /**
   * This method allows the user to log into their account and will change the scene to the primary
   * scene
   *
   * @param event The mouse click event
   */
  @FXML
  void loginClicked(MouseEvent event) throws IOException {
    boolean validLogin = false;
    String username = usernameField.getText();
    String password = passwordField.getText();
    System.out.println(username); // remove when done testing
    System.out.println(password); // remove when done testing
    users.add(new User("example", "pass", "example@example.net")); // remove when done testing
    users.add(new User("","","")); // remove after testing

    // Creating user to hold login user
    User userToLogIn = new User();
    // Checking if the login matches any on record
    for (User u : users) {
      if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
        validLogin = true;
        // Setting userToLogIn to user logging in
        userToLogIn = u;
      }
    }
    if (validLogin == false) {
      // Displaying message for user
      errorLabel.setText("Invalid Username or Password");
    } else {
      // setting the user in the controller
      Controller.currentUser = userToLogIn;

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
