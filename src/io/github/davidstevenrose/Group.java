package io.github.davidstevenrose;

import java.util.ArrayList;

/**
 * The blueprint for a Group
 *
 * @author drose
 */
public class Group {

  private String name;
  private String description;
  private String groupLeaderName;
  private ArrayList<String> tags;
  private ArrayList<Meeting> meetings;

  /**
   * Creates a new group with a name and description
   *
   * @param name        the group name
   * @param description the group description
   */
  public Group(String name, String description) {
    this.name = name;
    this.description = description;
    this.tags = new ArrayList<>();
    // making sure the empty tag is always present
    tags.add("");
    this.meetings = new ArrayList<>();
  }

  /**
   * Creates a group with a name, description, and group tags
   *
   * @param name        group name
   * @param description group description
   * @param tags        an array list of group tags
   */
  public Group(String name, String description, ArrayList<String> tags) {
    this.name = name;
    this.description = description;
    this.tags = tags;
    // making sure the empty tag is always present
    tags.add("");
    this.meetings = new ArrayList<>();
  }

  /**
   * Creates a group with a name, description, group tags, and accepts the name of the leader
   *
   * @param name        group name
   * @param description group description
   * @param tags        an array list of group tags
   * @param leaderName  the group leader's name
   */
  public Group(String name, String description, ArrayList<String> tags, String leaderName) {
    this.name = name;
    this.description = description;
    this.tags = tags;
    this.groupLeaderName = leaderName;
    // making sure the empty tag is always present
    tags.add("");
    this.meetings = new ArrayList<>();
  }

  /**
   * Gets the name of the group
   *
   * @return a String name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the group
   *
   * @param name a name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the description of the group
   *
   * @return the description (without format)
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the group description
   *
   * @param description a description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the group's list of tags
   *
   * @return a list of tags
   */
  public ArrayList<String> getTags() {
    return tags;
  }

  /**
   * Replaces the group's list of tags with an entirely new list
   *
   * @param tags a new list of tags
   */
  public void replaceTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  /**
   * Add a tag to the group's list of tags
   *
   * @param tag a tag
   */
  public void addTag(String tag) {
    tags.add(tag);
  }

  /**
   * Replaces the group's list of meetings with an entirely new list
   *
   * @param meetings the new list of meetings
   */
  public void replaceMeetings(ArrayList<Meeting> meetings) {
    this.meetings = meetings;
  }

  /**
   * Gets the group's list of meetings
   *
   * @return a list of meetings
   */
  public ArrayList<Meeting> getMeetings() {
    return meetings;
  }

  /**
   * Adds a meeting to the group
   *
   * @param meeting a new group meeting
   */
  public void addMeeting(Meeting meeting) {
    meetings.add(meeting);
  }

  /**
   * Gets the name of the group's owner.
   *
   * @return a name
   */
  public String getGroupLeaderName() {
    return groupLeaderName;
  }
}
