package sample;

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
import javafx.scene.input.MouseEvent;

public class Controller {
  @FXML private TitledPane title;

  @FXML private Button searchGroupsButton;

  @FXML private ChoiceBox<String> searchTag1;

  @FXML private ChoiceBox<String> searchTag3;

  @FXML private ChoiceBox<String> searchTag4;

  @FXML private ChoiceBox<String> searchTag2;

  @FXML private Button joinGroupsButton;

  @FXML private TableView<?> searchGroupTable;

  @FXML private TableColumn<?, ?> searchGroupsGroupNameCol;

  @FXML private TableColumn<?, ?> searchGroupsDescriptionCol;

  @FXML private Tab meetingsGroupNameCol;

  @FXML private TextField searchLocationTextbox;

  @FXML private DatePicker searchDataPicker;

  @FXML private Button searchMeetingsDateLocationButton;

  @FXML private Button searchByGroupsButton;

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



  @FXML
  public void initialize() {
    /*
    // Adding values to they type choice box
    for (ItemType it : ItemType.values()) {
        productType.getItems().add(it);
    }

    // Adding numbers to the quantity selector
    for (int i = 1; i < 11; i++) {
        String number = "" + i;
        chooseQuantity.getItems().add(number);
    }
    chooseQuantity.getSelectionModel().selectFirst();
    chooseQuantity.setEditable(true);
    testMultimedia();
    connectToDatabase();
    populateProductLine();
    */

  }
    @FXML
    void joinGroupButtonClick(MouseEvent event) {
    }
}
