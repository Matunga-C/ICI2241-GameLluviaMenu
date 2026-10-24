package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Laser extends Bala{	
	
	public Laser(int danio, float x, float y) {
		super(danio, x, y);
		this.hitbox = new Rectangle(x, y, 30, 60);
		this.sonidoDisparo = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		this.textura = new Texture("laser.png");
	}
	
	@Override
	public void actualizar(float delta) {
		this.y += 480 * delta;
		
		this.hitbox.y = this.y;
		this.hitbox.x = this.x;
	}
	
	@Override
	public void dispose() {
		textura.dispose();
		sonidoDisparo.dispose();
	}
	
}
