package io.github.davidstevenrose;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class EditProfileController {
    @FXML private Label editLabel;

    static User currentUser;

    @FXML
    void initialize() {

        editLabel.setText(currentUser.getUsername());
    }
}
