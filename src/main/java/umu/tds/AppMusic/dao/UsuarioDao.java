package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.Usuario;

public interface UsuarioDao {

    // Método para agregar un usuario
    void agregarUsuario(Usuario usuario);

    // Método para actualizar un usuario
    void actualizarUsuario(Usuario usuario);

    // Método para eliminar un usuario por nombre
    void eliminarUsuario(String nombre);

    // Método para obtener un usuario por nombre
    Usuario obtenerUsuarioPorNombre(String nombre);

    // Método para obtener todos los usuarios
    List<Usuario> obtenerTodosLosUsuarios();
}

