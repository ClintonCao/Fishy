package main;

import java.util.Random;

import javafx.scene.image.Image;

/**
 * An enemy fish is any fish other than the player fish. It inherits the
 * movement speed and sprite attributes from the general entity class. The added
 * boolean 'isLefty' simply indicates if the fish spawns on the left side of the
 * screen. If 'isLefty' is false, the fish spawns at the right side of the
 * screen.
 * 
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel
 *         Doesburg.
 */
public class EnemyFish extends Entity {
  private static String leftImageFileName = "EnemyFish_Left.png";
  private static String rightImageFileName = "EnemyFish_Right.png";
  private boolean isLefty;

  /**
   * Constructor which creates an enemy fish.
   * 
   * @param movespeed
   *          integer that determines the movement speed
   * @param isLefty
   *          boolean that whether fish comes from the left side of the screen
   * @param sprite
   *          A sprite represents the model of an entity on the screen
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
   * @return an enemy fish.
   */
  public static EnemyFish generateFish() {

    Random rng = new Random();

    // Generate the height at which the fish spawns, its movement speed, and
    // at which side of the screen it spawns.
    int randomHeight = rng.nextInt(600);
    int randomSpeed = rng.nextInt(9) + 1;
    boolean isLefty = rng.nextBoolean();
    
    double imgSizeMultiplier = rng.nextInt(150) + 5;
    imgSizeMultiplier /= 100;

    // Generate the fish, depending on which side of the screen it spawns.
    if (isLefty) {
      // Get the image for the fish and its respective height and width.
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(rightImageFileName, fishImage.getWidth() * imgSizeMultiplier, fishImage.getHeight() * imgSizeMultiplier, true, true);
      
      int fishImageWidth = (int) fishImage.getWidth();
      int fishImageHeight = (int) fishImage.getHeight();

      return new EnemyFish(randomSpeed, isLefty, new Sprite(fishImage,
          new BoundingBox(-fishImageWidth, randomHeight, fishImageWidth,
              fishImageHeight)));
    } else {
      Image fishImage = new Image(leftImageFileName);
      fishImage = new Image(leftImageFileName, fishImage.getWidth() * imgSizeMultiplier, fishImage.getHeight() * imgSizeMultiplier, true, true);

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

  /**
   * The method isLefty determine which side does the fish comes from.
   * 
   * @return an boolean whether the fish comes from left
   */
  public boolean isLefty() {
    return isLefty;
  }

  /**
   * The method setLefty can decide which side does the fish comes from.
   * 
   * @param isLefty
   *          boolean that whether fish comes from the left side of the screen
   */
  public void setLefty(boolean isLefty) {
    this.isLefty = isLefty;
  }
}
