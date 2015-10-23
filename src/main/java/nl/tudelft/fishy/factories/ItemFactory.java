package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.Item;
import nl.tudelft.fishy.Lance;
import nl.tudelft.fishy.Life;
import nl.tudelft.fishy.PlayerFish;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


/**
 * This class creates instances of an Item,
 * right now only a FishBomb can be created.
 * 
 * @author Matthijs
 *
 */
public final class ItemFactory extends AbstractFactory {
  
  private static ItemFactory itemFactory;
  
  /**
   * Basic Constructor.
   */
  private ItemFactory() {
    
  }

  /**
   * This method creates an instance of an item for the player.
   * As of this moment the only item in the game is a FishBomb,
   * and their is no item superclass.
   * This is why the method gives back a FishBomb instead of a generic item class.
   * @param itemType
   *      The item created.
   * @param player
   *      The player who uses the item.
   * @return
   *      The instance of the item created.
   */
  public Item createItem(String itemType, PlayerFish player) {
    
    switch (itemType.toUpperCase()) {
      case "FISHBOMB": 
        return FishBomb.createFishBomb(player);
      case "LANCE":
        return Lance.getSingletonLance();
      case "LIFE":
        return Life.getSingletonLife();  
      default:
        return null;
    }
  
  }
  
  /**
   * Basic getter.
   * @return The singleton ItemFactory.
   */
  public static synchronized ItemFactory getItemFactory() {
    
    if (itemFactory == null) {
      itemFactory = new ItemFactory();
    }
    
    return itemFactory;
  }

  @Override
  public AnimationTimer makeAnimationTimer(CompositeEnemyFish compositeEnemyFish) {
    return null;
  }

  @Override
  public Entity getEntity(String entityType) {
    return null;
  }

  @Override
  public EventHandler<MouseEvent> makeEventHandler(String buttonString) {
    return null;
  }
  
}
