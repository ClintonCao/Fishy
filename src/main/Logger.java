package main;

/**
 * This class contains the logger, which will write specific events to console.
 * 
 * @author Group 6
 *
 */
public class Logger {
  private PlayerFish player;
  private BoundingBox box;

  /**
   * The class' constructor.
   * 
   * @param pf
   *          the Player Fish.
   * @param bb
   *          the Bounding box.
   */
  public Logger(PlayerFish pf, BoundingBox bb) {
    player = pf;
    box = bb;
  }

  /**
   * Changes the player fish in the logger.
   * 
   * @param pf
   *          The player fish that will replace the old value.
   */
  public void setPlayer(PlayerFish pf) {
    player = pf;
  }

  /**
   * Changes the bounding box in the logger.
   * 
   * @param bb
   *          The bounding box that will replace the old value.
   */
  public void setBoundingBox(BoundingBox bb) {
    box = bb;
  }

  /**
   * This method will write to console if the player hits the border of the
   * playing field.
   * 
   */
  public void logEdgeBump() {
    if (player.intersectsLeftScreenEdge()) {
      System.out.println("Player bumped with the left border of the screen.");
    }
    if (player.intersectsRightScreenEdge()) {
      System.out.println("Player bumped with the right border of the screen.");
    }
    if (player.intersectsUpperScreenEdge()) {
      System.out.println("Player bumped with the upper border of the screen.");
    }
    if (player.intersectsUnderScreenEdge()) {
      System.out.println("Player bumped with the lower border of the screen.");
    }
  }

  /**
   * This method logs the button pressed by the player to console.
   * 
   * @param key
   *          The button pressed.
   */
  public void logKeyPress(String key) {
    System.out.println("Key " + key + " has been pressed.");
  }
}
