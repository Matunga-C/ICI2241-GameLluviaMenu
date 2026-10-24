package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private MainMenuScreen menu;
	private Texture fondoGO;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Sound sonidoGameOver;
	private int puntajeFinal;

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
		sonidoGameOver.play();
		batch.draw(fondoGO, 0, 0);
		game.getFont().draw(game.getBatch(), "GAME OVER", 100, 440);
		game.getFont().draw(game.getBatch(), "Puntaje final: " + puntajeFinal, 100, 410);
		game.getFont().draw(game.getBatch(), "Presiona ENTER para reiniciar", 100, 380);
		game.getBatch().end();

		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			sonidoGameOver.stop();
			game.setScreen(new GameScreen(game, menu));
			AdministradorJuego.getInstancia().reproducirMusicaMenu();
			dispose();
		}
	}

	@Override
	public void show() {
		this.fondoGO = new Texture("fondo_espacio_volt.jpg");
		this.sonidoGameOver = Gdx.audio.newSound(Gdx.files.internal("game_over_sound.wav"));
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

}
