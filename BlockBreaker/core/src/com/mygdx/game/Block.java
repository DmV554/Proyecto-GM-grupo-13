package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import com.badlogic.gdx.graphics.Color;

public class Block extends GameObject implements Collidable {
    private boolean destroyed;
    
    public Block(int x, int y, int ancho, int alto, Color color) {
        super(x,y,ancho,alto, color);
        destroyed = false;
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(color);
        shape.rect(x, y, ancho, alto);
    }

    public boolean getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean collidesWith(PingBall pp) {
        boolean intersectaX = (x + ancho >= pp.getX()-pp.getSize()) && (x <= pp.getX()+pp.getSize());
        boolean intersectaY = (y + alto >= pp.getY()-pp.getSize()) && (y <= pp.getY()+pp.getSize());
        return intersectaX && intersectaY;
    }

    @Override
    public void update() {
    }
}
