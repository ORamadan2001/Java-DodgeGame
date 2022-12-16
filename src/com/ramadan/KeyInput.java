package com.ramadan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean W = false, A = false, S = false, D = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.objects.size(); i++) {
			
			/* Get the object in the handler and its speed, if it is a player, react to corresponding button press by
			* setting its velocity in the corresponding direction to its speed.
			*/
			GameObject obj = handler.objects.get(i);
			int speed = obj.getSpeed();
			
			if (obj.id == ID.Player) {
				switch (key) {
				case KeyEvent.VK_W:
					obj.setVelY(-speed);
					W = true;
					break;
				case KeyEvent.VK_S:
					obj.setVelY(speed);
					S = true;
					break;
				case KeyEvent.VK_A:
					obj.setVelX(-speed);
					A = true;
					break;
				case KeyEvent.VK_D:
					obj.setVelX(speed);
					D = true;
					break;
				}	
			}
			
		}
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.objects.size(); i++) {	
			
			/* Get the object in the handler and its speed, if it is a player, react to corresponding button release by
			* either setting its velocity to 0 (if the opposite direction is not being held down), or to the correct velocity
			* if the opposing button is being held down.
			* 
			* This was done to solve issues when it came to holding down two opposing buttons (such as A and D), and then releasing one meant
			* the player did not move in the other direction, but instead came to a stop even though a button was being held down.
			*/
			GameObject obj = handler.objects.get(i);
			int speed = obj.getSpeed();
			if (obj.id == ID.Player) {
				switch (key) {
				case KeyEvent.VK_W:
					if (S) {
						obj.setVelY(speed);
					} else {
						obj.setVelY(0);
					}
					W = false;
					break;
				case KeyEvent.VK_S:
					if (W) {
						obj.setVelY(-speed);
					} else {
						obj.setVelY(0);
					}
					S = false;
					break;
				case KeyEvent.VK_A:
					if (D) {
						obj.setVelX(speed);
					} else {
						obj.setVelX(0);
					}
					A = false;
					break;
				case KeyEvent.VK_D:
					if (A) {
						obj.setVelX(-speed);
					} else {
						obj.setVelX(0);
					}
					D = false;
					break;
				}	
			}
			
		}
		
	}
	
}
