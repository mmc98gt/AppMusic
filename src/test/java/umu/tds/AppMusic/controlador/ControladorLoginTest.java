package umu.tds.AppMusic.controlador;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import umu.tds.AppMusic.dao.RepositorioUsuarios;
import umu.tds.AppMusic.modelo.Usuario;
import umu.tds.AppMusic.utils.PasswordUtils;

/**
 * Tests for the login functionality using hashed passwords.
 */
public class ControladorLoginTest {
    private Controlador controlador;

    @Before
    public void setUp() {
        controlador = Controlador.INSTANCE;
        // create and register a user directly in repository for testing
        String hashed = PasswordUtils.hashPassword("secret");
        Usuario user = new Usuario("Test", "User", "test@example.com", "tester", false, hashed, "01/01/2000");
        user.setId(5000);
        RepositorioUsuarios.INSTANCE.addUsuario(user);
    }

    @Test
    public void testLoginSuccess() {
        boolean result = controlador.loginUsuario("tester", "secret");
        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        boolean result = controlador.loginUsuario("tester", "wrong");
        assertFalse(result);
    }
}
