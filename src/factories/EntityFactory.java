package factories;

import main.EnemyFish;
import main.Entity;
import main.PlayerFish;

/**
 * This class creates instances of an Entity, This can be either a PlayerFish or
 * an EnemyFish. The creation of the Entities is handled by the subclasses
 * itself.
 * 
 * @author Matthijs
 *
 */
public final class EntityFactory {
  
  /**
   * The constructor.
   */
  private EntityFactory() {
    
  }
  
  private static EntityFactory entityFactory;

  /**
   * This method creates an entity that will be requested by the main.
   * 
   * @param entityType
   *          The entity requested.
   * @return The entity requested.
   */
  public Entity getEntity(String entityType) {

    switch (entityType.toUpperCase()) {
      case "PLAYER":
        return PlayerFish.getSingletonFish();
      case "ENEMY":
        return EnemyFish.generateFish();
      default:
        return null;
    }
  }
  
  /**
   * Basic getter.
   * @return The singleton EntityFactory
   */
  public static synchronized EntityFactory getEntityFactory() {
    
    if (entityFactory == null) {
      entityFactory = new EntityFactory();
    }
    
    return entityFactory;
  }
}