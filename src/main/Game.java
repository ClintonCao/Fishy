package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
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

  // final URL resource = getClass().getResource("FXML/track.mp3");
  // final Media media = new Media(resource.toString());
  // static MediaPlayer mediaPlayer = null;

  /**
   * Main method that launches the application.
   * 
   * @param args
   *          arguments for the main method (nothing is used).
   */
  public static void main(String[] args) {
    Application.launch(Game.class, (java.lang.String[]) null);
  }

  /**
   * @param firstStage
   *          The stage where the game starts from
   */
  @Override
  public void start(Stage firstStage) {
    try {

      stage = firstStage;

      firstStage.setTitle("Fishy");
      System.out.println("Loading game's icon..");
      stage.getIcons().add(
          new Image(Game.class.getResourceAsStream("FXML/Fish.png")));
      System.out.println("Successfully loaded..");
      System.out.println("Loading main screen of game..");
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "FXML/MainScreen.fxml"));

      pane = (Pane) loader.load();
      System.out.println("Successfully loaded..");

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
      System.out.println("Loading " + bestand.toString() + "..");
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Game.class.getResource(bestand));
      stage.getScene().setRoot((Parent) loader.load());
      System.out.println("Successfully loaded..");
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
}
