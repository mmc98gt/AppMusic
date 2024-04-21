package umu.tds.AppMusic.dao;

import java.util.List;
import java.util.stream.Collectors;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;
import umu.tds.AppMusic.cargadorCanciones.MapperCancionesXMLtoJava;
import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.cargadorCanciones.CancionXml;
import umu.tds.AppMusic.cargadorCanciones.Canciones;

/**
 * Clase que implementa el Adaptador DAO concreto de Cancion para archivos XML.
 * Proporciona la funcionalidad para realizar operaciones CRUD sobre un archivo XML de canciones.
 */
public class TDSCancionXMLDao implements CancionDao {

    private static final String PATH_XML = "xml/canciones.xml";

    /**
     * Agrega una nueva canción al archivo XML.
     * @param cancion La canción a agregar.
     */
    @Override
    public void agregarCancion(Cancion cancion) {
        // Implementación depende de cómo se maneje la adición de datos al XML.
        // Normalmente, la adición se haría a una base de datos y no directamente al XML.
    }

    /**
     * Actualiza una canción existente en el archivo XML.
     * @param cancion La canción a actualizar.
     */
    @Override
    public void actualizarCancion(Cancion cancion) {
        // Similar a agregarCancion, la actualización normalmente no se hace directamente en un archivo XML.
    }

    /**
     * Elimina una canción del archivo XML.
     * @param cancion La canción a eliminar.
     */
    @Override
    public void eliminarCancion(Cancion cancion) {
        // La eliminación de datos en un archivo XML es poco común y depende de la implementación específica.
    }

    /**
     * Obtiene una canción por su título desde un archivo XML.
     * @param titulo El título de la canción a buscar.
     * @return La canción encontrada o null si no existe.
     */
    @Override
    public Cancion obtenerCancionPorTitulo(String titulo) {
        Canciones cancionesXML = MapperCancionesXMLtoJava.cargarCanciones(PATH_XML);
        return cancionesXML.getCancion().stream()
            .filter(cancionXML -> cancionXML.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .map(this::convertirCancionXMLaCancion)
            .orElse(null);
    }

    /**
     * Obtiene todas las canciones disponibles en el archivo XML.
     * @return Una lista de todas las canciones.
     */
    @Override
    public List<Cancion> obtenerTodasLasCanciones() {
        Canciones cancionesXML = MapperCancionesXMLtoJava.cargarCanciones(PATH_XML);
        return cancionesXML.getCancion().stream()
                .map(this::convertirCancionXMLaCancion)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto CancionXml a un objeto Cancion.
     * @param cancionXML El objeto CancionXml a convertir.
     * @return El objeto Cancion convertido.
     */
    private Cancion convertirCancionXMLaCancion(CancionXml cancionXML) {
        // Debes implementar la lógica de conversión aquí, por ejemplo:
        EstiloMusical estilo = new EstiloMusical(cancionXML.getEstilo());
        Interprete interprete = new Interprete(cancionXML.getInterprete());
        Cancion cancion = new Cancion(cancionXML.getTitulo(), cancionXML.getURL(), estilo.getNombre(), interprete.getNombre());
        Controlador.INSTANCE.agregarCancion(cancion);
        return cancion;
    }

	@Override
	public Cancion obtenerCancionPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
