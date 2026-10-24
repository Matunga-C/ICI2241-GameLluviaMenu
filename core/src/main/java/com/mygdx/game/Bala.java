package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Bala {
	protected float x;
	protected float y;
	protected int danio;
	protected Texture textura;
	protected Rectangle hitbox;
	protected Sound sonidoDisparo;
	
	public Bala(int danio, float x, float y) {
		this.danio = danio;
		this.x = x;
		this.y = y;
	}
	
	public abstract void actualizar(float delta);
	
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, x, y, 30, 60);
	}
	
	public void dispose() {
		textura.dispose();
	}
	public float getX() {return x;}
	public float getY() {return y;}
	public int getDanio() {return danio;}
	public Texture getTextura() {return textura;}
	public Rectangle getHitbox() {return hitbox;}
	public Sound getSonidoDisparo() {return sonidoDisparo;}

	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setDanio(int danio) {this.danio = danio;}
	public void setTextura(Texture textura) {this.textura = textura;}
	public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}
	public void setSonidoDisparo(Sound sonidoDisparo) {this.sonidoDisparo = sonidoDisparo;}
	
}
