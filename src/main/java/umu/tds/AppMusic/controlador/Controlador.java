package umu.tds.AppMusic.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.dao.UsuarioDao;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;

/**
 * Controlador principal de la aplicación de música.
 * Gestiona las operaciones de usuario y la interacción con el repositorio de usuarios y canciones.
 */
public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDao factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDao.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el usuario actualmente activo en la aplicación.
	 * @return Usuario actual.
	 */
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	/**
	 * Comprueba si un usuario está registrado en el sistema.
	 * @param login Nombre de usuario.
	 * @return true si el usuario está registrado, false en caso contrario.
	 */
	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	/**
	 * Realiza el proceso de inicio de sesión de un usuario.
	 * @param nombre Nombre de usuario.
	 * @param password Contraseña del usuario.
	 * @return true si el inicio de sesión es exitoso, false en caso contrario.
	 */
	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	/**
	 * Registra un nuevo usuario en el sistema.
	 * @param nombre Nombre del usuario.
	 * @param apellidos Apellidos del usuario.
	 * @param email Correo electrónico del usuario.
	 * @param login Nombre de usuario.
	 * @param password Contraseña del usuario.
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 * @return true si el registro es exitoso, false en caso contrario.
	 */
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {
		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre,apellidos,email,login,password,fechaNacimiento);

		UsuarioDao usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.agregarUsuario(usuario);

		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}

	/**
	 * Elimina un usuario del sistema.
	 * @param usuario Usuario a eliminar.
	 * @return true si el usuario se eliminó correctamente, false en caso contrario.
	 */
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDao usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.eliminarUsuario(usuario.getNombre());

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	}
	
    /**
     * Añade una playlist al usuario actual.
     * @param playList La playlist a añadir.
     * @return true si se añade correctamente, false en caso contrario.
     */
    public boolean addPlaylistToCurrentUser(PlayList playList) {
        if (usuarioActual == null || playList == null) {
            return false;
        }

        usuarioActual.addPlayList(playList);

        UsuarioDao usuarioDAO = factoria.getUsuarioDAO();
        usuarioDAO.actualizarUsuario(usuarioActual);

        return true;
    }
    
    /**
     * Verifica si una canción está incluida en alguna de las playlists del usuario actual.
     * @param cancion La canción a verificar.
     * @return true si la canción es favorita (está en alguna playlist), false en caso contrario.
     */
    private boolean cancionEsFavorita(Cancion cancion) {
        if (usuarioActual == null || cancion == null) {
            return false;
        }
        return cancion.getEsFavorita();
    }

    /**
     * Busca canciones en el repositorio basándose en varios criterios.
     * 
     * @param interprete Nombre del intérprete de la canción.
     * @param titulo     Título de la canción.
     * @param esFavorita Indica si la canción es favorita o no.
     * @param estilo     Estilo musical de la canción.
     * @return Lista de canciones que coinciden con los criterios.
     */
    public List<Cancion> buscarCanciones(String interprete, String titulo, boolean esFavorita, EstiloMusical estilo) {
        return RepositorioCanciones.INSTANCE.findAllCanciones().stream()
                .filter(cancion -> (interprete == null || interprete.isEmpty() || cancion.getInterprete().getNombre().toLowerCase().contains(interprete.toLowerCase()))
                        && (titulo == null || titulo.isEmpty() || cancion.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                        && (!esFavorita || cancionEsFavorita(cancion))
                        && (estilo == null || cancion.getEstilo().equals(estilo)))
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene todas las canciones favoritas del usuario actual.
     * @return Lista de todas las canciones favoritas del usuario actual.
     */
    public List<Cancion> obtenerCancionesFavoritas() {
        if (usuarioActual == null) {
            return new ArrayList<>(); // Retorna una lista vacía si no hay usuario actual
        }
        return RepositorioCanciones.INSTANCE.findAllCanciones().stream()
                .filter(this::cancionEsFavorita) // Filtra usando el método cancionEsFavorita
                .collect(Collectors.toList());
    }
    
    
    public boolean comprobarListaYaExiste(String nombre) {
    	return usuarioActual.comprobarListaYaExiste(nombre);
    }

}
