package interfaces;

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
	 * @param string - the kind of EventHandler you need.
	 * @return the new EventHandler.
	 */
	EventHandler<MouseEvent> makeEventHandler(String string);
}
