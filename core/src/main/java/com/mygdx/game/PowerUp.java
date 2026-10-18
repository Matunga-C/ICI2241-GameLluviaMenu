package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class PowerUp implements CaidaLibre{
	protected float x; 
	protected float y;
	protected Texture textura;
	protected Rectangle hitbox;
	protected Sound sonidoPickUp;
	protected String texto;
	
	public PowerUp(float x, float y, Texture tx) {
		this.x = x;
		this.y = y;
		this.textura = tx;
		this.hitbox = new Rectangle(x, y, 50, 50);
		this.sonidoPickUp = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	}
	
	@Override
	public void caer(float velocidad) {
		this.y -= velocidad * Gdx.graphics.getDeltaTime();
		this.hitbox.y = y;
	}
	
	public void recoger(Nave nave) {
		sonidoPickUp.play();
		aplicarEfecto(nave);
	}
	
	public abstract void aplicarEfecto(Nave nave);
	
	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, x, y, 50, 50);
	}

	@Override
	public void dispose() {
		textura.dispose();
	}

	
	@Override
	public Rectangle getHitbox() {return hitbox;}
	
	//Getters 
	public float getX() {return x;}
	public float getY() {return y;}
	public Texture getTextura() {return textura;}
	public Sound getSonidoPickUp() {return sonidoPickUp;}
	public String getTexto() {return texto;}

	//Setters
	public void setX(float x) {this.x = x;}
	public void setTextura(Texture textura) {this.textura = textura;}
	public void setY(float y) {this.y = y;}
	public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}
	public void setSonidoPickUp(Sound sonidoPickUp) {this.sonidoPickUp = sonidoPickUp;}
	public void setTexto(String txt) {this.texto = txt;}
}
