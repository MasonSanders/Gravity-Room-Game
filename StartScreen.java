// StartScreen.java
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

public class StartScreen extends Level implements KeyListener {
	
	// constructor
	public StartScreen(Scene initScene, int lives, String id) {
		super(initScene, lives, id);
		
		scene.addKeyListener(this);
	}// end constructor
	
	// drawLevel method
	public void drawLevel(Graphics2D g2d) {
		// render text for the start screen
		g2d.setFont(new Font("SansSerif", 0, 60));
		g2d.setColor(new Color(255, 255, 255));
		
		g2d.drawString("Gravity Room", 60, 200);
		g2d.setFont(new Font("SansSerif", 0, 20));
		g2d.drawString("Press any key to start.", 150, 250);
		g2d.setFont(new Font("SansSerif", 0, 10));
		g2d.drawString("Objective: Open the door to the next level by collecting the yellow tokens.", 50, 480);
		g2d.drawString("You can earn extra lives by collecting the green coins.", 50, 490); 
		g2d.drawString("Controls: A moves up or left. D moves down or right. Use the arrow keys to change gravity.", 50, 500);
	}// end drawLevel
	
	// updateLevel method 
	public void updateLevel() {
		
	}// end updateLevel
	
	// nextLevel method
	public void nextLevel() {
		scene.removeKeyListener(this);
		scene.setCurrentLevel(new Level1(scene, 5, "Level 1"));
	}// end nextLevel
	
	// resetLevel method
	public void resetLevel() {}
	
	// keyPressed Method
	public void keyPressed(KeyEvent e) {
		nextLevel();
	}// end KeyPressed
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}