package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.Item;
import nl.tudelft.fishy.PlayerFish;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * An Abstract Factory class, created for the Abstract Factory Design Pattern.
 * It contains all functionality for all the different factories.
 * @author Matthijs
 *
 */
public abstract class AbstractFactory {
  
  /**
   * {@inheritDoc} Overrides the Handle method in AnimationTimer to contain the
   * game loop.
   * 
   * @return the new AnimationTimer.
   */
  abstract AnimationTimer makeAnimationTimer(CompositeEnemyFish compositeEnemyFish);
  
  /**
   * This method creates an entity that will be requested by the main.
   * 
   * @param entityType
   *          The entity requested.
   * @return The entity requested.
   */
  abstract Entity getEntity(String entityType);
  
  /**
   * This method creates an instance of an item for the player.
   * @param itemType
   *      The item created.
   * @param player
   *      The player who uses the item.
   * @return
   *      The instance of the item created.
   */
  abstract Item createItem(String itemType, PlayerFish player);
  
  /**
   * {@inheritDoc} Switch case.
   * 
   * @param buttonString
   *          - can be "playbutton", "menubutton" or "quitbutton".
   * @return the new EventHandler.
   */
  abstract EventHandler<MouseEvent> makeEventHandler(String buttonString);
}
