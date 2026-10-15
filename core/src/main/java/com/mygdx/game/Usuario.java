package com.mygdx.game;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private int score;
	private ArrayList<Usuario> usuarios;
	
	public Usuario() {}
	
	public void setNombre(String nombre) {
		//normalizar nombre
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getNombre() {return nombre;}
	public int getScore() {return score;}
}
