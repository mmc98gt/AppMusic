package umu.tds.AppMusic.controlador;

import java.util.Date;

import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.dao.UsuarioDao;
import umu.tds.AppMusic.modelo.Usuario;


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

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			Date fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre);

		UsuarioDao usuarioDAO = factoria
				.getUsuarioDao(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.agregarUsuario(usuario);

		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDao usuarioDAO = factoria.getUsuarioDao(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.eliminarUsuario(usuario.getNombre());

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	}
}

