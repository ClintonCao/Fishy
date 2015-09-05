package main;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game2 extends Application {
	private static PlayerFish playerfish;
	private static ArrayList<Entity> sprites;
	private static AABB screenbox;
	private static EnemyFish otherfish;

	public static void main(String[] args) {
		initialize();
		launch(args);
	}
	
	/**
	 * Set up things we need. Initialize the sprite list, and create the playerfish.
	 */
	public static void initialize() {
		sprites = new ArrayList<Entity>();
		createPlayerFish();
		createEnemyFish();
	}
	
	/**
	 * This method creates the fish the player controls.
	 */
	public static void createPlayerFish() {
		AABB aabb = new AABB(0, 0, 128, 128);
		Sprite sprite = new Sprite(new Image("Fish.png"), 0, 0, 0, 0, aabb);
		playerfish = new PlayerFish(10, true, sprite);
	}
	
	/**
	 * Temp method.
	 */
	public static void createEnemyFish() {
		AABB aabb = new AABB(800, 800, 128, 128);
		Sprite sprite = new Sprite(new Image("Fish.png"), 800, 800, 0, 0, aabb);
		otherfish = new EnemyFish(10, true, sprite);
		sprites.add(otherfish);
	}
	
	/**
	 * This is the method that runs the whole game. It contains the game loop,
	 * and what to render on screen.
	 */
	public void start(Stage stage) {
		stage.setTitle("Fishy");
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(1870,1030);
		
		root.getChildren().add(canvas);
		
		ArrayList<String> input = new ArrayList<String>();
		
		scene.setOnKeyPressed(
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						if(!input.contains(code)) {
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
		
		new AnimationTimer() {
			public void handle(long currentNTime) {
				
				// Draw the background every frame.
				gc.drawImage(background, 0, 0);
				
				
				// Control the playerfish using WASD.
				if(input.contains("A")) {
					playerfish.getSprite().updateX(-playerfish.getMovespeed());
				} else if(input.contains("D")) {
					playerfish.getSprite().updateX(playerfish.getMovespeed());
				} 
				if(input.contains("W")) {
					playerfish.getSprite().updateY(-playerfish.getMovespeed());
				} else if(input.contains("S")) {
					playerfish.getSprite().updateY(playerfish.getMovespeed());
				}
				
				// If the playerfish intersects another fish, remove it.
				for(int i = 0; i < sprites.size(); i++) {
					if(playerfish.getSprite().intersects(sprites.get(i).getSprite())) {
						sprites.remove(i);
					}
				}
				
				// Render the playerfish.
				playerfish.getSprite().render(gc);
				
				// Render all the remaining fish.
				for(int i = 0; i < sprites.size(); i++) {
					sprites.get(i).getSprite().render(gc);
				}
			}
		}.start();
		
		stage.show();
	}

}
