package interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.BoundingBox;
import main.Sprite;

public interface SpriteInterface {

	/**
	 * @param x - updates existing x-coordinate.
	 */
	public void updateX(int x);

	/**
	 * @param y - updates existing y-coordinate.
	 */
	public void updateY(int y);

	/**
	 * @param gc - GraphicsContext which will perform the rendering.
	 */
	public void render(GraphicsContext gc);

	/**
	 * @param otherSprite.
	 * @return true if intersecting.
	 */
	public boolean intersects(Sprite otherSprite);

	// --- Getters and Setters ---

	/**
	 * @return the Image of the sprite.
	 */
	public Image getImg();

	/**
	 * @param img - the new Image.
	 */
	public void setImg(Image img);

	/**
	 * @return the BoundingBox of the sprite.
	 */
	public BoundingBox getBoundingBox();

	/**
	 * @param boundingBox - the new BoundingBox.
	 */
	public void setBoundingBox(BoundingBox boundingBox);

	/**
	 * @param other.
	 * @return true if equal.
	 */
	public boolean equals(Object other);
}
