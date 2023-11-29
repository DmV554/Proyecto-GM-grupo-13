package com.mygdx.game;

import java.util.ArrayList;

public class CollisionManager {
    private ArrayList<Collidable> collidables;

    public CollisionManager() {
        collidables = new ArrayList<>();
    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public void removeCollidable(Collidable collidable) {
        collidables.remove(collidable);
    }

    public void checkCollisions() {
        for (Collidable collidableA : collidables) {
            for (Collidable collidableB : collidables) {
                if (collidableA != collidableB && collidableA.collidesWith(collidableB)) {
                    handleCollision(collidableA, collidableB);
                }
            }
        }
    }

    private void handleCollision(Collidable collidableA, Collidable collidableB) {
        if (collidableA instanceof Block && collidableB instanceof PingBall) {
            Block bloque = (Block) collidableA;
            PingBall bola = (PingBall) collidableB;
            if(!bloque.getDestroyed()) {
                bloque.hit();
                bola.cambiarDireccion();
            }

        }

        if (collidableA instanceof Paddle && collidableB instanceof PingBall) {
            Paddle pad = (Paddle) collidableA;
            PingBall bola = (PingBall) collidableB;

            bola.cambiarDireccion();
        }

    }
}

