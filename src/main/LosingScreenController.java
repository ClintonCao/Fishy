package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This class contains all the event handlers of the buttons on the losing
 * screen.
 * 
 * @author Group 6
 *
 */
public class LosingScreenController {
  private static Logger logger = new Logger();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button MainScreenButton;

  /**
   * Automatically generated via Scenebuilder.
   * 
   * @param event
   *          kind of event.
   */
  @FXML
  void MainScreenEvent(MouseEvent event) {

  }

  /**
   * Initialize the button and set up the event handlers for the button.
   * 
   */
  @FXML
  void initialize() {
    assert MainScreenButton != null : "fx:id=\"MainScreenButton\" "
        + "was not injected: check your FXML file 'LosingScreen.fxml'.";

    MainScreenButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Game.switchScreen("FXML/MainScreen.fxml");
        if(Game.getMusicOn()) {
        	Game.mediaPlayer.play();
        }
        logger.logSwitchScreen("MainScreen");
      }
    });
  }
}