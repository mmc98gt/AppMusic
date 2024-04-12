package umu.tds.AppMusic.modelo;

import java.util.List;

/**
 * Clase abstracta que representa un descuento. 
 * Proporciona la estructura básica para aplicar diferentes tipos de descuentos.
 */
public abstract class Descuento {
    
    /**
     * Constructor de Descuento.
     */
    public Descuento() {
        // Constructor por defecto.
    }

    /**
     * Método abstracto para calcular el descuento sobre un precio original.
     * Debe ser implementado por las subclases para aplicar una política de descuento específica.
     * @param precioOriginal El precio original antes del descuento.
     * @return El precio después de aplicar el descuento.
     */
    public abstract double calcDescuento(double precioOriginal);

	protected abstract List<String> descuentos();

    
}
