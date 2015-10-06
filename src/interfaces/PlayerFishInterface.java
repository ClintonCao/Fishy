package interfaces;

import java.util.ArrayList;

import javafx.scene.image.Image;
import main.EnemyFish;
import main.FishBomb;

/**
 * PlayerFish represents the fish controlled by the player in game. Both conceptually and 'physically'.
 * @author Michiel
 */
public interface PlayerFishInterface {
	  
	  /**
	   * Grow the fish when it 'eats' another fish.
	   * 
	   * @param multiplier - the multiplier for the X and Y values.
	   */
	  public void grow(double multiplier);

	  /**
	   * Check if the PlayerFish is smaller than the EnemyFish it's colliding with.
	   * 
	   * @param enemyfish.
	   * @return true if the player is smaller.
	   */
	  public boolean playerDies(EnemyFish enemyfish);
	  
// --- Getters and Setters --- 

	  /**
	   * @return true if the player is alive.
	   */
	  public boolean isAlive();

	  /**
	   * @param isAlive - the new alive state. 
	   */
	  public void setAlive(boolean isAlive);
	  
	  /**
	   * @return the PlayerFish' left Image.
	   */
	  public Image getPlayerFishLeftImage();

	  /**
	   * @param playerFishLeftImage - the new Image.
	   */
	  public void setPlayerFishLeftImage(Image playerFishLeftImage);

	  /**
	   * @return the PlayerFish' right Image.
	   */
	  public Image getPlayerFishRightImage();
	  
	  /**
	   * @param playerFishRightImage - the new Image.
	   */
	  public void setPlayerFishRightImage(Image playerFishRightImage);

	  /**
	   * @param number - the new Score.
	   */
	  public void setScore(int number);
	  
	  /**
	   * @return the score.
	   */
	  public int getScore();
	  
	  /**
	   * @return the ArrayList of items of the player.
	   */
	  public ArrayList<FishBomb> getItems();
	  
	  /**
	   * @param items - the new ArrayList of FishBombs.
	   */
	  public void setItems(ArrayList<FishBomb> items);
}
