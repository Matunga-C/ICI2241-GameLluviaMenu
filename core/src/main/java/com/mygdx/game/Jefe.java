package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Jefe {
	protected float x;
	protected float y;
	protected int vida;
	protected int danio;
	protected float tiempoAtaque;
	protected Rectangle hitbox;
	protected Texture textura;
	protected Sound sonidoAtaque;
	protected Sound sonidoSpawn;
	protected Music musicaFondo;
	
	public Jefe(float x, float y ,int vida, int danio, float ta) {
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.danio = danio;
		this.tiempoAtaque = ta;
	}
	
	public abstract void disparar();
	
	public void dibujar(SpriteBatch batch) {
		
	}
	
	public void dispose() {
		textura.dispose();
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public int getVida() {return vida;}
	public int getDanio() {return danio;}
	public float getTiempoAtaque() {return tiempoAtaque;}
	public Rectangle getHitbox() {return hitbox;}
	public Texture getTextura() {return textura;}
	public Sound getSonidoAtaque() {return sonidoAtaque;}
	public Sound getSonidoSpawn() {return sonidoSpawn;}
	public Music getMusicaFondo() {return musicaFondo;}
	

	public void setX(float x) { 
	    this.x = x; 
	    if (this.hitbox != null) this.hitbox.x = x; 
	}

	public void setY(float y) { 
	    this.y = y; 
	    if (this.hitbox != null) this.hitbox.y = y; 
	}
	public void setVida(int vida) {this.vida = vida;}
	public void setDanio(int danio) {this.danio = danio;}
	public void setTiempoAtaque(float tiempoAtaque) {this.tiempoAtaque = tiempoAtaque;}
	public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}
	public void setTextura(Texture textura) {this.textura = textura;}
	public void setSonidoAtaque(Sound sa) {this.sonidoAtaque = sa;}
	public void setMusicaFondo(Music mf) {this.musicaFondo = mf;}
	
}	
