package nl.tudelft.fishy;

import nl.tudelft.fishy.controllers.MainScreenController;
import nl.tudelft.fishy.interfaces.LifeInterface;

import javafx.scene.image.Image;

/**
 * This class represents the Life item that the player can catch and increase
 * the number of lives the player have.
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public class Life extends Item implements LifeInterface {

  private static String imageFileName = "/Heart.png";
  private static Image image = new Image(imageFileName);

  /**
   * Life constructor.
   */
  public Life(Sprite sprite, int posX, int posY, Image imgae) {
    super(sprite, posX, posY);
    this.setImage(image);
  }

  /**
   * Create the life item in the game
   * @return the life item.
   */
  public static Life createLifeItem() {
    // render the life image
    Image lifeImage = image;
    // starting position of the image on the screen
    int posX = 0;
    int posY = MainScreenController.getScreenbox().getHeight() / 4 * 3;
    // gets the image's height and width
    int lifeWidth = (int) lifeImage.getWidth();
    int lifeHeight = (int) lifeImage.getHeight();
    
    BoundingBox lifebb = new BoundingBox(posX, posY, lifeWidth, lifeHeight);
    Sprite lifeSprite = new Sprite(lifeImage, lifebb);
    
    return new Life(lifeSprite, posX, posY, lifeImage);
  }

  /**
   * {@inheritDoc}.
   */
  public boolean intersect(Sprite other) {
    return super.getSprite().intersects(other);
  }

  // -----------Getters and setters----------------

  public void setImage(Image img) {
    Life.image = img;

  }

  public Image getImage() {
    return Life.image;
  }

}