package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	Color pingBallColor = new Color();
	GameManager manager;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3, 2);

		manager = new GameManager();
			
		shape = new ShapeRenderer();
		ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true, pingBallColor.WHITE);
		pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10, Color.BLUE);
		crearBloques();
	}

	public void crearBloques() {
		int filas = 2 + manager.getNivel();
		blocks.clear();
		int blockWidth = 70;
		int blockHeight = 26;
		int y = Gdx.graphics.getHeight();

		for (int cont = 0; cont<filas; cont++ ) {
			y -= blockHeight+10;
			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				Random r = new Random(x+y);
				Block bloqueN = new Block(x, y, blockWidth, blockHeight, new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10));
				blocks.add(bloqueN);
			}
		}
	}

	public void dibujaTextos() {
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Puntos: " + manager.getPuntaje(), 10, 25);font.draw(batch, "Vidas : " + manager.getVidas(), Gdx.graphics.getWidth()-20, 25);
		batch.end();
	}
		
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		pad.draw(shape);
		// monitorear inicio del juego
		if (ball.estaQuieto()) {
			ball.setXY(pad.getX()+pad.getAncho()/2-5, pad.getY()+pad.getAlto()+11);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
		}else {
			ball.update();
		}

		//verificar si se fue la bola x abajo
		if (ball.getY()<0) {
			manager.decrementarVida();
			ball = new PingBall(pad.getX()+pad.getAncho()/2-5, pad.getY()+pad.getAlto()+11, 10, 5, 7, true, pingBallColor.WHITE);
		}

		if(manager.verificarGameOver()) {
			crearBloques();
		}

		if(manager.verificarTerminoNivel(blocks)) {
			manager.aumentarNivel();
			crearBloques();
			ball = new PingBall(pad.getX()+pad.getAncho()/2-5, pad.getY()+pad.getAlto()+11, 10, 5, 7, true,pingBallColor.WHITE);
		}

		dibujarTodosLosBloques();
		actualizarBloques();
	        
		ball.checkCollisionPad(pad);
		ball.draw(shape);
	        
		shape.end();
		dibujaTextos();
	}

	public void actualizarBloques() {
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b.getDestroyed()) {
				manager.aumentarPuntaje(1);
				blocks.remove(b);
				i--; //para no saltarse 1 tras eliminar del arraylist
			}
		}
	}

	public void dibujarTodosLosBloques() {
		for (Block b : blocks) {
			b.draw(shape);
			ball.checkCollisionBlock(b);
		}
	}
		
	@Override
	public void dispose () {

	}
}
