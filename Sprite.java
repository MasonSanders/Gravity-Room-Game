// Sprite.java
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

public abstract class Sprite {
	//Enumerations -------------------------------------------------------------------------------------------
	// public enumerations for sprite states
	// enumerations for movement directions.
	// split into stationary, positive, and negative
	// to reduce variables and split into a horizontal motion and vertical motion state
	public static final int STATIONARY = 0; 
	public static final int POSITIVE_MOTION = 1;
	public static final int NEGATIVE_MOTION = 2;
	public static final int JUMP = 3;
	public static final int LANDED = 4;
	public static final int FALLING = 5;
	

	// boundAction enumerations
	public static final int BA_NONE = 0;
	public static final int BA_STOP = 1;
	public static final int BA_WRAP = 2;
	
	// collision type enumeration
	public static final int COL_NONE = 0;
	public static final int COL_HALT = 1;
	public static final int COL_KILL_OTHER = 2;
	public static final int COL_KILL_SELF = 3;
	
	// collision direction enumerations to be used in collisionInfo
	public static final int COL_DIR_LEFT = 0;
	public static final int COL_DIR_TOP = 1;
	public static final int COL_DIR_RIGHT = 2;
	public static final int COL_DIR_BOTTOM = 3;
	
	// gravity direction enumeration
	public static final int GRAV_BOTTOM = 0;
	public static final int GRAV_LEFT = 1;
	public static final int GRAV_TOP = 2;
	public static final int GRAV_RIGHT = 3;
	
	
	//End Enumerations ---------------------------------------------------------------------------------------
	
	// pretected variables for the sprite
	protected int horizontalState; // state for horizontal motion
	protected int verticalState; // state for vertical motion
	protected int boundAction;
	protected int collisionType;
	protected int gravityDirState; // state for direction of gravity
	
	// AbstractMap assiciates a key, which are sprites, with a value, which is an integer enumeration
	// this allows for keeping and updating a list of sprites being collided with and the direction the
	// collision is coming from.
	protected HashMap<Sprite, Integer> collisionInfo;
	
	
	protected boolean visible;
	
	protected boolean hasGravity;
	
	
	
	protected Scene scene;
	protected Level level;
	protected int width;
	protected int height;
	
	// position
	protected int x;
	protected int y;
	
	// velocity
	protected int dx;
	protected int dy;
	protected double speed;
	
	// accelleration
	protected int ddy;
	protected int ddx;
	
	// angles
	protected double imgAngle; // angle of the image
	protected double movementAngle; // angle of the direction of motion of the sprite
	
	protected AffineTransform aftran;
	
	
	// double buffer for the sprite image
	protected BufferedImage dbImg;
	protected Graphics2D dbImgG2D;
	
	// tells whether or not the sprite is collidable
	protected boolean collidable;
	
	// Constructor for sprite without an image source. Image can be set later
	// takes in a scene, initial x position, initial y position
	public Sprite(Scene initScene, Level initLevel, int initX, int initY) {
		// set the scene and level
		scene = initScene;
		level = initLevel;
	
		x = initX;
		y = initY;
		dx = 0;
		dy = 0;
		ddx = 0;
		ddy = 0;
		speed = 0;
		movementAngle = 0;
		imgAngle = 0;
		
		dbImg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		
		width = dbImg.getWidth();
		height = dbImg.getHeight();
		
		// create graphics from dbImg and use it for dbImgG2D
		dbImgG2D = (Graphics2D) dbImg.createGraphics();
		
		// set the sprite to stationary movement
		horizontalState = Sprite.STATIONARY;
		verticalState = Sprite.STATIONARY;
		boundAction = Sprite.BA_WRAP;
		
		//affineTransformation object
		aftran = new AffineTransform();
		
		
		visible = true;
		// set gravity and collision type
		hasGravity = false;
		collisionType = Sprite.COL_HALT;
		
		collisionInfo = new HashMap<Sprite, Integer>();
	}// end Constructor 1
	
