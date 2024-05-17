package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.Usuario;

/**
 * Interfaz para operaciones CRUD de usuarios.
 */
public interface UsuarioDao {

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuario el usuario a agregar.
     */
    void agregarUsuario(Usuario usuario);

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario el usuario a actualizar.
     */
    void actualizarUsuario(Usuario usuario);

    /**
     * Elimina un usuario por su nombre.
     *
     * @param nombre el nombre del usuario a eliminar.
     */
    void eliminarUsuario(String nombre);

    /**
     * Obtiene un usuario por su nombre.
     *
     * @param nombre el nombre del usuario.
     * @return el usuario con el nombre especificado.
     */
    Usuario obtenerUsuarioPorNombre(String nombre);

    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios.
     */
    List<Usuario> obtenerTodosLosUsuarios();
}

