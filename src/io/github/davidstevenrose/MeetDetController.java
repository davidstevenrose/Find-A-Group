package io.github.davidstevenrose;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MeetDetController {

  // Need to make the program able to go to the Meeting Details Screen.
  @FXML private Label groupNameLabel;

  @FXML private Label timeLabel;

  @FXML private Label dateLabel;

  @FXML private Label locationLabel;

  @FXML private Button attendMeetingButton;

  @FXML private Button cancelAttendanceButton;

  @FXML private Button viewRosterButton;

  @FXML private Hyperlink backButton;

  boolean meetingAttend = false;
  ArrayList<String> attendees = new ArrayList<String>();
  static Meeting currentMeeting;

  /**
   * This method runs when the user switches to this screen and initializes all of the values.
   *
   * @author Cameron
   */
  @FXML
  void initialize() {
    groupNameLabel.setText(currentMeeting.getGroupName());
    dateLabel.setText(currentMeeting.getDate().toString());
    timeLabel.setText(currentMeeting.getTime());
    locationLabel.setText(currentMeeting.getLocation());

  }

  /** @author Jackson */
  @FXML
  public void attendMeetingClicked(MouseEvent mouseEvent) {
    if (meetingAttend == false) {
      meetingAttend = true;
      attendees.add("yourUsername");
      System.out.printf("Meeting Attended");
    } else {
      System.out.println("You are already attending this meeting");
    }
  }

  /**
   * @param mouseEvent
   * @author Jackson
   */
  @FXML
  public void cancelAttendanceClicked(MouseEvent mouseEvent) {
    if (meetingAttend == true) {
      meetingAttend = false;
      attendees.remove("yourUsername");
      System.out.println("Attendance Canceled");
    } else {
      System.out.println("You cannot cancel attendance to a meeting you aren't already attending");
    }
  }

  @FXML
  void backButtonClicked(MouseEvent event) {}

  /**
   * @param mouseEvent
   * @author Jackson
   */
  @FXML
  public void viewAttendeesClicked(MouseEvent mouseEvent) {
    System.out.println(attendees);
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
