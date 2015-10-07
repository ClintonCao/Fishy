package main;

import interfaces.SpriteInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A sprite represents the model of an entity on the screen. It consists of an Image
 * which will be rendered the screen, and a BoundingBox to represent it in a 2-D plane.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public class Sprite implements SpriteInterface {
  private Image img;
  private BoundingBox boundingBox;

  /**
   * Constructor.
   */
  public Sprite(Image img, BoundingBox boundingBox) {
    this.img = img;
    setBoundingBox(boundingBox);
  }

  /**
   * {@inheritDoc} Uses update method in BoundingBox.
   * @see BoundingBox#updateX(int)
   */
  public void updateX(int x) {
    boundingBox.updateX(x);
  }

  /**
   * {@inheritDoc} Uses update method in BoundingBox.
   * @see BoundingBox#updateY(int)
   */
  public void updateY(int y) {
    boundingBox.updateY(y);
  }

  /**
   * {@inheritDoc} Draws the Sprite's Image on the screen,
   * at the position of the Sprite's BoundingBox's x and y-coordinates.
   */
  public void render(GraphicsContext gc) {
    gc.drawImage(img, boundingBox.getX(), boundingBox.getY());
  }

  /**
   * {@inheritDoc} Two Sprites intersect if the BoundingBoxes intersect.
   * @see BoundingBox#intersects(BoundingBox)
   */
  public boolean intersects(Sprite s) {
    return s.getBoundingBox().intersects(this.getBoundingBox());
  }
  
  /**
   * {@inheritDoc} Two Sprites are equal if the Images and
   * BoundingBoxes are equal.
   * @see BoundingBox#equals(Object)
   * @see Image#equals(Object)
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

  // --- Getters and Setters ---

  public Image getImg() {
    return this.img;
  }

  public void setImg(Image img) {
    this.img = img;
  }

  public BoundingBox getBoundingBox() {
    return boundingBox;
  }
  
  public void setBoundingBox(BoundingBox boundingBox) {
    this.boundingBox = boundingBox;
  }
}
