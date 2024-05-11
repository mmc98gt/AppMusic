package umu.tds.AppMusic.modelo;

/**
 * Representa un estilo musical con un nombre específico. Esta clase se utiliza
 * para categorizar canciones según su estilo musical.
 */
public class EstiloMusical {

	private String nombre;

	/**
	 * Constructor para crear un nuevo estilo musical.
	 * 
	 * @param nombre El nombre del estilo musical.
	 */
	public EstiloMusical(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el nombre del estilo musical.
	 * 
	 * @return El nombre del estilo musical.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del estilo musical.
	 * 
	 * @param nombre El nuevo nombre para el estilo musical.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Proporciona una representación en forma de cadena de texto del estilo
	 * musical.
	 * 
	 * @return Una cadena de texto que representa el estilo musical.
	 */
	@Override
	public String toString() {
		return "EstiloMusical{" + "nombre=" + nombre + '}';
	}

}
