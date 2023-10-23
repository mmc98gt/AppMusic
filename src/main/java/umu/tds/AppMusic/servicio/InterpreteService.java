package umu.tds.AppMusic.servicio;

import java.util.List;

import umu.tds.AppMusic.modelo.Interprete;

public interface InterpreteService {

    // Métodos básicos

    // Registrar un nuevo intérprete
    void registerInterprete(Interprete interprete);

    // Obtener un intérprete por su nombre
    Interprete findInterpreteByName(String nombre);

    // Obtener todos los intérpretes
    List<Interprete> listAllInterpretes();

    // Actualizar información de un intérprete
    void updateInterpreteInfo(Interprete interprete);

    // Eliminar un intérprete
    void removeInterprete(Interprete interprete);

    // Métodos adicionales según la lógica de negocio
    // Por ejemplo: métodos para buscar intérpretes por género musical, por popularidad, etc.

}
