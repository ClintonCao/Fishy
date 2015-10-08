package factories;

import interfaces.LosingScreenEHFactoryInterface;
import main.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Makes EventHandlers for the buttons of the losing screen.
 * Singleton class.
 * @author Michiel
 */
public class LosingScreenEHFactory implements LosingScreenEHFactoryInterface{
	
	private static LosingScreenEHFactory losingScreenEHFactory = null;
	
	/**
	 * Constructor.
	 */
	private LosingScreenEHFactory() {
		
	}
	
	/**
	 * {@inheritDoc}
	 * @return the new EventHandler.
	 */
	public EventHandler<MouseEvent> makeEventHandler(String buttonString){

		switch (buttonString) {

		case "mainscreenbutton" : return makeMainScreenButtonEventHandler();

		default: return null;

		}
	}
	
	/**
	 * Synchronized getter.
	 * @return the Singleton LosingScreenEHFactory.
	 */
	public static synchronized LosingScreenEHFactory getLosingScreenEHFactory() {
		if(losingScreenEHFactory == null) {
			losingScreenEHFactory = new LosingScreenEHFactory();
		}
		return losingScreenEHFactory;
	}
	
	/**
	 * Overrides the handle method in EventHandler to make the Game switch screens
	 * to the main screen, and turn on the music.
	 * @return the new EventHandler.
	 */
	private EventHandler<MouseEvent> makeMainScreenButtonEventHandler() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.switchScreen("FXML/MainScreen.fxml");
				if (Game.getMusicOn()) {
					Game.getMediaPlayer().play();
				}
				Game.getLogger().logSwitchScreen("MainScreen");
			}
		};
	}
}
