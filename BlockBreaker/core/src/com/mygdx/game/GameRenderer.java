package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameRenderer {
    private GameLogic gameLogic;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    public GameRenderer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(); // Considera cargar una fuente personalizada si es necesario
        shapeRenderer = new ShapeRenderer();
        setupCamera();
    }

    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); // Ajusta estos valores según tus necesidades
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Color de fondo (negro en este caso)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Renderizar objetos del juego
        renderGameObjects();

        // Renderizar UI/Texto
        renderUI();
    }

    private void renderGameObjects() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Dibujar Paddle
        Paddle paddle = gameLogic.getPaddle();
        shapeRenderer.setColor(paddle.getColor());
        shapeRenderer.rect(paddle.getX(), paddle.getY(), paddle.getAncho(), paddle.getAlto());

        // Dibujar PingBall
        PingBall ball = gameLogic.getBall();
        shapeRenderer.setColor(ball.getColor());
        shapeRenderer.circle(ball.getX(), ball.getY(), ball.getSize());

        // Dibujar Blocks
        for (Block block : gameLogic.getBlocks()) {
            shapeRenderer.setColor(block.getColor());
            shapeRenderer.rect(block.getX(), block.getY(), block.getAncho(), block.getAlto());
        }

        shapeRenderer.end();
    }

    private void renderUI() {
        spriteBatch.begin();
        // Ejemplo de dibujar texto
        font.draw(spriteBatch, "Puntuación: " + gameLogic.getStateManager().getPuntaje(), 10, 460); // Asegúrate de tener un método getScore en GameLogic
        font.draw(spriteBatch, "Vidas: " + gameLogic.getStateManager().getVidas(), 700, 460); // Asegúrate de tener un método getLives en GameLogic
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }
}
