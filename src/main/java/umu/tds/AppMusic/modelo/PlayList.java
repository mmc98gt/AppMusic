package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	 * @param nombre El nombre de la playlist.
	 */
	public PlayList(String nombre) {
		this.nombre = nombre;
		this.canciones = new ArrayList<>();
	}

	public PlayList(String nombre, List<Cancion> canciones) {
		this.nombre = nombre;
		this.canciones = canciones;
	}

	/**
	 * Agrega una canción a la playlist.
	 * 
	 * @param cancion La canción a agregar.
	 */
	public void addCancion(Cancion cancion) {
		if (cancion != null && !canciones.contains(cancion)) {
			canciones.add(cancion);
		}
	}

	/**
	 * Incrementa el número de veces que ha sido reproduccida una canción.
	 * 
	 * @param song La canción a aumentar sus reproducciones.
	 */
	public void addReproduccion(Cancion song) {
		canciones.stream().filter(s -> s.getId() == song.getId()).distinct().forEach(Cancion::addReproduccion);
	}

	/**
	 * Obtiene la lista de canciones de la playlist.
	 * 
	 * @return Una lista de canciones.
	 */
	public List<Cancion> getCanciones() {
		return new ArrayList<Cancion>(canciones);
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	/**
	 * Obtiene el nombre de la playlist.
	 * 
	 * @return El nombre de la playlist.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece un nuevo nombre para la playlist.
	 * 
	 * @param nombre El nuevo nombre para la playlist.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el identificador de la playlist.
	 * 
	 * @return El identificador de la playlist.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador de la canción.
	 * 
	 * @param id El nuevo identificador de la canción.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Proporciona una representación en cadena de la playlist.
	 * 
	 * @return Una representación textual de la playlist, incluyendo su nombre y
	 *         canciones.
	 */
	@Override
	public String toString() {
		return "PlayList{" + "nombre='" + nombre + '\'' + ", canciones=" + canciones + '}';
	}

	public void borrarCancion(Cancion c) {
		if (canciones.contains(c)) {
			canciones.remove(c);
		}
	}

	public void addCancionReciente(Cancion cancion) {
		boolean duplicada = canciones.stream().anyMatch(c -> c.getId() == cancion.getId());
		if (!duplicada) {
			canciones.add(0, cancion);
		} else {
			for (int i = 0; i < canciones.size(); i++) {
				if (canciones.get(i).getId() == cancion.getId()) {
					canciones.remove(i);
				}
			}
			canciones.add(0, cancion);
			// tamaño máximo 10
			if (canciones.size() >= 10) {
				canciones.remove(canciones.size() - 1);
			}
		}

	}
}
