package nl.tudelft.fishy.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import nl.tudelft.fishy.factories.OptionsScreenEventHandlerFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This class contains all the event handlers of the buttons on the options
 * screen.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 *
 */
public class OptionsController {

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
  	
    assert OnButton != null : "fx:id=\"OnButton\" was not injected: " + "check your FXML file 'OptionsScreen.fxml'.";
    assert OffButton != null : "fx:id=\"OffButton\" was not injected: " + "check your FXML file 'OptionsScreen.fxml'.";
    assert BackButton != null : "fx:id=\"BackButton\" was not injected: " + "check your FXML file 'OptionsScreen.fxml'.";

    OptionsScreenEventHandlerFactory optionsScreenEventHandlerFactory = OptionsScreenEventHandlerFactory.getOptionsScreenEventHandlerFactory();
    
    EventHandler<MouseEvent> onButtonEventHandler = optionsScreenEventHandlerFactory.makeEventHandler("onbutton");
    OnButton.setOnMouseClicked(onButtonEventHandler);

    EventHandler<MouseEvent> ofButtonEventHandler = optionsScreenEventHandlerFactory.makeEventHandler("offbutton");
    OffButton.setOnMouseClicked(ofButtonEventHandler);
    		
    EventHandler<MouseEvent> backButtonEventHandler = optionsScreenEventHandlerFactory.makeEventHandler("backbutton");
    BackButton.setOnMouseClicked(backButtonEventHandler);
  }
}