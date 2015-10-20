package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.GameLoop;
import nl.tudelft.fishy.controllers.MainScreenController;
import javafx.animation.AnimationTimer;

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
  public AnimationTimer makeAnimationTimer(CompositeEnemyFish compositeEnemyFish) {

      return new AnimationTimer() {
        public void handle(long currentNTime) {

          GameLoop gameLoop = MainScreenController.getGameLoop();          
          
          gameLoop.runGameLoop();
         
        }
      };

    }
}
