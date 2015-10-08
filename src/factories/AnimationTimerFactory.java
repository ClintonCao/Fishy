package factories;

import interfaces.AnimationTimerFactoryInterface;
import main.FishBomb;
import main.Game;
import main.MainScreenController;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Makes Animation Timers.
 * Singleton class.
 * 
 * @author Michiel
 */
public class AnimationTimerFactory implements AnimationTimerFactoryInterface {
	
	private static AnimationTimerFactory animationTimerFactory = null;

	/**
	 * Constructor.
	 */
	private AnimationTimerFactory() {

	}

	/**
	 * Synchronized getters.
	 * @return the Singleton AnimationTimerFactory.
	 */
	public static AnimationTimerFactory getAnimationTimerFactory() {
		
		if(animationTimerFactory == null) {
			animationTimerFactory = new AnimationTimerFactory();
		}
		return animationTimerFactory;
	}
	
	/**
	 * {@inheritDoc} Overrides the Handle method in AnimationTimer to contain the game loop.
	 * @return the new AnimationTimer.
	 */
	public AnimationTimer makeAnimationTimer(GraphicsContext gc) {

		return new AnimationTimer() {
			public void handle(long currentNTime) {

				if (MainScreenController.playerHasWon()) {
					this.stop();
					Game.switchScreen("FXML/WinningScreen.fxml");
					Game.getMediaPlayer().stop();
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
