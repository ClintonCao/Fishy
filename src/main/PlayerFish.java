package main;

public class PlayerFish extends Entity {
	private boolean isAlive;
	
	public PlayerFish(int movespeed, boolean isAlive, Sprite sprite) {
		super(movespeed, sprite);
		this.isAlive = isAlive;
	}
	
	public boolean playerDies(EnemyFish enemyfish) {
		AABB playerAABB = this.getSprite().getAabb();
		AABB enemyAABB = enemyfish.getSprite().getAabb();
		return((playerAABB.getX() * playerAABB.getY()) <= (enemyAABB.getX() * enemyAABB.getY()));
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
