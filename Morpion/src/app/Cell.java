package app;

/**
 * @author Charles T.
 *
 */
public class Cell {

  int    num;
  EState state;

  public Cell(int num, EState state) {
    this.num   = num;
    this.state = state;
  }

  @Override
  public String toString() {
    return "[" + num + ", " + state + "] ";
  }

}
