package app;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * @author Charles T.
 *
 */
public class Images {

  static final Image        IM_PLAYER       = new Image("file:resources/player.png");
  static final Image        IM_INVADER      = new Image("file:resources/invader.png");
  static final Image        IM_BEAM_PLAYER  = new Image("file:resources/beam_player.png");
  static final Image        IM_BEAM_INVADER = new Image("file:resources/beam_invader.png");
  static final Image        IM_WALL         = new Image("file:resources/wall.png");

  static final ImagePattern PLAYER          = new ImagePattern(IM_PLAYER);
  static final ImagePattern INVADER         = new ImagePattern(IM_INVADER);
  static final ImagePattern BEAM_PLAYER     = new ImagePattern(IM_BEAM_PLAYER);
  static final ImagePattern BEAM_INVADER    = new ImagePattern(IM_BEAM_INVADER);
  static final ImagePattern WALL            = new ImagePattern(IM_WALL);

  private Images() {

  }

}
