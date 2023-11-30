package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public interface PowerUp {
    void activate(GameLogic gameLogic);
    Color getColor();
}