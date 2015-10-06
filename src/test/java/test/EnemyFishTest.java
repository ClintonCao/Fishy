package test.java.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import main.EnemyFish;
import main.Sprite;

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
    assertEquals(true, enemyFish.isLefty());
    enemyFish.setLefty(false);
    assertEquals(false, enemyFish.isLefty());
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
    assertEquals(true, enemyFish.isLefty());
  }

  // This method is originally used to test generateFish method,
  // however, there is internal graphic not initialized error.
  /*
   * @Test public void testgenerateFish1() { EnemyFish EF1 =
   * EnemyFish.generateFish(); int moveS = EF1.getMoveSpeed(); moveS = moveS +
   * 10; EF1.setMoveSpeed(moveS + 10); assertEquals(moveS, EF1.getMoveSpeed());
   * }
   */

  // another approach, however, this one needs to change the the generateFish
  // method
  // in EnemyFish class to not static, and will affect the
  // mainScreenController
  /*
   * @Test public void testgenerateFish1() { EnemyFish EF1 =
   * mock(EnemyFish.class); EF1.setLefty(true); leftImageFileName =
   * "EnemyFish_Left.png"; rightImageFileName = "EnemyFish_Right.png";
   * EF1.generateFish(); int moveS = EF1.getMoveSpeed(); moveS = moveS + 10;
   * EF1.setMoveSpeed(moveS + 10); assertEquals(moveS, EF1.getMoveSpeed()); }
   */

}
