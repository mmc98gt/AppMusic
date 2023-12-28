package ficherosAyuda;

import umu.tds.AppMusic.dao.TDSCancionDao;
import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import umu.tds.AppMusic.modelo.Interprete;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class CancionesHelper {

    private static final String RUTA_CANCION = "Canciones/"; // Ruta de la carpeta de canciones por defecto
    private TDSCancionDao cancionDao;
    
    public CancionesHelper() throws DAOException {
        // Inicialización del DAO
        FactoriaDao factoria = FactoriaDao.getInstancia();
        cancionDao = factoria.getCancionDAO();
    }

    public void anadirCancion(String titulo, String estilo, String interprete) {
        // Crear una nueva canción y añadirla a la base de datos
        EstiloMusical estiloMusical = new EstiloMusical(estilo);
        Interprete interpreteObj = new Interprete(interprete);
        Cancion cancion = new Cancion(titulo, RUTA_CANCION + titulo + ".mp3", estiloMusical, interpreteObj); // Asume que el nombre del archivo es el título con extensión .mp3
        cancionDao.agregarCancion(cancion);
    }

    public void listarCanciones() {
        // Listar todas las canciones en la base de datos
        List<Cancion> canciones = cancionDao.obtenerTodasLasCanciones();
        for (int i = 0; i < canciones.size(); i++) {
            System.out.println((i + 1) + ". " + canciones.get(i).getTitulo());
        }
    }

    public void borrarCancion(Scanner scanner) {
        // Mostrar canciones y permitir al usuario seleccionar una para borrar
        listarCanciones();
        System.out.println("Seleccione el número de la canción para borrar:");
			int opcion = scanner.nextInt();

			List<Cancion> canciones = cancionDao.obtenerTodasLasCanciones();
			if(opcion > 0 && opcion <= canciones.size()) {
			    cancionDao.eliminarCancion(canciones.get(opcion - 1));
			} else {
			    System.out.println("Número de canción inválido");
			}
    }
    
    public void anadirCancionCarpeta(Scanner scanner) {
        File carpeta = new File(RUTA_CANCION);
        File[] listaCanciones = carpeta.listFiles();
        
        if (listaCanciones == null || listaCanciones.length == 0) {
            System.out.println("No hay canciones en la carpeta.");
            return;
        }

        System.out.println("Seleccione la canción que desea añadir:");
        for (int i = 0; i < listaCanciones.length; i++) {
            if (listaCanciones[i].isFile()) {
                System.out.println((i + 1) + ". " + listaCanciones[i].getName());
            }
        }

        int eleccion = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea

        if (eleccion < 1 || eleccion > listaCanciones.length) {
            System.out.println("Selección no válida.");
            return;
        }

        File cancionElegida = listaCanciones[eleccion - 1];
        System.out.println("Introduce el estilo musical de la canción:");
        String estilo = scanner.nextLine();
        System.out.println("Introduce el nombre del intérprete:");
        String interprete = scanner.nextLine();

        // Aquí puedes agregar lógica adicional para determinar el título de la canción
        String titulo = cancionElegida.getName().replace(".mp3", "");

        EstiloMusical estiloMusical = new EstiloMusical(estilo);
        Interprete interpreteObj = new Interprete(interprete);
        Cancion cancion = new Cancion(titulo, RUTA_CANCION + cancionElegida.getName(), estiloMusical, interpreteObj);
        cancionDao.agregarCancion(cancion);
        System.out.println("Canción agregada con éxito: " + titulo);
    }
    
    public void borrarTodas() {
        List<Cancion> canciones = cancionDao.obtenerTodasLasCanciones();
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones para borrar.");
            return;
        }

        for (Cancion cancion : canciones) {
            cancionDao.eliminarCancion(cancion);
        }

        System.out.println("Todas las canciones han sido eliminadas.");
    }

}
