package app;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @author Charles T.
 *
 */
public class Controller {

  @FXML
  GridPane        board;
  @FXML
  Text            score;
  @FXML
  Text            level;
  @FXML
  CheckBox        aiMode;
  @FXML
  Canvas          canvas;

  GraphicsContext gc;

  Renderer        renderer;
  Game            game;
  GameLoop        gameLoop;
  GamePlay        gamePlay;

  public Controller() {
    //
  }

  @FXML
  void initialize() {
    canvas.widthProperty().bind(board.widthProperty());
    canvas.heightProperty().bind(board.heightProperty());
  }

  @FXML
  void init() {
    
    game     = new Game();
    renderer = new Renderer(canvas, 10, 350, 700);
    gamePlay = new GamePlay(game);
    gameLoop = new GameLoop(renderer, gamePlay, game);

    game.end.addListener((obs, old, newv) -> renderer.gameOver());
    canvas.setOnKeyPressed(gamePlay::getInputs);
    renderer.drawGrid();

    level.textProperty().bind(game.level.asString());
    score.textProperty().bind(Bindings.convert(game.score));
  }

  @FXML
  void run() {
    canvas.requestFocus();
    gameLoop.start();
  }

  @FXML
  void reset() {
    gameLoop.stop();
  }

}
