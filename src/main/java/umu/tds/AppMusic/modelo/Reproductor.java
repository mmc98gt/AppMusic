package umu.tds.AppMusic.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.AppMusic.controlador.Controlador;

/**
 * Singleton que representa el reproductor de música.
 */
public enum Reproductor {
    INSTANCE;

    private static MediaPlayer mediaPlayer;

    /**
     * Activa el reproductor de música.
     */
    public void activar() {
        try {
            com.sun.javafx.application.PlatformImpl.startup(() -> {});
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    /**
     * Reproduce una canción.
     *
     * @param cancion la canción a reproducir.
     * @throws FileNotFoundException si no se encuentra el archivo de la canción.
     */
    public void play(Cancion cancion) throws FileNotFoundException {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            mediaPlayer.play();
            return;
        }
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Controlador.INSTANCE.addReciente();
        Controlador.INSTANCE.addReproduccion(cancion);
        String tempPath = System.getProperty("user.dir") + "/temp";
        String url = cancion.getRutaFichero();
        try {
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
            JOptionPane.showMessageDialog(null, "No se ha podido reproducir la canción", "Error de reproducción",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Detiene la reproducción de la canción.
     */
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

    /**
     * Pausa la reproducción de la canción.
     */
    public void pauseCancion() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    /**
     * Reproduce la siguiente canción en la playlist.
     *
     * @param playlist la playlist que contiene las canciones.
     * @param i        el índice de la canción a reproducir.
     * @throws FileNotFoundException si no se encuentra el archivo de la canción.
     */
    public void siguiente(PlayList playlist, int i) throws FileNotFoundException {
        if (mediaPlayer != null) {
            if (playlist == null || i == -1) {
                mediaPlayer.stop();
                return;
            }
            if (playlist.getCanciones().size() > i) {
                Cancion cancion = playlist.getCanciones().get(i);
                Controlador.INSTANCE.establecerCancionActual(cancion, i);
                play(cancion);
            }
        }
    }
}
