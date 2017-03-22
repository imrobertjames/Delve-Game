package dev.delve.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16, margin = 1;

	public static BufferedImage[] player_down, player_up, player_left, player_right, player_still;

	public static BufferedImage[] btn_start;
	
	public static BufferedImage grass, graybrick, brownstone, tree, lava, dooropen, doorclosed, stone;

	private static SpriteSheet characters, map, ui;

	public static void init() {
		/*
		 * gets all the sprites and assigns them to variables
		 */
		// just going to load a few for now
		map = new SpriteSheet(ImageLoader.loadImage("/textures/tileset.png"));

		grass = map.crop((width+margin)*3, (height+margin)*16, width, height);
		tree = map.crop((width+margin)*13, (height+margin)*9, width, height);
		stone = map.crop((width+margin)*55, (height+margin)*21, width, height);
		
		
		//UI
		ui = new SpriteSheet(ImageLoader.loadImage("/textures/start_button.png"));
		btn_start = new BufferedImage[2];
		btn_start[0] = ui.crop(0, 0, 128, 64);
		btn_start[1] = ui.crop(0, 64, 128, 64);
		

		characters = new SpriteSheet(ImageLoader.loadImage("/textures/gfx/character.png"));

		//player = characters.crop(0, 0, width, height);
		int pheight = height*2; //fixes a weird issue with player gfx
		player_still = new BufferedImage[1];
		player_still[0] = characters.crop(0, 0, width, pheight);
		
		player_down = new BufferedImage[4];
		player_down[0] = characters.crop(0, 0, width, pheight);
		player_down[1] = characters.crop(width, 0, width, pheight);
		player_down[2] = characters.crop(width*2, 0, width, pheight);
		player_down[3] = characters.crop(width*3, 0, width, pheight);
		
		player_left = new BufferedImage[4];
		player_left[0] = characters.crop(0, pheight*3, width, pheight);
		player_left[1] = characters.crop(width, pheight*3, width, pheight);
		player_left[2] = characters.crop(width*2, pheight*3, width, pheight);
		player_left[3] = characters.crop(width*2, pheight*3, width, pheight);
		
		player_right = new BufferedImage[4];
		player_right[0] = characters.crop(0, pheight, width, pheight);
		player_right[1] = characters.crop(width, pheight, width, pheight);
		player_right[2] = characters.crop(width*2, pheight, width, pheight);
		player_right[3] = characters.crop(width*2, pheight, width, pheight);
		
		player_up = new BufferedImage[4];
		player_up[0] = characters.crop(0, pheight * 2, width, pheight);
		player_up[1] = characters.crop(width, pheight * 2, width, pheight);
		player_up[2] = characters.crop(width*2, pheight * 2, width, pheight);
		player_up[3] = characters.crop(width*2, pheight*2, width, pheight);
	}

}
