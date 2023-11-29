package com.mygdx.game;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
public class CollisionManager {

    public void checkCollisions(PingBall ball, Paddle paddle, ArrayList<Block> bloques) {
        checkBallPaddleCollision(ball, paddle);
        checkBallBlocksCollision(ball, bloques);
    }

    private void checkBallPaddleCollision(PingBall ball, Paddle paddle) {
        if (ball.getBoundingRectangle().overlaps(paddle.getBoundingRectangle())) {
            ball.cambiarDireccion();
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

