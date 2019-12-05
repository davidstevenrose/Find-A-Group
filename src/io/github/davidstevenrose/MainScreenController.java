package io.github.davidstevenrose;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController {

  //String in format XX:XX. Does not support military time, and ten's place digit in hour may be
  //omitted.
  static final String TIME_REGEX = "^([0]?\\d|1[0-2]):[0-5]\\d$";

  //String in format w+@w+.[a-zA-Z]+
  static final String EMAIL_REGEX = "^\\w+@\\w+.[a-zA-Z]+$";

  //The password must conform to shall statement 9
  static final String P_WORD_REGEX = "^\\w{7,}$";

  /**
   * The meeting location column in edit meeting table.
   */
  @FXML
  private TableColumn<TableView<Meeting>, String> editMeetingPlaceCol;

  /**
   * The meeting time column in edit meeting table.
   */
  @FXML
  private TableColumn<TableView<Meeting>, String> editMeetingTimeCol;

  /**
   * The meeting date column in edit meeting table.
   */
  @FXML
  private TableColumn<TableView<Meeting>, String> editMeetingDateCol;

  /**
   * The text field for choosing the time of a meeting.
   */
  @FXML
  private TextField selectTimeTxt;

  /**
   * The AM\PM picker.
   */
  @FXML
  private ChoiceBox<String> meridiemBox;

  /**
   * error message in create group tab.
   */
  @FXML
  private Label createGroupErrorMsg;

  /**
   * error message in edit group tab.
   */
  @FXML
  private Label messageLabelEditGroup;

  @FXML
  private CheckBox mustIncludeAllCheckBox;

  @FXML
  private ChoiceBox<String> searchTag1;

  @FXML
  private ChoiceBox<String> searchTag3;
  /**
   * The tab to search and view groups for the user to join.
   */
  @FXML
  private Tab searchForGroupsTab;
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
  private ChoiceBox<String> searchTag4;

  @FXML
  private ChoiceBox<String> searchTag2;

  @FXML
  private TableView<Group> searchGroupTable;

  @FXML
  private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML
  private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML
  private TableView<Group> ptGroupTable;

  @FXML
  private TableColumn<?, ?> ptGroupName;

  @FXML
  private TableColumn<?, ?> ptDescription;

  @FXML
  private TextField searchLocationTextbox;

  @FXML
  private DatePicker searchDatePicker;

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

  /**
   * The choice box in the edit group tab that is populated with the groups the current user owns.
   */
  @FXML
  private ChoiceBox<Group> editGroupSelector;

  @FXML
  private TextField createGroupTextfield;

  /**
   * The description area in the create group tab.
   */
  @FXML
  private TextArea addDescriptionTextarea;

  @FXML
  private TextArea editDescriptionTextArea;

  @FXML
  private Label savedChangesLabel;

  @FXML
  private Label joinLabel;

  @FXML
  private TableView<Meeting> editMeetingTable;

  @FXML
  private TabPane tabPane;

  @FXML
  private Label userNameLabel;

  // Array list to store all of the groups in
  static ArrayList<Group> allGroups = new ArrayList<>();

  // Array list to store all of the meetings in
  static ArrayList<Meeting> allMeetings = new ArrayList<>();

  // The user currently using the program
  static User currentUser;

  @FXML
  void initialize() {
    //instantiate a listener to whenever the edit group choice box selects a group
    editGroupSelector.getSelectionModel().selectedItemProperty()
        .addListener((v, oldVal, newVal) -> fillEditGroupTab());

    //the date picker is not editable
    addMeetingDatePicker.setEditable(false);
    //fill the edit group selector
    populateGroupSelectors();
    /*
    /**
     * ------------------------------------------------------ Profile Code
     *
     * @author Darian + Nicholas Hansen
     */
    // Profile uses the user's input username (current user) displays it on the profile tab.

    userNameLabel.setText(currentUser.getUsername() + "!");

    // list of tag choice boxes
    ArrayList<ChoiceBox<String>> tagBoxes = new ArrayList<>();
    tagBoxes.add(searchTag1);
    tagBoxes.add(searchTag2);
    tagBoxes.add(searchTag3);
    tagBoxes.add(searchTag4);
    tagBoxes.add(addTag1);
    tagBoxes.add(addTag2);
    tagBoxes.add(addTag3);
    tagBoxes.add(addTag4);
    tagBoxes.add(editTag1);
    tagBoxes.add(editTag2);
    tagBoxes.add(editTag3);
    tagBoxes.add(editTag4);
    //choice box for am/pm
    meridiemBox.getItems().addAll("AM", "PM");
    meridiemBox.setValue("AM");
    //the list of boxes are now filled
    fillBoxesWithTags(tagBoxes);

    // Adding values to group display on startup
    // preparing columns
    searchGroupsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    searchGroupsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    searchGroupTable.setItems(FXCollections.observableArrayList(allGroups));

    // Preparing columns
    ptGroupName.setCellValueFactory(new PropertyValueFactory<>("name"));
    ptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    // Adding value to display
    displayGroupsInProfile();

    // Adding meetings to display on startup
    // preparing columns
    meetingsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
    meetingsDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    meetingsTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    meetingsLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
    findMeetingsTable.setItems(FXCollections.observableArrayList(allMeetings));

    //preparing the editMeeting table
    //preparing columns
    editMeetingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("location"));
    editMeetingDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    editMeetingTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

    //clear error message labels - David
    //label in edit group tab
    messageLabelEditGroup.setText("");
    //label in create group tab
    createGroupErrorMsg.setVisible(false);
  }

  /**
   * Creates a group and returns a reference to a group object.
   *
   * @return a group object reference
   * @throws Exception if no tags or name for group is provided
   * @author drose
   */
  private Group createGroup() throws Exception {
    String groupName = createGroupTextfield.getText().trim();
    ArrayList<String> tags = new ArrayList<>();
    if (addTag1.getSelectionModel().getSelectedItem() != null) {
      tags.add(addTag1.getValue());
    }
    if (addTag2.getSelectionModel().getSelectedItem() != null) {
      tags.add(addTag2.getValue());
    }
    if (addTag3.getSelectionModel().getSelectedItem() != null) {
      tags.add(addTag3.getValue());
    }
    if (addTag4.getSelectionModel().getSelectedItem() != null) {
      tags.add(addTag4.getValue());
    }
    String desc =
        ((addDescriptionTextarea.getText() == null) ? "" : addDescriptionTextarea.getText());
    if (tags.size() == 0 || groupName.isEmpty()) {
      throw new Exception();
    }
    for (String s : tags) {
      System.out.println(s);
    }
    return new Group(groupName, desc, tags, currentUser.getUsername());
  }

  /**
   * This method runs when the joinGroupButton button is clicked. This method gets the group
   * selected in the searchGroupTable table and adds that group to the groupMember ArrayList in the
   * user object.
   *
   * @author Cameron
   */
  @FXML
  void joinGroupButtonClick() {
    try {
      // Getting selected group from table
      Group group = searchGroupTable.getSelectionModel().getSelectedItem();
      // Creating variable to keep track of if the user is a member of a group already
      boolean alreadyJoined = false;
      for (Group g : currentUser.getGroupMember()) {
        if (group.equals(g)) {
          alreadyJoined = true;
        }
      }
      for (Group g : currentUser.getGroupLeader()) {
        if (group.equals(g)) {
          alreadyJoined = true;
        }
      }
      if (!alreadyJoined) {
        // Adding user to group
        currentUser.addGroupMember(group);

        // Updating the users.txt file
        TextFileManager.editUser(LoginController.users);

        // Updating the selectors
        populateGroupSelectors();

        // Adding to profile tab
        displayGroupsInProfile();

        // Displaying result for the user
        joinLabel.setText("Join Successful");
        Main.fadeAway(joinLabel);
      } else {
        joinLabel.setText("You are already in that group");
        Main.fadeAway(joinLabel);
      }
    } catch (NullPointerException npe) {
      joinLabel.setText("Please select a group to join");
      Main.fadeAway(joinLabel);
    }
  }

  /**
   * This method runs when the searchGroupsButton button is clicked. This method reads the tags
   * selected by the user and displays groups to the searchGroupsTable based on the information that
   * the user provides.
   *
   * @author Cameron
   */
  @FXML
  public void searchGroupsButtonClicked() {

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
      // if tag is unselected don't include in search criteria
      String tag1 = searchTag1.getValue().equals("") ? "unused" : searchTag1.getValue();
      String tag2 = searchTag2.getValue().equals("") ? "unused" : searchTag2.getValue();
      String tag3 = searchTag3.getValue().equals("") ? "unused" : searchTag3.getValue();
      String tag4 = searchTag4.getValue().equals("") ? "unused" : searchTag4.getValue();
      // Making it so that if there are no tags selected, all of the groups will be shown
      if (tag1.equals("unused")
          && tag2.equals("unused")
          && tag3.equals("unused")
          && tag4.equals("unused")) {
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
  }

  /**
   * Goes through the process of creating a group and saving the new group data to the database.
   *
   * @author drose
   */
  @FXML
  void createGroupsButtonClicked() {
    Group newGroup;
    try {
      newGroup = createGroup();
    } catch (Exception e) {
      createGroupErrorMsg.setStyle("-fx-text-fill: Red");
      createGroupErrorMsg.setText("Please enter a group name or at least one group tag");
      createGroupErrorMsg.setVisible(true);
      Main.fadeAway(createGroupErrorMsg);
      return;
    }
    //check to see if group name already exists
    for (Group g : allGroups) {
      if (g.getName().equals(newGroup.getName())) {
        createGroupErrorMsg.setText("Group \"" + g.getName() + "\" already exists.");
        createGroupErrorMsg.setVisible(true);
        Main.fadeAway(createGroupErrorMsg);
        return;
      }
    }
    //adds new group to user's collection of groups
    currentUser.addGroupLeader(newGroup);
    allGroups.add(newGroup);
    //return to searchForGroups tab (broken)
    tabPane.getSelectionModel().select(searchForGroupsTab);
    //add group to text file
    TextFileManager.addGroupToFile(newGroup);
    //update user information with new group
    TextFileManager.editUser(LoginController.users);
    populateGroupSelectors();
    System.out.println("New group added to database.");
    createGroupTextfield.clear();
    addDescriptionTextarea.clear();
  }

  /**
   * Fills the edit group tab with information of the selected group to edit.
   *
   * @author drose
   */
  private void fillEditGroupTab() {
    editMeetingTable.getItems().clear();
    editTag1.setValue(null);
    editTag2.setValue(null);
    editTag3.setValue(null);
    editTag4.setValue(null);
    Group selectedGroup = editGroupSelector.getValue();
    if (selectedGroup == null) {
      return;
    }
    List<String> tags = selectedGroup.getTags();
    try {
      editTag1.setValue(tags.get(0));
      editTag2.setValue(tags.get(1));
      editTag3.setValue(tags.get(2));
      editTag4.setValue(tags.get(3));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("The group had less than 4 tags");
    }
    editDescriptionTextArea.setText(selectedGroup.getDescription());
    editMeetingTable.getItems().addAll(selectedGroup.getMeetings());
  }

  /**
   * Edits the group selected from the choice box.
   *
   * @author Nick + David
   */
  @FXML
  void editGroupsButtonClicked() {
    Group selectedGroup = editGroupSelector.getValue();
    if (selectedGroup == null) {
      savedChangesLabel.setStyle("-fx-text-fill: Red");
      savedChangesLabel.setText("Please select a group.");
      Main.fadeAway(savedChangesLabel);
      return;
    }
    // updating description
    selectedGroup.setDescription(editDescriptionTextArea.getText());

    // Create an array list for the tags
    ArrayList<String> tags = new ArrayList<>(0);
    //add new tags to group's list of tags
    if (editTag1.getValue() != null) {
      tags.add(editTag1.getValue());
    }
    if (editTag2.getValue() != null) {
      tags.add(editTag2.getValue());
    }
    if (editTag3.getValue() != null) {
      tags.add(editTag3.getValue());
    }
    if (editTag4.getValue() != null) {
      tags.add(editTag4.getValue());
    }
    //if no tags were selected
    if (tags.get(0).isEmpty()) {
      savedChangesLabel.setStyle("-fx-text-fill: Red");
      savedChangesLabel.setText("Please select at least one tag.");
      Main.fadeAway(savedChangesLabel);
      return;
    }

    // setting the tags to the selected ones
    selectedGroup.replaceTags(tags);

    // displaying information to the user
    savedChangesLabel.setStyle("-fx-text-fill: Black");
    savedChangesLabel.setText("Saved Changes");
    Main.fadeAway(savedChangesLabel);
  }

  /**
   * This method runs when the searchMeetingsButton button is clicked. This method checks the
   * criteria selected by the user and provides meetings fitting those criteria.
   *
   * <p>This method runs when the searchMeetingsButton button is clicked.</p>
   *
   * @author Cameron
   */
  @FXML
  void searchMeetingsButtonClicked() {

    // Creating ArrayList to hold meetings and giving it all the meetings
    ArrayList<Meeting> foundMeetings = new ArrayList<>();
    for (Group g : currentUser.getGroupLeader()) {
      foundMeetings.addAll(g.getMeetings());
    }
    for (Group g : currentUser.getGroupMember()) {
      foundMeetings.addAll(g.getMeetings());
    }

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
   * @param event The mouse click event created by the user clicking on the button
   * @author Cameron
   */
  @FXML
  void viewMeetingDetailsButtonClicked(MouseEvent event) throws IOException {
    // Get selected meeting
    Meeting selectedMeeting = findMeetingsTable.getSelectionModel().getSelectedItem();
    //make sure the user selected a row from the table - David
    if (selectedMeeting == null) {
      return;
    }
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
   *
   * @param event click the button to engage
   * @throws IOException if the FXMLLoader could not load the resource
   * @author Darian + Nicholas Hansen
   */
  @FXML
  void editProfile(MouseEvent event) throws IOException {
    // Going to the edit profile page! Yeah!
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("editProfile.fxml"));
    Scene primaryScreen = new Scene(primaryScreenParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(primaryScreen);
    window.show();
  }

  /**
   * This logs the user out and returns them to the login screen.
   *
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
   *
   * @author Cameron + Darian
   */
  private void displayGroupsInProfile() {
    ObservableList<Group> currentUserGroups = FXCollections.observableArrayList();
    currentUserGroups.addAll(currentUser.getGroupLeader());
    currentUserGroups.addAll(currentUser.getGroupMember());
    ptGroupTable.getItems().setAll(currentUserGroups);
  }

  /**
   * Fills the choice boxes that holds groups visible to the logged in user.
   *
   * @author Cameron
   */
  private void populateGroupSelectors() {
    editGroupSelector.getItems().clear();
    groupsPicker.getItems().clear();

    // Populating edit group picker and groups picker with groups the user is a leader of
    for (Group g : currentUser.getGroupLeader()) {
      editGroupSelector.getItems().add(g);
      groupsPicker.getItems().add(g.getName());
    }
    // populate the group picker for meeting search
    for (Group g : currentUser.getGroupMember()) {
      groupsPicker.getItems().add(g.getName());
    }
    editGroupSelector.getItems().add(null);
    groupsPicker.getItems().add(null);
  }

  /**
   * Fills the combo boxes by retrieving the current list of pre-made tags. The cbo uses tags, but
   * may be converted to using enumerations of TagCollection. Method is now compatible with the
   * search group module by adding an empty string instead of null for the not-selected option.
   *
   * @param tagBoxes the list of combo boxes to fill with group tags.
   * @author drose
   */
  private void fillBoxesWithTags(ArrayList<ChoiceBox<String>> tagBoxes) {
    ArrayList<String> tags = new ArrayList<>();
    for (TagCollection tag : TagCollection.class.getEnumConstants()) {
      tags.add(tag.getName());
    }
    for (ChoiceBox<String> cho : tagBoxes) {
      cho.getItems().add("");
      cho.getItems().addAll(tags);
      cho.getSelectionModel().selectFirst();
    }
  }

  /**
   * Creates a meeting and adds the meeting to the selected group's list of meetings.
   *
   * @author drose
   */
  @FXML
  private void addMeetingButtonClicked() {
    if ( !(editGroupSelector.getValue() == null)){
      if (addMeetingDatePicker.getValue() != null
          && !addMeetingLocationTextfield.getText().isEmpty()
          && !selectTimeTxt.getText().isEmpty()) {
        LocalDate meetingDate = addMeetingDatePicker.getValue();
        String meetingLoc = addMeetingLocationTextfield.getText();
        // replace combo box with string regex
        String meetingTime = selectTimeTxt.getText();
        if (Pattern.matches(TIME_REGEX, meetingTime)) {
          meetingTime = meetingTime.concat(meridiemBox.getValue());
          Meeting newMeeting = new Meeting(meetingLoc, meetingDate, meetingTime,
              editGroupSelector.getValue().getName(),
              editGroupSelector.getValue().getGroupLeaderName());
          editGroupSelector.getValue().addMeeting(newMeeting);
          addMeetingLocationTextfield.clear();
          selectTimeTxt.clear();
          addMeetingDatePicker.setValue(null);
          //reset edit group tab
          fillEditGroupTab();
          //confirm add meeting
          messageLabelEditGroup.setStyle("-fx-text-fill: Black");
          messageLabelEditGroup.setText("Meeting added");
          Main.fadeAway(messageLabelEditGroup);
          //send message to text file to update group meeting data
          TextFileManager.addMeetingToFile(newMeeting);
          System.out.println(newMeeting.toString());
        } else {
          //invalid time input, handle error
          messageLabelEditGroup.setStyle("-fx-text-fill: Red");
          messageLabelEditGroup.setText("Invalid time. Please try again.");
          Main.fadeAway(messageLabelEditGroup);
        }
      } else {
        //a required field is missing, handle error
        messageLabelEditGroup.setStyle("-fx-text-fill: Red");
        messageLabelEditGroup.setText("Please fill out all fields.");
        Main.fadeAway(messageLabelEditGroup);
      }
    } else {
      //group not selected, handle error
      messageLabelEditGroup.setStyle("-fx-text-fill: Red");
      messageLabelEditGroup.setText("Please select a group to edit.");
      Main.fadeAway(messageLabelEditGroup);
    }
  }

  /**
   * Begin editing a meeting. The event works on the double-click of a selected element in the
   * related table. Opens a pop-up box similar to the view meeting detail scene, but with editable
   * features.
   *
   * @param mouseEvent a passed event
   * @author drose
   */
  @FXML
  private void editMeetingEvent(MouseEvent mouseEvent) throws IOException {
    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
      if (mouseEvent.getClickCount() == 2) {
        if (!editMeetingTable.getSelectionModel().isEmpty()) {
          int index = editMeetingTable.getSelectionModel().getSelectedIndex();
          //get a reference to a meeting in the group's list of meetings
          Meeting selectedMeeting = editGroupSelector.getSelectionModel().getSelectedItem()
              .getMeetings().get(index);
          // switch to the edit meeting scene
          FXMLLoader loader = new FXMLLoader(getClass().getResource("EditableMeetingDetails.fxml"));
          Parent primaryScreenParent = loader.load();
          EditableMeetDetController c = loader.getController();
          // Make that the selected meeting in the MeetDetController class
          c.setCurrentMeeting(selectedMeeting);

          Scene primaryScreen = new Scene(primaryScreenParent);
          Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
          window.setScene(primaryScreen);
          window.show();
        }
      }
    }
  }

  /**
   * This method will resize the window while the user is viewing the edit groups tab in the tab
   * pane.
   *
   * @author Nicholas Hansen
   * @param event this listens for the changing of the tab selection. (Basically it listens for when
   *     the user selects a new tab)
   * @apiNote  due to merge conflicts with fxml files, this artifact is still under construction
   */
  public void resize(Event event) {
    /*if (%fx:id of tab%.isSelected()) {
      editGroupSelector.getScene().getWindow().setHeight(850);
    } else {
      ptGroupTable.getScene().getWindow().setHeight(650);
    }*/
  }
}
