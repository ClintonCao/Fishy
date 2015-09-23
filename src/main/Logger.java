package main;

/**
 * This class contains the logger, which will write specific events to console.
 * 
 * @author Group 6
 *
 */
public final class Logger {
 

  /**
   * This method will write to console if the player hits the border of the
   * playing field.
   * 
   */
  public void logEdgeBump(PlayerFish player) {
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
   * This method logs when the game's icon is being loaded.
   */
  public void logIcon() {
    System.out.println("Loading game's icon..");
  }
  
  /**
   * This method logs when game is being initialized.
   */
  public void logInit() {
    System.out.println("Initializing game objects..");
  }
  
  /**
   * This method logs when game is successfully initialized.
   */
  public void logInitSucceeded() {
    System.out.println("Successfully initialized..");
  }
  
  /**
   * This method logs the screen which the game is loading.
   * 
   * @param screen the screen the game is loading
   */
  public void logLoadingScreen(String screen) {
    System.out.println("Loading " + screen + "..");
  }
  
  /**
   * This method shows the message of successfully loaded.
   */
  public void logLoadSucceeded() {
    System.out.println("Successfully loaded..");
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
  
  /**
   * This method informs the player that the player fish has been eaten.
   * 
   */
  public void logPlayerFishDies() {
    System.out.println("You got eaten by a bigger fish..");
  }
  
  /**
   * This method informs the player when the player fish ate a smaller fish.
   * 
   * @param score indicates the score the player fish gained. 
   */
  public void logPlayerFishGrows(int score) {
    System.out.println("You just ate a smaller fish.");
    System.out.println("And you have just gained " + score + " points");
  }
  
  /**
   * This method indicates whether the music is on or off.
   * 
   * @param on boolean, if music is on, it will be true, vice versa.
   */
  public void logMusicOnOff(boolean on) {
    String text = "off";
    if (on) {
      text = "on";
    }
    System.out.println("The music is " + text);
  }
}