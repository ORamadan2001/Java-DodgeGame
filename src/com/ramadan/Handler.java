package com.ramadan;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	Player player = null;
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			
			GameObject obj = objects.get(i);
			obj.tick();
			
			if (obj.id == ID.Hazard) {
				if (obj.x < 0 - obj.size || obj.x > Game.WIDTH + obj.size || 
						obj.y < 0 - obj.size || obj.y > Game.HEIGHT + obj.size) {
					removeObject(obj);
				}
			}
			
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
		if (obj.id == ID.Player) {
			player = (Player) obj;
		}
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public int size() {
		return objects.size();
	};
	
	public void collision() {
		
	}
	
}
