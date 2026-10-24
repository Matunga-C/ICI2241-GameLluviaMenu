package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class PausaScreen implements Screen {

	private final GameLluviaMenu game;
	private MainMenuScreen menuPrincipal;
	private GameScreen juego;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;

	public PausaScreen (final GameLluviaMenu game, GameScreen juego, MainMenuScreen menu) {
		this.game = game;
		this.menuPrincipal = menu;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			game.setScreen(menuPrincipal);
			dispose();
		}
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(juego.getFondo1(), 0, 0);
		font.draw(batch, "Juego en Pausa ", 80, 400);
		font.draw(batch, "Toca ENTER para continuar !!!", 80, 370);
		font.draw(batch, "Toca ESC para volver al Menu Principal", 80, 340);
		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			game.setScreen(juego);
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

}

