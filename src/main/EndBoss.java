package main;

import interfaces.EnemyFishInterface;

/**
 * An EndBoss is the end boss in this game which automatically moves over the screen,
 * from left to right or right to left, field isLefty determines this. 
 * If the PlayerFish collides with end boss without wielding the lance, 
 * the PlayerFish dies. 
 * If the PlayerFish collides with end boss with the lance, 
 * end boss dies (it might take several hits).
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public class EndBoss extends Entity implements EnemyFishInterface {
  private static String leftImageFileName = "SharkelliLeft.png";
  private static String rightImageFileName = "SharekelliRight.png";
  private boolean isLefty;

  /**
   * Constructor. 
   * @see Entity#Entity(int, Sprite)
   */
  public EndBoss(int movespeed, boolean isLefty, Sprite sprite) {
    super(movespeed, sprite);
    this.isLefty = isLefty;
  }
  
  // --- Getters and Setters ---  
  
  public boolean isLefty() {
    return isLefty;
  }

  public void setLefty(boolean isLefty) {
    this.isLefty = isLefty;
  }

}
