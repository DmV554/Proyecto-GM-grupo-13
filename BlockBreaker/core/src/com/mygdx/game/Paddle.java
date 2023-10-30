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



    public boolean collidesWith(PingBall pp) {
        boolean intersectaX = (x + ancho >= pp.getX()-pp.getSize()) && (x <= pp.getX()+pp.getSize());
        boolean intersectaY = (y + alto >= pp.getY()-pp.getSize()) && (y <= pp.getY()+pp.getSize());
        return intersectaX && intersectaY;
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        float x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =x-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=x+15;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2+ancho < Gdx.graphics.getWidth()) {
            x = x2;
        }
        shape.rect(x, y, ancho, alto);
    }

    public void update() {

    }
    
}
