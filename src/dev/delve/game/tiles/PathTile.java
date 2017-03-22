package dev.delve.game.tiles;

import java.awt.image.BufferedImage;

import dev.delve.game.gfx.Assets;

public class PathTile extends Tile {

	private BufferedImage pathTile;
	
	public PathTile(int id, int sid) {
		super(getPathTile(sid), id);
		// TODO Auto-generated constructor stub
	}
	
	private static BufferedImage getPathTile(int sid) {
		switch(sid) {
		case 1: 
			return Assets.path;
		case 2:
			return Assets.path_top;
		case 3:
			return Assets.path_bot;
		case 4:
			return Assets.path_left;
		case 5:
			return Assets.path_right;
		case 6:
			return Assets.path_topright;
		case 7:
			return Assets.path_botright;
		case 8:
			return Assets.path_topleft;
		case 0:
			return Assets.path_botleft;
		default:
			return Assets.path;	
		}
	}

}
