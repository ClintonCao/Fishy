package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

	static Stage stage;
	static Pane pane;
	static int resX = 1280;
	static int resY = 720;
	

	// final URL resource = getClass().getResource("FXML/track.mp3");
	// final Media media = new Media(resource.toString());
	// static MediaPlayer mediaPlayer = null;

	public static void main(String[] args) {
		Application.launch(Game.class, (java.lang.String[]) null);
	}

	/**
	 * @param firstStage
	 *            is de stage waar het start.
	 */
	@Override
	public void start(Stage firstStage) {
		try {

			stage = firstStage;	

			firstStage.setTitle("Fishy");
			System.out.println("Loading game's icon..");
			stage.getIcons().add(
					new Image(Game.class.getResourceAsStream("FXML/Fish.png")));
			System.out.println("Successfully loaded..");
			System.out.println("Loading main screen of game..");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"FXML/MainScreen.fxml"));

			pane = (Pane) loader.load();
			System.out.println("Successfully loaded..");
			
			Scene scene = new Scene(pane);
			firstStage.setHeight(resY);
			firstStage.setWidth(resX);
			firstStage.setResizable(false);
			firstStage.setScene(scene);
			
			
			firstStage.show();

		} catch (Exception e) {
			System.out.println("File is not found " + e);
		}
	}

	/**
	 * Deze methode wordt gebruikt voor het veranderen tussen scenes
	 * 
	 * @param bestand
	 *            is de naam van de FXML bestand van de scene die je wilt laden.
	 */
	public static void switchScreen(String bestand) {
		try {
			System.out.println("Loading "+ bestand.toString()+ "..");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Game.class.getResource(bestand));
			stage.getScene().setRoot((Parent) loader.load());
			System.out.println("Successfully loaded..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
