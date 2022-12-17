package com.ramadan;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Rectangle;

public abstract class GameObject {

	// Variables to identify and manage every game objects rendering and collision detection
	protected ID id;
	protected int x, y;
	protected int velX, velY;
	protected int minSpeed, maxSpeed;
	protected int size;
	
	/**
	 * Full constructor for any game object, with predetermined position, id and size, but randomized speed in a given range.
	 * Uses x and y position to ensure that velX and velY are always towards the center of the window to prevent hazards
	 * being spawned on the edge and immediately going off screen.
	 * 
	 * @param x X position of the top left corner of the game object relative to the Window.
	 * @param y Y position of the top left corner of the game object relative to the Window.
	 * @param size Size of the object.
	 * @param minSpeed Minimum speed the object can have
	 * @param maxSpeed Maximum speed the object can have
	 * @param id enum to identify if object is a player or a hazard.
	 */
	public GameObject(int x, int y, int size, int minSpeed, int maxSpeed, ID id) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.maxSpeed = maxSpeed;
		this.id = id;
		
		// Check if object is on the right or left half, and ensure velX points in the right direction.
		if (x < Game.WIDTH/2) {
			velX = (id == ID.Player) ? 0 : Utilities.getRandomInt(minSpeed, maxSpeed);
		} else {
			velX = (id == ID.Player) ? 0 : Utilities.getRandomInt(-maxSpeed, -minSpeed);
		}
		
		// Check if object is on the top or bottom half, and ensure velY points in the right direction.
		if (y < Game.HEIGHT/2) {
			velY = (id == ID.Player) ? 0 : Utilities.getRandomInt(minSpeed, maxSpeed);
		} else {
			velY = (id == ID.Player) ? 0 : Utilities.getRandomInt(-maxSpeed, -minSpeed);
		}	
		
	}
	
	/**
	 * Simplified constructor that uses a default value of 10 for size, and default min and max speeds of 3 and 7.
	 * @param x
	 * @param y
	 * @param id
	 */
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.size = 10;
		this.minSpeed = 3;
		this.maxSpeed = 7;
		this.id = id;
		
		if (x < Game.WIDTH/2) {
			velX = (id == ID.Player) ? 0 : Utilities.getRandomInt(minSpeed, maxSpeed);
		} else {
			velX = (id == ID.Player) ? 0 : Utilities.getRandomInt(-maxSpeed, -minSpeed);
		}
		
		
		if (y < Game.HEIGHT/2) {
			velY = (id == ID.Player) ? 0 : Utilities.getRandomInt(minSpeed, maxSpeed);
		} else {
			velY = (id == ID.Player) ? 0 : Utilities.getRandomInt(-maxSpeed, -minSpeed);
		}
	}
	
	/**
	 * Process the game objects collision detection (if it has any) and movement.
	 */
	public abstract void tick();
	
	/**
	 * Render the game object in the window.
	 * @param g
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Helper function to return the bounds of the given object for collision detection.
	 * @return A rectangle object with bounds that surround the game object.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, this.size, this.size);
	}
	
	/**
	 * Set X function, does not allow player to move outside the window bounds, but allows hazards to leave bounds
	 * so they may get removed from the handler.
	 * @param x X position to set game object to.
	 */
	public void setX(int x) {
		if (id == ID.Hazard) {
			this.x = x;
			return;
		}
		
		if (x > Game.WIDTH - this.size - 18) {
			this.x = Game.WIDTH - this.size - 18;
		} else if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}
	
	/**
	 * Set Y function, does not allow player to move outside the window bounds, but allows hazards to leave bounds
	 * so they may get removed from the handler.
	 * @param y Y position to set game object to.
	 */
	public void setY(int y) {
		if (id == ID.Hazard) {
			this.y = y;
			return;
		}
		
		
		if (y > Game.HEIGHT - 50) {
			this.y = Game.HEIGHT - 50;
		} else if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return this.id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public int getVelX() {
		return this.velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelY() {
		return this.velY;
	}
	
	public void setSpeed(int speed) {
		this.maxSpeed = speed;
	}
	
	public int getSpeed() {
		return this.maxSpeed;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
}
