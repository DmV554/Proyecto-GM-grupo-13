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
    private static final int xSpeed = 290;
    private static final int ySpeed = 290;

    private final ArrayList<FallingPowerUp> fallingPowerUps = new ArrayList<>();

    private static final float chance_de_bloque_mejorado = 0.1f;

    public GameLogic() {
        //System.out.println(String.valueOf(screenHeight) + ", " + String.valueOf(screenWidth));
        this.stateManager = new GameManager();
        this.collisionManager = new CollisionManager(this);
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
                Block bloqueN = new Block(x, y, widthBloque, heightBloque, color, hitPoints, this); // 'this' se refiere a GameLogic
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
        for (FallingPowerUp powerUp : fallingPowerUps) {
            powerUp.update();
        }
        collisionManager.checkPowerUpCollisions(paddle, fallingPowerUps);
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
            handleBallLost();
        }

        if (stateManager.verificarTerminoNivel(blocks)) {
            stateManager.aumentarNivel();
            createBlocks();
            ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, sizeBola, xSpeed, ySpeed, true, Color.WHITE);
        }
    }


    private void handleBallLost() {
        stateManager.decrementarVida();
        ball = new PingBall(paddle.getX() + paddle.getAncho() / 2 - 5, paddle.getY() + paddle.getAlto() + 11, sizeBola, xSpeed, ySpeed, true, Color.WHITE);
        for (FallingPowerUp powerUp : fallingPowerUps) {
            powerUp.getPowerUp().deactivate(this);
        }
        fallingPowerUps.clear();
    }
    public void generatePowerUp(float x, float y) {
        Random rand = new Random();
        float chance = rand.nextFloat();
        PowerUp newPowerUp;
        if (chance < 0.20f) {
            newPowerUp = new SlowDownPowerUp();
        } else if (chance < 0.40f) {
            newPowerUp = new SpeedUpPowerUp();
        } else if (chance < 0.60f) {
            newPowerUp = new ExpandPaddlePowerUp();
        } else {
            newPowerUp = new ExtraLifePowerUp();
        }
        FallingPowerUp fallingPowerUp = new FallingPowerUp(x, y, newPowerUp);
        fallingPowerUps.add(fallingPowerUp);
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

    public FallingPowerUp[] getFallingPowerUps() {
        return fallingPowerUps.toArray(new FallingPowerUp[0]);
    }
}

