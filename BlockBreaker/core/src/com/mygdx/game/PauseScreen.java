package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class PauseScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;

    public PauseScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "show");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (batch == null) {
            batch = new SpriteBatch();
        }
        if (font == null) {
            font = new BitmapFont();
            font.getData().setScale(2); // Ajusta el tamaño del texto según sea necesario
        }

        String pauseText = "PauseChamp";
        GlyphLayout layout = new GlyphLayout(font, pauseText); // Usar GlyphLayout para obtener el ancho del texto
        float width = layout.width; // Obtener el ancho del texto

        batch.begin();
        font.draw(batch, pauseText, (Gdx.graphics.getWidth() - width) / 2, (float) Gdx.graphics.getHeight() / 2);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "resize");

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "pause");

    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "resume");

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "hide");

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("PauseScreen", "dispose");
        batch.dispose();
        font.dispose();

    }
}
