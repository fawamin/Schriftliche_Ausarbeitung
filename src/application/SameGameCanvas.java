package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import samegame.common.Constants;
import samegame.model.SameGameBoard;

public class SameGameCanvas extends Canvas
{
   private SameGameBoard board;
   private GraphicsContext context;
   
   private final Color [] colors = new Color[]
         {
                   Color.BLACK,
                   Color.RED,
                   Color.BLUE,
                   Color.GREEN
               
         };
   
   public SameGameCanvas(SameGameBoard sameGameBoard)
   {
      setHeight(Constants.SCREEN_DIMENSION[0]);
      setWidth(Constants.SCREEN_DIMENSION[1]);
      this.board = sameGameBoard;
      this.context = getGraphicsContext2D();
   }
   
   public void renderCanvas() 
   {
      this.context.setFill(Color.BLACK);
      this.context.fillRect(0, 0, getWidth(), getHeight());
      
      double width = getWidth() / this.board.getColumns();
      
      double height = getHeight() / this.board.getRows();
      
      for(int col = 0; col < this.board.getColumns(); col++)
      {
         for(int row = 0; row < this.board.getRows(); row++)
         {
            int block = this.board.getBoardSpace(row, col);
            
            if(block > 0)
            {
               this.context.setFill(colors[block]);
               this.context.fillRect( (col*width) , (row*height) , height, width );
            }
         }
      }
   }
   
   public void setBoard(SameGameBoard newBoard)
   {
      this.board = newBoard;
   }
}
