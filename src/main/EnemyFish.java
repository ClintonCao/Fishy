package main;

import java.util.Random;

import javafx.scene.image.Image;

/**
 * An enemy fish is any fish other than the player fish. It inherits
 * the movement speed and sprite attributes from the general entity class.
 * The added boolean 'isLefty' simply indicates if the fish spawns on the left side of the screen.
 * If 'isLefty' is false, the fish spawns at the right side of the screen.
 * @author Clinton Cao, Dmity Malarev, Matthijs Halvemaan, Sunwei Wang, Michiel Doesburg.
 */
public class EnemyFish extends Entity {
	private boolean isLefty;
	
	public EnemyFish(int movespeed, boolean isLefty, Sprite sprite) {
		super(movespeed, sprite);
		this.isLefty = isLefty;
	}
	
	/** Generate an enemy fish to be placed on the screen.
	 * Make a new fish with a random movement speed, random height at which it spawns, and random
	 * side of the screen at which it spawns.
	 * @return an enemy fish.
	 */
	public static EnemyFish generateFish() {
		
		Random rng = new Random();
		
		// Generate the height at which the fish spawns, its movement speed, and at which side of the screen it spawns.
		int randomHeight = rng.nextInt(800);
		int randomSpeed = rng.nextInt(9) + 1;
		boolean isLefty = rng.nextBoolean();
		
		// Get the image for the fish and its respective height and width.
		Image fishImage = new Image(MainScreenController.getFishFile());
		int fishImageWidth = (int) fishImage.getWidth();
		int fishImageHeight = (int) fishImage.getHeight();
		
		// Generate the fish, depending on which side of the screen it spawns.
		if(isLefty) {
			return new EnemyFish(randomSpeed, isLefty, new Sprite(fishImage, new AABB(0, randomHeight, fishImageWidth, fishImageHeight)));
		} else {
			// If the fish spawns at the right side of the screen, it needs to be placed at the X coordinate equal to the width of the AABB screenbox;
			return new EnemyFish(randomSpeed, isLefty, new Sprite(fishImage, new AABB(MainScreenController.getScreenbox().getWidth(), randomHeight, fishImageWidth, fishImageHeight)));
		}
	}

	public boolean isLefty() {
		return isLefty;
	}

	public void setLefty(boolean isLefty) {
		this.isLefty = isLefty;
	}
}
