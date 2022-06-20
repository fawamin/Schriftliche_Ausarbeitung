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

public class SameGamePane extends BorderPane
{
   private SameGameBoard board;

   private SameGameCanvas canvas;

   private Label blockCount = new Label();

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

   public void endGame()
   {
      Alert popup = new Alert(AlertType.CONFIRMATION);
      popup.setTitle("Game over!");

      // Game won or lost?
      if (board.getRemaining() > 0)
      {
         popup.setHeaderText("Sie haben verloren.");
         popup.setContentText("Es sind noch " + board.getRemaining()
               + " Bl�cke �ber! \nUm nocheinmal zu spielen dr�cken sie \"OK\" \nUm das Programm zu beenden dr�cken sie \"Abbrechen\" ");
      }
      else
      {
         popup.setHeaderText("Sie haben gewonnen.");
         popup.setContentText(
               "Alle bl�cke wurden entfernt! \nUm nocheinmal zu spielen dr�cken sie \"OK\" \nUm das Programm zu beenden dr�cken sie \"Abbrechen\" ");
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

   public void updateLabel()
   {
      this.blockCount.setText(
            "Remainig Blocks: " + Integer.toString(board.getRemaining()));

   }

   public SameGameCanvas getCanvas()
   {
      return this.canvas;
   }

   public SameGameBoard getBoard()
   {
      return this.board;
   }
}
