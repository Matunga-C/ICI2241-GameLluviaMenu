package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Llave implements CaidaLibre{
	private float x;
	private float y;
	private Texture textura;
	private Rectangle hitbox;
	
	public Llave(float x, float y) {
		this.x = x;
		this.y = y;
		this.textura = new Texture("llave.jpg");
		this.hitbox = new Rectangle(x, y, 100, 100);
	}
	
	
	@Override
	public void caer(float velocidad) {
		y -= velocidad * Gdx.graphics.getDeltaTime(); 
		this.hitbox.y = y;
	}
	
	@Override
	public void dispose() {
		textura.dispose();
	}


	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, x, y, 100, 100);
	}


	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	
}
