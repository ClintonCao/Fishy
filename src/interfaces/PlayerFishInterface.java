package interfaces;

import main.EnemyFish;
import main.FishBomb;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * PlayerFish represents the fish controlled by the player in game. Both
 * conceptually and 'physically'.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 */
public interface PlayerFishInterface {

  /**
   * Grow the fish when it 'eats' another fish.
   * 
   * @param multiplier - the multiplier for the X and Y values.
   */
  void grow(double multiplier);

  /**
   * Check if the PlayerFish is smaller than the EnemyFish it's colliding with.
   * 
   * @param enemyfish.
   * @return true if the player is smaller.
   */
  boolean playerDies(EnemyFish enemyfish);

  /**
   * Tests intersection with the left screen edge.
   * 
   * @return true if intersect.
   */
  boolean intersectsLeftScreenEdge();

  /**
   * Tests intersection with the right screen edge.
   * 
   * @return true if intersect.
   */
  boolean intersectsRightScreenEdge();

  /**
   * Tests intersection with the upper screen edge.
   * 
   * @return true if intersect.
   */
  boolean intersectsUpperScreenEdge();

  /**
   * Tests intersection with the under screen edge.
   * 
   * @return true if intersect.
   */
  boolean intersectsUnderScreenEdge();

  // --- Getters and Setters ---

  /**
   * @return true if the player is alive.
   */
  boolean isAlive();

  /**
   * @param isAlive - the new alive state.
   */
  void setAlive(boolean isAlive);

  /**
   * Get the left image.
   * @return the PlayerFish' left Image.
   */
  Image getPlayerFishLeftImage();

  /**
   * @param playerFishLeftImage - the new Image.
   */
  void setPlayerFishLeftImage(Image playerFishLeftImage);

  /**
   * Get the right image.
   * @return the PlayerFish' right Image.
   */
  Image getPlayerFishRightImage();

  /**
   * @param playerFishRightImage - the new Image.
   */
  void setPlayerFishRightImage(Image playerFishRightImage);

  /**
   * @param number - the new Score.
   */
  void setScore(int number);

  /**
   * Get the score.
   * @return the score.
   */
  int getScore();

  /**
   * Get the list of items.
   * @return the ArrayList of items of the player.
   */
  ArrayList<FishBomb> getBombs();

  /**
   * @param items - the new ArrayList of FishBombs.
   */
  void setBombs(ArrayList<FishBomb> items);
}
