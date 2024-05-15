package umu.tds.AppMusic.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppMusic.modelo.Cancion;

/**
 * Repositorio para gestionar las canciones, permitiendo acceder, añadir y
 * eliminar canciones. Utiliza patrón Singleton para mantener una única
 * instancia.
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

			listaCanciones.forEach(cancion -> {
				cancionesPorID.put(cancion.getId(), cancion);
				cancionesPorTitulo.put(cancion.getTitulo(), cancion);
			});
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	/**
	 * Método para obtener todas las canciones disponibles.
	 * 
	 * @return una lista de todas las canciones.
	 */
	public List<Cancion> findAllCanciones() {
		return new LinkedList<>(cancionesPorTitulo.values());
	}
}
