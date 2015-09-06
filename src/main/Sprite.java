package main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A sprite represents the model of an entity on the screen. It has a position in X and Y coordinates,
 * a certain velocity in a direction expressed by X and Y coordinates, A width and a height, and an image to draw on the screen.
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 */
public class Sprite {
	private Image img;
	private AABB aabb;
	
	public Sprite(Image img, AABB aabb) {
		this.img = img;
		setAabb(aabb);	
	}
	
	public void grow() {
		
	}
	
	public void updateX(int x) {
		aabb.updateX(x);
	}
	
	public void updateY(int y) {
		aabb.updateY(y);
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(img, aabb.getX(), aabb.getY());
	}
	
	public boolean intersects(Sprite s) {
		return s.getAabb().intersects(this.getAabb());
	}
	
//------------Getters and setters-------------------------------------------
	public Image getImg() {
		return this.img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}

	public AABB getAabb() {
		return aabb;
	}

	public void setAabb(AABB aabb) {
		this.aabb = aabb;
	}

}
