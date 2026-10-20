package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Zumarah extends Jefe{
	
	public Zumarah(float x, float y, int vida, int danio, float ta) {
		super(x, y, vida, danio, ta);
		this.hitbox = new Rectangle(x, y, 150, 150);
		this.musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("zumarah_song.mp3"));
		this.sonidoAtaque = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		this.sonidoSpawn = Gdx.audio.newSound(Gdx.files.internal("zumarah_spawn.wav"));
		this.textura = new Texture("zumarah.png");
	}
	@Override 
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, hitbox.x, hitbox.y, 150, 150);
	}
	
	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}
	
}
