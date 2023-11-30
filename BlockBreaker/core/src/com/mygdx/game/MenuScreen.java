package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;

public class MenuScreen extends ScreenAdapter implements InputProcessor {
    private static MenuScreen instance;
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch spriteBatch;

    private MenuScreen() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("d3e7b107-954a-4d37-9a68-f655f4c9de15.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(this);
    }

    public static MenuScreen getInstance() {
        if (instance == null) {
            instance = new MenuScreen();
        }
        return instance;
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }


    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            ((BlockBreakerGame) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            return true;
        }
        return false;
    }

    public void dispose() {
        backgroundTexture.dispose();
        spriteBatch.dispose();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null); // Desactiva el InputProcessor al salir del menú
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
