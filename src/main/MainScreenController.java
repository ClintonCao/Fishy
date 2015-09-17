package main;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
   * playerfish.
   */
  public static void init() {
    entities = new ArrayList<EnemyFish>();
    setScreenbox(new BoundingBox(0, 0, Game.getResX(), Game.getResY()));
    playerFish = PlayerFish.createPlayerFish();
    scoreText.setText("Score");
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

        System.out.println("Initializing game objects..");
        init();
        System.out.println("Successfully initialized..");
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
            }
            renderStatics(gc);

            handlePlayerMovement();
            
            generateEnemyFish();

            for (int i = 0; i < entities.size(); i++) {

                if (!entities.get(i).getSprite().getBoundingBox().intersects(screenbox)) {
                entities.remove(i);
              } else if (playerFish.intersects(entities.get(i)) && playerFish.isAlive()) {
                if (playerFish.playerDies(entities.get(i))) {
                    this.stop();
                    currScore = 0;
                    playerFish.setScore(currScore);
                    Game.switchScreen("FXML/LosingScreen.fxml");
                }            
                handleCollision(i);
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

      }
    });

    QuitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        Platform.exit();
        System.out.println("Quiting application..");
        System.out.println("Exited successfully..");

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

    } else if (input.contains("D")
            && !playerFish.intersectsRightScreenEdge()) {

      playerFish.getSprite().setImg(
              playerFish.getPlayerFishRightImage());
      playerFish.getSprite().updateX(playerFish.getMoveSpeed());
    }
    if (input.contains("W") && !playerFish.intersectsUpperScreenEdge()) {

      playerFish.getSprite().updateY(-playerFish.getMoveSpeed());

    } else if (input.contains("S")
            && !playerFish.intersectsUnderScreenEdge()) {

      playerFish.getSprite().updateY(playerFish.getMoveSpeed());
    }
  }
  
  /**
   * Handles collisions between player fish and enemy fish.
   * @param i - the i'th enemy fish in the entities arrayList.
   */
  private static void handleCollision(int i) {
    int height = entities.get(i).getSprite().getBoundingBox()
            .getHeight();
    int width = entities.get(i).getSprite().getBoundingBox()
            .getWidth();
    entities.remove(i);
    playerFish.grow(MULTIPLIER);
    int score = height * width;
    currScore = currScore + score / 100;
    playerFish.setScore(currScore);
  }
  
  /**
   * Generates a new enemy fish every 90 frames.
   */
  private static void generateEnemyFish() {
    if (frames % 90 == 0) {
      entities.add(EnemyFish.generateFish());
    }
  }  
}
