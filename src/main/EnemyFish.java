package main;

import java.util.Random;

import javafx.scene.image.Image;

public class EnemyFish extends Entity {
	private boolean isLefty;
	
	public EnemyFish(int movespeed, boolean isLefty, Sprite sprite) {
		super(movespeed, sprite);
		this.isLefty = isLefty;
	}
	
	public static EnemyFish generateFish() {
		Random rng = new Random();
		int randomHeight = rng.nextInt(800);
		int randomSpeed = rng.nextInt(9) + 1;
		boolean isLefty = rng.nextBoolean();
		if(isLefty) {
			return new EnemyFish(randomSpeed, isLefty, new Sprite(new Image("Fish.png"), new AABB(0, randomHeight, 128, 128)));
		} else {
			return new EnemyFish(randomSpeed, isLefty, new Sprite(new Image("Fish.png"), new AABB(MainScreenController.getScreenbox().getWidth(), randomHeight, 128, 128)));
		}
	}

	public boolean isLefty() {
		return isLefty;
	}

	public void setLefty(boolean isLefty) {
		this.isLefty = isLefty;
	}
}
