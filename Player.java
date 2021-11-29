// Player.java
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

public class Player extends Sprite implements KeyListener {
	
	
	// Constructor with an initial scene, x and y, but no image source
	public Player(Scene initScene, Level initLevel, int initX, int initY) {
		// initialize the parent sprite class
		super(initScene, initLevel, initX, initY);
		// give the player gravity
		hasGravity = false;
		
		// create the keyListener
		scene.addKeyListener(this);
	}// end constructor 1
	
	// Player Constructor that takes a scene, image source, initial x, and initial y
	public Player(Scene initScene, Level initLevel, int initX, int initY, String imgSource) {
		// initialize the parent sprite class
		super(initScene, initLevel, initX, initY, imgSource);
		
		// give the player gravity
		hasGravity = true;
		
		gravityDirState = Sprite.GRAV_BOTTOM;
		verticalState = Sprite.FALLING;
		// create the keyListener
		scene.addKeyListener(this);
	}// end constructor 2
	
	// draw method
	
	public void drawSprite(Graphics2D g2d) {
		g2d.drawImage(dbImg, new AffineTransformOp(aftran, AffineTransformOp.TYPE_BILINEAR), x, y);
	}
	
	// update method
	public void update() {
		// update dx and dy based on movement direction.
		manageGravityControls();
		
		// moves the sprite
		x += dx;
		y += dy;
		
		// check boundary conditions
		checkBounds();
		
		// update and handle collisions
		updateCollision();
		if (gravityDirState == Sprite.GRAV_BOTTOM) {
			if (!(collisionInfo.containsValue(Sprite.COL_DIR_BOTTOM))) {
				verticalState = Sprite.FALLING;
			}
		}
		if (gravityDirState == Sprite.GRAV_LEFT) {
			if (!(collisionInfo.containsValue(Sprite.COL_DIR_LEFT))) {
				horizontalState = Sprite.FALLING;
			}
		}
		if (gravityDirState == Sprite.GRAV_TOP) {
			if (!(collisionInfo.containsValue(Sprite.COL_DIR_TOP))) {
				verticalState = Sprite.FALLING;
			}
		}
		if (gravityDirState == Sprite.GRAV_RIGHT) {
			if (!(collisionInfo.containsValue(Sprite.COL_DIR_RIGHT))) {
				horizontalState = Sprite.FALLING;
			}
		}
	}// end update
	
	// manageGravityControls method
	public void manageGravityControls() {
		
		// determine motion mechanics for each gravity direction
		if (gravityDirState == Sprite.GRAV_BOTTOM) {
			dx = 0;
			if (verticalState == Sprite.JUMP) {
				ddy = 1;
				fall();
			} 
			if (verticalState == Sprite.FALLING) {
				ddy = 1;
				fall();
			}
			if (verticalState == Sprite.LANDED) {
				ddy = 0;
				dy = 0;
			}
			if (horizontalState == Sprite.NEGATIVE_MOTION) {
				dx = -5;
			}
			if (horizontalState == Sprite.POSITIVE_MOTION) {
				dx = 5;
			}
		}
		if (gravityDirState == Sprite.GRAV_LEFT) {
			dy = 0;
			if (horizontalState == Sprite.JUMP) {
				ddx = -1;
				fall();
			}
			if (horizontalState == Sprite.FALLING) {
				ddx = -1;
				fall();
			}
			if (horizontalState == Sprite.LANDED) {
				ddx = 0;
				dx = 0;
			}
			if (verticalState == Sprite.NEGATIVE_MOTION) {
				dy = -5;
			}
			if (verticalState == Sprite.POSITIVE_MOTION) {
				dy = 5;
			}
		}
		if (gravityDirState == Sprite.GRAV_TOP) {
			dx = 0;
			if (verticalState == Sprite.JUMP) {
				ddy = -1;
				fall();
			}
			if (verticalState == Sprite.FALLING) {
				ddy = -1;
				fall();
			}
			if (verticalState == Sprite.LANDED) {
				ddy = 0;
				dy = 0;
			}
			if (horizontalState == Sprite.NEGATIVE_MOTION) {
				dx = -5;
			}
			if (horizontalState == Sprite.POSITIVE_MOTION) {
				dx = 5;
			}
		}
		if (gravityDirState == Sprite.GRAV_RIGHT) {
			dy = 0;
			if (horizontalState == Sprite.JUMP) {
				ddx = 1;
				fall();
			}
			if (horizontalState == Sprite.FALLING) {
				ddx = 1;
				fall();
			}
			if (horizontalState == Sprite.LANDED) {
				ddx = 0;
				dx = 0;
			}
			if (verticalState == Sprite.NEGATIVE_MOTION) {
				dy = -5;
			}
			if (verticalState == Sprite.POSITIVE_MOTION) {
				dy = 5;
			}
		}
	}// end manageGravityControls
	
