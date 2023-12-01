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
		shape.setColor(getColor());
		shape.circle(getX(), getY(), size);
	}

	public Rectangle getBoundingRectangle() {
		return new Rectangle(getX() - size / 2, getY() - size / 2, size, size);
	}

	@Override
	public void update() {
		if (estaQuieto) return;

		float deltaTime = Gdx.graphics.getDeltaTime();

		// Multiplica la velocidad por el tiempo delta
		setX(getX() + xSpeed * deltaTime);
		setY(getY() + ySpeed * deltaTime);

		// Comprobaciones de límites y cambio de dirección si es necesario
		if (getX() - size < 0 || getX() + size > Gdx.graphics.getWidth()) {
			xSpeed = -xSpeed;
		}
		if (getY() + size > Gdx.graphics.getHeight()) {
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
		setX(getX()+ (float) (xSpeed * 1.2));
		setY(getY() + (float) (ySpeed * 1.2));
	}
}
