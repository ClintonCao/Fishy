package factories;

import main.FishBomb;
import main.PlayerFish;

/**
 * This class creates instances of an Item,
 * right now only a FishBomb can be created.
 * 
 * @author Matthijs
 *
 */
public class ItemFactory {

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
  public FishBomb createItem(String itemType, PlayerFish player) {
    
    switch (itemType.toUpperCase()) {
      case "FISHBOMB": 
        return FishBomb.createFishBomb(player);
      default:
        return null;
    }
  
  }
  
}
