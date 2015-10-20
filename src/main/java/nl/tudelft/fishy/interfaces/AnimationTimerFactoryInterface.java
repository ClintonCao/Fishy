package nl.tudelft.fishy.interfaces;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * An AnimationTimerFactory is a factory to create AnimationTimers.
 * 
 * @author Michiel
 */
public interface AnimationTimerFactoryInterface {

  /**
   * For now, just one kind of AnimationTimer is made.
   * 
   * @param gc
   *          - GraphicsContext needed for rendering.
   * @return new AnimationTimer.
   */
  AnimationTimer makeAnimationTimer(GraphicsContext gc);
}
