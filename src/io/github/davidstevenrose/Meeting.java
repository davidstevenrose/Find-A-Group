package io.github.davidstevenrose;

import java.time.LocalDate;
import java.util.ArrayList;

public class Meeting {
  private LocalDate date;
  private String location;
  private String time;
  private String groupName;
  private String hostName;
  private MeetingStatus status;
  /**
   * list of attendees. name and type of field may be edited by Jackson.
   */
  private ArrayList<String> attendees;

  Meeting(LocalDate date, String location, String time, String groupName) {
    this.time = time;
    this.date = date;
    this.location = location;
    this.groupName = groupName;
  }

  Meeting(String location, LocalDate date,  String time, String hostGroupName, String hostName) {
    this.time = time;
    this.date = date;
    this.location = location;
    this.groupName = hostGroupName;
    this.hostName = hostName;
    this.status = MeetingStatus.ACTIVE;
    this.attendees = new ArrayList<>();
    attendees.add(hostName);
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
