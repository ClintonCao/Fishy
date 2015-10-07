package main;

import interfaces.BoundingBoxInterface;

/**
 * This class represents the bounding box of the sprites.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public class BoundingBox implements BoundingBoxInterface {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Contructor of BoundinBox.
   * 
   * @param x
   *          the x-coordinate.
   * @param y
   *          the y-coordinate.
   * @param width
   *          the width of the box.
   * @param height
   *          the width of the box.
   */
  public BoundingBox(int x, int y, int width, int height) {
    this.setX(x);
    this.setY(y);
    this.setWidth(width);
    this.setHeight(height);
  }

  /**
   * Needs explanation. // STILL NEEDS EXPLANATION.
   * 
   * @param other
   *          the bounding box that this bounding box will collide with
   * @return true if they collide with each other.
   */
  public boolean intersects(BoundingBox other) {
    return !((this.getX() + this.getWidth()) < other.getX()
        || this.getX() > (other.getX() + other.getWidth())
        || (this.getY() + this.getHeight()) < other.getY() || this.getY() > (other
        .getY() + other.getHeight()));
  }

  /**
   * {@inheritDoc}.
   */
  public void updateX(int x) {
    this.x += x;
  }

  /**
   * {@inheritDoc}.
   */
  public void updateY(int y) {
    this.y += y;
  }

  /**
   * {@inheritDoc} If two BoundingBoxes have the same x and y position, have the
   * same width and the same height, they are equal.
   * 
   * @param other
   *          the object that will be compared to. .
   * @return true if equal.
   */
  public boolean equals(Object other) {
    if (!(other instanceof BoundingBox)) {
      return false;
    }

    if (this.x != ((BoundingBox) other).getX()) {
      return false;
    }

    if (this.y != ((BoundingBox) other).getY()) {
      return false;
    }

    if (this.width != ((BoundingBox) other).getWidth()) {
      return false;
    }

    if (this.height != ((BoundingBox) other).getHeight()) {
      return false;
    }

    return true;
  }

  // -----------Getters and setters-------------------
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
