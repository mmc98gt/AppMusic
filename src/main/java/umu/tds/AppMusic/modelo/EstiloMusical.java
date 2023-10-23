package umu.tds.AppMusic.modelo;

public class EstiloMusical {
    
    private String nombre;

    // Constructor
    public EstiloMusical(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstiloMusical{" + "nombre=" + nombre + '}';
    }
    
}