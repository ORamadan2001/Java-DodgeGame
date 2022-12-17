package com.ramadan;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int lives = 3;
	private Game game;
	
	public HUD(Game game) {
		this.game = game;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int points, double gameTime) {
		g.setColor(Color.WHITE);
		g.drawString("Points: " +  points, 30, 30);
		g.drawString("Lives: " + lives, 30, 50);
	}
	
	public void gainLife() {
		lives++;
	}
	
	public void loseLife() {
		lives--;
		if (lives == 0) {
			game.endGame();
		}
	}
	
}
