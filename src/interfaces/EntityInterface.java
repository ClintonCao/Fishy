package interfaces;

import main.Entity;
import main.Sprite;

/**
 * An Entity represents an ingame creature (like a fish).
 * @author Michiel
 *
 */
public interface EntityInterface {

	  /**
	   * Check if two entities intersect.
	   * @param other.
	   * @return true if they intersect.
	   */
	  public boolean intersects(Entity other);

	  /**
	   * @return Sprite.
	   */
	  public Sprite getSprite();
	  
	  /**
	   * @param sprite - the new Sprite.
	   */
	  public void setSprite(Sprite sprite);
	  
	  /**
	   * @return movement speed.
	   */
	  public int getMoveSpeed();

	  /**
	   * @param moveSpeed - the new movement speed.
	   */
	  public void setMoveSpeed(int moveSpeed);
}
