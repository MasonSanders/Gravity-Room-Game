// Scene.java

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.color.*;
import java.lang.Math;
import java.util.ArrayList;

/*
 * Scene Class
 */
public class Scene extends JPanel implements ActionListener {
	
	//timer for framerate
	private Timer t = new Timer(17, this);
	
	// the current level 
	private Level currentLevel;
	
	
	// constructor
	public Scene() {
		// set the initial level to a new startscreen
		currentLevel = new StartScreen(this, 0, "Start Screen");
		// allow the jpanel to get focus,
		// which allows for event listeners to work
		setPreferredSize(new Dimension(500, 600));
		setFocusable(true);
		requestFocus();
		// start the timer
		t.start();
	}// end constructor
	
	// paintComponenet Method
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		setBackground(new Color(157, 139, 217));
		
		// draw every sprite in spriteList
		currentLevel.drawLevel(g2d);
		
		
	}// end paintComponenet
	
	// getCurrentLevel method
	public Level getCurrentLevel() {
		return currentLevel;
	}// end getCurrentLevel
	
	// setCurrentLevel
	public void setCurrentLevel(Level lvl) {
		currentLevel = lvl;
	}//end setCurrentLevel
	
	// actionPerformed method
	public void actionPerformed(ActionEvent e) {
		// update every sprite
		currentLevel.updateLevel();
		
		repaint();
	}// end actionPerformed
}