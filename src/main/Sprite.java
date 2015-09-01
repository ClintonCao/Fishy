package main;
import javafx.scene.image.Image;

/**
 * A sprite represents the model of an entity on the screen. It has a position in X and Y coordinates,
 * a certain velocity in a direction expressed by X and Y coordinates, A width and a height, and an image to draw on the screen.
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 */
public class Sprite {
	private Image img;
	private double posX;
	private double posY;
	private double velX;
	private double velY;
	private double width;
	private double height;
	
	public Sprite(Image img, double posX, double posY, double velX, double velY, double width, double height) {
		this.img = img;
		this.setPosX(posX);
		this.setPosY(posY);
		this.setVelX(velX);
		this.setVelY(velY);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	
//------------Getters and setters-------------------------------------------
	public Image getImg() {
		return this.img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
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
