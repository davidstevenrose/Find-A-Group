package sample;

import javafx.collections.FXCollections;
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

public class Controller {

  @FXML private Button searchGroupsButton;

  @FXML private ChoiceBox<String> searchTag1;

  @FXML private ChoiceBox<String> searchTag3;

  @FXML private ChoiceBox<String> searchTag4;

  @FXML private ChoiceBox<String> searchTag2;

  @FXML private Button joinGroupsButton;

  @FXML private TableView<Group> searchGroupTable;

  @FXML private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML private TextField searchLocationTextbox;

  @FXML private DatePicker searchDataPicker;

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

  @FXML private DatePicker addMeetingDatePicker;

  @FXML private TextField addMeetingLocationTextfield;

  @FXML private ComboBox<String> addMeetingTimePicker;

  @FXML private ChoiceBox<String> editGroupSelector;

  @FXML private TextField createGroupTextfield;

  @FXML private TextArea addDescriptionTextarea;

  @FXML private Label savedChangesLabel;

  @FXML private Label joinLabel;

  private String[] tags = {"", "Gaming", "Sports", "Fitness", "Reading", "Study", "Social", "Fun"};

  private ArrayList<Group> groups = new ArrayList<>();

  private ArrayList<Meeting> allMeetings = new ArrayList<>();

  static User currentUser;

  @FXML
  public void initialize() {
    // Putting values in the tags boxes
    for (String tag : tags) {
      searchTag1.getItems().add(tag);
      searchTag2.getItems().add(tag);
      searchTag3.getItems().add(tag);
      searchTag4.getItems().add(tag);
      addTag1.getItems().add(tag);
      addTag2.getItems().add(tag);
      addTag3.getItems().add(tag);
      addTag4.getItems().add(tag);
    }
    // Updating the meetings ArrayList
    updateMeetings();

    // Putting values in the add meeting time picker
    for (int i = 1; i < 13; i++) {
      addMeetingTimePicker.getItems().add(i + ":00 AM");
      addMeetingTimePicker.getItems().add(i + ":00 PM");
    }

    // Adding values to group selectors
    populateGroupSelectors();

    // values used for testing and demo, remove later
    allMeetings.add(new Meeting(LocalDate.now(), "FGCU", "5:00 PM", "FGCU Games Group"));
    Group exampleGroup1 =
        new Group("FGCU Games Group", "A group of FGCU students who like to play video games");
    exampleGroup1.addTag("Gaming");
    Group exampleGroup2 =
        new Group(
            "FGCU Running Group", "A group of FGCU students who like to get together and run");
    exampleGroup2.addTag("Fitness");
    exampleGroup2.addTag("Sports");
    groups.add(exampleGroup1);
    groups.add(exampleGroup2);
    // remove later
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
    // preparing columns
    searchGroupsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    searchGroupsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

    // creating an array list to hold the groups that match the search criteria
    ArrayList<Group> foundGroups = new ArrayList<>();
    // ternary to set the tag to "" if empty instead of null
    String tag1 = ((searchTag1.getValue() == null) ? "" : searchTag1.getValue());
    String tag2 = ((searchTag2.getValue() == null) ? "" : searchTag2.getValue());
    String tag3 = ((searchTag3.getValue() == null) ? "" : searchTag3.getValue());
    String tag4 = ((searchTag4.getValue() == null) ? "" : searchTag4.getValue());
    // checking if groups contain tags
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

  @FXML
  void createEditGroupsButtonClicked(MouseEvent event) {
    // If they are creating a new group
    if (editGroupSelector.getValue() == null && !createGroupTextfield.getText().isEmpty()) {

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
        groups.add(group);
        // Making the current user group leader
        currentUser.addGroupLeader(group);
        // Updating the selectors
        populateGroupSelectors();
        // creating a new meeting if all information entered
        if (addMeetingDatePicker.getValue() != null
            && addMeetingLocationTextfield.getText() != null
            && addMeetingTimePicker.getValue() != null) {
          // getting values from user
          LocalDate meetingDate = addMeetingDatePicker.getValue();
          String meetingLocation = addMeetingLocationTextfield.getText();
          String meetingTime = addMeetingTimePicker.getValue();
          // creating new meeting
          Meeting meeting = new Meeting(meetingDate, meetingLocation, meetingTime, name);
          // updating all meetings and group meetings
          allMeetings.add(meeting);
          group.addMeeting(meeting);
        }
        // displaying information to the user
        savedChangesLabel.setText("Saved Changes");

        // Resetting fields
        createGroupTextfield.setPromptText("Enter Group Name Here");
        addDescriptionTextarea.setPromptText("Add or Edit Description (optional)");
        addTag1.setValue("");
        addTag2.setValue("");
        addTag3.setValue("");
        addTag4.setValue("");
      } catch (Exception e) {
        savedChangesLabel.setText("Please enter all non-optional fields");
      }

    } else if (editGroupSelector.getValue() != null){
      Group selectedGroup = new Group("error", "error");
      for (Group g : currentUser.getGroupLeader()) {
        if (g.getName() == editGroupSelector.getValue()) {
          selectedGroup = g;
        }
      }
      // updating description
      selectedGroup.setDescription(addDescriptionTextarea.getText());

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

      // setting the tags to the selected ones
      selectedGroup.setTags(tags);

      // displaying information to the user
      savedChangesLabel.setText("Saved Changes");

      // Resetting fields
      createGroupTextfield.clear();
      addDescriptionTextarea.clear();
      addTag1.setValue("");
      addTag2.setValue("");
      addTag3.setValue("");
      addTag4.setValue("");

      // displaying information to the user
      savedChangesLabel.setText("Saved Changes");


    } else {
      System.out.println("askdjflkjasdkjdf");
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

  @FXML
  void searchMeetingsButtonClicked(MouseEvent event) {
    // preparing columns
    meetingsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
    meetingsDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    meetingsTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    meetingsLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

    // adding all meetings to table, change later for search functionality
    findMeetingsTable.setItems(FXCollections.observableArrayList(allMeetings));
  }

  @FXML
  void logoutClicked(MouseEvent event) throws IOException {
    // Creating the new scene
    Parent primaryScreenParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
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

    // Populating edit group picker with groups the user is a leader of
    for (Group g : currentUser.getGroupLeader()) {
      editGroupSelector.getItems().add(g.getName());
    }
    // populate the group picker for meeting search
    for (Group g : currentUser.getGroupMember()) {
      groupsPicker.getItems().add(g.getName());
    }
    for (Group g : currentUser.getGroupLeader()) {
      groupsPicker.getItems().add(g.getName());
    }
  }
}
