package io.github.davidstevenrose;
import java.time.LocalDate;
import java.util.ArrayList;

public class Meeting {
  private LocalDate date;
  private String location;
  private String time;
  private String groupName;
  private ArrayList<String> attendees;

  Meeting(LocalDate date, String location, String time, String groupName) {
    this.time = time;
    this.date = date;
    this.location = location;
    this.groupName = groupName;
    attendees = new ArrayList<>();
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

  public void addAttendee(String attendee) {
    attendees.add(attendee);
  }

  public ArrayList<String> getAttendees() {
    return attendees;
  }
}