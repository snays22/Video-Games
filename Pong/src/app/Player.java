package app;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * @author Charles T.
 *
 */
public class Player extends Rectangle {

  double velocity;

  public Player(double x, double y, double w, double h) {
    super(x, y, w, h);
    setFill(Color.WHITE);
    setOnKeyPressed(this::onKeyPressed);
    setOnKeyReleased(this::onKeyReleased);
  }

  void onKeyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case J:
        if (getY() >= 10) {
          velocity = -10;
          setY(getY() + velocity);
        }
        break;
      case N:
        if (getY() <= 600) {
          velocity = 10;
          setY(getY() + velocity);
        }
        break;
      default:
        break;
    }
  }

  void onKeyReleased(KeyEvent e) {
    velocity = 0;
  }

}
