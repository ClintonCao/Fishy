package interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface AnimationTimerFactoryInterface {
	
	EventHandler<MouseEvent> makeEventHandler();
}
