package umu.tds.AppMusic.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class TDSUsuarioDAO implements UsuarioDao {

	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PREMIUM = "premium";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PLAYLISTS = "playlists";
	private static final String RECIENTES = "recientes";

	private ServicioPersistencia servPersistencia;
	private FactoriaDao factoria;

	// private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		// dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			factoria = FactoriaDao.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimientoStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String playlistID = servPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS);
		String recientesStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES);
		int recientes = recientesStr != null ? Integer.parseInt(recientesStr) : -1;
		List<PlayList> playlists = new LinkedList<>();

		if (playlistID != null) {
			for (String c : playlistID.trim().split(" ")) {
				try {
					if (!c.equals("")) {
						PlayList playlist = FactoriaDao.getInstancia().getPlayListDAO()
								.obtenerPlaylistPorId(Integer.parseInt(c));
						if (playlist != null)
							playlists.add(playlist);
					}
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}

		}
		boolean prem = Boolean.parseBoolean(premium);
		Usuario usuario = new Usuario(nombre, apellidos, email, login, prem, password, fechaNacimientoStr);
		PlayList recent = Controlador.INSTANCE.obtenerPlaylist(recientes);
		if (recent != null) {
			usuario.setRecientesId(recientes);
			List<Cancion> rCanciones = recent.getCanciones();
			for (Cancion c : rCanciones)
				usuario.anadirCancionReciente(c);
		}

		usuario.setId(eUsuario.getId());
		usuario.setPlaylists(playlists);
		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {

		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		// Controlador.INSTANCE.addPlaylistToCurrentUser(usuario.getRecientes());

		factoria.getPlayListDAO().agregarPlaylist(usuario.getRecientes());
		String fechaNacimientoStr = usuario.getFechaNacimiento() != null ? usuario.getFechaNacimiento() : ""; // o
																												// alguna
																												// fecha
																												// por
																												// defecto

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), new Propiedad(PREMIUM, Boolean.toString(usuario.isPremium())),
				new Propiedad(PASSWORD, usuario.getPassword()), new Propiedad(FECHA_NACIMIENTO, fechaNacimientoStr),
				new Propiedad(PLAYLISTS, obtenerIdPlaylist(usuario.getPlaylists())),
				new Propiedad(RECIENTES, Integer.toString(usuario.getRecientes().getId())))));
		return eUsuario;
	}

	/**
	 * Agrega un nuevo usuario a la base de datos.
	 * 
	 * @param usuario El usuario a agregar.
	 */
	@Override
	public void agregarUsuario(Usuario usuario) {
		Entidad eUsuario = usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	/**
	 * Actualiza la información de un usuario existente.
	 * 
	 * @param usuario El usuario con la información actualizada.
	 */
	@Override
	public void actualizarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getLogin());
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(Boolean.toString(usuario.isPremium()));
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento());
			} else if (prop.getNombre().equals(PLAYLISTS)) {
				prop.setValor(obtenerIdPlaylist(usuario.getPlaylists()));
			} else if (prop.getNombre().equals(RECIENTES)) {
				prop.setValor(Integer.toString(usuario.getRecientes().getId()));
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}

	/**
	 * Elimina un usuario basado en su nombre.
	 * 
	 * @param nombre El nombre del usuario a eliminar.
	 */
	@Override
	public void eliminarUsuario(String nombre) {
		servPersistencia.recuperarEntidades(USUARIO).stream()
				.filter(eUsuario -> servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE).equals(nombre))
				.findFirst().ifPresent(servPersistencia::borrarEntidad);
	}

	/**
	 * Obtiene un usuario basado en su nombre.
	 * 
	 * @param nombre El nombre del usuario.
	 * @return El usuario con ese nombre, o null si no se encuentra.
	 */
	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre) {
		return servPersistencia.recuperarEntidades(USUARIO).stream()
				.filter(eUsuario -> servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE).equals(nombre))
				.map(this::entidadToUsuario).findFirst().orElse(null);
	}

	/**
	 * Obtiene todos los usuarios de la base de datos.
	 * 
	 * @return Una lista de todos los usuarios.
	 */
	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return servPersistencia.recuperarEntidades(USUARIO).stream().map(this::entidadToUsuario)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * 
	 * Elimina todos los usuarios de la base de datos.
	 * 
	 * @return Verdadero si todos los usuarios fueron eliminados con éxito, falso si
	 *         ocurrió algún error.
	 */
	public boolean eliminarTodosLosUsuarios() {
		try {
			// Recupera todas las entidades de tipo "Usuario"
			List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

			// Elimina cada entidad correspondiente a un usuario
			for (Entidad eUsuario : entidades) {
				if (!servPersistencia.borrarEntidad(eUsuario)) {
					// Si no se pudo eliminar alguna entidad, retorna falso
					return false;
				}
			}

			// Si todas las entidades fueron eliminadas correctamente, retorna verdadero
			return true;
		} catch (Exception e) {
			// En caso de excepción, imprime la pila de excepción y retorna falso
			e.printStackTrace();
			return false;
		}
	}

	private String obtenerIdPlaylist(List<PlayList> list) {
		return list.stream().map(song -> Integer.toString(song.getId())).collect(Collectors.joining(" "));
	}

}
