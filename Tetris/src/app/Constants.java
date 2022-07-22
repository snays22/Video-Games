package app;

import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * @author Charles T.
 *
 */
public class Constants {

  static final List<Tetromino> TETROMINOS =
      Arrays.asList(new Tetromino(Color.YELLOW, new Square(4, 2), new Square(5, 2), new Square(4, 3), new Square(5, 3)),
          new Tetromino(Color.CYAN, new Square(4, 2), new Square(4, 0), new Square(4, 1), new Square(4, 3)),
          new Tetromino(Color.VIOLET, new Square(4, 2), new Square(3, 2), new Square(5, 2), new Square(4, 3)),
          new Tetromino(Color.ORANGE, new Square(4, 1), new Square(5, 1), new Square(4, 2), new Square(4, 3)),
          new Tetromino(Color.BLUE, new Square(4, 1), new Square(3, 1), new Square(4, 2), new Square(4, 3)),
          new Tetromino(Color.RED, new Square(4, 2), new Square(3, 2), new Square(4, 3), new Square(5, 3)),
          new Tetromino(Color.LAWNGREEN, new Square(4, 2), new Square(5, 2), new Square(4, 3), new Square(3, 3)));

  public static final double   TILE_SIZE  = 35.0;

  private Constants() {

  }

}
