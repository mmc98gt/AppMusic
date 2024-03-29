package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una lista de reproducción de canciones con un nombre específico.
 */
public class PlayList {
    
    private String nombre;
    private List<Cancion> canciones;

    /**
     * Constructor para crear una nueva playlist.
     * @param nombre El nombre de la playlist.
     */
    public PlayList(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    /**
     * Agrega una canción a la playlist.
     * @param cancion La canción a agregar.
     */
    public void addCancion(Cancion cancion) {
        if (cancion != null && !canciones.contains(cancion)) {
            canciones.add(cancion);
        }
    }

    /**
     * Obtiene la lista de canciones de la playlist.
     * @return Una lista de canciones.
     */
    public List<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * Obtiene el nombre de la playlist.
     * @return El nombre de la playlist.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la playlist.
     * @param nombre El nuevo nombre para la playlist.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Proporciona una representación en cadena de la playlist.
     * @return Una representación textual de la playlist, incluyendo su nombre y canciones.
     */
    @Override
    public String toString() {
        return "PlayList{" +
                "nombre='" + nombre + '\'' +
                ", canciones=" + canciones +
                '}';
    }
}
