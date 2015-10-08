package interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface MainScreenEHFactoryInterface {
	
	EventHandler<MouseEvent> makeEventHandler(String button);
}
