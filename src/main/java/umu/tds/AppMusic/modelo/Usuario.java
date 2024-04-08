package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa a un usuario de la aplicación, incluyendo información personal y de acceso,
 * estado de suscripción y listas de reproducción.
 */
public class Usuario {
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	private boolean premium;
	private List<PlayList> playlists;

	/**
	 * Constructor para crear un usuario solo con su nombre.
	 * @param nombre El nombre del usuario.
	 */
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.premium = false; // Por defecto, el usuario no es premium
		this.playlists = new ArrayList<>();
	}

	/**
	 * Constructor para crear un usuario con información completa.
	 * @param nombre El nombre del usuario.
	 * @param apellidos Los apellidos del usuario.
	 * @param email El correo electrónico del usuario.
	 * @param login El nombre de usuario para iniciar sesión.
	 * @param password La contraseña del usuario.
	 * @param fechaNacimiento La fecha de nacimiento del usuario.
	 */
	public Usuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.premium = false; // Por defecto, el usuario no es premium
		this.playlists = new ArrayList<>();
	}

	/**
	 * Realiza el proceso para convertir al usuario en premium.
	 */
	public void realizarPago() {
		this.premium = true; // Cambiar el estado a premium después del pago
	}

	/**
	 * Añade una playlist a la lista de playlists del usuario.
	 * @param playList La playlist a añadir.
	 */
	public void addPlayList(PlayList playList) {
		if (playList != null && !this.playlists.contains(playList)) {
			this.playlists.add(playList);
		}
	}

	/**
	 * Elimina una playlist de la lista de playlists del usuario.
	 * @param playList La playlist a eliminar.
	 */
	public void removePlayList(PlayList playList) {
		this.playlists.remove(playList);
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public List<PlayList> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlayList> playlists) {
		if (playlists == null) {
			this.playlists = new ArrayList<>();
		} else {
			this.playlists = playlists;
		}
	}

	public boolean comprobarListaYaExiste(String nombre) {
		for (PlayList playlist : getPlaylists()) {
			if (playlist.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
	    if (id < 0) {
	        throw new IllegalArgumentException("El ID no puede ser negativo");
	    }
	    this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login == null || login.trim().isEmpty()) {
			throw new IllegalArgumentException("El login no puede ser null o vacío");
		}
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
