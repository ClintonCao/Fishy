package test.java.test;

import static org.junit.Assert.assertEquals;

import main.Logger;



import org.junit.Test;

public class LoggerTest {
  Logger logger = new Logger();

/**
   * This method is for testing logEdgeBump method.
   */
  @Test
  public void testlogEdgeBump() {

  }
  
  /**
   * This method is for testing logIcon method.
   */
  @Test
  public void testLogIcon() {
    String expected = "Loading game's icon..";
    logger.logIcon();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }
  
  /**
   * This method is for testing logInit method.
   */
  @Test
  public void testLogInit() {
    String expected = "Initializing game objects..";
    logger.logInit();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }  
  
  /**
   * This method is for testing logInitSucceeded method.
   */
  @Test
  public void testlogInitSucceeded() {
    String expected = "Successfully initialized..";
    logger.logInitSucceeded();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  } 
  
  /**
   * This method is for testing logLoadingScreen method.
   */
  @Test
  public void testlogLoadingScreen() {
    String screen = "MainScreen";
    String expected = "Loading " + screen + "..";
    logger.logLoadingScreen(screen);
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }
  
  /**
   * This method is for testing logLoadSucceeded method.
   */
  @Test
  public void testlogLoadSucceeded() {
    String expected = "Successfully loaded..";
    logger.logLoadSucceeded();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  } 
  
  /**
   * This method is for testing logKeyPress method.
   */
  @Test
  public void testlogKeyPress() {
    String key = "W";
    String expected = "Key " + key + " has been pressed.";
    logger.logKeyPress(key);
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }
  
  /**
   * This method is for testing logNewScore method.
   */
  @Test
  public void testlogNewScore() {
    int score = 1;
    String expected = "Player's score increased to: " + score;
    logger.logNewScore(score);
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }
  
  /**
   * This method is for testing logStartGame method.
   */
  @Test
  public void testlogStartGame() {
    String expected = "The game has started running.";
    logger.logStartGame();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  } 
  
  /**
   * This method is for testing logEndGame() method.
   */
  @Test
  public void testlogEndGame() {
    String expected = "The game is shutting down.";
    logger.logEndGame();
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  } 
  
  /**
   * This method is for testing logSwitchScreen() method.
   */
  @Test
  public void testlogSwitchScreen() {
    String screen = "MainScreen";
    String expected = "The game has switched to the " + screen + " screen.";
    logger.logSwitchScreen(screen);
    String actual = logger.getCurrentLog();
    assertEquals(expected, actual);
  }
  
  
}