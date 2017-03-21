package dev.delve.game.states;

import java.awt.Graphics;

import dev.delve.game.Handler;
import dev.delve.game.entities.creatures.Player;
import dev.delve.game.entities.statics.Tree;
import dev.delve.game.tiles.Tile;
import dev.delve.game.worlds.World;

public class GameState extends State{
	
	private World world;
	private int spawnX;
	private int spawnY;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/World/world1.txt");
		handler.setWorld(world);
		
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		//Tile.tiles[0].render(g, 0, 0);
		//Tile.tiles[2].render(g, 64, 0);

	}
	
}
