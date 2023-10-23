package umu.tds.AppMusic.dao;

import java.util.List;

import umu.tds.AppMusic.modelo.Interprete;

public interface InterpreteDao {

    // Métodos para el CRUD básico

    // Crear un nuevo intérprete
    void createInterprete(Interprete interprete);

    // Obtener un intérprete por su nombre
    Interprete getInterpreteByName(String nombre);

    // Obtener todos los intérpretes
    List<Interprete> getAllInterpretes();

    // Actualizar un intérprete
    void updateInterprete(Interprete interprete);

    // Eliminar un intérprete
    void deleteInterprete(Interprete interprete);
}
