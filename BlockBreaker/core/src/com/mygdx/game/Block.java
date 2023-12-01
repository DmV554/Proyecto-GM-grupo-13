package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Block extends GameObject implements Collidable {
    private boolean destroyed;
    private int hitPoints;
    private GameLogic gameLogic;
    public Block(int x, int y, int ancho, int alto, Color color, int hitPoints, GameLogic gameLogic) {
        super(x,y,ancho,alto, color);
        this.hitPoints = hitPoints;
        this.gameLogic = gameLogic;
        destroyed = false;
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(getColor());
        shape.rect(getX(), getY(), getAncho(), getAlto());
    }

    public void hit() {
        hitPoints--;
        if (hitPoints == 2) {
            setColor(Color.RED);
        } else if (hitPoints == 1) {
            setColor(Color.ORANGE);
        }
        if (hitPoints <= 0) {
            destroyed = true;
            if (Math.random() < 0.4){
                gameLogic.generatePowerUp(getX(), getY());
            }
        }
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }

    public boolean getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean collidesWith(Collidable other) {
        if (other instanceof PingBall) {
            PingBall ball = (PingBall) other;
            return (getX() + getAncho() >= ball.getX() - ball.getSize()) && (getX() <= ball.getX() + ball.getSize())
                    && (getY() + getAlto() >= ball.getY() - ball.getSize()) && (getY() <= ball.getY() + ball.getSize());
        }
        return false;
    }

    @Override
    public void update() {
    }
}
