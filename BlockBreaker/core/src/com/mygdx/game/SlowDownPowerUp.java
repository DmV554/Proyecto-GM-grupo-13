
package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;

public class SlowDownPowerUp implements PowerUp {
    private final Color color = Color.PURPLE;
    @Override
    public void activate(GameLogic gameLogic) {
        gameLogic.getBall().decreaseSpeed(1.2f); // Asegúrate de tener este método en PingBall
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void deactivate(GameLogic gameLogic) {

    }
}