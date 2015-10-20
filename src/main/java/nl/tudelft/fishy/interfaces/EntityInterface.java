package nl.tudelft.fishy.interfaces;

import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.Sprite;

/**
 * An Entity represents an ingame creature (like a fish).
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public interface EntityInterface {

  /**
   * Check if two entities intersect.
   * 
   * @param other.
   * @return true if they intersect.
   */
  boolean intersects(Entity other);

  // --- Getters and Setters ---  

  /**
   * @return Sprite.
   */
  Sprite getSprite();

  /**
   * @param sprite - the new Sprite.
   */
  void setSprite(Sprite sprite);

  /**
   * @return movement speed.
   */
  int getMoveSpeed();

  /**
   * @param moveSpeed - the new movement speed.
   */
  void setMoveSpeed(int moveSpeed);
}
