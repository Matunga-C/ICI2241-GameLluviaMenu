package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Nave nave;
	private Asteroide asteroide;
	
	private Texture laserTex;
	private Texture fondo;
	private Sound sonidoLaser;
	private Sound sonidoExplosion;
	private Music  musicaFondo;
	Array<Rectangle> laseres;
	
	private Array<CaidaLibre> objetosCayendo;
    private float tiempoParaNuevoObjeto = 0;
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
		this.batch = game.getBatch();
        this.font = game.getFont();
        objetosCayendo = new Array<>();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void show() {
		nave = new Nave(270f, 9f);
		laserTex = new Texture("laser.png");
		fondo = new Texture("fondo_espacio.jpg");
        laseres = new Array<>();
        asteroide = new Asteroide(270f, 720f);
        sonidoLaser = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("Music_PesoEstable.mp3"));
        musicaFondo.setLooping(true);
        musicaFondo.play();
	}
	
	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update(); 
		
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(fondo, 0, 0);
		//dibujar textos
		font.draw(batch, "Puntos totales: " + nave.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + nave.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		
		nave.mover();
		nave.dibujar(batch);
		
		tiempoParaNuevoObjeto -= delta;
		if(tiempoParaNuevoObjeto <= 0) {
			float randomX = MathUtils.random(0, Gdx.graphics.getWidth() - 100);
			
			objetosCayendo.add(new Asteroide(randomX, Gdx.graphics.getHeight()));
			
			tiempoParaNuevoObjeto = 1.5f;
		}
		
		for(int i = objetosCayendo.size - 1; i >= 0; i--) {
			CaidaLibre obj = objetosCayendo.get(i);
			
			obj.caer(200);
			obj.dibujar(batch);
			
			if(obj.getHitbox().overlaps(nave.getHitbox())) {
				nave.restarVida();
				asteroide.setY(720);
        		asteroide.setX(MathUtils.random(0, 980));
	        	
	        	if(nave.getVidas() <= 0) {
	        		musicaFondo.stop();
	        		//sonidoGameOver.play();
	        		game.setScreen(new GameOverScreen(game, nave.getPuntos()));
	        		dispose();
	        	}
				
				objetosCayendo.removeIndex(i);
			} else if(obj.getHitbox().y < -100) {
				objetosCayendo.removeIndex(i);
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        	Rectangle nuevoLaser = new Rectangle();
        	sonidoLaser.play();
        	nuevoLaser.x = nave.getX() + 35;
        	nuevoLaser.y = nave.getY() + 100;
        	laseres.add(nuevoLaser);
        }
		
		for(int i = laseres.size - 1; i >= 0; i--) {
        	Rectangle laser = laseres.get(i);
        	
        	laser.y += 480 * Gdx.graphics.getDeltaTime();
        	
        	boolean laserEliminado = false;
        	
        	if(laser.y > 720) {
        		laseres.removeIndex(i);
        		continue; 
        	}
        	
        	
        	for(int j = objetosCayendo.size - 1; j >= 0; j--) {
        		CaidaLibre obj = objetosCayendo.get(j);
        		
        		if(laser.overlaps(obj.getHitbox())) {
        			sonidoExplosion.play();
        			laseres.removeIndex(i);      
        			objetosCayendo.removeIndex(j); 
        			nave.sumarPuntos();
        			
        			laserEliminado = true;
        			break; 
        		}
        	}
        	
        	if(laserEliminado) {
        		continue; 
        	}
        }
		
		for(Rectangle laser : laseres) {
        	game.getBatch().draw(laserTex, laser.x, laser.y, 30, 60);
        }
        
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      nave.dispose();
      asteroide.dispose();
      laserTex.dispose();
	}

}
