// Exit.java
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

public class Exit extends Sprite {
	// Constructor 1
	public Exit(Scene initScene, 
							 Level initLevel, 
							 int initX, 
							 int initY) {
		super(initScene, initLevel, initX, initY);
		collisionType = Sprite.COL_HALT;
		hasGravity = false;
	}// end Constructor 1
	
	// Constructor 2
	public Exit(Scene initScene, 
							 Level initLevel, 
							 int initX, 
							 int initY, 
							 String imgSource) {
		super(initScene, initLevel, initX, initY, imgSource);
		collisionType = Sprite.COL_HALT;
		hasGravity = false;
	}// end Constructor 2
	
	// drawSpriteMethod
	public void drawSprite(Graphics2D g2d) {
		if (visible) {
			g2d.drawImage(dbImg, null, x, y);
		}
	}// end drawSprite
	
	// update method
	public void update() {
		if (level.isCompletable()) {
			visible = false;
		}
		updateCollision(); // just check for collision
	}// end update
	
	// handleCollisionWith method
	public void handleCollisionWith(Sprite s, int collisionDir) {
		if (s.getCollisionType() == Sprite.COL_PLAYER) {
			if (level.isCompletable()) {
				level.nextLevel();
			}
		}
	}// end handleCollisionWith
}