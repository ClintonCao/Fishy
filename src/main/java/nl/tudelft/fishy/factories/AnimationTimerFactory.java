package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.BoundingBox;
import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.EndBoss;
import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.GameLoop;
import nl.tudelft.fishy.Lance;
import nl.tudelft.fishy.PlayerFish;
import nl.tudelft.fishy.Sprite;
import nl.tudelft.fishy.interfaces.AnimationTimerFactoryInterface;
import nl.tudelft.fishy.controllers.MainScreenController;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Makes Animation Timers. Singleton class.
 * 
 * @author Michiel
 */
public final class AnimationTimerFactory {

  private static AnimationTimerFactory animationTimerFactory = null;

  /**
   * Constructor.
   */
  private AnimationTimerFactory() {

  }

  /**
   * Synchronized getters.
   * 
   * @return the Singleton AnimationTimerFactory.
   */
  public static synchronized AnimationTimerFactory getAnimationTimerFactory() {

    if (animationTimerFactory == null) {
      animationTimerFactory = new AnimationTimerFactory();
    }
    return animationTimerFactory;
  }

/**
   * {@inheritDoc} Overrides the Handle method in AnimationTimer to contain the
   * game loop.
   * 
   * @return the new AnimationTimer.
   */
  public AnimationTimer makeAnimationTimer(GraphicsContext gc, EndBoss endBoss, PlayerFish playerFish, Lance lance, int frames, CompositeEnemyFish compositeEnemyFish) {

      return new AnimationTimer() {
        public void handle(long currentNTime) {

          BoundingBox endBossbb = endBoss.getSprite().getBoundingBox();

          BoundingBox lancebb = lance.getSprite().getBoundingBox();

          Sprite pfSprite = playerFish.getSprite();
          BoundingBox pfbb = pfSprite.getBoundingBox();
          
          GameLoop gameLoop = MainScreenController.getGameLoop();

          if (GameLoop.playerHasWon()) {
            this.stop();
            Game.switchScreen("/WinningScreen.fxml");
            Game.getMediaPlayer().stop();
            Game.getLogger().logSwitchScreen("WinningScreen");

            GameLoop.setBossMode(false);
            playerFish.setHasLance(false);

            endBossbb.setX(-2000);
            endBossbb.setY(-2000);
          }

          if (pfbb.getWidth() > 60) {
            if (endBossbb.getX() == -2000) {
              endBossbb.setX(0);
              endBossbb.setY(0);
            }
            if ((lancebb.getX() == -2000) && !playerFish.hasLance()) {
              lancebb.setX(0);
              BoundingBox screenBox = GameLoop.screenbox;
              lancebb.setY(screenBox.getHeight() / 4 * 3);
            }
            GameLoop.setBossMode(true);
          }

          if (playerFish.intersects(endBoss) && !playerFish.hasLance()) {
            endBossbb.setX(-2000);
            endBossbb.setY(-2000);

            this.stop();
            GameLoop.playerLost();
          }

          MainScreenController.renderStatics(gc);

          gameLoop.handleBoss(gc);

          gameLoop.handleWeapon(gc);

          gameLoop.handlePlayerInput(gc);

          gameLoop.generateEnemyFish();

          if (pfbb.intersects(lancebb)) {

            lancebb.setX(-2000);
            lancebb.setY(-2000);

            Image pfImg = pfSprite.getImg();
            int playerFishSizeX = (int) pfImg.getWidth();
            int playerFishSizeY = (int) pfImg.getHeight();

            Image leftImg = new Image("/FishKnightLeft.png", playerFishSizeX,
                playerFishSizeY, true, true);
            Image rightImg = new Image("/FishKnightRight.png", playerFishSizeX,
                playerFishSizeY, true, true);

            playerFish.setPlayerFishLeftImage(leftImg);
            playerFish.setPlayerFishRightImage(rightImg);

            playerFish.setHasLance(true);

            playerFish.getSprite().setImg(leftImg);
          }

          compositeEnemyFish.removeOffScreenEnemyFish(GameLoop.screenbox);
          
          Pair<Integer, Boolean> res = compositeEnemyFish.intersectsPlayerFish(playerFish);
          
          if (res.getKey() != -1) {
          	if (res.getValue()) {
          		this.stop();
          		GameLoop.playerLost();
          	} else {
          		compositeEnemyFish.remove(res.getKey());
          		playerFish.grow(GameLoop.MULTIPLIER);
          	}
          }
          
          GameLoop.renderNonStatics(gc);
          GameLoop.frames++;
        }
      };

    }
}
