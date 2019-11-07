package io.github.davidstevenrose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class EditProfileController {
    @FXML private Label editLabel;

    @FXML private PasswordField newPassword;

    @FXML private Button btnSubmit;

    @FXML private Button cancelAction;

    @FXML private PasswordField newPassword2;

    static User currentUser;

    @FXML
    void initialize() {

        editLabel.setText(currentUser.getUsername());
    }

    @FXML
    void returnProfile(MouseEvent event) throws IOException {
        // Creating the new scene
        Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));
        Scene primaryScreen = new Scene(primaryScreenParent);


        // Getting the stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


        // Setting stage
        window.setScene(primaryScreen);
        window.show();
    }

    @FXML
    void submitToProfile(MouseEvent event) throws IOException {
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
