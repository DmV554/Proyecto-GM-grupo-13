package com.mygdx.game;

public class SpeedUpPowerUp implements PowerUp {
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getBall().increaseSpeed(); // Asegúrate de tener este método en PingBall
    }
}