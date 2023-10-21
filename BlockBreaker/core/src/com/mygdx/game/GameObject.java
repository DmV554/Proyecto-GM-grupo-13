package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameObject {
    protected float x, y; // Posición
    protected float ancho, alto; // Dimensiones
    protected Color color; // Color del objeto

    // Constructor
    public GameObject(float x, float y, float ancho, float alto, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
    }

    public GameObject(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void update(); // Actualizar el estado del objeto
    public abstract void draw(ShapeRenderer shape); // Renderizar el objeto

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public float getAncho() { return ancho; }
    public void setAncho(float width) { this.ancho = width; }
    public float getAlto() { return alto; }
    public void setAlto(float height) { this.alto = height; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
