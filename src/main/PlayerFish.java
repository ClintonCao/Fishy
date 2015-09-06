package main;

import javafx.scene.image.Image;

public class PlayerFish extends Entity {
	private boolean isAlive;
	
	public PlayerFish(int movespeed, boolean isAlive, Sprite sprite) {
		super(movespeed, sprite);
		this.isAlive = isAlive;
	}
	
	/**
	 * This method creates the fish the player controls.
	 */
	public static PlayerFish createPlayerFish() {
		Image playerFishImage = new Image(MainScreenController.getFishFile());
		
		// Create a hitbox for the playerfish. The playerfish will start at the middle of the screen. 
		// So the starting position is the respective screen diameters/2. The size of the hitbox is
		// the size of the image casted to int values.
		AABB aabb = new AABB(MainScreenController.getScreenbox().getWidth()/2, MainScreenController.getScreenbox().getHeight()/2, (int) playerFishImage.getWidth(), (int) playerFishImage.getHeight());
		
		// Create a new 'sprite' using the image and its corresponding hitbox.
		Sprite sprite = new Sprite(playerFishImage, aabb);
		
		return new PlayerFish(10, true, sprite);
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
}
