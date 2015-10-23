package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.EndBoss;
import nl.tudelft.fishy.EnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.Item;
import nl.tudelft.fishy.PlayerFish;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


/**
 * This class creates instances of an Entity, This can be either a PlayerFish or
 * an EnemyFish. The creation of the Entities is handled by the subclasses
 * itself.
 * 
 * @author Matthijs
 *
 */
public final class EntityFactory extends AbstractFactory {
  
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
      case "BOSS":
        return EndBoss.getSingletonEndBoss();
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

  @Override
  public AnimationTimer makeAnimationTimer(CompositeEnemyFish compositeEnemyFish) {
    return null;
  }

  @Override
  public Item createItem(String itemType, PlayerFish player) {
    return null;
  }

  @Override
  public EventHandler<MouseEvent> makeEventHandler(String buttonString) {
    return null;
  }
}