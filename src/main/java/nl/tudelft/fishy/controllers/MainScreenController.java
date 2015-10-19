package nl.tudelft.fishy.controllers;

import nl.tudelft.fishy.BoundingBox;
import nl.tudelft.fishy.EndBoss;
import nl.tudelft.fishy.EnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.Lance;
import nl.tudelft.fishy.PlayerFish;
import nl.tudelft.fishy.Sprite;
import nl.tudelft.fishy.factories.EntityFactory;
import nl.tudelft.fishy.factories.ItemFactory;
import nl.tudelft.fishy.factories.MainScreenEventHandlerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class contains all the event handlers of the buttons on the main screen.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class MainScreenController {

  public static PlayerFish playerFish;
  public static ArrayList<Entity> entities;
  public static BoundingBox screenbox;
  public static int frames;
  private static final double MULTIPLIER = 1.05;
  private static Text scoreText = new Text();
  public static int currScore;
  public static ArrayList<String> input;
  private static Image background = new Image("/resources/Fishy_bg.jpg");
  public static boolean bomb1;
  public static boolean bomb2;
  public static boolean bomb3;
  private static EndBoss endBoss = (EndBoss) EntityFactory.getEntityFactory()
      .getEntity("BOSS");
  private static Lance lance;
  private static boolean bossMode;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Text HighScoreText;

  @FXML
  private Button MenuButton;

  @FXML
  private Button QuitButton;

  @FXML
  private Button PlayButton;

  @FXML
  private Text NGPText;

  /**
   * Automatically generated via Scenebuilder.
   */
  @FXML
  void PlayEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   */
  @FXML
  void MenuEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   */
  @FXML
  void QuitEvent(MouseEvent event) {

  }

  /**
   * Set up things we need. Initialize the sprite list, and create the player
   * fish.
   */
  public static void init() {
    EntityFactory entityFactory = EntityFactory.getEntityFactory();
    ItemFactory itemFactory = ItemFactory.getItemFactory();
    entities = new ArrayList<Entity>();
    setScreenbox(new BoundingBox(0, 0, Game.getResX(), Game.getResY()));
    playerFish = (PlayerFish) entityFactory.getEntity("PLAYER");
    playerFish.getBombs().add(
        (FishBomb) itemFactory.createItem("FISHBOMB", playerFish));
    scoreText.setText("Score");
    input = new ArrayList<String>();
    frames = 0;
    if (!Game.isPlayingNewGamePlus()) {
      currScore = 0;
    }
    setLance((Lance) itemFactory.createItem("LANCE", playerFish));
    setBossMode(false);
  }

  /**
   * Initialize the buttons and also set the event handlers for the buttons.
   */
  @FXML
  void initialize() {

    assert PlayButton != null : "fx:id=\"PlayButton\" was not injected:"
        + " check your FXML file 'Main Screen.fxml'.";
    assert MenuButton != null : "fx:id=\"OptionsButton\" was not injected: "
        + "check your FXML file 'Main Screen.fxml'.";
    assert QuitButton != null : "fx:id=\"QuitButton\" was not injected: "
        + "check your FXML file 'Main Screen.fxml'.";
    assert NGPText != null : "fx:id=\"NGPText\" was not injected: "
        + "check your FXML file 'MainScreen.fxml'.";
    assert HighScoreText != null : "fx:id=\"HighScoreText\" was not injected:"
        + " check your FXML file 'MainScreen.fxml'.";

    Game.getLogger().logInit();
    init();
    HighScoreText.setText("" + Game.getHighScore());
    Game.getLogger().logInitSucceeded();

    if (Game.isPlayingNewGamePlus()) {
      QuitButton.setVisible(false);
      MenuButton.setVisible(false);
      NGPText.setVisible(true);
      playerFish.setScore(getCurrScore());
    }

    MainScreenEventHandlerFactory mainScreenEHFactory = MainScreenEventHandlerFactory
        .getMainScreenEHFactory();

    EventHandler<MouseEvent> playbuttonEH = mainScreenEHFactory
        .makeEventHandler("playbutton");
    PlayButton.setOnMouseClicked(playbuttonEH);

    EventHandler<MouseEvent> menubuttonEH = mainScreenEHFactory
        .makeEventHandler("menubutton");
    MenuButton.setOnMouseClicked(menubuttonEH);

    EventHandler<MouseEvent> quitbuttonEH = mainScreenEHFactory
        .makeEventHandler("quitbutton");
    QuitButton.setOnMouseClicked(quitbuttonEH);

  }

  /**
   * Set the bounding box of the screen.
   * 
   * @param boundingBox
   *          is an object of BoundingBox. This is the bounding box that you
   *          want to set to the screen.
   */
  private static void setScreenbox(BoundingBox boundingBox) {
    screenbox = boundingBox;
  }

  /**
   * Get the bounding box of the screen.
   * 
   * @return the bounding box of the screen.
   */
  public static BoundingBox getScreenbox() {
    return screenbox;
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
   * Renders all the static elements like background and score.
   * 
   * @param gc
   *          - the graphicsContext which needs to do the rendering.
   */
  public static void renderStatics(GraphicsContext gc) {
    gc.drawImage(background, 0, 0);
    gc.setFill(Color.AQUA);
    gc.fillOval(525, 1, 200, 75);
    gc.setFill(Color.BLACK);
    gc.setFont(Font.font("Comic Sans", 30));
    gc.fillText(scoreText.getText().toString(), 625, 20);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.fillText(Integer.toString(playerFish.getScore()), 625, 55);

    for (int i = 0; i < playerFish.getBombs().size(); i++) {
      playerFish.getBombs().get(i).getSprite().render(gc);
    }
  }

  /**
   * Render all the non static elements, i.e. the enemy fish and the player
   * fish.
   * 
   * @param gc
   *          - the graphicsContext which needs to do the rendering.
   */
  public static void renderNonStatics(GraphicsContext gc) {
    playerFish.getSprite().render(gc);

    for (int i = 0; i < entities.size(); i++) {

      Entity curr = entities.get(i);
      EnemyFish currEnemyFish = (EnemyFish) curr;

      if (currEnemyFish.isLefty()) {
        currEnemyFish.getSprite().updateX(currEnemyFish.getMoveSpeed());
      } else {
        currEnemyFish.getSprite().updateX(-currEnemyFish.getMoveSpeed());
      }

      entities.get(i).getSprite().render(gc);
    }
  }

  /**
   * Handle movement, rendering and collisions of the end boss.
   * 
   * @param gc
   *          - the GraphicsContext.
   */
  public static void handleBoss(GraphicsContext gc) {
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
   * Handle movement, rendering and collisions of the weapon.
   * 
   * @param gc
   *          - the GraphicsContext.
   */
  public static void handleWeapon(GraphicsContext gc) {
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
  public static void handlePlayerInput(GraphicsContext gc) {
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
      for (int i = 0; i < entities.size(); i++) {
        if (fishBomb.intersectsRectangle(entities.get(i).getSprite()
            .getBoundingBox())) {
          handleCollision(i);
        }
      }
      playerFish.getBombs().remove(index);
    }

  }

  /**
   * Handles collisions between player fish and enemy fish.
   * 
   * @param nth
   *          - the nth enemy fish in the entities arrayList.
   */
  public static void handleCollision(int nth) {
    // get the current enemyFish
    Entity enemyFish = entities.get(nth);
    // get the sprite of the current playerFish.
    Sprite sprite = enemyFish.getSprite();
    // get the bounding box of the sprite.
    BoundingBox box = sprite.getBoundingBox();
    // first get the height of enemy fish.
    int height = box.getHeight();
    // second get the width of enemy fish.
    int width = box.getWidth();
    // remove the fish from the screen.
    entities.remove(nth);
    // let the fish of the player grow.
    playerFish.grow(MULTIPLIER);
    // get the area as the score.
    int score = (height * width) / 100;
    // print in the console that player fish has eaten a smaller fish.
    Game.getLogger().logPlayerFishGrows(score);
    // then adds the score to the current score.
    setCurrScore(currScore + score);
    // print in the console of the current score of the player.
    Game.getLogger().logNewScore(currScore);
    // finally sets the total score to the player
    // fish.
    playerFish.setScore(currScore);

    if (currScore > Game.getHighScore()) {
      Game.setHighScore(currScore);
    }
  }

  /**
   * This method is being called when the player fish collide with a large enemy
   * fish, and then the game is proceed to losing screen.
   *
   */
  public static void playerLost() {
    Game.getLogger().logPlayerFishDies();
    Game.getLogger().logGameResult("lost", currScore);
    setCurrScore(0);
    playerFish.setScore(currScore);

    Game.setNewGamePlusMode(false);
    Game.getMediaPlayer().stop();

    Game.switchScreen("/resources/LosingScreen.fxml");
    Game.getLogger().logSwitchScreen("LosingScreen");
    setBossMode(false);
    playerFish.setHasLance(false);
  }

  /**
   * Generates a new enemy fish every 90 frames.
   */
  public static void generateEnemyFish() {
    EntityFactory entityFactory = EntityFactory.getEntityFactory();
    if ((frames % 90 == 0) && !isBossMode()) {
      entities.add((EnemyFish) entityFactory.getEntity("ENEMY"));
      Game.getLogger().logEdgeBump(playerFish);
    }
  }

  /**
   * Set the current score of the game.
   * 
   * @param score
   *          the desired score.
   */
  public static void setCurrScore(int score) {
    currScore = score;
  }

  /**
   * Get the current score of the game.
   * 
   * @return the current score of the game.
   */
  public static int getCurrScore() {
    return currScore;
  }

  /**
   * Get the player (playerFish).
   * 
   * @return the player (PlayerFish).
   */
  public static PlayerFish getPlayerFish() {
    return playerFish;
  }

  /**
   * {@inheritDoc} Overrides the Handle method in AnimationTimer to contain the
   * game loop.
   * 
   * @return the new AnimationTimer.
   */
  public static AnimationTimer makeAnimationTimer(GraphicsContext gc) {

    return new AnimationTimer() {
      public void handle(long currentNTime) {

        BoundingBox endBossbb = endBoss.getSprite().getBoundingBox();

        BoundingBox lancebb = lance.getSprite().getBoundingBox();

        Sprite pfSprite = playerFish.getSprite();
        BoundingBox pfbb = pfSprite.getBoundingBox();

        if (playerHasWon()) {
          this.stop();
          Game.switchScreen("/resources/WinningScreen.fxml");
          Game.getMediaPlayer().stop();
          Game.getLogger().logSwitchScreen("WinningScreen");

          setBossMode(false);
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
            BoundingBox screenBox = getScreenbox();
            lancebb.setY(screenBox.getHeight() / 4 * 3);
          }
          setBossMode(true);
        }

        if (currScore > 500 && !bomb1) {
          playerFish.getBombs().add(FishBomb.createFishBomb(playerFish));
          bomb1 = true;
        }
        if (currScore > 2000 && !bomb2) {
          playerFish.getBombs().add(FishBomb.createFishBomb(playerFish));
          bomb2 = true;
        }
        if (currScore > 5000 && !bomb3) {
          playerFish.getBombs().add(FishBomb.createFishBomb(playerFish));
          bomb3 = true;
        }

        if (playerFish.intersects(endBoss) && !playerFish.hasLance()) {
          endBossbb.setX(-2000);
          endBossbb.setY(-2000);

          this.stop();
          playerLost();
        }

        renderStatics(gc);

        handleBoss(gc);

        handleWeapon(gc);

        handlePlayerInput(gc);

        generateEnemyFish();

        if (pfbb.intersects(lancebb)) {

          lancebb.setX(-2000);
          lancebb.setY(-2000);

          Image pfImg = pfSprite.getImg();
          int playerFishSizeX = (int) pfImg.getWidth();
          int playerFishSizeY = (int) pfImg.getHeight();

          Image leftImg = new Image("/resources/FishKnightLeft.png", playerFishSizeX,
              playerFishSizeY, true, true);
          Image rightImg = new Image("/resources/FishKnightRight.png", playerFishSizeX,
              playerFishSizeY, true, true);

          playerFish.setPlayerFishLeftImage(leftImg);
          playerFish.setPlayerFishRightImage(rightImg);

          playerFish.setHasLance(true);

          playerFish.getSprite().setImg(leftImg);
        }

        for (int i = 0; i < entities.size(); i++) {

          Sprite currSprite = entities.get(i).getSprite();
          BoundingBox currbb = currSprite.getBoundingBox();

          if (!currbb.intersects(screenbox)) {

            entities.remove(i);

          } else if (playerFish.intersects(entities.get(i))
              && playerFish.isAlive()) {

            if (!playerFish.playerDies(entities.get(i))) {

              handleCollision(i);
            } else {

              this.stop();
              playerLost();
            }
          }
        }
        renderNonStatics(gc);
        frames++;
      }
    };

  }

  public static EndBoss getEndBoss() {
    return endBoss;
  }

  public static void setEndBoss(EndBoss endBoss) {
    MainScreenController.endBoss = endBoss;
  }

  public static Lance getLance() {
    return lance;
  }

  public static void setLance(Lance lance) {
    MainScreenController.lance = lance;
  }

  public static boolean isBossMode() {
    return bossMode;
  }

  public static void setBossMode(boolean bossMode) {
    MainScreenController.bossMode = bossMode;
  }

}
