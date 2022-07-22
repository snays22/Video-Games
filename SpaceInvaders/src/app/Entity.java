package app;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * @author Charles T.
 *
 */
public class Entity extends Rectangle {

  private EType   type;
  private boolean dead;

  public Entity(double x, double y, double w, double h, ImagePattern img, EType type) {
    super(x, y, w, h);
    this.type = type;
    this.dead = false;
    this.setFill(img);
  }

  public void moveHorizontal(double dX) {
    setX(getX() + dX);
  }

  public void moveVertical(double dY) {
    setY(getY() + dY);
  }

  public EType getType() {
    return type;
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

}
