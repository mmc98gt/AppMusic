package umu.tds.AppMusic.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer; 

public class Reproductor {
	private MediaPlayer mediaPlayer;
	
	public void activar() {
		// activar reproductor
		try {
		com.sun.javafx.application.PlatformImpl.startup(()->{});
		} catch(Exception ex) {
		ex.printStackTrace();
		System.out.println("Exception: " + ex.getMessage());
		 }
	}
	
	// reproducir una canción
	public void play(Cancion cancion) throws FileNotFoundException {
		String rutaCancion = cancion.getRutaFichero();
		URL resourceURL = getClass().getResource("/canciones"+"/" + rutaCancion);
		if (resourceURL == null)
		throw new FileNotFoundException("Fichero canción no encontrado: " + rutaCancion);
		Media hit = new Media(resourceURL.toExternalForm());
		mediaPlayer = new MediaPlayer(hit); 
	}
	

}
