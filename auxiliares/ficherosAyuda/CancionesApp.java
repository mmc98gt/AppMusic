package ficherosAyuda;

import java.util.Scanner;
import umu.tds.AppMusic.dao.DAOException;

public class CancionesApp {

    public static void main(String[] args) throws DAOException {
        CancionesHelper helper = new CancionesHelper();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Añadir Canción Manualmente");
            System.out.println("2. Añadir Canción desde Carpeta");
            System.out.println("3. Listar Canciones");
            System.out.println("4. Borrar Canción");
            System.out.println("5. Borrar Todas las Canciones");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (opcion) {
                case 1:
                    // Añadir canción manualmente
                    System.out.println("Ingrese el título de la canción:");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese el estilo musical:");
                    String estilo = scanner.nextLine();
                    System.out.println("Ingrese el nombre del intérprete:");
                    String interprete = scanner.nextLine();
                    helper.anadirCancion(titulo, estilo, interprete);
                    break;
                case 2:
                    // Añadir canción desde la carpeta
                    helper.anadirCancionCarpeta(scanner);
                    break;
                case 3:
                    // Listar canciones
                    helper.listarCanciones();
                    break;
                case 4:
                    // Borrar canción
                    helper.borrarCancion(scanner);
                    break;
                case 5:
                    // Borrar todas las canciones
                    helper.borrarTodas();
                    break;
                case 6:
                    // Salir de la aplicación
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
