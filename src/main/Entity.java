package main;

public class Entity {
	private double movespeed;
	private boolean isAlive;
	private Sprite sprite;
	
	public Entity(double movespeed, boolean isAlive, Sprite sprite) {
		this.movespeed = movespeed;
		this.isAlive = isAlive;
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
}
