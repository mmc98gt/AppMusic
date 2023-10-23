package umu.tds.AppMusic.servicio;

import java.util.List;

import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;

public interface UsuarioService {

    // Método para registrar un usuario
    boolean registrarUsuario(Usuario usuario);

    // Método para promover a un usuario a premium
    boolean hacerUsuarioPremium(String nombre);

    // Método para obtener la información de un usuario por nombre
    Usuario consultarUsuarioPorNombre(String nombre);

    // Método para obtener una lista de todos los usuarios
    List<Usuario> listarTodosLosUsuarios();

    // Método para eliminar un usuario
    boolean eliminarUsuario(String nombre);

    // Método para agregar una playlist al usuario
    boolean agregarPlaylistAUsuario(String nombreUsuario, PlayList playlist);
}

