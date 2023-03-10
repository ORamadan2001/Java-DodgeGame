package com.ramadan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.nio.file.FileSystemNotFoundException;

public class Player extends GameObject {
	
	private Handler handler;
	private HUD hud;
	private long lastCollision;

	/**
	 * Create a player object
	 * @param x X position of the top left corner of the game object relative to the Window.
	 * @param y Y position of the top left corner of the game object relative to the Window.
	 * @param size Size of the object.
	 * @param minSpeed Minimum speed the object can have
	 * @param maxSpeed Maximum speed the object can have
	 * @param id enum to identify if object is a player or a hazard.
	 * @param handler handler for collision detection
	 * @param hud hud HUD to decrement lives value
	 */
	public Player(int x, int y, int size, int minSpeed, int maxSpeed, ID id, Handler handler, HUD hud) {
		super(x, y, size, minSpeed, maxSpeed, id);
		this.handler = handler;
		this.hud = hud;
		lastCollision = System.currentTimeMillis();
	}

	public Player(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		lastCollision = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		setX(getX() + velX);
		setY(getY() + velY);
		
		/* 
		 * Add a 0.5 second grace period after a collision in order to prevent multiple collisions
		 * from one hazard.
		 */ 
		if (System.currentTimeMillis() - lastCollision > 500) {
			checkCollision();
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, size, size);
	}
	
	/**
	 * Check if a collision has occurred and detect life loss if there has been a collision.
	 */
	public void checkCollision() {
		
		for (int i = 0; i < handler.size(); i++) {
		
			GameObject obj = handler.objects.get(i);
			if (obj.id == ID.Hazard) {
				
				// Check if there is a collision.
				if (getBounds().intersects(obj.getBounds())) {
					hud.loseLife();
					lastCollision = System.currentTimeMillis();
				}
				
			}
			
		}
	}
	
}
