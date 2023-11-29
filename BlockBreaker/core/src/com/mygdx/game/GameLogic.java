package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private PingBall ball;
    private Paddle paddle;
    private ArrayList<Block> blocks = new ArrayList<>();
    private CollisionManager collisionManager;
    private GameManager stateManager;
    private static final int widthPaddle = 140;
    private static final int heightPaddle = 15;
    private static final int sizeBola = 12;
    private static final int widthBloque = 70;
    private static final int heightBloque = 26;
    private static final int xSpeed = 200;
    private static final int ySpeed = 200;


    private static final float chance_de_bloque_mejorado = 0.1f;

    public GameLogic() {
        //System.out.println(String.valueOf(screenHeight) + ", " + String.valueOf(screenWidth));
        this.collisionManager = new CollisionManager();
        this.stateManager = new GameManager();
        initializeGame();
    }

    private void initializeGame() {
        ball = new PingBall((float) Gdx.graphics.getWidth() /2-10, 41, sizeBola, xSpeed, ySpeed, true, Color.WHITE); // Parámetros adecuados
        paddle = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10, Color.BLUE); // Parámetros adecuados
        createBlocks();
    }

    private void createBlocks() {
        int filas = 2 + stateManager.getNivel();
        blocks.clear();
        int y = Gdx.graphics.getHeight();
        Random random = new Random();

        for (int cont = 0; cont < filas; cont++ ) {
            y -= heightBloque + 10;
            for (int x = 5; x < Gdx.graphics.getWidth(); x += widthBloque + 10) {
                int hitPoints = random.nextFloat() < chance_de_bloque_mejorado ? 2 : 1;
                Color color = hitPoints == 2 ? Color.RED : new Color(0.1f + random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                Block bloqueN = new Block(x, y, widthBloque, heightBloque, color, hitPoints);
                blocks.add(bloqueN);
            }
        }
    }

    public void update() {
        if (!stateManager.verificarGameOver()) {
            ball.update();
            paddle.update();
            updateBlocks();
            collisionManager.checkCollisions(ball, paddle, blocks);
            checkGameConditions();
        } else {
            createBlocks();
        }
    }

    private void updateBlocks() {
        ArrayList<Block> blocksToRemove = new ArrayList<>();

        for (Block block : blocks) {
            if (block.getDestroyed()) {
                stateManager.aumentarPuntaje(1);
                blocksToRemove.add(block);
            }
        }

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
            ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, sizeBola, xSpeed, ySpeed, true, Color.WHITE);
        }

        if (stateManager.verificarTerminoNivel(blocks)) {
            stateManager.aumentarNivel();
            createBlocks();
            ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, sizeBola, xSpeed, ySpeed, true, Color.WHITE);
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

