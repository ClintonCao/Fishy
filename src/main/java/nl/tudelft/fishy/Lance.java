package nl.tudelft.fishy;

import nl.tudelft.fishy.interfaces.LanceInterface;
import javafx.scene.image.Image;

/**
 * This class represents the Lance item that the player can use to fight
 * the final boss of the game.
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public final class Lance extends Item implements LanceInterface {
  private boolean isLefty;
  private static Lance singletonLance;

  /**
   * Contructor.
   */
  private Lance(Sprite sprite, int posX, int posY, boolean isLefty) {
    super(sprite, posX, posY);
    this.setLefty(isLefty);
  }
  
  /**
   * Get the an instance of Lance, but there's maximum of once instance.
   * @param playerFish the player.
   * @return the Lance.
   */
  public static synchronized Lance getSingletonLance(PlayerFish playerFish) {
    if (singletonLance == null) {
      singletonLance = createLance(playerFish);
    }
    return singletonLance; 
  }

  /**
   * Create the lance for the player.
   * @param pf the player.
   * @return the lance.
   */
  public static Lance createLance(PlayerFish pf) {
    int posX = 0;
    int posY = GameLoop.screenbox.getHeight() / 4 * 3;
    
    Image lanceImg = new Image("/Lance.png");
    int lanceWidth = (int) lanceImg.getWidth();
    int lanceHeight = (int) lanceImg.getHeight();
    
    BoundingBox lancebb = new BoundingBox(posX, posY, lanceWidth, lanceHeight);
    Sprite lanceSprite = new Sprite(lanceImg, lancebb);
    
    return new Lance(lanceSprite, posX, posY, true);
  }

  /**
   * {@inheritDoc}.
   */
  public boolean intersect(Sprite other) {
    return super.getSprite().intersects(other);
  }
  
  /**
   * Switches the direction the lance is facing.
   */
  public void switchDirection() {
    isLefty = !isLefty;
  }

  // -----------Getters and setters----------------

  public boolean isLefty() {
    return isLefty;
  }

  public void setLefty(boolean isLefty) {
    this.isLefty = isLefty;
  }

}
