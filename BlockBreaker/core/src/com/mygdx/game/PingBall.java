package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends GameObject {
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private boolean estaQuieto;
	    
	    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto, Color color) {
			super(x,y, color);
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }

	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, size);
	    }
	    
	    public void update() {
	    	if (estaQuieto) return;
	        x += xSpeed;
	        y += ySpeed;
	        if (x-size < 0 || x+size > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }
	        if (y+size > Gdx.graphics.getHeight()) {
	            ySpeed = -ySpeed;
	        }
	    }
	    
	    public void checkCollision(Paddle paddle) {
	        if(collidesWith(paddle)){
	            color = Color.GREEN;
	            ySpeed = -ySpeed;
	        }
	        else{
	            color = Color.WHITE;
	        }
	    }
	    private boolean collidesWith(Paddle pp) {
	    	boolean intersectaX = (pp.getX() + pp.getWidth() >= x-size) && (pp.getX() <= x+size);
	        boolean intersectaY = (pp.getY() + pp.getHeight() >= y-size) && (pp.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	    public void checkCollision(Block block) {
	        if(collidesWith(block)){
	            ySpeed = - ySpeed;
	            block.destroyed = true;
	        }
	    }
	    private boolean collidesWith(Block bb) {
	    	boolean intersectaX = (bb.x + bb.ancho >= x-size) && (bb.x <= x+size);
	        boolean intersectaY = (bb.y + bb.alto >= y-size) && (bb.y <= y+size);
	    	return intersectaX && intersectaY;
	    }
	    
	}
