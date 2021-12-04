// SpikeTrap.java

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

public class SpikeTrap extends Sprite {
	// Constructor 1
	public SpikeTrap(Scene initScene, Level initLevel, int initX, int initY) {
		super(initScene, initLevel, initX, initY);
		collisionType = Sprite.COL_KILL_OTHER; // Set so that SpikeTraps kill the player character
		hasGravity = false;
	}// end Constructor 1
	
	
	// Constructor 2
	public SpikeTrap(Scene initScene, Level initLevel, int initX, int initY, String imgSource) {
		super(initScene, initLevel, initX, initY, imgSource);
		collisionType = Sprite.COL_KILL_OTHER;
		hasGravity = false;
	}// end Constructor 2
	
	public void drawSprite(Graphics2D g2d) {
		if (visible) {
			g2d.drawImage(dbImg, null, x, y);
		}
	}
	
	public void update() {
		
	}
	
	public void handleCollisionWith(Sprite s, int collisionDir) {
		
	}	
}