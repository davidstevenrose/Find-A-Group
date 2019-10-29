package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MeetDetController {

  //Need to make the program able to go to the Meeting Details Screen.

  boolean meetingAttend = false;

  @FXML
  public void attendMeetingClicked(MouseEvent mouseEvent) {
    if (meetingAttend == false) {
      meetingAttend = true;
      System.out.printf("Meeting Attended");
    } else {
      System.out.println("You are already attending this meeting");
    }
  }

  public void cancelAttendanceClicked(MouseEvent mouseEvent) {
    if (meetingAttend == true) {
      meetingAttend = false;
      System.out.println("Attendance Canceled");
    } else {
      System.out.println("You cannot cancel attendance to a meeting you aren't already attending");
    }
  }

  public void closeClicked(MouseEvent mouseEvent) {
    System.out.println("Closing Program");
    System.exit(1);
  }
}
