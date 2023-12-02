package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.audio.Music;

public class GameOverScreen implements Screen {
    private static GameOverScreen instance;
    private final Stage stage;
    private final Skin skin;
    private final BitmapFont gameOverFont;
    private final Music gameOverMusic;


    GameOverScreen(final BlockBreakerGame game) {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        gameOverFont = new BitmapFont(Gdx.files.internal("GAMEOVER.fnt"));
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("GAMEOVER.mp3"));

        // Crear y configurar botones...
        TextButton restartButton = new TextButton("Restart", skin);
        restartButton.setPosition(500, 200); // Ajusta la posición según sea necesario
        restartButton.setSize(200, 50);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.restartLevel();
            }
        });

        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        mainMenuButton.setPosition(500, 150); // Ajusta la posición según sea necesario
        mainMenuButton.setSize(200, 50);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.create();
            }
        });

        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
    }

    public static GameOverScreen getInstance(BlockBreakerGame game) {
        if (instance == null) {
            instance = new GameOverScreen(game);
        }
        return instance;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        gameOverMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        Batch batch = stage.getBatch();
        batch.begin();
        gameOverFont.draw(batch, "GAME OVER", 500, 400);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        // No es necesario implementar este método
    }

    @Override
    public void pause() {
        // No es necesario implementar este método
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameOverMusic.stop();
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
