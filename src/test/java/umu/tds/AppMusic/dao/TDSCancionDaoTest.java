package umu.tds.AppMusic.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;

public class TDSCancionDaoTest {

    private TDSCancionDao cancionDao;
    private FactoriaDao factoria;
    
    @Before
    public void setUp() {
        try {
            factoria = FactoriaDao.getInstancia();
            cancionDao = factoria.getCancionDAO();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAgregarCancion() {
        EstiloMusical estilo = new EstiloMusical("Rock");
        Interprete interprete = new Interprete("Interprete Test");
        Cancion cancion = new Cancion("Titulo Test", "Ruta Test", estilo.getNombre(), interprete.getNombre());
        cancionDao.agregarCancion(cancion);

        Cancion retrievedCancion = cancionDao.obtenerCancionPorTitulo("Titulo Test");
        assertEquals("Titulo Test", retrievedCancion.getTitulo());
    }

    @Test
    public void testActualizarCancion() {
        EstiloMusical estilo = new EstiloMusical("Pop");
        Interprete interprete = new Interprete("Interprete Test");
        Cancion cancion = new Cancion("Titulo Test2", "Ruta Test", estilo.getNombre(), interprete.getNombre());
        cancionDao.agregarCancion(cancion);

        cancion.setRutaFichero("Ruta Actualizada");
        cancionDao.actualizarCancion(cancion);

        Cancion updatedCancion = cancionDao.obtenerCancionPorTitulo("Titulo Test2");
        assertEquals("Ruta Actualizada", updatedCancion.getRutaFichero());
    }

    @Test
    public void testEliminarCancion() {
        EstiloMusical estilo = new EstiloMusical("Jazz");
        Interprete interprete = new Interprete("Interprete Test");
        Cancion cancion = new Cancion("Titulo Test3", "Ruta Test", estilo.getNombre(), interprete.getNombre());
        cancionDao.agregarCancion(cancion);

        cancionDao.eliminarCancion(cancion);

        Cancion deletedCancion = cancionDao.obtenerCancionPorTitulo("Titulo Test3");
        assertNull(deletedCancion);
    }

    @Test
    public void testObtenerTodasLasCanciones() {
        List<Cancion> canciones = cancionDao.obtenerTodasLasCanciones();
        assertTrue(canciones.size() > 0);
    }
}
