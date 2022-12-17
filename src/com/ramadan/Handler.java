package com.ramadan;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	// Linked list to contain all game objects so they can be rendered and ticked when the handler is called.
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			
			// Tick every object in the linkedList.
			GameObject obj = objects.get(i);
			obj.tick();
			
			//Check if object is a hazard outside window bounds, if so, remove it from the handler.
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
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public int size() {
		return objects.size();
	};
	
}
