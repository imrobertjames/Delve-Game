package dev.delve.game.worlds;

import java.awt.Graphics;
import java.io.IOException;

import dev.delve.game.Handler;
import dev.delve.game.entities.EntityManager;
import dev.delve.game.entities.creatures.Player;
import dev.delve.game.entities.statics.Tree;
import dev.delve.game.tiles.Tile;
import dev.delve.game.utils.Utils;
import dev.delve.game.worlds.generation.WorldGenerator;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private WorldGenerator worldGenerator;
	
	//Entities
	private EntityManager entityManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 300, 120));
		
		//loadWorld(path);
		loadRandWorld();
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		entityManager.tick();
	}

	public void render(Graphics g) {
		//need to limit tiles that are drawn to those that can be seen
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		//taking max between 0 and camera x offset (how much cam moved in pixels) / tile_width so we get it in terms of tiles, instead of pixels
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		// is the camera offset
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT); // top most tile user can see
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				Tile.grassTile.render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset())); //render grass behind everything for now
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.grassTile; // default to something incase of null
		return t;

	}

	private void loadWorld(String path) {
		/*
		 * File Format for loaded Worlds! First Line: width and height,
		 * separated by space. ex. 12 14 Second Line: spawn point of player,
		 * sep. by space. ex. 1 4 everything else: Grid of tile IDs with
		 * appropriate width and height from first line
		 */

		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		// Everything after this is world data
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				// (x + y * width) effectively converts the 3d array so we can
				// properly set values
				// the +4 is because we already set the first 4, so it positions
				// us correctly
			}
		}

	}
	
	private void loadRandWorld() {
		worldGenerator = new WorldGenerator();
		
		width = 512;
		worldGenerator.setWIDTH(width);
		height = 512;
		worldGenerator.setHEIGHT(height);
		spawnX = 0;
		spawnY = 0;
		
		worldGenerator.setFEATURE_SIZE(24.0);
		
		try {
			tiles = worldGenerator.GenerateRandomWorld();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Getters and setters

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
