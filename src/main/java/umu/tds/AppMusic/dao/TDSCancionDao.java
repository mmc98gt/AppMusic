package umu.tds.AppMusic.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;

//TODO añadir funcionalidad faltante... y comprobar que este bien
/**
 * Clase que implementa el Adaptador DAO concreto de Cancion para el tipo H2.
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

    // Métodos para convertir entre Entidad y Cancion, y viceversa...

    private Cancion entidadToCancion(Entidad eCancion) {
        String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
        String rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, RUTA_FICHERO);
        String numReproduccionesStr = servPersistencia.recuperarPropiedadEntidad(eCancion, NUM_REPRODUCCIONES);
        int numReproducciones = Integer.parseInt(numReproduccionesStr);

        // Aquí debes implementar la lógica para convertir los identificadores de estilo e intérprete
        // en objetos EstiloMusical e Interprete. Esto puede depender de cómo estén representados estos en tu base de datos.
        EstiloMusical estilo = new EstiloMusical(""); // Convertir de cadena/identificador a objeto EstiloMusical
        Interprete interprete = new Interprete(""); // Convertir de cadena/identificador a objeto Interprete

        Cancion cancion = new Cancion(titulo, rutaFichero, estilo, interprete);
        for (int i = 0; i < numReproducciones; i++) {
            cancion.incrementarReproducciones();
        }

        return cancion;
    }

    private Entidad cancionToEntidad(Cancion cancion) {
        Entidad eCancion = new Entidad();
        eCancion.setNombre(CANCION);

        // Aquí debes implementar la lógica para convertir los objetos EstiloMusical e Interprete
        // en una representación que pueda ser almacenada en la base de datos, como un identificador o cadena de texto.
        String estiloId = ""; // Convertir objeto EstiloMusical a cadena/identificador
        String interpreteId = ""; // Convertir objeto Interprete a cadena/identificador

        eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
            new Propiedad(TITULO, cancion.getTitulo()),
            new Propiedad(RUTA_FICHERO, cancion.getRutaFichero()),
            new Propiedad(NUM_REPRODUCCIONES, String.valueOf(cancion.getNumReproducciones())),
            new Propiedad(ESTILO_MUSICAL, estiloId),
            new Propiedad(INTERPRETE, interpreteId)
        )));
        return eCancion;
    }


    // Métodos CRUD para Cancion

    public void agregarCancion(Cancion cancion) {
        Entidad eCancion = this.cancionToEntidad(cancion);
        eCancion = servPersistencia.registrarEntidad(eCancion);
        // Se asume que la ID se establece automáticamente en la base de datos
    }

    public void actualizarCancion(Cancion cancion) {
        Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());

        for (Propiedad prop : eCancion.getPropiedades()) {
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
                    // Convertir estilo a cadena o identificador
                    break;
                case INTERPRETE:
                    // Convertir intérprete a cadena o identificador
                    break;
            }
            servPersistencia.modificarPropiedad(prop);
        }
    }

    public void eliminarCancion(Cancion cancion) {
        Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
        if (eCancion != null) {
            servPersistencia.borrarEntidad(eCancion);
        }
    }

    public Cancion obtenerCancionPorTitulo(String titulo) {
        List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);
        for (Entidad eCancion : entidades) {
            if (servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO).equals(titulo)) {
                return entidadToCancion(eCancion);
            }
        }
        return null;
    }

    public List<Cancion> obtenerTodasLasCanciones() {
        List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);
        List<Cancion> canciones = new LinkedList<>();
        for (Entidad eCancion : entidades) {
            canciones.add(entidadToCancion(eCancion));
        }
        return canciones;
    }


}
