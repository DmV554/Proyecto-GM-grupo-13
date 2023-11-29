package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private PingBall ball;
    private Paddle paddle;
    private ArrayList<Block> blocks = new ArrayList<>();
    private CollisionManager collisionManager;
    private GameManager stateManager;
    private int screenWidth, screenHeight;
    private static final float chance_de_bloque_mejorado = 0.1f;

    public GameLogic(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.collisionManager = new CollisionManager();
        this.stateManager = new GameManager();
        initializeGame();
    }

    private void initializeGame() {
        // Inicialización de objetos del juego
        // Puedes utilizar un Builder si la creación es compleja
        ball = new PingBall((float) Gdx.graphics.getWidth() /2-10, 41, 11, 5, 7, true, Color.WHITE); // Parámetros adecuados
        paddle = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10, Color.BLUE); // Parámetros adecuados
        initializeBlocks();

        collisionManager.addCollidable(paddle);
        collisionManager.addCollidable(ball);
    }

    private void initializeBlocks() {
        int filas = 2 + stateManager.getNivel();
        blocks.clear();
        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();
        Random random = new Random();

        for (int cont = 0; cont < filas; cont++ ) {
            y -= blockHeight + 10;
            for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                int hitPoints = random.nextFloat() < chance_de_bloque_mejorado ? 2 : 1;
                Color color = hitPoints == 2 ? Color.RED : new Color(0.1f + random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                Block bloqueN = new Block(x, y, blockWidth, blockHeight, color, hitPoints);
                blocks.add(bloqueN);
                collisionManager.addCollidable(bloqueN);
            }
        }
    }


    public void update() {
        // Verifica y actualiza el estado del juego
        if (!stateManager.verificarGameOver()) {
            ball.update();
            paddle.update();
            updateBlocks();
            collisionManager.checkCollisions();
            checkGameConditions();
        } else {
            initializeBlocks();
        }
    }

    private void updateBlocks() {
        ArrayList<Block> blocksToRemove = new ArrayList<>();

        for (Block block : blocks) {
            if (block.getDestroyed()) {
                stateManager.aumentarPuntaje(1);
                blocksToRemove.add(block);
                collisionManager.removeCollidable(block);
            }
        }

        // Elimina los bloques marcados para eliminación
        blocks.removeAll(blocksToRemove);

    }

    private void checkGameConditions() {
        if (ball.estaQuieto()) {
            ball.setXY(paddle.getX()+paddle.getAncho()/2-5, paddle.getY()+paddle.getAlto()+11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        }else {
            ball.update();
        }

        if (ball.getY() < 0) {
            stateManager.decrementarVida();
            ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, 10, 5, 7, true, Color.WHITE);
            collisionManager.addCollidable(ball);
        }

        if (stateManager.verificarTerminoNivel(blocks)) {
            stateManager.aumentarNivel();
            initializeBlocks();
            ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, 10, 5, 7, true, Color.WHITE);
            collisionManager.addCollidable(ball);

        }




    }

    public PingBall getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    public GameManager getStateManager(){
        return stateManager;
    }

}

