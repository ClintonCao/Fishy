package main;

/**
 * An Axis-Aligned Bounding Box is a rectangle aligned with the X and Y axis, i.e. no rotation.
 * The AABB is used as a hitbox for collision detection between fish.
 * @author Michiel
 *
 */
public class AABB {
	private double x;
	private double y;
	private double width;
	private double height;
	
	public AABB(double x, double y, double width, double height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	//public boolean isColliding(AABB other) {
	//}
	
//-----------Getters and setters-------------------
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
