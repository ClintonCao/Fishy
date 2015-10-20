package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.interfaces.OptionsScreenEventHandlerFactoryInterface;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import nl.tudelft.fishy.Game;

/**
 * A OptionsScreenEventHandlerFactory is a factory to create EventHandlers for the options screen.
 * 
 * @author Michiel
 */
public final class OptionsScreenEventHandlerFactory implements OptionsScreenEventHandlerFactoryInterface {
  private static OptionsScreenEventHandlerFactory optionsScreenEventHandlerFactory = null;

  /**
   * Constructor.
   */
  private OptionsScreenEventHandlerFactory() {

  }

  /**
   * {@inheritDoc}
   * @param buttonString - can be "onbutton", "offbutton" or "backbutton".
   * @return the new EventHandler.
   */
  public EventHandler<MouseEvent> makeEventHandler(String buttonString) {

    switch (buttonString) {

    case "onbutton" : return makeOptionsScreenOnButtonEventHandler();
    case "offbutton" : return makeOptionsScreenOfButtonEventHandler();
    case "backbutton" : return makeOptionsScreenBackButtonEventHandler();

    default: return null;

    }
  }

  /**
   * Synchronized getter.
   * @return the Singleton OptionsScreenEventHandlerFactory.
   */
  public static synchronized OptionsScreenEventHandlerFactory getOptionsScreenEventHandlerFactory() {
    if (optionsScreenEventHandlerFactory == null) {
      optionsScreenEventHandlerFactory = new OptionsScreenEventHandlerFactory();
    }
    return optionsScreenEventHandlerFactory;
  }

  private EventHandler<MouseEvent> makeOptionsScreenOnButtonEventHandler() {

    return new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Game.getMediaPlayer().play();
        Game.setMusicOn(true);
        Game.getLogger().logMusicOnOff(true);
      }
    };
  }

  private EventHandler<MouseEvent> makeOptionsScreenOfButtonEventHandler() {

    return new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Game.getMediaPlayer().stop();
        Game.setMusicOn(false);
        Game.getLogger().logMusicOnOff(false);
      }
    };
  }

  private EventHandler<MouseEvent> makeOptionsScreenBackButtonEventHandler() {

    return new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Game.switchScreen("/MainScreen.fxml");
        Game.getLogger().logSwitchScreen("MainScreen");
      }
    };
  }
}
