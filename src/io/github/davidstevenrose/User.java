package io.github.davidstevenrose;

import java.util.ArrayList;

/**
 * The definition of an account on Find-A-Group.
 */
public class User {

  private String username;
  private String password;
  private String email;
  private ArrayList<Group> groupMember = new ArrayList<>();
  private ArrayList<Group> groupLeader = new ArrayList<>();

  /**
   * This is a no argument constructor for the User class used in the LoginController class.
   *
   * @author Cameron
   */
  public User() {
  }

  /**
   * Creating a new account.
   *
   * @param username the username of the account
   * @param password the password of the account
   * @param email    the email of the account holder
   */
  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  /**
   * Gets the username of the account
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the account
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password of the account
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the account
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the email of the account
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the account
   *
   * @param email the new email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the list of groups the user is a member of.
   *
   * @return an array list of groups
   */
  public ArrayList<Group> getGroupMember() {
    return groupMember;
  }

  /**
   * Adds the user to a group.
   *
   * @param group the group the user joined
   */
  public void addGroupMember(Group group) {
    groupMember.add(group);
  }

  /**
   * Gets the list of groups the user is the leader of.
   *
   * @return an array list of groups
   */
  public ArrayList<Group> getGroupLeader() {
    return groupLeader;
  }

  /**
   * Adds a new group to the user's list of groups he/she is the leader of.
   *
   * @param group the group the user created
   */
  public void addGroupLeader(Group group) {
    groupLeader.add(group);
  }
}