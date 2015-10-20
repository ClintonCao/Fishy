package nl.tudelft.fishy;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class item represents item in the game that the player can use.
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 */
public abstract class Item {
  private Sprite sprite;
  private int posX;
  private int posY;

  /**
   * Constructor.
   */
  public Item(Sprite sprite, int posX, int posY) {
    this.sprite = sprite;
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * Update the x-coordinate of the item.
   * @param mod the change for the x-coordinate.
   */
  public void updateX(int mod) {
    posX += mod;
  }

  /**
   * Update the y-coordinate of the item.
   * @param mod the change for the y-coordinate.
   */
  public void updateY(int mod) {
    posY += mod;
  }

  /**
   * Render the item.
   * @param gc the graphicsContext.
   */
  public void render(GraphicsContext gc) {
    gc.drawImage(this.getSprite().getImg(), posX, posY);
  }

  //---------------- Getter and setters--------------
  
  public void setSprite(Sprite sprite) {
    this.sprite = sprite;
  }

  public Sprite getSprite() {
    return this.sprite;
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
}
