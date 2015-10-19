package nl.tudelft.fishy;

import nl.tudelft.fishy.interfaces.FishBombInterface;

import javafx.scene.image.Image;

/**
 * This class represents the FishBomb object. This object is an item that the
 * player can use in the game in order to kill enemy fishes and to get a higher
 * score. 
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 *
 */
public class FishBomb extends Item implements FishBombInterface {
  private int radius;
  private Image explosionImg;


  /**
   * Constructor.
   */
  public FishBomb(Sprite sprite, int radius, Image explosionImg, int posX, int posY) {
    super(sprite, posX, posY);
    this.setRadius(radius);
    this.setExplosionImg(explosionImg);
  }


  /**
   * Creates a new FishBomb.
   * 
   * @param pf - The PlayerFish that will get the bomb.
   * @return the new bomb.
   */
  public static FishBomb createFishBomb(PlayerFish pf) {
    BoundingBox pfbb = pf.getSprite().getBoundingBox();
    int explImgDim = pfbb.getHeight() * 4;
    return new FishBomb(new Sprite(new Image("/resources/fishbombbig.png"),
        new BoundingBox(10, 10, 0, 0)), explImgDim / 2, new Image(
            "/resources/redcircle.png", explImgDim, explImgDim, true, true),
            (int) (pfbb.getX() - 0.5 * pf.getSprite().getImg().getWidth()),
            (int) (pfbb.getY() - 0.5 * pf.getSprite().getImg().getHeight()));
  }


  /**
   * {@inheritDoc} STILL NEEDS EXPLANATION.
   */
  public boolean intersectsRectangle(BoundingBox bb) {
    int circleDistanceX = Math.abs(getPosX() - bb.getX());
    int circleDistanceY = Math.abs(getPosY() - bb.getY());

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

  // --- Getters and Setters ---

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public Image getExplosionImg() {
    return explosionImg;
  }

  public void setExplosionImg(Image explosionImg) {
    this.explosionImg = explosionImg;
  }
}
