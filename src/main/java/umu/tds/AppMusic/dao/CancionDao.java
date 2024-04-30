package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.Cancion;

public interface CancionDao {

    // Método para agregar una nueva canción
    void agregarCancion(Cancion cancion);
    
    // Método para actualizar una canción existente
    void actualizarCancion(Cancion cancion);
    
    // Método para eliminar una canción
    void eliminarCancion(Cancion cancion);  // Usando el título como identificador, o puedes usar otro atributo único si prefieres
    
    // Método para obtener una canción por su título
    Cancion obtenerCancionPorTitulo(String titulo);
    
 // Método para obtener una canción por su id
    Cancion obtenerCancionPorId(Integer id);
    
    // Método para obtener todas las canciones
    List<Cancion> obtenerTodasLasCanciones();

	void borrarCanciones();
    
}
