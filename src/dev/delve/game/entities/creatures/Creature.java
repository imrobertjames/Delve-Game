package dev.delve.game.entities.creatures;

import dev.delve.game.Handler;
import dev.delve.game.entities.Entity;
import dev.delve.game.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH; // temp
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) //check if movement is going to cause a collision, don't move if it does
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		if(xMove > 0) { //Moving right
			
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH; // x coordinate of tile we are trying to move IN to
			int upperY = (int) (y + bounds.y) / Tile.TILE_HEIGHT; // tx, upperY = upper right corner
			int lowerY = (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT; // tx, lowerY = lower right corner
			
			if (!collisionWithTile(tx, upperY) && !collisionWithTile(tx, lowerY)) { 
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
			
		}else if(xMove < 0) { // Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH; // note this variable is calculated different than above
			int upperY = (int) (y + bounds.y) / Tile.TILE_HEIGHT; // tx, upperY = upper right corner
			int lowerY = (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT; // tx, lowerY = lower right corner
			
			if (!collisionWithTile(tx, upperY) && !collisionWithTile(tx, lowerY)) { 
				// (y + bounds.y) / TILE_HEIGHT - checks upper right corner
				// (y + bounds.y + bounds.height) / TILE_HEIGHT - checks bottom right
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) { //up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			int leftX = (int) (x + bounds.x) / Tile.TILE_WIDTH; //leftX, ty = upper left corner
			int rightX = (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH; //rightX, ty = upper right corner
			
			if(!collisionWithTile(leftX, ty) &&
					!collisionWithTile(rightX, ty)){
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
			
		}else if(yMove > 0) { //down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			int leftX = (int) (x + bounds.x) / Tile.TILE_WIDTH; //leftX, ty = upper left corner
			int rightX = (int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH; //rightX, ty = upper right corner
			
			if(!collisionWithTile(leftX, ty) &&
					!collisionWithTile(rightX, ty)){
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	//GETTERS AND SETTERS
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
