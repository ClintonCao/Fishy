package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
	private int frames;
	private final double multiplier = 1.01;
	private static Text scoreText = new Text();
	private static int currScore = 0;

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
		entities = new ArrayList<EnemyFish>();
		setScreenbox(new AABB(0, 0, Game.resX, Game.resY));
		playerFish = PlayerFish.createPlayerFish();
		scoreText.setText("Score");
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

				Canvas canvas = new Canvas(Game.resX, Game.resY);

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
						gc.setFill(Color.AQUA);
						gc.fillOval(525, 1, 200, 75);
						
						// Draw the scoreboard with the player's score.
						gc.setFill(Color.BLACK);
						gc.setFont(Font.font("Comic Sans", 30));
						gc.fillText(scoreText.getText().toString(), 625, 20);
				        gc.setTextAlign(TextAlignment.CENTER);
				        gc.setTextBaseline(VPos.CENTER);
						gc.fillText(Integer.toString(playerFish.getScore()), 625, 55);
						
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
								playerFish.grow(multiplier);
								
								// first get the height of enemy fish as the score
								int score = entities.get(i).getSprite().getAabb().getHeight();
								// then adds the score to the current score
								currScore = currScore + score;
								// finally sets the total score to the player fish
								playerFish.setScore(currScore);
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
				System.out.println("Exited successfully..");

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
