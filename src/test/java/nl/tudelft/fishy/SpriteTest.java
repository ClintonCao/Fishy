package nl.tudelft.fishy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import javafx.scene.image.Image;

/**
 * This the the JUnit test case for Sprite class, all the branches and methods
 * are tested except render method, because render method require an image, and
 * even it was mocked, it still cannot be tested.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
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
   * Test that two different objects are not equal to each other.
   */
  @Test 
  public void testEqualsFalseWithWrongInstance() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Sprite sprite = new Sprite(image1, boundingBox);
    
    assertNotEquals(sprite,boundingBox);
  }
  
  /**
   * Test that two different objects are not equal to each other.
   */
  @Test 
  public void testEqualsFalseWithWrongImage() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    Image image1 = mock(Image.class);
    Image image = mock(Image.class);
    Sprite sprite = new Sprite(image1, boundingBox);
    Sprite sprite2 = new Sprite(image, boundingBox);
    
    assertNotEquals(sprite,sprite2);
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

}
