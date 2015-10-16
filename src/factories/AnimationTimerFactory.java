package factories;

import interfaces.AnimationTimerFactoryInterface;
import main.FishBomb;
import main.Game;
import main.MainScreenController;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Makes Animation Timers. Singleton class.
 * 
 * @author Michiel
 */
public final class AnimationTimerFactory implements
    AnimationTimerFactoryInterface {

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
  public AnimationTimer makeAnimationTimer(GraphicsContext gc) {
    AnimationTimer timer = MainScreenController.makeAnimationTimer(gc);
    return timer;
  }
}
