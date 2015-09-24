package test.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.logging.Handler;
import java.util.logging.StreamHandler;

import static org.mockito.Mockito.mock;
import main.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for the Logger class.
 * 
 * @author Group #6
 *
 */

public class LoggerTest {
  Logger logger = mock(Logger.class);
  private OutputStream logOut;
  private StreamHandler testLogHandler;
  

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
    logger.logIcon();
  }
  
  /**
   * This method is for testing logInit method.
   */
  @Test
  public void testLogInit() {

  }  

  /**
   * This method is for testing logInitSucceeded method.
   */
  @Test
  public void testlogInitSucceeded() {

  } 
  
  /**
   * This method is for testing logLoadingScreen method.
   */
  @Test
  public void testlogLoadingScreen() {

  }
  
  
}
