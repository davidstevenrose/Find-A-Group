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

public class CreateAccount {

  @FXML private PasswordField confirmPasswordField;

  @FXML private PasswordField passwordField;

  @FXML private TextField usernameField;

  @FXML private TextField emailField;

  @FXML private TextField confirmEmailField;

  @FXML private Label errorLabel;

  @FXML private Button createAccountButton;

  @FXML
  void createAccountButtonClicked(MouseEvent event) throws IOException {
    // get user input data
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
    } else if (username != null && password != null && email != null) {
      //write to database later
      loginController.users.add(new User(username, password, email));

      // Creating scene
      Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
      Scene primaryScreen = new Scene(primaryScreenParent);

      // Getting the stage
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Setting stage and displaying
      window.setScene(primaryScreen);
      window.show();
    }
  }
}
