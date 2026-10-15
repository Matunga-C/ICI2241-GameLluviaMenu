package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public interface CaidaLibre {
	void caer(float velocidad);
	
	void dibujar(SpriteBatch batch);
	
	Rectangle getHitbox();
	
	void dispose();
}
