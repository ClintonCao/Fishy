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
 * @author Group 6 
 *
 */
public class MainScreenController {
	
	private static PlayerFish playerfish;
	private static ArrayList<EnemyFish> sprites;
	private static EnemyFish otherfish;
	private static int resX;
	private static int resY;
	private int frames;

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
	 * Set up things we need. Initialize the sprite list, and create the playerfish.
	 */
	public static void init() {
		resX = 1870;
		resY = 1030;
		sprites = new ArrayList<EnemyFish>();
		setScreenbox(new AABB(0, 0, resX, resY));
		createPlayerFish();
		//createEnemyFish();
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
	
	public static void setScreenbox(AABB screenbox) {
		Game.screenbox = screenbox;
	}

    @FXML
    void initialize() {
    	
    	assert PlayButton != null : "fx:id=\"PlayButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
        assert MenuButton != null : "fx:id=\"OptionsButton\" was not injected: check your FXML file 'Main Screen.fxml'.";
        assert QuitButton != null : "fx:id=\"QuitButton\" was not injected: check your FXML file 'Main Screen.fxml'.";

        
        
		PlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				init();
				Group root = new Group();
				Scene scene = new Scene(root);
				Game.stage.setScene(scene);
				
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
				frames = 0;
				
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
						
						if(frames%180==0) {
							sprites.add(EnemyFish.generateFish());
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
							EnemyFish curr = sprites.get(i);
							if(curr.isLefty()){
								curr.getSprite().updateX(curr.getMoveSpeed());
							} else {
								curr.getSprite().updateX(-curr.getMoveSpeed());
							}
							sprites.get(i).getSprite().render(gc);
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

			}
		});
    }
}
