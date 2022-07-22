package app;

import static app.Constants.*;
import static app.EState.*;
import static app.EPlayer.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

/**
 * @author Charles T.
 *
 */
public class Game extends Task<Void> {

  StringProperty  winner;
  IntegerProperty pos;

  IPlay           computer;
  Board           board;
  Cell            cell;
  EPlayer         move;
  boolean         end;
  int             nbMoves;
  Random          rand;

  public Game(EPlayer firstPlayer, int mode) {
    winner   = new SimpleStringProperty();
    pos      = new SimpleIntegerProperty();
    computer = mode == 0 ? this::randomPlays : this::perfectPlays;
    cell     = new Cell(-1, E);
    board    = new Board();
    end      = false;
    move     = firstPlayer;
    nbMoves  = NB_MOVES;
    rand     = new Random();
  }

  @Override
  protected Void call() throws Exception {
    do {
      switch (move) {
        case HUMAN:
          humanPlays();
          break;
        case COMPUTER:
          computer.play();
          break;
        default:
          break;
      }
      nbMoves--;
    } while (!isEndGame());
    winner.set(move == COMPUTER ? "HUMAN" : "COMPUTER");
    return null;
  }

  public boolean isEndGame() {
    end = board.checkWin() || nbMoves == 0;

    return end;
  }

  public void humanPlays() {
    while (move == HUMAN) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void randomPlays() {
    List<Cell> validCells = Arrays.stream(board.grid).filter(c -> c.state == E).toList();

    cell       = validCells.get(rand.nextInt(validCells.size()));
    cell.state = O;
    pos.set(cell.num);
    board.update(cell.num, O);
    move = HUMAN;
  }

  public void perfectPlays() {

  }

}
