package dev.delve.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;

	public static BufferedImage[] player_down, player_up, player_left, player_right, player_still;

	public static BufferedImage grass, graybrick, brownstone, tree, lava, dooropen, doorclosed, stone;

	private static SpriteSheet characters, map;

	public static void init() {
		/*
		 * gets all the sprites and assigns them to variables
		 */
		// just going to load a few for now
		map = new SpriteSheet(ImageLoader.loadImage("/textures/tileset.png"));

		grass = map.crop(0, 0, width, height);
		stone = map.crop(width*6, height*11, width, height);
		graybrick = map.crop(width, 0, width, height);
		brownstone = map.crop(width * 2, 0, width, height);
		tree = map.crop(0, height, width, height);
		lava = map.crop(width * 3, 0, width, height);
		dooropen = map.crop(width * 7, height, width, height);
		doorclosed = map.crop(width * 8, height, width, height);

		characters = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));

		//player = characters.crop(0, 0, width, height);
		player_still = new BufferedImage[1];
		player_still[0] = characters.crop(width, 0, width, height);
		
		player_down = new BufferedImage[3];
		player_down[0] = characters.crop(0, 0, width, height);
		player_down[1] = characters.crop(width, 0, width, height);
		player_down[2] = characters.crop(width*2, 0, width, height);
		
		player_left = new BufferedImage[3];
		player_left[0] = characters.crop(0, height, width, height);
		player_left[1] = characters.crop(width, height, width, height);
		player_left[2] = characters.crop(width*2, height, width, height);
		
		player_right = new BufferedImage[3];
		player_right[0] = characters.crop(0, height * 2, width, height);
		player_right[1] = characters.crop(width, height * 2, width, height);
		player_right[2] = characters.crop(width*2, height * 2, width, height);
		
		player_up = new BufferedImage[3];
		player_up[0] = characters.crop(0, height * 3, width, height);
		player_up[1] = characters.crop(width, height * 3, width, height);
		player_up[2] = characters.crop(width*2, height * 3, width, height);
	}

}
