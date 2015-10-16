package factories;

import java.util.ArrayList;

import interfaces.AnimationTimerFactoryInterface;
import main.BoundingBox;
import main.EndBoss;
import main.Entity;
import main.FishBomb;
import main.Game;
import main.Lance;
import main.MainScreenController;
import main.PlayerFish;
import main.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Makes Animation Timers. Singleton class.
 * 
 * @author Michiel
 */
public final class AnimationTimerFactory implements
    AnimationTimerFactoryInterface {

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
  public AnimationTimer makeAnimationTimer(GraphicsContext gc) {

    return new AnimationTimer() {
      public void handle(long currentNTime) {
      	
      	EndBoss endBoss = MainScreenController.getEndBoss();
      	BoundingBox endBossbb = endBoss.getSprite().getBoundingBox();
      	
      	Lance lance = MainScreenController.getLance();
      	BoundingBox lancebb = lance.getSprite().getBoundingBox();
      	
      	PlayerFish playerFish = MainScreenController.playerFish;
      	Sprite pfSprite = playerFish.getSprite();
      	BoundingBox pfbb = pfSprite.getBoundingBox();

        if (MainScreenController.playerHasWon()) {
          this.stop();
          Game.switchScreen("FXML/WinningScreen.fxml");
          Game.getMediaPlayer().stop();
          Game.getLogger().logSwitchScreen("WinningScreen");
          
          MainScreenController.setBossMode(false);
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
        		BoundingBox screenBox = MainScreenController.getScreenbox();
          	lancebb.setY(screenBox.getHeight() / 4 * 3);
        	}
        	MainScreenController.setBossMode(true);
        }

        if (MainScreenController.currScore > 500 && !MainScreenController.bomb1) {
          playerFish.getBombs().add(
              FishBomb.createFishBomb(playerFish));
          MainScreenController.bomb1 = true;
        }
        if (MainScreenController.currScore > 2000
            && !MainScreenController.bomb2) {
          playerFish.getBombs().add(
              FishBomb.createFishBomb(playerFish));
          MainScreenController.bomb2 = true;
        }
        if (MainScreenController.currScore > 5000
            && !MainScreenController.bomb3) {
          playerFish.getBombs().add(
              FishBomb.createFishBomb(playerFish));
          MainScreenController.bomb3 = true;
        }
        
        
        if (playerFish.intersects(endBoss) && !playerFish.hasLance()) {
        	endBossbb.setX(-2000);
        	endBossbb.setY(-2000);
        	
        	this.stop();
        	MainScreenController.playerLost();
        }

        MainScreenController.renderStatics(gc);
        
        MainScreenController.handleBoss(gc);
        
        MainScreenController.handleWeapon(gc);

        MainScreenController.handlePlayerInput(gc);

        MainScreenController.generateEnemyFish();

        if (pfbb.intersects(lancebb)) {
        	
        	lancebb.setX(-2000);
        	lancebb.setY(-2000);
        
        	Image pfImg = pfSprite.getImg();
        	int playerFishSizeX = (int) pfImg.getWidth();
        	int playerFishSizeY = (int) pfImg.getHeight();
        	
        	Image leftImg = new Image("FishKnightLeft.png", playerFishSizeX, playerFishSizeY, true, true);
        	Image rightImg = new Image("FishKnightRight.png", playerFishSizeX, playerFishSizeY, true, true);

        	playerFish.setPlayerFishLeftImage(leftImg);
        	playerFish.setPlayerFishRightImage(rightImg);
        	
        	playerFish.setHasLance(true);

        	playerFish.getSprite().setImg(leftImg);
        }

        ArrayList<Entity> entities = MainScreenController.entities;
        
        for (int i = 0; i < entities.size(); i++) {
        	
        	Sprite currSprite = entities.get(i).getSprite();
        	BoundingBox currbb = currSprite.getBoundingBox();
        	
          if (!currbb.intersects(MainScreenController.screenbox)) {
          	
            entities.remove(i);
            
          } else if (playerFish.intersects(entities.get(i)) && playerFish.isAlive()) {
          	
            if (!playerFish.playerDies(entities.get(i))) {
            	
              MainScreenController.handleCollision(i);
            } else {
            	
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
