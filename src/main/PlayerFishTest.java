package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * This Test case will test the functionalities of the PlayerFish class.
 * 
 * @author Dmitry
 *
 */
public class PlayerFishTest {

  Sprite sprite;
  String leftimage;
  String rightimage;
  BoundingBox box;

  /**
   * Set up a the bounding box and the sprite for the player fish.
   */
  @Before
  public void setUp() {
	box = new BoundingBox(100, 100, 50, 50);
    sprite = mock(Sprite.class);
    //new Sprite(null, box);
    leftimage = "FishOriginal_transparent.png";
    rightimage = "Fish_Right_Transparent.png";
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
   * 
   */
  @Test
  public void testAlive() {

    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    assertTrue(player.isAlive());
    player.setAlive(false);
    assertFalse(player.isAlive());
  }

  /**
   * This Test checks the capability of changing the PlayerFish's score.
   * 
   */
  @Test
  public void testScore() {

    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    assertEquals(0, player.getScore());
    player.setScore(60);
    assertEquals(60, player.getScore());
  }

  /**
   * This Test checks the class's constructor.
   * 
   */
  @Test
  public void testConstructor() {

    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    assertEquals(50, player.getMoveSpeed());
  }

  /**
   * This Test checks the ability to retrieve the PlayerFish's Image-string.
   * 
   */
  @Test
  public void testImageString() {

    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    assertEquals(leftimage, player.getPlayerFishLeftImageName());
    assertEquals(rightimage, player.getPlayerFishRightImageName());
  }

  /**
   * This Test checks the ability to set and retrieve the images of a
   * PlayerFish.
   * 
   */
  @Test
  public void testImage() {
    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    Image img = mock(Image.class);
    Image img2 = mock(Image.class);
    player.setPlayerFishLeftImage(img);
    player.setPlayerFishRightImage(img2);
    assertEquals(img, player.getPlayerFishLeftImage());
    assertEquals(img2, player.getPlayerFishRightImage());
  }

  /**
   * This Test checks the ability to set an image via a string.
   * 
   */
  @Test
  public void testImageStringSet() {
    Game.main(null);
    PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite,
        0);
    Image img = mock(Image.class);
    Image img2 = mock(Image.class);
    player.setPlayerFishLeftImage(img);
    player.setPlayerFishRightImage(img2);
    player.setPlayerFishLeftImageName("FishOriginal_transparent.png");
    player.setPlayerFishRightImageName("Fish_Right_Transparent.png");
    assertEquals(leftimage, player.getPlayerFishLeftImageName());
    assertEquals(rightimage, player.getPlayerFishRightImageName());
  }

}
