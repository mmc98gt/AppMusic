package umu.tds.AppMusic.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = new Usuario("Juan", "Pérez", "juan.perez@example.com", "juanperez", "1234", "01/01/2000");
	}

	@Test
	public void testRealizarPago() {
		assertFalse("El usuario debería no ser premium inicialmente", usuario.isPremium());
		usuario.realizarPago();
		assertTrue("El usuario debería ser premium después de realizar el pago", usuario.isPremium());
	}

	@Test
	public void testAddPlayList() {
		PlayList playlist = new PlayList("Mi Playlist");
		usuario.addPlayList(playlist);
		assertEquals("La playlist debe agregarse a la lista del usuario", Arrays.asList(playlist),
				usuario.getPlaylists());
	}

	@Test
	public void testSettersAndGetters() {
		usuario.setNombre("Ana");
		assertEquals("El nombre debe ser Ana", "Ana", usuario.getNombre());

		usuario.setApellidos("López");
		assertEquals("Los apellidos deben ser López", "López", usuario.getApellidos());

		usuario.setEmail("ana.lopez@example.com");
		assertEquals("El email debe ser ana.lopez@example.com", "ana.lopez@example.com", usuario.getEmail());

		usuario.setLogin("anita123");
		assertEquals("El login debe ser anita123", "anita123", usuario.getLogin());

		usuario.setPassword("contraseña");
		assertEquals("La contraseña debe ser contraseña", "contraseña", usuario.getPassword());

		usuario.setFechaNacimiento("02/02/2000");
		assertEquals("La fecha de nacimiento debe ser 02/02/2000", "02/02/2000", usuario.getFechaNacimiento());

		usuario.setPremium(true);
		assertTrue("El usuario debe ser premium", usuario.isPremium());

		PlayList playlist1 = new PlayList("Playlist 1");
		PlayList playlist2 = new PlayList("Playlist 2");
		usuario.setPlaylists(Arrays.asList(playlist1, playlist2));
		assertEquals("Las playlists deben ser [Playlist 1, Playlist 2]", Arrays.asList(playlist1, playlist2),
				usuario.getPlaylists());

		usuario.setId(123);
		assertEquals("El ID debe ser 123", 123, usuario.getId());
	}

	@Test
	public void testAddNullPlayList() {
		int initialSize = usuario.getPlaylists().size();
		usuario.addPlayList(null);
		assertEquals("Agregar una playlist nula no debe cambiar la lista de playlists", initialSize,
				usuario.getPlaylists().size());
	}

	@Test
	public void testUsuarioConstructorSinParametros() {
		Usuario usuarioSimple = new Usuario("Carlos");
		assertEquals("El nombre debe ser Carlos", "Carlos", usuarioSimple.getNombre());
		assertFalse("El usuario debería no ser premium inicialmente", usuarioSimple.isPremium());
		assertTrue("La lista de playlists debe ser inicializada y vacía", usuarioSimple.getPlaylists().isEmpty());
	}

	@Test
	public void testRemovePlayList() {
		PlayList playlist1 = new PlayList("Playlist 1");
		PlayList playlist2 = new PlayList("Playlist 2");
		usuario.addPlayList(playlist1);
		usuario.addPlayList(playlist2);
		usuario.removePlayList(playlist1);
		assertEquals("La playlist 1 debe ser eliminada de la lista", Arrays.asList(playlist2), usuario.getPlaylists());
	}

	@Test
	public void testAddDuplicatePlayList() {
		PlayList playlist = new PlayList("Mi Playlist");
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist);
		int expectedSize = 1;
		assertEquals("La lista de playlists no debe tener duplicados", expectedSize, usuario.getPlaylists().size());
	}

	@Test
	public void testSetPlaylistsToNull() {
		usuario.setPlaylists(null);
		assertNotNull("La lista de playlists no debe ser null después de establecerla a null", usuario.getPlaylists());
		assertTrue("La lista de playlists debe estar vacía después de establecerla a null",
				usuario.getPlaylists().isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLoginNull() {
		usuario.setLogin(null);
		fail("Establecer el login a null debería lanzar una IllegalArgumentException");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEmptyLogin() {
		usuario.setLogin("");
		fail("Establecer el login a cadena vacía debería lanzar una IllegalArgumentException");
	}

	@Test
	public void testSetNegativeId() {
		int negativeId = -1;
		try {
			usuario.setId(negativeId);
			fail("Establecer el ID a un valor negativo debería ser manejado adecuadamente");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testSetVeryHighId() {
		int veryHighId = Integer.MAX_VALUE;
		usuario.setId(veryHighId);
		assertEquals("Establecer el ID a un valor muy alto debería ser manejado adecuadamente", veryHighId,
				usuario.getId());
	}

}
