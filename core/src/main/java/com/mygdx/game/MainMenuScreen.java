package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen {
	
	final GameLluviaMenu game;
	private Texture fondoMM;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Music  musicaFondo;

	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fondoMM, 0, 0);
		font.getData().setScale(2, 2);
		game.getFont().draw(game.getBatch(), "NAVE VS ASTEROIDES", 100, 440);
		game.getFont().draw(game.getBatch(), "Presiona ENTER para jugar", 100, 410);
		game.getFont().draw(game.getBatch(), "Presiona 1 para ver la ScoreBoard", 100, 380);
		game.getFont().draw(game.getBatch(), "Presiona 2 para ver los Creditos", 100, 350);

		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			game.setScreen(new GameScreen(game, this));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			game.setScreen(new ScoreBoard(game, this));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			game.setScreen(new Creditos(game, this));
			dispose();
		}
	}

	@Override
	public void show() {
		this.fondoMM = new Texture("fondo_espacio_volt.jpg");
		AdministradorAudio.getInstancia().reproducirMusicaMenu();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

	public Music getMusicaFondo() {
		return musicaFondo;
	}

	public void setMusicaFondo(Music musicaFondo) {
		this.musicaFondo = musicaFondo;
	}
	
	
}
