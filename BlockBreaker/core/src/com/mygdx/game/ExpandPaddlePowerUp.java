package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class ExpandPaddlePowerUp implements PowerUp {
    private boolean active = false;
    private final Color color = Color.BLUE;
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getPaddle().increaseSize();
        active = true;
    }

    public void deactivate(GameLogic gameLogic) {
            gameLogic.getPaddle().decreaseSize();
            active = false;
    }
    @Override
    public Color getColor() {
        return color;
    }
}