package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.*;

public class PingBall extends GameObject{
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private boolean estaQuieto;
	    
	    public PingBall(float x, float y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto, Color color) {
			super(x,y, color);
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }

		public int getSize() {
			return size;
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

		@Override
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

		public void checkCollisionPad(Paddle pad) {
			if(pad.collidesWith(this)) {
				color = Color.GREEN;
				ySpeed = -ySpeed;
			}
			else{
				color = Color.WHITE;
			}
		}

		public void checkCollisionBlock(Block b) {
				if (b.collidesWith(this)) {
					b.setDestroyed(true);
					ySpeed = - ySpeed;
				}
		}
}
