package dev.delve.game.states;

import java.awt.Graphics;

import dev.delve.game.Handler;
import dev.delve.game.entities.creatures.Player;
import dev.delve.game.tiles.Tile;
import dev.delve.game.worlds.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	private int spawnX;
	private int spawnY;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/World/world1.txt");
		handler.setWorld(world);
		spawnX = world.getSpawnX() * Tile.TILE_WIDTH;
		spawnY = world.getSpawnY() * Tile.TILE_HEIGHT;
		
		player = new Player(handler, spawnX, spawnY);
		
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
	
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		//Tile.tiles[0].render(g, 0, 0);
		//Tile.tiles[2].render(g, 64, 0);
		
		player.render(g); //should be on of the last so it's on top
	}
	
}
