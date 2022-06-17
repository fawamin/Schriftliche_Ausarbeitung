package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import samegame.model.SameGameBoard;
import samegame.common.Constants;

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
         
      });
      
      setCenter(canvas);
      setBottom(blockCount);
      
      context = canvas.getGraphicsContext2D();
      
      render();
      updateLabel();
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
