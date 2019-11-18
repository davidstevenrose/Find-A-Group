package io.github.davidstevenrose;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

  //String in format XX:XX. Does not support military time, and ten's place digit in hour may be omitted.
  public static final String TIMEREGEX = "^([0]?\\d|1[0-2]):[0-5]\\d\n$";

  //String in format w+@w+.[a-zA-Z]+
  public static final String EMAILREGEX = "^\\w+@\\w+.[a-zA-Z]+$";

  //The password must conform to shall statement 9
  public static final String PWORDREGEX = "^\\w{7,}$";

  /**
   * The AM\PM picker
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
  private Label messageLabelEGT;
  /**
   * adds meeting to selected group in edit group tab.
   */
  @FXML
  private Button addMeetingButton;

  @FXML
  private CheckBox mustIncludeAllCheckBox;

  @FXML
  private ChoiceBox<String> searchTag1;

  @FXML
  private ChoiceBox<String> searchTag3;
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
  private ChoiceBox<String> searchTag4;

  @FXML
  private ChoiceBox<String> searchTag2;

  @FXML
  private Button joinGroupsButton;

  @FXML
  private TableView<Group> searchGroupTable;

  @FXML
  private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML
  private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML
  private TableView<Group> pGroupTable;

  @FXML
  private TableColumn<?, ?> pGroupName;

  @FXML
  private TableColumn<?, ?> pDescription;

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

  /**
   * The choice box in the edit group tab that is populated with the groups the current user owns.
   */
  @FXML
  private ChoiceBox<Group> editGroupSelector;

  @FXML
  private TextField createGroupTextfield;

  @FXML
  private TextArea addDescriptionTextarea;

  @FXML
  private TextArea editDescriptionTextArea;

  @FXML
  private Label savedChangesLabel;

  @FXML
  private Label savedChangesLabel1;

  @FXML
  private Label joinLabel;

  @FXML
  private Tab editGroupTab;

  @FXML
  private TableView<Meeting> editMeetingTable;

  @FXML
  private TabPane tabPane;

  @FXML
  private Label userNameLabel;

  // Array list to store all of the groups in
  private ArrayList<Group> allGroups = new ArrayList<>();

  // Array list to store all of the meetings in
  private ArrayList<Meeting> allMeetings = new ArrayList<>();

  // The user currently using the program
  static User currentUser;


  @FXML
  void initialize() {
    //instantiate a listener to whenever the edit group choice box selects a group
    editGroupSelector.getSelectionModel().selectedItemProperty()
        .addListener((v, oldVal, newVal) -> fillEditGroupTab());

    /*
     * ------------------------------------------------------ Profile Code
     *
     * @author Darian + Nicholas Hansen
     */
    // Profile uses the user's input username (current user) displays it on the profile tab.

    userNameLabel.setText(currentUser.getUsername() + "!");

    // TODO: 11/7/2019 Populate the table on the profile tab with groups they are a member of.
    // TODO: 11/7/2019 Clean up the initialize statement's foreach loops?

    // --------------------------------------------------------

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

    // Putting values in the add meeting time picker
    //testing, remove later
    for (int i = 1; i < 13; i++) {
      addMeetingTimePicker.getItems().add(i + ":00 AM");
      addMeetingTimePicker.getItems().add(i + ":00 PM");
    }
    //end remove

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

    driverMethod();

    //clear error message labels - David
    //label in edit group tab
    //TODO: configure style of label
    messageLabelEGT.setText("");


  }

  /**
   * Driver module for testing program
   */
  private void driverMethod() {

    // testing remove after
    Group exampleGroup1 =
        new Group("FGCU Games Group", "A group of FGCU students who like to play video games");
    Group exampleGroup2 =
        new Group("FGCU Running Group",
            "A group of FGCU students who like to get together and run");
    Group exampleGroup3 =
        new Group("FGCU Book Club", "A group of FGCU students who like to get together and read");
    Meeting exampleMeeting1 = new Meeting(LocalDate.now(), "FGCU", "5:00 PM", "FGCU Games Group");
    Meeting exampleMeeting2 =
        new Meeting(LocalDate.of(2019, 10, 20), "FGCU", "5:00 PM", "FGCU Running Group");
    Meeting exampleMeeting3 =
        new Meeting(LocalDate.of(2019, 10, 25), "not FGCU", "5:00 PM", "FGCU Book Club");
    // remove
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

    // Hiding the editGroupsTab on startup
    //temporary comment out artifact from master branch
    //if (currentUser.getGroupLeader().size() == 0) {
    // tabPane.getTabs().remove(editGroupTab);
    //}

    // Setting the first element as selected
    searchGroupTable.getSelectionModel().selectFirst();
    findMeetingsTable.getSelectionModel().selectFirst();
    searchTag1.getSelectionModel().selectFirst();
    searchTag2.getSelectionModel().selectFirst();
    searchTag3.getSelectionModel().selectFirst();
    searchTag4.getSelectionModel().selectFirst();
  }

  /**
   * Creates a group and returns a reference to a group object.
   *
   * @return a group object reference
   * @throws Exception if no tags or name for group is provided
   * @author drose
   */
  private Group createGroup() throws Exception {
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
    String desc =
        ((addDescriptionTextarea.getText() == null) ? "" : addDescriptionTextarea.getText());
    if (tags.size() == 0 || groupName.isEmpty()) {
      throw new Exception();
    }
    return new Group(groupName, desc, tags, currentUser.getUsername());
  }

  /**
   * This method runs when the joinGroupButton button is clicked. This method gets the group
   * selected in the searchGroupTable table and adds that group to the groupMember ArrayList in the
   * user object.
   *
   * @param event The mouse click event created by the user clicking on the button
   * @author Cameron
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
   * @param event The mouse click event created by the user clicking on the button
   * @author Cameron
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

    // Displaying that group in the user groups page

  }

  /**
   * Goes through the process of creating a group and saving the new group data to the database.
   *
   * @param event the source of the event
   * @author drose
   */
  @FXML
  void createGroupsButtonClicked(MouseEvent event) {
    //TODO: encapsulate some labels for exception scenarios.
    createGroupErrorMsg.setVisible(false);
    Group newGroup;
    try {
      newGroup = createGroup();
    } catch (Exception e) {
      createGroupErrorMsg.setVisible(true);
      return;
    }
    //adds new group to user's collection of groups
    currentUser.addGroupLeader(newGroup);
    allGroups.add(newGroup);
    //return to searchForGroups tab
    tabPane.getSelectionModel().select(searchForGroupsTab);
    TextFileManager.addGroupToFile(newGroup);
    System.out.println("New group added to database.");
    createGroupTextfield.clear();
  }

  /**
   * Fills the edit group tab with information of the selected group to edit.
   * @author drose
   */
  private void fillEditGroupTab() {
    editMeetingTable.getItems().clear();
    Group selectedGroup = editGroupSelector.getValue();
    List<String> tags = selectedGroup.getTags();
    try {
      editTag1.setValue(tags.get(0));
      editTag1.setValue(tags.get(1));
      editTag1.setValue(tags.get(2));
      editTag1.setValue(tags.get(3));
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
    try {
      Group selectedGroup = new Group("error", "error"); // only if broken will happen
      for (Group g : currentUser.getGroupLeader()) {
        if (g.isEqualTo(editGroupSelector.getValue())) {
          selectedGroup = g;
        }
      }
      // updating description
      selectedGroup.setDescription(editDescriptionTextArea.getText());

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

      // getting values from user
       /* LocalDate meetingDate = addMeetingDatePicker.getValue();
        String meetingLocation = addMeetingLocationTextfield.getText();
        String meetingTime = addMeetingTimePicker.getValue();
        // creating new meeting
        Meeting meeting =
            new Meeting(meetingLocation, meetingDate, meetingTime, selectedGroup.getName(),
                currentUser.getUsername());
        meeting.addAttendee(currentUser.getUsername());
        // updating all meetings and group meetings
        allMeetings.add(meeting);
        selectedGroup.addMeeting(meeting);*/

      // displaying information to the user
      savedChangesLabel1.setText("Saved Changes");

      // Resetting fields
      editDescriptionTextArea.clear();
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
   * @param event The mouse click event created by the user clicking on the button
   * @author Cameron
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
   * @param event The mouse click event created by the user clicking on the button
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
   * This takes you from the Primary to the edit profile screen.
   *
   * @param event click the button to engage
   * @throws IOException
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
    //setAll removes repeated current groups in table view
    pGroupTable.getItems().setAll(currentUserGroups);
  }

  /**
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
  }

  /**
   * The tables should be updated every time the user clicks on the respective tab.
   *
   * @deprecated
   */
  void updateMeetings() {
    // adding all of the meetings into the all meetings array
    for (Group g : allGroups) {
      for (Meeting m : g.getMeetings()) {
        allMeetings.add(m);
      }
    }
  }

  /**
   * Fills the combo boxes by retrieving the current list of pre-made tags. The cbo uses tags, but
   * may be converted to using enumerations of TagCollection.
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
      cho.getItems().addAll(tags);
    }
  }

  /**
   * Creates a meeting and adds the meeting to the selected group's list of meetings.
   *
   * @author drose
   */
  @FXML
  private void addMeetingButtonClicked() {
    if (editGroupSelector.getSelectionModel().isEmpty()) {
      if (addMeetingDatePicker.getValue() != null
          && addMeetingLocationTextfield.getText() != null
          && addMeetingTimePicker.getValue() != null) {

        LocalDate meetingDate = addMeetingDatePicker.getValue();
        String meetingLoc = addMeetingLocationTextfield.getText();
        // replace combo box with string regex
        String meetingTime = addMeetingTimePicker.getSelectionModel().getSelectedItem();
        if (Pattern.matches(TIMEREGEX, meetingTime)) {
          meetingTime = meetingTime.concat(meridiemBox.getValue());
          Meeting newMeeting = new Meeting(meetingDate, meetingLoc, meetingTime,
              editGroupSelector.getValue().getName());
          editGroupSelector.getValue().addMeeting(newMeeting);
          addMeetingLocationTextfield.clear();
          addMeetingTimePicker.getSelectionModel().clearSelection();
          //reset edit group tab
          fillEditGroupTab();
          //confirm add meeting
          messageLabelEGT.setText("Meeting added");
          //send message to text file to update group meeting data
          TextFileManager.addMeetingToFile(newMeeting);
        } else {
          //invalid time input, handle error
          messageLabelEGT.setText("Invalid time. Please try again.");
          //TODO: set the style of the message to error style
        }
      } else {
        //a required field is missing, handle error
        messageLabelEGT.setText("Please fill out all fields.");
      }
    } else {
      //group not selected, handle error
      messageLabelEGT.setText("Please select a group to edit.");
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
          //get a reference to a meeting in the group's list of meeitngs
          Meeting selectedMeeting = editGroupSelector.getSelectionModel().getSelectedItem()
              .getMeetings().get(index);
          // Make that the selected meeting in the MeetDetController class
          EditableMeetDetController.setMeeting(selectedMeeting);
          // switch to the edit meeting scene
          Parent primaryScreenParent = FXMLLoader
              .load(getClass().getResource("EditableMeetingDetails.fxml"));
          Scene primaryScreen = new Scene(primaryScreenParent);
          Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
          window.setScene(primaryScreen);
          window.show();
        }
      }
    }
  }
}
