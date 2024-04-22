package umu.tds.AppMusic.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.OptionalInt;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.AppMusic.controlador.Controlador;

public enum Reproductor {
	INSTANCE;
	
	private static MediaPlayer mediaPlayer;


	public void activar() {
		// activar reproductor
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	// reproducir una canción
	public void play(Cancion cancion) throws FileNotFoundException {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
			mediaPlayer.play();
			return;
		}
		if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.stop();
		}

		String tempPath = System.getProperty("user.dir") + "/temp";
		String url = cancion.getRutaFichero();
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});

			Media media;
			if (url.startsWith("http")) {
				URL uri = new URL(url);
				System.setProperty("java.io.tmpdir", tempPath);
				Path mp3 = Files.createTempFile("now-playing", ".mp3");
				try (InputStream stream = uri.openStream()) {
					Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
				}
				media = new Media(mp3.toFile().toURI().toString());

				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.play();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Método para detener la reproducción
	public void stop() {
		String tempPath = System.getProperty("user.dir") + "/temp";
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
			mediaPlayer = null;
		}
		File directorio = new File(tempPath);
		if (directorio.exists() && directorio.isDirectory()) {
			String[] files = directorio.list();
			if (files != null) {
				for (String archivo : files) {
					File fichero = new File(directorio, archivo);
					fichero.delete();
				}
			}
		}
	}

	// Método para pausar la reproducción de la canción
	public void pauseCancion() {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.pause();
		}
	}

	public void siguiente(PlayList playlist, int i) throws FileNotFoundException {
		if (playlist == null || i == -1) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			return;
		}
		if (playlist.getCanciones().size() > i + 1) {
			Cancion cancion = playlist.getCanciones().get(i + 1);
			Controlador.INSTANCE.establecerCancionActual(cancion, i+1);
			play(cancion);
			
		} else {
			if (mediaPlayer != null)
				mediaPlayer.stop();
		}

	}
	
	public void anterior(PlayList playlist, int i) throws FileNotFoundException {
		if (playlist == null || i == -1) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			return;
		}
		if (playlist.getCanciones().size() > i-1) {
			Cancion cancion = playlist.getCanciones().get(i-1);
			Controlador.INSTANCE.establecerCancionActual(cancion,i-1);
			play(cancion);
			
		} else {
			if (mediaPlayer != null)
				mediaPlayer.stop();
		}

	}

}
