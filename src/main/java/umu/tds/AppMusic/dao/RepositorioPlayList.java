package umu.tds.AppMusic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.AppMusic.modelo.PlayList;

public enum RepositorioPlayList {
	INSTANCE;

	private FactoriaDao factoria;
	private Map<Integer, PlayList> playlist;

	private RepositorioPlayList() {
		playlist = new HashMap<>();

		try {
			factoria = FactoriaDao.getInstancia();
			List<PlayList> listaPlaylist = factoria.getPlayListDAO().obtenerTodasLasPlayList();
			listaPlaylist.forEach(lista -> {
				playlist.put(lista.getId(), lista);
			});
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	/**
	 * Almacena una playlist en memoria y persistencia. Si la playlist ya existe, el
	 * método no modifica nada.
	 *
	 * @param playlist la playlist a añadir.
	 */
	public void addPlaylist(PlayList playlist) {
		this.playlist.put(playlist.getId(), playlist);
	}

	/**
	 * Obtiene una playlist por su id.
	 *
	 * @param id el id de la playlist.
	 * @return la playlist con el id especificado.
	 */
	public PlayList obtenerPlaylist(int id) {
		return factoria.getPlayListDAO().obtenerPlaylistPorId(id);
	}

	/**
	 * Modifica una playlist en memoria.
	 *
	 * @param playlist la playlist a modificar.
	 */
	public void modificarPlaylist(PlayList playlist) {
		this.playlist.put(playlist.getId(), playlist);
	}

	/**
	 * Elimina una playlist en memoria.
	 *
	 * @param playlist la playlist a eliminar.
	 */
	public void deletePlaylist(PlayList playlist) {
		this.playlist.remove(playlist.getId());
	}

}
