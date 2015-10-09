package factories;

import main.EnemyFish;
import main.Entity;
import main.PlayerFish;

/**
 * This class creates instances of an Entity,
 * This can be either a PlayerFish or an EnemyFish.
 * The creation of the Entities is handled by the subclasses itself.
 * @author Matthijs
 *
 */
public class EntityFactory {

  /**
   * This method returns an entity that will be requested by the main.
   * @param entityType
   *        The entity requested.
   * @return
   *        The entity requested.
   */
  public Entity getEntity(String entityType) {
    if (entityType == null) {
      return null;
    }
    if (entityType.equals("PLAYER")) {
      return PlayerFish.getSingletonFish();
    }
    if (entityType.equals("ENEMY")) {
      return EnemyFish.generateFish();
    }
    
    return null;
  }
}
