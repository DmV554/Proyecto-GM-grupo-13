package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    private GameLogic gameLogic;
    private GameRenderer gameRenderer;
    public GameScreen() {
        gameLogic = new GameLogic();
        gameRenderer = new GameRenderer(gameLogic);
    }


    @Override
    public void render(float delta) {
        gameLogic.update();
        gameRenderer.render();
    }

    // Otros métodos como dispose()
}