package dev.delve.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.delve.game.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x, y; // floats for smooth movement
	protected int width, height;
	protected Rectangle bounds;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0,0, width, height);
	}
	
	public Rectangle getcollisionBounds(float xOffset, float yOffset) {
		//returns rectangle that covers the entity
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if(e.getcollisionBounds(0f, 0f).intersects(getcollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
