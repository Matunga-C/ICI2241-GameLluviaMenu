package com.mygdx.game;

public class Usuario implements Comparable<Usuario>{
	private String nombre;
	private int score;
	
	public Usuario(String nombre, int score) {
		this.nombre = normalizarNombre(nombre);
		this.score = score;
	}
	
	public String normalizarNombre(String nombre) {
		if(nombre == null || nombre.isEmpty()) {
			return "";
		}
		
		StringBuilder textoLimpio = new StringBuilder();
		
		for(char c : nombre.toCharArray()) {
			if(Character.isLetter(c)) {
				textoLimpio.append(Character.toUpperCase(c));
			}else if(Character.isDigit(c)){
				textoLimpio.append(c);
			}
		}
		return textoLimpio.toString();
	}
	
	@Override
	public int compareTo(Usuario o) {
		if(Integer.compare(score, o.getScore()) == 0) {
			return this.nombre.compareTo(o.getNombre());
		}
		return Integer.compare(o.getScore(), this.score);
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void setNombre(String nombre) {
		this.nombre = normalizarNombre(nombre);
	}
	public String getNombre() {return nombre;}
	public int getScore() {return score;}
}
