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

public class ScoreBoard implements Screen {
	private final GameLluviaMenu game;
	private MainMenuScreen menuPrincipal;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture fondoSB;
	private OrthographicCamera camera;
	
	public ScoreBoard(final GameLluviaMenu game, MainMenuScreen menu) {
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
		
		AdministradorScore.getInstancia().getTablaPuntuaciones().clear();
		AdministradorScore.getInstancia().cargarTablaCSV();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(fondoSB, 0, 0);
		
		font.draw(batch, "TOP 8 - MEJORES PILOTOS", 100, 650);
		
		ArrayList<Usuario> tabla = AdministradorScore.getInstancia().getTablaPuntuaciones();
		
		int yInicial = 570; 
		
		if(tabla.isEmpty()) {
			font.draw(batch, "Aún no hay puntajes registrados.", 80, 400);
		} else {
			
			font.draw(batch, "POS", 100, yInicial);
			font.draw(batch, "NOMBRE", 200, yInicial);
			font.draw(batch, "PUNTAJE", 400, yInicial);
			
			yInicial -= 40; 
			
			for (int i = 0; i < tabla.size(); i++) {
				Usuario u = tabla.get(i);
				
				font.draw(batch, (i + 1) + ".", 100, yInicial);           
				font.draw(batch, u.getNombre(), 200, yInicial);           
				font.draw(batch, String.valueOf(u.getScore()), 400, yInicial); 
				
				yInicial -= 30; 
			}
		}
		
		font.draw(batch, "Presiona ESC para volver al Menu Principal", 5, 25);
		
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
		if (fondoSB != null) {
			fondoSB.dispose();
		}
	}
	
	public BitmapFont getFont() {return font;}
	public SpriteBatch getBatch() {return batch;}
	public Texture getFondoSB() {return fondoSB;}
	
	public void setFont(BitmapFont font) {this.font = font;}
	public void setBatch(SpriteBatch batch) {this.batch = batch;}
	public void setFondoSB(Texture fondoSB) {this.fondoSB = fondoSB;}
}