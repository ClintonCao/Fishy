package main;

import interfaces.BoundingBoxInterface;

/**
 * This class represents a BoundingBox which consists of a location (x,y) in a 2-D plane,
 * and a width and height. BoundingBoxes are used for collisions between fish.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public class BoundingBox implements BoundingBoxInterface {
  private int xcoordinate;
  private int ycoordinate;
  private int width;
  private int height;

  /**
   * Contructor.
   */
  public BoundingBox(int xcoordinate, int ycoordinate, int width, int height) {
    this.setX(xcoordinate);
    this.setY(ycoordinate);
    this.setWidth(width);
    this.setHeight(height);
  }

  /**
   * Needs explanation. // STILL NEEDS EXPLANATION.
   * 
   * @param other - the bounding box that this bounding box will collide with
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
  public void updateX(int xcoordinate) {
    this.xcoordinate += xcoordinate;
  }

  /**
   * {@inheritDoc}.
   */
  public void updateY(int ycoordinate) {
    this.ycoordinate += ycoordinate;
  }

  /**
   * {@inheritDoc} If two BoundingBoxes have the same x and y position, have the
   * same width and the same height, they are equal.
   * 
   * @param other - the object that will be compared to.
   * @return true if equal.
   */
  public boolean equals(Object other) {
    if (!(other instanceof BoundingBox)) {
      return false;
    }

    if (this.xcoordinate != ((BoundingBox) other).getX()) {
      return false;
    }

    if (this.ycoordinate != ((BoundingBox) other).getY()) {
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
    return xcoordinate;
  }

  public void setX(int xcoordinate) {
    this.xcoordinate = xcoordinate;
  }

  public int getY() {
    return ycoordinate;
  }

  public void setY(int ycoordinate) {
    this.ycoordinate = ycoordinate;
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
