// Platform.java

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

public class Platform extends Sprite {
	
	// Constructor with an initial scene, x and y, but no image source
	public Platform(Scene initScene, Level initLevel, int initX, int initY) {
		super(initScene, initLevel, initX, initY);
		this.hasGravity = false;
	}
	
	public Platform(Scene initScene, Level initLevel, int initX, int initY, String imgSource) {
		super(initScene, initLevel, initX, initY, imgSource);
		this.hasGravity = false;
	}
	
	public void drawSprite(Graphics2D g2d) {
		g2d.drawImage(this.dbImg, null, this.x, this.y);
	}
	
	// update doesn't need to do anything for the platform since player already checks
	public void update() {
		
	}
	
	public void handleCollisionWith(Sprite s, int collisionDir) {
		
	}
}