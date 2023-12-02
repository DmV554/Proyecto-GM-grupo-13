package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class BlockBreakerGame extends Game {
    private GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        this.setScreen(MenuScreen.getInstance()); // Iniciar con el menú
    }


    public void startGame() {
        setScreen(new GameScreen());
    }

    public void pauseGame() {
        setScreen(new PauseScreen(this));
    }


    public void resumeGame() {
        setScreen(gameScreen);
    }

    public void restartLevel() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    public void showGameOver() {
        setScreen(GameOverScreen.getInstance(this));
    }


    public void quitGame() {
        Gdx.app.exit();
    }

}

