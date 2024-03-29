package umu.tds.AppMusic.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;

/**
 * Clase que implementa el Adaptador DAO concreto de Cancion para el tipo H2. Se
 * encarga de convertir objetos Cancion en entidades para persistirlas y
 * viceversa.
 */
public class TDSCancionDao implements CancionDao {

	private static final String CANCION = "Cancion";
	private static final String TITULO = "titulo";
	private static final String RUTA_FICHERO = "rutaFichero";
	private static final String NUM_REPRODUCCIONES = "numReproducciones";
	private static final String ESTILO_MUSICAL = "estiloMusical";
	private static final String INTERPRETE = "interprete";

	private ServicioPersistencia servPersistencia;

	public TDSCancionDao() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/**
	 * Convierte una entidad a un objeto Cancion.
	 * 
	 * @param eCancion La entidad a convertir.
	 * @return El objeto Cancion correspondiente a la entidad.
	 */
	private Cancion entidadToCancion(Entidad eCancion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, RUTA_FICHERO);
		String numReproduccionesStr = servPersistencia.recuperarPropiedadEntidad(eCancion, NUM_REPRODUCCIONES);
		int numReproducciones = Integer.parseInt(numReproduccionesStr);

		EstiloMusical estilo = new EstiloMusical(""); // Lógica pendiente
		Interprete interprete = new Interprete(""); // Lógica pendiente

		Cancion cancion = new Cancion(titulo, rutaFichero, estilo, interprete);
		for (int i = 0; i < numReproducciones; i++) {
			cancion.incrementarReproducciones();
		}

		return cancion;
	}

	/**
	 * Convierte un objeto Cancion a una entidad.
	 * 
	 * @param cancion La canción a convertir.
	 * @return La entidad correspondiente al objeto Cancion.
	 */
	private Entidad cancionToEntidad(Cancion cancion) {
		Entidad eCancion = new Entidad();
		eCancion.setNombre(CANCION);

		String estiloId = ""; // Lógica pendiente
		String interpreteId = ""; // Lógica pendiente

		eCancion.setPropiedades(new ArrayList<>(Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(RUTA_FICHERO, cancion.getRutaFichero()),
				new Propiedad(NUM_REPRODUCCIONES, String.valueOf(cancion.getNumReproducciones())),
				new Propiedad(ESTILO_MUSICAL, estiloId), new Propiedad(INTERPRETE, interpreteId))));
		return eCancion;
	}

	/**
	 * Agrega una nueva canción a la base de datos.
	 * 
	 * @param cancion La canción a agregar.
	 */
	public void agregarCancion(Cancion cancion) {
		Entidad eCancion = this.cancionToEntidad(cancion);
		servPersistencia.registrarEntidad(eCancion);
	}

	/**
	 * Actualiza una canción existente en la base de datos.
	 * 
	 * @param cancion La canción a actualizar.
	 */
	public void actualizarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		eCancion.getPropiedades().forEach(prop -> {
			switch (prop.getNombre()) {
			case TITULO:
				prop.setValor(cancion.getTitulo());
				break;
			case RUTA_FICHERO:
				prop.setValor(cancion.getRutaFichero());
				break;
			case NUM_REPRODUCCIONES:
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
				break;
			case ESTILO_MUSICAL:
			case INTERPRETE:
				// Lógica pendiente para convertir estilo e intérprete
				break;
			}
			servPersistencia.modificarPropiedad(prop);
		});
	}

	/**
	 * Elimina una canción de la base de datos.
	 * 
	 * @param cancion La canción a eliminar.
	 */
	public void eliminarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		if (eCancion != null) {
			servPersistencia.borrarEntidad(eCancion);
		}
	}

	/**
	 * Obtiene una canción por su título.
	 * 
	 * @param titulo El título de la canción a buscar.
	 * @return La canción encontrada o null si no existe.
	 */
	public Cancion obtenerCancionPorTitulo(String titulo) {
		return servPersistencia.recuperarEntidades(CANCION).stream()
				.filter(eCancion -> servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO).equals(titulo))
				.findFirst().map(this::entidadToCancion).orElse(null);
	}

	/**
	 * Obtiene todas las canciones disponibles en la base de datos.
	 * 
	 * @return Una lista de todas las canciones.
	 */
	public List<Cancion> obtenerTodasLasCanciones() {
		return servPersistencia.recuperarEntidades(CANCION).stream().map(this::entidadToCancion)
				.collect(Collectors.toList());
	}
}
