package interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.BoundingBox;
import main.Sprite;

/**
 * A FishBomb can be made to explode. EnemyFish within the explosion radius
 * will die.
 * 
 * @author Michiel
 */
public interface FishBombInterface {

  /**
   * Update the x-coordinate of the FishBomb. Used for moving FishBombs over the screen.
   *
   * @param mod - the new x-coordinate.
   */
  void updateX(int mod);

  /**
   * Update the y-coordinate of the FishBomb. Used for moving FishBombs over the screen.
   *
   * @param mod - the new y-coordinate.
   */
  void updateY(int mod);

  /**
   * @param gc - GraphicsContext which will perform the rendering.
   */
  void render(GraphicsContext gc);

  /**
   * Check if the explosion radius of the FishBomb intersects a BoundingBox.
   * @param bb.
   * @return true if intersecting.
   */
  boolean intersectsRectangle(BoundingBox bb);

  // --- Getters and Setters ---

  /**
   * Get the y-coordinate of the bomb.
   * 
   * @return the x-coordinate of the bomb.
   */
  int getPosX();

  /**
   * Set the x-coordinate of the bomb.
   * 
   * @param posX - the new x-coordinate.
   */
  void setPosX(int posX);

  /**
   * Get the y-coordinate of the bomb.
   * 
   * @return the y-coordinate of the bomb.
   */
  int getPosY();

  /**
   * @param posY - the new y-coordinate.
   */
  void setPosY(int posY);

  /**
   * Get the image of the explosion.
   * 
   * @return the Image for the explosion.
   */
  Image getExplosionImg();

  /**
   * @param explosionImg - the new Image for the explosion.
   */
  void setExplosionImg(Image explosionImg);

  /**
   * @param sprite - the new Sprite.
   */
  void setSprite(Sprite sprite);

  /**
   * Get the sprite of the bomb.
   * 
   * @return the Sprite.
   */
  Sprite getSprite();

  /**
   * Get the radius of the bomb.
   * 
   * @return the radius.
   */
  int getRadius();

  /**
   * @param radius - the new radius.
   */
  void setRadius(int radius);
}
