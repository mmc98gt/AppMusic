package umu.tds.AppMusic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
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
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PLAYLISTS = "playlists";

	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimientoStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String playlistID = servPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS);
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

		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimientoStr);
		usuario.setId(eUsuario.getId());
		usuario.setPlaylists(playlists);

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		String fechaNacimientoStr = usuario.getFechaNacimiento() != null ? usuario.getFechaNacimiento() : ""; // o
																												// alguna
																												// fecha
																												// por
																												// defecto

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, fechaNacimientoStr),
				new Propiedad(PLAYLISTS, obtenerIdPlaylist(usuario.getPlaylists())))));
		return eUsuario;
	}

	/**
	 * Crea un nuevo usuario en la base de datos.
	 * 
	 * @param usuario Usuario a crear.
	 */
	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	/**
	 * Elimina un usuario de la base de datos.
	 * 
	 * @param usuario Usuario a eliminar.
	 * @return Verdadero si se eliminó con éxito, falso de lo contrario.
	 */
	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Actualiza la información de un usuario en la base de datos.
	 * 
	 * @param usuario Usuario con la información actualizada.
	 */
	public void update(Usuario usuario) {
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
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals(PLAYLISTS)) {
				prop.setValor(obtenerIdPlaylist(usuario.getPlaylists()));
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	/**
	 * Recupera un usuario basado en su identificador.
	 * 
	 * @param id El identificador del usuario.
	 * @return El usuario correspondiente al identificador.
	 */
	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	/**
	 * Obtiene todos los usuarios de la base de datos.
	 * 
	 * @return Una lista de todos los usuarios.
	 */
	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
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
//TODO: es la misma funcion que update?????

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
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento());
			} else if (prop.getNombre().equals(PLAYLISTS)) {
				prop.setValor(obtenerIdPlaylist(usuario.getPlaylists()));
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

	private String obtenerIdPlaylist(List<PlayList> list) {
		return list.stream().map(song -> Integer.toString(song.getId())).collect(Collectors.joining(" "));
	}

}
