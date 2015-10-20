package nl.tudelft.fishy;

import nl.tudelft.fishy.interfaces.LifeInterface;

import javafx.scene.image.Image;

/**
 * This class represents the Life item that the player can catch and increase
 * the number of lives the player have.
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public final class Life extends Item implements LifeInterface {

  private static String imageFileName = "/Heart.png";
  private static Life singletonLife;

  /**
   * Life constructor.
   */
  private Life(Sprite sprite, int posX, int posY) {
    super(sprite, posX, posY);
  }

  
  /**
   * Get the an instance of Life Item, there's maximum of one instance.
   * @return the Life item.
   */
  public static synchronized Life getSingletonLife() {
    if (singletonLife == null) {
      singletonLife = createLifeItem();
    }
    return singletonLife; 
  }
  
  /**
   * Create the life item in the game
   * @return the life item.
   */
  private static Life createLifeItem() {
    // render the life image
    Image lifeImage = new Image(imageFileName);
    // starting position of the image on the screen
    int posX = 0;
    int posY = Game.getScreenbox().getHeight() / 4 * 3;
    // gets the image's height and width
    int lifeWidth = (int) lifeImage.getWidth();
    int lifeHeight = (int) lifeImage.getHeight();
    
    BoundingBox lifebb = new BoundingBox(posX, posY, lifeWidth, lifeHeight);
    Sprite lifeSprite = new Sprite(lifeImage, lifebb);
    
    return new Life(lifeSprite, posX, posY);
  }

  /**
   * {@inheritDoc}.
   */
  public boolean intersect(Sprite other) {
    return super.getSprite().intersects(other);
  }


}