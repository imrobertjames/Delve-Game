package dev.delve.game.entities.statics;

import dev.delve.game.Handler;
import dev.delve.game.entities.Entity;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
