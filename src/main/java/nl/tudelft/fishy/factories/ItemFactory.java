package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.Item;
import nl.tudelft.fishy.Lance;
import nl.tudelft.fishy.Life;
import nl.tudelft.fishy.PlayerFish;


/**
 * This class creates instances of an Item,
 * right now only a FishBomb can be created.
 * 
 * @author Matthijs
 *
 */
public final class ItemFactory {
  
  private static ItemFactory itemFactory;
  
  /**
   * Basic Constructor.
   */
  private ItemFactory() {
    
  }

  /**
   * This method creates an instance of an item for the player.
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
  
}
