package test.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javafx.scene.image.Image;
import main.BoundingBox;
import main.Sprite;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * This the the JUnit test case for Sprite class, all the branches and methods are tested
 * except render method, because render method require an image, and even it was mocked,
 * it still cannot be tested.
 * @author Sunwei
 *
 */
public class SpriteTest {

	@Test
	public void testSetImg() {
		BoundingBox boundingBox = new BoundingBox(53, 129,67,2);		
		Image image1 = mock(Image.class);
		Sprite sprite = new Sprite(null, boundingBox);
		assertEquals(null, sprite.getImg());
		sprite.setImg(image1);
		assertEquals(image1, sprite.getImg());
	}
	@Test
	public void testGetImg() {
		Image image1 = mock(Image.class);
		BoundingBox boundingBox = new BoundingBox(53, 129,67,2);	
		Sprite sprite = new Sprite(image1, boundingBox);
		Image rightImg = sprite.getImg();
		assertEquals(image1, rightImg);
	}
	
	
	@Test
	public void testIntersectFalse() {
		BoundingBox boundingBox = new BoundingBox(53, 129,-2,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, boundingBox);
		BoundingBox aabb2 = new BoundingBox(100, 10,34,5);
		Sprite sprite2 = new Sprite(image1, aabb2);
		assertFalse(sprite1.intersects(sprite2));
	}

	@Test
	public void testIntersectTrue() {
		BoundingBox boundingBox = new BoundingBox(67, 129,1,2);
		BoundingBox aabb2 = boundingBox;
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, boundingBox);
		Sprite sprite2 = new Sprite(image1, aabb2);
		assertTrue(sprite1.intersects(sprite2));
	}
	
	@Test
	public void updateX() {
		BoundingBox boundingBox = new BoundingBox(53, 129,67,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, boundingBox);
		assertEquals(53,boundingBox.getX());
		sprite1.updateX(27);
		assertEquals(80,boundingBox.getX());
	}
	
	@Test
	public void updateY() {
		BoundingBox boundingBox = new BoundingBox(53, 129,67,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, boundingBox);
		assertEquals(129,boundingBox.getY());
		sprite1.updateY(27);
		assertEquals(156,boundingBox.getY());
	}
	
	/**
	 * The method was originaly in the sprite, but it is now moved, so the test method is gone.
	 */
/*	@Test
	public void grow() {
		AABB aabb = new AABB(10, 10,10,10);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		double multi = 10;
		assertEquals(10, aabb.getHeight());
		assertEquals(10, aabb.getWidth());
		
		sprite1.grow(multi);
		assertEquals(100, aabb.getHeight());
		assertEquals(100, aabb.getWidth());
	}*/
	
/*	@Test
	public void render() {
		AABB aabb = new AABB(10, 10,10,10);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		GraphicsContext gc = mock(GraphicsContext.class);
		sprite1.render(gc);
	}
*/
}
