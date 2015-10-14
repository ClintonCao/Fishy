package main;

import interfaces.GameInterface;

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
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 *
 */
public final class Game extends Application implements GameInterface {

  private static Stage stage;
  static Pane pane;
  private static int resX = 1280;
  private static int resY = 720;
  private static Logger logger;
  final URL resource = getClass().getResource("FXML/theme.mp3");
  final Media media = new Media(resource.toString());
  private static MediaPlayer mediaPlayer;
  private static boolean musicOn;
  private static boolean newgameplus = false;
  private static int highscore = 0;

  /**
   * Main method that launches the application.
   * 
   * @param args - not used.
   */
  public static void main(String[] args) {
    musicOn = true;
    Application.launch(Game.class, (java.lang.String[]) null);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void start(Stage firstStage) {
    try {

      setStage(firstStage);
      logger = Logger.getSingletonLogger();

      firstStage.setTitle("Fishy");
      logger.logStartGame();
      logger.logIcon();
      getStage().getIcons().add(new Image(Game.class.getResourceAsStream("FXML/Fish.png")));
      logger.logLoadSucceeded();
      logger.logSwitchScreen("MainScreen");
      FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/MainScreen.fxml"));

      pane = (Pane) loader.load();
      logger.logLoadSucceeded();

      setMediaPlayer(new MediaPlayer(media));
      if (musicOn) {
        getMediaPlayer().play();
      }
      
      getMediaPlayer().setCycleCount(100);

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
   * @param fileName - name of 'Destination' FXML file.
   */
  public static void switchScreen(String fileName) {
    try {
      logger.logLoadingScreen(fileName);
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Game.class.getResource(fileName));
      getStage().getScene().setRoot((Parent) loader.load());
      logger.logLoadSucceeded();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // --- Getters and Setters ---
  
  public static int getResY() {
    return resY;
  }

  public static void setResY(int resY) {
    Game.resY = resY;
  }

  public static int getResX() {
    return resX;
  }

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

  public static Logger getLogger() {
    return logger;
  }

  public static void setHighScore(int score) {
    highscore = score;
  }

  public static int getHighScore() {
    return highscore;
  }

  public static MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }

  public static void setMediaPlayer(MediaPlayer mediaPlayer) {
    Game.mediaPlayer = mediaPlayer;
  }

  public static Stage getStage() {
    return stage;
  }

  public static void setStage(Stage stage) {
    Game.stage = stage;
  }
}
