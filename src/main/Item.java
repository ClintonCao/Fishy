package main;

public abstract class Item {
  private int uses;
  private Sprite sprite;

  public Item(int uses, Sprite sprite) {
    this.setUses(uses);
    this.setSprite(sprite);
  }

  public int getUses() {
    return uses;
  }

  public void setUses(int uses) {
    this.uses = uses;
  }

  public Sprite getSprite() {
    return sprite;
  }

  public void setSprite(Sprite sprite) {
    this.sprite = sprite;
  }
}
