package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
	}
	
}
