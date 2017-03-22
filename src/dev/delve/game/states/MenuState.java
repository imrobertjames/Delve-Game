package dev.delve.game.states;

import java.awt.Graphics;

import dev.delve.game.Handler;
import dev.delve.game.gfx.Assets;
import dev.delve.game.ui.ClickListener;
import dev.delve.game.ui.UIImageButton;
import dev.delve.game.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(260, 200, 128, 64, Assets.btn_start, new ClickListener(){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		uiManager.render(g);
	}

}
