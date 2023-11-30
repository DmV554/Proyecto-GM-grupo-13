package com.mygdx.game;
import com.badlogic.gdx.Game;

public class BlockBreakerGame extends Game {
    @Override
    public void create() {
        this.setScreen(MenuScreen.getInstance()); // Iniciar con el menú
    }

    // Otros métodos como render() y dispose() no necesitan modificarse

}