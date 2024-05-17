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
		String estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO_MUSICAL);
		String interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETE);
		int numReproducciones = Integer.parseInt(numReproduccionesStr);


		Cancion cancion = new Cancion(titulo, rutaFichero, estilo, interprete);
		for (int i = 0; i < numReproducciones; i++) {
			cancion.incrementarReproducciones();
		}
		

		cancion.setId(eCancion.getId());
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


		eCancion.setPropiedades(new ArrayList<>(Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(RUTA_FICHERO, cancion.getRutaFichero()),
				new Propiedad(NUM_REPRODUCCIONES, String.valueOf(cancion.getNumReproducciones())),
				new Propiedad(ESTILO_MUSICAL, cancion.getEstilo()), new Propiedad(INTERPRETE, cancion.getInterprete()))));
		return eCancion;
	}

    /**
     * Agrega una nueva canción a la base de datos solo si no existe una canción con el mismo título e intérprete.
     * 
     * @param cancion La canción a agregar.
     */
	public void agregarCancion(Cancion cancion) {
	    if (!existeCancion(cancion.getTitulo(), cancion.getInterprete())) {
	    	Entidad eCancion = cancionToEntidad(cancion);
	    	eCancion = servPersistencia.registrarEntidad(eCancion);
	    	cancion.setId(eCancion.getId());
	    }
	}

	/**
	 * Actualiza una canción existente en la base de datos.
	 * 
	 * @param cancion La canción a actualizar.
	 */
	public void actualizarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		if (eCancion == null) {
			throw new IllegalStateException("Cancion not found with ID: " + cancion.getId());
	    }
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
				prop.setValor(cancion.getEstilo());
				break;
			case INTERPRETE:
				prop.setValor(cancion.getInterprete());
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
	
	/**
	 * Obtiene una canción por su id.
	 * 
	 * @param titulo El id de la canción a buscar.
	 * @return La canción encontrada o null si no existe.
	 */
	@Override
	public Cancion obtenerCancionPorId(Integer id) {
	    Entidad entidad = servPersistencia.recuperarEntidad(id);
	    if (entidad == null) {
	        // Handle this case, maybe log an error or throw
	        return null;
	    }
	    return entidadToCancion(entidad);
	}
	
    /**
     * Verifica si existe una canción con el mismo título e intérprete en la base de datos.
     * 
     * @param titulo El título de la canción.
     * @param interprete El intérprete de la canción.
     * @return true si la canción existe, false de lo contrario.
     */
    private boolean existeCancion(String titulo, String interprete) {
        return servPersistencia.recuperarEntidades(CANCION).stream()
            .anyMatch(e -> titulo.equals(servPersistencia.recuperarPropiedadEntidad(e, TITULO)) &&
                           interprete.equals(servPersistencia.recuperarPropiedadEntidad(e, INTERPRETE)));
    }
    
    /**
     * Borra todas las canciones en la persistencia.
     */
    public void borrarCanciones() {
        List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);
        entidades.forEach(servPersistencia::borrarEntidad);
    }

}
