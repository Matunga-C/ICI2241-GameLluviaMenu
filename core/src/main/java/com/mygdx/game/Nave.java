package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Nave {
	private Texture nave;
	private float x;
	private float y;
	private int vidas = 3;
	private int puntos = 0;
	private Rectangle hitbox;
	
	
	public Nave(float x, float y) {
		this.x = x;
		this.y = y;
		this.nave = new Texture("nave_espacial.png");
		this.vidas = 3;
		this.hitbox = new Rectangle(x, y, 50, 50);
	}
	
	public void mover() {
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	y += 300 * Gdx.graphics.getDeltaTime();
        	if(y >= 250.0f) {
        		y = 250.0f;
        	}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	y -= 300 * Gdx.graphics.getDeltaTime();
        	if(y <= 0.0f) {
        		y = 0.0f;
        	}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	x -= 300 * Gdx.graphics.getDeltaTime();
        	if(x <= 0.0f) {
        		x = 0.0f;
        	}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	x += 300 * Gdx.graphics.getDeltaTime();
        	if(x >= 550.0f) {
        		x = 550.0f;
        	}
        }
        hitbox.x = x;
        hitbox.y = y;
	}
	
	public void restarVida() {
		this.vidas -= 1;
	}
	
	public void sumarPuntos() {
		this.puntos += 100;
	}
	
	public void dibujar(SpriteBatch batch) {
		batch.draw(nave, x, y, 50, 50);
	}
	
	public void dispose() {
		nave.dispose();
	}
	
	//Getters
	public float getX() {return x;}
	public float getY() {return y;}
	public Texture getNave() {return nave;}
	public Rectangle getHitbox() {return hitbox;}
	public int getVidas() {return vidas;}
	public void setNave(Texture nave) {this.nave = nave;}
	public int getPuntos() {return puntos;}

	//Setters
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setVidas(int vidas) {this.vidas = vidas;}
	public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}
	public void setPuntos(int puntos) {this.puntos = puntos;}

}
