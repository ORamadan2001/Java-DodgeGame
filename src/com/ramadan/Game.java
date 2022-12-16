package com.ramadan;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -978257958772857869L;

	static final int WIDTH = 1280, HEIGHT = 720;
	final String NAME = "Dodge Game";
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	/**
	 * Instantiate a game object that creates a window containing this game instance.
	 */
	public Game() {
		new Window(WIDTH, HEIGHT, NAME, this);
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		handler.addObject(new Player(WIDTH/2 - 50, HEIGHT/2 - 50, ID.Player));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ticks = 60.0; // How many ticks per second to run the game at.
		double ns = 1000000000 / ticks; // Calculated nanoseconds per tick.
		double delta = 0; // Tick backlog tracker.
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // Add to tick backlog based on elapsed time.
			lastTime = now;
			
			// While there is a tick backlog, repeatedly call tick and decrement data until next run loop.
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if (running)
				render();
			frames++;
			
			// Every second, print out number of elapsed frames and reset frame counter to constantly output FPS.
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
			
		}
		stop();
	}

	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
}
