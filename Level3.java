// Level3.java
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

public class Level3 extends Level {
	public Level3(Scene initScene, int lives, String id) {
		super(initScene, lives, id);
		
		requiredTokens = 3;
		
		spriteList.add(new Platform(initScene, this, 0, 575, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/hBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 0, 100, "assets/vBorderWall.png"));
		spriteList.add(new Platform(initScene, this, 475, 100, "assets/RightWall.png"));
		spriteList.add(new Exit(initScene, this, 475, 550, "assets/Exit.png"));
		spriteList.add(new SpikeTrap(initScene, this, 26, 180, "assets/LWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 52, 242, "assets/GroundSpike.png"));
		spriteList.add(new Platform(initScene, this, 32, 267, "assets/MediumPlatformH.png"));
		spriteList.add(new SpikeTrap(initScene, this, 114, 180, "assets/RWallSpike.png"));
		spriteList.add(new Platform(initScene, this, 139, 160, "assets/LongPlatformV.png"));
		spriteList.add(new Platform(initScene, this, 189, 210, "assets/XLongPlatformV.png"));
		spriteList.add(new SpikeTrap(initScene, this, 164, 449, "assets/RWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 26, 388, "assets/LWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 114, 292, "assets/RWallSpike.png"));
		spriteList.add(new Platform(initScene, this, 189, 510, "assets/MediumPlatformH.png"));
		spriteList.add(new Platform(initScene, this, 274, 210, "assets/XLongPlatformV.png"));
		spriteList.add(new SpikeTrap(initScene, this, 208, 485, "assets/GroundSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 208, 126, "assets/CeilingSpike.png"));
		spriteList.add(new Platform(initScene, this, 289, 210, "assets/MediumPlatformH.png"));
		spriteList.add(new Platform(initScene, this, 389, 210, "assets/XLongPlatformV.png"));
		spriteList.add(new SpikeTrap(initScene, this, 289, 449, "assets/LWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 364, 449, "assets/RWallSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 308, 225, "assets/CeilingSpike.png"));
		spriteList.add(new SpikeTrap(initScene, this, 450, 151, "assets/RWallSpike.png"));
		spriteList.add(new Token(initScene, this, 325, 265, "assets/Token.png"));
		spriteList.add(new Token(initScene, this, 79, 312, "assets/Token.png"));
		spriteList.add(new Token(initScene, this, 225, 450, "assets/Token.png"));
		spriteList.add(new Life(initScene, this, 70, 200, "assets/Life.png"));
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
		scene.setCurrentLevel(new Level3(scene, numLives, "Level 3"));
	}// end resetLevel
}