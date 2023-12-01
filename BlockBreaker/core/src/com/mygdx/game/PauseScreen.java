package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;

    private final BlockBreakerGame game;
    private final Stage stage;

    public PauseScreen(final BlockBreakerGame game) {
        this.game = game;
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        TextButton restartButton = new TextButton("Restart", skin);
        restartButton.setPosition(500, 150);
        restartButton.setSize(200, 50);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((BlockBreakerGame) Gdx.app.getApplicationListener()).restartLevel();
            }
        });

        TextButton returnToGameButton = new TextButton("Volver al Juego", skin);
        returnToGameButton.setPosition(500, 200); // Ajusta la posición según sea necesario
        returnToGameButton.setSize(200, 50);
        returnToGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resumeGame();
            }
        });

        TextButton quitButton = new TextButton("Quit", skin);
        quitButton.setPosition(500, 100); // Ajusta la posición
        quitButton.setSize(200, 50);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((BlockBreakerGame) Gdx.app.getApplicationListener()).quitGame();
            }
        });

        stage.addActor(returnToGameButton);
        stage.addActor(restartButton);
        stage.addActor(quitButton);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if (batch == null) {
            batch = new SpriteBatch();
        }
        if (font == null) {
            font = new BitmapFont();
            font.getData().setScale(2); // Ajusta el tamaño del texto según sea necesario
        }

        String pauseText = "Pause";
        GlyphLayout layout = new GlyphLayout(font, pauseText); // Usar GlyphLayout para obtener el ancho del texto
        float width = layout.width; // Obtener el ancho del texto




        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ((BlockBreakerGame) Gdx.app.getApplicationListener()).resumeGame();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.restartLevel();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }

        batch.begin();
        font.draw(batch, pauseText, (Gdx.graphics.getWidth() - width) / 2, (float) Gdx.graphics.getHeight() / 2);
        batch.end();
    }



    @Override
    public void resize(int width, int height) {
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("PauseScreen", "resize");

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("PauseScreen", "pause");

    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("PauseScreen", "hide");

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("PauseScreen", "dispose");
        batch.dispose();
        font.dispose();

    }
}
