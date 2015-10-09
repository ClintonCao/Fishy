package factories;

import interfaces.WinningScreenEventHandlerFactoryInterface;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import main.Game;
import main.MainScreenController;

/**
 * Makes EventHandlers for the buttons of the winning screen.
 * Singleton class.
 * 
 * @author Michiel
 */
public class WinningScreenEventHandlerFactory implements WinningScreenEventHandlerFactoryInterface {

	private static WinningScreenEventHandlerFactory winningScreenEventHandlerFactory = null;

	/**
	 * Constructor.
	 */
	private WinningScreenEventHandlerFactory() {

	}

	/**
	 * {@inheritDoc}
	 * @param buttonString - can be "mainscreenbutton" or "newgameplusbutton".
	 * @return the new EventHandler.
	 */
	public EventHandler<MouseEvent> makeEventHandler(String buttonString) {

		switch (buttonString) {

		case "mainscreenbutton" : return makeMainScreenButtonEventHandler();
		case "newgameplusbutton" : return makeNewGamePlusButtonEventHandler();

		default: return null;

		}
	}

	/**
	 * Synchronized getter.
	 * @return the Singleton WinningScreenEventHandlerFactory.
	 */
	public static synchronized WinningScreenEventHandlerFactory getWinningScreenEventHandlerFactory() {
		if (winningScreenEventHandlerFactory == null) {
			winningScreenEventHandlerFactory = new WinningScreenEventHandlerFactory();
		}
		return winningScreenEventHandlerFactory;
	}
	
	/**
	 * Overrides the handle method in EventHandler to make the Game go into
	 * new game plus mode. 
	 * @return the new EventHandler.
	 */
	private EventHandler<MouseEvent> makeNewGamePlusButtonEventHandler() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.setNewGamePlusMode(true);
				int score = MainScreenController.getPlayerFish().getScore();
				MainScreenController.setCurrScore(score);
				if (Game.getMusicOn()) {
					Game.getMediaPlayer().play();
				}
				Game.switchScreen("FXML/MainScreen.fxml");

			}
		};
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
				Game.setNewGamePlusMode(false);
				Game.switchScreen("FXML/MainScreen.fxml");
				if (Game.getMusicOn()) {
					Game.getMediaPlayer().play();
				}
				Game.getLogger().logSwitchScreen("MainScreen");

			}
		};
	}
}
