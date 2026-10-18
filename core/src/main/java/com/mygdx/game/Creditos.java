package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Creditos implements Screen{
	private final GameLluviaMenu game;
	private MainMenuScreen menuPrincipal;
	private Texture fondoCreditos;
	private BitmapFont font;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Creditos(final GameLluviaMenu game ,MainMenuScreen menu) {
		this.game = game;
		this.menuPrincipal = menu;
		this.font = game.getFont();
		this.batch = game.getBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 700);
	}	
	
	@Override
	public void show() {
		this.fondoCreditos = new Texture("fondo_espacio_volt.jpg");
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);
		batch.begin();
		batch.draw(fondoCreditos, 0, 0);
		font.draw(batch, "Juego desarrollado y creado por:", 500, 440);
		font.draw(batch, "-Juan Mercade.", 500, 415);
		font.draw(batch, "-Leonardo Jorquera", 500, 390);
		font.draw(batch, "-Pablo Alvarez", 500, 365);
		font.draw(batch, "Programacion Avanzada", 500, 340);
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
		fondoCreditos.dispose();
	}
	
	public Texture getFondoCreditos() {return fondoCreditos;}
	public BitmapFont getFont() {return font;}
	public SpriteBatch getBatch() {return batch;}
	
	
	public void setFondoCreditos(Texture fondoCreditos) {this.fondoCreditos = fondoCreditos;}
	public void setFont(BitmapFont font) {this.font = font;}
	public void setBatch(SpriteBatch batch) {this.batch = batch;}	
}
