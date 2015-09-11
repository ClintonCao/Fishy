package test.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import main.BoundingBox;
import main.Entity;
import main.Game;
import main.Sprite;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.image.Image;

/**
 * This test case shall check the functionalities of the Entity class.
 * 
 * @author Matthijs
 *
 */
public class EntityTest {

  Sprite sprite;
  BoundingBox box;
  Image image = mock(Image.class);

  /**
   * Set up an object of the class.
   */
  @Before
  public void setUp() {
    box = new BoundingBox(100, 100, 50, 50);
    sprite = new Sprite(image, box);
  }

  /**
   * This test checks the Entity's constructor.
   * 
   */
  @Test
  public void constructorTest() {
    Entity entity = new Entity(0, sprite);
    assertNotNull(entity);
  }

  /**
   * This test checks the condition when two entities intersect.
   * 
   */
  @Test
  public void intersectsSuccesTest() {
    BoundingBox box2 = new BoundingBox(110, 110, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity1 = new Entity(0, sprite);
    Entity entity2 = new Entity(0, sprite2);

    assertTrue(entity1.intersects(entity2));
  }

  /**
   * This test checks the condition when two entities don't intersect.
   * 
   */
  @Test
  public void intersectsFailureTest() {
    BoundingBox box2 = new BoundingBox(160, 160, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity1 = new Entity(0, sprite);
    Entity entity2 = new Entity(0, sprite2);

    assertFalse(entity1.intersects(entity2));
  }

  /**
   * This test checks the condition when the entity intersects with the left
   * side of the screen.
   * 
   */
  @Test
  public void intersectsLeftScreenEdgeSuccesTest() {
    BoundingBox box2 = new BoundingBox(-10, 100, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity = new Entity(0, sprite2);

    assertTrue(entity.intersectsLeftScreenEdge());
  }

  /**
   * This test checks the condition when the entity does not intersect with the
   * left side of the screen.
   * 
   */
  @Test
  public void intersectsLeftScreenEdgeFailureTest() {
    Entity entity = new Entity(0, sprite);

    assertFalse(entity.intersectsLeftScreenEdge());
  }

  /**
   * This test checks the condition when the entity intersects with the right
   * side of the screen.
   * 
   */
  @Test
  public void intersectsRightScreenEdgeSuccesTest() {
    BoundingBox box2 = new BoundingBox(Game.getResX() + 10, 100, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity = new Entity(0, sprite2);

    assertTrue(entity.intersectsRightScreenEdge());
  }

  /**
   * This test checks the condition when the entity does not intersect with the
   * right side of the screen.
   * 
   */
  @Test
  public void intersectsRightScreenEdgeFailureTest() {
    Entity entity = new Entity(0, sprite);

    assertFalse(entity.intersectsRightScreenEdge());
  }

  /**
   * This test checks the condition when the entity intersects with the upper
   * side of the screen.
   * 
   */
  @Test
  public void intersectsUpperScreenEdgeSuccesTest() {
    BoundingBox box2 = new BoundingBox(100, -10, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity = new Entity(0, sprite2);

    assertTrue(entity.intersectsUpperScreenEdge());
  }

  /**
   * This test checks the condition when the entity does not intersect with the
   * upper side of the screen.
   * 
   */
  @Test
  public void intersectsUpperScreenEdgeFailureTest() {
    Entity entity = new Entity(0, sprite);

    assertFalse(entity.intersectsUpperScreenEdge());
  }

  /**
   * This test checks the condition when the entity intersecs with the lower
   * side of the screen.
   * 
   */
  @Test
  public void intersectsUnderScreenEdgeSuccesTest() {
    BoundingBox box2 = new BoundingBox(10, Game.getResY() + 10, 50, 50);
    Sprite sprite2 = new Sprite(null, box2);
    Entity entity = new Entity(0, sprite2);

    assertTrue(entity.intersectsUnderScreenEdge());
  }

  /**
   * This test checks the condition when the entity does not intersect with the
   * lower side of the screen.
   * 
   */
  @Test
  public void intersectsUnderScreenEdgeFailureTest() {
    Entity entity = new Entity(0, sprite);

    assertFalse(entity.intersectsUnderScreenEdge());
  }

  /**
   * Test the getSprite function.
   */
  @Test
  public void getSpriteTest() {
    Entity entity = new Entity(0, sprite);
    Sprite sprite2 = entity.getSprite();

    assertEquals(sprite, sprite2);
  }

  /**
   * Tests the setSprite function.
   */
  @Test
  public void setSpriteTest() {
    BoundingBox box2 = new BoundingBox(100, -10, 50, 50);
    Sprite sprite1 = new Sprite(null, box2);
    Entity entity = new Entity(0, sprite1);

    entity.setSprite(sprite);
    Sprite sprite2 = entity.getSprite();

    assertEquals(sprite, sprite2);
  }

  /**
   * Tests the getMoveSpeed function.
   */
  @Test
  public void getMoveSpeedTest() {
    int speed1 = 10;
    Entity entity = new Entity(speed1, sprite);

    int speed2 = entity.getMoveSpeed();

    assertEquals(speed1, speed2);
  }

  /**
   * Tests the setMoveSpeed function.
   */
  @Test
  public void setMoveSpeedTest() {
    int speed1 = 10;
    Entity entity = new Entity(0, sprite);

    entity.setMoveSpeed(speed1);
    int speed2 = entity.getMoveSpeed();

    assertEquals(speed1, speed2);
  }

}
