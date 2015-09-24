package main;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * This Class represents the game itself.
 * 
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel
 *         Doesburg.
 *
 */
public class Game extends Application {

  static Stage stage;
  static Pane pane;
  private static int resX = 1280;
  private static int resY = 720;
  private static Logger logger;
  final URL resource = getClass().getResource("FXML/theme.mp3");
  final Media media = new Media(resource.toString());
  static MediaPlayer mediaPlayer;
  private static boolean musicOn;
  private static boolean newgameplus = false;

  /**
   * Main method that launches the application.
   * 
   * @param args
   *          arguments for the main method (nothing is used).
   */
  public static void main(String[] args) {
    musicOn = true;
    Application.launch(Game.class, (java.lang.String[]) null);
  }

  /**
   * The stage where the game starts from.
   * 
   * @param firstStage the stage for the start of the application.
   *          
   */
  @Override
  public void start(Stage firstStage) {
    try {

      stage = firstStage;
      logger = new Logger(null, null);
      
      firstStage.setTitle("Fishy");
      logger.logStartGame();
      logger.logIcon();
      stage.getIcons().add(
          new Image(Game.class.getResourceAsStream("FXML/Fish.png")));
      logger.logLoadSucceeded();
      logger.logSwitchScreen("MainScreen");
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "FXML/MainScreen.fxml"));

      pane = (Pane) loader.load();
      logger.logLoadSucceeded();
      
      mediaPlayer = new MediaPlayer(media);
      if (musicOn) {
        mediaPlayer.play();
      }
      mediaPlayer.setCycleCount(100);

      Scene scene = new Scene(pane);
      firstStage.setHeight(getResY());
      firstStage.setWidth(getResX());
      firstStage.setResizable(false);
      firstStage.setScene(scene);

      firstStage.show();

    } catch (Exception e) {
      System.out.println("File is not found " + e);
    }
  }

  /**
   * This method is used to switch the screens of the game.
   * 
   * @param bestand
   *          The name of the "destination" FXML file
   */
  public static void switchScreen(String bestand) {
    try {
      logger.logLoadingScreen(bestand);
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Game.class.getResource(bestand));
      stage.getScene().setRoot((Parent) loader.load());
      logger.logLoadSucceeded();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method retrieve the Y value of the game's resolution.
   * 
   * @return The Y of the Resolution
   */
  public static int getResY() {
    return resY;
  }

  /**
   * This method sets a new Y value for the resolution.
   * 
   * @param resY
   *          The new Y value
   */
  public static void setResY(int resY) {
    Game.resY = resY;
  }

  /**
   * This method retrieve the X value of the game's resolution.
   * 
   * @return The X of the Resolution
   */
  public static int getResX() {
    return resX;
  }

  /**
   * This method sets a new Y value for the resolution.
   * 
   * @param resX
   *          The new X value
   */
  public static void setResX(int resX) {
    Game.resX = resX;
  }

  public static boolean getMusicOn() {
    return musicOn;
  }

  public static void setMusicOn(boolean bool) {
    musicOn = bool;
  }
  
  public static void setNewGamePlusMode(boolean bool) {
    newgameplus = bool;
  }
  
  public static boolean isPlayingNewGamePlus() {
    return newgameplus;
  }
}
