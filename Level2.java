// Level2.java
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

public class Level2 extends Level {
	
	// Constructor
	public Level2(Scene initScene, int lives, String id) {
		super(initScene, lives, id);
		
		requiredTokens = 2;
		
		// add all necessary sprites to level layout
		spriteList.add(new Platform(initScene, this, 0, 575, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/vBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 475, 100, "assets/RightWall.png"));
		spriteList.add(new Exit(initScene, this, 475, 550, "assets/Exit.png"));
		spriteList.add(new SpikeTrap(initScene, this, 26, 126, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 87, 126, "assets/CeilingSpike.png"));
		spriteList.add(new Life(initScene, this, 390, 166, "assets/Life.png"));
		spriteList.add(new Platform(initScene, this, 149, 126, "assets/ShortPlatformV.png"));
		spriteList.add(new SpikeTrap(initScene, this, 414, 126, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 449, 152, "assets/RWallSpike.png"));
		spriteList.add(new Token(initScene, this, 445, 218, "assets/Token.png"));
		spriteList.add(new SpikeTrap(initScene, this, 414, 285, "assets/GroundSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 353, 126, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 449, 325, "assets/RWallSpike.png"));
		spriteList.add(new Token(initScene, this, 419, 335, "assets/Token.png"));
		spriteList.add(new Platform(initScene, this, 375, 310, "assets/MediumPlatformH.png"));
		spriteList.add(new Platform(initScene, this, 125, 310, "assets/MediumPlatformH.png"));
		spriteList.add(new SpikeTrap(initScene, this, 145, 285, "assets/GroundSpike.png"));
		spriteList.add(new Platform(initScene, this, 125, 371, "assets/LongPlatformH.png"));
		spriteList.add(new SpikeTrap(initScene, this, 200, 386, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 200, 549, "assets/GroundSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 261, 549, "assets/GroundSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 449, 464, "assets/RWallSpike.png"));
		spriteList.add(new Player(initScene, this, 100, 548, "assets/Player.png"));
		spriteList.add(new HUD(initScene, this, 0, 0));
	}
	
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
		scene.setCurrentLevel(new Level3(scene, numLives, "Level 3"));
	}// end nextLevel
	
	// resetLevel method
	public void resetLevel() {
		scene.setCurrentLevel(new Level2(scene, numLives, "Level 2"));
	}// end resetLevel
}