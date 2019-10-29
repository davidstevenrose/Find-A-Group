package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that holds the main method.
 * <h1>Some Thoughts</h1>
 * <p>Our SRS specifies that we must direct the client to the profile page after logon,
 * but we have no fxml for the profile page (Statement 13). We also must revisit the 'group finding'
 * mechanism the client will be using. I.E. should the user only search with up to four group tags?
 *
 * </p>
 */
public class Main extends Application {

    private String fieldOne;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("MeetingDetails.fxml"));
        primaryStage.setTitle("2sday's Find A Group");
        primaryStage.setScene(new Scene(root, 383, 469));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
