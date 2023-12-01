package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle extends GameObject implements Collidable {
    
    public Paddle(int x, int y, int ancho, int alto, Color color) {
    	super(x,y,ancho,alto, color);
    }

    public boolean collidesWith(Collidable other) {
        if (other instanceof PingBall) {
            PingBall ball = (PingBall) other;

            boolean intersectaX = (getX() + getAncho() >= ball.getX() - ball.getSize()) && (getX() <= ball.getX() + ball.getSize());
            boolean intersectaY = (getY() + getAlto() >= ball.getY() - ball.getSize()) && (getY() <= ball.getY() + ball.getSize());
            return intersectaX && intersectaY;
        }

        return false;
    }

    public void update() {
        float x2 = getX(); //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =getX()-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=getX()+15;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2+getAncho() < Gdx.graphics.getWidth()) {
            setX(x2);
        }
    }
    public Rectangle getBoundingRectangle() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }

    @Override
    public void draw(ShapeRenderer shape) {

    }

    public void increaseSize() {
        setAncho(getAncho() + 10);
    }
}
