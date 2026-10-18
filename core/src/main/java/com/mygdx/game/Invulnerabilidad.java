package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public class Invulnerabilidad extends PowerUp{
	
	public Invulnerabilidad(float x, float y, Texture tx) {
		super(x, y, tx);
		this.texto = "Invulnerabilidad por 5s...";
	}

	@Override
	public void aplicarEfecto(Nave nave) {
		nave.activarInvulnerabilidad();
	}
}
