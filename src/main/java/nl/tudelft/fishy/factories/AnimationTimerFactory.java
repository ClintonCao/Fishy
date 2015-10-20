package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.BoundingBox;
import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.EndBoss;
import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.GameLoop;
import nl.tudelft.fishy.Lance;
import nl.tudelft.fishy.PlayerFish;
import nl.tudelft.fishy.Sprite;
import nl.tudelft.fishy.interfaces.AnimationTimerFactoryInterface;
import nl.tudelft.fishy.controllers.MainScreenController;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Makes Animation Timers. Singleton class.
 * 
 * @author Michiel
 */
public final class AnimationTimerFactory {

  private static AnimationTimerFactory animationTimerFactory = null;

  /**
   * Constructor.
   */
  private AnimationTimerFactory() {

  }

  /**
   * Synchronized getters.
   * 
   * @return the Singleton AnimationTimerFactory.
   */
  public static synchronized AnimationTimerFactory getAnimationTimerFactory() {

    if (animationTimerFactory == null) {
      animationTimerFactory = new AnimationTimerFactory();
    }
    return animationTimerFactory;
  }

/**
   * {@inheritDoc} Overrides the Handle method in AnimationTimer to contain the
   * game loop.
   * 
   * @return the new AnimationTimer.
   */
  public AnimationTimer makeAnimationTimer(GraphicsContext gc, CompositeEnemyFish compositeEnemyFish) {

      return new AnimationTimer() {
        public void handle(long currentNTime) {

          GameLoop gameLoop = MainScreenController.getGameLoop();          
          
          gameLoop.turnOnBossMode();
          
          gameLoop.playerWins();
          
          gameLoop.playerDiesToBoss();

          MainScreenController.renderStatics(gc);

          gameLoop.handleBoss(gc);

          gameLoop.handleWeapon(gc);

          gameLoop.handlePlayerInput(gc);

          gameLoop.generateEnemyFish();

          gameLoop.playerPicksUpLance();
          
          compositeEnemyFish.removeOffScreenEnemyFish(GameLoop.screenbox);
          
          gameLoop.playerIntersectsFish();     
          
          gameLoop.renderNonStatics(gc);
          
          gameLoop.updateFrames();
        }
      };

    }
}
