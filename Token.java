// Token.java
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

public class Token extends Sprite {
	
	private boolean collected;
	// Constructor 1
	public Token(Scene initScene, 
							 Level initLevel, 
							 int initX, 
							 int initY) {
		super(initScene, initLevel, initX, initY);
		collisionType = Sprite.COL_KILL_SELF;
		hasGravity = false;
		collected = false;
	}// end Constructor 1
	
	// Constructor 2
	public Token(Scene initScene, 
							 Level initLevel, 
							 int initX, 
							 int initY, 
							 String imgSource) {
		super(initScene, initLevel, initX, initY, imgSource);
		collisionType = Sprite.COL_KILL_SELF;
		hasGravity = false;
		collected = false;
	}// end Constructor 2
	
	// drawSprite method
	public void drawSprite(Graphics2D g2d) {
		if (visible) {
			g2d.drawImage(dbImg, null, x, y);
		}
	}// end drawSprite
	
	// update method
	public void update() {
		updateCollision(); // just check for collision
	}// end update
	
	// handleCollisionWith method
	public void handleCollisionWith(Sprite s, int collisionDir) {
		// increase the number of tokens
		if (s.getCollisionType() == Sprite.COL_PLAYER) {
			if (!collected) {
				level.setTokens(level.getTokens() + 1);
				collected = true;
			}
		}
	}// end handleCollisionWith
}