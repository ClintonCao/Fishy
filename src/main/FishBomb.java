package main;

import java.lang.Math;  

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FishBomb extends Item {
  private int radius;
  private Image explosionImg;
  private int posX;
  private int posY;

  /**
   * The constructor for the FishBomb class.
   *
   * @param sprite
   *    The sprite used to represent the bomb.
   * @param radius
   *    The the area of effect o the bomb.
   * @param posX
   *    The x coordinate of the bomb.
   * @param posY
   *    The y coordinate of the bomb.
   */
  public FishBomb(Sprite sprite, int radius, Image explosionImg, int posX, int posY) {
    super(sprite);
    this.setRadius(radius);
    this.setExplosionImg(explosionImg);
    this.setPosX(posX);
    this.setPosY(posY);
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public void updateX(int mod) {
    posX += mod;
  }

  public void updateY(int mod) {
    posY += mod;
  }

  /**
   * Creates a new FishBomb.
   * @param pf
   *    The entity that will use the bomb.
   * @return
   *    Returns the new bomb.
   */
  public static FishBomb createFishBomb(PlayerFish pf) {
    BoundingBox pfbb = pf.getSprite().getBoundingBox();
    int explImgDim = pfbb.getHeight() * 4;
    return new FishBomb(new Sprite(new Image("fishbombbig.png"),
        new BoundingBox(10, 10, 0, 0)), explImgDim / 2, 
        new Image("redcircle.png", explImgDim, explImgDim, true, true), 
    (int) (pfbb.getX() - 0.5 * pf.getSprite().getImg().getWidth()), 
    (int) (pfbb.getY() - 0.5 * pf.getSprite().getImg().getHeight()));
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(this.getSprite().getImg(), posX, posY);
  }

  /**
   * Checks if the bomb affects another entity.
   * @param bb
   *    The boundingbox of the entity affected.
   * @return
   *    Returns if the entity is effected.
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

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public Image getExplosionImg() {
    return explosionImg;
  }

  public void setExplosionImg(Image explosionImg) {
    this.explosionImg = explosionImg;
  }
}
