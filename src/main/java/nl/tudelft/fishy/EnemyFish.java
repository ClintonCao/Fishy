package nl.tudelft.fishy;

import nl.tudelft.fishy.controllers.MainScreenController;
import nl.tudelft.fishy.interfaces.EnemyFishInterface;

import java.util.Random;

import javafx.scene.image.Image;

/**
 * An EnemyFish is a fish which automatically moves over the screen,
 * from left to right or right to left, field isLefty determines this. 
 * If the PlayerFish collides with a smaller EnemyFish, the EnemyFish dies. 
 * If the EnemyFish is larger, the Player dies.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public class EnemyFish extends Entity implements EnemyFishInterface {
  private static String leftImageFileName = "/resources/EnemyFish_Left.png";
  private static String rightImageFileName = "/resources/EnemyFish_Right.png";
  private boolean isLefty;

  /**
   * Constructor. 
   * @see Entity#Entity(int, Sprite)
   */
  public EnemyFish(int movespeed, boolean isLefty, Sprite sprite) {
    super(movespeed, sprite);
    this.isLefty = isLefty;
  }

  /**
   * Generate an enemy fish to be placed on the screen. Make a new fish with a
   * random movement speed, random height at which it spawns, and random side of
   * the screen at which it spawns.
   * 
   * @return a new enemy fish.
   */
  public static EnemyFish generateFish() {

    Random rng = new Random();

    // Generate the height at which the fish spawns, its movement speed, and
    // at which side of the screen it spawns.
    int randomHeight = rng.nextInt(600);
    int randomSpeed = rng.nextInt(9) + 1;
    boolean isLefty = rng.nextBoolean();

    double imgSizeMultiplier = rng.nextInt(50) + 5;
    imgSizeMultiplier /= 100;

    // Generate the fish, depending on which side of the screen it spawns.
    if (isLefty) {
      // Get the image for the fish and its respective height and width.
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(rightImageFileName, fishImage.getWidth()
          * imgSizeMultiplier, fishImage.getHeight() * imgSizeMultiplier, true,
          true);

      int fishImageWidth = (int) fishImage.getWidth();
      int fishImageHeight = (int) fishImage.getHeight();

      return new EnemyFish(randomSpeed, isLefty, new Sprite(fishImage,
          new BoundingBox(-fishImageWidth, randomHeight, fishImageWidth,
              fishImageHeight)));
    } else {
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(leftImageFileName, fishImage.getWidth()
          * imgSizeMultiplier, fishImage.getHeight() * imgSizeMultiplier, true,
          true);

      int fishImageWidth = (int) fishImage.getWidth();
      int fishImageHeight = (int) fishImage.getHeight();

      // If the fish spawns at the right side of the screen, it needs to
      // be placed at the X coordinate equal to the width of the AABB
      // screenbox;
      return new EnemyFish(randomSpeed, isLefty, new Sprite(fishImage,
          new BoundingBox(MainScreenController.getScreenbox().getWidth(),
              randomHeight, fishImageWidth, fishImageHeight)));
    }
  }

  // --- Getters and Setters ---  
  
  public boolean isLefty() {
    return isLefty;
  }

  public void setLefty(boolean isLefty) {
    this.isLefty = isLefty;
  }
}
