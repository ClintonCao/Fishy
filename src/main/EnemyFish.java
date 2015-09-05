package main;

public class EnemyFish extends Entity {
	private boolean isLefty;
	
	public EnemyFish(int movespeed, boolean isLefty, Sprite sprite) {
		super(movespeed, sprite);
		this.isLefty = isLefty;
	}

	public boolean isLefty() {
		return isLefty;
	}

	public void setLefty(boolean isLefty) {
		this.isLefty = isLefty;
	}
}
