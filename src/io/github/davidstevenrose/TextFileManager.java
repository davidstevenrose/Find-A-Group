package io.github.davidstevenrose;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to manage all of the reading and writing of the text files that are used by
 * this project.
 *
 * @author Cameron
 */
public class TextFileManager {

  /**
   * This method is used to add the details of a group to the groups text file.
   *
   * @param group The group to be added
   * @author Cameron
   */
  static void addGroupToFile(Group group) {
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
   * @param user The user to be added
   * @author Cameron
   */
  static void addUserToFile(User user) {
    try {
      // Opening file stream
      FileWriter writer = new FileWriter("res/users.txt", true);
      PrintWriter print = new PrintWriter(writer);

      // Getting values
      String name = user.getUsername();
      String pass = user.getPassword();
      String email = user.getEmail();
      print.write(name + "," + pass + "," + email + ",");

      // Looping to write the groups the user is a leader of to file
      for (Group group : user.getGroupLeader()) {
        print.write(group.getName() + ",");
      }
      print.write("M,"); // Delimiter character used to separate leader and member groups

      // Looping to write the groups the user is a member of to file
      for (Group group : user.getGroupMember()) {
        print.write(group.getName() + ",");
      }

      // Ending on new line
      print.write("\n");

      // Closing file stream
      print.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to add the details of a meeting to the meetings text file.
   *
   * @param meeting The meeting to be added
   * @author Cameron
   */
  static void addMeetingToFile(Meeting meeting) {
    try {
      // Opening file stream
      FileWriter writer = new FileWriter("res/meetings.txt", true);
      PrintWriter print = new PrintWriter(writer);

      // Getting values
      String date = meeting.getDate().toString();
      String loc = meeting.getLocation();
      String time = meeting.getTime();
      String groupName = meeting.getGroupName();

      // Writing to file
      print.write(date + "," + loc + "," + time + "," + groupName + "," );
      for (String attendee : meeting.getAttendees()) {
        print.write(attendee + ",");
      }
      // Ending on new line
      print.write("\n");

      // Closing stream
      print.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to update the users.txt file after the user has changed some information.
   *
   * @param users The list storing all of the users
   * @author Cameron
   */
  static void editUser(ArrayList<User> users) {
    try {
      new FileOutputStream("res/users.txt").close();
      for (User u : users) {
        addUserToFile(u);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to update the groups.txt file after there has been some change to one or
   * more groups.
   *
   * @param groups The list storing all of the groups
   * @author Cameron
   */
  static void editGroup(ArrayList<Group> groups) {
    try {
      new FileOutputStream("res/groups.txt").close();
      for (Group g : groups) {
        addGroupToFile(g);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to update the meetings.txt file after changes have been made to one or more
   * meetings.
   *
   * @param meetings The list storing all of the meetings
   * @author Cameron
   */
  static void editMeeting(ArrayList<Meeting> meetings) {
    try {
      new FileOutputStream("res/meetings.txt").close();
      for (Meeting m : meetings) {
        addMeetingToFile(m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method reads from the text files users.txt, groups.txt, and meetings.txt and uses the
   * information there to create User, Group, and Meeting objects which it uses to populate the
   * users, groups, and meetings ArrayLists.
   *
   * @param users The ArrayList that will be used to store all of the User objects
   * @param groups The ArrayList that will be used to store all of the Group objects
   * @param meetings The ArrayList that will be used to store all of the Meeting objects
   * @author Cameron
   */
  static void loadAll(ArrayList<User> users, ArrayList<Group> groups, ArrayList<Meeting> meetings) {
    // Ensuring that all of the ArrayLists are empty to start
    users.clear();
    groups.clear();
    meetings.clear();
    try {
      // Reading the meetings file
      File file = new File("res/meetings.txt");
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        // Getting the line
        String line = sc.nextLine();
        // Splitting on comma
        String[] meetingData = line.split(",");
        // Creating Meeting with the data
        LocalDate date = LocalDate.parse(meetingData[0]);
        String loc = meetingData[1];
        String time = meetingData[2];
        String groupName = meetingData[3];
        String host = meetingData[4];
        Meeting meeting = new Meeting(loc, date, time, groupName, host);
        // Adding the attendees to the meeting
        for (int i = 5; i < meetingData.length; i++) {
          meeting.addAttendee(meetingData[i]);
        }

        // Adding meeting to meetings ArrayList
        meetings.add(meeting);
      }
      // Closing file
      sc.close();

      // Reading the groups file
      file = new File("res/groups.txt");
      sc = new Scanner(file);

      while (sc.hasNextLine()) {
        // Getting the line
        String line = sc.nextLine();

        // Splitting on comma
        String[] groupData = line.split(",");

        // Creating group with the data
        String groupName = groupData[0];
        String desc = groupData[1];
        String leader = groupData[2];
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 3; i < groupData.length; i++) {
          tags.add(groupData[i]);
        }
        Group group = new Group(groupName, desc, tags, leader);

        // Adding meeting to group if that meeting belongs to that group
        for (Meeting meeting : meetings) {
          if (meeting.getGroupName().equals(group.getName())) {
            group.addMeeting(meeting);
          }
        }

        // Adding group to groups ArrayList
        groups.add(group);
      }
      // Closing file
      sc.close();

      // Reading the users file
      file = new File("res/users.txt");
      sc = new Scanner(file);

      while (sc.hasNextLine()) {
        // Getting the line
        String line = sc.nextLine();

        // Splitting on comma
        String[] userData = line.split(",");

        // Creating user with the data
        String username = userData[0];
        String password = userData[1];
        String email = userData[2];
        User user = new User(username, password, email);

        // Creating variable to store index number
        int index = 3;

        // Looping for group leader groups until the member deliminator
        for (int i = index; i < userData.length; i++) {
          if (userData[i].equals("M")) {
            index++;
            break;
          } else {
            // Adding group to user's group leader list if it is the one from the file
            for (Group group : groups) {
              if (userData[i].equals(group.getName())) {
                user.addGroupLeader(group);
              }
            }
            index++;
          }
        }

        // Looping for the member groups
        for (int i = index; i < userData.length; i++) {
          // Adding group to user's group member list if it is the one from the file
          for (Group group : groups) {
            if (userData[i].equals(group.getName())) {
              user.addGroupMember(group);
            }
          }
        }
        // Adding the user to the users ArrayList
        users.add(user);
      }
      // Closing file
      sc.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
