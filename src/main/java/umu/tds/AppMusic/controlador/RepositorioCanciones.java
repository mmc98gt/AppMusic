package umu.tds.AppMusic.controlador;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.modelo.Cancion;

/**
 * Repositorio para gestionar las canciones, permitiendo acceder, añadir y eliminar canciones.
 * Utiliza patrón Singleton para mantener una única instancia.
 */
public enum RepositorioCanciones {
    INSTANCE;

    private FactoriaDao factoria;
    private HashMap<Integer, Cancion> cancionesPorID;
    private HashMap<String, Cancion> cancionesPorTitulo;

    /**
     * Constructor privado para inicializar el repositorio.
     */
    private RepositorioCanciones() {
        cancionesPorID = new HashMap<>();
        cancionesPorTitulo = new HashMap<>();
        
        try {
            factoria = FactoriaDao.getInstancia();
            List<Cancion> listaCanciones = factoria.getCancionDAO().obtenerTodasLasCanciones();
            listaCanciones.clear();
            listaCanciones.addAll(factoria.getCancionXMLDao().obtenerTodasLasCanciones());
            for(Cancion c: listaCanciones) {
            	System.out.println(c.getTitulo() + " " + c.getId());
            }
            listaCanciones.forEach(cancion -> {
                cancionesPorID.put(cancion.getId(), cancion);
                cancionesPorTitulo.put(cancion.getTitulo(), cancion);
            });
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    /**
     * Encuentra canciones disponibles en el repositorio.
     * @return una lista de canciones.
     */
    public List<Cancion> findCanciones() throws DAOException {
        return new LinkedList<>(cancionesPorTitulo.values());
    }

    /**
     * Busca una canción por su título.
     * @param titulo el título de la canción.
     * @return la canción encontrada, o null si no existe.
     */
    public Cancion findCancion(String titulo) {
        return cancionesPorTitulo.get(titulo);
    }

    /**
     * Busca una canción por su identificador.
     * @param id el identificador de la canción.
     * @return la canción encontrada, o null si no existe.
     */
    public Cancion findCancion(int id) {
        return cancionesPorID.get(id);
    }

    /**
     * Añade una canción al repositorio.
     * @param cancion la canción a añadir.
     */
    public void addCancion(Cancion cancion) {
        cancionesPorID.put(cancion.getId(), cancion);
        cancionesPorTitulo.put(cancion.getTitulo(), cancion);
    }

    /**
     * Elimina una canción del repositorio.
     * @param cancion la canción a eliminar.
     */
    public void removeCancion(Cancion cancion) {
        cancionesPorID.remove(cancion.getId());
        cancionesPorTitulo.remove(cancion.getTitulo());
    }

    /**
     * Método para obtener todas las canciones disponibles.
     * @return una lista de todas las canciones.
     */
    public List<Cancion> findAllCanciones() {
        return new LinkedList<>(cancionesPorTitulo.values());
    }
}
