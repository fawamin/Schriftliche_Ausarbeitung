package samegame;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import samegame.Pane.SameGamePane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    SameGamePane root = new SameGamePane();
			Scene scene = new Scene(root,650,650);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
