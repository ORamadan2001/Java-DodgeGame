package com.ramadan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Hazard extends GameObject {

	public Hazard(int x, int y, int size, int minSpeed, int maxSpeed, ID id) {
		super(x, y, size, minSpeed, maxSpeed, id);
	}
	
	public Hazard(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		setX(getX() + velX);
		setY(getY() + velY);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, size, size);
	}	

}
