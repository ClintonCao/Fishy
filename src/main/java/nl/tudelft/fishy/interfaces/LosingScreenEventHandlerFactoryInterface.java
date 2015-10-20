package nl.tudelft.fishy.interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * A LosingScreenEHFactory is a factory to create EventHandlers for the losing
 * screen.
 * 
 * @author Michiel
 */
public interface LosingScreenEventHandlerFactoryInterface {

  /**
   * Wrapper method for the different kinds of EventHandlers.
   */
  EventHandler<MouseEvent> makeEventHandler(String string);
}
