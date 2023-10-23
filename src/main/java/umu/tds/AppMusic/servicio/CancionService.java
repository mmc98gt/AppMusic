package umu.tds.AppMusic.servicio;

import java.util.List;

import umu.tds.AppMusic.modelo.Cancion;

public interface CancionService {

    // Método para agregar una nueva canción
    void agregarCancion(Cancion cancion);
    
    // Método para actualizar una canción
    void actualizarCancion(Cancion cancion);
    
    // Método para eliminar una canción
    void eliminarCancion(String titulo);  // Usando el título como identificador
    
    // Método para obtener una canción por su título
    Cancion obtenerCancionPorTitulo(String titulo);
    
    // Método para obtener todas las canciones
    List<Cancion> obtenerTodasLasCanciones();
    
    // Método para incrementar el número de reproducciones de una canción
    void incrementarReproducciones(Cancion cancion);
   
}

