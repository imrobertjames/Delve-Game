package dev.delve.game.tiles;

import dev.delve.game.gfx.Assets;

public class TreeTile extends Tile {

	public TreeTile(int id) {
		super(Assets.tree, id);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
