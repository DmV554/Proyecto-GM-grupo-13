package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends GameObject implements Collidable {
    /*private int x = 20;
    private int y = 20;
    private int width = 100;
    private int height = 10;*/
    
    public Paddle(int x, int y, int ancho, int alto, Color color) {
    	super(x,y,ancho,alto, color);
    }

    public boolean collidesWith(Collidable other) {
        if (other instanceof PingBall) {
            PingBall ball = (PingBall) other;

            boolean intersectaX = (x + ancho >= ball.getX() - ball.getSize()) && (x <= ball.getX() + ball.getSize());
            boolean intersectaY = (y + alto >= ball.getY() - ball.getSize()) && (y <= ball.getY() + ball.getSize());
            return intersectaX && intersectaY;
        }

        return false;
    }

    public void update() {
        float x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =x-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=x+15;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2+ancho < Gdx.graphics.getWidth()) {
            x = x2;
        }
    }

    @Override
    public void draw(ShapeRenderer shape) {

    }

}
