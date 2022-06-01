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


   private GraphicsContext context;
   
   public SameGamePane()
   {
      SameGameBoard board = new SameGameBoard();
      
      updateLabel();
      
      canvas = new Canvas(Constants.SCREEN_DIMENSION[0],Constants.SCREEN_DIMENSION[1]);
      
      
      setCenter(canvas);
      setBottom(blockCount);
      
      context = canvas.getGraphicsContext2D();
      render();
   }

   private void render()
   {
      context.setFill(Color.BLUE);
      context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
      
   }

   private void updateLabel()
   {
      // TODO Auto-generated method stub
      
   }
}
