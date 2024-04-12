package umu.tds.AppMusic.controlador;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.modelo.Usuario;

/**
 * Repositorio para la gestión de usuarios, permitiendo acceder, añadir y eliminar usuarios.
 * Utiliza el patrón Singleton para mantener una única instancia.
 */
public enum RepositorioUsuarios {
    INSTANCE;

    private FactoriaDao factoria;
    private HashMap<Integer, Usuario> usuariosPorID;
    private HashMap<String, Usuario> usuariosPorLogin;

    /**
     * Constructor privado para inicializar el repositorio de usuarios.
     */
    private RepositorioUsuarios() {
        usuariosPorID = new HashMap<>();
        usuariosPorLogin = new HashMap<>();

        try {
            factoria = FactoriaDao.getInstancia();
            List<Usuario> listausuarios = factoria.getUsuarioDAO().obtenerTodosLosUsuarios();
            listausuarios.forEach(usuario -> {
                usuariosPorID.put(usuario.getId(), usuario);
                usuariosPorLogin.put(usuario.getLogin(), usuario);
            });
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    /**
     * Encuentra todos los usuarios disponibles en el repositorio.
     * @return una lista de todos los usuarios.
     * @throws DAOException si hay un error al acceder a los datos.
     */
    public List<Usuario> findUsuarios() throws DAOException {
        return new LinkedList<>(usuariosPorLogin.values());
    }

    /**
     * Busca un usuario por su login.
     * @param login el login del usuario.
     * @return el usuario si se encuentra, null en caso contrario.
     */
    public Usuario findUsuario(String login) {
        if (login == null) {
            return null;
        }
        return usuariosPorLogin.get(login);
    }

    /**
     * Busca un usuario por su identificador.
     * @param id el identificador del usuario.
     * @return el usuario si se encuentra, null en caso contrario.
     */
    public Usuario findUsuario(int id) {
        return usuariosPorID.get(id);
    }

    /**
     * Añade un nuevo usuario al repositorio.
     * @param usuario el usuario a añadir.
     */
    public void addUsuario(Usuario usuario) {
        usuariosPorID.put(usuario.getId(), usuario);
        usuariosPorLogin.put(usuario.getLogin(), usuario);
    }

    /**
     * Elimina un usuario del repositorio.
     * @param usuario el usuario a eliminar.
     */
    public void removeUsuario(Usuario usuario) {
        usuariosPorID.remove(usuario.getId());
        usuariosPorLogin.remove(usuario.getLogin());
    }
    
    public void modificarUsuario(Usuario usuario) {
    	//TODO hacer con java8, modificar si ha cambiado algun atributo, pj: premium
    	
    }
}
