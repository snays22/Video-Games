package app;

import java.util.function.Predicate;

/**
 * @author Charles T.
 *
 */
public enum EType implements Predicate<EType> {

  NONE, PLAYER, BEAM_PLAYER, INVADER, BEAM_INVADER, WALL;

  @Override
  public boolean test(EType t) {
    return this == t;
  }

}
