package interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
/**
 * A LosingScreenEHFactory is a factory to create EventHandlers for the losing screen.
 * 
 * @author Michiel
 */
public interface LosingScreenEHFactoryInterface {
	
		/**
		 * Wrapper method for the different kinds of EventHandlers.
		 * @param - the kind of EventHandler you need.
		 */
		EventHandler<MouseEvent> makeEventHandler(String string);
}
