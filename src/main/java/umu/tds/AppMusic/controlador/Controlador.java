package umu.tds.AppMusic.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import umu.tds.AppMusic.dao.CancionDao;
import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.dao.PlayListDao;
import umu.tds.AppMusic.dao.RepositorioCanciones;
import umu.tds.AppMusic.dao.RepositorioPlayList;
import umu.tds.AppMusic.dao.RepositorioUsuarios;
import umu.tds.AppMusic.dao.UsuarioDao;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.CreadorPDF;
import umu.tds.AppMusic.modelo.Descuento;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Reproductor;
import umu.tds.AppMusic.modelo.Usuario;

/**
 * Controlador principal de la aplicación de música. Gestiona las operaciones de
 * usuario y la interacción con el repositorio de usuarios y canciones.
 */
public enum Controlador {
	INSTANCE;

	private Usuario usuarioActual;
	private FactoriaDao factoria;
	private PlayList playlistActual;
	private Cancion cancionActual;
	private int indexCancion;
	private List<Cancion> masReproducidas = new ArrayList<Cancion>();

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDao.getInstancia();
			Reproductor.INSTANCE.activar();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el usuario actualmente activo en la aplicación.
	 * 
	 * @return Usuario actual.
	 */
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	/**
	 * Comprueba si un usuario está registrado en el sistema.
	 * 
	 * @param login Nombre de usuario.
	 * @return true si el usuario está registrado, false en caso contrario.
	 */
	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	/**
	 * Realiza el proceso de inicio de sesión de un usuario.
	 * 
	 * @param nombre   Nombre de usuario.
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

	public boolean loginUsuarioGitHub(String nombre, String password) {
		// TODO: acepta cualquier usuario
		GitHub github;
		try {
			github = new GitHubBuilder().withPassword(nombre, password).build();
			System.out.println("¿Login válido?:" + github.isCredentialValid());
			if (github.isCredentialValid()) {
				GHUser user = github.getUser(nombre);
				System.out.println(user.getEmail());
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Registra un nuevo usuario en el sistema.
	 * 
	 * @param nombre          Nombre del usuario.
	 * @param apellidos       Apellidos del usuario.
	 * @param email           Correo electrónico del usuario.
	 * @param login           Nombre de usuario.
	 * @param password        Contraseña del usuario.
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 * @return true si el registro es exitoso, false en caso contrario.
	 */
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, boolean premium,
			String password, String fechaNacimiento) {
		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, premium, password, fechaNacimiento);

		UsuarioDao usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.agregarUsuario(usuario);

		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}

	/**
	 * Elimina un usuario del sistema.
	 * 
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
	 * 
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
	 * Verifica si una canción está incluida en alguna de las playlists del usuario
	 * actual.
	 * 
	 * @param cancion La canción a verificar.
	 * @return true si la canción es favorita (está en alguna playlist), false en
	 *         caso contrario.
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
	public List<Cancion> buscarCanciones(String interprete, String titulo, boolean esFavorita, String estilo) {
		List<Cancion> canciones = RepositorioCanciones.INSTANCE.findAllCanciones().stream()
				.filter(cancion -> (interprete == null || interprete.isEmpty()
						|| cancion.getInterprete().toLowerCase().contains(interprete.toLowerCase()))
						&& (titulo == null || titulo.isEmpty()
								|| cancion.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
						&& (!esFavorita || cancionEsFavorita(cancion))
						&& (estilo == null || cancion.getEstilo().equals(estilo)))
				.collect(Collectors.toList());
		PlayList busqueda = new PlayList("busqueda", canciones);
		playlistActual = busqueda;
		return canciones;
	}

	/**
	 * Obtiene todas las canciones favoritas del usuario actual.
	 * 
	 * @return Lista de todas las canciones favoritas del usuario actual.
	 */
	public List<Cancion> obtenerCancionesFavoritas() {
		if (usuarioActual == null) {
			return new ArrayList<>(); // Retorna una lista vacía si no hay usuario actual
		}
		return RepositorioCanciones.INSTANCE.findAllCanciones().stream().filter(this::cancionEsFavorita) // Filtra
																											// usando el
																											// método
																											// cancionEsFavorita
				.collect(Collectors.toList());
	}

	public boolean comprobarListaYaExiste(String nombrePlaylist) {
		return usuarioActual.comprobarListaYaExiste(nombrePlaylist);
	}

	public void crearPlaylist(String nombrePlaylist, List<Cancion> canciones) {
		PlayList playlist = new PlayList(nombrePlaylist, canciones);

		PlayListDao playlistDAO = factoria.getPlayListDAO();
		playlistDAO.agregarPlaylist(playlist);
		// playlistDAO.updatePlayList(playlist);

		RepositorioPlayList.INSTANCE.addPlaylist(playlist);
		addPlaylistToCurrentUser(playlist);
		// factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);

	}

	public void borrarListaCanciones(PlayList playlist) {
		if (usuarioActual.removePlayList(playlist)) {
			RepositorioPlayList.INSTANCE.deletePlaylist(playlist);
			playlistActual = null;
			usuarioActual.borrarPlaylist(playlist);
			factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);
		}

	}

	public List<PlayList> obtenerPlaylistsUsuario() {
		return usuarioActual.getPlaylists();
	}

	public PlayList obtenerPlaylist(int id) {
		return RepositorioPlayList.INSTANCE.obtenerPlaylist(id);
	}

	public double getDescuento(double precio, String opcionSeleccionada) {
		double total = 0;
		Set<Descuento> descuentos = Descuento.descuentos();
		for (Descuento d : descuentos) {
			if (d.getDescuento().equals(opcionSeleccionada)) {
				total = d.calcDescuento(precio);
			}
		}
		return total;
	}

	public void hacerPremium() {
		usuarioActual.realizarPago();
		factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);
	}

	public void salirPremium() {
		usuarioActual.setPremium(false);
		factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);
	}

	public Boolean comprobarDescuento(String opcionSeleccionada) {
		return usuarioActual.comprobarDescuento(opcionSeleccionada);
	}

	public void addCancionesToPlaylist(PlayList playlist, List<Cancion> canciones) {
		for (Cancion c : canciones) {
			playlist.addCancion(c);
		}
		RepositorioPlayList.INSTANCE.modificarPlaylist(playlist);
		factoria.getPlayListDAO().updatePlayList(playlist);
		usuarioActual.actualizarPlaylist(playlist);
		factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);

	}

	public void deleteCancionesToPlaylist(PlayList playlist, List<Cancion> canciones) {
		for (Cancion c : canciones) {
			playlist.borrarCancion(c);
		}
		RepositorioPlayList.INSTANCE.modificarPlaylist(playlist);
		factoria.getPlayListDAO().updatePlayList(playlist);
		usuarioActual.actualizarPlaylist(playlist);
		factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);

	}

	public PlayList obtenerPlaylistActual() {

		return playlistActual;
	}

	public void establecerPlaylistActual(PlayList playlistSeleccionada) {
		this.playlistActual = playlistSeleccionada;

	}

	public List<Cancion> getCancionesPlaylistPorNombre(String nombrePlaylist) {
		return usuarioActual.getCancionesPlaylistPorNombre(nombrePlaylist);

	}

	public boolean crearPDF() {
		CreadorPDF creador = new CreadorPDF();

		try {
			creador.crearPDF(usuarioActual);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void agregarCancion(Cancion cancion) {
		factoria.getCancionDAO().agregarCancion(cancion);

	}

	public Cancion obtenerCancionActual() {

		return cancionActual;
	}

	public void establecerCancionActual(Cancion cancion, int i) {
		this.cancionActual = cancion;
		this.indexCancion = i;

	}

	public void play() throws FileNotFoundException {
		if (cancionActual != null) {
			Reproductor.INSTANCE.play(cancionActual);
		}
	}

	public void stop() {
		if (cancionActual != null) {
			Reproductor.INSTANCE.stop();
		}
	}

	public void pause() {
		if (cancionActual != null) {
			Reproductor.INSTANCE.pauseCancion();
		}
	}

	public void siguiente() throws FileNotFoundException {
		if (playlistActual != null && indexCancion != -1) {
			int i = (indexCancion + 1) % playlistActual.getCanciones().size();
			Reproductor.INSTANCE.siguiente(playlistActual, i);
		}

	}

	public void anterior() throws FileNotFoundException {
		if (playlistActual != null && indexCancion != -1) {
			int i = (indexCancion - 1) % playlistActual.getCanciones().size();
			Reproductor.INSTANCE.siguiente(playlistActual, i);
		}

	}

	public void addReciente() {
		usuarioActual.anadirCancionReciente(cancionActual);
		RepositorioPlayList.INSTANCE.modificarPlaylist(usuarioActual.getRecientes());
		factoria.getPlayListDAO().updatePlayList(usuarioActual.getRecientes());
		factoria.getUsuarioDAO().actualizarUsuario(usuarioActual);
	}

	public List<Cancion> getRecientes() {
		return usuarioActual.getRecientes().getCanciones();
	}

	public List<Cancion> getMasReproducidas() {
		masReproducidas.clear();
		masReproducidas.addAll(
				factoria.getCancionDAO().obtenerTodasLasCanciones().stream().filter(c -> c.getNumReproducciones() != 0)
						.sorted(Comparator.comparing(Cancion::getNumReproducciones).reversed()).limit(10)
						.collect(Collectors.toList()));
		return masReproducidas;
	}

	public void addReproduccion(Cancion cancion) {
		cancion.addReproduccion();
		CancionDao cancionDAO = factoria.getCancionDAO();
		cancionDAO.actualizarCancion(cancion);
	}

	public List<String> obtenerEstilosMusicales() {
		List<Cancion> canciones = factoria.getCancionDAO().obtenerTodasLasCanciones();
		List<String> estilos = new LinkedList<String>();
		estilos.add("Estilo Musical");
		for (Cancion c : canciones) {
			estilos.add(c.getEstilo());
		}
		return estilos;
	}

}
