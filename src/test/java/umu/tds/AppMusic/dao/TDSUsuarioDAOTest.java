package umu.tds.AppMusic.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import umu.tds.AppMusic.modelo.Usuario;

public class TDSUsuarioDAOTest {

    private FactoriaDao factoria;
    
    @Before
    public void setUp() {
		try {
			factoria = FactoriaDao.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testAgregarUsuario() {
        Usuario usuario = new Usuario("nombreTest2", "apellidosTest2", "emailTest2@test.com", "loginTest2",false, "passwordTest2", "02/02/2000");
		UsuarioDao usuarioDAO = factoria.getUsuarioDAO();
        usuarioDAO.agregarUsuario(usuario);

        Usuario retrievedUser = usuarioDAO.obtenerUsuarioPorNombre("nombreTest2");
        assertEquals("nombreTest2", retrievedUser.getNombre());
    }

    @Test
    public void testActualizarUsuario() {
        UsuarioDao usuarioDAO = factoria.getUsuarioDAO();

        Usuario usuario = new Usuario("nombreTest3", "apellidosTest3", "emailTest3@test.com", "loginTest3",false,"passwordTest3", "03/03/2000");
        usuarioDAO.agregarUsuario(usuario);

        usuario.setNombre("nombreTest3Updated");
        usuarioDAO.actualizarUsuario(usuario);

        Usuario updatedUser = usuarioDAO.obtenerUsuarioPorNombre("nombreTest3Updated");
        assertEquals("nombreTest3Updated", updatedUser.getNombre());
    }


    @Test
    public void testEliminarUsuario() {
        UsuarioDao usuarioDAO = factoria.getUsuarioDAO();

        Usuario usuario = new Usuario("nombreTest4", "apellidosTest4", "emailTest4@test.com", "loginTest4",false, "passwordTest4", "04/04/2000");
        usuarioDAO.agregarUsuario(usuario);

        usuarioDAO.eliminarUsuario("nombreTest4");

        Usuario deletedUser = usuarioDAO.obtenerUsuarioPorNombre("nombreTest4");
        assertNull(deletedUser);
    }

    @Test
    public void testObtenerUsuarioPorNombre() {
        UsuarioDao usuarioDAO = factoria.getUsuarioDAO();

        Usuario usuario = new Usuario("nombreTest5", "apellidosTest5", "emailTest5@test.com", "loginTest5",false, "passwordTest5", "05/05/2000");
        usuarioDAO.agregarUsuario(usuario);

        Usuario retrievedUser = usuarioDAO.obtenerUsuarioPorNombre("nombreTest5");
        assertEquals("nombreTest5", retrievedUser.getNombre());
    }


    @Test
    public void testObtenerTodosLosUsuarios() {
        UsuarioDao usuarioDAO = factoria.getUsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

        assertTrue(usuarios.size()>0);
    }


}
