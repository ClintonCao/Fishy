package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javafx.scene.image.Image;
import main.AABB;
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
		AABB aabb = new AABB(53, 129,67,2);		
		Image image1 = mock(Image.class);
		Sprite sprite = new Sprite(null, aabb);
		assertEquals(null, sprite.getImg());
		sprite.setImg(image1);
		assertEquals(image1, sprite.getImg());
	}
	@Test
	public void testGetImg() {
		Image image1 = mock(Image.class);
		AABB aabb = new AABB(53, 129,67,2);	
		Sprite sprite = new Sprite(image1, aabb);
		Image rightImg = sprite.getImg();
		assertEquals(image1, rightImg);
	}
	
	
	@Test
	public void testIntersectFalse() {
		AABB aabb = new AABB(53, 129,-2,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		AABB aabb2 = new AABB(100, 10,34,5);
		Sprite sprite2 = new Sprite(image1, aabb2);
		assertFalse(sprite1.intersects(sprite2));
	}

	@Test
	public void testIntersectTrue() {
		AABB aabb = new AABB(67, 129,1,2);
		AABB aabb2 = aabb;
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		Sprite sprite2 = new Sprite(image1, aabb2);
		assertTrue(sprite1.intersects(sprite2));
	}
	
	@Test
	public void updateX() {
		AABB aabb = new AABB(53, 129,67,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		assertEquals(53,aabb.getX());
		sprite1.updateX(27);
		assertEquals(80,aabb.getX());
	}
	
	@Test
	public void updateY() {
		AABB aabb = new AABB(53, 129,67,2);
		Image image1 = mock(Image.class);
		Sprite sprite1 = new Sprite(image1, aabb);
		assertEquals(129,aabb.getY());
		sprite1.updateY(27);
		assertEquals(156,aabb.getY());
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
