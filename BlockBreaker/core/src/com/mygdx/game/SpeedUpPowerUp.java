package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;

public class SpeedUpPowerUp implements PowerUp {
    private final Color color = Color.RED;
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getBall().increaseSpeed(); // Asegúrate de tener este método en PingBall
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void deactivate(GameLogic gameLogic) {

    }
}