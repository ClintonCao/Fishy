package main;

/**
 * An entity represents an entity in the game world. It has a movement speed, an alive state, and
 * a sprite as a model.
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 *
 */
public class Entity {
	private int moveSpeed;
	private Sprite sprite;
	
	public Entity(int moveSpeed, Sprite sprite) {
		this.setMoveSpeed(moveSpeed);
		this.sprite = sprite;
	}
	
	public boolean intersects(Entity other) {
		return this.sprite.getAabb().intersects(other.getSprite().getAabb());
	}

	public boolean intersectsLeftScreenEdge() {
		return this.sprite.getAabb().getX() <= 0;
	}
	
	public boolean intersectsRightScreenEdge() {
		return (this.sprite.getAabb().getX() + this.sprite.getAabb().getWidth()) >= Game.getResX();
	}
	
	public boolean intersectsUpperScreenEdge() {
		return this.sprite.getAabb().getY() <= 0;
	}
	
	public boolean intersectsUnderScreenEdge() {
		return (this.sprite.getAabb().getY() + this.sprite.getAabb().getHeight()) >= Game.getResY();
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
