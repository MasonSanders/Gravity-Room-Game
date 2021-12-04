// Level.java
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

public abstract class Level {
	
	protected ArrayList<Sprite> spriteList;
	protected String levelID;
	protected Scene scene;
	protected int numLives;// number of lives the player has
	protected int numTokens;// number of tokens the player has
	protected int requiredTokens;// number of tokens required to beat the level.
	protected boolean completable;
	
	// constructor
	public Level(Scene initScene, 
							 int lives, 
							 String id) {
		scene = initScene;
		levelID = id;
		spriteList = new ArrayList<Sprite>();
		numLives = lives;
		numTokens = 0;
		requiredTokens = 0;
		completable = false;
	}
	
	//abstract drawLevel method
	public abstract void drawLevel(Graphics2D g2d);
	
	// updateLevel method
	public abstract void updateLevel();
	
	// nextLevel method
	public abstract void nextLevel();
	
	// resetLevle method
	public abstract void resetLevel();
	
	// setLevelID method
	public void setLevelID(String id) {
		levelID = id;
	}// end setLevelID
	
	// getSpriteList method
	public ArrayList<Sprite> getSpriteList() {
		return spriteList;
	}// end getSpriteList
	
	// getLevelID method
	public String getLevelID() {
		return levelID;
	}// end getLevelID
	
	// setLives method
	public void setLives(int lives) {
		numLives = lives;
	}
	
	// setTokens method
	public void setTokens(int tokens) {
		numTokens = tokens;
	}// end setTokens
	
	// getLives method
	public int getLives() {
		return numLives;
	}// end getLives
	
	// getTokens method
	public int getTokens() {
		return numTokens;
	}// end getTokens
	
	// isCompletable
	public boolean isCompletable() {
		return completable;
	}// end isCompletable
}