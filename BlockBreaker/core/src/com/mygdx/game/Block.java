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
    	shape.setColor(color);
        shape.rect(x, y, ancho, alto);
    }

    public void hit() {
        hitPoints--;
        setColor(Color.GOLD);
        if (hitPoints <= 0) {
            destroyed = true;
            if (Math.random() < 0.2){
                gameLogic.generatePowerUp(x, y);
            }
        }
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(x, y, ancho, alto);
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
            return (x + ancho >= ball.getX() - ball.getSize()) && (x <= ball.getX() + ball.getSize())
                    && (y + alto >= ball.getY() - ball.getSize()) && (y <= ball.getY() + ball.getSize());
        }
        return false;
    }

    @Override
    public void update() {
    }
}
