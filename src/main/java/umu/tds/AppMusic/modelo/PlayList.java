package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    
    // Atributos
    private String nombre;
    private List<Cancion> canciones;

    // Constructor
    public PlayList(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    // Métodos

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

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "nombre='" + nombre + '\'' +
                ", canciones=" + canciones +
                '}';
    }
}
