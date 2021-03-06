package nl.tudelft.fishy.interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * A MainScreenEHFactory is a factory to create AnimationTimers.
 * 
 * @author Michiel
 */
public interface MainScreenEventHandlerFactoryInterface {

  /**
   * Wrapper method for the kinds of EventHandler to produce.
   * 
   * @return the new EventHandler.
   */
  EventHandler<MouseEvent> makeEventHandler(String string);
}
