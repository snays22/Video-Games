package app;

import static app.Constants.TILE_SIZE;

import java.util.List;
import java.util.stream.IntStream;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * @author Charles T.
 *
 */
public class Renderer {

  Canvas          canvas;
  GraphicsContext gc;

  int             dim;
  double          w;
  double          h;

  public Renderer(Canvas canvas, int dim, double w, double h) {
    this.canvas = canvas;
    this.gc     = canvas.getGraphicsContext2D();
    this.dim    = dim;
    this.w      = w;
    this.h      = h;
  }

  void drawGrid() {
    gc.clearRect(0, 0, 350, 700);
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, 350, 700);
    gc.setStroke(Color.WHITE);
    IntStream.range(1, dim).forEach(i -> gc.strokeLine(i * w / dim, 0, i * w / dim, h));
    IntStream.range(1, dim * 2).forEach(j -> gc.strokeLine(0, j * 0.5 * h / dim, w, j * 0.5 * h / dim));
  }

  void drawTetrominos(List<Tetromino> tetrominos) {
    tetrominos.forEach(t -> {
      gc.setFill(t.color);
      t.squares.forEach(s -> gc.fillRect(s.x * TILE_SIZE + 1, (s.y - 4) * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2));
    });
  }

  void gameOver() {
    gc.setFill(Color.RED);
    gc.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.fillText("GAME OVER", 150, 300);
  }

  void render(List<Tetromino> tetrominos) {
    drawGrid();
    drawTetrominos(tetrominos);
  }

}
