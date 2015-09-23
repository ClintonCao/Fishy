package main;
import java.lang.Math;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FishBomb extends Item {
	private int radius;
	private int posX;
	private int posY;

	public FishBomb(int uses, Sprite sprite, int radius, int posX, int posY) {
		super(uses, sprite);
		this.setRadius(radius);
		this.setPosX(posX);
		this.setPosY(posY);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void updateX(int mod) {
		posX += mod;
	}
	
	public void updateY(int mod) {
		posY += mod;
	}
	
	public static FishBomb createFishBomb(PlayerFish pf) {
		BoundingBox pfbb = pf.getSprite().getBoundingBox();
		return new FishBomb(1, new Sprite(new Image("fishbombbig.png"), new BoundingBox(10, 10, 0, 0)), 100, pfbb.getX(), pfbb.getY());
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(this.getSprite().getImg(), posX, posY);
	}
	
	public boolean intersectsRectangle(BoundingBox bb) {
	    int circleDistanceX = Math.abs(posX - bb.getX());
	    int circleDistanceY = Math.abs(posY- bb.getY());

	    if (circleDistanceX > (bb.getWidth()/2 + radius)) { return false; }
	    if (circleDistanceY > (bb.getHeight()/2 + radius)) { return false; }

	    if (circleDistanceX <= (bb.getWidth()/2)) { return true; } 
	    if (circleDistanceY <= (bb.getHeight()/2)) { return true; }
	    
	    if (Math.sqrt(circleDistanceX^2 + circleDistanceY^2) <= radius) {
	    	return true;
	    }
	    
	    int cornerDistance = (circleDistanceX - bb.getWidth()/2)^2 + (circleDistanceY - bb.getHeight()/2)^2;

	    return (cornerDistance <= (radius^2));
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
