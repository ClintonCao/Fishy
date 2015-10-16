package main;

import interfaces.EnemyFishInterface;

import java.util.Random;

import javafx.scene.image.Image;


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
public final class EndBoss extends Entity implements EnemyFishInterface {
  private static String leftImageFileName = "SharkelliLeft.png";
  private static String rightImageFileName = "SharkelliRight.png";
  private boolean isLefty;
  private static BoundingBox boundingBox = new BoundingBox(1, 1, 1, 1);
  private static Sprite sprite;
  private static int health;
  private static EndBoss singletonEndBoss;

  /**
   * End boss Constructor, this is private,
   * so this ensures that EndBoss class only has once instance.
   * @see Entity#Entity(int, Sprite)
   */
  @SuppressWarnings("static-access")
private EndBoss(int movespeed, boolean isLefty, Sprite sprite) {
    super(movespeed, sprite);
    this.isLefty = isLefty;
    this.sprite = sprite;
  }
  
  /**
   * Get the an instance of EndBoss, 
   * there should maximum one instance on the stage.
   * @return the end boss.
   */
  public static synchronized EndBoss getSingletonEndBoss() {
    if (singletonEndBoss == null) {
      singletonEndBoss = generateBoss();
    }
    return singletonEndBoss; 
  }

  /**
   * Generate end boss to be placed on the screen. Make a end boss with a
   * random movement speed, relatively random height at which it spawns, 
   * and random side of the screen at which it spawns.
   * 
   * @return a new end boss
   */
  private static EndBoss generateBoss() {
    Random rng = new Random();

    // Generate the height at which the end boss spawns, its movement speed, and
    // at which side of the screen it spawns.
    int randomHeight = rng.nextInt(600);
    int speed = 6;
    boolean isLefty = rng.nextBoolean();

    double imgSizeMultiplier = 100;
    imgSizeMultiplier /= 100;
    
    boundingBox.setY(randomHeight);

    // Generate the end boss, depending on which side of the screen it spawns.
    if (isLefty) {
      // Get the image for the end boss and its respective height and width.
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(rightImageFileName, fishImage.getWidth() * imgSizeMultiplier,
          fishImage.getHeight() * imgSizeMultiplier, true, true);
      int fishImageWidth = (int) fishImage.getWidth();
      int fishImageHeight = (int) fishImage.getHeight();
      sprite = new Sprite(fishImage, boundingBox);
      boundingBox.setHeight(fishImageHeight);
      boundingBox.setWidth(fishImageWidth);
      boundingBox.setX(-2000);
      boundingBox.setY(-2000);
      sprite.setBoundingBox(boundingBox);
     
      return new EndBoss(speed, isLefty, sprite);
    } else {
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(leftImageFileName, fishImage.getWidth() * imgSizeMultiplier, 
          fishImage.getHeight() * imgSizeMultiplier, true, true);
      int fishImageWidth = (int) fishImage.getWidth();
      int fishImageHeight = (int) fishImage.getHeight();
      sprite = new Sprite(fishImage, boundingBox);
      boundingBox.setHeight(fishImageHeight);
      boundingBox.setWidth(fishImageWidth);
      boundingBox.setX(-2000);
      boundingBox.setY(-2000);
      sprite.setBoundingBox(boundingBox);
      // If the end boss spawns at the right side of the screen, it needs to
      // be placed at the X coordinate equal to the width of the AABB
      // screenbox;
      return new EndBoss(speed, isLefty, sprite);
    }
  }
  
  /**
   * Switches the direction the EndBoss is facing.
   */
  public void switchDirection() {
  	if(isLefty) {
  		sprite.setImg(new Image("SharkelliLeft.png"));
  	} else {
  		sprite.setImg(new Image("SharkelliRight.png"));
  	}
  	isLefty = !isLefty;
  }
  
  // --- Getters and Setters ---  
  
  public boolean isLefty() {
    return isLefty;
  }

  public void setLefty(boolean isLefty) {
    this.isLefty = isLefty;
  }
  
  public int getHealth() {
    return health;  
  }
  
  public void setHealth(int currentHealth) {
    health = currentHealth;
  }
}
