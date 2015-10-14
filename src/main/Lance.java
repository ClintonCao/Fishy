package main;

import interfaces.LanceInterface;
import javafx.scene.image.Image;

public final class Lance extends Item implements LanceInterface {

  private Image image;
  private static Lance singletonLance;

  /**
   * Contructor.
   */
  private Lance(Sprite sprite, int posX, int posY, Image image) {
    super(sprite, posX, posY);
    this.setImage(image);

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
    BoundingBox pfbb = pf.getSprite().getBoundingBox();
    int posX = pfbb.getWidth() / 2;
    int posY = 3 * pfbb.getHeight() / 4;
    return new Lance(new Sprite(new Image("Lance.png"), new BoundingBox(posX,
        posY, (int) 1.5 * pfbb.getWidth(), (int) 0.25 * pfbb.getHeight())),
        posX, posY, new Image("Lance.png"));
  }

  /**
   * {@inheritDoc}.
   */
  public boolean intersect(Sprite other) {
    return super.getSprite().intersects(other);
  }

  // -----------Getters and setters----------------

  public void setImage(Image img) {
    this.image = img;

  }

  public Image getImage() {
    return this.image;
  }

}
