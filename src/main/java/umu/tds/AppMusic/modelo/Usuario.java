package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

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

	// Constructor existente
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.premium = false; // Asumiendo que por defecto no es premium
		this.playlists = new ArrayList<>();
	}

	// Nuevo constructor
	public Usuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.premium = false;
		this.playlists = new ArrayList<>();
	}

	// Métodos
	public void realizarPago() {
		// Lógica para realizar el pago y convertirse en usuario premium
		this.premium = true; // Por ejemplo, puedes cambiar el estado a premium después del pago
	}

	public void addPlayList(PlayList playList) {
		if (playList != null) {
			this.playlists.add(playList);
		}
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
		this.playlists = playlists;
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
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
