// Level1.java
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

public class Level1 extends Level {
	
	// Constructor
	public Level1(Scene initScene, String id) {
		super(initScene, id);
		
		// add sprites to the level
		spriteList.add(new Platform(initScene, this, 0, 475, "hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 0, "hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 0, "vBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 475, 0, "vBorderWall.png"));
		spriteList.add(new Player(initScene, this, 100, 448, "Player.png"));
		
	}// end Constructor
	
	// drawLevel method
	public void drawLevel(Graphics2D g2d) {
		for (int i = 0; i < spriteList.size(); ++i) {
			spriteList.get(i).drawSprite(g2d);
		}
	}// end drawLevel
	
}