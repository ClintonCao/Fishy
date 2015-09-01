package main;
import javafx.scene.image.Image;

/**
 *
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 * 
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
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
	}
	
	public Image getImg() {
		return this.img;
	}
}
