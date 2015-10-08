package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class AnimationTimerFactory {
	private static final AnimationTimerFactory animationTimerFactory = new AnimationTimerFactory();

	private AnimationTimerFactory() {

	}

	public static AnimationTimerFactory getAnimationTimerFactory() {
		return animationTimerFactory;
	}
	
	public AnimationTimer makeAnimationTimer(GraphicsContext gc) {
		return new AnimationTimer() {
			public void handle(long currentNTime) {

				if (MainScreenController.playerHasWon()) {
					this.stop();
					Game.switchScreen("FXML/WinningScreen.fxml");
					Game.mediaPlayer.stop();
					Game.getLogger().logSwitchScreen("WinningScreen");
				}

				if (MainScreenController.currScore > 500 && !MainScreenController.bomb1) {
					MainScreenController.playerFish.getBombs().add(FishBomb.createFishBomb(MainScreenController.playerFish));
					MainScreenController.bomb1 = true;
				}
				if (MainScreenController.currScore > 2000 && !MainScreenController.bomb2) {
					MainScreenController.playerFish.getBombs().add(FishBomb.createFishBomb(MainScreenController.playerFish));
					MainScreenController.bomb2 = true;
				}
				if (MainScreenController.currScore > 5000 && !MainScreenController.bomb3) {
					MainScreenController.playerFish.getBombs().add(FishBomb.createFishBomb(MainScreenController.playerFish));
					MainScreenController.bomb3 = true;
				}

				MainScreenController.renderStatics(gc);

				MainScreenController.handlePlayerInput(gc);

				MainScreenController.generateEnemyFish();

				for (int i = 0; i < MainScreenController.entities.size(); i++) {

					if (!MainScreenController.entities.get(i).getSprite().getBoundingBox()
							.intersects(MainScreenController.screenbox)) {
						MainScreenController.entities.remove(i);
					} else if (MainScreenController.playerFish.intersects(MainScreenController.entities.get(i))
							&& MainScreenController.playerFish.isAlive()) {
						// if the player fish is bigger than the enemy fish,
						// then the player fish grows.
						if (!MainScreenController.playerFish.playerDies(MainScreenController.entities.get(i))) {
							MainScreenController.handleCollision(i);
						} else {
							// else the game stops.
							this.stop();
							MainScreenController.playerLost();
						}
					}
				}
				MainScreenController.renderNonStatics(gc);
				MainScreenController.frames++;
			}
		};
	}
}
