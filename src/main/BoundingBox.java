package main;

/**
 * An Axis-Aligned Bounding Box is a rectangle aligned with the X and Y axis,
 * i.e. no rotation. The BoundingBox is used as a hitbox for collision detection
 * between fish.
 * 
 * @author Michiel
 *
 */
public class BoundingBox {
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * The constructor of BoundingBox.
	 * 
	 * @param x
	 *            is the x-coordinate of the box on the screen.
	 * @param y
	 *            is the y-coordinate of the box on the screen.
	 * @param width
	 *            is the width of the box.
	 * @param height
	 *            is the height of the box.
	 */
	public BoundingBox(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Checks if two bounding boxed intersects with each other.
	 * 
	 * @param other
	 *            is the bounding box that this boundingbox is colliding with.
	 * @return a boolean indicating if the two boxes intersects with each other.
	 */
	public boolean intersects(BoundingBox other) {
		return !((this.getX() + this.getWidth()) < other.getX()
				|| this.getX() > (other.getX() + other.getWidth())
				|| (this.getY() + this.getHeight()) < other.getY() || this
				.getY() > (other.getY() + other.getHeight()));
	}

	// -----------Getters and setters-------------------

	/**
	 * Get the x-coordinate of the bounding box.
	 * 
	 * @return the x-coordinate of the bounding box
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the x-coordinate of the bounding box.
	 * 
	 * @param x
	 *            is the desired x-coordinate that you want the bounding box to
	 *            be at.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the y-coordinate of the bounding box.
	 * 
	 * @return the y-coordinate of the bounding box
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the y-coordinate of the bounding box.
	 * 
	 * @param y
	 *            is the desired x-coordinate that you want the bounding box to
	 *            be at.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the width of the bounding box.
	 * 
	 * @return the width of the bounding box.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the bounding box.
	 * 
	 * @param width
	 *            the desired width of the bounding box that you want it to be.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get the height of the bounding box.
	 * 
	 * @return the height of the bounding box.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the bounding box.
	 * 
	 * @param height
	 *            the desired height of the bounding box that you want it to be.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Update the x-coordinate of the bounding box. Used for moving bounding
	 * boxes(Fish).
	 * 
	 * @param x
	 *            the x-coordinate.
	 */
	public void updateX(int x) {
		this.x += x;
	}

	/**
	 * Update the y-coordinate of the bounding box. Used for moving bounding
	 * boxes(Fish).
	 * 
	 * @param y
	 *            the y coordinate.
	 */
	public void updateY(int y) {
		this.y += y;
	}

	/**
	 * Eguals method of the class. Check if two objects of bounding box are the
	 * same.
	 * 
	 * @param other
	 *            the other object that will be check against this one.
	 * @return a boolean indicating if the two objects are equal or not.
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
}
