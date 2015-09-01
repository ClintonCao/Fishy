package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

	static Stage stage;
	static Pane pane;
//	final URL resource = getClass().getResource("FXML/track.mp3");
//    final Media media = new Media(resource.toString());
//    static MediaPlayer mediaPlayer = null;
    
    
    
    
	public static void main(String[] args) {
		Application.launch(Game.class, (java.lang.String[]) null);
	}

	/**
	 * Deze methode wordt gebruikt om de begin scene van Football Manager op te
	 * laden
	 * 
	 * @param firstStage
	 *            is de stage waar het start.
	 */
	@Override
	public void start(Stage firstStage) {
		try {

			stage = firstStage;

			firstStage.setTitle("Fishy");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"FXML/MainScreen.fxml"));

			pane = (Pane) loader.load();

			Scene scene = new Scene(pane);
//			mediaPlayer = new MediaPlayer(media);
//		    mediaPlayer.play();
//		    mediaPlayer.setCycleCount(Integer.MAX_VALUE);
			firstStage.setHeight(715);
	        firstStage.setWidth(1160);
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Game.class.getResource(bestand));
			stage.getScene().setRoot((Parent) loader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

