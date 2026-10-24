package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AdministradorJuego {
    private static AdministradorJuego instancia;

    private int highScore;
    private Music musicaMenu;
    private Music musicaJuego;
    private Music musicaJefe;
    
    private AdministradorJuego() {
        this.highScore = 0;
        
        this.musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("Music_PesoEstable.mp3")); 
        this.musicaMenu.setLooping(true);
        
        this.musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("musica_fondo_normal.mp3"));
        this.musicaJuego.setLooping(true);
        
        this.musicaJefe = Gdx.audio.newMusic(Gdx.files.internal("zumarah_song.mp3"));
        this.musicaJefe.setLooping(true);
    }
    
    public static AdministradorJuego getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorJuego();
        }
        return instancia;
    }
    
    public int getHighScore() { 
        return highScore; 
    }
    
    public void actualizarHighScore(int puntajeNuevo) {
        if (puntajeNuevo > this.highScore) {
            this.highScore = puntajeNuevo;
        }
    }
    
    public void reproducirMusicaMenu() {
        if (musicaJuego.isPlaying()) musicaJuego.stop();
        if (!musicaMenu.isPlaying()) musicaMenu.play();
    }
    
    public void reproducirMusicaJuego() {
        if (musicaMenu.isPlaying()) musicaMenu.stop();
        if (!musicaJuego.isPlaying()) musicaJuego.play();
    }
    
    public void reproducirMusicaJefe() {
        if (musicaJefe.isPlaying()) musicaJefe.stop();
        if (!musicaJefe.isPlaying()) musicaJefe.play();
    }
    
    public void detenerTodaLaMusica() {
        musicaMenu.stop();
        musicaJuego.stop();
        musicaJefe.stop();
    }
    
    public void dispose() {
        musicaMenu.dispose();
        musicaJuego.dispose();
        musicaJefe.dispose();
    }
}