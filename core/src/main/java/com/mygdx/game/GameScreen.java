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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
	private MainMenuScreen menu;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Nave nave;
	private Asteroide asteroide;
	
	private Texture fondo1;
	private Texture fondo2;
	private Sound sonidoExplosion;
	private Sound sonidoGameOver;
	
	
	private float yFondo1;
	private float yFondo2;
	private float velocidadFondo = 150f;
	private String mensajePantalla = "";
	private float tiempoMensaje = 0f;
	Array<Bala> laseres;
	
	private Array<CaidaLibre> objetosCayendo;
    private float tiempoParaNuevoObjeto = 0;
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game, MainMenuScreen menu) {
		this.game = game;
		this.menu = menu;
		this.batch = game.getBatch();
        this.font = game.getFont();
        objetosCayendo = new Array<>();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
	}
	
	@Override
	public void show() {
		nave = new Nave(270f, 9f);
		fondo1 = new Texture("fondo_espacial_test.jpg");
		fondo2 = new Texture("fondo_espacial_3_test.jpg");
		yFondo1 = 0;
		yFondo2 = 700;
        laseres = new Array<>();
        asteroide = new Asteroide(270f, 720f);
        sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        sonidoGameOver = Gdx.audio.newSound(Gdx.files.internal("game_over_sound.wav"));
        
        
	}
	
	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update(); 
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			menu.getMusicaFondo().pause();
			game.setScreen(new PausaScreen(game, this, menu));
		}
		
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		yFondo1 -= velocidadFondo * delta;
		yFondo2 -= velocidadFondo * delta;
		
		if(yFondo1 + 700 <= 0) {
			yFondo1 = yFondo2 + 700;
		}
		if(yFondo2 + 700 <= 0) {
			yFondo2 = yFondo1 + 700;
		}
		
		batch.draw(fondo1, 0, yFondo1, 600, 700);
		batch.draw(fondo2, 0, yFondo2, 600, 700);
		
		//dibujar textos
		font.draw(batch, "Puntos totales: " + nave.getPuntos(), 170, 680);
		font.draw(batch, "Vidas : " + nave.getVidas(), 480, 680);
		font.draw(batch, "HighScore : " + game.getHigherScore(), 5, 25);
		font.draw(batch, "P (Pausa)", 5, 680);
		
		
		nave.mover();
		nave.dibujar(batch);
		
		if(tiempoMensaje > 0) {
			font.draw(batch, mensajePantalla, 100, 400);
			tiempoMensaje -= delta;
		}
		
		tiempoParaNuevoObjeto -= delta;
		
		if(tiempoParaNuevoObjeto <= 0) {
			
			float randomX = MathUtils.random(0, Gdx.graphics.getWidth() - 100);
			
			float dado = MathUtils.random(1f, 100f);
			
			if(dado <= 95) {
				objetosCayendo.add(new Asteroide(randomX, Gdx.graphics.getHeight()));
			} else if(dado <= 97.5f) {
				objetosCayendo.add(new Llave(randomX, Gdx.graphics.getHeight()));
			} else {
				Texture texPowerUp = new Texture("nave_espacial.png");
				objetosCayendo.add(new Invulnerabilidad(randomX, Gdx.graphics.getHeight(), texPowerUp));
			}
			
			tiempoParaNuevoObjeto = 0.5f;
		}
		
		for(int i = objetosCayendo.size - 1; i >= 0; i--) {
			CaidaLibre obj = objetosCayendo.get(i);
			
			obj.caer(400);
			obj.dibujar(batch);
			
			if(obj.getHitbox().overlaps(nave.getHitbox())) {
				if(obj instanceof Asteroide) {
					nave.restarVida();
					asteroide.setY(720);
	        		asteroide.setX(MathUtils.random(0, 300));
		        	
		        	if(nave.getVidas() <= 0) {
		        		if(game.getHigherScore() <= nave.getPuntos()) {
		        			game.setHigherScore(nave.getPuntos());
		        		}
		        		menu.getMusicaFondo().stop();
		        		sonidoGameOver.play();
		        		game.setScreen(new GameOverScreen(game, nave.getPuntos(), menu));
		        		dispose();
		        	}
				} else if(obj instanceof PowerUp) {
					PowerUp powerUp = (PowerUp)obj;
					powerUp.recoger(nave);
					mensajePantalla = powerUp.getTexto();
					tiempoMensaje = 2.0f;
					
				} else if(obj instanceof Llave) {
					nave.sumarVida();
				}
				
				objetosCayendo.removeIndex(i);
			} else if(obj.getHitbox().y < -100) {
				objetosCayendo.removeIndex(i);
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        	Laser nuevoLaser = new Laser(100, nave.getX() + 15, nave.getY() + 50);
        	nuevoLaser.getSonidoDisparo().play();
        	laseres.add(nuevoLaser);
        }
		
		for(int i = laseres.size - 1; i >= 0; i--) {
        	Bala laserActual = laseres.get(i);
        	
        	laserActual.actualizar(Gdx.graphics.getDeltaTime());
        	laserActual.dibujar(batch);
        	
        	
        	boolean laserEliminado = false;
        	
        	if(laserActual.getY() > 720) {
        		laseres.removeIndex(i);
        		continue; 
        	}
        	
        	
        	for(int j = objetosCayendo.size - 1; j >= 0; j--) {
        		CaidaLibre obj = objetosCayendo.get(j);
        		
        		if(laserActual.getHitbox().overlaps(obj.getHitbox())) {
        			if(obj instanceof Asteroide) {
	        			sonidoExplosion.play();
	        			laseres.removeIndex(i);      
	        			objetosCayendo.removeIndex(j); 
	        			nave.sumarPuntos();
	        			
	        			laserEliminado = true;
	        			break;
        			}	
        		}
        	}
        	
        	if(laserEliminado) {
        		continue; 
        	}
        }
		
		for(Bala laser : laseres) {
        	game.getBatch().draw(laser.getTextura(), laser.getX(), laser.getY(), 30, 60);
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
		game.setScreen(new PausaScreen(game, this, menu)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      nave.dispose();
      asteroide.dispose();
	}
	
	public Texture getFondo1() {
		return fondo1;
	}
	public Texture getFondo2() {
		return fondo2;
	}
	
}
