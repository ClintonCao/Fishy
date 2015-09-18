package main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class contains all the event handlers of the buttons on the options
 * screen.
 * 
 * @author Group 6
 *
 */
public class OptionsController {
  
  private static Logger logger = new Logger(null, null);

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button OnButton;

  @FXML
  private Button OffButton;

  @FXML
  private Button BackButton;

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void MusicOnEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void MusicOffEvent(MouseEvent event) {

  }

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void BackButtonEvent(MouseEvent event) {

  }

  /**
   * Initialize the buttons and set up the event handlers for each button.
   */
  @FXML
  void initialize() {
    assert OnButton != null : "fx:id=\"OnButton\" was not injected: "
        + "check your FXML file 'OptionsScreen.fxml'.";
    assert OffButton != null : "fx:id=\"OffButton\" was not injected: "
        + "check your FXML file 'OptionsScreen.fxml'.";
    assert BackButton != null : "fx:id=\"BackButton\" was not injected: "
        + "check your FXML file 'OptionsScreen.fxml'.";

    OnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        logger.logMusicOnOff(true);

      }
    });

    OffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        logger.logMusicOnOff(false);

      }
    });

    BackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        Game.switchScreen("FXML/MainScreen.fxml");
        logger.logSwitchScreen("MainScreen");

      }
    });
  }
}