	// handleCollisionWith method
	public void handleCollisionWith(Sprite s, int collisionDir) {
		if (collisionDir == Sprite.COL_DIR_LEFT) {
			x = s.getX() + s.getWidth();
			if (gravityDirState == Sprite.GRAV_LEFT) {
				horizontalState = Sprite.LANDED;
			}
		}
		if (collisionDir == Sprite.COL_DIR_TOP) {
			y = s.getY() + s.getHeight();
			if (gravityDirState == Sprite.GRAV_TOP) {
				verticalState = Sprite.LANDED;
			}
		}
		if (collisionDir == Sprite.COL_DIR_RIGHT) {
			x = s.getX() - width;
			if (gravityDirState == Sprite.GRAV_RIGHT) {
				horizontalState = Sprite.LANDED;
			}
		}
		if (collisionDir == Sprite.COL_DIR_BOTTOM) {
			y = s.getY() - height;
			if (gravityDirState == Sprite.GRAV_BOTTOM) {
				verticalState = Sprite.LANDED;
			}
		}
	}
	
	// jump method
	public void fall() {
		// decide how the player should fall based on gravity direction
		if (gravityDirState == Sprite.GRAV_BOTTOM) {
			dy += ddy;
		}
		if (gravityDirState == Sprite.GRAV_LEFT) {
			dx += ddx;
		}
		if (gravityDirState == Sprite.GRAV_TOP) {
			dy += ddy;
		}
		if (gravityDirState == Sprite.GRAV_RIGHT) {
			dx += ddx;
		}
	}// end jump
	
	// keyPressed method
	public void keyPressed(KeyEvent e) {
		// set the direction being moved in
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (gravityDirState == Sprite.GRAV_BOTTOM) {
				horizontalState = Sprite.NEGATIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_LEFT) {
				verticalState = Sprite.NEGATIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_TOP) {
				horizontalState = Sprite.NEGATIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_RIGHT) {
				verticalState = Sprite.POSITIVE_MOTION;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (gravityDirState == Sprite.GRAV_BOTTOM) {
				horizontalState = Sprite.POSITIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_LEFT) {
				verticalState = Sprite.POSITIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_TOP) {
				horizontalState = Sprite.POSITIVE_MOTION;
			}
			if (gravityDirState == Sprite.GRAV_RIGHT) {
				verticalState = Sprite.NEGATIVE_MOTION; 
			}
		}
		// if the player has gravity, then use jump instead of the w an s keys to move up and down
		if (hasGravity) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					
					// determine jump direction based on gravity direction.
				if (gravityDirState == Sprite.GRAV_BOTTOM) {
					if (verticalState == Sprite.LANDED) {
						dy = -14;
						verticalState = Sprite.JUMP;
					}
				}
				if (gravityDirState == Sprite.GRAV_LEFT) {
					if (horizontalState == Sprite.LANDED) {
						dx = 14;
						horizontalState = Sprite.JUMP;
					}
				}
				if (gravityDirState == Sprite.GRAV_TOP) {
					if (verticalState == Sprite.LANDED) {
						dy = 14;
						verticalState = Sprite.JUMP;
					}
				}
				if (gravityDirState == Sprite.GRAV_RIGHT) {
					if (horizontalState == Sprite.LANDED) {
						dx = -14;
						horizontalState = Sprite.JUMP;
					}
				}	
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				verticalState = Sprite.NEGATIVE_MOTION;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				verticalState = Sprite.POSITIVE_MOTION;
			}
		}
		
		// Change the Direction of gravity
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gravityDirState = Sprite.GRAV_BOTTOM;
			setImgAngle(0);
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gravityDirState = Sprite.GRAV_LEFT;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gravityDirState = Sprite.GRAV_TOP;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gravityDirState = Sprite.GRAV_RIGHT;
		}
 	}// end keyPressed
	
	// keyReleased method
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (gravityDirState == Sprite.GRAV_BOTTOM) {
				// check to make sure the sprite isn't already moving in the opposite direction first before stopping motion
				if (horizontalState != Sprite.POSITIVE_MOTION) {
					horizontalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_LEFT) {
				if (verticalState != Sprite.POSITIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_TOP) {
				if (horizontalState != Sprite.POSITIVE_MOTION) {
					horizontalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_RIGHT) {
				if (verticalState != Sprite.NEGATIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (gravityDirState == Sprite.GRAV_BOTTOM) {
				// check to make sure the sprite isn't already moving in the opposite direction first before stopping motion
				if (horizontalState != Sprite.NEGATIVE_MOTION) {
					horizontalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_LEFT) {
				if (verticalState != Sprite.NEGATIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_TOP) {
				if (horizontalState != Sprite.NEGATIVE_MOTION) {
					horizontalState = Sprite.STATIONARY;
				}
			}
			if (gravityDirState == Sprite.GRAV_RIGHT) {
				if (verticalState != Sprite.POSITIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			}
		}
		// only stop motion on key release if there is gravity
		if (!hasGravity) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				if (verticalState != Sprite.POSITIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				if (verticalState != Sprite.NEGATIVE_MOTION) {
					verticalState = Sprite.STATIONARY;
				}
			} 
			
		}
		
	}// end keyReleased
	
	public void keyTyped(KeyEvent e) {
		
	}
}