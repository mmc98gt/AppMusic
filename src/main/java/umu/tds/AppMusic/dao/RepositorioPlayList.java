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
	 * @param playlist La playlist a añadir.
	 */
	public void addPlaylist(PlayList playlist) {

		this.playlist.put(playlist.getId(), playlist);
	}

	public PlayList obtenerPlaylist(int id) {
		return factoria.getPlayListDAO().obtenerPlaylistPorId(id);
	}

	public void modificarPlaylist(PlayList playlist) {
		this.playlist.put(playlist.getId(), playlist);
	}

	public void deletePlaylist(PlayList playlist) {
		this.playlist.remove(playlist.getId());

	}

}