package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BlockBreakerGame extends ApplicationAdapter {
	private GameLogic gameLogic;
	private GameRenderer gameRenderer;

	@Override
	public void create() {
		gameLogic = new GameLogic();
		gameRenderer = new GameRenderer(gameLogic);
	}

	@Override
	public void render() {
		gameLogic.update();
		gameRenderer.render();
	}

	@Override
	public void dispose() {
		gameRenderer.dispose();
	}

}
