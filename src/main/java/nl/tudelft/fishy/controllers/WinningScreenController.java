package nl.tudelft.fishy.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import nl.tudelft.fishy.factories.FactoryProducer;
import nl.tudelft.fishy.factories.WinningScreenEventHandlerFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This class contains all the event handlers of the buttons on the winning
 * screen.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev, Sunwei Wang.
 *
 */
public class WinningScreenController {
  
  private static FactoryProducer factoryProducer = FactoryProducer.getFactoryProducer();

  @FXML
  private ResourceBundle resources; // NOPMD - Needed for JavaFX.
  @FXML
  private URL location; // NOPMD - Needed for JavaFX.
  @FXML
  private Button MainScreenButton;
  @FXML
  private Button NGPButton;

  /**
   * @param event.
   */
  @FXML
  void MainScreenEvent(MouseEvent event) {

  }

  /**
   * @param event.
   */
  @FXML
  void NGPEvent(MouseEvent event) {

  }

  /**
   * Initialize the button and set up the event handlers for the button.
   */
  @FXML
  void initialize() {
  	
    assert MainScreenButton != null : "fx:id=\"MainScreenButton\" was not injected: " + "check your FXML file 'Untitled'.";
    assert NGPButton != null : "fx:id=\"MainScreenButton\" was not injected: " + "check your FXML file 'Untitled'.";

    WinningScreenEventHandlerFactory winningScreenEventHandlerFactory = (WinningScreenEventHandlerFactory) factoryProducer.getFactory("WINNINGSCREEN");
    
    EventHandler<MouseEvent> mainScreenButtonEventHandler = winningScreenEventHandlerFactory.makeEventHandler("mainscreenbutton");
    MainScreenButton.setOnMouseClicked(mainScreenButtonEventHandler);
    
    EventHandler<MouseEvent> newGamePlusButtonEventHandler = winningScreenEventHandlerFactory.makeEventHandler("newgameplusbutton");
    NGPButton.setOnMouseClicked(newGamePlusButtonEventHandler);
  }
}