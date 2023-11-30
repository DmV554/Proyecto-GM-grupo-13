package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class ExtraLifePowerUp implements PowerUp {
    private final Color color = Color.GREEN;
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getStateManager().incrementLife();
    }

    @Override
    public Color getColor() {
        return color;
    }
}