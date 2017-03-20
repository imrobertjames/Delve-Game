package dev.delve.game;

import dev.delve.game.display.Display;

public class Launcher {
	/*
	 * Only responsible for starting up our game.
	 */

	public static void main(String[] args) {
		int width = 640;
		int height = 360;
		int scale = 1;
		
		Game game = new Game("Delve", width, height, scale); 
		game.start();
	}
}
