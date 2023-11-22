package umu.tds.AppMusic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
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

	    Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimientoStr);
	    usuario.setId(eUsuario.getId());

	    return usuario;
	}


	private Entidad usuarioToEntidad(Usuario usuario) {
	    Entidad eUsuario = new Entidad();
	    eUsuario.setNombre(USUARIO);

	    String fechaNacimientoStr = usuario.getFechaNacimiento() != null 
	                                 ? usuario.getFechaNacimiento() 
	                                 : ""; // o alguna fecha por defecto

	    eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
	            new Propiedad(NOMBRE, usuario.getNombre()),
	            new Propiedad(APELLIDOS, usuario.getApellidos()),
	            new Propiedad(EMAIL, usuario.getEmail()),
	            new Propiedad(LOGIN, usuario.getLogin()),
	            new Propiedad(PASSWORD, usuario.getPassword()),
	            new Propiedad(FECHA_NACIMIENTO, fechaNacimientoStr))));
	    return eUsuario;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
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
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		   Entidad eUsuario = usuarioToEntidad(usuario);
		    eUsuario = servPersistencia.registrarEntidad(eUsuario);
		    usuario.setId(eUsuario.getId());
	}

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
		        } else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
		        	prop.setValor(usuario.getFechaNacimiento());
		        }
		        servPersistencia.modificarPropiedad(prop);
		    }		
	}

	@Override
	public void eliminarUsuario(String nombre) {
	    // Recuperar entidad usuario basado en el nombre
	    List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
	    for (Entidad eUsuario : entidades) {
	        if (servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE).equals(nombre)) {
	            servPersistencia.borrarEntidad(eUsuario);
	            break;
	        }
	    }		
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre) {
	    // Recuperar entidad usuario basado en el nombre
	    List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
	    for (Entidad eUsuario : entidades) {
	        if (servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE).equals(nombre)) {
	            return entidadToUsuario(eUsuario);
	        }
	    }
	    return null; // Si no se encuentra el usuario
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
	    List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
	    List<Usuario> usuarios = new LinkedList<>();
	    for (Entidad eUsuario : entidades) {
	        usuarios.add(entidadToUsuario(eUsuario));
	    }
	    return usuarios;
	}

}
