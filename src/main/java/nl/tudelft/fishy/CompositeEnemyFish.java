package nl.tudelft.fishy;
import java.util.ArrayList;

import nl.tudelft.fishy.controllers.MainScreenController;
import  javafx.util.Pair;
import javafx.scene.canvas.GraphicsContext;

public class CompositeEnemyFish {
	private ArrayList<EnemyFish> fEnemyFishList = new ArrayList<EnemyFish>();
	
	public CompositeEnemyFish() {
		
	}
	
	public int intersects(Entity aEntity) {
		for (int i = 0; i < fEnemyFishList.size(); i++) {
			EnemyFish currEnemyFish = fEnemyFishList.get(i);
			if (aEntity.intersects(currEnemyFish)) {
				return i;
			}
		}
		return -1;
	}
	
	public Pair<Integer, Boolean> intersectsPlayerFish(PlayerFish aPlayerFish) {
		for (int i = 0; i < fEnemyFishList.size(); i++) {
			EnemyFish currEnemyFish = fEnemyFishList.get(i);
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
	
	public void removeOffScreenEnemyFish(BoundingBox aBoundingBox) {
		for (int i = 0; i < fEnemyFishList.size(); i++) {
			EnemyFish currEnemyFish = fEnemyFishList.get(i);
			BoundingBox currEnemyFishBoundingBox = currEnemyFish.getSprite().getBoundingBox();
			if (!aBoundingBox.intersects(currEnemyFishBoundingBox)) {
				this.remove(i);
			}
		}
	}
	
	public void render(GraphicsContext aGraphicsContext) {
		for (EnemyFish currEnemyFish : fEnemyFishList) {
			currEnemyFish.getSprite().render(aGraphicsContext);
		}
	}
	
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
	
	public void add(EnemyFish aEnemyFish) {
		fEnemyFishList.add(aEnemyFish);
	}
	
	public void remove(EnemyFish aEnemyFish) {
		fEnemyFishList.remove(aEnemyFish);
	}
	
	public void remove(int i) {
		fEnemyFishList.remove(i);
	}
	
	public void clear() {
		fEnemyFishList.clear();
	}
}
