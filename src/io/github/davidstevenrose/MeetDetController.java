package io.github.davidstevenrose;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    //since the group leader is automatically an attendee, we'll
    // append the leader to front of list - David
    observableAttendees.add(currentMeeting.getHostName());
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
   * Marks the group member as an attendee and places the user object in the list of meeting
   * attendees.
   * <p>If the user is the group leader of the meeting, the system will tell the leader he/she is
   * unable to change their attendance.</p>
   *
   * @author Jackson and Cameron, display labels by drose
   */
  @FXML
  public void attendMeetingClicked() {
    if (MainScreenController.currentUser.getUsername().equals(currentMeeting.getHostName())) {
      Alert displayMsg = new Alert(AlertType.ERROR,
          "You are group leader; cannot change attendance");
      displayMsg.show();
      System.out.println("You are group leader; cannot change attendance");
      return;
    }
    if (meetingAttend == false) {
      meetingAttend = true;
      currentMeeting.addAttendee(MainScreenController.currentUser.getUsername());
      observableAttendees.add(MainScreenController.currentUser.getUsername());
      System.out.println("Meeting Attended");
      userMessageLabel.setText("Marked Attending");
      Main.fadeAway(userMessageLabel);
      TextFileManager.editMeeting(MainScreenController.allMeetings);

    } else {
      userMessageLabel.setText("You are already attending.");
      Main.fadeAway(userMessageLabel);
      System.out.println("You are already attending this meeting");
    }
  }

  /**
   * Removes the group member from the meeting roster.
   * <p>If the user is the meeting's group leader, he/she will be unable to remove the self from
   * the
   * roster.</p>
   *
   * @param mouseEvent the object that called this method
   * @author Jackson and Cameron, display labels by drose
   */
  @FXML
  public void cancelAttendanceClicked(MouseEvent mouseEvent) {
    if (MainScreenController.currentUser.getUsername().equals(currentMeeting.getHostName())) {
      Alert displayMsg = new Alert(AlertType.ERROR,
          "You are group leader; cannot change attendance");
      displayMsg.show();
      System.out.println("You are group leader; cannot change attendance");
      return;
    }
    if (meetingAttend == true) {
      meetingAttend = false;
      observableAttendees.remove(MainScreenController.currentUser.getUsername());
      currentMeeting.removeAttendee(MainScreenController.currentUser.getUsername());
      System.out.println("Attendance Canceled");
      userMessageLabel.setText("No Longer Attending");
      Main.fadeAway(userMessageLabel);
      TextFileManager.editMeeting(MainScreenController.allMeetings);
    } else {
      userMessageLabel.setText("You Are Not On The Roster");
      Main.fadeAway(userMessageLabel);
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