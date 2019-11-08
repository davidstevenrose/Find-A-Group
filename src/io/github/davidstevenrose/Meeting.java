package io.github.davidstevenrose;
import java.time.LocalDate;
import java.util.ArrayList;

public class Meeting {

  private LocalDate date;
  private String location;
  private String time;
  private final String groupName;
  private String hostName;
  private MeetingStatus status;
  /**
   * list of attendees. name and type of field may be edited by Jackson.
   */
  private ArrayList<String> attendees;

  /**
   * Creates a meeting with a meeting data, location, time, and name of host group
   *
   * @param date      the date
   * @param location  the location
   * @param time      the time
   * @param groupName the name of the host group
   */
  Meeting(LocalDate date, String location, String time, String groupName) {
    this.time = time;
    this.date = date;
    this.location = location;
    this.groupName = groupName;
    attendees = new ArrayList<>();
  }

  /**
   * Creates a meeting with a meeting data, location, time, name of host group, and the name of the
   * host user to sign up on the roster
   *
   * @param location      the location
   * @param date          the data
   * @param time          the time
   * @param hostGroupName the name of the host group
   * @param hostName      the host's name
   */
  Meeting(String location, LocalDate date, String time, String hostGroupName, String hostName) {
    this.time = time;
    this.date = date;
    this.location = location;
    this.groupName = hostGroupName;
    this.hostName = hostName;
    this.status = MeetingStatus.ACTIVE;
    this.attendees = new ArrayList<>();
    attendees.add(hostName);
  }

  /**
   * Gets the date of the meeting
   *
   * @return the data
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Set the date of the meeting
   *
   * @param date the new date
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Gets the location of the meeting
   *
   * @return the meeting location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location of the meeting
   *
   * @param location the meeting location
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Gets the time of the meeting
   *
   * @return the time
   */
  public String getTime() {
    return time;
  }

  /**
   * Sets the time of the meeting
   *
   * @param time the time
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Gets the name of the host group
   *
   * @return the name of group
   */
  public String getGroupName() {
    return groupName;
  }

  /**
   * Gets the name of the host
   *
   * @return name of host
   */
  public String getHostName() {
    return hostName;
  }

  /**
   * Gets the status of the meeting
   *
   * @return the status
   */
  public MeetingStatus getStatus() {
    return status;
  }

  /**
   * Sets the status of the meeting
   *
   * @param status the new status
   */
  public void setStatus(MeetingStatus status) {
    this.status = status;
  }

  public void addAttendee(String attendee) {
    attendees.add(attendee);
  }

  public ArrayList<String> getAttendees() {
    return attendees;
  }
}