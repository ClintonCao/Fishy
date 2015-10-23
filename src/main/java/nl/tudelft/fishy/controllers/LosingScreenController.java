package nl.tudelft.fishy.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import nl.tudelft.fishy.factories.FactoryProducer;
import nl.tudelft.fishy.factories.LosingScreenEventHandlerFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This class contains all the event handlers of the buttons on the losing
 * screen.
 * 
 * @author Clinton Cao, Michiel Doesburg, Matthijs Halvemaan, Dmitry Malarev,
 *         Sunwei Wang.
 *
 */
public class LosingScreenController {
  private static FactoryProducer factoryProducer = FactoryProducer.getFactoryProducer();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button MainScreenButton;

  /**
   * Automatically generated via Scenebuilder.
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

    LosingScreenEventHandlerFactory losingScreenEHFactory = (LosingScreenEventHandlerFactory) factoryProducer.getFactory("LOSINGSCREEN");
    
    EventHandler<MouseEvent> mainScreenButtonEH = losingScreenEHFactory
        .makeEventHandler("mainscreenbutton");

    MainScreenButton.setOnMouseClicked(mainScreenButtonEH);
  }
}