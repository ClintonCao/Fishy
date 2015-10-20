package nl.tudelft.fishy.controllers;

import nl.tudelft.fishy.BoundingBox;
import nl.tudelft.fishy.EndBoss;
import nl.tudelft.fishy.EnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.FishBomb;
import nl.tudelft.fishy.CompositeEnemyFish;
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
import javafx.util.Pair;

/**
 * This class contains all the event handlers of the buttons on the main screen.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class MainScreenController {

  public static PlayerFish playerFish;
  public static BoundingBox screenbox;
  private static Text scoreText = new Text();
  public static int currScore;
  private static Image background = new Image("/Fishy_bg.jpg");



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
    screenbox = new BoundingBox(0, 0, Game.getResX(), Game.getResY());

    EntityFactory entityFactory = EntityFactory.getEntityFactory();
    ItemFactory itemFactory = ItemFactory.getItemFactory();
    playerFish = (PlayerFish) entityFactory.getEntity("PLAYER");
    playerFish.getBombs().add(
        (FishBomb) itemFactory.createItem("FISHBOMB", playerFish));
    scoreText.setText("Score");

    if (!Game.isPlayingNewGamePlus()) {
      currScore = 0;
    }
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
      playerFish.setScore(currScore);
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

//  /**
//   * Set the bounding box of the screen.
//   * 
//   * @param boundingBox
//   *          is an object of BoundingBox. This is the bounding box that you
//   *          want to set to the screen.
//   */
//  private static void setScreenbox(BoundingBox boundingBox) {
//    screenbox = boundingBox;
//  }
//
//  /**
//   * Get the bounding box of the screen.
//   * 
//   * @return the bounding box of the screen.
//   */
//  public static BoundingBox getScreenbox() {
//    return screenbox;
//  }



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
}
