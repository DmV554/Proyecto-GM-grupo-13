package com.mygdx.game;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class CollisionManager {

    public CollisionManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
    private final GameLogic gameLogic;
    public void checkCollisions(PingBall ball, Paddle paddle, ArrayList<Block> bloques) {
        checkBallPaddleCollision(ball, paddle);
        checkBallBlocksCollision(ball, bloques);
    }

    private void checkBallPaddleCollision(PingBall ball, Paddle paddle) {
        if (ball.getBoundingRectangle().overlaps(paddle.getBoundingRectangle())) {
            ball.cambiarDireccion();
        }
    }

    public void checkPowerUpCollisions(Paddle paddle, ArrayList<FallingPowerUp> powerUps) {
        Iterator<FallingPowerUp> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            FallingPowerUp powerUp = iterator.next();
            if (paddle.getBoundingRectangle().overlaps(powerUp.getBoundingRectangle())) {
                powerUp.activatePowerUp(gameLogic);
                iterator.remove();
                break;
            }
        }
    }



    private void checkBallBlocksCollision(PingBall ball, ArrayList<Block> bloques) {
        for (Block bloque : bloques) {
            if (ball.getBoundingRectangle().overlaps(bloque.getBoundingRectangle())) {
                if(!bloque.getDestroyed()) {
                    bloque.hit();
                    ball.cambiarDireccion();
                }

                break;
            }
        }
    }
}

