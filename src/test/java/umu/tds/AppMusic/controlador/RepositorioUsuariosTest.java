package umu.tds.AppMusic.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.modelo.Usuario;

public class RepositorioUsuariosTest {
	private RepositorioUsuarios repositorio;
	private Usuario usuarioTest;

	@Before
	public void setUp() {
		repositorio = RepositorioUsuarios.INSTANCE;
		usuarioTest = new Usuario("testUser", "Test", "test.user@example.com", "testuser", "testpass", "01/01/1990");
		usuarioTest.setId(999);
		repositorio.addUsuario(usuarioTest);
	}

	@Test
	public void testAddUsuario() {
		Usuario result = repositorio.findUsuario(usuarioTest.getLogin());
		assertNotNull("El usuario añadido debe ser encontrado por login", result);
		assertEquals("El usuario añadido debe coincidir con el recuperado", usuarioTest, result);
	}

	@Test
	public void testFindUsuarioById() {
		Usuario result = repositorio.findUsuario(usuarioTest.getId());
		assertNotNull("El usuario debe ser encontrado por ID", result);
		assertEquals("El usuario encontrado por ID debe ser el mismo que el añadido", usuarioTest, result);
	}

	@Test
	public void testFindUsuarioByLogin() {
		Usuario result = repositorio.findUsuario(usuarioTest.getLogin());
		assertNotNull("El usuario debe ser encontrado por login", result);
		assertEquals("El usuario encontrado por login debe ser el mismo que el añadido", usuarioTest, result);
	}

	@Test
	public void testFindUsuarios() throws DAOException {
		List<Usuario> usuarios = repositorio.findUsuarios();
		assertTrue("La lista de usuarios debe contener al usuario añadido", usuarios.contains(usuarioTest));
	}

	@Test
	public void testRemoveUsuario() {
		repositorio.removeUsuario(usuarioTest);
		Usuario resultById = repositorio.findUsuario(usuarioTest.getId());
		Usuario resultByLogin = repositorio.findUsuario(usuarioTest.getLogin());
		assertNull("El usuario no debe ser encontrado por ID después de ser eliminado", resultById);
		assertNull("El usuario no debe ser encontrado por login después de ser eliminado", resultByLogin);
	}

	@Test
	public void testFindUsuarioByNullLogin() {
		Usuario result = repositorio.findUsuario(null);
		assertNull("Buscar un usuario por login null debe devolver null", result);
	}

	@Test
	public void testFindUsuarioByNonExistingId() {
		int nonExistingId = Integer.MAX_VALUE;
		Usuario result = repositorio.findUsuario(nonExistingId);
		assertNull("Buscar un usuario por un ID no existente debe devolver null", result);
	}

	@Test
	public void testAddUsuarioWithNull() {
		Usuario nullUsuario = null;
		try {
			repositorio.addUsuario(nullUsuario);
			fail("Añadir un usuario null debería lanzar una excepción o fallar");
		} catch (Exception e) {
			assertNotNull("La excepción debe ser lanzada al intentar añadir un usuario null", e);
		}
	}

	@Test
	public void testRemoveNonExistingUsuario() {
		Usuario nonExistingUser = new Usuario("nonExisting", "User", "non.existing@example.com", "nonexisting", "pass",
				"01/01/2000");
		nonExistingUser.setId(888);
		repositorio.removeUsuario(nonExistingUser);
		Usuario result = repositorio.findUsuario(nonExistingUser.getId());
		assertNull("Eliminar un usuario no existente no debe afectar al repositorio", result);
	}

	@After
	public void tearDown() {
		repositorio.removeUsuario(usuarioTest);
	}
}
