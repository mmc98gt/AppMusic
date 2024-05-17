package umu.tds.AppMusic.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Interfaz que representa a los descuentos. Proporciona la estructura básica
 * para aplicar diferentes tipos de descuentos.
 */
public interface Descuento {
    // Patrón estrategia

    /**
     * Obtiene un conjunto de descuentos disponibles.
     *
     * @return un conjunto de descuentos.
     */
    static Set<Descuento> descuentos() {
        Set<Descuento> d = new HashSet<>();
        Collections.addAll(d, new DescuentoFijo(), new DescuentoJovenes());
        return d;
    }

    /**
     * Calcula el descuento aplicando una cantidad fija de descuento al precio
     * original.
     *
     * @param precioOriginal el precio original antes de aplicar el descuento.
     * @return el precio después de aplicar el descuento fijo.
     */
    double calcDescuento(double precioOriginal);

    /**
     * Obtiene una descripción del descuento.
     *
     * @return la descripción del descuento.
     */
    String getDescuento();

    /**
     * Verifica si el descuento es aplicable a un usuario específico.
     *
     * @param usuario el usuario para el cual se verifica la aplicabilidad del descuento.
     * @return true si el descuento es aplicable, false en caso contrario.
     */
    boolean esAplicable(Usuario usuario);
}
