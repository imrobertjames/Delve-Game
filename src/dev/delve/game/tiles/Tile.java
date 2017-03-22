package dev.delve.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// STATIC STUFF FOR TRACKING AND ACCESSING ANYWHERE
	public static Tile[] tiles = new Tile[256]; // holds various tile objects
	public static Tile grassTile = new GrassTile(0);
	//public static Tile stoneTile = new StoneTile(6);
	//public static Tile treeTile = new TreeTile(5);
	public static Tile waterTile = new WaterTile(2);
	public static Tile water2Tile = new WaterTile(3);
	public static Tile water3Tile = new WaterTile(4);

	// CLASS

	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this; // stores instance upon instantiating in static tiles
							// property, allowing Tile.tiles to happen from
							// anywhere
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}

}
