package com.mygdx.game;

public class ExtraLifePowerUp implements PowerUp {
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getStateManager().incrementLife();
    }
}