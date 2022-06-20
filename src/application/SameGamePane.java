package application;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import samegame.model.SameGameBoard;

public class SameGamePane extends BorderPane
{
   private SameGameBoard board;   
   private SameGameCanvas canvas;
   private Label blockCount = new Label();
   
   public SameGamePane()
   {
      board = new SameGameBoard();
      
      canvas = new SameGameCanvas(board);
      
      canvas.setOnMouseClicked(event ->
      {
         double width = canvas.getWidth() / board.getColumns();
         
         double height = canvas.getHeight() / board.getRows();
         
         int row = (int) (event.getY()/height);
         
         int column = (int) (event.getX() / width);
         
         board.deleteBlocks(row, column);
         
         canvas.renderCanvas();
         
         updateLabel();
         
         if(board.isGameOver())
         {
            endGame();
         }
         
      });
      
      setCenter(canvas);
      setBottom(blockCount);      
      canvas.renderCanvas();
      
      updateLabel();
   }

   private void endGame()
   {
      Alert popup = new Alert(AlertType.CONFIRMATION);
      popup.setTitle("Game over!");
      
      //Game won or lost?
      if(board.getRemaining() > 0)
      {
         popup.setHeaderText("Sie haben verloren.");
         popup.setContentText("Es sind noch "+ board.getRemaining() +" Blöcke über! \nUm nocheinmal zu spielen drücken sie \"OK\" \nUm das Programm zu beenden drücken sie \"Abbrechen\" ");
      }
      else
      {
         popup.setHeaderText("Sie haben gewonnen.");
         popup.setContentText("Alle blöcke wurden entfernt! \nUm nocheinmal zu spielen drücken sie \"OK\" \nUm das Programm zu beenden drücken sie \"Abbrechen\" ");
      }
      
      // Check for Newgame or Exit
      Optional<ButtonType> result = popup.showAndWait();
      if(result.get() == ButtonType.OK)
      {
         this.board =  new SameGameBoard();
         canvas.setBoard(board);
         canvas.renderCanvas();
      }
      else
      {
         Platform.exit();
      }
      
   }

   private void updateLabel()
   {
      this.blockCount.setText(Integer.toString(board.getRemaining()));
      
   }
}
