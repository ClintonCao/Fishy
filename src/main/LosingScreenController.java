package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LosingScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainScreenButton;

    @FXML
    void MainScreenEvent(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert MainScreenButton != null : "fx:id=\"MainScreenButton\" was not injected: check your FXML file 'LosingScreen.fxml'.";
        
        
        MainScreenButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.switchScreen("FXML/MainScreen.fxml");

			}
		});
    }
}