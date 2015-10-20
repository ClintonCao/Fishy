package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.interfaces.LosingScreenEventHandlerFactoryInterface;
import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.controllers.MainScreenController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Makes EventHandlers for the buttons of the losing screen. Singleton class.
 * 
 * @author Michiel
 */
public final class LosingScreenEventHandlerFactory implements
    LosingScreenEventHandlerFactoryInterface {

  private static LosingScreenEventHandlerFactory losingScreenEHFactory = null;

  /**
   * Constructor.
   */
  private LosingScreenEventHandlerFactory() {

  }

  /**
   * {@inheritDoc}
   * 
   * @param buttonString
   *          - can be "mainscreenbutton".
   * @return the new EventHandler.
   */
  public EventHandler<MouseEvent> makeEventHandler(String buttonString) {

    switch (buttonString) {

      case "mainscreenbutton":
        return makeMainScreenButtonEventHandler();

      default:
        return null;

    }
  }

  /**
   * Synchronized getter.
   * 
   * @return the Singleton LosingScreenEHFactory.
   */
  public static synchronized LosingScreenEventHandlerFactory getLosingScreenEHFactory() {
    if (losingScreenEHFactory == null) {
      losingScreenEHFactory = new LosingScreenEventHandlerFactory();
    }
    return losingScreenEHFactory;
  }

  /**
   * Overrides the handle method in EventHandler to make the Game switch screens
   * to the main screen, and turn on the music.
   * 
   * @return the new EventHandler.
   */
  private EventHandler<MouseEvent> makeMainScreenButtonEventHandler() {
    return new EventHandler<MouseEvent>() {

    	@Override
    	public void handle(MouseEvent event) {


    		Game.resetPlayerFishSize();
    		MainScreenController.playerFish.setHasLance(false);
    		MainScreenController.setBossMode(false);

    		MainScreenController.playerFish.setHasLance(false);

    		Game.switchScreen("/MainScreen.fxml");
    		if (Game.getMusicOn()) {
    			Game.getMediaPlayer().play();
    		}
    		Game.getLogger().logSwitchScreen("MainScreen");
    	}
    };
  }
}
