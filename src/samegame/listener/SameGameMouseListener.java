package samegame.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import samegame.Pane.SameGamePane;
import samegame.model.SameGameBoard;
import samegame.model.SameGameCanvas;

/**
 * 
 * Listener that Handles mouse Clicks on the Game
 *
 */
public class SameGameMouseListener implements EventHandler<MouseEvent>
{
   // Class Variables
   SameGameCanvas canvas;

   SameGameBoard board;

   SameGamePane pane;

   /**
    * 
    * Constructor that sets the Class variables
    *
    * @param pane
    *           The SameGamepane for which the Handler works
    */
   public SameGameMouseListener(SameGamePane sameGamePane)
   {
      if (sameGamePane == null)
      {
         throw new IllegalArgumentException(
               "SamegamePane im  SameGameMouseListener Konstruktor ist null!");
      }
      this.pane = sameGamePane;
      this.board = this.pane.getBoard();
      this.canvas = this.pane.getCanvas();
   }

   /**
    * 
    * Setter to update the Board
    *
    * @param newBoard
    *           new Board to replace the old one
    */
   public void setBoard(SameGameBoard newBoard)
   {
      if (newBoard == null)
      {
         throw new IllegalArgumentException(
               "newBoard im SameGameMouseListener Board setter ist null!");
      }
      this.board = newBoard;
   }

   @Override
   /**
    * Handle Method that calculates which Block was clicked, tells the board
    * to delete it and checks wether the game is over
    */
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
