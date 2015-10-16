package main;

import interfaces.GameInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

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
    loadHighScore("highscore.txt");
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
      e.printStackTrace();
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
  
  /**
   * Reset the playerFish size and its images.
   */
  public static void resetPlayerFishSize() {
  	Image temp = new Image("FishOriginal_transparent.png");

  	int imgWidth = (int) (temp.getWidth() * 0.30);
  	int imgHeight = (int) (temp.getHeight() * 0.30);

  	Image playerFishImageLeft = 
  			new Image("FishOriginal_transparent.png", imgWidth, imgHeight, true, true);

  	Image playerFishImageRight = 
  			new Image("Fish_Right_Transparent.png", imgWidth, imgHeight, true, true);

  	PlayerFish playerFish = MainScreenController.playerFish;
  	Sprite pfSprite = playerFish.getSprite();
  	BoundingBox pfbb = pfSprite.getBoundingBox();

  	pfSprite.setImg(playerFishImageLeft);

  	playerFish.setHasLance(false);
  	MainScreenController.setBossMode(false);

  	pfbb.setWidth((int) playerFishImageLeft.getWidth());
  	pfbb.setHeight((int) playerFishImageLeft.getHeight());

  	playerFish.setPlayerFishLeftImageName("FishOriginal_transparent.png");
  	playerFish.setPlayerFishLeftImage(playerFishImageLeft);
  	playerFish.setPlayerFishRightImageName("Fish_Right_Transparent.png");
  	playerFish.setPlayerFishRightImage(playerFishImageRight);
  }


   /** This method saves the highscore onto a text file.
   * 
   * @param infile - The name of the file
   */
  public static void saveHighScore(String infile) {
    try {
      File file = new File(infile);
      BufferedWriter output = new BufferedWriter(new FileWriter(file,false));
      output.write("" + getHighScore());
      output.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  /**
   * This method loads the highscore from the tetx file.
   * 
   * @param infile - The name of the file
   */
  public static void loadHighScore(String infile) {
    try {
      File file = new File(infile);
      Scanner sc = new Scanner(file);
      int read = sc.nextInt();
      setHighScore(read);
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // --- Getters and Setters ---
  
  public static int getResY() {
    return resY;
  }

  public static int getResX() {
    return resX;
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

  private static void setMediaPlayer(MediaPlayer mediaPlayer) {
    Game.mediaPlayer = mediaPlayer;
  }

  public static Stage getStage() {
    return stage;
  }

  private static void setStage(Stage stage) {
    Game.stage = stage;
  }
 
}
