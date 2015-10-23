package nl.tudelft.fishy.factories;

import nl.tudelft.fishy.CompositeEnemyFish;
import nl.tudelft.fishy.Entity;
import nl.tudelft.fishy.Item;
import nl.tudelft.fishy.PlayerFish;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class AbstractFactory {
  
  abstract AnimationTimer makeAnimationTimer(CompositeEnemyFish compositeEnemyFish);
  
  abstract Entity getEntity(String entityType);
  
  abstract Item createItem(String itemType, PlayerFish player);
  
  abstract EventHandler<MouseEvent> makeEventHandler(String buttonString);
}
