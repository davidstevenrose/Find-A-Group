package io.github.davidstevenrose;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
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

  /**
   * The tab to create a new group.
   */
  public Tab createGroupTab;
  /**
   * The button to submit user input to create a new group.
   */
  public Button createGroupsButton;
  /**
   * The tab to view meetings from groups the user has joined.
   */
  public Tab findMeetingsTab;
  /**
   * The tab to search and view groups for the user to join.
   */
  public Tab searchForGroupsTab;
  /**
   * The profile tab to allow the user to toggle between profile and group.
   */
  public Tab profileTabDriver;
  /**
   * The lable to print the user's username.
   */
  public Label profileUserNameLab;
  /**
   * The first of four tag choice boxes. Located in create group tab.
   */
  @FXML
  private ChoiceBox<String> addTag1;
  /**
   * The second of four tag choice boxes. Located in create group tab.
   */
  @FXML
  private ChoiceBox<String> addTag2;
  /**
   * The third of four tag choice boxes. Located in create group tab.
   */
  @FXML
  private ChoiceBox<String> addTag3;
  /**
   * The fourth of four tag choice boxes. Located in create group tab.
   */
  @FXML
  private ChoiceBox<String> addTag4;

  @FXML
  private Button joinGroupsButton;

  @FXML
  private TableView<Group> searchGroupTable;

  @FXML
  private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML
  private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML
  private TextField searchLocationTextbox;

  @FXML
  private DatePicker searchDatePicker;

  @FXML
  private Button searchMeetingsButton;

  @FXML
  private ChoiceBox<String> groupsPicker;

  @FXML
  private TableView<Meeting> findMeetingsTable;

  @FXML
  private TableColumn<?, ?> meetingsGroupNameCol;

  @FXML
  private TableColumn<?, ?> meetingsDateCol;

  @FXML
  private TableColumn<?, ?> meetingsTimeCol;

  @FXML
  private TableColumn<?, ?> meetingsLocationCol;

  @FXML
  private ChoiceBox<String> searchTag1;

  @FXML
  private ChoiceBox<String> searchTag2;

  @FXML
  private ChoiceBox<String> searchTag3;

  @FXML
  private ChoiceBox<String> searchTag4;

  @FXML
  private ChoiceBox<String> editTag1;

  @FXML
  private ChoiceBox<String> editTag2;

  @FXML
  private ChoiceBox<String> editTag3;

  @FXML
  private ChoiceBox<String> editTag4;

  @FXML
  private DatePicker addMeetingDatePicker;

  @FXML
  private TextField addMeetingLocationTextfield;

  @FXML
  private ComboBox<String> addMeetingTimePicker;

  @FXML
  private ChoiceBox<String> editGroupSelector;

  @FXML
  private TextField createGroupTextfield;

  @FXML
  private TextArea addDescriptionTextarea;

  @FXML
  private TextArea addDescriptionTextarea1;

  @FXML
  private Label savedChangesLabel;

  @FXML
  private Label savedChangesLabel1;

  @FXML
  private Label joinLabel;

  @FXML
  private Tab editGroupTab;

  @FXML
  private TabPane tabPane;

  /**
   * A string of premade tags. This artifact is temporary, as tags will be converted to objects.
   */
  private final String[] tags = {
      "", "Gaming", "Sports", "Fitness", "Reading", "Study", "Fun", "Movies", "Art"
  };

  //these two next fields will be replaced by databases
  private ArrayList<Group> groups = new ArrayList<>();

  private ArrayList<Meeting> allMeetings = new ArrayList<>();

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

    //initialize create group scenario - David
    //list of tag choice boxes
    ArrayList<ComboBox<String>> tagBoxes = new ArrayList<>();//empty list
    fillBoxesWithTags(tagBoxes);
    //end initialize

    // Putting values in the tags boxes
    for (String tag : tags) {
      searchTag1.getItems().add(tag);
      searchTag2.getItems().add(tag);
      searchTag3.getItems().add(tag);
      searchTag4.getItems().add(tag);
      //these lines are replaced - David======
      addTag1.getItems().add(tag);
      addTag2.getItems().add(tag);
      addTag3.getItems().add(tag);
      addTag4.getItems().add(tag);
      //======================================
      editTag1.getItems().add(tag);
      editTag2.getItems().add(tag);
      editTag3.getItems().add(tag);
      editTag4.getItems().add(tag);
    }
    // Updating the meetings ArrayList
    updateMeetings();

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
    groups.add(exampleGroup1);
    groups.add(exampleGroup2);
    groups.add(exampleGroup3);
    // remove later

    // Adding values to group display on startup
    // preparing columns
    searchGroupsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    searchGroupsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    searchGroupTable.setItems(FXCollections.observableArrayList(groups));

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

    //add username to profile tab (driver)
    profileUserNameLab.setText(currentUser.getUsername());
  }

  @FXML
  void joinGroupButtonClick(MouseEvent event) {
    // Getting selected group from table
    Group group = searchGroupTable.getSelectionModel().getSelectedItem();
    // Adding user to group
    currentUser.addGroupMember(group);
    // Updating the selectors
    populateGroupSelectors();

    // Displaying result for the user
    joinLabel.setText("Join Successful");
  }

  @FXML
  void searchGroupsButtonClicked(MouseEvent event) {

    // creating an array list to hold the groups that match the search criteria
    ArrayList<Group> foundGroups = new ArrayList<>();
    // ternary to set the tag to "" if empty instead of null
    String tag1 = ((searchTag1.getValue() == null) ? "" : searchTag1.getValue());
    String tag2 = ((searchTag2.getValue() == null) ? "" : searchTag2.getValue());
    String tag3 = ((searchTag3.getValue() == null) ? "" : searchTag3.getValue());
    String tag4 = ((searchTag4.getValue() == null) ? "" : searchTag4.getValue());

    // checking if groups contain tags
    // Later this will be done with a database search
    for (Group group : groups) {
      if (group.getTags().contains(tag1)
          && group.getTags().contains(tag2)
          && group.getTags().contains(tag3)
          && group.getTags().contains(tag4)) {
        foundGroups.add(group);
      }
    }
    // displaying the groups matching search criteria
    searchGroupTable.setItems(FXCollections.observableArrayList(foundGroups));
  }

  /**
   * Goes through the process of creating a group and saving the new group data to the database.
   *
   * @param event the source of the event
   */
  @FXML
  void createGroupsButtonClicked(MouseEvent event) {
    //TODO: encapsulate some labels for exception scenarios.
    Group newGroup = createGroup();
    //adds new group to user's collection of groups
    //note: for easier retrieval of group from database, we should assign the user a collection of group ID's the user owns
    groups.add(newGroup);
    //return to searchForGroups tab
    tabPane.getSelectionModel().select(searchForGroupsTab);
    System.out.println("New group added to database.");
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
      selectedGroup.replaceTags(tags);

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

  void populateGroupSelectors() {
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

  void updateMeetings() {
    // adding all of the meetings into the all meetings array
    for (Group g : groups) {
      for (Meeting m : g.getMeetings()) {
        allMeetings.add(m);
      }
    }
  }

  /**
   * Creates a group and returns a reference to a group object.
   *
   * @return a group object reference
   */
  private Group createGroup() {
    String groupName = createGroupTextfield.getText();
    ArrayList<String> tags = new ArrayList<>();
    if (addTag1.getSelectionModel().getSelectedIndex() > 0) {
      tags.add(addTag1.getValue());
    }
    if (addTag2.getSelectionModel().getSelectedIndex() > 0) {
      tags.add(addTag2.getValue());
    }
    if (addTag3.getSelectionModel().getSelectedIndex() > 0) {
      tags.add(addTag3.getValue());
    }
    if (addTag4.getSelectionModel().getSelectedIndex() > 0) {
      tags.add(addTag4.getValue());
    }
    String desc = ((addDescriptionTextarea.getText() == null) ? ""
        : addDescriptionTextarea.getText());
    return new Group(groupName, desc, tags, currentUser.getUsername());
  }

  /**
   * Fills the combo boxes by retrieving the current list of premade tags. The cbo uses tags, but
   * may be converted to using enumerations of TagCollection. consult Cameron for further notes.
   *
   * @param tagBoxes the list of combo boxes to fill with group tags.
   */
  private void fillBoxesWithTags(ArrayList<ComboBox<String>> tagBoxes) {
    ArrayList<String> tags = new ArrayList<>();
    for (TagCollection tag : TagCollection.class.getEnumConstants()
    ) {
      tags.add(tag.getName());
    }
    for (ComboBox<String> cbo : tagBoxes) {
      cbo.getItems().addAll(tags);
    }
  }

  /**
   * Creates a meeting and displays it under the search meeting table of all group members.
   * Currently, there are no add/edit meeting controls, so method will be implemented with BUI in
   * mind.
   */
  private void addMeeting() {
    LocalDate meetingDate = addMeetingDatePicker.getValue();
    //replace combo box with string regex
    String meetingTime = addMeetingTimePicker.getSelectionModel().getSelectedItem();
    meetingTime = "12:00PM";
    String meetingLoc = addMeetingLocationTextfield.getText();
    Meeting newMeeting = new Meeting(meetingLoc, meetingDate, meetingTime, "driver group", "Tom");
    //add meeting to list of meetings for that meeting's group

  }


}