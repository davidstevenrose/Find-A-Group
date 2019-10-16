package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class Controller {
  @FXML private TitledPane title;

  @FXML private Button searchGroupsButton;

  @FXML private ChoiceBox<String> searchTag1;

  @FXML private ChoiceBox<String> searchTag3;

  @FXML private ChoiceBox<String> searchTag4;

  @FXML private ChoiceBox<String> searchTag2;

  @FXML private Button joinGroupsButton;

  @FXML private TableView<Group> searchGroupTable;

  @FXML private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML private Tab meetingsGroupNameCol;

  @FXML private TextField searchLocationTextbox;

  @FXML private DatePicker searchDataPicker;

  @FXML private Button searchMeetingsButton;

  @FXML private ChoiceBox<String> groupsPicker;

  @FXML private TableView<String> findMeetingsTable;

  @FXML private TableColumn<?, ?> meetingsDateCol;

  @FXML private TableColumn<?, ?> meetingsTimeCol;

  @FXML private TableColumn<?, ?> meetingsLocationCol;

  @FXML private Tab add;

  @FXML private ChoiceBox<String> addTag1;

  @FXML private ChoiceBox<String> addTag2;

  @FXML private ChoiceBox<String> addTag3;

  @FXML private ChoiceBox<String> addTag4;

  @FXML private DatePicker addMeetingDatePicker;

  @FXML private TextField addMeetingLocationTextfield;

  @FXML private Button saveChanges;

  @FXML private TextArea groupDescription;

  @FXML private ComboBox<?> addMeetingTimePicker;

  @FXML private ChoiceBox<String> editGroupSelector;

  @FXML private TextField createGroupTextfield;

  private String[] tags = {"Gaming", "Sports", "Fitness", "Reading", "Study", "Social","Fun"};

  @FXML
  public void initialize() {
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
  }

  @FXML
  void joinGroupButtonClick(MouseEvent event) {}

  @FXML
  void searchGroupsButtonClicked(MouseEvent event) {
    searchGroupsGroupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    searchGroupsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    Group exampleGroup1 =
        new Group(
            "FGCU Games Group",
            "A group of FGCU students who like to play video games, lets see if making the description much longer breaks the code and makes it not properly show up in the table view of the groups");
    Group exampleGroup2 =
        new Group(
            "FGCU Running Group", "A group of FGCU students who like to get together and run");
    ArrayList<Group> groups = new ArrayList<>();
    groups.add(exampleGroup1);
    groups.add(exampleGroup2);
    searchGroupTable.setItems(FXCollections.observableArrayList(groups));
  }
}
