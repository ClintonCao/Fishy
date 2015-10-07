package interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.BoundingBox;
import main.Sprite;

public interface SpriteInterface {

	/**
	 * @param x - updates existing x-coordinate.
	 */
	void updateX(int x);

	/**
	 * @param y - updates existing y-coordinate.
	 */
	void updateY(int y);

	/**
	 * @param gc - GraphicsContext which will perform the rendering.
	 */
	void render(GraphicsContext gc);

	/**
	 * @param otherSprite.
	 * @return true if intersecting.
	 */
	boolean intersects(Sprite otherSprite);

	// --- Getters and Setters ---

	/**
	 * @return the Image of the sprite.
	 */
	Image getImg();

	/**
	 * @param img - the new Image.
	 */
	void setImg(Image img);

	/**
	 * @return the BoundingBox of the sprite.
	 */
	BoundingBox getBoundingBox();

	/**
	 * @param boundingBox - the new BoundingBox.
	 */
	void setBoundingBox(BoundingBox boundingBox);

	/**
	 * @param other.
	 * @return true if equal.
	 */
	boolean equals(Object other);
}
