package app;

import java.util.stream.IntStream;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * @author Charles T.
 *
 */
public class Controller {
  @FXML
  private Pane            board;
  @FXML
  private Label           score;

  private Game            game;
  private double          playerVelX;
  private AnimationTimer  timer;
  private BooleanProperty endGame;

  public Controller() {
    timer = new AnimationTimer() {

      @Override
      public void handle(long now) {
        handlePlayer();
        game.update();
        render();
      }
    };
  }

  @FXML
  public void init() {
    endGame = new SimpleBooleanProperty(false);
    game    = new Game();
    game.initPlayer();
    game.initInvaders(50, 50);
    game.initWalls(100, 500);
    board.getChildren().add(game.beamPlayer);
    board.getChildren().add(game.player);
    board.getChildren().addAll(game.invaders);
    board.getChildren().addAll(game.walls);
    score.textProperty().bind(Bindings.convert(game.score));
    endGame.bindBidirectional(game.over);
  }

  @FXML
  public void run() {
    endGame.addListener((obs, oldValue, newValue) -> {
      if (Boolean.TRUE.equals(newValue)) {
        timer.stop();
      }
    });
    board.requestFocus();
    timer.start();
  }

  @FXML
  public void reset() {
    timer.stop();
    board.getChildren().clear();
  }

  @FXML
  public void onKeyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case J:
        playerVelX = -7;
        handlePlayer();
        break;
      case L:
        playerVelX = 7;
        handlePlayer();
        break;
      case C:
        if (game.shootPlayer) {
          game.playerShoot();
        }
        break;
      default:
        break;
    }
  }

  @FXML
  public void onKeyReleased(KeyEvent e) {
    playerVelX = 0;
  }

  public void handlePlayer() {
    game.player.moveHorizontal(playerVelX);
  }

  public void render() {
    board.getChildren().removeIf(e -> {
      var entity = (Entity) e;
      return entity.isDead();
    });
    IntStream.range(game.prevSize, game.beamInvaders.size()).forEach(i -> board.getChildren().add(game.beamInvaders.get(i)));
  }
}
