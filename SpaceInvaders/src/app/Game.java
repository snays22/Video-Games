package app;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Charles T.
 *
 */
public class Game {

  enum Direction {
    HORIZONTAL, VERTICAL
  }

  Entity          player;
  Entity          beamPlayer;
  List<Entity>    invaders;
  List<Entity>    walls;
  List<Entity>    beamInvaders;

  Direction       direction         = Direction.HORIZONTAL;
  double          leftLimit         = 50;
  double          virtualLeftLimit  = 50;
  double          rightLimit        = 925;
  double          virtualRightLimit = 925;
  double          deltaX            = 15;
  double          deltaY            = 15;

  int             nbInvaders        = 55;
  int             prevSize          = 0;
  Random          rand              = new Random();
  int             shootProbability  = 55;
  boolean         shootPlayer       = true;

  double          t                 = 0.0;
  double          timeMoveInvaders  = 1;

  IntegerProperty score             = new SimpleIntegerProperty(0);
  BooleanProperty over              = new SimpleBooleanProperty(false);

  public Game() {
    this.beamPlayer   = new Entity(-20, -20, 12, 20, Images.BEAM_PLAYER, EType.BEAM_PLAYER);
    this.invaders     = new LinkedList<>();
    this.walls        = new LinkedList<>();
    this.beamInvaders = new LinkedList<>();
  }

  public void update() {
    prevSize  = beamInvaders.size();
    t        += 0.016;
    if (t > timeMoveInvaders) {
      updateMoveInvaders();
      invadersShoot();
      updateSpeedInvaders();
      t = 0;
    }
    updateBeamsInvaders();
    updateBeamPlayer();
    collisions();
    endGame();
  }

  private void endGame() {
    if (beamInvaders.stream().filter(beam -> beam.getBoundsInParent().intersects(player.getBoundsInParent())).count() > 0) {
      player.setDead(true);
      over.setValue(Boolean.TRUE);
    }
  }

  private void collisions() {
    beamsInvadersCollisions();
    beamPlayerCollisions();
  }

  private void beamsInvadersCollisions() {
    beamInvaders.stream().forEach(beam -> {
      walls.stream().filter(wall -> beam.getBoundsInParent().intersects(wall.getBoundsInParent())).forEach(wall -> {
        wall.setDead(true);
        beam.setDead(true);
      });
    });
    walls.removeIf(Entity::isDead);
    beamInvaders.removeIf(Entity::isDead);
  }

  private void beamPlayerCollisions() {
    invaders.stream().filter(invader -> invader.getBoundsInParent().intersects(beamPlayer.getBoundsInParent()))
        .forEach(invader -> {
          invader.setDead(true);
          nbInvaders--;
          beamPlayer.setY(-20);
          score.set(score.get() + 20);
        });
    walls.stream().filter(wall -> wall.getBoundsInParent().intersects(beamPlayer.getBoundsInParent())).forEach(wall -> {
      wall.setDead(true);
      beamPlayer.setY(-20);
    });
    walls.removeIf(Entity::isDead);
    invaders.removeIf(Entity::isDead);
  }

  public void playerShoot() {
    beamPlayer.setX(player.getX() + 14);
    beamPlayer.setY(player.getY() - 20);
    shootPlayer = false;
  }

  private void updateBeamPlayer() {
    if (beamPlayer.getY() < 0) {
      beamPlayer.setY(0);
      beamPlayer.setX(-20);
      shootPlayer = true;
    } else if (beamPlayer.getX() > 0) {
      beamPlayer.setY(beamPlayer.getY() - 15);
    }
  }

  private void invadersShoot() {
    invaders.stream().forEach(invader -> {
      if (beamInvaders.size() < 5 && rand.nextInt(shootProbability) == 0) {
        Entity beam = new Entity(invader.getX() + 14, invader.getY() + 60, 15, 15, Images.BEAM_INVADER, EType.BEAM_INVADER);
        beamInvaders.add(beam);
      }
    });
  }

  private void updateBeamsInvaders() {
    beamInvaders.forEach(beam -> {
      if (beam.getY() > 700) {
        beam.setDead(true);
      } else {
        beam.setY(beam.getY() + 5);
      }
    });
    beamInvaders.removeIf(Entity::isDead);
  }

  private void updateMoveInvaders() {
    if (direction == Direction.HORIZONTAL) {
      invaders.forEach(invader -> invader.moveHorizontal(deltaX));
    } else {
      invaders.forEach(invader -> invader.moveVertical(deltaY));
    }
    deltaX    = (direction == Direction.VERTICAL) ? -deltaX : deltaX;
    direction = (direction == Direction.VERTICAL) ? Direction.HORIZONTAL : direction;
    setDirectionInvaders();
  }

  private void setDirectionInvaders() {
    invaders.forEach(invader -> {
      if (invader.getX() < leftLimit) {
        direction  = Direction.VERTICAL;
        leftLimit  = 0;
        rightLimit = virtualRightLimit;
        return;
      } else if (invader.getX() > rightLimit) {
        direction   = Direction.VERTICAL;
        rightLimit *= 1.2;
        leftLimit   = virtualLeftLimit;
        return;
      }
    });
  }

  private void updateSpeedInvaders() {
    timeMoveInvaders = (timeMoveInvaders > 0.016) ? nbInvaders * 0.01 : timeMoveInvaders;
  }

  public void initPlayer() {
    player = new Entity(480, 660, 40, 40, Images.PLAYER, EType.PLAYER);
  }

  public void initInvaders(int x, int y) {
    for (var i = 1; i <= 5; i++) {
      for (var j = 0; j <= 11; j++) {
        invaders.add(new Entity(x, y, 60, 60, Images.INVADER, EType.INVADER));
        x += 70;
      }
      x  = 50;
      y += 60;
    }
  }

  public void initWalls(int x, int y) {
    for (var i = 0; i < 4; i++) {
      for (var j = 0; j < 5; j++) {
        walls.add(new Entity(x, y, 40, 30, Images.WALL, EType.WALL));
        x += 40;
      }
      x  = 100;
      y += 30 - 1;
    }
    x = 400;
    y = 500;
    for (var i = 0; i < 4; i++) {
      for (var j = 0; j < 5; j++) {
        walls.add(new Entity(x, y, 40, 30, Images.WALL, EType.WALL));
        x += 40;
      }
      x  = 400;
      y += 30 - 1;
    }
    x = 700;
    y = 500;
    for (var i = 0; i < 4; i++) {
      for (var j = 0; j < 5; j++) {
        walls.add(new Entity(x, y, 40, 30, Images.WALL, EType.WALL));
        x += 40;
      }
      x  = 700;
      y += 30 - 1;
    }
  }

}
