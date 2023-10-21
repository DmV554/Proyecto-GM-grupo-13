package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends GameObject {
    boolean destroyed;
    
    public Block(int x, int y, int ancho, int alto, Color color) {
        super(x,y,ancho,alto, color);
        destroyed = false;
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(color);
        shape.rect(x, y, ancho, alto);
    }

    public void update() {
    };
}
