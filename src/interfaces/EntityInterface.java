package interfaces;

import main.Entity;
import main.Sprite;

/**
 * An Entity represents an ingame creature (like a fish).
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public interface EntityInterface {

  /**
   * Check if two entities intersect.
   * 
   * @param other
   *          .
   * @return true if they intersect.
   */
  boolean intersects(Entity other);

  /**
   * Get the sprite.
   * 
   * @return Sprite.
   */
  Sprite getSprite();

  /**
   * Set the sprite.
   * 
   * @param sprite
   *          - the new Sprite.
   */
  void setSprite(Sprite sprite);

  /**
   * Get the move speed.
   * 
   * @return movement speed.
   */
  int getMoveSpeed();

  /**
   * Set the move speed.
   * 
   * @param moveSpeed
   *          - the new movement speed.
   */
  void setMoveSpeed(int moveSpeed);
}
