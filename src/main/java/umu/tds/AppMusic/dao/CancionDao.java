package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.Cancion;

/**
 * Interfaz para operaciones CRUD de canciones.
 */
public interface CancionDao {

    /**
     * Agrega una nueva canción.
     *
     * @param cancion la canción a agregar.
     */
    void agregarCancion(Cancion cancion);

    /**
     * Actualiza una canción existente.
     *
     * @param cancion la canción a actualizar.
     */
    void actualizarCancion(Cancion cancion);

    /**
     * Elimina una canción.
     *
     * @param cancion la canción a eliminar.
     */
    void eliminarCancion(Cancion cancion);

    /**
     * Obtiene una canción por su título.
     *
     * @param titulo el título de la canción.
     * @return la canción con el título especificado.
     */
    Cancion obtenerCancionPorTitulo(String titulo);

    /**
     * Obtiene una canción por su id.
     *
     * @param id el id de la canción.
     * @return la canción con el id especificado.
     */
    Cancion obtenerCancionPorId(Integer id);

    /**
     * Obtiene todas las canciones.
     *
     * @return una lista de todas las canciones.
     */
    List<Cancion> obtenerTodasLasCanciones();

    /**
     * Borra todas las canciones.
     */
    void borrarCanciones();

}
