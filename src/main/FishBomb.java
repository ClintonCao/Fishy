package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents the FishBomb object. This object is an item that the
 * player can use in the game in order to kill enemy fishes and to get a higher
 * score.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class FishBomb {
  private Sprite sprite;
  private int radius;
  private Image explosionImg;
  private int posX;
  private int posY;

  /**
   * The constructor for the FishBomb class.
   *
   * @param sprite
   *          The sprite used to represent the bomb.
   * @param radius
   *          The the area of effect o the bomb.
   * @param posX
   *          The x coordinate of the bomb.
   * @param posY
   *          The y coordinate of the bomb.
   * @param explosionImg
   *          The image for the explosion.
   */
  public FishBomb(Sprite sprite, int radius, Image explosionImg, int posX,
      int posY) {
    this.setSprite(sprite);
    this.setRadius(radius);
    this.setExplosionImg(explosionImg);
    this.setPosX(posX);
    this.setPosY(posY);
  }

  /**
   * Set the sprite of the item.
   * 
   * @param sprite
   *          the desired sprite that will be set to the Fishbomb.
   */
  public void setSprite(Sprite sprite) {
    this.sprite = sprite;

  }

  /**
   * Get the sprite of the Fishbomb object.
   * 
   * @return a sprite object
   */
  public Sprite getSprite() {
    return this.sprite;

  }

  /**
   * Get the radius of the bomb.
   * 
   * @return an integer value of the radius.
   */
  public int getRadius() {
    return radius;
  }

  /**
   * Set the radius of the bomb.
   * 
   * @param radius
   *          the desired integer value of the bomb.
   */
  public void setRadius(int radius) {
    this.radius = radius;
  }

  /**
   * Update the x coordinate of the bomb.
   * 
   * @param mod
   *          an integer value.
   */
  public void updateX(int mod) {
    posX += mod;
  }

  /**
   * Update the y coordinate of the bomb.
   * 
   * @param mod
   *          an integer value.
   */
  public void updateY(int mod) {
    posY += mod;
  }

  /**
   * Creates a new FishBomb.
   * 
   * @param pf
   *          The entity that will use the bomb.
   * @return Returns the new bomb.
   */
  public static FishBomb createFishBomb(PlayerFish pf) {
    BoundingBox pfbb = pf.getSprite().getBoundingBox();
    int explImgDim = pfbb.getHeight() * 4;
    return new FishBomb(new Sprite(new Image("fishbombbig.png"),
        new BoundingBox(10, 10, 0, 0)), explImgDim / 2, new Image(
        "redcircle.png", explImgDim, explImgDim, true, true),
        (int) (pfbb.getX() - 0.5 * pf.getSprite().getImg().getWidth()),
        (int) (pfbb.getY() - 0.5 * pf.getSprite().getImg().getHeight()));
  }

  /**
   * Renders the sprite of this object.
   * 
   * @param gc
   *          GraphicsContext where the image will be drawn.
   */
  public void render(GraphicsContext gc) {
    gc.drawImage(this.getSprite().getImg(), posX, posY);
  }

  /**
   * Checks if the bomb affects another entity.
   * 
   * @param bb
   *          The boundingbox of the entity affected.
   * @return Returns if the entity is effected.
   */
  public boolean intersectsRectangle(BoundingBox bb) {
    int circleDistanceX = Math.abs(posX - bb.getX());
    int circleDistanceY = Math.abs(posY - bb.getY());

    if (circleDistanceX > (bb.getWidth() / 2 + radius)) {
      return false;
    }
    if (circleDistanceY > (bb.getHeight() / 2 + radius)) {
      return false;
    }

    if (circleDistanceX <= (bb.getWidth() / 2)) {
      return true;
    }
    if (circleDistanceY <= (bb.getHeight() / 2)) {
      return true;
    }

    if (Math.sqrt(circleDistanceX ^ 2 + circleDistanceY ^ 2) <= radius) {
      return true;
    }

    int cornerDistance = (circleDistanceX - bb.getWidth() / 2) ^ 2
        + (circleDistanceY - bb.getHeight() / 2) ^ 2;

    return (cornerDistance <= (radius ^ 2));
  }

  /**
   * Get the x coordinate of the bomb.
   * 
   * @return the x coordinate of the bomb.
   */
  public int getPosX() {
    return posX;
  }

  /**
   * Set the x coordinate of the bomb.
   * 
   * @param posX
   *          the desired x coordinate.
   */
  public void setPosX(int posX) {
    this.posX = posX;
  }

  /**
   * Get the y coordinate of the bomb.
   * 
   * @return the y coordinate of the bomb.
   */
  public int getPosY() {
    return posY;
  }

  /**
   * Set the y coordinate of the bomb.
   * 
   * @param posY
   *          the desired y coordinate.
   */
  public void setPosY(int posY) {
    this.posY = posY;
  }

  /**
   * Get the image for the explosion.
   * 
   * @return the image for explosion.
   */
  public Image getExplosionImg() {
    return explosionImg;
  }

  /**
   * Set the explosion image.
   * 
   * @param explosionImg
   *          the desired image for the explosion.
   */
  public void setExplosionImg(Image explosionImg) {
    this.explosionImg = explosionImg;
  }
}
