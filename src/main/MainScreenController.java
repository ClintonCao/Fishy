package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * This class contains all the event handlers of the buttons on the main screen.
 * 
 * @author Group 6
 *
 */
public class MainScreenController {

	private static PlayerFish playerFish;
	private static ArrayList<EnemyFish> entities;
	private static AABB screenbox;
	private static int resX;
	private static int resY;
	private int frames;
	private final double multiplier = 1.01;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button MenuButton;

	@FXML
	private Button QuitButton;

	@FXML
	private Button PlayButton;

	@FXML
	void PlayEvent(MouseEvent event) {

	}

	@FXML
	void MenuEvent(MouseEvent event) {

	}

	@FXML
	void QuitEvent(MouseEvent event) {

	}

	/**
	 * Set up things we need. Initialize the sprite list, and create the
	 * playerfish.
	 */
	public static void init() {
		resX = 1870;
		resY = 1030;
		entities = new ArrayList<EnemyFish>();
		setScreenbox(new AABB(0, 0, resX, resY));
		playerFish = PlayerFish.createPlayerFish();
	}

	@FXML
	void initialize() {

		assert PlayButton != null : "fx:id=\"PlayButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
		assert MenuButton != null : "fx:id=\"OptionsButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
		assert QuitButton != null : "fx:id=\"QuitButton\" was not injected: check your FXML file 'Main Screen.fxml'.";

		PlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				
				System.out.println("Initializing game objects..");
				init();
				System.out.println("Successfully initialized..");
				Group root = new Group();
				Scene scene = new Scene(root);
				Game.stage.setScene(scene);

				Canvas canvas = new Canvas(resX, resY);

				root.getChildren().add(canvas);

				ArrayList<String> input = new ArrayList<String>();

				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						if (!input.contains(code)) {
							input.add(code);
						}
					}
				});

				scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						input.remove(code);
					}
				});

				GraphicsContext gc = canvas.getGraphicsContext2D();
				Image background = new Image("Fishy_bg.jpg");
				frames = 0;

				new AnimationTimer() {
					public void handle(long currentNTime) {

						// Draw the background every frame.
						gc.drawImage(background, 0, 0);

						// Control the playerfish using WASD.
						if (input.contains("A")
								&& !playerFish.intersectsLeftScreenEdge()) {

							// Set the image of the playerfish to the 'looking
							// to the left version'.
							playerFish.getSprite().setImg(
									playerFish.getPlayerFishLeftImage());
							playerFish.getSprite().updateX(
									-playerFish.getMoveSpeed());

						} else if (input.contains("D")
								&& !playerFish.intersectsRightScreenEdge()) {

							// Set the image of the playerfish to the 'looking
							// to the right version'.
							playerFish.getSprite().setImg(
									playerFish.getPlayerFishRightImage());
							playerFish.getSprite().updateX(
									playerFish.getMoveSpeed());
						}
						if (input.contains("W")
								&& !playerFish.intersectsUpperScreenEdge()) {

							playerFish.getSprite().updateY(
									-playerFish.getMoveSpeed());

						} else if (input.contains("S")
								&& !playerFish.intersectsUnderScreenEdge()) {

							playerFish.getSprite().updateY(
									playerFish.getMoveSpeed());
						}

						// Generate an enemy fish every so many frames.
						if (frames % 45 == 0) {
							entities.add(EnemyFish.generateFish());
						}

						// If the playerfish intersects another fish, remove it.
						for (int i = 0; i < entities.size(); i++) {
							// First check if a fish is outside the screen, if
							// it is, remove it.
							if (!entities.get(i).getSprite().getAabb()
									.intersects(screenbox)) {
								entities.remove(i);
								// Secondly check if a fish is intersecting with
								// the playerfish, if it is, remove it.
							} else if (playerFish.intersects(entities.get(i))
									&& playerFish.isAlive()) {
								entities.remove(i);
								playerFish.getSprite().grow(multiplier);
								int newHeight = playerFish.getSprite().getAabb()
										.getHeight();
								int newWidth = playerFish.getSprite().getAabb()
										.getWidth();
								playerFish.setPlayerFishLeftImage(new Image(
										"FishOriginal_transparent.png", newHeight,
										newWidth, false, false));
								playerFish.setPlayerFishRightImage(new Image(
										"Fish_Right_Transparent.png", newHeight,
										newWidth, false, false));
							}
						}

						// Render the playerfish.
						playerFish.getSprite().render(gc);

						// Render all the remaining fish.
						for (int i = 0; i < entities.size(); i++) {
							EnemyFish curr = entities.get(i);
							if (curr.isLefty()) {
								curr.getSprite().updateX(curr.getMoveSpeed());
							} else {
								curr.getSprite().updateX(-curr.getMoveSpeed());
							}
							entities.get(i).getSprite().render(gc);
						}
						frames++;
					}
				}.start();

			}
		});

		MenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Game.switchScreen("FXML/MenuScreen.fxml");

			}
		});

		QuitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Platform.exit();
				System.out.println("Quiting application..");
				System.out.println("Successfully quitted..");

			}
		});
	}

	private static void setScreenbox(AABB aabb) {
		screenbox = aabb;
	}

	public static AABB getScreenbox() {
		return screenbox;
	}
}
