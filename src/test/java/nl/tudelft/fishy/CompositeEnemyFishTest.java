package nl.tudelft.fishy;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Non- GUI tests for CompositeEnemyFish.
 * @author Clinton
 *
 */
public class CompositeEnemyFishTest {
  
  private CompositeEnemyFish compEnemFish = new CompositeEnemyFish();
  private EnemyFish enemyFish = mock(EnemyFish.class);
  
  /**
   * Test the remove method for specific fish.
   */
  @Test
  public void testRemoveofSpecificFish() {
    compEnemFish.add(enemyFish);
    compEnemFish.remove(enemyFish);
    assertTrue(compEnemFish.getList().isEmpty());
  }
  
  /**
   * Test the remove method for a specific index.
   */
  @Test
  public void testRemoveofIndex() {
    compEnemFish.add(enemyFish);
    compEnemFish.remove(0);
    assertTrue(compEnemFish.getList().isEmpty());
  }
  
  /**
   * Test the clear method.
   */
  @Test
  public void testClear() {
    compEnemFish.add(enemyFish);
    compEnemFish.clear();
    assertTrue(compEnemFish.getList().isEmpty());
  }
  
  
}
