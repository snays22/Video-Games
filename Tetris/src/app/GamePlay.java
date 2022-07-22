package app;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Charles T.
 *
 */
public class GamePlay {

  Game       game;

  public GamePlay(Game game) {
    this.game     = game;
  }

  void getInputs(KeyEvent e) {
    if (e.getCode() == KeyCode.M && game.collideSides("M", x -> x < 9)) {
      game.curTetro.move(1, 0);
    } else if (e.getCode() == KeyCode.K && game.collideSides("K", x -> x > 0)) {
      game.curTetro.move(-1, 0);
    } else if (e.getCode() == KeyCode.C) {
      game.curTetro.rotate();
    } else if (e.getCode() == KeyCode.SPACE) {
      game.dropTetromino();
    }
  }

}
