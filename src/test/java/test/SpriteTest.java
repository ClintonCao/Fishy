package test.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import main.BoundingBox;
import main.Sprite;

import org.junit.Test;

import javafx.scene.image.Image;

/**
 * This the the JUnit test case for Sprite class, all the branches and methods
 * are tested except render method, because render method require an image, and
 * even it was mocked, it still cannot be tested.
 * 
 * @author Sunwei
 *
 */
public class SpriteTest {

  /**
   * Test the setImg method.
   */
  @Test
  public void testSetImg() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Sprite sprite = new Sprite(null, boundingBox);
    assertEquals(null, sprite.getImg());
    sprite.setImg(image1);
    assertEquals(image1, sprite.getImg());
  }

  /**
   * Test the getImg method.
   */
  @Test
  public void testGetImg() {
    Image image1 = mock(Image.class);
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Sprite sprite = new Sprite(image1, boundingBox);
    Image rightImg = sprite.getImg();
    assertEquals(image1, rightImg);
  }

  /**
   * Test the intersectFalse method.
   */
  @Test
  public void testIntersectFalse() {
    BoundingBox boundingBox = new BoundingBox(53, 129, -2, 2);
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox);
    BoundingBox aabb2 = new BoundingBox(100, 10, 34, 5);
    Sprite sprite2 = new Sprite(image1, aabb2);
    assertFalse(sprite1.intersects(sprite2));
  }

  /**
   * Test the intersectTrue method.
   */
  @Test
  public void testIntersectTrue() {
    BoundingBox boundingBox = new BoundingBox(67, 129, 1, 2);
    BoundingBox aabb2 = boundingBox;
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox);
    Sprite sprite2 = new Sprite(image1, aabb2);
    assertTrue(sprite1.intersects(sprite2));
  }

  /**
   * Test the updateX method.
   */
  @Test
  public void updateX() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox);
    assertEquals(53, boundingBox.getX());
    sprite1.updateX(27);
    assertEquals(80, boundingBox.getX());
  }

  /**
   * Test the updateY method.
   */
  @Test
  public void updateY() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox);
    assertEquals(129, boundingBox.getY());
    sprite1.updateY(27);
    assertEquals(156, boundingBox.getY());
  }

  /**
   * Tests if two identical Sprites are considered equal.
   */
  @Test
  public void testEqualsTrue() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox);
    Sprite sprite2 = new Sprite(image1, boundingBox);

    assertEquals(sprite1, sprite2);
  }

  /**
   * Tests if two different sprites are not considered equal.
   */
  @Test
  public void testEqualsFalse() {
    BoundingBox boundingBox1 = new BoundingBox(53, 129, 67, 2);
    BoundingBox boundingBox2 = new BoundingBox(10, 10, 10, 10);
    Image image1 = mock(Image.class);
    Sprite sprite1 = new Sprite(image1, boundingBox1);
    Sprite sprite2 = new Sprite(image1, boundingBox2);

    assertFalse(sprite1.equals(sprite2));
  }

  /**
   * The method was originaly in the sprite, but it is now moved, so the test
   * method is gone.
   */
  /*
   * @Test public void grow() { AABB aabb = new AABB(10, 10,10,10); Image image1
   * = mock(Image.class); Sprite sprite1 = new Sprite(image1, aabb); double
   * multi = 10; assertEquals(10, aabb.getHeight()); assertEquals(10,
   * aabb.getWidth());
   * 
   * sprite1.grow(multi); assertEquals(100, aabb.getHeight()); assertEquals(100,
   * aabb.getWidth()); }
   */

  /*
   * @Test public void render() { AABB aabb = new AABB(10, 10,10,10); Image
   * image1 = mock(Image.class); Sprite sprite1 = new Sprite(image1, aabb);
   * GraphicsContext gc = mock(GraphicsContext.class); sprite1.render(gc); }
   */
}
