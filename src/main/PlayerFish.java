package main;

public class PlayerFish extends Entity {
	private boolean isAlive;
	
	public PlayerFish(int movespeed, boolean isAlive, Sprite sprite) {
		super(movespeed, sprite);
		this.isAlive = isAlive;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
