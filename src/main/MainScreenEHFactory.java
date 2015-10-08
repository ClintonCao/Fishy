package main;

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

public class MainScreenEHFactory implements MainScreenEHFactoryInterface {
	
	private static final MainScreenEHFactory mainScreenEHFactory = new MainScreenEHFactory();

	private MainScreenEHFactory() {

	}

	public static MainScreenEHFactory getMainScreenEHFactory() {
		return mainScreenEHFactory;
	}

	public EventHandler<MouseEvent> makeEventHandler(String buttonString){

		switch (buttonString) {
		case "playbutton" : return makePlayButtonEventHandler();

		case "menubutton" : return makeMenuButtonEventHandler();

		case "quitbutton" : return makeQuitButtonEventHandler();

		default: return null;

		}
	}

	private EventHandler<MouseEvent> makePlayButtonEventHandler(){
		
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Group root = new Group();
				Scene scene = new Scene(root);
				Game.stage.setScene(scene);

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

	private EventHandler<MouseEvent> makeQuitButtonEventHandler() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Platform.exit();
				Game.getLogger().logEndGame();

			}
		};
	}

	private EventHandler<MouseEvent> makeMenuButtonEventHandler() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.switchScreen("FXML/MenuScreen.fxml");
				Game.getLogger().logSwitchScreen("MenuScreen");

			}
		};
	}
	
	private EventHandler<KeyEvent> makeKeyPressedEventHandler() {
		
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!MainScreenController.input.contains(code)) {
					MainScreenController.input.add(code);
				}
			}
		};
	}
	
	private EventHandler<KeyEvent> makeKeyReleasedEventHandler() {
		
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				MainScreenController.input.remove(code);
			}
		};
	}
}
