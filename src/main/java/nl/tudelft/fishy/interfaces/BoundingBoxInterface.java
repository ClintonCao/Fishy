package nl.tudelft.fishy.interfaces;

import nl.tudelft.fishy.BoundingBox;

/**
 * BoundingBoxes (axis-aligned) are used to calculate collisions between fish.
 * Each fish is represented in the 2-D plane of the screen with a BoundingBox.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public interface BoundingBoxInterface {

  /**
   * Checks if two bounding boxed intersects with each other.
   * 
   * @param other.
   * @return true if the boxes collide.
   */
  boolean intersects(BoundingBox other);

  /**
   * Update the x-coordinate of the bounding box. Used for moving bounding boxes
   * representing the fishes over the screen.
   *
   * @param xcoordinate - the new x-coordinate.
   */
  void updateX(int xcoordinate);

  /**
   * Update the y-coordinate of the bounding box. Used for moving bounding
   * boxes(Fish).
   * 
   * @param ycoordinate  - the new y-coordinate.
   */
  void updateY(int ycoordinate);

  /**
   * Used for testing.
   * 
   * @param other.
   * @return true if equal.
   */
  boolean equals(Object other);

  // --- Getters and setters ---

  /**
   * Get the x-coordinate of the box.
   * @return the x-coordinate of the bounding box.
   */
  int getX();

  /**
   * @param xcoordinate - the new x-coordinate of the bounding box.
   */
  void setX(int xcoordinate);

  /**
   * Get the y-coordinate of the box.
   * @return the y-coordinate of the bounding box.
   */
  int getY();

  /**
   * @param ycoordinate - the new y-coordinate of the bounding box.
   */
  void setY(int ycoordinate);

  /**
   * @return width of the bounding box.
   */
  int getWidth();

  /**
   * @param width - the new width.
   */
  void setWidth(int width);

  /**
   * @return height of the bounding box.
   */
  int getHeight();

  /**
   * @param height - the new height.
   */
  void setHeight(int height);
}
