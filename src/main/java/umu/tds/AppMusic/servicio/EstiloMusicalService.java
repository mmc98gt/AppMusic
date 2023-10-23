package umu.tds.AppMusic.servicio;

import java.util.List;

import umu.tds.AppMusic.modelo.EstiloMusical;

public interface EstiloMusicalService {

    // Método para agregar un nuevo estilo musical
    void agregarEstilo(EstiloMusical estilo);

    // Método para actualizar un estilo musical
    void actualizarEstilo(EstiloMusical estilo);

    // Método para eliminar un estilo musical por su nombre
    void eliminarEstilo(String nombre);

    // Método para obtener un estilo musical por su nombre
    EstiloMusical obtenerEstiloPorNombre(String nombre);

    // Método para obtener todos los estilos musicales
    List<EstiloMusical> obtenerTodosLosEstilos();

}
