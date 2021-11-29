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
	
	// constructor
	public Level(Scene initScene, String id) {
		scene = initScene;
		levelID = id;
		spriteList = new ArrayList<Sprite>();
	}
	
	//abstract drawLevel method
	public abstract void drawLevel(Graphics2D g2d);
	
	// update
	public void updateLevel() {
		// call update each sprite in the level
		for (int i = 0; i < spriteList.size(); ++i) {
			spriteList.get(i).update();
		}
	}
	
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
	}// emd getLevelID
}