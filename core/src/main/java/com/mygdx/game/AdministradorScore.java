package com.mygdx.game;

import java.util.ArrayList;
import java.util.Collections;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class AdministradorScore {
	private static AdministradorScore instancia;
	
	private int highScore;
	private ArrayList<Usuario> tablaPuntuaciones;
	
	private AdministradorScore() {
		this.highScore = 0;
		this.tablaPuntuaciones = new ArrayList<>();
		cargarTablaCSV();
	}
	
	public static AdministradorScore getInstancia() {
		if(instancia == null) {
			instancia = new AdministradorScore();
		}
		return instancia;
	}
	
	public void registrarPuntaje(String nombre, int puntos) {
		Usuario user = new Usuario(nombre, puntos);
		tablaPuntuaciones.add(user);
		Collections.sort(tablaPuntuaciones);
		if(tablaPuntuaciones.size() > 10) {
			tablaPuntuaciones.remove(10);
		}
		guardarTablaCSV();
	}
	
	public void guardarTablaCSV() {
		FileHandle archivo = Gdx.files.local("scoreboard.csv");
		StringBuilder texto = new StringBuilder();
		
		for(Usuario user: tablaPuntuaciones) {
			texto.append(user.getNombre()).append(",").append(user.getScore()).append("\n");
		}
		archivo.writeString(texto.toString(), false);
	}
	
	public void cargarTablaCSV() {
		FileHandle archivo = Gdx.files.local("scoreboard.csv");
		if(archivo.exists()) {
			String lineas[] = archivo.readString().split("\n");
			for(String linea: lineas) {
				if(linea.trim().isEmpty()) {
					continue;
				}
				String partes[] = linea.split(",");
				Usuario user = new Usuario(partes[0], Integer.parseInt(partes[1].trim()));
				tablaPuntuaciones.add(user);
			}
		}	
	}
	
	public int getHighScore() { 
        return highScore; 
    }
	
	public ArrayList<Usuario> getTablaPuntuaciones(){
		return tablaPuntuaciones;
	}
	
	public void actualizarHighScore(int puntajeNuevo) {
        if (puntajeNuevo > this.highScore) {
            this.highScore = puntajeNuevo;
        }
    }
}
