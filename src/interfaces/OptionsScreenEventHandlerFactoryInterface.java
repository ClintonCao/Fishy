package interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * A OptionsScreenEventHandlerFactory is a factory to create EventHandlers for
 * the options screen.
 * 
 * @author Michiel
 */
public interface OptionsScreenEventHandlerFactoryInterface {

  /**
   * Wrapper method for the different kinds of EventHandlers.
   */
  EventHandler<MouseEvent> makeEventHandler(String string);
}
