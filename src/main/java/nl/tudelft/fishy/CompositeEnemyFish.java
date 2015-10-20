package nl.tudelft.fishy;
import java.util.ArrayList;

import nl.tudelft.fishy.controllers.MainScreenController;
import nl.tudelft.fishy.interfaces.Composition;
import  javafx.util.Pair;
import javafx.scene.canvas.GraphicsContext;

/**
 * CompositeEnemyFish represents a composition of Enemy Fish. It is an implementation
 * of the Composite design pattern. Objects can interact with CompositeEnemyFish as a single object.
 * Even though it is actually a list.
 * @author Michiel
 *
 */
public class CompositeEnemyFish implements Composition<EnemyFish> {
	
	private ArrayList<EnemyFish> fEnemyFishList = new ArrayList<EnemyFish>();
	
	/**
	 * Constructor.
	 */
	public CompositeEnemyFish() {
		
	}
	
	/**
	 * Removes all EnemyFish 'hit' by a FishBomb.
	 * Updates the score of the player for every 'hit' fish.
	 * @param aFishBomb
	 */
	public void handleFishBomb(FishBomb aFishBomb) {
		
		ArrayList<EnemyFish> fishToRemove = new ArrayList<EnemyFish>();
		
		for (EnemyFish currEnemyFish : fEnemyFishList) {
			
			BoundingBox currEnemyFishBoundingBox = currEnemyFish.getSprite().getBoundingBox();		
			
			if (aFishBomb.intersectsRectangle(currEnemyFishBoundingBox)) {	
				
				MainScreenController.updateScore(currEnemyFish);
				fishToRemove.add(currEnemyFish);
				
			}
		}
		
		for (EnemyFish currEnemyFish : fishToRemove) {
			fEnemyFishList.remove(currEnemyFish);
		}
	}
	
	/**
	 * Searches for an intersection between the EnemyFish and the parameter PlayerFish.
	 * If an intersection is found, a comparison is made ( Enemy Fish small than PlayerFish ? ).
	 * If it is, the player's score is updated.
	 * @param aPlayerFish 
	 * @return
	 * 					- The integer -1 indicates no intersection.
	 * 					- The boolean indicates if the player dies. (true is dead).
	 */
	public Pair<Integer, Boolean> intersectsPlayerFish(PlayerFish aPlayerFish) {
		for (int i = 0; i < fEnemyFishList.size(); i++) {
			Entity currEnemyFish = fEnemyFishList.get(i);
			if (aPlayerFish.intersects(currEnemyFish)) {
				int currEnemyFishSize = currEnemyFish.getSprite().getBoundingBox().getWidth();
				int aPlayerFishSize = aPlayerFish.getSprite().getBoundingBox().getWidth();
				if (currEnemyFishSize < aPlayerFishSize) {
					MainScreenController.updateScore(currEnemyFish);
					return new Pair<Integer, Boolean>(i, false);
				} else {
					return new Pair<Integer, Boolean>(i, true);
				}
			}
		}
		return new Pair<>(-1, false);
	}
	
	/**
	 * Removes fish that have moved outside a certain bounding box.
	 * @param aBoundingBox
	 * 											- the comparison bounding box.
	 */
	public void removeOffScreenEnemyFish(BoundingBox aBoundingBox) {
		for (int i = 0; i < fEnemyFishList.size(); i++) {
			EnemyFish currEnemyFish = fEnemyFishList.get(i);
			BoundingBox currEnemyFishBoundingBox = currEnemyFish.getSprite().getBoundingBox();
			if (!aBoundingBox.intersects(currEnemyFishBoundingBox)) {
				this.remove(i);
			}
		}
	}
	
	/**
	 * Renders all the fish to a particular GraphicsContext.
	 * @param aGraphicsContext 
	 * 													- GraphicsContext to render to.
	 */
	public void render(GraphicsContext aGraphicsContext) {
		for (EnemyFish currEnemyFish : fEnemyFishList) {
			currEnemyFish.getSprite().render(aGraphicsContext);
		}
	}
	
	/**
	 * Moves all the fish.
	 */
	public void move() {
		for (EnemyFish currEnemyFish : fEnemyFishList) {
			int movespeed = currEnemyFish.getMoveSpeed();
			if (currEnemyFish.isLefty()) {
				currEnemyFish.getSprite().updateX(movespeed);
			} else {
				currEnemyFish.getSprite().updateX(-movespeed);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void add(EnemyFish aEnemyFish) {
		fEnemyFishList.add(aEnemyFish);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void remove(EnemyFish aEnemyFish) {
		fEnemyFishList.remove(aEnemyFish);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void remove(int i) {
		fEnemyFishList.remove(i);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void clear() {
		fEnemyFishList.clear();
	}
	
}
