package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BlockBreakerGame extends ApplicationAdapter {
	private GameLogic gameLogic;
	private GameRenderer gameRenderer;

	@Override
	public void create() {
		// Inicializa GameLogic y GameRenderer
		gameLogic = new GameLogic(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameRenderer = new GameRenderer(gameLogic);
	}

	@Override
	public void render() {
		// Limpiar la pantalla
		Gdx.gl.glClearColor(0, 0, 0, 1); // Establece el color de fondo (negro en este caso)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Actualizar la lógica del juego
		gameLogic.update();
		// Renderizar el juego
		gameRenderer.render();
	}

	@Override
	public void dispose() {
		// Limpia los recursos
		gameRenderer.dispose();
	}

}
