package nl.tudelft.fishy.interfaces;

import nl.tudelft.fishy.Sprite;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for the Lance item.
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public interface LifeInterface {


  /**
   * Update the x-coordinate of the Life Item. Used for moving Life Item over the screen.
   *
   * @param mod - the new x-coordinate.
   */
  void updateX(int mod);

  /**
   * Update the y-coordinate of the Life Item. Used for moving Life Item over the screen.
   *
   * @param mod - the new y-coordinate.
   */
  void updateY(int mod);

  /**
   * @param gc - GraphicsContext which will perform the rendering.
   */
  void render(GraphicsContext gc);


  // --- Getters and Setters ---

  /**
   * Get the x-coordinate.
   * @return the x-coordinate of the Life Item.
   */
  int getPosX();

  /**
   * @param posX - the new x-coordinate.
   */
  void setPosX(int posX);

  /**
   * Get the y-coordinate.
   * @return the y-coordinate of the Life Item.
   */
  int getPosY();

  /**
   * @param posY - the new y-coordinate.
   */
  void setPosY(int posY);


  /**
   * @param sprite - the new Sprite.
   */
  void setSprite(Sprite sprite);

  /**
   * Get the sprite.
   * @return the Sprite.
   */
  Sprite getSprite();
  
  
  /**
   * Check if it intersects with another sprite.
   * @param other the other sprite.
   * @return true if both sprite intersects with each other.
   */
  boolean intersect(Sprite other);

}
