package samegame.Pane;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import samegame.listener.SameGameHeightResizeListener;
import samegame.listener.SameGameMouseListener;
import samegame.listener.SameGameWidthResizeListener;
import samegame.model.SameGameBoard;
import samegame.model.SameGameCanvas;

/**
 * 
 * Class that Displays the SameGame on a Borderpane and implements playing it
 *
 */
public class SameGamePane extends BorderPane
{
   // Class Variables
   private SameGameBoard board;

   private SameGameCanvas canvas;

   private Label blockCount = new Label();

   /**
    * 
    * Constructor that Initialises the Canvas and Board, sets the Listeners
    * and adds everything to the Borderpane
    *
    */
   public SameGamePane()
   {
      board = new SameGameBoard();
      canvas = new SameGameCanvas(board);

      canvas.setOnMouseClicked(new SameGameMouseListener(this));
      heightProperty().addListener(new SameGameHeightResizeListener(canvas));
      widthProperty().addListener(new SameGameWidthResizeListener(canvas));
      setCenter(canvas);
      setBottom(blockCount);
      canvas.renderCanvas();
      updateLabel();
   }

   /**
    * 
    * Method that Displays the End of Game Message and either Resets the Board
    * or Ends the Process
    *
    */
   public void endGame()
   {
      Alert popup = new Alert(AlertType.CONFIRMATION);
      popup.setTitle("Game over!");

      // Game won or lost?
      if (board.getRemaining() > 0)
      {
         popup.setHeaderText("Sie haben verloren.");
         popup.setContentText("Es sind noch " + board.getRemaining()
               + " Blöcke über! \nUm nocheinmal zu spielen drücken sie \"OK\" \nUm das Programm zu beenden drücken sie \"Abbrechen\" ");
      }
      else
      {
         popup.setHeaderText("Sie haben gewonnen.");
         popup.setContentText(
               "Alle blöcke wurden entfernt! \nUm nocheinmal zu spielen drücken sie \"OK\" \nUm das Programm zu beenden drücken sie \"Abbrechen\" ");
      }

      // Check for new round or Exit
      Optional<ButtonType> result = popup.showAndWait();
      if (result.get() == ButtonType.OK)
      {
         this.board.resetGame();
         canvas.renderCanvas();
         updateLabel();
      }
      else
      {
         Platform.exit();
      }

   }

   /**
    * 
    * Method that updates the Label with the remaining Block count.
    *
    */
   public void updateLabel()
   {
      this.blockCount.setText(
            "Remainig Blocks: " + Integer.toString(board.getRemaining()));

   }

   /**
    * 
    * Getter for the Canvas
    *
    * @return the Canvas
    */
   public SameGameCanvas getCanvas()
   {
      return this.canvas;
   }

   /**
    * 
    * Getter for the Board
    *
    * @return the Board
    */
   public SameGameBoard getBoard()
   {
      return this.board;
   }
}
