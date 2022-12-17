package com.ramadan;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	// Track how many lives the player has
	public static int lives = 3;
	
	// Game instance for the HUD to call stop if needed.
	private Game game;
	
	public HUD(Game game) {
		this.game = game;
	}
	
	/**
	 * Render HUD on screen.
	 * @param g Graphics objects for rendering
	 * @param points Value of points to draw on screen
	 */
	public void render(Graphics g, int points) {
		g.setColor(Color.WHITE);
		g.drawString("Points: " +  points, 30, 30);
		g.drawString("Lives: " + lives, 30, 50);
	}
	
	public void gainLife() {
		lives++;
	}
	
	/**
	 * Decrement lives and end game if lives are less than or equal to 0.
	 */
	public void loseLife() {
		lives--;
		if (lives <= 0) {
			game.endGame();
		}
	}
	
}
