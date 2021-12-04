// GameOverScreen.java
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class GameOverScreen extends Level implements KeyListener {
	
	// Constructor
	public GameOverScreen(Scene initScene, int lives, String id) {
		super(initScene, lives, id);
		
		scene.addKeyListener(this);
	}// end Constructor
	
	// drawLevel method
	public void drawLevel(Graphics2D g2d) {
		g2d.setFont(new Font("SansSerif", 0, 60));
		g2d.setColor(new Color(255, 255, 255));
		
		g2d.drawString("Game Over", 90, 200);
		g2d.setFont(new Font("SansSerif", 0, 20));
		g2d.drawString("Press any key to continue.", 130, 250);
	}// end drawLevel
	
	// updateLevel method
	public void updateLevel() {
		
	}// end updateLevel
	
	// nextLevel method
	public void nextLevel() {
		scene.removeKeyListener(this);
		scene.setCurrentLevel(new StartScreen(scene, 0, "Start Screen"));
	}// end nextLevel
	
	// resetLevel method
	public void resetLevel() {}
	
	// keyPressed method
	public void keyPressed(KeyEvent e) {
		nextLevel();
	}// end keyPressed
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}