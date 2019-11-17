package io.github.davidstevenrose;

import java.io.FileWriter;
import java.io.PrintWriter;


/**
 * This class is used to manage all of the reading and writing of the text files that are used by this project.
 *
 * @author Cameron
 */
public class TextFileManager {

  /**
   * This method is used to add the details of a group to the groups text file.
   *
   * @param group The group to be added.
   * @author Cameron
   */
  public static void addGroupToFile (Group group) {
    try {
      // Opening file stream
      FileWriter writer = new FileWriter("res/groups.txt", true);
      PrintWriter print = new PrintWriter(writer);

      // Getting values
      String name = group.getName();
      String desc = group.getDescription();
      String leader = group.getGroupLeaderName();

      // Adding name, description, and group leader name to file
      print.write(name + "," + desc + "," + leader + ",");

      // Adding tags to file
      for (String tag : group.getTags()) {
        print.write(tag + ",");
      }
      // Ending on a new line
      print.write("\n");

      // Closing file stream
      print.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to add the details of a user to the users text file.
   *
   * @param user The user to be added.
   * @author Cameron
   */
  public static void addUserToFile(User user) {
    try {
      FileWriter writer = new FileWriter("res/users.txt", true);
      PrintWriter print = new PrintWriter(writer);
      String name = user.getUsername();
      String pass = user.getPassword();
      String email = user.getEmail();
      print.write("\n" + name + "," + pass + "," + email + "," + "L,");
      for (Group group : user.getGroupLeader()) {
        print.write(group.getName() + ",");
      }
      print.write("M,");
      for (Group group : user.getGroupMember()) {
        print.write(group.getName() + ",");
      }
      print.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to add the details of a meeting to the meetings text file.
   *
   * @param meeting The meeting to be added.
   * @author Cameron
   */
  public static void addMeetingToFile(Meeting meeting) {
    try {
      FileWriter writer = new FileWriter("res/meetings.txt", true);
      PrintWriter print = new PrintWriter(writer);
      String date = meeting.getDate().toString();
      String loc = meeting.getLocation();
      String time = meeting.getTime();
      String groupName = meeting.getGroupName();
      String host = meeting.getHostName();
      String status = meeting.getStatus().toString();

      print.write(
          "\n" + date + "," + loc + "," + time + "," + groupName + "," + "," + host + "," + status
              + ",");
      for (String attendee : meeting.getAttendees()) {
        print.write(attendee + ",");
      }

      print.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
