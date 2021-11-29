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

public class HUD extends Sprite {
	
	protected Player p;
	
	public HUD (Scene initScene, Level initLevel int initX, int initY) {
		super(initScene, initLevel, initX, initY);
		collisionType = Sprite.COL_NONE;
		hasGravity = false;
	}
	
	public void drawSprite(Graphics2D g2d) {
		
	}
	
	public void update() {
		
	}
	
	public void handleCollisionWith(Sprite s, int collisionDir) {
		
	}
	
	
}