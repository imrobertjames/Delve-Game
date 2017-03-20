package dev.delve.game.tiles;

import dev.delve.game.gfx.Assets;

public class StoneTile extends Tile{

	public StoneTile(int id) {
		super(Assets.stone, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
