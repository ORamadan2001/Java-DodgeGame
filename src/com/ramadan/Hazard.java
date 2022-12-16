package com.ramadan;

import java.awt.Graphics;

public class Hazard extends GameObject {

	public Hazard(int x, int y, int size, int speed, ID id) {
		super(x, y, size, speed, id);
	}
	
	public Hazard(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
