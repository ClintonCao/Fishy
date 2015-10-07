package main;

import interfaces.PlayerFishInterface;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * This class represents the fish that the player controls in the game.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public class PlayerFish extends Entity implements PlayerFishInterface {
  private static String leftImageName = "FishOriginal_transparent.png";
  private static String rightImageName = "Fish_Right_Transparent.png";
  private ArrayList<FishBomb> bombs = new ArrayList<FishBomb>();
  private Image leftImage;
  private Image rightImage;
  private boolean isAlive;
  private int score;
  private int counter;

  /**
   * Constructor.
   * @see Entity#Entity(int, Sprite)
   */
  public PlayerFish(int movespeed, boolean isAlive, Sprite sprite, int score) {

    super(movespeed, sprite);
    setAlive(isAlive);
    setScore(score);

    if (sprite.getImg() != null) {

      int imgWidth = (int) sprite.getImg().getWidth();
      int imgHeight = (int) sprite.getImg().getHeight();

      setPlayerFishLeftImage(new Image(leftImageName, imgWidth, imgHeight,
          true, true));
      setPlayerFishRightImage(new Image(rightImageName, imgWidth, imgHeight,
          true, true));
    } else {

      setPlayerFishLeftImage(new Image(leftImageName, 128, 128, true, true));
      setPlayerFishRightImage(new Image(rightImageName, 128, 128, true, true));
    }
  }

  /**
   * This method creates the fish the player controls. The image is scaled to
   * its starting size. A BoundingBox with the same dimensions is created and
   * placed at the middle of the screen.
   * 
   * @return new PlayerFish.
   */
  public static PlayerFish createPlayerFish() {

    Image temp = new Image(leftImageName);

    int startImageWidth = (int) (temp.getWidth() * 0.30);
    int startImageHeight = (int) (temp.getHeight() * 0.30);

    Image playerFishImage = new Image(leftImageName, startImageWidth,
        startImageHeight, true, true);

    int startPosX = MainScreenController.getScreenbox().getWidth() / 2;
    int startPosY = MainScreenController.getScreenbox().getHeight() / 2;

    BoundingBox boundingBox = new BoundingBox(startPosX, startPosY,
        startImageWidth, startImageHeight);

    Sprite sprite = new Sprite(playerFishImage, boundingBox);

    return new PlayerFish(10, true, sprite, 0);
  }

  /**
   * {@inheritDoc} A new scaled image is created, and the PlayerFish'
   * BoundingBox is scaled accordingly.
   */
  public void grow(double multiplier) {
    double newWidth = multiplier * this.getSprite().getImg().getWidth();
    double newHeight = multiplier * this.getSprite().getImg().getHeight();

    if (this.getSprite().getImg().equals(this.leftImage)) {
      this.setPlayerFishLeftImage(new Image(leftImageName, newWidth, newHeight, true, true));
      this.setPlayerFishRightImage(new Image(rightImageName, newWidth, newHeight, true, true));
      this.getSprite().setImg(leftImage);
    } else {
      this.setPlayerFishLeftImage(new Image(leftImageName, newWidth, newHeight, true, true));
      this.setPlayerFishRightImage(new Image(rightImageName, newWidth, newHeight, true, true));
      this.getSprite().setImg(rightImage);
    }
    BoundingBox playerFishBoundingBox = this.getSprite().getBoundingBox();

    playerFishBoundingBox.setWidth((int) this.getPlayerFishLeftImage().getWidth());
    playerFishBoundingBox.setHeight((int) this.getPlayerFishLeftImage().getHeight());
  }

  /**
   * {@inheritDoc} Width of the images is used for comparison.
   */
  public boolean playerDies(EnemyFish enemyfish) {
    return this.getSprite().getImg().getWidth() <= enemyfish.getSprite().getImg().getWidth();
  }

  /**
   * {@inheritDoc} Check if the x-coordinate is less or equals to the 0.
   */
  public boolean intersectsLeftScreenEdge() {
    return this.getSprite().getBoundingBox().getX() <= 0;
  }

  /**
   * {@inheritDoc} Check if the x-coordinate + width is greater or equals to the
   * x-resolution.
   */
  public boolean intersectsRightScreenEdge() {
    return (this.getSprite().getBoundingBox().getX() + this.getSprite().getBoundingBox().getWidth()) >= Game.getResX();
  }

  /**
   * {@inheritDoc} Check if the y-coordinate is less or equals to the 0.
   */
  public boolean intersectsUpperScreenEdge() {
    return this.getSprite().getBoundingBox().getY() <= 0;
  }

  /**
   * {@inheritDoc} Check if the y-coordinate + height is greater or equals to
   * the y-resolution.
   */
  public boolean intersectsUnderScreenEdge() {
    return (this.getSprite().getBoundingBox().getY() + this.getSprite().getBoundingBox().getHeight()) >= Game.getResY();
  }

  // --- Getters and Setters ---

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  public Image getPlayerFishLeftImage() {
    return leftImage;
  }

  public void setPlayerFishLeftImage(Image playerFishLeftImage) {
    this.leftImage = playerFishLeftImage;
  }

  public Image getPlayerFishRightImage() {
    return rightImage;
  }

  public void setPlayerFishRightImage(Image playerFishRightImage) {
    this.rightImage = playerFishRightImage;
  }

  public static String getPlayerFishLeftImageName() {
    return leftImageName;
  }

  public static void setPlayerFishLeftImageName(String playerFishLeftImageName) {
    PlayerFish.leftImageName = playerFishLeftImageName;
  }

  public static String getPlayerFishRightImageName() {
    return rightImageName;
  }

  public static void setPlayerFishRightImageName(String playerFishRightImageName) {
    PlayerFish.rightImageName = playerFishRightImageName;
  }

  /**
   * Set the score of the player.
   * 
   * @param number
   *          the score.
   */
  public void setScore(int number) {
    counter += (number - score);
    if (counter >= 100) {
      counter = 0;
      FishBomb bomb = FishBomb.createFishBomb(this);
      if (bombs.size() == 0) {
        bombs.add(bomb);
      }
    }
    score = number;
  }

  public int getScore() {
    return score;
  }

  public ArrayList<FishBomb> getBombs() {
    return bombs;
  }

  public void setBombs(ArrayList<FishBomb> items) {
    this.bombs = items;
  }
}
