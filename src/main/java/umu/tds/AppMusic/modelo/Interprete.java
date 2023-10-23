package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

public class Interprete {

    // Atributos
    private String nombre;
    private List<Cancion> canciones;

    // Constructor
    public Interprete(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    // Métodos
    public void addCancion(Cancion cancion) {
        if (cancion != null && !canciones.contains(cancion)) {
            canciones.add(cancion);
        }
    }

    public void removeCancion(Cancion cancion) {
        canciones.remove(cancion);
    }

}
