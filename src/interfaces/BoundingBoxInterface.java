package interfaces;

import main.BoundingBox;

/**
 * BoundingBoxes (axis-aligned) are used to calculate collisions between fish.
 * Each fish is represented in the 2-D plane of the screen with a BoundingBox.
 * 
 * @author Michiel
 */
public interface BoundingBoxInterface {

	  /**
	   * Checks if two bounding boxed intersects with each other.
	   * 
	   * @param other.
	   * @return true if the boxes collide.
	   */
	  public boolean intersects(BoundingBox other);
	  
	  /**
	   * Update the x-coordinate of the bounding box. Used for moving bounding
	   * boxes representing the fishes over the screen.
	   *
	   * @param x - the new x-coordinate.
	   */
	  public void updateX(int x);

	  /**
	   * Update the y-coordinate of the bounding box. Used for moving bounding
	   * boxes(Fish).
	   * 
	   * @param y - the new y-coordinate.
	   */
	  public void updateY(int y);

	  /**
	   * Used for testing.
	   * 
	   * @param other.
	   * @return true if equal.
	   */
	  public boolean equals(Object other);
	  
// --- Getters and setters ---

	  /**
	   * @return the x-coordinate of the bounding box.
	   */
	  public int getX();

	  /**
	   * @param x - the new x-coordinate of the bounding box.
	   */
	  public void setX(int x);

	  /**
	   * @return the y-coordinate of the bounding box.
	   */
	  public int getY();

	  /**
	   * @param y - the new y-coordinate of the bounding box.
	   */
	  public void setY(int y);

	  /**
	   * @return width of the bounding box.
	   */
	  public int getWidth();
	  
	  /**
	   * @param width - the new width.
	   */
	  public void setWidth(int width);
	  
	  /**
	   * @return height of the bounding box.
	   */
	  public int getHeight();

	  /**
	   * @param height - the new height.
	   */
	  public void setHeight(int height);
}
