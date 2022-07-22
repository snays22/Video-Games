package app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

/**
 * @author Charles T.
 *
 */
public class Tetromino {

  static final int SIZE = 3;

  Color            color;
  List<Square>     squares;
  int[]            newX;
  int[]            newY;
  boolean          alter;

  public Tetromino(Color color, Square... squares) {
    this.color   = color;
    this.squares = Arrays.asList(squares);
    this.newX    = new int[SIZE];
    this.newY    = new int[SIZE];
  }

  public Tetromino(Tetromino t) {
    this.color   = t.color;
    this.squares = t.squares.stream().map(Square::new).collect(Collectors.toList());
    this.newX    = t.newX;
    this.newY    = t.newY;
  }

  void move(int dx, int dy) {
    squares.forEach(s -> {
      s.x += dx;
      s.y += dy;
    });
  }

  void rotate() {
    var origin = squares.get(0);

    for (var i = 0; i < SIZE; i++) {
      var s = squares.get(i + 1);
      newX[i] = origin.x + origin.y - s.y;
      newY[i] = origin.y - origin.x + s.x;
    }
    alter = Arrays.stream(newX).allMatch(x -> x >= 0 && x < 10) && Arrays.stream(newY).allMatch(y -> y < 23);
    if (alter) {
      for (var i = 0; i < SIZE; i++) {
        squares.get(i + 1).x = newX[i];
        squares.get(i + 1).y = newY[i];
      }
    }
  }

}
