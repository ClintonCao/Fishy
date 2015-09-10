package main;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class PlayerFishTest {
	
	Sprite sprite;
	String leftimage = "FishOriginal_transparent.png"; 
	String rightimage = "Fish_Right_Transparent.png";
	AABB box;
	
	
	
	
	@Before
	public void setUp() {
		box = new AABB(100, 100, 50, 50);
		sprite = new Sprite(null, box);
		
	}
	
	@After
	public void tearDown() {
		Platform.exit();
	}

	@Test
	public void testAlive() {
		
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		assertTrue(player.isAlive());
		player.setAlive(false);
		assertFalse(player.isAlive());
	}
	@Test
	public void testScore() {
		
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		assertEquals(0,player.getScore());
		player.setScore(60);
		assertEquals(60,player.getScore());
	}
	@Test
	public void testConstructor() {
		
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		assertEquals(50,player.getMoveSpeed());
	}
	@Test
	public void testImageString() {
		
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		assertEquals(leftimage,player.getPlayerFishLeftImageName());
		assertEquals(rightimage,player.getPlayerFishRightImageName());
	}
	@Test
	public void testgrow() {
		
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		Image img = mock(Image.class);
		player.getSprite().setImg(img);
		player.grow(2);
		
		assertEquals(203,player.getSprite().getAabb().getWidth());
	}
	@Test
	public void testImage() {
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		Image img = mock(Image.class);
		Image img2 = mock(Image.class);
		player.setPlayerFishLeftImage(img);
		player.setPlayerFishRightImage(img2);
		assertEquals(img,player.getPlayerFishLeftImage());
		assertEquals(img2,player.getPlayerFishRightImage());
	}
	@Test
	public void testImageStringSet() {
		Game.main(null);
		PlayerFish player = new PlayerFish(50, true, leftimage, rightimage, sprite, 0);
		Image img = mock(Image.class);
		Image img2 = mock(Image.class);
		player.setPlayerFishLeftImage(img);
		player.setPlayerFishRightImage(img2);
		player.setPlayerFishLeftImageName(leftimage);
		player.setPlayerFishRightImageName(rightimage);
		assertEquals(leftimage,player.getPlayerFishLeftImageName());
		assertEquals(rightimage,player.getPlayerFishRightImageName());
	}

}
