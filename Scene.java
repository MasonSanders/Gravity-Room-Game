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
	
	// list of levels
	private ArrayList<Level> levelList;
	
	// the current level 
	private Level currentLevel;
	
	
	// constructor
	public Scene() {
		// add levels to the game and set the current level to the first level.
		
		levelList = new ArrayList<Level>();
		levelList.add(new StartScreen(this, 0, "Start Screen"));
		currentLevel = levelList.get(0);
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
	
	// getLevelList method
	public ArrayList<Level> getLevelList() {
		return levelList;
	}// end getLevelList
	
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