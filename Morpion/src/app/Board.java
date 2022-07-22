package app;

import static app.Constants.*;
import static app.EState.*;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Charles T.
 *
 */
public class Board {

  Cell[] grid;
  int[]  rows;
  int[]  cols;
  int    lDiag;
  int    rDiag;

  public Board() {
    grid  = new Cell[NB_CELLS];
    rows  = new int[NB_CASES];
    cols  = new int[NB_CASES];
    lDiag = 0;
    rDiag = 0;
    Stream.iterate(0, n -> n + 1).limit(NB_CELLS).forEach(i -> grid[i] = new Cell(i, E));
  }

  public void update(int num, EState state) {
    int c = num % 3;
    int r = num / 3;

    rows[r] += state.value;
    cols[c] += state.value;
    lDiag   += (r == c) ? state.value : 0;
    rDiag   += (r + c == NB_CASES - 1) ? state.value : 0;
  }

  public boolean checkWin() {
    return (Arrays.stream(rows).filter(v -> Math.abs(v) == NB_CASES).count() == 1)
        || (Arrays.stream(cols).filter(v -> Math.abs(v) == NB_CASES).count() == 1) || (Math.abs(lDiag) == NB_CASES)
        || (Math.abs(rDiag) == NB_CASES);
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    var k  = 0;

    for (var i = 0; i < 3; i++) {
      sb.append("|");
      for (var j = 0; j < 3; j++) {
        sb.append(grid[k++].state + "|");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
