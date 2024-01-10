package umu.tds.AppMusic.modelo;

/**
 * Representa una canción con sus detalles incluyendo título, ruta de fichero, estilo musical y el intérprete.
 */
public class Cancion {
    
    private int id;
    private String titulo;
    private String rutaFichero;
    private int numReproducciones;
    private EstiloMusical estilo;
    private Interprete interprete;
    
    /**
     * Constructor para crear una nueva canción.
     * @param titulo El título de la canción.
     * @param rutaFichero La ruta al fichero de la canción.
     * @param estilo El estilo musical de la canción.
     * @param interprete El intérprete de la canción.
     */
    public Cancion(String titulo, String rutaFichero, EstiloMusical estilo, Interprete interprete) {
        this.titulo = titulo;
        this.rutaFichero = rutaFichero;
        this.numReproducciones = 0; // inicialmente la canción no ha sido reproducida
        this.estilo = estilo;
        this.interprete = interprete;
    }
    
    // Getters y Setters con comentarios JavaDoc...

    /**
     * Obtiene el título de la canción.
     * @return El título de la canción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la canción.
     * @param titulo El nuevo título de la canción.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la ruta al fichero de la canción.
     * @return La ruta al fichero de la canción.
     */
    public String getRutaFichero() {
        return rutaFichero;
    }

    /**
     * Establece la ruta al fichero de la canción.
     * @param rutaFichero La nueva ruta al fichero de la canción.
     */
    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    /**
     * Obtiene el número de reproducciones de la canción.
     * @return El número de reproducciones.
     */
    public int getNumReproducciones() {
        return numReproducciones;
    }

    /**
     * Incrementa el número de reproducciones de la canción.
     */
    public void incrementarReproducciones() {
        this.numReproducciones++;
    }

    /**
     * Obtiene el estilo musical de la canción.
     * @return El estilo musical de la canción.
     */
    public EstiloMusical getEstilo() {
        return estilo;
    }

    /**
     * Establece el estilo musical de la canción.
     * @param estilo El nuevo estilo musical de la canción.
     */
    public void setEstilo(EstiloMusical estilo) {
        this.estilo = estilo;
    }

    /**
     * Obtiene el intérprete de la canción.
     * @return El intérprete de la canción.
     */
    public Interprete getInterprete() {
        return interprete;
    }

    /**
     * Establece el intérprete de la canción.
     * @param interprete El nuevo intérprete de la canción.
     */
    public void setInterprete(Interprete interprete) {
        this.interprete = interprete;
    }

    /**
     * Obtiene el identificador de la canción.
     * @return El identificador de la canción.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la canción.
     * @param id El nuevo identificador de la canción.
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
