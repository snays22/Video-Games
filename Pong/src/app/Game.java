package app;

import static app.Constants.*;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Charles T.
 * 
 */
public class Game extends AnimationTimer {

  IntegerProperty score = new SimpleIntegerProperty(0);

  Player          player;
  Rectangle       computer;
  Circle          ball;

  double          angle = atan2(INITIAL_VY, INITIAL_VX);
  double          mag   = sqrt(INITIAL_MAG);
  double          dX    = mag * cos(angle);
  double          dY    = mag * sin(angle);


  public Game(Player player, Rectangle computer, Circle ball) {
    this.player   = player;
    this.computer = computer;
    this.ball     = ball;
  }

  @Override
  public void handle(long now) {
    handlePlayer();
    update();
    endGame();
  }

  void endGame() {
    if (ball.getCenterX() < 15) {
      stop();
    }
  }

  void handlePlayer() {
    player.setY((player.getY() >= 10 && player.getY() <= 600) ? player.getY() + player.velocity : player.getY());
  }

  void reset() {
    stop();
    score.set(0);
    ball.setCenterX(450);
    ball.setCenterY(350);
    player.setY(150);
    computer.setY(450);
    angle = atan2(INITIAL_VY, INITIAL_VX);
    mag   = sqrt(INITIAL_MAG);
    dX    = mag * cos(angle);
    dY    = mag * sin(angle);
  }

  void update() {
    ball.setCenterX(ball.getCenterX() + dX);
    ball.setCenterY(ball.getCenterY() + dY);
    computer.setY(ball.getCenterY() - 75);

    if (player.getBoundsInParent().intersects(ball.getBoundsInParent())) {
      mag   *= (mag < SPEED_LIMIT) ? ACCELERATION : 1;
      angle  = (PI / 4.0) * abs((player.getY() + 75 - ball.getCenterY() - 15) / 75);
      dX     = abs(mag * cos(angle));
      dY     = (ball.getCenterY() <= player.getY() + 75) ? -abs(mag * sin(angle)) : abs(mag * sin(angle));
      score.set(score.get() + 1);
    } else if (computer.getBoundsInParent().intersects(ball.getBoundsInParent())) {
      dX = -dX;
    } else if (ball.getCenterY() > BOTTOM_LIMIT) {
      dY = -dY;
      ball.setCenterY(719);
    } else if (ball.getCenterY() < UP_LIMIT) {
      dY = -dY;
      ball.setCenterY(16);
    }
  }

}
