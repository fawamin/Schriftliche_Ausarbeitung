package application;

import java.util.Optional;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import samegame.common.Constants;
import samegame.model.SameGameBoard;

public class SameGamePane extends BorderPane
{
   private SameGameBoard board;   
   private Canvas canvas;
   private Label blockCount = new Label();
   
   private final Color [] colors = new Color[]
   {
             Color.BLACK,
             Color.RED,
             Color.BLUE,
             Color.GREEN
         
   };


   private GraphicsContext context;
   
   public SameGamePane()
   {
      board = new SameGameBoard();
      
      
      canvas = new Canvas(Constants.SCREEN_DIMENSION[0],Constants.SCREEN_DIMENSION[1]);
      
      canvas.setOnMouseClicked(event ->
      {
         double width = canvas.getWidth() / board.getColumns();
         
         double height = canvas.getHeight() / board.getRows();
         
         int row = (int) (event.getY()/height);
         
         int column = (int) (event.getX() / width);
         
         board.deleteBlocks(row, column);
         
         render();
         
         updateLabel();
         
         if(board.isGameOver())
         {
            endGame();
         }
         
      });
      
      setCenter(canvas);
      setBottom(blockCount);
      
      context = canvas.getGraphicsContext2D();
      
      render();
      updateLabel();
   }

   private void endGame()
   {
      Alert popup = new Alert(AlertType.INFORMATION);
      popup.setTitle("Game over!");
      if(board.getRemaining() != 0)
      {
         popup.setHeaderText("Sie haben verloren.");
         popup.setContentText("Es sind noch "+ board.getRemaining() +" Blöcke über! \nUm nocheinmal zu spielen drücken sie \"OK\"");
      }
      else
      {
         popup.setHeaderText("Sie haben gewonnen.");
         popup.setContentText("Alle blöcke wurden entfernt! \nUm nocheinmal zu spielen drücken sie \"OK\" ");
      }
      Optional<ButtonType> result = popup.showAndWait();
      if(result.get() == ButtonType.OK)
      {
         this.board =  new SameGameBoard();
         render();
      }
      else
      {
         
      }
      
   }

   private void render()
   {
      context.setFill(Color.BLACK);
      context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
      
      double width = canvas.getWidth() / board.getColumns();
      
      double height = canvas.getHeight() / board.getRows();
      
      for(int col = 0; col < board.getColumns(); col++)
      {
         for(int row = 0; row < board.getRows(); row++)
         {
            int block = board.getBoardSpace(row, col);
            
            if(block > 0)
            {
               context.setFill(colors[block]);
               context.fillRect( (col*width) , (row*height) , height, width );
            }
         }
      }
      
   }

   private void updateLabel()
   {
      this.blockCount.setText(Integer.toString(board.getRemaining()));
      
   }
}
