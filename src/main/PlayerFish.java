package main;

import javafx.scene.image.Image;

/**
 * A PlayerFish represents the Entity controlled by the player.
 * 
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel
 *         Doesburg.
 *
 */
public class PlayerFish extends Entity {
  private static String leftImageName = "FishOriginal_transparent.png";
  private static String rightImageName = "Fish_Right_Transparent.png";

  private Image leftImage;
  private Image rightImage;
  private boolean isAlive;
  private int score;

  /**
   * The PlayerFish's constructor.
   * 
   * @param movespeed
   *          Integer that represents the speed of the fish.
   * @param isAlive
   *          Boolean that determines whether the fish is alive or dead.
   * @param leftImgFileName
   *          How the fish looks like when swimming left.
   * @param rightImgFileName
   *          How the image looks like swimming right.
   * @param sprite
   *          The fish's Sprite
   * @param score
   *          The Fish's score
   */
  public PlayerFish(int movespeed, boolean isAlive, String leftImgFileName,
      String rightImgFileName, Sprite sprite, int score) {
    super(movespeed, sprite);
    setAlive(isAlive);
    if(sprite.getImg()!=null) {
    	setPlayerFishLeftImage(new Image(leftImageName, sprite.getImg().getWidth(), sprite.getImg().getHeight(), true, true));
    	setPlayerFishRightImage(new Image(rightImageName, sprite.getImg().getWidth(), sprite.getImg().getHeight(), true, true));
    } else {
    	setPlayerFishLeftImage(new Image(leftImageName, 128, 128, true, true));
    	setPlayerFishRightImage(new Image(rightImageName, 128, 128, true, true));
    }
    this.score = score;
  }

  /**
   * This method creates the fish the player controls.
   * 
   * @return An object of PlayerFish.
   */
  public static PlayerFish createPlayerFish() {
    Image temp = new Image(leftImageName);
    Image playerFishImage = new Image(leftImageName, temp.getWidth() * 0.30, temp.getHeight() * 0.30, true, true);
    //Image playerFishImageRight = new Image(rightImageName, temp.getWidth() * 0.30, temp.getHeight() * 0.30, true, true);

    // Create a hitbox for the playerfish. The playerfish will start at the
    // middle of the screen.
    // So the starting position is the respective screen diameters/2. The
    // size of the hitbox is
    // the size of the image casted to int values.
    BoundingBox boundingBox = new BoundingBox(MainScreenController
        .getScreenbox().getWidth() / 2, MainScreenController.getScreenbox()
        .getHeight() / 2, (int) playerFishImage.getWidth(),
        (int) playerFishImage.getHeight());

    // Create a new 'sprite' using the image and its corresponding hitbox.
    Sprite sprite = new Sprite(playerFishImage, boundingBox);

    return new PlayerFish(10, true, leftImageName, rightImageName, sprite, 0);
  }

  /**
   * This method grows the fish when it 'eats' another fish.
   * 
   * @param multiplier
   *          the multiplier for the X and Y values.
   */
  public void grow(double multiplier) {
    BoundingBox playerFishBoundingBox = this.getSprite().getBoundingBox();
    double newWidth = multiplier * this.getSprite().getImg().getWidth();
    double newHeight = multiplier * this.getSprite().getImg().getHeight();

    this.setPlayerFishLeftImage(new Image("FishOriginal_transparent.png",
        newWidth, newHeight, true, true));
    this.setPlayerFishRightImage(new Image("Fish_Right_Transparent.png",
        newWidth, newHeight, true, true));
    
    this.getSprite().setImg(leftImage);

    playerFishBoundingBox.setWidth((int) this.getPlayerFishLeftImage()
        .getWidth());
    playerFishBoundingBox.setHeight((int) this.getPlayerFishLeftImage()
        .getHeight());
  }

  /**
   * This method looks if the Player fish is smaller than the enemy fish.
   * 
   * @param enemyfish
   *          The fish it compares itself with
   * @return Boolean that determines whether the player fish dies.
   */
  public boolean playerDies(EnemyFish enemyfish) {
	return this.getSprite().getImg().getWidth() <= enemyfish.getSprite().getImg().getWidth();
  }

  /**
   * This method retrieves the info about the PlayerFish's life.
   * 
   * @return Whether the PlayerFish is alive
   */
  public boolean isAlive() {
    return isAlive;
  }

  /**
   * This method edits the PlayerFish's info about whether it is alive.
   * 
   * @param isAlive
   *          The boolean that replaces the old value
   */
  public void setAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  /**
   * This method retrieves the Left-side Image of the PlayerFish.
   * 
   * @return The image of player fish left image
   */
  public Image getPlayerFishLeftImage() {
    return leftImage;
  }

  /**
   * This method replaces the Left-side Image of the PlayerFish.
   * 
   * @param playerFishLeftImage
   *          The image that replaces the old one
   * 
   */
  public void setPlayerFishLeftImage(Image playerFishLeftImage) {
    this.leftImage = playerFishLeftImage;
  }

  /**
   * This method retrieves the Right-side Image of the PlayerFish.
   * 
   * @return The image of player fish right image
   */
  public Image getPlayerFishRightImage() {
    return rightImage;
  }

  /**
   * This method replaces the Right-side Image of the PlayerFish.
   * 
   * @param playerFishRightImage
   *          The image that replaces the old one
   */
  public void setPlayerFishRightImage(Image playerFishRightImage) {
    this.rightImage = playerFishRightImage;
  }

  /**
   * This method retrieves the string of the Left-side image.
   * 
   * @return The string of the image
   */
  public static String getPlayerFishLeftImageName() {
    return leftImageName;
  }

  /**
   * This method changes the string of the left-side image.
   * 
   * @param playerFishLeftImageName
   *          The replacement string
   */
  public static void setPlayerFishLeftImageName(String playerFishLeftImageName) {
    PlayerFish.leftImageName = playerFishLeftImageName;
  }

  /**
   * This method retrieves the string of the Right-side image.
   * 
   * @return The string of the image
   */
  public static String getPlayerFishRightImageName() {
    return rightImageName;
  }

  /**
   * This method changes the string of the right-side image.
   * 
   * @param playerFishRightImageName
   *          The replacement string
   */
  public static void setPlayerFishRightImageName(String playerFishRightImageName) {
    PlayerFish.rightImageName = playerFishRightImageName;
  }

  /**
   * This method replaces the PlayerFish's score.
   * 
   * @param number
   *          The score that will replace the old value
   */
  public void setScore(int number) {
    score = number;
  }

  /**
   * This method Retrieves the score of the PlayerFish.
   * 
   * @return The score
   */
  public int getScore() {
    return score;
  }
}
