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
	private static int resX;
	private static int resY;

	public static void main(String[] args) {
		initialize();
		launch(args);
	}
	
	/**
	 * Set up things we need. Initialize the sprite list, and create the playerfish.
	 */
	public static void initialize() {
		resX = 1870;
		resY = 1030;
		sprites = new ArrayList<Entity>();
		setScreenbox(new AABB(0, 0, resX, resY));
		createPlayerFish();
		createEnemyFish();
	}
	
	/**
	 * This method creates the fish the player controls.
	 */
	public static void createPlayerFish() {
		AABB aabb = new AABB(resX/2, resY/2, 128, 128);
		Sprite sprite = new Sprite(new Image("Fish.png"), aabb);
		playerfish = new PlayerFish(10, true, sprite);
	}
	
	/**
	 * Temp method.
	 */
	public static void createEnemyFish() {
		AABB aabb = new AABB(800, 800, 128, 128);
		Sprite sprite = new Sprite(new Image("Fish.png"), aabb);
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
		
		Canvas canvas = new Canvas(resX,resY);
		
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
				if(input.contains("A") && !playerfish.intersectsLeftScreenEdge()) {
					playerfish.getSprite().updateX(-playerfish.getMoveSpeed());
				} else if(input.contains("D") && !playerfish.intersectsRightScreenEdge()) {
					playerfish.getSprite().updateX(playerfish.getMoveSpeed());
				} 
				if(input.contains("W") && !playerfish.intersectsUpperScreenEdge()) {
					playerfish.getSprite().updateY(-playerfish.getMoveSpeed());
				} else if(input.contains("S") && !playerfish.intersectsUnderScreenEdge()) {
					playerfish.getSprite().updateY(playerfish.getMoveSpeed());
				}
				
				// If the playerfish intersects another fish, remove it.
				for(int i = 0; i < sprites.size(); i++) {
					if(playerfish.intersects(sprites.get(i))) {
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

	public static AABB getScreenbox() {
		return screenbox;
	}

	public static void setScreenbox(AABB screenbox) {
		Game2.screenbox = screenbox;
	}

}
