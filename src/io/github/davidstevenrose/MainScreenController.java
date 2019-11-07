package io.github.davidstevenrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainScreenController {

  @FXML private CheckBox mustIncludeAllCheckBox;

  @FXML private ChoiceBox<String> searchTag1;

  @FXML private ChoiceBox<String> searchTag3;

  @FXML private ChoiceBox<String> searchTag4;

  @FXML private ChoiceBox<String> searchTag2;

  @FXML private Button joinGroupsButton;

  @FXML private TableView<Group> searchGroupTable;

  @FXML private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML private TableView<Group> pGroupTable;

  @FXML private TableColumn<?, ?> pGroupName;

  @FXML private TableColumn<?, ?> pDescription;

  @FXML private TextField searchLocationTextbox;

  @FXML private DatePicker searchDatePicker;

  @FXML private Button searchMeetingsButton;

  @FXML private ChoiceBox<String> groupsPicker;

  @FXML private TableView<Meeting> findMeetingsTable;

  @FXML private TableColumn<?, ?> meetingsGroupNameCol;

  @FXML private TableColumn<?, ?> meetingsDateCol;

  @FXML private TableColumn<?, ?> meetingsTimeCol;

  @FXML private TableColumn<?, ?> meetingsLocationCol;

  @FXML private ChoiceBox<String> addTag1;

  @FXML private ChoiceBox<String> addTag2;

  @FXML private ChoiceBox<String> addTag3;

  @FXML private ChoiceBox<String> addTag4;

  @FXML private ChoiceBox<String> editTag1;

  @FXML private ChoiceBox<String> editTag2;

  @FXML private ChoiceBox<String> editTag3;

  @FXML private ChoiceBox<String> editTag4;

  @FXML private DatePicker addMeetingDatePicker;

  @FXML private TextField addMeetingLocationTextfield;

  @FXML private ComboBox<String> addMeetingTimePicker;

  @FXML private ChoiceBox<String> editGroupSelector;

  @FXML private TextField createGroupTextfield;

  @FXML private TextArea addDescriptionTextarea;

  @FXML private TextArea addDescriptionTextarea1;

  @FXML private Label savedChangesLabel;

  @FXML private Label savedChangesLabel1;

  @FXML private Label joinLabel;

  @FXML private Tab editGroupTab;

  @FXML private TabPane tabPane;

  @FXML private Label userNameLabel;

  private final String[] tags = {
    "", "Gaming", "Sports", "Fitness", "Reading", "Study", "Fun", "Movies"
  };

  // Array list to store all of the groups in
  private ArrayList<Group> allGroups = new ArrayList<>();

  // Array list to store all of the meetings in
  private ArrayList<Meeting> allMeetings = new ArrayList<>();

  // The user currently using the program
  static User currentUser;

  // testing remove after
  Group exampleGroup1 =
      new Group("FGCU Games Group", "A group of FGCU students who like to play video games");
  Group exampleGroup2 =
      new Group("FGCU Running Group", "A group of FGCU students who like to get together and run");
  Group exampleGroup3 =
      new Group("FGCU Book Club", "A group of FGCU students who like to get together and read");
  Meeting exampleMeeting1 = new Meeting(LocalDate.now(), "FGCU", "5:00 PM", "FGCU Games Group");
  Meeting exampleMeeting2 =
      new Meeting(LocalDate.of(2019, 10, 20), "FGCU", "5:00 PM", "FGCU Running Group");
  Meeting exampleMeeting3 =
      new Meeting(LocalDate.of(2019, 10, 25), "not FGCU", "5:00 PM", "FGCU Book Club");
  // remove

  @FXML
  void initialize() {
    /** ------------------------------------------------------
     * Profile Code
     * @author Darian + Nicholas Hansen
     */
    //Profile uses the user's input username (current user) displays it on the profile tab.


    userNameLabel.setText(currentUser.getUsername() + "!");

    // TODO: 11/7/2019 Populate the table on the profile tab with groups they are a member of.
    // TODO: 11/7/2019 Clean up the initialize statement's foreach loops?

    // Current Groups and Description

    // --------------------------------------------------------

    // Putting values in the tags boxes
    for (String tag : tags) {
      // Shorten later with fancy stuffs
      searchTag1.getItems().add(tag);
      searchTag2.getItems().add(tag);
      searchTag3.getItems().add(tag);
      searchTag4.getItems().add(tag);
      addTag1.getItems().add(tag);
      addTag2.getItems().add(tag);
      addTag3.getItems().add(tag);
      addTag4.getItems().add(tag);
      editTag1.getItems().add(tag);
      editTag2.getItems().add(tag);
      editTag3.getItems().add(tag);
      editTag4.getItems().add(tag);
    }

    // Putting values in the add meeting time picker
    for (int i = 1; i < 13; i++) {
      addMeetingTimePicker.getItems().add(i + ":00 AM");
      addMeetingTimePicker.getItems().add(i + ":00 PM");
    }

    // values used for testing and demo, remove later
    allMeetings.add(exampleMeeting1);
    allMeetings.add(exampleMeeting2);
    allMeetings.add(exampleMeeting3);
    exampleGroup1.addTag("Gaming");
    exampleGroup2.addTag("Fitness");
    exampleGroup2.addTag("Sports");
    exampleGroup3.addTag("Reading");
    allGroups.add(exampleGroup1);
    allGroups.add(exampleGroup2);
    allGroups.add(exampleGroup3);
    // remove later

    // Adding values to group display on startup
    // preparing columns
    searchGroupsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    searchGroupsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    searchGroupTable.setItems(FXCollections.observableArrayList(allGroups));

    // Preparing columns
    pGroupName.setCellValueFactory(new PropertyValueFactory<>("name"));
    pDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    // Adding value to display
    displayGroupsInProfile();

    // Adding meetings to display on startup
    // preparing columns
    meetingsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
    meetingsDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    meetingsTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    meetingsLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
    findMeetingsTable.setItems(FXCollections.observableArrayList(allMeetings));

    // Hiding the editGroupsTab on startup
    if (currentUser.getGroupLeader().size() == 0) {
      tabPane.getTabs().remove(editGroupTab);
    }

    // Setting the first element as selected
    searchGroupTable.getSelectionModel().selectFirst();
    findMeetingsTable.getSelectionModel().selectFirst();
    searchTag1.getSelectionModel().selectFirst();
    searchTag2.getSelectionModel().selectFirst();
    searchTag3.getSelectionModel().selectFirst();
    searchTag4.getSelectionModel().selectFirst();

  }

  /**
   * This method runs when the joinGroupButton button is clicked. This method gets the group
   * selected in the searchGroupTable table and adds that group to the groupMember ArrayList in the
   * user object.
   *
   * @author Cameron
   * @param event The mouse click event created by the user clicking on the button
   */
  @FXML
  void joinGroupButtonClick(MouseEvent event) {
    // Getting selected group from table
    Group group = searchGroupTable.getSelectionModel().getSelectedItem();
    // Adding user to group
    currentUser.addGroupMember(group);
    // Updating the selectors
    populateGroupSelectors();

    // Adding to profile tab
    displayGroupsInProfile();

    // Displaying result for the user
    joinLabel.setText("Join Successful");
  }

  /**
   * This method runs when the searchGroupsButton button is clicked. This method reads the tags
   * selected by the user and displays groups to the searchGroupsTable based on the information that
   * the user provides.
   *
   * @author Cameron
   * @param event The mouse click event created by the user clicking on the button
   */
  @FXML
  void searchGroupsButtonClicked(MouseEvent event) {

    // creating an array list to hold the groups that match the search criteria
    ArrayList<Group> foundGroups = new ArrayList<>();

    // Testing the checkbox to see how groups will be displayed
    if (mustIncludeAllCheckBox.isSelected()) {
      // Getting the tags
      String tag1 = searchTag1.getValue();
      String tag2 = searchTag2.getValue();
      String tag3 = searchTag3.getValue();
      String tag4 = searchTag4.getValue();
      // Looping through the groups and selecting ones where all of the tags are present
      for (Group group : allGroups) {
        if (group.getTags().contains(tag1)
            && group.getTags().contains(tag2)
            && group.getTags().contains(tag3)
            && group.getTags().contains(tag4)) {
          foundGroups.add(group);
        }
      }
    } else {
      // Getting the tags
      String tag1 = searchTag1.getValue();
      String tag2 = searchTag2.getValue();
      String tag3 = searchTag3.getValue();
      String tag4 = searchTag4.getValue();
      // Making it so that if there are no tags selected, all of the groups will be shown
      if (tag1 == null && tag2 == null && tag3 == null && tag4 == null) {
        tag1 = "";
      }
      // looping through the groups and returning ones that contain at least one of the specified
      // tags
      for (Group group : allGroups) {
        if (group.getTags().contains(tag1)
            || group.getTags().contains(tag2)
            || group.getTags().contains(tag3)
            || group.getTags().contains(tag4)) {
          foundGroups.add(group);
        }
      }
    }
    // Displaying the groups matching search criteria
    searchGroupTable.setItems(FXCollections.observableArrayList(foundGroups));

    // Displaying that group in the user groups page

  }

  @FXML
  void createGroupsButtonClicked(MouseEvent event) {
    try {
      // Get the values they entered
      String name = createGroupTextfield.getText();
      String description =
          ((addDescriptionTextarea.getText() == null) ? "" : addDescriptionTextarea.getText());

      // Create an array list for the tags
      ArrayList<String> tags = new ArrayList<>();
      // ternary to set the tag to "" if empty instead of null
      String tag1 = ((addTag1.getValue() == null) ? "" : addTag1.getValue());
      String tag2 = ((addTag2.getValue() == null) ? "" : addTag2.getValue());
      String tag3 = ((addTag3.getValue() == null) ? "" : addTag3.getValue());
      String tag4 = ((addTag4.getValue() == null) ? "" : addTag4.getValue());
      // Adding tags to tags array
      tags.add(tag1);
      tags.add(tag2);
      tags.add(tag3);
      tags.add(tag4);
      // Creating new group with parameters from user
      Group group = new Group(name, description, tags);
      // Adding to groups ArrayList
      allGroups.add(group);
      // Making the current user group leader
      currentUser.addGroupLeader(group);
      // Updating the selectors
      populateGroupSelectors();
      // displaying information to the user
      savedChangesLabel.setText("Saved Changes");

      // Resetting fields
      createGroupTextfield.clear();
      addDescriptionTextarea.clear();
      addTag1.setValue("");
      addTag2.setValue("");
      addTag3.setValue("");
      addTag4.setValue("");
      // Displaying the group tab
      tabPane.getTabs().add(editGroupTab);

      // Adding to profile tab
      displayGroupsInProfile();

    } catch (Exception e) {
      savedChangesLabel.setText("Please enter all non-optional fields");
    }
  }

  @FXML
  void editGroupsButtonClicked() {
    try {
      Group selectedGroup = new Group("error", "error"); // only if broken will happen
      for (Group g : currentUser.getGroupLeader()) {
        if (g.getName().equals(editGroupSelector.getValue())) {
          selectedGroup = g;
        }
      }
      // updating description
      selectedGroup.setDescription(addDescriptionTextarea1.getText());

      // Create an array list for the tags
      ArrayList<String> tags = new ArrayList<>();
      // ternary to set the tag to "" if empty instead of null
      String tag1 = ((editTag1.getValue() == null) ? "" : editTag1.getValue());
      String tag2 = ((editTag2.getValue() == null) ? "" : editTag2.getValue());
      String tag3 = ((editTag3.getValue() == null) ? "" : editTag3.getValue());
      String tag4 = ((editTag4.getValue() == null) ? "" : editTag4.getValue());
      // Adding tags to tags array
      tags.add(tag1);
      tags.add(tag2);
      tags.add(tag3);
      tags.add(tag4);

      // setting the tags to the selected ones
      selectedGroup.setTags(tags);

      // creating a new meeting if all information entered
      if (addMeetingDatePicker.getValue() != null
          && addMeetingLocationTextfield.getText() != null
          && addMeetingTimePicker.getValue() != null) {
        // getting values from user
        LocalDate meetingDate = addMeetingDatePicker.getValue();
        String meetingLocation = addMeetingLocationTextfield.getText();
        String meetingTime = addMeetingTimePicker.getValue();
        // creating new meeting
        Meeting meeting =
            new Meeting(meetingDate, meetingLocation, meetingTime, selectedGroup.getName());
        // updating all meetings and group meetings
        allMeetings.add(meeting);
        selectedGroup.addMeeting(meeting);
      }

      // displaying information to the user
      savedChangesLabel1.setText("Saved Changes");

      // Resetting fields
      addDescriptionTextarea1.clear();
      editTag1.setValue("");
      editTag2.setValue("");
      editTag3.setValue("");
      editTag4.setValue("");

    } catch (Exception e) {
      System.out.println("Please enter all non-optional fields");
    }
  }

  /**
   * This method runs when the searchMeetingsButton button is clicked. This method checks the
   * criteria selected by the user and provides meetings fitting those criteria.
   *
   * @author Cameron
   * @param event The mouse click event created by the user clicking on the button
   */
  // This method runs when the searchMeetingsButton button is clicked.
  @FXML
  void searchMeetingsButtonClicked(MouseEvent event) {

    // Creating ArrayList to hold meetings and giving it all the meetings
    ArrayList<Meeting> foundMeetings = new ArrayList<>();
    foundMeetings.addAll(allMeetings);
    ArrayList<Meeting> meetingsToRemove = new ArrayList<>();
    // Later all of these will be replaced with a database search
    // If there is a date selected
    if (searchDatePicker.getValue() != null) {
      for (Meeting m : foundMeetings) {
        // If not the data selected
        if (!m.getDate().equals(searchDatePicker.getValue())) {
          // Adding meeting to list of meetings to remove
          meetingsToRemove.add(m);
        }
      }
    }
    // Removing meetings not fitting criteria
    foundMeetings.removeAll(meetingsToRemove);
    // If there is a location specified
    if (!searchLocationTextbox.getText().isEmpty()) {

      for (Meeting m : foundMeetings) {
        // If not the data selected
        if (!m.getLocation().equals(searchLocationTextbox.getText())) {
          meetingsToRemove.add(m);
        }
      }
    }
    // Removing meetings not fitting criteria
    foundMeetings.removeAll(meetingsToRemove);
    if (groupsPicker.getValue() != null) {
      for (Meeting m : foundMeetings) {
        // If not the data selected
        if (!m.getGroupName().equals(groupsPicker.getValue())) {
          meetingsToRemove.add(m);
        }
      }
    }
    // Removing meetings not fitting criteria
    foundMeetings.removeAll(meetingsToRemove);
    // Displaying results
    findMeetingsTable.setItems(FXCollections.observableArrayList(foundMeetings));
  }

  /**
   * The button to direct the user to the meeting details page and give the selected meeting to the
   * controller for the meeting details page.
   *
   * @author Cameron
   * @param event The mouse click event created by the user clicking on the button
   */
  @FXML
  void viewMeetingDetailsButtonClicked(MouseEvent event) throws IOException {
    // Get selected meeting
    Meeting selectedMeeting = findMeetingsTable.getSelectionModel().getSelectedItem();

    // Make that the selected meeting in the MeetDetController class
    MeetDetController.setMeeting(selectedMeeting);

    // Creating the new scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("MeetingDetails.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    // Getting the stage
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Setting stage
    window.setScene(primaryScreen);
    window.show();
  }

  /**
   * This takes you to the edit profile screen.
   * @param event click the button to engage
   * @throws IOException
   * @author Darian + Nicholas Hansen
   */
  @FXML
  void editProfile(MouseEvent event) throws IOException{
    //Goin to the edit profile page! Yeah!
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("editProfile.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(primaryScreen);
    window.show();

  }

  /**
   * This logs the user out and returns them to the login screen.
   * @param event The mouse click event created by the user clicking on the button
   * @throws IOException An exception that can occur if the fxml file is not found
   * @author Cameron
   */
  @FXML
  void logoutClicked(MouseEvent event) throws IOException {
    // Creating the new scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    // Getting the stage
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Setting stage
    window.setScene(primaryScreen);
    window.show();
  }

  /**
   * This method displays the groups the user is in to the table view in the profile tab.
   * @author Cameron
   */
  private void displayGroupsInProfile() {
    ObservableList<Group> currentUserGroups = FXCollections.observableArrayList();
    currentUserGroups.addAll(currentUser.getGroupLeader());
    currentUserGroups.addAll(currentUser.getGroupMember());
    pGroupTable.getItems().addAll(currentUserGroups);

  }

  /**
   *
   * @author Cameron
   */
  private void populateGroupSelectors() {
    editGroupSelector.getItems().clear();
    groupsPicker.getItems().clear();

    // Populating edit group picker and groups picker with groups the user is a leader of
    for (Group g : currentUser.getGroupLeader()) {
      editGroupSelector.getItems().add(g.getName());
      groupsPicker.getItems().add(g.getName());
    }
    // populate the group picker for meeting search
    for (Group g : currentUser.getGroupMember()) {
      groupsPicker.getItems().add(g.getName());
    }
  }
}
