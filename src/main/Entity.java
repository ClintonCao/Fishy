package main;

/**
 * An entity represents an entity in the game world. It has a movement speed, an
 * alive state, and a sprite as a model.
 * 
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel
 *         Doesburg.
 *
 */
public class Entity {
	private int moveSpeed;
	private Sprite sprite;

	/**
	 * Constructor, creates an entity in the game world.
	 * 
	 * @param moveSpeed
	 *            integer that determines the movement speed
	 * @param sprite
	 *            A sprite represents the model of an entity on the screen
	 */
	public Entity(int moveSpeed, Sprite sprite) {
		this.setMoveSpeed(moveSpeed);
		this.sprite = sprite;
	}

	/**
	 * The method intersects test whether two entities intersect with each
	 * other.
	 * 
	 * @param other
	 *            entity
	 * @return a boolean whether if this entity intersects with the other entity
	 */
	public boolean intersects(Entity other) {
		return this.sprite.getBoundingBox().intersects(
				other.getSprite().getBoundingBox());
	}

	/**
	 * The method intersects test whether this entity intersects with the left
	 * screen edge.
	 * 
	 * @return a boolean whether if this entity intersects with the left screen
	 *         edge
	 */
	public boolean intersectsLeftScreenEdge() {
		return this.sprite.getBoundingBox().getX() <= 0;
	}

	/**
	 * The method intersects test whether this entity intersects with the right
	 * screen edge.
	 * 
	 * @return a boolean whether if this entity intersects with the right screen
	 *         edge
	 */
	public boolean intersectsRightScreenEdge() {
		return (this.sprite.getBoundingBox().getX() + this.sprite
				.getBoundingBox().getWidth()) >= Game.getResX();
	}

	/**
	 * The method intersects test whether this entity intersects with the upper
	 * screen edge.
	 * 
	 * @return a boolean whether if this entity intersects with the upper screen
	 *         edge
	 */
	public boolean intersectsUpperScreenEdge() {
		return this.sprite.getBoundingBox().getY() <= 0;
	}

	/**
	 * The method intersects test whether this entity intersects with the under
	 * screen edge.
	 * 
	 * @return a boolean whether if this entity intersects with the under screen
	 *         edge
	 */
	public boolean intersectsUnderScreenEdge() {
		return (this.sprite.getBoundingBox().getY() + this.sprite
				.getBoundingBox().getHeight()) >= Game.getResY();
	}

	/**
	 * Get the sprite of this entity.
	 * 
	 * @return this sprite represents the model of an entity on the screen
	 */
	public Sprite getSprite() {
		return this.sprite;
	}

	/**
	 * Set the sprite of this entity.
	 * 
	 * @param sprite
	 *            the Sprite that you want to set for the entity.
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Get the movement speed of this entity.
	 * 
	 * @return integer movement speed
	 */
	public int getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * Set the movement speed.
	 * 
	 * @param moveSpeed
	 *            integer movement speed
	 */
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
