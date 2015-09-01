package main;

/**
 * An entity represents an entity in the game world. It has a movement speed, an alive state, and
 * a sprite as a model.
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 *
 */
public class Entity {
	private double movespeed;
	private boolean isAlive;
	private Sprite sprite;
	
	public Entity(double movespeed, boolean isAlive, Sprite sprite) {
		this.setMovespeed(movespeed);
		this.setAlive(isAlive);
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public double getMovespeed() {
		return movespeed;
	}

	public void setMovespeed(double movespeed) {
		this.movespeed = movespeed;
	}
}
