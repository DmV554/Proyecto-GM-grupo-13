package com.mygdx.game;

public class ExpandPaddlePowerUp implements PowerUp {
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getPaddle().increaseSize();
    }
}