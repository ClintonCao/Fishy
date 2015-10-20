package nl.tudelft.fishy.interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * A WinningScreenEventHandlerFactory is a factory to create EventHandlers for
 * the winning screen.
 * 
 * @author Michiel
 */
public interface WinningScreenEventHandlerFactoryInterface {

  /**
   * Wrapper method for the different kinds of EventHandlers.
   */
  EventHandler<MouseEvent> makeEventHandler(String string);
}
