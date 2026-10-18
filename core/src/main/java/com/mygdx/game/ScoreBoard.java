package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class ScoreBoard implements Screen{
	private final GameLluviaMenu game;
	private MainMenuScreen menuPrincipal;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture fondoSB;
	private OrthographicCamera camera;
	
	private ArrayList<Usuario> Users;
	
	public ScoreBoard(final GameLluviaMenu game ,MainMenuScreen menu) {
		this.game = game;
		this.menuPrincipal = menu;
		this.font = game.getFont();
		this.batch = game.getBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
	}	

	@Override
	public void show() {
		this.fondoSB = new Texture("fondo_espacio_volt.jpg");
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);
		batch.begin();
		batch.draw(fondoSB, 0, 0);
		font.draw(batch, "Tabla de Puntaje y Tiempo", 500, 650);
		
		font.draw(batch, "Preciona ESC para volver al Menu Principal", 5, 25);
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			game.setScreen(menuPrincipal);
			dispose();
		}
		batch.end();
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
	public void dispose() {
		fondoSB.dispose();
	}
	public BitmapFont getFont() {return font;}
	public SpriteBatch getBatch() {return batch;}
	public Texture getFondoSB() {return fondoSB;}
	
	
	public void setFont(BitmapFont font) {this.font = font;}
	public void setBatch(SpriteBatch batch) {this.batch = batch;}
	public void setFondoSB(Texture fondoSB) {this.fondoSB = fondoSB;}
	
}
