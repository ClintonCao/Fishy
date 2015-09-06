package main;

import javafx.scene.image.Image;

public class PlayerFish extends Entity {
	private static String playerFishLeftImageName = "FishOriginal_transparent.png";
	private static String playerFishRightImageName = "Fish_Right_Transparent.png";
	
	private Image playerFishLeftImage;
	private Image playerFishRightImage;
	private boolean isAlive;
	
	public PlayerFish(int movespeed, boolean isAlive, String leftImgFileName, String rightImgFileName, Sprite sprite) {
		super(movespeed, sprite);
		setAlive(isAlive);
		setPlayerFishLeftImage(new Image(leftImgFileName));
		setPlayerFishRightImage(new Image(rightImgFileName));
	}
	
	/**
	 * This method creates the fish the player controls.
	 */
	public static PlayerFish createPlayerFish() {
		Image playerFishImage = new Image(playerFishLeftImageName);
		
		// Create a hitbox for the playerfish. The playerfish will start at the middle of the screen. 
		// So the starting position is the respective screen diameters/2. The size of the hitbox is
		// the size of the image casted to int values.
		AABB aabb = new AABB(MainScreenController.getScreenbox().getWidth()/2, MainScreenController.getScreenbox().getHeight()/2, (int) playerFishImage.getWidth(), (int) playerFishImage.getHeight());
		
		// Create a new 'sprite' using the image and its corresponding hitbox.
		Sprite sprite = new Sprite(playerFishImage, aabb);
		
		return new PlayerFish(10, true, playerFishLeftImageName, playerFishRightImageName, sprite);
	}

	public boolean playerDies(EnemyFish enemyfish) {
		AABB playerAABB = this.getSprite().getAabb();
		AABB enemyAABB = enemyfish.getSprite().getAabb();
		return((playerAABB.getX() * playerAABB.getY()) <= (enemyAABB.getX() * enemyAABB.getY()));
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Image getPlayerFishLeftImage() {
		return playerFishLeftImage;
	}

	public void setPlayerFishLeftImage(Image playerFishLeftImage) {
		this.playerFishLeftImage = playerFishLeftImage;
	}

	public Image getPlayerFishRightImage() {
		return playerFishRightImage;
	}

	public void setPlayerFishRightImage(Image playerFishRightImage) {
		this.playerFishRightImage = playerFishRightImage;
	}

	public static String getPlayerFishLeftImageName() {
		return playerFishLeftImageName;
	}

	public static void setPlayerFishLeftImageName(String playerFishLeftImageName) {
		PlayerFish.playerFishLeftImageName = playerFishLeftImageName;
	}

	public static String getPlayerFishRightImageName() {
		return playerFishRightImageName;
	}

	public static void setPlayerFishRightImageName(String playerFishRightImageName) {
		PlayerFish.playerFishRightImageName = playerFishRightImageName;
	}
}
