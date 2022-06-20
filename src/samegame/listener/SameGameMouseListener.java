package samegame.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import samegame.Pane.SameGamePane;
import samegame.model.SameGameBoard;
import samegame.model.SameGameCanvas;

public class SameGameMouseListener implements EventHandler<MouseEvent>
{
   SameGameCanvas canvas;

   SameGameBoard board;
   
   SameGamePane pane;

   public SameGameMouseListener(SameGamePane pane)
   {
      this.pane = pane;
      this.board = pane.getBoard();
      this.canvas = pane.getCanvas();
   }

   public void setBoard(SameGameBoard newBoard)
   {
      this.board = newBoard;
   }
   
   @Override
   public void handle(MouseEvent event)
   {
      double width = canvas.getWidth() / board.getColumns();
      double height = canvas.getHeight() / board.getRows();
      int row = (int) (event.getY() / height);
      int column = (int) (event.getX() / width);
      board.deleteBlocks(row, column);
      canvas.renderCanvas();
      pane.updateLabel();
      if (board.isGameOver())
      {
         pane.endGame();
      }

   }

}
