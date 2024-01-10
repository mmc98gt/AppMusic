package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un intérprete musical, incluyendo su nombre y las canciones que interpreta.
 */
public class Interprete {

    private String nombre;
    private List<Cancion> canciones;

    /**
     * Constructor para crear un nuevo intérprete.
     * @param nombre El nombre del intérprete.
     */
    public Interprete(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del intérprete.
     * @return El nombre del intérprete.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del intérprete.
     * @param nombre El nuevo nombre del intérprete.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de canciones interpretadas por el intérprete.
     * @return Una lista de canciones.
     */
    public List<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * Establece la lista de canciones interpretadas por el intérprete.
     * @param canciones La nueva lista de canciones.
     */
    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    /**
     * Añade una canción a la lista de canciones interpretadas por el intérprete.
     * @param cancion La canción a añadir.
     */
    public void addCancion(Cancion cancion) {
        if (cancion != null && !canciones.contains(cancion)) {
            canciones.add(cancion);
        }
    }

    /**
     * Elimina una canción de la lista de canciones interpretadas por el intérprete.
     * @param cancion La canción a eliminar.
     */
    public void removeCancion(Cancion cancion) {
        canciones.remove(cancion);
    }

}
