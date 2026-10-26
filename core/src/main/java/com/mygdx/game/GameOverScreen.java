package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen, InputProcessor {
	private final GameLluviaMenu game;
	private MainMenuScreen menu;
	private Texture fondoGO;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Sound sonidoGameOver;
	private int puntajeFinal;
	
	private String nombreJugador = "";
	private boolean puntajeGuardado = false;
	
	public GameOverScreen(final GameLluviaMenu game, int puntaje, MainMenuScreen menu) {
		this.game = game;
		this.menu = menu;
        this.batch = game.getBatch();
        this.setFont(game.getFont());
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
		this.puntajeFinal = puntaje;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		batch.draw(fondoGO, 0, 0);
		game.getFont().draw(game.getBatch(), "GAME OVER", 100, 440);
		game.getFont().draw(game.getBatch(), "Puntaje final: " + puntajeFinal, 100, 410);
		if(!puntajeGuardado) {
			game.getFont().draw(game.getBatch(), "Escribe tu nombre: ", 150, 350);
            game.getFont().draw(game.getBatch(), "> " + nombreJugador + " <", 250, 300);
			game.getFont().draw(game.getBatch(), "Presiona ENTER para reiniciar", 100, 380);
		} else {
			game.getFont().draw(game.getBatch(), "Guardando...", 250, 300);
		}
		game.getBatch().end();
	}
	
	@Override 
	public boolean keyTyped(char character) {
		if(puntajeGuardado) return false;
		
		if(character =='\r'|| character == '\n') {
			if(nombreJugador.length() > 0 ) {
				guardarYSalir();
			}
			return true;
		}
		
		if (character == '\b' && nombreJugador.length() > 0) {
            nombreJugador = nombreJugador.substring(0, nombreJugador.length() - 1);
            return true;
        }

	    if (nombreJugador.length() < 10 && Character.isLetterOrDigit(character)) {
	        nombreJugador += Character.toUpperCase(character);
	        return true;
	    }
		return false;
	}
	
	private void guardarYSalir() {
		puntajeGuardado = true;
		AdministradorScore.getInstancia().registrarPuntaje(nombreJugador, puntajeFinal);
        AdministradorScore.getInstancia().actualizarHighScore(puntajeFinal);
        
        Gdx.input.setInputProcessor(null); 
        
        game.setScreen(menu);
        dispose();
	}
	
	@Override
	public void show() {
		this.fondoGO = new Texture("fondo_espacio_volt.jpg");
		this.sonidoGameOver = Gdx.audio.newSound(Gdx.files.internal("game_over_sound.wav"));
		sonidoGameOver.play();
		Gdx.input.setInputProcessor(this);
	}
	
	@Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
	
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {if(fondoGO != null) fondoGO.dispose(); if(sonidoGameOver != null) sonidoGameOver.dispose();}

	public BitmapFont getFont() {return font;}

	public void setFont(BitmapFont font) {this.font = font;}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
}
