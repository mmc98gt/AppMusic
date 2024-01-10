package umu.tds.AppMusic.controlador;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.modelo.Cancion;

public enum RepositorioCanciones {
    INSTANCE;

    private FactoriaDao factoria;

    private HashMap<Integer, Cancion> cancionesPorID;
    private HashMap<String, Cancion> cancionesPorTitulo;

    private RepositorioCanciones() {
        cancionesPorID = new HashMap<>();
        cancionesPorTitulo = new HashMap<>();
        
        try {
            factoria = FactoriaDao.getInstancia();
            
            List<Cancion> listaCanciones = factoria.getCancionDAO().obtenerTodasLasCanciones();
            listaCanciones.addAll(factoria.getCancionXMLDao().obtenerTodasLasCanciones());
            for (Cancion cancion : listaCanciones) {
                cancionesPorID.put(cancion.getId(), cancion);
                cancionesPorTitulo.put(cancion.getTitulo(), cancion);
            }
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    public List<Cancion> findCanciones() throws DAOException {
        return new LinkedList<>(cancionesPorTitulo.values());
    }

    public Cancion findCancion(String titulo) {
        return cancionesPorTitulo.get(titulo);
    }

    public Cancion findCancion(int id) {
        return cancionesPorID.get(id);
    }

    public void addCancion(Cancion cancion) {
        cancionesPorID.put(cancion.getId(), cancion);
        cancionesPorTitulo.put(cancion.getTitulo(), cancion);
    }

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
