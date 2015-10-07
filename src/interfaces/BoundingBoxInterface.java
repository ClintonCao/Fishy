package interfaces;

import main.BoundingBox;

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
   * @param other
   *          .
   * @return true if the boxes collide.
   */
  boolean intersects(BoundingBox other);

  /**
   * Update the x-coordinate of the bounding box. Used for moving bounding boxes
   * representing the fishes over the screen.
   *
   * @param x
   *          - the new x-coordinate.
   */
  void updateX(int x);

  /**
   * Update the y-coordinate of the bounding box. Used for moving bounding
   * boxes(Fish).
   * 
   * @param y
   *          - the new y-coordinate.
   */
  void updateY(int y);

  /**
   * Used for testing.
   * 
   * @param other
   *          .
   * @return true if equal.
   */
  boolean equals(Object other);

  // --- Getters and setters ---

  /**
   * Get the x-coordinate of the bounding box.
   * 
   * @return the x-coordinate of the bounding box.
   */
  int getX();

  /**
   * Set the y-coordinate of the bounding box.
   * 
   * @param x
   *          - the new x-coordinate of the bounding box.
   */
  void setX(int x);

  /**
   * Get the y-coordinate of the bounding box.
   * 
   * @return the y-coordinate of the bounding box.
   */
  int getY();

  /**
   * Set the y-coordinate of the bounding box.
   * 
   * @param y
   *          - the new y-coordinate of the bounding box.
   */
  void setY(int y);

  /**
   * Get the width of the bounding box.
   * 
   * @return width of the bounding box.
   */
  int getWidth();

  /**
   * Set the width of the bounding box.
   * 
   * @param width
   *          - the new width.
   */
  void setWidth(int width);

  /**
   * Get the height of the bounding box.
   * 
   * @return height of the bounding box.
   */
  int getHeight();

  /**
   * Set the height of the bounding box.
   * 
   * @param height
   *          - the new height.
   */
  void setHeight(int height);
}
