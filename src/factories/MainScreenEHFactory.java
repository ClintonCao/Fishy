package factories;

import main.Game;
import main.MainScreenController;
import interfaces.MainScreenEHFactoryInterface;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Makes EventHandlers for the buttons of the main screen.
 * Singleton class.
 * @see MainScreenController
 * @author Michiel
 *
 */
public class MainScreenEHFactory implements MainScreenEHFactoryInterface {
	
	private static MainScreenEHFactory mainScreenEHFactory = null;
	
	/**
	 * Constructor.
	 */
	private MainScreenEHFactory() {

	}
	
	/**
	 * Synchronized getter.
	 * @return the singleton MainScreenEHFactory
	 */
	public static synchronized MainScreenEHFactory getMainScreenEHFactory() {
		if(mainScreenEHFactory == null) {
			mainScreenEHFactory = new MainScreenEHFactory();
		}
		return mainScreenEHFactory;
	}
	
	/**
	 * {@inheritDoc} Switch case.
	 * @return the new EventHandler.
	 */
	public EventHandler<MouseEvent> makeEventHandler(String buttonString){

		switch (buttonString) {
		case "playbutton" : return makePlayButtonEventHandler();

		case "menubutton" : return makeMenuButtonEventHandler();

		case "quitbutton" : return makeQuitButtonEventHandler();

		default: return null;

		}
	}
	
	/**
	 * This EventHandler contains the game loop.
	 * @return new play button Event Handler.
	 */
	private EventHandler<MouseEvent> makePlayButtonEventHandler(){
		
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Group root = new Group();
				Scene scene = new Scene(root);
				Game.getStage().setScene(scene);

				Canvas canvas = new Canvas(Game.getResX(), Game.getResY());

				root.getChildren().add(canvas);

				scene.setOnKeyPressed(makeKeyPressedEventHandler());

				scene.setOnKeyReleased(makeKeyReleasedEventHandler());

				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				AnimationTimerFactory animationTimerFactory = AnimationTimerFactory.getAnimationTimerFactory();
				AnimationTimer animationTimer = animationTimerFactory.makeAnimationTimer(gc);
				
				animationTimer.start();
			}
		};
	}
	
	/**
	 * Overrides handle method in EventHandler to exit the platform.
	 * @return new quit button EventHandler.
	 */
	private EventHandler<MouseEvent> makeQuitButtonEventHandler() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Platform.exit();
				Game.getLogger().logEndGame();
			}
		};
	}
	
	/**
	 * Overrides handle method in EventHandler to switch the Game to the menu screen.
	 * @return the new menuscreen button EventHandler.
	 */
	private EventHandler<MouseEvent> makeMenuButtonEventHandler() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.switchScreen("FXML/MenuScreen.fxml");
				Game.getLogger().logSwitchScreen("MenuScreen");

			}
		};
	}
	
	/**
	 * Overrides handle method in EventHandler to put the keys pressed into ArrayList.
	 * @return the new 'key pressed' EventHandler.
	 */
	private EventHandler<KeyEvent> makeKeyPressedEventHandler() {
		
		return new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!MainScreenController.input.contains(code)) {
					MainScreenController.input.add(code);
				}
			}
		};
	}
	
	/**
	 * Overrides handle method in EventHandler to remove the keys released from the input ArrayList.
	 * @return the new 'key released' EventHandler.
	 */
	private EventHandler<KeyEvent> makeKeyReleasedEventHandler() {
		
		return new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				MainScreenController.input.remove(code);
			}
		};
	}
}
