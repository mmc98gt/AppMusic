package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.PlayList;

/**
 * Interfaz para operaciones CRUD de playlists.
 */
public interface PlayListDao {

    /**
     * Inserta una nueva playlist en la base de datos.
     *
     * @param playlist la playlist a insertar.
     */
    void agregarPlaylist(PlayList playlist);

    /**
     * Actualiza una playlist existente en la base de datos.
     *
     * @param playList la playlist con los datos actualizados.
     */
    void updatePlayList(PlayList playList);

    /**
     * Elimina una playlist de la base de datos.
     *
     * @param playlist la playlist a eliminar.
     */
    void deletePlayList(PlayList playlist);

    /**
     * Obtiene una playlist por su id.
     *
     * @param id el id de la playlist.
     * @return la playlist con el id especificado.
     */
    PlayList obtenerPlaylistPorId(int id);

    /**
     * Obtiene todas las playlists.
     *
     * @return una lista de todas las playlists.
     */
    List<PlayList> obtenerTodasLasPlayList();
}

