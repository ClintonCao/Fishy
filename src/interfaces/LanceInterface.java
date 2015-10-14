package interfaces;

import main.BoundingBox;
import main.Lance;
import main.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface LanceInterface {


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


  // --- Getters and Setters ---

  /**
   * Get the x-coordinate.
   * @return the x-coordinate of the bomb.
   */
  int getPosX();

  /**
   * @param posX - the new x-coordinate.
   */
  void setPosX(int posX);

  /**
   * Get the y-coordinate.
   * @return the y-coordinate of the bomb.
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
   * Set the image of the lance.
   * @param img the image of the lance.
   */
  void setImage(Image img);
  
  /**
   * Get the image of the lance.
   * @return the image of the lance.
   */
  Image getImage();
  
  /**
   * Check if two lance are the same.
   * @param other the other objec that will be compared with this one.
   * @return true if both are equal.
   */
  boolean equals(Lance other);
  
  /**
   * Check if it intersects with another sprite.
   * @param other the other sprite.
   * @return true if both sprite intersects with each other.
   */
  boolean intersect(Sprite other);

}
