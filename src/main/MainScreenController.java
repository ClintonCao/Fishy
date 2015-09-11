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
  private int frames;
  private final double multiplier = 1.02;
  private static Text scoreText = new Text();
  private static int currScore = 0;

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

        ArrayList<String> input = new ArrayList<String>();

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
        Image background = new Image("Fishy_bg.jpg");
        frames = 0;

        new AnimationTimer() {
          public void handle(long currentNTime) {

            // Draw the background every frame.
            gc.drawImage(background, 0, 0);
            gc.setFill(Color.AQUA);
            gc.fillOval(525, 1, 200, 75);

            // Draw the scoreboard with the player's score.
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Comic Sans", 30));
            gc.fillText(scoreText.getText().toString(), 625, 20);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText(Integer.toString(playerFish.getScore()), 625, 55);

            // Control the playerfish using WASD.
            if (input.contains("A") && !playerFish.intersectsLeftScreenEdge()) {

              // Set the image of the playerfish to the 'looking
              // to the left version'.
              playerFish.getSprite()
                  .setImg(playerFish.getPlayerFishLeftImage());
              playerFish.getSprite().updateX(-playerFish.getMoveSpeed());

            } else if (input.contains("D")
                && !playerFish.intersectsRightScreenEdge()) {

              // Set the image of the playerfish to the 'looking
              // to the right version'.
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

            // Generate an enemy fish every so many frames.
            if (frames % 45 == 0) {
              entities.add(EnemyFish.generateFish());
            }

            // If the playerfish intersects another fish, remove it.
            for (int i = 0; i < entities.size(); i++) {
              // First check if a fish is outside the screen, if
              // it is, remove it.
              if (!entities.get(i).getSprite().getBoundingBox()
                  .intersects(screenbox)) {
                entities.remove(i);
                // Secondly check if a fish is intersecting with
                // the playerfish, if it is, remove it.
              } else if (playerFish.intersects(entities.get(i))
                  && playerFish.isAlive()) {

                // first get the height of enemy fish
                int height = entities.get(i).getSprite().getBoundingBox()
                    .getHeight();
                // second get the width of enemy fish
                int width = entities.get(i).getSprite().getBoundingBox()
                    .getWidth();
                // remove the fish from the screen
                entities.remove(i);
                // let the fish of the player grow.
                playerFish.grow(multiplier);
                // get the area as the score
                int score = height * width;
                // then adds the score to the current score
                currScore = currScore + score / 10000;
                // finally sets the total score to the player
                // fish
                playerFish.setScore(currScore);
              }
            }

            // Render the playerfish.
            playerFish.getSprite().render(gc);

            // Render all the remaining fish.
            for (int i = 0; i < entities.size(); i++) {
              EnemyFish curr = entities.get(i);
              if (curr.isLefty()) {
                curr.getSprite().updateX(curr.getMoveSpeed());
              } else {
                curr.getSprite().updateX(-curr.getMoveSpeed());
              }
              entities.get(i).getSprite().render(gc);
            }
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
   * @return return the bounding box of the screen.
   */
  public static BoundingBox getScreenbox() {
    return screenbox;
  }
}
