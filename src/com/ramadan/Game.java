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
	private HUD hud;
	
	private boolean gameLost = false;
	private double gameTime; // How long the game has been running in ms (Pauses and shop time should not be included).
	private int maxHazards = 100;
	private int ticksSinceSpawn = 0;
	private int spawnTicks = 5;
	private long timer;
	private int timeBasedDifficulty = 0;
	private int points;
	
	
	/**
	 * Instantiate a game object that creates a window containing this game instance.
	 */
	public Game() {
		new Window(WIDTH, HEIGHT, NAME, this);
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		hud = new HUD(this);
		handler.addObject(new Player(WIDTH/2 - 50, HEIGHT/2 - 50, ID.Player, handler, hud));
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
		timer = System.currentTimeMillis();
		int frames = 0;
		gameTime = 0;
		
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
			
			if (gameLost) {
				stop();
			}
			
			// Every second, print out number of elapsed frames and reset frame counter to constantly output FPS.
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " - Active Objects: " + handler.size() + " Difficulty: " + timeBasedDifficulty);
				frames = 0;
			}
			
			if (System.currentTimeMillis() - timer > 125)
				gameTime += 125;
			
			
		}
		stop();
	}

	private void tick() {
		handler.tick();
		
		if (ticksSinceSpawn > spawnTicks) {
			if (handler.size() < maxHazards) {
				for (int i = 0; i <= timeBasedDifficulty/3; i++) {
					spawnStandardHazard();
				}
			}
			ticksSinceSpawn = 0;
		} else {
			ticksSinceSpawn++;
		}
		
		points = (int) (gameTime/10000);
		timeBasedDifficulty = points / 1000;
		
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
		
		hud.render(g, points, gameTime);
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void spawnStandardHazard() {
		int x = -1, y = -1;
		int size = Utilities.getRandomInt(10, 15);
		int minSpeed = 5 + timeBasedDifficulty;
		int maxSpeed = Utilities.getRandomInt(8, 12) + timeBasedDifficulty;
		int wall = Utilities.getRandomInt(1, 4);
		
		
		switch(wall) {
		case 1: // Left vertical spawn
			x = 0;
			y = Utilities.getRandomInt(0, Game.HEIGHT);
			break;
		case 2: //Right Vertical spawn
			x = Game.WIDTH;
			y = Utilities.getRandomInt(0, Game.HEIGHT);
			break;
		case 3: // Top horizontal spawn
			x = Utilities.getRandomInt(0, Game.WIDTH);
			y = 0; 
			break;
		case 4:
			x = Utilities.getRandomInt(0, Game.WIDTH);
			y = Game.HEIGHT;
			break;
		}
		handler.addObject(new Hazard(x, y, size, minSpeed, maxSpeed, ID.Hazard));
	}
	
	public void endGame() {
		gameLost = true;
	}
	
}
