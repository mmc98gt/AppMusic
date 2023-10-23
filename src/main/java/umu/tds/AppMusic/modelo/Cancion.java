package umu.tds.AppMusic.modelo;

public class Cancion {
    
    private String titulo;
    private String rutaFichero;
    private int numReproducciones;
    private EstiloMusical estilo;
    private Interprete interprete;
    
    // Constructor
    public Cancion(String titulo, String rutaFichero, EstiloMusical estilo, Interprete interprete) {
        this.titulo = titulo;
        this.rutaFichero = rutaFichero;
        this.numReproducciones = 0; // inicialmente la canción no ha sido reproducida
        this.estilo = estilo;
        this.interprete = interprete;
    }
    
    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaFichero() {
        return rutaFichero;
    }

    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    public int getNumReproducciones() {
        return numReproducciones;
    }

    public void incrementarReproducciones() {
        this.numReproducciones++;
    }

    public EstiloMusical getEstilo() {
        return estilo;
    }

    public void setEstilo(EstiloMusical estilo) {
        this.estilo = estilo;
    }

    public Interprete getInterprete() {
        return interprete;
    }

    public void setInterprete(Interprete interprete) {
        this.interprete = interprete;
    }
    
}
