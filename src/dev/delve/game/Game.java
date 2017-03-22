package dev.delve.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.delve.game.display.Display;
import dev.delve.game.gfx.Assets;
import dev.delve.game.gfx.GameCamera;
import dev.delve.game.input.KeyManager;
import dev.delve.game.input.MouseManager;
import dev.delve.game.states.CreationState;
import dev.delve.game.states.GameState;
import dev.delve.game.states.MenuState;
import dev.delve.game.states.State;

public class Game implements Runnable {
	/*
	 * Main class. Holds all base code [starts, runs, closes] What is a thread
	 * in layman? Java makes a big program that runs all our code We use a
	 * thread as a "mini program" in big program, in it's own space
	 * 
	 */

	private Display display;
	private int width, height, scale;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState;
	public State menuState;
	public State creationState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	private Handler handler;
	
	public Game(String title, int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.scale = scale;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		// synchronized used when working directly with thread, like start/stop
		thread = new Thread(this); // pass game through to run it in a thread

		thread.start(); // also calls run()
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() {
		display = new Display(title, width, height, scale);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		creationState = new CreationState(handler);

		State.setState(gameState);
		State.setState(menuState);
	}

	private void tick() {
		keyManager.tick();
		
		if (State.getState() != null)
			State.getState().tick();
	}

	private void render() {
		// set to render current buffer
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear the Screen
		g.clearRect(0, 0, width*scale, height*scale);
		/*
		 * Graphics object allows us to draw things, like a paintbrush to the
		 * canvas.
		 */
		// Draw here!
		if (State.getState() != null)
			State.getState().render(g);
		// End Drawing!
		bs.show(); // works buffer magic.
		g.dispose();
	}

	public void run() {

		init(); // gets everything ready

		int tps = 60; // ticks per second
		double timePerTick = 1000000000.0 / tps; // 1bil nanoseconds = 1 sec
		double delta = 0.0;
		long now;
		long lastTime = System.nanoTime(); // nnnnnnnanoclock!
		long timer = 0;
		int ticks = 0;
		int fps = 0;

		/*
		 * Game Loop: updates all variables, positions of objects, etc Renders
		 * everything to the screen Loops
		 */

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick; // now-lastTime = time
														// interval since last
														// called.
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
				ticks++;
			}
			if (timer >= 1000000000) {
				System.out.println("Ticks: " + ticks + " | FPS: " + fps);
				ticks = 0;
				fps = 0;
				timer = 0;
			}

			//fps++;
			//render(); // i choose to allow rendering to happen as much as it
						// wants.
		}

		stop();

	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