	public Sprite(Scene initScene, Level initLevel, int initX, int initY, String imgSource) {
		scene = initScene;
		level = initLevel;
		
		x = initX;
		y = initY;
		dx = 0;
		dy = 0;
		ddx = 0;
		ddy = 0;
		speed = 0;
		movementAngle = 0;
		imgAngle = 0;
		setImage(imgSource);
		
		width = dbImg.getWidth();
		height = dbImg.getHeight();
		
		dbImgG2D = (Graphics2D) dbImg.getGraphics();
		
		horizontalState = Sprite.STATIONARY;
		verticalState = Sprite.STATIONARY;
		boundAction = Sprite.BA_WRAP;
		
		// affine transformation object
		aftran = new AffineTransform();
		
		visible = true;
		// set gravity and collision type
		hasGravity = false;
		collisionType = Sprite.COL_HALT;
		
		collisionInfo = new HashMap<Sprite, Integer>();
	}

	// draw method
	public abstract void drawSprite(Graphics2D g2d);
	
	// update method to be implemented by each subclass of Sprite
	public abstract void update();
	
	// checkBounds method
	public void checkBounds() {
		boolean offRight = false;
		boolean offLeft = false;
		boolean offTop = false;
		boolean offBottom = false;
		
		if (boundAction == Sprite.BA_STOP) {
			// determine the conditions for being out of bounds for the stop action
			if (x < 0) {
				offLeft = true;
			}
			if (x + width > scene.getWidth()) {
				offRight = true;
			}
			if (y < 0) {
				offTop = true;
			}
			if (y + height > scene.getHeight()) {
				offBottom = true;
			}
			
			// modify sprite location
			if (offRight) {
				x = scene.getWidth() - width;
			}
			if (offLeft) {
				x = 0;
			}
			if (offTop) {
				y = 0;
			}
			if (offBottom) {
				y = scene.getHeight() - height;
			}
			
			
		} else if (boundAction == Sprite.BA_WRAP) {
			// determine the conditions for being out of bounds for the wrap action
			if (x + width < 0) {
				offLeft = true;
			}
			if (x > scene.getWidth()) {
				offRight = true;
			}
			if (y + height < 0) {
				offTop = true;
			}
			if (y > scene.getHeight()) {
				offBottom = true;
			}
			
			// modify sprite location
			if (offRight) {
				x = 0 - width;
			}
			if (offLeft) {
				x = scene.getWidth();
			}
			if (offTop) {
				y = scene.getHeight();
			}
			if (offBottom) {
				y = 0 - height;
			}
			
		}
	}// end checkBounds
	
	
	// collidesWith method
	public boolean collidesWith(Sprite s) {
		boolean collision = false;
		
		if (collisionType != Sprite.COL_NONE) {
			// location variables for this sprite
			int myLeft = x;
			int myRight = x + width;
			int myTop = y;
			int myBottom = y + height;
			// location variables for the other sprite
			int otherLeft = s.getX();
			int otherRight = s.getX() + s.getWidth();
			int otherTop = s.getY();
			int otherBottom = s.getY() + s.getHeight();
			
			collision = true;
			
			if ((myBottom < otherTop) ||
				(myTop > otherBottom) || 
				(myRight < otherLeft) || 
				(myLeft > otherRight)) {
				collision = false;
			}
	
			
		}
		return collision;

	} // end collidesWith method
	
	// updateCollision method
	public void updateCollision() {
		
		for (int i = 0; i < level.getSpriteList().size(); ++i) {
			Sprite other = level.getSpriteList().get(i);
			// location variable for current sprite
			int myLeft = x;
			int myRight = x + width;
			int myTop = y;
			int myBottom = y + height;
			// location variables for the other sprite
			int otherLeft = other.getX();
			int otherRight = other.getX() + other.getWidth();
			int otherTop = other.getY();
			int otherBottom = other.getY() + other.getHeight();
			
			boolean collisionFound = false;
			
			if (other != this) {
				if (collidesWith(other)) {
					if (myBottom >= otherTop) {
						if (!(myBottom - dy >= otherTop)) {
							collisionInfo.put(other, Sprite.COL_DIR_BOTTOM);
						}
					}
					if (myTop <= otherBottom) {
						if (!(myTop - dy <= otherBottom)) {
							collisionInfo.put(other, Sprite.COL_DIR_TOP);
						}
					}
					if (myRight >= otherLeft) {
						if (!(myRight - dx >= otherLeft)) {
							collisionInfo.put(other, Sprite.COL_DIR_RIGHT);
						}
					}
					if (myLeft <= otherRight) {
						if (!(myLeft - dx <= otherRight)) {
							collisionInfo.put(other, Sprite.COL_DIR_LEFT);
						}
					}
					
					handleCollisionWith(other, collisionInfo.get(other));
				} else {
					if (collisionInfo.containsKey(other)) {
						collisionInfo.remove(other);
					}
				}
			}
		}
		
	}
	
