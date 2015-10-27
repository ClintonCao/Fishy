package nl.tudelft.fishy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

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

}
