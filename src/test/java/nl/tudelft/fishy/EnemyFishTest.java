package nl.tudelft.fishy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import javafx.scene.image.Image;

import org.junit.Test;

/**
 * Test for the Entity class.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class EnemyFishTest {

  /**
   * Test the contructor.
   */
  @Test
  public void testEnemyFish() {
    int movespeed = 1;
    boolean isLefty = true;
    Sprite sprite = mock(Sprite.class);

    EnemyFish enemyFish = new EnemyFish(movespeed, isLefty, sprite);
    assertEquals(sprite, enemyFish.getSprite());
  }

  /**
   * The the method of isLefty.
   */
  @Test
  public void testSetLefty() {
    int movespeed = 1;
    boolean isLefty = true;
    Sprite sprite = mock(Sprite.class);

    EnemyFish enemyFish = new EnemyFish(movespeed, isLefty, sprite);
    assertTrue(enemyFish.isLefty());
    enemyFish.setLefty(false);
    assertFalse(enemyFish.isLefty());
  }

  /**
   * Test the method of isLefty with different parameters.
   */
  @Test
  public void testisLefty() {
    int movespeed = 2;
    boolean isLefty = true;
    Sprite sprite = mock(Sprite.class);
    EnemyFish enemyFish = new EnemyFish(movespeed, isLefty, sprite);
    assertTrue(enemyFish.isLefty());
  }

  /**
   * Test the method of setSprite.
   */
  @Test
  public void testSetSprite() { 
    Sprite sprite = mock(Sprite.class);
    int movespeed = 2;
    boolean isLefty = true;
    EnemyFish enemyFish = new EnemyFish(movespeed, isLefty, sprite);
    Sprite sprite1 = mock(Sprite.class);
    enemyFish.setSprite(sprite1);
    assertEquals(sprite1, enemyFish.getSprite());
  }
  
  /**
   * Test whether two entities can intersect with each other.
   */
  @Test
  public void testIntersect() {
    BoundingBox boundingBox = new BoundingBox(53, 129, 67, 2);
    BoundingBox aabb = boundingBox;
    Image image = mock(Image.class);
    Sprite sprite = new Sprite(image, boundingBox);
    Sprite sprite1 = new Sprite(image, aabb);
    int movespeed = 2;
    boolean isLefty = true;
    EnemyFish enemyFish = new EnemyFish(movespeed, isLefty, sprite); 
    int movespeed1 = 3;
    boolean isLefty1 = false;
    EnemyFish enemyFish1 =  new EnemyFish(movespeed1, isLefty1, sprite1);
    assertTrue(enemyFish.intersects(enemyFish1));
  }
}
