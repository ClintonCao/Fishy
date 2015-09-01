package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


/**
 * This class contains all the event handlers of the buttons on the main screen.
 * @author Group 6 
 *
 */
public class MainScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OptionsButton;

    @FXML
    private Button QuitButton;

    @FXML
    private Button PlayButton;

    @FXML
    void PlayEvent(MouseEvent event) {

    }

    @FXML
    void OptionsEvent(MouseEvent event) {

    }

    @FXML
    void QuitEvent(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	
    	assert PlayButton != null : "fx:id=\"PlayButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
        assert OptionsButton != null : "fx:id=\"OptionsButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
        assert QuitButton != null : "fx:id=\"QuitButton\" was not injected: check your FXML file 'Main Screen.fxml'.";

        
        
		PlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				System.out.println("Play Clicked");

			}
		});
		
		OptionsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Game.switchScreen("FXML/OptionsScreen.fxml");

			}
		});
		
		QuitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Platform.exit();

			}
		});
    }
}
