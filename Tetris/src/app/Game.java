package app;

import static app.Constants.TETROMINOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Charles T.
 *
 */
public class Game {

  BooleanProperty end        = new SimpleBooleanProperty(false);
  IntegerProperty level      = new SimpleIntegerProperty(1);
  IntegerProperty score      = new SimpleIntegerProperty(0);

  int[][]         board      = new int[24][10];
  Random          rand       = new Random();
  List<Tetromino> tetrominos = new ArrayList<>();
  Tetromino       curTetro   = new Tetromino(TETROMINOS.get(rand.nextInt(7)));
  Tetromino       newTetro;

  double          time;
  double          limit;

  public Game() {
    tetrominos.add(curTetro);
    limit = 1.0;
  }

  void endGame() {
    end.setValue(Arrays.stream(board[3]).anyMatch(e -> e == 1));
  }

  void update() {
    if (collideBottom(curTetro.squares)) {
      curTetro.squares.forEach(s -> s.y++);
    } else {
      curTetro.squares.forEach(s -> board[s.y][s.x] = 1);
      curTetro = new Tetromino(TETROMINOS.get(rand.nextInt(7)));
      int nbRows = rowsFilled();
      updateScore(nbRows);
      updateLevel();
      tetrominos.add(curTetro);
    }
  }

  void updateLevel() {
    level.set(1 + (score.get() / 2000));
    time = (1 / Math.pow(2, level.get() - 1.0));
  }

  void updateScore(int nbLines) {
    score.set(score.get() + level.get() * 100 * nbLines);
  }

  int rowsFilled() {
    var nbLines = 0;
    var i       = 23;

    while (i > 0) {
      if (Arrays.stream(board[i]).filter(e -> e == 1).count() == 10) {
        nbLines++;
        resetGrid();
        var lim = i;
        tetrominos.forEach(t -> t.squares.removeIf(s -> s.y == lim));
        tetrominos.removeIf(t -> t.squares.isEmpty());
        tetrominos.forEach(t -> t.squares.forEach(s -> s.y += (s.y < lim) ? 1 : 0));
        tetrominos.forEach(t -> t.squares.forEach(s -> board[s.y][s.x] = 1));
      } else {
        i--;
      }
    }
    return nbLines;
  }

  boolean collideBottom(List<Square> squares) {
    return squares.stream().allMatch(s -> s.y < 23 && board[s.y + 1][s.x] == 0);
  }

  boolean collideSides(String keyCode, IntPredicate p) {
    return switch (keyCode) {
      case "M" -> curTetro.squares.stream().allMatch(s -> p.test(s.x) && board[s.y][s.x + 1] == 0);
      case "K" -> curTetro.squares.stream().allMatch(s -> p.test(s.x) && board[s.y][s.x - 1] == 0);
      default -> false;
    };
  }

  void dropTetromino() {
    while (collideBottom(curTetro.squares)) {
      curTetro.move(0, 1);
    }
  }

  void resetGrid() {
    for (int[] line : board) {
      Arrays.fill(line, 0);
    }
  }

}
