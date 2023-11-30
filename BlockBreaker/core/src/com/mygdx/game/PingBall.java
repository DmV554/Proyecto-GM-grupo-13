package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PingBall extends GameObject implements Collidable{
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

	public Rectangle getBoundingRectangle() {
		return new Rectangle(x - size / 2, y - size / 2, size, size);
	}

		@Override
		public void update() {
			if (estaQuieto) return;

			float deltaTime = Gdx.graphics.getDeltaTime();

			// Multiplica la velocidad por el tiempo delta
			x += xSpeed * deltaTime;
			y += ySpeed * deltaTime;

			// Comprobaciones de límites y cambio de dirección si es necesario
			if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
				xSpeed = -xSpeed;
			}
			if (y + size > Gdx.graphics.getHeight()) {
				cambiarDireccion();
			}
		}

	public void cambiarDireccion() {
		ySpeed = - ySpeed;
	}

	@Override
	public boolean collidesWith(Collidable x) {
		return false;
	}

	public void increaseSpeed() {
		x += (float) (xSpeed * 1.2);
		y += (float) (ySpeed * 1.2);
	}
}
