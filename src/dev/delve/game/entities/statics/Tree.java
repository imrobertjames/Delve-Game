package dev.delve.game.entities.statics;

import java.awt.Graphics;

import dev.delve.game.Handler;
import dev.delve.game.gfx.Assets;
import dev.delve.game.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*2);
		// TODO Auto-generated constructor stub
		
		bounds.x = 5;
		bounds.y = (int) (height / 2);
		bounds.width = width - 5;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
