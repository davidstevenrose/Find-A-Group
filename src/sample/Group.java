package sample;

import java.util.ArrayList;

public class Group {
  private String name;
  private String description;
  private ArrayList<String> tags;
  private ArrayList<Meeting> meetings;

  public Group(String name, String description) {
    this.name = name;
    this.description = description;
    this.tags = new ArrayList<>();
    // making sure the empty tag is always present
    tags.add("");
    this.meetings = new ArrayList<>();
    }

  public Group(String name, String description, ArrayList<String> tags) {
    this.name = name;
    this.description = description;
    this.tags = tags;
    // making sure the empty tag is always present
    tags.add("");
    this.meetings = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<String> getTags() {
    return tags;
  }

  public void setTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  public void addTag(String tag) {
    tags.add(tag);
  }

  public void setMeetings(ArrayList<Meeting> meetings) {
  this.meetings = meetings;
  }

  public ArrayList<Meeting> getMeetings() {
    return meetings;
  }
  public void addMeeting(Meeting meeting) {
    meetings.add(meeting);
  }
}
