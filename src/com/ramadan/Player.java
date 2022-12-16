package com.ramadan;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(int x, int y, int size, int speed, ID id) {
		super(x, y, size, speed, id);
	}

	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		setX(getX() + velX);
		setY(getY() + velY);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, size, size);
	}
	
}
