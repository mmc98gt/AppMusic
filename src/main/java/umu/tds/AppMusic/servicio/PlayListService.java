package umu.tds.AppMusic.servicio;

import java.util.List;

import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;

public interface PlayListService {

    /**
     * Crea y guarda una nueva playlist.
     * @param playList La playlist a guardar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    boolean createPlayList(PlayList playList);

    /**
     * Busca una playlist por su nombre.
     * @param nombre El nombre de la playlist a buscar.
     * @return La playlist encontrada o null si no existe.
     */
    PlayList findPlayListByName(String nombre);

    /**
     * Obtiene todas las playlists disponibles.
     * @return Una lista con todas las playlists.
     */
    List<PlayList> findAllPlayLists();

    /**
     * Modifica una playlist existente.
     * @param playList La playlist con la información actualizada.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    boolean modifyPlayList(PlayList playList);

    /**
     * Elimina una playlist según su nombre.
     * @param nombre El nombre de la playlist a eliminar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    boolean removePlayList(String nombre);
    
    /**
     * Agrega una canción a una playlist existente.
     * @param playList La playlist a la que se agregará la canción.
     * @param cancion La canción a agregar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    boolean addSongToPlayList(PlayList playList, Cancion cancion);

    /**
     * Elimina una canción de una playlist existente.
     * @param playList La playlist de la cual se eliminará la canción.
     * @param cancion La canción a eliminar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    boolean removeSongFromPlayList(PlayList playList, Cancion cancion);
}

