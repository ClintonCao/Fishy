package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.interfaces.WinningScreenEventHandlerFactoryInterface;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import nl.tudelft.fishy.Game;
import nl.tudelft.fishy.GameLoop;
import nl.tudelft.fishy.controllers.MainScreenController;

/**
 * Makes EventHandlers for the buttons of the winning screen.
 * Singleton class.
 * 
 * @author Michiel
 */
public final class WinningScreenEventHandlerFactory implements WinningScreenEventHandlerFactoryInterface {

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

        Game.resetPlayerFishSize();     
        GameLoop.playerFish.setHasLance(false);
        GameLoop.setBossMode(false);  
        
        int score = GameLoop.getPlayerFish().getScore();
        GameLoop.setCurrScore(score);
        if (Game.getMusicOn()) {
          Game.getMediaPlayer().play();
        }
        Game.switchScreen("/MainScreen.fxml");

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
      	
      	Game.resetPlayerFishSize();    
      	GameLoop.playerFish.setHasLance(false);
        GameLoop.setBossMode(false);
        
        Game.setNewGamePlusMode(false);
        Game.switchScreen("/MainScreen.fxml");
        if (Game.getMusicOn()) {
          Game.getMediaPlayer().play();
        }
        Game.getLogger().logSwitchScreen("MainScreen");

      }
    };
  }
}
