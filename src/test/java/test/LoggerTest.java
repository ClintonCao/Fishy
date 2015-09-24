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
}
