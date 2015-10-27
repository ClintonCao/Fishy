package nl.tudelft.fishy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

/**
 * This Test case will test the functionalities of the PlayerFish class.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public class PlayerFishTest {

  private Sprite sprite;
  private String leftimage;
  private String rightimage;
  private BoundingBox box;
  private PlayerFish playerFish;
  private FxRobot robot;

  /**
   * Set up a the bounding box and the sprite for the player fish.
   */
  @Before
  public void setUp() {
    box = new BoundingBox(100, 100, 50, 50);
    sprite = mock(Sprite.class);
    //new Sprite(null, box);
    leftimage = "/FishOriginal_transparent.png";
    rightimage = "/Fish_Right_Transparent.png";
    robot  = new FxRobot();
  }

  /**
   * Clean up the launcher.
   */
  @After
  public void tearDown() {
    Platform.exit();
  }

  /**
   * This Test checks the capability of changing whether the PlayerFish is alive
   * or not.
   */
  @Test
  public void testAlive() {

    PlayerFish player = PlayerFish.getSingletonFish();
    assertTrue(player.isAlive());
    player.setAlive(false);
    assertFalse(player.isAlive());
  }

  /**
   * This Test checks the capability of changing the PlayerFish's score.
   */
  @Test
  public void testScore() {

    PlayerFish player = PlayerFish.getSingletonFish();
    assertEquals(0, player.getScore());
    player.setScore(60);
    assertEquals(60, player.getScore());
  }

  /**
   * This Test checks the class's constructor.
   */
  @Test
  public void testConstructor() {

    PlayerFish player = PlayerFish.getSingletonFish();
    assertEquals(10, player.getMoveSpeed());
  }

  /**
   * This Test checks the ability to set and retrieve the images of a
   * PlayerFish.
   */
  @Test
  public void testImage() {
    PlayerFish player = PlayerFish.getSingletonFish();
    Image img = mock(Image.class);
    Image img2 = mock(Image.class);
    player.setPlayerFishLeftImage(img);
    player.setPlayerFishRightImage(img2);
    assertEquals(img, player.getPlayerFishLeftImage());
    assertEquals(img2, player.getPlayerFishRightImage());
  }

  /**
   * This Test checks the ability to set an image via a string.
   * @throws TimeoutException throws an exception when the it has timed out.
   */
  @Test
  public void testImageStringSet() throws TimeoutException {
    new JFXPanel();
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(Game.class, (java.lang.String[]) null);
    robot.closeCurrentWindow();
    PlayerFish player = PlayerFish.getSingletonFish();
    Image img = mock(Image.class);
    Image img2 = mock(Image.class);
    player.setPlayerFishLeftImage(img);
    player.setPlayerFishRightImage(img2);
    player.setPlayerFishLeftImageName("/FishOriginal_transparent.png");
    player.setPlayerFishRightImageName("/Fish_Right_Transparent.png");
    assertEquals(leftimage, PlayerFish.getPlayerFishLeftImageName());
    assertEquals(rightimage, PlayerFish.getPlayerFishRightImageName());
  }

}