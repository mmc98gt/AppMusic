package umu.tds.AppMusic.modelo;

/**
 * Representa una canción con sus detalles incluyendo título, ruta de fichero,
 * estilo musical y el intérprete.
 */
public class Cancion {

    private int id;
    private String titulo;
    private String rutaFichero;
    private int numReproducciones;
    private String estilo;
    private String interprete;
    private boolean esFavorita;

    /**
     * Constructor para crear una nueva canción.
     *
     * @param titulo      el título de la canción.
     * @param rutaFichero la ruta al fichero de la canción.
     * @param estilo      el estilo musical de la canción.
     * @param interprete  el intérprete de la canción.
     */
    public Cancion(String titulo, String rutaFichero, String estilo, String interprete) {
        this.titulo = titulo;
        this.rutaFichero = rutaFichero;
        this.numReproducciones = 0; // inicialmente la canción no ha sido reproducida
        this.estilo = estilo;
        this.interprete = interprete;
        this.esFavorita = false;
    }

    /**
     * Obtiene el título de la canción.
     *
     * @return el título de la canción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la canción.
     *
     * @param titulo el nuevo título de la canción.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la ruta al fichero de la canción.
     *
     * @return la ruta al fichero de la canción.
     */
    public String getRutaFichero() {
        return rutaFichero;
    }

    /**
     * Establece la ruta al fichero de la canción.
     *
     * @param rutaFichero la nueva ruta al fichero de la canción.
     */
    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    /**
     * Obtiene el número de reproducciones de la canción.
     *
     * @return el número de reproducciones.
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
     *
     * @return el estilo musical de la canción.
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * Establece el estilo musical de la canción.
     *
     * @param estilo el nuevo estilo musical de la canción.
     */
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    /**
     * Obtiene el intérprete de la canción.
     *
     * @return el intérprete de la canción.
     */
    public String getInterprete() {
        return interprete;
    }

    /**
     * Establece el intérprete de la canción.
     *
     * @param interprete el nuevo intérprete de la canción.
     */
    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    /**
     * Obtiene el identificador de la canción.
     *
     * @return el identificador de la canción.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la canción.
     *
     * @param id el nuevo identificador de la canción.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene si la canción es favorita.
     *
     * @return true si la canción es favorita, false en caso contrario.
     */
    public boolean getEsFavorita() {
        return esFavorita;
    }

    /**
     * Establece si la canción es favorita.
     *
     * @param favorita true si la canción es favorita, false en caso contrario.
     */
    public void setEsFavorita(boolean favorita) {
        this.esFavorita = favorita;
    }

    /**
     * Incrementa el número de reproducciones de la canción.
     */
    public void addReproduccion() {
        numReproducciones++;
    }
}
