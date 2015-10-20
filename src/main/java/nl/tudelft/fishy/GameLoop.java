package nl.tudelft.fishy;

import java.util.ArrayList;

import nl.tudelft.fishy.factories.AnimationTimerFactory;
import nl.tudelft.fishy.factories.EntityFactory;
import nl.tudelft.fishy.factories.ItemFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class GameLoop {
	
	private static PlayerFish playerFish;
  private static CompositeEnemyFish compositeEnemyFish = new CompositeEnemyFish();
  private static Text scoreText = new Text();
  private static int currScore;
  private static ArrayList<String> input;
  private static EndBoss endBoss = (EndBoss) EntityFactory.getEntityFactory().getEntity("BOSS");
  private static Lance lance;
  private static boolean bossMode;
  private AnimationTimer fAnimationTimer;
  
  public static BoundingBox screenbox;
  public static int frames;
  public static final double MULTIPLIER = 1.05;

	public GameLoop(GraphicsContext gc) {
		EntityFactory entityFactory = EntityFactory.getEntityFactory();
    ItemFactory itemFactory = ItemFactory.getItemFactory();
    screenbox = new BoundingBox(0, 0, Game.getResX(), Game.getResY());
    playerFish = (PlayerFish) entityFactory.getEntity("PLAYER");
    frames = 0;
    lance = (Lance) ItemFactory.getItemFactory().createItem("LANCE", playerFish);
    playerFish.getBombs().add(
        (FishBomb) itemFactory.createItem("FISHBOMB", playerFish));
    scoreText.setText("Score");
    input = new ArrayList<String>();
    frames = 0;
    if (!Game.isPlayingNewGamePlus()) {
      currScore = 0;
    }
    bossMode = false;
    setLance((Lance) itemFactory.createItem("LANCE", playerFish));
    setBossMode(false);
    
    fAnimationTimer = AnimationTimerFactory.getAnimationTimerFactory().makeAnimationTimer(gc, endBoss, playerFish, lance, frames, compositeEnemyFish);
	}
    
  /**
   * Render all the non static elements, i.e. the enemy fish and the player
   * fish.
   * 
   * @param gc
   *          - the graphicsContext which needs to do the rendering.
   */
  public void renderNonStatics(GraphicsContext gc) {
    playerFish.getSprite().render(gc);
    
    compositeEnemyFish.render(gc);
    compositeEnemyFish.move();
  }

  /**
   * Handle movement, rendering and collisions of the end boss.
   * 
   * @param gc
   *          - the GraphicsContext.
   */
  public void handleBoss(GraphicsContext gc) {
    BoundingBox endBossbb = endBoss.getSprite().getBoundingBox();
    BoundingBox playerFishbb = playerFish.getSprite().getBoundingBox();

    if (bossMode) {
      endBoss.getSprite().render(gc);
      if (endBoss.isLefty()) {
        endBoss.getSprite().updateX(getEndBoss().getMoveSpeed());
      } else {
        endBoss.getSprite().updateX(-getEndBoss().getMoveSpeed());
      }

      int playerX = playerFishbb.getX();
      int endBossX = endBossbb.getX();
      boolean endBossOutsideScreenBox = !endBossbb.intersects(screenbox);
      boolean playerLeftOfEndBoss = playerX < endBossX;
      boolean playerRightOfEndBoss = endBossX < playerX;

      if (endBossOutsideScreenBox
          && (playerLeftOfEndBoss || playerRightOfEndBoss)) {
        endBoss.switchDirection();
      }
    } else {
      endBossbb.setX(-2000);
      endBossbb.setY(-2000);
    }
  }
  
  /**
   * Check if the player has won the game.
   * 
   * @return true if the player is bigger than a certain size.
   */
  public static boolean playerHasWon() {
    return playerFish.intersects(endBoss) && playerFish.hasLance();
  }

  /**
   * Handle movement, rendering and collisions of the weapon.
   * 
   * @param gc
   *          - the GraphicsContext.
   */
  public void handleWeapon(GraphicsContext gc) {
    if (bossMode) {
      lance.getSprite().render(gc);

      if (lance.isLefty()) {
        lance.getSprite().updateX(5);
      } else {
        lance.getSprite().updateX(-5);
      }

      BoundingBox playerFishbb = playerFish.getSprite().getBoundingBox();
      BoundingBox lancebb = lance.getSprite().getBoundingBox();

      int playerX = playerFishbb.getX();
      int lanceX = lancebb.getX();
      boolean lanceOutsideScreenBox = !lancebb.intersects(screenbox);
      boolean playerLeftOfLance = playerX < lanceX;
      boolean playerRightOfLance = lanceX < playerX;

      if (lanceOutsideScreenBox && (playerLeftOfLance || playerRightOfLance)) {
        lance.switchDirection();
      }
    }
  }

  /**
   * This method handles the WASD input of the player. And any other input, like
   * X for using item.
   */
  public void handlePlayerInput(GraphicsContext gc) {
    ArrayList<FishBomb> playerBombs = playerFish.getBombs();
    if (input.contains("A") && !playerFish.intersectsLeftScreenEdge()) {
      playerFish.getSprite().setImg(playerFish.getPlayerFishLeftImage());
      playerFish.getSprite().updateX(-playerFish.getMoveSpeed());
      for (int i = 0; i < playerBombs.size(); i++) {
        playerBombs.get(i).updateX(-playerFish.getMoveSpeed());
      }
      Game.getLogger().logKeyPress("A");
      Game.getLogger().logDirectionChange("left");

    } else if (input.contains("D") && !playerFish.intersectsRightScreenEdge()) {
      playerFish.getSprite().setImg(playerFish.getPlayerFishRightImage());
      playerFish.getSprite().updateX(playerFish.getMoveSpeed());
      for (int i = 0; i < playerBombs.size(); i++) {
        playerBombs.get(i).updateX(playerFish.getMoveSpeed());
      }
      Game.getLogger().logKeyPress("D");
      Game.getLogger().logDirectionChange("right");
    }

    if (input.contains("W") && !playerFish.intersectsUpperScreenEdge()) {
      playerFish.getSprite().updateY(-playerFish.getMoveSpeed());
      for (int i = 0; i < playerBombs.size(); i++) {
        playerBombs.get(i).updateY(-playerFish.getMoveSpeed());
      }
      Game.getLogger().logKeyPress("W");
      Game.getLogger().logDirectionChange("upwards");

    } else if (input.contains("S") && !playerFish.intersectsUnderScreenEdge()) {
      playerFish.getSprite().updateY(playerFish.getMoveSpeed());
      for (int i = 0; i < playerBombs.size(); i++) {
        playerBombs.get(i).updateY(playerFish.getMoveSpeed());
      }
      Game.getLogger().logKeyPress("S");
      Game.getLogger().logDirectionChange("downwards");
    }

    if (input.contains("X") && playerFish.getBombs().size() > 0) {
      int index = playerFish.getBombs().size() - 1;
      FishBomb fishBomb = (FishBomb) playerFish.getBombs().get(index);
      Image explosionImg = playerFish.getBombs().get(index).getExplosionImg();
      int imgPosX = (int) (fishBomb.getPosX() - 0.25 * explosionImg.getWidth());
      int imgPosY = (int) (fishBomb.getPosY() - 0.25 * explosionImg.getHeight());
      gc.drawImage(explosionImg, imgPosX, imgPosY);
      
      compositeEnemyFish.handleFishBomb(fishBomb);
      
      playerFish.getBombs().remove(index);
    }

  }
  
  /** 
   * Update the player's score according to the size of the Enemy Fish.
   * @param enemyFish
   */
  public static void updateScore(Entity enemyFish) {
  	Sprite sprite = enemyFish.getSprite();
    BoundingBox box = sprite.getBoundingBox();
    int height = box.getHeight();
    int width = box.getWidth();

    playerFish.grow(MULTIPLIER);
    int score = (height * width) / 100;
    Game.getLogger().logPlayerFishGrows(score);
    setCurrScore(currScore + score);
    Game.getLogger().logNewScore(currScore);
    playerFish.setScore(currScore);

    if (currScore > Game.getHighScore()) {
      Game.setHighScore(currScore);
    }
  }

  /**
   * This method is being called when the player fish collide with a large enemy
   * fish, and then the game is proceed to losing screen.
   */
  public static void playerLost() {
    Game.getLogger().logPlayerFishDies();
    Game.getLogger().logGameResult("lost", currScore);
    setCurrScore(0);
    playerFish.setScore(currScore);

    Game.setNewGamePlusMode(false);
    Game.getMediaPlayer().stop();

    Game.switchScreen("/LosingScreen.fxml");
    Game.getLogger().logSwitchScreen("LosingScreen");
    setBossMode(false);
    playerFish.setHasLance(false);
    
    compositeEnemyFish.clear();
  }

  /**
   * Generates a new enemy fish every 90 frames.
   */
  public void generateEnemyFish() {
    EntityFactory entityFactory = EntityFactory.getEntityFactory();
    if ((frames % 90 == 0) && !isBossMode()) {
      compositeEnemyFish.add((EnemyFish) entityFactory.getEntity("ENEMY"));
      Game.getLogger().logEdgeBump(playerFish);
    }
  }
  
  /**
   * Increment frames.
   */
  public void updateFrames() {
  	frames++;
  }
  
  public void playerPicksUpLance() {
  	BoundingBox lancebb = lance.getSprite().getBoundingBox();
    Sprite pfSprite = playerFish.getSprite();
    BoundingBox pfbb = pfSprite.getBoundingBox();
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
  }
  
  public void playerIntersectsFish() {
    Pair<Integer, Boolean> res = compositeEnemyFish.intersectsPlayerFish(playerFish);
    
    if (res.getKey() != -1) {
    	if (res.getValue()) {
    		fAnimationTimer.stop();
    		GameLoop.playerLost();
    	} else {
    		compositeEnemyFish.remove(res.getKey());
    		playerFish.grow(GameLoop.MULTIPLIER);
    	}
    }
  }
  
  public void playerDiesToBoss() {
    BoundingBox endBossbb = endBoss.getSprite().getBoundingBox();
  	
    if (playerFish.intersects(endBoss) && !playerFish.hasLance()) {
      endBossbb.setX(-2000);
      endBossbb.setY(-2000);

      fAnimationTimer.stop();
      GameLoop.playerLost();
    }
  }

  // --- Getters and Setters ---
  
  public static void setCurrScore(int score) {
    currScore = score;
  }

  public static int getCurrScore() {
    return currScore;
  }

  public static PlayerFish getPlayerFish() {
    return playerFish;
  }

  public static EndBoss getEndBoss() {
    return endBoss;
  }

  public static void setEndBoss(EndBoss dendBoss) {
  	endBoss = dendBoss;
  }

  public static Lance getLance() {
    return lance;
  }

  public static void setLance(Lance dlance) {
    lance = dlance;
  }

  public static boolean isBossMode() {
    return bossMode;
  }

  public static void setBossMode(boolean dbossMode) {
    bossMode = dbossMode;
  }

	public AnimationTimer getAnimationTimer() {
		return fAnimationTimer;
	}

	public static ArrayList<String> getInput() {
		return input;
	}
	
}
