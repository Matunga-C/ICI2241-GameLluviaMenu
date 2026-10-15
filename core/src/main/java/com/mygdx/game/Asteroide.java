package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Asteroide implements CaidaLibre{
	private float x;
	private float y;
	private Texture asteroide;
	private Rectangle hitbox;
	
	public Asteroide(float x, float y) {
		this.x = x;
		this.y = y;
		this.asteroide = new Texture("asteroide.png");
		this.hitbox = new Rectangle(x, y, 100, 100);
	}
	
	@Override
	public void caer(float velocidad) {
		y -= velocidad * Gdx.graphics.getDeltaTime(); 
		this.hitbox.y = y;
	}
	
	@Override
	public void dispose() {
		asteroide.dispose();
	}

	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(asteroide, x, y, 100, 100);
	}

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getAsteroide() {
		return asteroide;
	}

	public void setAsteroide(Texture asteroide) {
		this.asteroide = asteroide;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	
}
