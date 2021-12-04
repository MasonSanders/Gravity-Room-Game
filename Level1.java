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
	public Level1(Scene initScene, int lives, String id) {
		super(initScene, lives, id);
		
		requiredTokens = 1;
		
		// add sprites to the level
		spriteList.add(new Platform(initScene, this, 0, 575, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/vBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 475, 100, "assets/RightWall.png"));
		spriteList.add(new Exit(initScene, this, 475, 550, "assets/Exit.png"));
		spriteList.add(new SpikeTrap(initScene, this, 26, 126, "assets/LWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 26, 187, "assets/LWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 52, 126, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 113, 126, "assets/CeilingSpike.png")); 
		spriteList.add(new Platform(initScene, this, 175, 126, "assets/MediumPlatformV.png"));
		spriteList.add(new Token(initScene, this, 145, 156, "assets/Token.png"));
		spriteList.add(new SpikeTrap(initScene, this, 290, 126, "assets/CeilingSpike.png"));
		spriteList.add(new Life(initScene, this, 300, 156, "assets/Life.png"));
		spriteList.add(new Player(initScene, this, 100, 548, "assets/Player.png"));
		spriteList.add(new HUD(initScene, this, 0, 0));
		
		
	}// end Constructor
	
	// drawLevel method
	public void drawLevel(Graphics2D g2d) {
		for (int i = 0; i < spriteList.size(); ++i) {
			spriteList.get(i).drawSprite(g2d);
		}
	}// end drawLevel
	
	// updateLevel method
	public void updateLevel() {
		// call update each sprite in the level
		for (int i = 0; i < spriteList.size(); ++i) {
			spriteList.get(i).update();
		}
		
		if (numTokens == requiredTokens) {
			completable = true;
		}
		
		// game over if lives go below 1
		if (numLives <= 0) {
			scene.setCurrentLevel(new GameOverScreen(scene, 0, "Game Over Screen"));
		}
	}// end updateLevel
	
	// nextLevel method
	public void nextLevel() {
		scene.setCurrentLevel(new Level2(scene, numLives, "Level 2"));
	}// end nextLevel
	
	// resetLevel method
	public void resetLevel() {
		scene.setCurrentLevel(new Level1(scene, numLives, "Level 1"));
	}// end resetLevel
}