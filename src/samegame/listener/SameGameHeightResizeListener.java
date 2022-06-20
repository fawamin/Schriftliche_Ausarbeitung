package samegame.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import samegame.model.SameGameCanvas;

public class SameGameHeightResizeListener implements ChangeListener<Number>
{
   SameGameCanvas canvas;

   public SameGameHeightResizeListener(SameGameCanvas canvas)
   {
      this.canvas = canvas;
      if(this.canvas == null)
      {
         throw new IllegalArgumentException("SameGame Canvas bei Height Listener ist null!");
      }
   }

   @Override
   public void changed(ObservableValue<? extends Number> value, Number old,
         Number newValue)
   {
      try
      {
         canvas.setHeight(newValue.intValue() - 20);
         canvas.renderCanvas();
      }
      catch (Exception e)
      {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("ERROR");
         alert.setHeaderText(
               "Beim Rezisen der Höhe des Boardes ist ein fehler aufgetreten!");
         alert.showAndWait();
      }

   }
}
