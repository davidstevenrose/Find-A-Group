package io.github.davidstevenrose;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Control to allow the group leader to edit a meeting.
 *
 * @author David
 */
public class EditableMeetDetController {

  @FXML
  private ChoiceBox<String> meridiumEditBox;

  @FXML
  private Label groupNameLabel;

  @FXML
  private TextField timeLabel;

  @FXML
  private DatePicker dateLabel;

  @FXML
  private TextField locationLabel;

  @FXML
  private Label userMessageLabel;

  @FXML
  private ChoiceBox<MeetingStatus> statusBox;

  @FXML
  private ListView<String> attendeesList;

  private ObservableList<String> observableAttendees = FXCollections.observableArrayList();
  //a attribute of a reference to the current meeting to edit
  private Meeting currentMeeting;

  /**
   * Set the pointer to the meeting to edit to the passed reference of the meeting to edit.
   *
   * @param m a reference to the meeting to edit
   */
  void setCurrentMeeting(Meeting m) {
    currentMeeting = m;
    init();
  }

  /**
   * This method runs when the user switches to this screen and initializes all of the values.
   */
  private void init() {
    //add content to meridium box
    meridiumEditBox.getItems().addAll("AM", "PM");
    // Getting the attendees for the meeting
    //append group leader name to front of list
    observableAttendees.add(currentMeeting.getHostName());
    observableAttendees.addAll(currentMeeting.getAttendees());

    groupNameLabel.setText(currentMeeting.getGroupName());
    dateLabel.setEditable(false);
    dateLabel.setChronology(currentMeeting.getDate().getChronology());
    dateLabel.setValue(currentMeeting.getDate());
    String time = currentMeeting.getTime()
        .substring(0, currentMeeting.getTime().length() - 2);
    String med = currentMeeting.getTime()
        .substring(currentMeeting.getTime().length() - 2);
    timeLabel.setText(time);
    if (med.equals("AM")) {
      meridiumEditBox.setValue("AM");
    } else {
      meridiumEditBox.setValue("PM");
    }
    locationLabel.setText(currentMeeting.getLocation());
    statusBox.getItems().addAll(MeetingStatus.values());
    statusBox.setValue(currentMeeting.getStatus());
    // Adding attendees all to the list view
    attendeesList.setItems(observableAttendees);
  }

  /**
   * This method attempts to edit the referenced meeting, then returns the user to the primary
   * screen.
   *
   * @param event The mouse event created by the user clicking on the button
   * @throws IOException An exception that can occur if the fxml file is not found
   */
  @FXML
  private void saveEditsClicked(MouseEvent event) throws IOException {
    //check input for date
    boolean badInput = true;
    if (dateLabel.getValue() != null && !dateLabel.getValue().toString().isEmpty()) {
      //check input for time
      if (timeLabel.getText() != null && timeLabel.getText()
          .matches(MainScreenController.TIME_REGEX)) {
        //check input for location
        if (locationLabel.getText() != null && !locationLabel.getText().isEmpty()) {
          //check for status
          if (statusBox.getValue() != null) {
            currentMeeting.setLocation(locationLabel.getText());
            currentMeeting.setTime(timeLabel.getText().trim() + meridiumEditBox.getValue());
            currentMeeting.setDate(dateLabel.getValue());
            currentMeeting.setStatus(statusBox.getValue());
            badInput = false;
          }
        }
      }
    }
    if (badInput) {
      userMessageLabel.setText("Invalid input or missing required fields; please try again.");
      Main.fadeAway(userMessageLabel);
    } else {
      backButtonClicked(event);
    }
  }

  /**
   * This method runs when the back button is clicked and returns the user to the primary screen.
   *
   * @param event The mouse event created by the user clicking on the button
   * @throws IOException An exception that can occur if the fxml file is not found
   * @author Cameron
   */
  @FXML
  void backButtonClicked(MouseEvent event) throws IOException {
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
