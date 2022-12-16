package com.ramadan;

import java.awt.Graphics;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	protected int speed;
	protected int size;
	
	public GameObject(int x, int y, int size, int speed, ID id) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.id = id;
		velX = 0;
		velY = 0;
	}
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.size = 10;
		this.speed = 7;
		this.id = id;
		velX = 0;
		velY = 0;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		if (x > Game.WIDTH - this.size - 18) {
			this.x = Game.WIDTH - this.size - 18;
		} else if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}
	
	public void setY(int y) {
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
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
}
