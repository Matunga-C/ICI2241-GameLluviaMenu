package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Jefe {
	private int vida;
	private int danio;
	private Rectangle hitbox;
	private Texture textura;
	private Sound sonidoAtaque;
	private Sound sonidoSpawn;
	private Music musicaFondo;
	
	public Jefe(int vida, int danio, Sound sa, Music mf, Texture tx, Rectangle hitbox) {
		this.vida = vida;
		this.danio = danio;
		this.hitbox = hitbox;
		this.textura = tx;
		this.sonidoAtaque = sa;
		this.sonidoSpawn = Gdx.audio.newSound(Gdx.files.internal(null));
		this.musicaFondo = mf;
	}

	public int getVida() {return vida;}
	public int getDanio() {return danio;}
	public Rectangle getHitbox() {return hitbox;}
	public Texture getTextura() {return textura;}
	public Sound getSonidoAtaque() {return sonidoAtaque;}
	public Sound getSonidoSpawn() {return sonidoSpawn;}
	public Music getMusicaFondo() {return musicaFondo;}
	
	public void setVida(int vida) {this.vida = vida;}
	public void setDanio(int danio) {this.danio = danio;}
	public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}
	public void setTextura(Texture textura) {this.textura = textura;}
	public void setSonidoAtaque(Sound sa) {this.sonidoAtaque = sa;}
	public void setMusicaFondo(Music mf) {this.musicaFondo = mf;}
	
}	
