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
  
  /**
   * This method logs the player's score to the console.
   * 
   * @param score
   *          The new score.
   */
  public void logNewScore(int score) {
    System.out.println("Player's score increased to: " + score);
  }
  
 /**
  * This method logs the game starting up.
  */
  public void logStartGame() {
    System.out.println("The game has started running.");
  }
  
  /**
   * This method logs the game shutting down.
   */
  public void logEndGame() {
    System.out.println("The game is shutting down.");
  }
  
  /**
   * This method logs the screen that the game has changed to.
   * 
   * @param screenName
   *            The name of the screen the game has changed to.
   */
  public void logSwitchScreen(String screenName) {
    System.out.println("The game has switched to the " + screenName + " screen.");
  }
  
  /**
   * This method logs the end result of the game.
   * 
   * @param result
   *            The result of the game. 
   * @param score
   *            The final score.
   */
  public void logGameResult(String result, int score) {
    System.out.println("The player has " + result + " the game, with an end score of: " + score);
  }
  
  /**
   * This method logs the movement of the player.
   * 
   * @param direction
   *            The direction in which the player is moving.
   */
  public void logDirectionChange(String direction) {
    System.out.println("The player has started moving " + direction + ".");
  }
}