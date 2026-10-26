package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class RayoJefe extends Bala{
	private boolean advirtiendo;
	private boolean congelado;
	
	private float tiempoAdvertencia;
	private float tiempoCongelado;
	private float tiempoImpacto;
	
	private Texture texturaAviso;
	private Texture texturaImpacto;
	private TextureRegion regionActual;
	
	private float origenX, origenY;
	private float destinoX, destinoY;
	private float distancia;
	private float angulo;
	
	public RayoJefe(float origenX, float origenY) {
	 	super(1, origenX, origenY);
	 	this.advirtiendo = true;
	 	this.congelado = false;
	 	
	 	this.tiempoAdvertencia = 2.0f;
	 	this.tiempoCongelado = 0.5f;
	 	this.tiempoImpacto = 1.0f;
	 	
	 	this.texturaAviso = new Texture("laser_advertencia.png");
	 	this.texturaImpacto = new Texture("laser_impacto.png");
	 	this.regionActual = new TextureRegion(texturaAviso);
	 	
	 	this.origenX = origenX;
	 	this.origenY = origenY;
	}
	
	@Override
	public void actualizarLaser(float delta) {}
	
	@Override
	public void actualizarRayo(float delta, Nave nave, float jefeCentroX, float jefeCentroY) {
		if(advirtiendo) {
			tiempoAdvertencia -= delta;
			
			this.origenX = jefeCentroX;
			this.origenY = jefeCentroY;
		        
		    this.destinoX = nave.getX() + 25;
		    this.destinoY = nave.getY() + 25;
		    
		    this.distancia = Vector2.dst(origenX, origenY, destinoX, destinoY);
		    
		    this.angulo = MathUtils.atan2(destinoY - origenY, destinoX - origenX) * MathUtils.radiansToDegrees;
		    
		    if(tiempoAdvertencia <= 0) {
		    	advirtiendo = false; 
		    	congelado = true;
		    }
		} else if(congelado){
			tiempoCongelado -= delta;
			this.origenX = jefeCentroX;
			this.origenY = jefeCentroY;
			this.distancia = Vector2.dst(origenX, origenY, destinoX, destinoY);
			
			if(tiempoCongelado <= 0) {
				congelado = false;
				this.regionActual = new TextureRegion(texturaImpacto); 
			}
		} else {
			tiempoImpacto -= delta;
			this.origenX = jefeCentroX;
			this.origenY = jefeCentroY;
			this.distancia = Vector2.dst(origenX, origenY, destinoX, destinoY);
		}	
	}
	
	public TextureRegion getRegion() { return regionActual;}
	
	public boolean isLetal() {
		return !advirtiendo && !congelado;
	}
	
	public boolean terminoAtaque() {
		return !advirtiendo && !congelado && tiempoImpacto <= 0;
	}
	
	
	@Override
	public void dispose() {
		texturaAviso.dispose();
		texturaImpacto.dispose();
	}

	public float getOrigenX() {return origenX;}

	public float getOrigenY() {return origenY;}

	public float getDestinoX() {return destinoX;}

	public float getDestinoY() {return destinoY;}

	public float getDistancia() {return distancia;}

	public float getAngulo() {return angulo;}
	
	
}
