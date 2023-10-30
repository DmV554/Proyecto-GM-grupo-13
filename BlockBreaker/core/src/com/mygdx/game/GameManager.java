package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.*;

public class GameManager {
    private int vidas;
    private int puntaje;
    private int nivel;

    public GameManager() {
        this.vidas = 3;
        this.puntaje = 0;
        this.nivel = 1;
    }

    public int getVidas() {
        return vidas;
    }

    public void decrementarVida() {
        this.vidas--;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public int getNivel() {return this.nivel; }
    public boolean verificarGameOver() {
        if (vidas<=0) {
            vidas = 3;
            nivel = 1;
            return true;
        }
        return false;
    }

    public void aumentarNivel() {
        nivel++;
    }

    public void aumentarPuntaje(int aumento) {
        puntaje += aumento;
    }

    public boolean verificarTerminoNivel(ArrayList<Block> blocks) {
        if(blocks.isEmpty()) {
            return true;
        }
        return false;
    }
}