package test.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import main.BoundingBox;

import org.junit.Test;

/**
 * This Test Case is used to check the functionalities of the Axis-Aligned
 * Bounding Box.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class BoundingBoxTest {
  /**
   * This test controls the ability to set a X value to the AABB.
   * 
   */
  @Test
  public void testSetX() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(53, boundingBox.getX());
    boundingBox.setX(1);
    assertEquals(1, boundingBox.getX());
  }

  /**
   * This test controls the ability to set a Y value to the AABB.
   * 
   */
  @Test
  public void testSetY() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(129, boundingBox.getY());
    boundingBox.setY(58);
    assertEquals(58, boundingBox.getY());
  }

  /**
   * This test controls the ability to set a width value to the AABB.
   * 
   */
  @Test
  public void testSetWidth() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(67, boundingBox.getWidth());
    boundingBox.setWidth(9);
    assertEquals(9, boundingBox.getWidth());
  }

  /**
   * This test controls the ability to set a height value to the AABB.
   * 
   */
  @Test
  public void testSetHeight() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(2, boundingBox.getHeight());
    boundingBox.setHeight(75);
    assertEquals(75, boundingBox.getHeight());
  }

  /**
   * This test controls the ability to update the X value by adding the desired
   * number to the old value.
   * 
   */
  @Test
  public void testUpdateX() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(53, boundingBox.getX());
    boundingBox.updateX(27);
    assertEquals(80, boundingBox.getX());
  }

  /**
   * This test controls the ability to update the Y value by adding the desired
   * number to the old value.
   * 
   */
  @Test
  public void testUpdateY() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    assertEquals(129, boundingBox.getY());
    boundingBox.updateY(51);
    assertEquals(180, boundingBox.getY());
  }

  /**
   * Checks the different conditions regarding the intersection of two bounding
   * boxes.
   * 
   */
  @Test
  public void testIntersectFalse1() {
    BoundingBox boundingBox = new BoundingBox(53, 129, -2, 2);
    BoundingBox aabb2 = new BoundingBox(100, 10, 34, 5);
    assertFalse(boundingBox.intersects(aabb2));
  }

  /**
   * Checks the different conditions regarding the intersection of two bounding
   * boxes.
   */
  @Test
  public void testIntersectFalse2() {
    BoundingBox boundingBox = new BoundingBox(100, 129, 62, 2);
    BoundingBox aabb2 = new BoundingBox(3, 10, 34, 5);
    assertFalse(boundingBox.intersects(aabb2));
  }

  /**
   * Checks the different conditions regarding the intersection of two bounding
   * boxes.
   */
  @Test
  public void testIntersectFalse3() {
    BoundingBox boundingBox = new BoundingBox(2, 129, 62, 2);
    BoundingBox aabb2 = new BoundingBox(2, 1000000, 62, 2);
    assertFalse(boundingBox.intersects(aabb2));
  }

  /**
   * Checks the different conditions regarding the intersection of two bounding
   * boxes.
   */
  @Test
  public void testIntersectFalse4() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, -3);
    BoundingBox aabb2 = new BoundingBox(40, 10, 34, 5);
    assertFalse(boundingBox.intersects(aabb2));
  }

  /**
   * Checks the different conditions regarding the intersection of two bounding
   * boxes.
   */
  @Test
  public void testIntersectTrue() {
    BoundingBox boundingBox = new BoundingBox(67, 129, 1, 2);
    BoundingBox aabb2 = boundingBox;
    assertTrue(boundingBox.intersects(aabb2));
  }

  /**
   * Checks if two identical Bounding Boxes are considered equal.
   */
  @Test
  public void testEqualsTrue() {
    BoundingBox boundingBox1 = new BoundingBox(67, 129, 1, 2);
    BoundingBox boundingBox2 = new BoundingBox(67, 129, 1, 2);

    assertEquals(boundingBox1, boundingBox2);

  }

  /**
   * Checks if two different Bounding Boxes are considered different.
   */
  @Test
  public void testEqualsFalse() {
    BoundingBox boundingBox1 = new BoundingBox(67, 129, 1, 2);
    BoundingBox boundingBox2 = new BoundingBox(10, 10, 10, 10);

    assertFalse(boundingBox1.equals(boundingBox2));

  }
}
