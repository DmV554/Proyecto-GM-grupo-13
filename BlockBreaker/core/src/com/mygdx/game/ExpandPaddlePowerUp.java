package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class ExpandPaddlePowerUp implements PowerUp {
    private final Color color = Color.BLUE;
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getPaddle().increaseSize();
    }

    @Override
    public Color getColor() {
        return color;
    }
}