package samegame;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import samegame.Pane.SameGamePane;
import samegame.common.Constants;


/**
 * 
 * Main Class that shows a SameGamePane
 *
 */
public class Main extends Application {
   /**
    * Start Method that initialises and displays a SameGamePane
    */
	@Override
	public void start(Stage primaryStage) {
		try {
		    SameGamePane root = new SameGamePane();
			Scene scene = new Scene(root,Constants.SCREEN_DIMENSION[0],Constants.SCREEN_DIMENSION[1]);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
	/**
	 * 
	 * Main Method that starts the program
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
