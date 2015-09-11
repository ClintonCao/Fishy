package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A sprite represents the model of an entity on the screen. It has a position
 * in X and Y coordinates, a certain velocity in a direction expressed by X and
 * Y coordinates, A width and a height, and an image to draw on the screen.
 * 
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel
 *         Doesburg.
 */
public class Sprite {
  private Image img;
  private BoundingBox boundingBox;

  /**
   * Constructor of the class.
   * 
   * @param img
   *          image for the sprite.
   * @param boundingBox
   *          boundingbox of the sprite.
   */
  public Sprite(Image img, BoundingBox boundingBox) {
    this.img = img;
    setBoundingBox(boundingBox);
  }

  /**
   * Update the x coordinate of the sprite.
   * 
   * @param x
   *          integer, x coordinate
   */
  public void updateX(int x) {
    boundingBox.updateX(x);
  }

  /**
   * Update the y coordinate of the sprite.
   * 
   * @param y
   *          integer, y coordinate
   */
  public void updateY(int y) {
    boundingBox.updateY(y);
  }

  /**
   * Renders the image.
   * 
   * @param gc
   *          GraphicsContext
   */
  public void render(GraphicsContext gc) {
    gc.drawImage(img, boundingBox.getX(), boundingBox.getY());
  }

  /**
   * Test whether this sprite intersects with the other sprite.
   * 
   * @param s
   *          the other Sprite
   * @return a boolean whether this sprite intersects with other sprite
   */
  public boolean intersects(Sprite s) {
    return s.getBoundingBox().intersects(this.getBoundingBox());
  }

  // ------------Getters and
  // setters-------------------------------------------

  /**
   * Get the image of the sprite.
   * 
   * @return the image of the sprite.
   */
  public Image getImg() {
    return this.img;
  }

  /**
   * Set the image of the sprite.
   * 
   * @param img
   *          the desired image for the sprite.
   */
  public void setImg(Image img) {
    this.img = img;
  }

  /**
   * Get the bounding box of the sprite.
   * 
   * @return the bounding box of the sprite.
   */
  public BoundingBox getBoundingBox() {
    return boundingBox;
  }

  /**
   * Set the bounding box of the sprite.
   * 
   * @param boundingBox
   *          the desired bounding box for the sprite.
   */
  public void setBoundingBox(BoundingBox boundingBox) {
    this.boundingBox = boundingBox;
  }

  /**
   * Equals method of this class.
   * 
   * @param other
   *          the other object to check whether the equal.
   * @return boolean whether the two objects are equal or not.
   */
  public boolean equals(Object other) {
    if (!(other instanceof Sprite)) {
      return false;
    }

    if (!this.img.equals(((Sprite) other).getImg())) {
      return false;
    }

    if (!this.boundingBox.equals(((Sprite) other).getBoundingBox())) {
      return false;
    }

    return true;
  }

}
