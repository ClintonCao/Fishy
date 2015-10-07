package main;

import interfaces.EntityInterface;

/**
 * Entity is modeled by a Sprite and a movement speed.
 * @see Sprite
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public abstract class Entity implements EntityInterface {
  private int moveSpeed;
  private Sprite sprite;

  /**
   * Constructor.
   */
  public Entity(int moveSpeed, Sprite sprite) {
    this.setMoveSpeed(moveSpeed);
    this.sprite = sprite;
  }

  /**
   * {@inheritDoc} Check if the BoundingBoxes of the two sprites intersect.
   * @see BoundingBox#intersects(BoundingBox)
   */
  public boolean intersects(Entity other) {
    return this.sprite.getBoundingBox().intersects(other.getSprite().getBoundingBox());
  }
  
// --- Getters and Setters ---  

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
