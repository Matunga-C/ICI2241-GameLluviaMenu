package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class RayoJefe extends Bala{
	private boolean advirtiendo;
	private float tiempoAdvertencia;
	private float tiempoImpacto;
	
	private Texture texturaAviso;
	private Texture texturaImpacto;
	
	public RayoJefe(float x, float altoRayo) {
	 	super(1, x, 0);
	 	this.advirtiendo = true;
	 	this.tiempoAdvertencia = 1.0f;
	 	this.tiempoImpacto = 1.0f;
	 	
	 	this.hitbox = new Rectangle(x, 0, 80, altoRayo);
	 	
	 	this.texturaAviso = new Texture("laser_advertencia.png");
	 	this.texturaImpacto = new Texture("laser_impacto.png");
	 	this.textura = texturaAviso;
	}
	
	@Override
	public void actualizar(float delta) {
		if(advirtiendo) {
			tiempoAdvertencia -= delta;
			if(tiempoAdvertencia <= 0) {
				advirtiendo = false;
				this.textura = texturaImpacto;
			}
		} else {
			tiempoImpacto -= delta;
		}
		
	}
	
	public boolean isAdvirtiendo() {
		return advirtiendo;
	}
	
	public boolean terminoAtaque() {
		return !advirtiendo && tiempoImpacto <= 0;
	}
	
	
	@Override
	public void dispose() {
		texturaAviso.dispose();
		texturaImpacto.dispose();
	}
}
