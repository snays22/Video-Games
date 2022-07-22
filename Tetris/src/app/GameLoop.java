package app;

import javafx.animation.AnimationTimer;

/**
 * @author Charles T.
 *
 */
public class GameLoop extends AnimationTimer {

  Renderer renderer;
  GamePlay gamePlay;
  Game     game;

  double   time;
  double   limit;

  public GameLoop(Renderer renderer, GamePlay gamePlay, Game game) {
    this.renderer = renderer;
    this.gamePlay = gamePlay;
    this.game     = game;
    this.time     = 0;
    this.limit    = 1;
  }

  @Override
  public void handle(long now) {
    time += 0.017;
    if (time >= limit) {
      game.update();
      game.endGame();
      time = 0.0;
    }
    renderer.render(game.tetrominos);
  }

}