	// abstract handleCollisionWith method
	public abstract void handleCollisionWith(Sprite s, int collisionDir);
	
	
	// calcVector method
	public void calcVector() {
		dx = (int) Math.round(speed * Math.cos(movementAngle));
		dy = (int) Math.round(speed * Math.sin(movementAngle));
	} // end calcVector
	
	public void calcSpeedAngle() {
		speed = Math.sqrt(((double)dx * (double)dx) + 
								((double)dy * (double)dy));
		movementAngle = Math.atan2((double)dy, (double)dx);
	}
	
	public void addVector(int angle, double thrust) {
		double radAngle = Math.toRadians(angle);
		
		dx += thrust * Math.cos(radAngle);
		dy += thrust * Math.sin(radAngle);
		calcSpeedAngle(); 
	}	
	// SETTERS
	
	// setWidth method
	public void setWidth(int w) {
		width = w;
	}// end setWidth
	
	// setHeight method
	public void setHeight(int h) {
		height = h;
	}// end setHeight
	
	// setX method
	public void setX(int newX) {
		x = newX;
	}// end Set
	
	// setY method
	public void setY(int newY) {
		y = newY;
	}
	
	// setDX method
	public void setDX(int newDX) {
		dx = newDX;
	}
	
	// setDY method
	public void setDY(int newDY) {
		dy = newDY;
	}
	
	// set DDX method
	public void setDDX(int newDDX) {
		ddx = newDDX;
	}// end setDDX
	
	// set DDY method
	public void setDDY(int newDDY) {
		ddy = newDDY;
	}// end setDDX

	
	// setImage method
	public void setImage(String imgSource) {
		try {
			// load the image from a string source.
			dbImg = ImageIO.read(new File(imgSource));
		} catch (IOException e) {
			// do nothing on an exception
		}
	}// end setImage
	
	// setSpeed method
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
		calcVector();
	} // end setSpeed
	
	// changeSpeedBy method
	public void changeSpeedBy(double diff) {
		speed += diff;
		calcVector();
	}// end changeSpeedBy

	// setImgAngle
	public void setImgAngle(int thetaDegrees) {
		imgAngle = Math.toRadians(thetaDegrees);
	}// end setImgAngle
	
	public void changeImgAngleBy(int thetaDegrees) {
		imgAngle += Math.toRadians(thetaDegrees); 
	}
	
	public void setMovementAngle(int thetaDegrees) {
		movementAngle = Math.toRadians(thetaDegrees);
	}
	
	public void changeMovementAngleBy(int thetaDegrees) {
		movementAngle += Math.toRadians(thetaDegrees);
		calcVector();
	}
	
	// GETTERS
	
	// getWidth method
	public int getWidth() {
		return width;
	}
	
	// getHeight method
	public int getHeight() {
		return height;
	}
	
	// getX method
	public int getX() {
		return x;
	}
	
	// getY method
	public int getY() {
		return y;
	}
	
	// getDX method
	public int getDX() {
		return dx;
	}
	
	// getDY method
	public int getDY() {
		return dy;
	}
	
	// getImage method
	public BufferedImage getImage() {
		return dbImg;
	}
	
	// getSpeed method
	public double getSpeed() {
		speed = Math.sqrt(((double)dx * (double)dx) + 
								((double)dy * (double)dy));
		return speed;
	}// end getSpeed
	
	// getImgAngle method
	public int getImgAngle() {
		int thetaDegrees = (int) Math.round(Math.toDegrees(imgAngle));
		return thetaDegrees;
	}// end getSpeed
	
	// getMovementAngle method;
	public int getMovementAngle() {
		int thetaDegrees = (int) Math.round(Math.toDegrees(movementAngle));
		return thetaDegrees;
	}// end getMovementAngle
	
	// getCollisionType method
	public int getCollisionType() {
		return collisionType;
	}// end getCollisionType
	
}