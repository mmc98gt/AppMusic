package umu.tds.AppMusic.dao;

import java.util.List;
import java.util.stream.Collectors;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;
import umu.tds.AppMusic.cargadorCanciones.MapperCancionesXMLtoJava;
import umu.tds.AppMusic.cargadorCanciones.CancionXml;
import umu.tds.AppMusic.cargadorCanciones.Canciones;

public class TDSCancionXMLDao implements CancionDao {

    private static final String PATH_XML = "xml/canciones.xml";

    @Override
    public void agregarCancion(Cancion cancion) {
        // Implementación depende de cómo se maneje la adición de datos al XML.
        // Normalmente, la adición se haría a una base de datos y no directamente al XML.
    }

    @Override
    public void actualizarCancion(Cancion cancion) {
        // Similar a agregarCancion, la actualización normalmente no se hace directamente en un archivo XML.
    }

    @Override
    public void eliminarCancion(Cancion cancion) {
        // La eliminación de datos en un archivo XML es poco común y depende de la implementación específica.
    }

    @Override
    public Cancion obtenerCancionPorTitulo(String titulo) {
        Canciones cancionesXML = MapperCancionesXMLtoJava.cargarCanciones(PATH_XML);
        for (CancionXml cancionXML : cancionesXML.getCancion()) {
            if (cancionXML.getTitulo().equalsIgnoreCase(titulo)) {
                return convertirCancionXMLaCancion(cancionXML);
            }
        }
        return null;
    }

    @Override
    public List<Cancion> obtenerTodasLasCanciones() {
        Canciones cancionesXML = MapperCancionesXMLtoJava.cargarCanciones(PATH_XML);
        return cancionesXML.getCancion().stream()
                .map(this::convertirCancionXMLaCancion)
                .collect(Collectors.toList());
    }

    private Cancion convertirCancionXMLaCancion(CancionXml cancionXML) {
        // Debes implementar la lógica de conversión aquí, por ejemplo:
        EstiloMusical estilo = new EstiloMusical(cancionXML.getEstilo());
        Interprete interprete = new Interprete(cancionXML.getInterprete());
        return new Cancion(cancionXML.getTitulo(), cancionXML.getURL(), estilo, interprete);
    }
}
