package test.java.test;

import static org.junit.Assert.*;
import main.AABB;

import org.junit.Test;

/**
 * This Test Case is used to check the functionalities of the Axis-Aligned Bounding Box (AABB).
 * @author Dmitry
 *
 */
public class AABBTest {
	/**
	 *This test controls the ability to set a X value to the AABB
	 * 
	 */
	@Test
	public void testSetX() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(53,aabb.getX());
		aabb.setX(1);
		assertEquals(1,aabb.getX());
	}
	/**
	 *This test controls the ability to set a Y value to the AABB
	 * 
	 */
	@Test
	public void testSetY() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(129,aabb.getY());
		aabb.setY(58);
		assertEquals(58,aabb.getY());
	}
	/**
	 *This test controls the ability to set a width value to the AABB
	 * 
	 */
	@Test
	public void testSetWidth() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(67,aabb.getWidth());
		aabb.setWidth(9);
		assertEquals(9,aabb.getWidth());
	}
	/**
	 *This test controls the ability to set a height value to the AABB
	 * 
	 */
	@Test
	public void testSetHeight() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(2,aabb.getHeight());
		aabb.setHeight(75);
		assertEquals(75,aabb.getHeight());
	}
	/**
	 *This test controls the ability to update the X value by adding the desired number to the old value.
	 * 
	 */
	@Test
	public void testUpdateX() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(53,aabb.getX());
		aabb.updateX(27);
		assertEquals(80,aabb.getX());
	}
	/**
	 *This test controls the ability to update the Y value by adding the desired number to the old value.
	 * 
	 */
	@Test
	public void testUpdateY() {
		AABB aabb = new AABB(53, 129,67,2);
		assertEquals(129,aabb.getY());
		aabb.updateY(51);
		assertEquals(180,aabb.getY());
	}
	/**
	 *The Following series of tests check the different conditions regarding the intersection of two AABBs.
	 * 
	 */
	@Test
	public void testIntersectFalse1() {
		AABB aabb = new AABB(53, 129,-2,2);
		AABB aabb2 = new AABB(100, 10,34,5);
		assertFalse(aabb.intersects(aabb2));
	}
	@Test
	public void testIntersectFalse2() {
		AABB aabb = new AABB(100, 129,62,2);
		AABB aabb2 = new AABB(3, 10,34,5);
		assertFalse(aabb.intersects(aabb2));
	}
	@Test
	public void testIntersectFalse3() {
		AABB aabb = new AABB(2, 129,62,2);
		AABB aabb2 = new AABB(2, 1000000,62,2);
		assertFalse(aabb.intersects(aabb2));
	}
	@Test
	public void testIntersectFalse4() {
		AABB aabb = new AABB(53, 129,67,-3);
		AABB aabb2 = new AABB(40, 10,34,5);
		assertFalse(aabb.intersects(aabb2));
	}
	@Test
	public void testIntersectTrue() {
		AABB aabb = new AABB(67, 129,1,2);
		AABB aabb2 = aabb;
		assertTrue(aabb.intersects(aabb2));
	}
}
