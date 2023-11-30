package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class FallingPowerUp extends GameObject {
    private final PowerUp powerUp;

    public FallingPowerUp(float x, float y, PowerUp powerUp) {
        super(x, y, 10, 10, Color.GREEN); // Ejemplo: Tamaño 10x10 y color verde
        this.powerUp = powerUp;
    }

    @Override
    public void update() {
        // Hacer que el power-up caiga hacia abajo
        y -= 4; // Ejemplo: Mover 2 píxeles hacia abajo cada actualización
    }

    public void activatePowerUp(GameLogic gameLogic) {
        powerUp.activate(gameLogic);
    }

    @Override
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.rect(x, y, ancho, alto);
    }

    public void render(ShapeRenderer shape) {
        shape.setColor(color);
        shape.rect(x, y, ancho, alto);
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(x, y, ancho, alto);
    }

    public float getSize() {
        return ancho;
    }
}