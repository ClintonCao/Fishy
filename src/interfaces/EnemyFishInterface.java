package interfaces;

/**
 * An EnemyFish is a fish which is not the PlayerFish.
 * 
 * @author Michiel
 */
public interface EnemyFishInterface {

	/**
	 * @return true - if the fish spawns at the left side of the screen,
	 * and moves to the right.
	 */
	public boolean isLefty();

	/**
	 * @param isLefty.
	 */
	public void setLefty(boolean isLefty);
}
