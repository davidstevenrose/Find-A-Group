package io.github.davidstevenrose;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This is the controller class for the meeting details screen.
 *
 * @author Cameron and Jackson
 */
public class MeetDetController {

  // Need to make the program able to go to the Meeting Details Screen.
  @FXML
  private Label groupNameLabel;

  @FXML
  private Label timeLabel;

  @FXML
  private Label dateLabel;

  @FXML
  private Label locationLabel;

  @FXML
  private Label userMessageLabel;

  @FXML
  private Button attendMeetingButton;

  @FXML
  private Button cancelAttendanceButton;

  @FXML
  private Hyperlink backButton;

  @FXML
  private ListView<String> attendeesList;

  private boolean meetingAttend = false;
  ObservableList<String> observableAttendees = FXCollections.observableArrayList();
  private static Meeting currentMeeting;

  /**
   * This method runs when the user switches to this screen and initializes all of the values.
   *
   * @author Cameron
   */
  @FXML
  void initialize() {
    // Getting the attendees for the meeting
    observableAttendees.addAll(currentMeeting.getAttendees());
    groupNameLabel.setText(currentMeeting.getGroupName());
    dateLabel.setText(currentMeeting.getDate().toString());
    timeLabel.setText(currentMeeting.getTime());
    locationLabel.setText(currentMeeting.getLocation());
    // If the current user is already attending the meeting
    if (currentMeeting.getAttendees().contains(MainScreenController.currentUser.getUsername())) {
      meetingAttend = true;
    }
    // Adding them all to the list view
    attendeesList.setItems(observableAttendees);
  }

  /**
   * @author Jackson and Cameron
   */
  @FXML
  public void attendMeetingClicked(MouseEvent mouseEvent) {
    if (meetingAttend == false) {
      meetingAttend = true;
      currentMeeting.addAttendee(MainScreenController.currentUser.getUsername());
      observableAttendees.add(MainScreenController.currentUser.getUsername());
      System.out.println("Meeting Attended");
      userMessageLabel.setText("Marked Attending");

    } else {
      System.out.println("You are already attending this meeting");
    }
  }

  /**
   * @param mouseEvent
   * @author Jackson and Cameron
   */
  @FXML
  public void cancelAttendanceClicked(MouseEvent mouseEvent) {
    if (meetingAttend == true) {
      meetingAttend = false;
      observableAttendees.remove(MainScreenController.currentUser.getUsername());
      System.out.println("Attendance Canceled");
      userMessageLabel.setText("No Longer Attending");
    } else {
      userMessageLabel.setText("You Are Not Attending");
      System.out.println("You cannot cancel attendance to a meeting you aren't already attending");
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

  /**
   * @param mouseEvent
   * @author Jackson
   */
  @FXML
  public void viewAttendeesClicked(MouseEvent mouseEvent) {
  }

  /**
   * This method allows the MainScreenController to set the Meeting object that this controller will
   * be operating on.
   *
   * @param meeting The meeting that will be used by this screen
   * @author Cameron
   */
  public static void setMeeting(Meeting meeting) {
    currentMeeting = meeting;
  }
}