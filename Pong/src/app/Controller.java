package app;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Charles T.
 *
 */
public class Controller {

  @FXML
  Pane      board;

  @FXML
  Rectangle computer;

  @FXML
  Circle    ball;

  @FXML
  Label     score;

  Player    player;

  Game      game;


  public Controller() {
    
  }

  @FXML
  void initialize() {
    player = new Player(1, 150, 30, 150);
    game   = new Game(player, computer, ball);
    
    score.textProperty().bind(Bindings.convert(game.score));
    board.getChildren().add(player);
  }

  @FXML
  void run() {
    player.requestFocus();
    game.start();
  }

  @FXML
  void reset() {
    game.reset();
  }

}
