package samegame.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import samegame.common.Constants;

public class SameGameCanvas extends Canvas
{
   private SameGameBoard board;

   private final Color[] colors = new Color[] { Color.BLACK, Color.RED,
         Color.BLUE, Color.GREEN

   };

   public SameGameCanvas(SameGameBoard sameGameBoard)
   {
      if (sameGameBoard == null)
      {
         throw new IllegalArgumentException(
               "SameGameBoard im Canvas ist NULL!");
      }

      this.board = sameGameBoard;

      this.setHeight(Constants.SCREEN_DIMENSION[0]);
      this.setWidth(Constants.SCREEN_DIMENSION[1]);
   }

   public void renderCanvas()
   {
      this.getGraphicsContext2D().setFill(Color.BLACK);
      this.getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

      double width = this.getWidth() / this.board.getColumns();

      double height = this.getHeight() / this.board.getRows();

      for (int col = 0; col < this.board.getColumns(); col++)
      {
         for (int row = 0; row < this.board.getRows(); row++)
         {
            int block = this.board.getBoardSpace(row, col);

            if (block > 0)
            {
               this.getGraphicsContext2D().setFill(colors[block]);
               this.getGraphicsContext2D().fillRect((col * width) + 2,
                     (row * height) + 2, width - 2, height - 2);
            }
         }
      }
   }

   public void setBoard(SameGameBoard newBoard)
   {
      this.board = newBoard;
   }
}
