package main;

public abstract class Item {
  private Sprite sprite;

  public Item(Sprite sprite) {
    this.setSprite(sprite);
  }

  public Sprite getSprite() {
    return sprite;
  }

  public void setSprite(Sprite sprite) {
    this.sprite = sprite;
  }
}
