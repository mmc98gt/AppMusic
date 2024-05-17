package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una lista de reproducción de canciones con un nombre específico.
 */
public class PlayList {

    private String nombre;
    private int id;
    private List<Cancion> canciones;

    /**
     * Constructor para crear una nueva playlist.
     *
     * @param nombre el nombre de la playlist.
     */
    public PlayList(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    /**
     * Constructor para crear una nueva playlist con una lista de canciones.
     *
     * @param nombre    el nombre de la playlist.
     * @param canciones la lista de canciones de la playlist.
     */
    public PlayList(String nombre, List<Cancion> canciones) {
        this.nombre = nombre;
        this.canciones = canciones;
    }

    /**
     * Agrega una canción a la playlist.
     *
     * @param cancion la canción a agregar.
     */
    public void addCancion(Cancion cancion) {
        if (cancion != null && !canciones.contains(cancion)) {
            canciones.add(cancion);
        }
    }

    /**
     * Incrementa el número de veces que ha sido reproducida una canción.
     *
     * @param song la canción a aumentar sus reproducciones.
     */
    public void addReproduccion(Cancion song) {
        canciones.stream().filter(s -> s.getId() == song.getId()).distinct().forEach(Cancion::addReproduccion);
    }

    /**
     * Obtiene la lista de canciones de la playlist.
     *
     * @return una lista de canciones.
     */
    public List<Cancion> getCanciones() {
        return new ArrayList<>(canciones);
    }

    /**
     * Establece una nueva lista de canciones para la playlist.
     *
     * @param canciones la nueva lista de canciones.
     */
    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    /**
     * Obtiene el nombre de la playlist.
     *
     * @return el nombre de la playlist.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la playlist.
     *
     * @param nombre el nuevo nombre para la playlist.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador de la playlist.
     *
     * @return el identificador de la playlist.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la playlist.
     *
     * @param id el nuevo identificador de la playlist.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Proporciona una representación en cadena de la playlist.
     *
     * @return una representación textual de la playlist, incluyendo su nombre y canciones.
     */
    @Override
    public String toString() {
        return "PlayList{" +
                "nombre='" + nombre + '\'' +
                ", canciones=" + canciones +
                '}';
    }

    /**
     * Borra una canción de la playlist.
     *
     * @param c la canción a borrar.
     */
    public void borrarCancion(Cancion c) {
        canciones.remove(c);
    }

    /**
     * Agrega una canción reciente a la playlist. Si la canción ya existe, la mueve al principio.
     *
     * @param cancion la canción reciente a agregar.
     */
    public void addCancionReciente(Cancion cancion) {
        canciones.removeIf(c -> c.getId() == cancion.getId());
        canciones.add(0, cancion);
        // tamaño máximo 10
        if (canciones.size() > 10) {
            canciones.remove(canciones.size() - 1);
        }
    }
}
