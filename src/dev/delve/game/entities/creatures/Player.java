package dev.delve.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.delve.game.Handler;
import dev.delve.game.gfx.Animation;
import dev.delve.game.gfx.Assets;

public class Player extends Creature {
	
	//animations
	private Animation animDown, animUp, animLeft, animRight, animNone;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 16; //how far IN from the left
		bounds.y = 5; // How far DOWN from the top
		bounds.width = 32;
		bounds.height =59;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		animNone = new Animation(500, Assets.player_still);
	}

	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animNone.tick();
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up)
			yMove = -speed; // seems backwards for up movement, but java sets
							// 0,0 at top right, so to move down we add, to move
							// up we subtract
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if (xMove > 0) {
			return animRight.getCurrentFrame();
		}else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return animNone.getCurrentFrame();
		}
		
		
	}

}
