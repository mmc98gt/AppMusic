package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.PlayList;

public interface PlayListDao {

	/**
	 * Inserta una nueva playlist en la base de datos.
	 * 
	 * @param playList La playlist a insertar.
	 * @return true si la inserción fue exitosa, false de lo contrario.
	 */
	void agregarPlaylist(PlayList playlist);

	/**
	 * Actualiza una playlist existente en la base de datos.
	 * 
	 * @param playList La playlist con los datos actualizados.
	 * @return true si la actualización fue exitosa, false de lo contrario.
	 */
	void updatePlayList(PlayList playList);

	/**
	 * Elimina una playlist de la base de datos.
	 * 
	 * @param nombre El nombre de la playlist a eliminar.
	 * @return true si la eliminación fue exitosa, false de lo contrario.
	 */
	void deletePlayList(PlayList playlist);

	PlayList obtenerPlaylistPorId(int parseInt);

	List<PlayList> obtenerTodasLasPlayList();

}
