package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class contains all the event handlers of the buttons on the main screen.
 * 
 * @author Group 6
 *
 */
public class MainScreenController {

  private static PlayerFish playerFish;
  private static ArrayList<EnemyFish> entities;
  private static BoundingBox screenbox;
  private static int frames;
  private static final double MULTIPLIER = 1.05;
  private static Text scoreText = new Text();
  private static int currScore;
  private static Logger logger;
  private static ArrayList<String> input;
  private static Image background = new Image("Fishy_bg.jpg");

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button MenuButton;

  @FXML
  private Button QuitButton;

  @FXML
  private Button PlayButton;

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void PlayEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void MenuEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void QuitEvent(MouseEvent event) {

  }

  /**
   * Set up things we need. Initialize the sprite list, and create the
   * player fish.
   */
  public static void init() {
    entities = new ArrayList<EnemyFish>();
    setScreenbox(new BoundingBox(0, 0, Game.getResX(), Game.getResY()));
    playerFish = PlayerFish.createPlayerFish();
    scoreText.setText("Score");
    logger = new Logger(playerFish, playerFish.getSprite().getBoundingBox());
    input = new ArrayList<String>();
    frames = 0;
    currScore = 0;
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

    PlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Logger logger1 = new Logger(null, null);

        logger1.logInit();
        init();
        logger1.logInitSucceeded();
        Group root = new Group();
        Scene scene = new Scene(root);
        Game.stage.setScene(scene);

        Canvas canvas = new Canvas(Game.getResX(), Game.getResY());

        root.getChildren().add(canvas);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
              input.add(code);
            }
          }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
            String code = e.getCode().toString();
            input.remove(code);
          }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();
        


        new AnimationTimer() {
          public void handle(long currentNTime) {
              
            if (playerHasWon()) {
                currScore = 0;
                this.stop();
                Game.switchScreen("FXML/WinningScreen.fxml");
                Game.mediaPlayer.stop();
                logger.logSwitchScreen("WinningScreen");
            }
            
            renderStatics(gc);
            
            handlePlayerMovement();
            
            generateEnemyFish();

            for (int i = 0; i < entities.size(); i++) {

                if (!entities.get(i).getSprite().getBoundingBox().intersects(screenbox)) {
                entities.remove(i);
              } else if (playerFish.intersects(entities.get(i)) && playerFish.isAlive()) {
                  // if the player fish is bigger than the enemy fish,
                  // then the player fish grows.
                  if (!playerFish.playerDies(entities.get(i))) {
                  handleCollision(i);
                } else {           
                  // else the game stops.
                  this.stop();
                  playerLost();
                }
              }
            }
            renderNonStatics(gc);
            frames++;
          }
        }.start();
      }
    });

    MenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Game.switchScreen("FXML/MenuScreen.fxml");
        logger.logSwitchScreen("MenuScreen");

      }
    });

    QuitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        Platform.exit();
        logger.logEndGame();

      }
    });
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
   * @return true if the player is bigger than a certain size.
   */
  private static boolean playerHasWon() {
    return (playerFish.getSprite().getBoundingBox().getHeight() > 400);
  }
  
  /**
   * Renders all the static elements like background and score.
   * @param gc - the graphicsContext which needs to do the rendering.
   */
  private static void renderStatics(GraphicsContext gc) {
    gc.drawImage(background, 0, 0);
    gc.setFill(Color.AQUA);
    gc.fillOval(525, 1, 200, 75);
    gc.setFill(Color.BLACK);
    gc.setFont(Font.font("Comic Sans", 30));
    gc.fillText(scoreText.getText().toString(), 625, 20);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.fillText(Integer.toString(playerFish.getScore()), 625, 55);
  }
  
  /**
   * Render all the non static elements, i.e. the enemy fish and the player fish.
   * @param gc - the graphicsContext which needs to do the rendering.
   */
  private static void renderNonStatics(GraphicsContext gc) {
    playerFish.getSprite().render(gc);

    for (int i = 0; i < entities.size(); i++) {
      EnemyFish curr = entities.get(i);
      if (curr.isLefty()) {
        curr.getSprite().updateX(curr.getMoveSpeed());
      } else {
        curr.getSprite().updateX(-curr.getMoveSpeed());
      }
      entities.get(i).getSprite().render(gc);
    }
  }
  
  /**
   * This method handles the WASD input of the player.
   */
  private static void handlePlayerMovement() {
    if (input.contains("A") && !playerFish.intersectsLeftScreenEdge()) {

      playerFish.getSprite()
        .setImg(playerFish.getPlayerFishLeftImage());
      playerFish.getSprite().updateX(-playerFish.getMoveSpeed());
      logger.logKeyPress("A");
      logger.logDirectionChange("left");

    } else if (input.contains("D")
            && !playerFish.intersectsRightScreenEdge()) {

      playerFish.getSprite().setImg(
              playerFish.getPlayerFishRightImage());
      playerFish.getSprite().updateX(playerFish.getMoveSpeed());
      logger.logKeyPress("D");
      logger.logDirectionChange("right");
    }
    if (input.contains("W") && !playerFish.intersectsUpperScreenEdge()) {

      playerFish.getSprite().updateY(-playerFish.getMoveSpeed());
      logger.logKeyPress("W");
      logger.logDirectionChange("upwards");

    } else if (input.contains("S")
            && !playerFish.intersectsUnderScreenEdge()) {

      playerFish.getSprite().updateY(playerFish.getMoveSpeed());
      logger.logKeyPress("S");
      logger.logDirectionChange("downwards");
    }
  }
  
  /**
   * Handles collisions between player fish and enemy fish.
   * @param i - the i'th enemy fish in the entities arrayList.
   */
  private static void handleCollision(int i) {
    // first get the height of enemy fish.
    int height = entities.get(i).getSprite().getBoundingBox()
            .getHeight();
    // second get the width of enemy fish.
    int width = entities.get(i).getSprite().getBoundingBox()
            .getWidth();
    // remove the fish from the screen.
    entities.remove(i);
    // let the fish of the player grow.
    playerFish.grow(MULTIPLIER);
    // get the area as the score.
    int score = (height * width) / 100;
    // print in the console that player fish has eaten a smaller fish.
    logger.logPlayerFishGrows(score);
    // then adds the score to the current score.
    currScore = currScore + score;
    // print in the console of the current score of the player.
    logger.logNewScore(currScore);
    // finally sets the total score to the player
    // fish.
    playerFish.setScore(currScore);
  }
  
  /**
   * This method is being called when the player fish collide with a large enemy fish,
   * and then the game is proceed to losing screen.
   *
   */
  private static void playerLost() {
    // the logger prints the fact that player fish dies.
    logger.logPlayerFishDies();
    // the logger also prints the status of the game.
    logger.logGameResult("lost", currScore);
    // reset the current game score.
    currScore = 0;
    playerFish.setScore(currScore);
    Game.mediaPlayer.stop();
    // switch to losing screen.
    Game.switchScreen("FXML/LosingScreen.fxml");
    // log the process of switching to losing screen.
    logger.logSwitchScreen("LosingScreen");
  }
  /**
   * Generates a new enemy fish every 90 frames.
   */
  private static void generateEnemyFish() {
    if (frames % 90 == 0) {
      entities.add(EnemyFish.generateFish());
      logger.logEdgeBump();
    }
  } 
}
