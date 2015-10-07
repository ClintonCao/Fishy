package interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.BoundingBox;
import main.Sprite;

/**
 * A FishBomb can be made to explode. EnemyFish within the explosion radius
 * will die.
 * @author Michiel
 *
 */
public interface FishBombInterface {
	
	/**
	 * Update the x-coordinate of the FishBomb. Used for moving FishBombs over the screen.
	 *
	 * @param x - the new x-coordinate.
	 */
	public void updateX(int mod);
	
	/**
	 * Update the y-coordinate of the FishBomb. Used for moving FishBombs over the screen.
	 *
	 * @param y - the new y-coordinate.
	 */
	public void updateY(int mod);

	/**
	 * @param gc - GraphicsContext which will perform the rendering.
	 */
	public void render(GraphicsContext gc);

	/**
	 * Check if the explosion radius of the FishBomb intersects a BoundingBox.
	 * @param bb.
	 * @return true if intersecting.
	 */
	public boolean intersectsRectangle(BoundingBox bb);
	
// --- Getters and Setters ---	

	/**
	 * @return the x-coordinate of the bomb.
	 */
	public int getPosX();

	/**
	 * @param posX - the new x-coordinate.
	 */
	public void setPosX(int posX);

	/**
	 * @return the y-coordinate of the bomb.
	 */
	public int getPosY();

	/**
	 * @param posY - the new y-coordinate.
	 */
	public void setPosY(int posY);

	/**
	 * @return the Image for the explosion.
	 */
	public Image getExplosionImg();

	/**
	 * @param explosionImg - the new Image for the explosion.
	 */
	public void setExplosionImg(Image explosionImg);
	
	/**
	 * @param sprite - the new Sprite.
	 */
	public void setSprite(Sprite sprite);

	/**
	 * @return the Sprite.
	 */
	public Sprite getSprite();

	/**
	 * @return the radius.
	 */
	public int getRadius();

	/**
	 * @param radius - the new radius.
	 */
	public void setRadius(int radius);
}
