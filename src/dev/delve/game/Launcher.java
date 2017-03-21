package dev.delve.game;

import dev.delve.game.display.Display;

public class Launcher {
	/*
	 * Only responsible for starting up our game.
	 */

	public static void main(String[] args) {
		int width = 900;
		int height = 600;
		int scale = 1; //broken, don't use this.
		
		Game game = new Game("Delve", width, height, scale); 
		game.start();
	}
}
