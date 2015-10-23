package nl.tudelft.fishy.controllers;

import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.GameLoop;
import nl.tudelft.fishy.factories.FactoryProducer;
import nl.tudelft.fishy.factories.MainScreenEventHandlerFactory;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
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

  private static Text scoreText = new Text();
  private static Image background = new Image("/Fishy_bg.jpg");
  private static GameLoop gameLoop;
  private static Canvas canvas = new Canvas(Game.getResX(), Game.getResY());
  private static GraphicsContext gc = canvas.getGraphicsContext2D();
  private static Text lifeText = new Text();
  private static FactoryProducer factoryProducer = FactoryProducer.getFactoryProducer();

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

    scoreText.setText("Score");

    gameLoop = new GameLoop(gc);
    lifeText.setText("Lives: " + gameLoop.getPlayerFishLives());

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
    }

    MainScreenEventHandlerFactory mainScreenEHFactory = (MainScreenEventHandlerFactory) factoryProducer.getFactory("MAINSCREEN");

    EventHandler<MouseEvent> playbuttonEH = mainScreenEHFactory.makeEventHandler("playbutton");
    PlayButton.setOnMouseClicked(playbuttonEH);

    EventHandler<MouseEvent> menubuttonEH = mainScreenEHFactory.makeEventHandler("menubutton");
    MenuButton.setOnMouseClicked(menubuttonEH);

    EventHandler<MouseEvent> quitbuttonEH = mainScreenEHFactory.makeEventHandler("quitbutton");
    QuitButton.setOnMouseClicked(quitbuttonEH);

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
    lifeText.setText("Lives: " + gameLoop.getPlayerFishLives());
    gc.fillText(lifeText.getText().toString(), 400, 35);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.fillText(Integer.toString(gameLoop.getCurrScore()), 625, 55);

    if (gameLoop.getPlayerFish().getBombs().size() > 0) {
      gameLoop.getPlayerFish().getBombs().get(0).getSprite().render(gc);
    }
  }

  public static GameLoop getGameLoop() {
    return gameLoop;
  }

  public static Canvas getCanvas() {
    return canvas;
  }
}
