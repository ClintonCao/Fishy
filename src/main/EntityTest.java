package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntityTest {
	
	Sprite sprite;
	AABB box;
	
	@Before
	public void setUp() {
		box = new AABB(100, 100, 50, 50);
		sprite = new Sprite(null, box);
	}

	@Test
	public void constructorTest() {
		Entity entity = new Entity(0, sprite);
		assertNotNull(entity);
	}
	
	@Test
	public void intersectsSuccesTest() {
		AABB box2 = new AABB(110, 110, 50, 50);
		Sprite sprite2 = new Sprite(null, box2);
		Entity entity1 = new Entity(0, sprite);
		Entity entity2 = new Entity(0, sprite2);
		
		assertTrue(entity1.intersects(entity2));
	}
	
	@Test
	public void intersectsFailureTest() {
		AABB box2 = new AABB(160, 160, 50, 50);
		Sprite sprite2 = new Sprite(null, box2);
		Entity entity1 = new Entity(0, sprite);
		Entity entity2 = new Entity(0, sprite2);
		
		assertFalse(entity1.intersects(entity2));
	}
	
	@Test
	public void intersectsLeftScreenEdgeSuccesTest() {
		AABB box2 = new AABB(-10, 100, 50, 50);
		Sprite sprite2 = new Sprite(null, box2);
		Entity entity = new Entity(0, sprite2);
		
		assertTrue(entity.intersectsLeftScreenEdge());
	}
	
	@Test
	public void intersectsLeftScreenEdgeFailureTest() {
		Entity entity = new Entity(0, sprite);
		
		assertFalse(entity.intersectsLeftScreenEdge());
	}
	
	@Test
	public void intersectsRightScreenEdgeSuccesTest() {
		AABB box2 = new AABB(-10, 100, 50, 50);
		Sprite sprite2 = new Sprite(null, box2);
		Entity entity = new Entity(0, sprite2);
	}

}
