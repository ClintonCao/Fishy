package factories;

import interfaces.AnimationTimerFactoryInterface;
import main.FishBomb;
import main.Game;
import main.MainScreenController;
import main.PlayerFish;
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

        if (MainScreenController.playerHasWon()) {
          this.stop();
          Game.switchScreen("FXML/WinningScreen.fxml");
          Game.getMediaPlayer().stop();
          Game.getLogger().logSwitchScreen("WinningScreen");
          
          MainScreenController.setBossMode(false);
          MainScreenController.playerFish.setHasLance(false);
          
          MainScreenController.getEndBoss().getSprite().getBoundingBox().setX(-2000);
        	MainScreenController.getEndBoss().getSprite().getBoundingBox().setY(-2000);
        }
        
        if (MainScreenController.playerFish.getSprite().getBoundingBox().getWidth() > 60) {
        	if (MainScreenController.getEndBoss().getSprite().getBoundingBox().getX() == -2000) {
        		MainScreenController.getEndBoss().getSprite().getBoundingBox().setX(0);
          	MainScreenController.getEndBoss().getSprite().getBoundingBox().setY(0);
        	}
        	if ((MainScreenController.getLance().getSprite().getBoundingBox().getX() == -2000) && !MainScreenController.getPlayerFish().hasLance()) {
        		MainScreenController.getLance().getSprite().getBoundingBox().setX(0);
          	MainScreenController.getLance().getSprite().getBoundingBox().setY(MainScreenController.getScreenbox().getHeight()/4*3);
        	}
        	MainScreenController.setBossMode(true);
        }

        if (MainScreenController.currScore > 500 && !MainScreenController.bomb1) {
          MainScreenController.playerFish.getBombs().add(
              FishBomb.createFishBomb(MainScreenController.playerFish));
          MainScreenController.bomb1 = true;
        }
        if (MainScreenController.currScore > 2000
            && !MainScreenController.bomb2) {
          MainScreenController.playerFish.getBombs().add(
              FishBomb.createFishBomb(MainScreenController.playerFish));
          MainScreenController.bomb2 = true;
        }
        if (MainScreenController.currScore > 5000
            && !MainScreenController.bomb3) {
          MainScreenController.playerFish.getBombs().add(
              FishBomb.createFishBomb(MainScreenController.playerFish));
          MainScreenController.bomb3 = true;
        }
        
        
        if (MainScreenController.playerFish.intersects(MainScreenController.getEndBoss()) && !MainScreenController.playerFish.hasLance()) {
        	MainScreenController.getEndBoss().getSprite().getBoundingBox().setX(-2000);
        	MainScreenController.getEndBoss().getSprite().getBoundingBox().setY(-2000);
        	
        	this.stop();
        	MainScreenController.playerLost();
        }

        MainScreenController.renderStatics(gc);
        
        MainScreenController.handleBoss(gc);
        
        MainScreenController.handleWeapon(gc);

        MainScreenController.handlePlayerInput(gc);

        MainScreenController.generateEnemyFish();

        if (MainScreenController.playerFish.getSprite().getBoundingBox().intersects(MainScreenController.getLance().getSprite().getBoundingBox())) {
        	
        	MainScreenController.getLance().getSprite().getBoundingBox().setX(-2000);
        	MainScreenController.getLance().getSprite().getBoundingBox().setY(-2000);
        	
        	int playerFishSizeX = (int) MainScreenController.playerFish.getSprite().getImg().getWidth();
        	int playerFishSizeY = (int) MainScreenController.playerFish.getSprite().getImg().getHeight();
        	
        	Image leftImg = new Image("FishKnightLeft.png", playerFishSizeX, playerFishSizeY, true, true);
        	Image rightImg = new Image("FishKnightRight.png", playerFishSizeX, playerFishSizeY, true, true);

        	MainScreenController.playerFish.setPlayerFishLeftImage(leftImg);
        	MainScreenController.playerFish.setPlayerFishRightImage(rightImg);
        	
        	MainScreenController.playerFish.setHasLance(true);

        	MainScreenController.playerFish.getSprite().setImg(leftImg);
        }

        for (int i = 0; i < MainScreenController.entities.size(); i++) {

          if (!MainScreenController.entities.get(i).getSprite()
              .getBoundingBox().intersects(MainScreenController.screenbox)) {
            MainScreenController.entities.remove(i);
          } else if (MainScreenController.playerFish
              .intersects(MainScreenController.entities.get(i))
              && MainScreenController.playerFish.isAlive()) {
            // if the player fish is bigger than the enemy fish,
            // then the player fish grows.
            if (!MainScreenController.playerFish
                .playerDies(MainScreenController.entities.get(i))) {
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
