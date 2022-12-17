package com.ramadan;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	protected int minSpeed, maxSpeed;
	protected int size;
	
	public GameObject(int x, int y, int size, int minSpeed, int maxSpeed, ID id) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.maxSpeed = maxSpeed;
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
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, this.size, this.size);
	}
	
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
