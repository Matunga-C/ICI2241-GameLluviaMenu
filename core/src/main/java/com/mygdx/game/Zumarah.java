package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

public class Zumarah extends Jefe{
	float tiempoSigMov = 3.5f;
	
	public Zumarah(float x, float y, int vida, int danio, float ta) {
		super(x, y, vida, danio, ta);
		this.hitbox = new Rectangle(x, y, 150, 150);
		this.sonidoAtaque = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		this.sonidoSpawn = Gdx.audio.newSound(Gdx.files.internal("zumarah_spawn.wav"));
		this.textura = new Texture("zumarah.png");
	}
	
	public void movimiento(float delta) {
		tiempoSigMov -= delta;
		if(tiempoSigMov <= 0.0f) {
			int dado = MathUtils.random(0, 100);
		
			if(dado <= 45) {
				this.setX(this.getX() - 150);
				if(getX() < 0.0f) {
					 setX(0.0f);
				}
			} else if(dado <= 90) {
				this.setX(this.getX() + 150);
				if(getX() >= 450.0f) {
					setX(450.0f);
				}
			} else {
				this.setX(MathUtils.random(0, 450));
			}
			tiempoSigMov = 3.0f;
		} 
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
