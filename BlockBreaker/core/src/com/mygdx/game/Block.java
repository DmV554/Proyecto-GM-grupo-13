package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import com.badlogic.gdx.graphics.Color;

public class Block extends GameObject implements Collidable {
    private boolean destroyed;
    private int hitPoints;

    public Block(int x, int y, int ancho, int alto, Color color, int hitPoints) {
        super(x,y,ancho,alto, color);
        this.hitPoints = hitPoints;
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
        }
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
