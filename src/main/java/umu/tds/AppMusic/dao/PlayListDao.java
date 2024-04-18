package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.PlayList;

public interface PlayListDao {

    /**
     * Inserta una nueva playlist en la base de datos.
     * @param playList La playlist a insertar.
     * @return true si la inserción fue exitosa, false de lo contrario.
     */
	void agregarPlaylist(PlayList playlist);

    /**
     * Recupera una playlist de la base de datos según su nombre.
     * @param nombre El nombre de la playlist.
     * @return La playlist si se encontró, null de lo contrario.
     */
    PlayList getPlayListByName(String nombre);

    /**
     * Recupera todas las playlists de la base de datos.
     * @return Una lista con todas las playlists.
     */
    List<PlayList> getAllPlayLists();

    /**
     * Actualiza una playlist existente en la base de datos.
     * @param playList La playlist con los datos actualizados.
     * @return true si la actualización fue exitosa, false de lo contrario.
     */
    void updatePlayList(PlayList playList);

    /**
     * Elimina una playlist de la base de datos.
     * @param nombre El nombre de la playlist a eliminar.
     * @return true si la eliminación fue exitosa, false de lo contrario.
     */
    void deletePlayList(PlayList playlist);

	
}

