package nl.tudelft.fishy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeoutException;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.KeyCode;

/**
 * This Test case will test the functionalities of the PlayerFish class.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
@SuppressWarnings("PMD")
public class FishySmokeTest {

  private FxRobot robot;

  /**
   * Set up the robot.
   * @throws TimeoutException throws an exception if a timeout occurs.
   */
  @Before
  public void setUp() throws TimeoutException {
    robot  = new FxRobot();
    new JFXPanel();
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(Game.class, (java.lang.String[]) null);
  }

  /**
   * Clean up the launcher.
   */
  @After
  public void tearDown() {
    Platform.exit();
  }



  /**
   * This Test checks the ability to set an image via a string.
   * @throws InterruptedException throws an exception if the thread is interrupted.
   */
  @Test
  public void smokeTest() throws InterruptedException {

    // Click on the menu button.
    robot.clickOn("#MenuButton");

    // Click on the on button. And check
    // that the music is turned on.
    robot.clickOn("#OnButton");
    assertTrue(Game.getMusicOn());

    // Click on the on button. And check
    // that the music is turned off.
    robot.clickOn("#OffButton");
    assertFalse(Game.getMusicOn());

    // Click on the back button;
    robot.clickOn("#BackButton");

    // Start the game.
    robot.clickOn("#PlayButton");

    // Check some basic properties of the playerFish.
    assertTrue(PlayerFish.getSingletonFish().isAlive());
    assertEquals(0, PlayerFish.getSingletonFish().getScore());
    assertEquals(1, PlayerFish.getSingletonFish().getLives());
    assertFalse(PlayerFish.getSingletonFish().hasLance());
    String leftImageName = "/FishOriginal_transparent.png";
    String rightImageName = "/Fish_Right_Transparent.png";
    assertEquals(leftImageName, PlayerFish.getPlayerFishLeftImageName());
    assertEquals(rightImageName, PlayerFish.getPlayerFishRightImageName());
    
    // increment lives and check that it works.
    PlayerFish.getSingletonFish().incrementLives();
    assertEquals(2, PlayerFish.getSingletonFish().getLives());
    
    // decrement lives and check that it works.
    PlayerFish.getSingletonFish().decrementLives();
    assertEquals(1, PlayerFish.getSingletonFish().getLives());
    
    // Use bomb and check that the player does not have any bombs left.
    robot.press(KeyCode.X);
    robot.release(KeyCode.X);
    assertEquals(0, PlayerFish.getSingletonFish().getBombs().size());
    
    // Move the player up and check that the player did actually move.
    Sprite playerSprite = PlayerFish.getSingletonFish().getSprite();
    BoundingBox box = playerSprite.getBoundingBox();
    int oldYposition = box.getY();
    
    robot.press(KeyCode.W);
    robot.release(KeyCode.W);

    int newYposition = box.getY();
    assertNotEquals(oldYposition, newYposition);

    int oldXposition = box.getX();
    robot.press(KeyCode.A);
    robot.release(KeyCode.A);
    int newXposition = box.getX();
    assertNotEquals(newXposition, oldXposition);

    // Move around with the other keys.
    robot.press(KeyCode.D);
    robot.release(KeyCode.D);
    robot.press(KeyCode.S);
    robot.release(KeyCode.S);
    Thread.sleep(200L);

    // Close the current window to stop all the rendering and game.
    robot.closeCurrentWindow();

  }

}
