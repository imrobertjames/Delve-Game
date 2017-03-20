package dev.delve.game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	
	private int speed, index;
	private BufferedImage[] frames;
	public long lastTime, timer;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime; //time in millis since last tick
		lastTime = System.currentTimeMillis(); //REEEESET
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0; //restart
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}
