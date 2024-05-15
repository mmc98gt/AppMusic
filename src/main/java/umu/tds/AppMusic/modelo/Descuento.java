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

	static Set<Descuento> descuentos() {
		Set<Descuento> d = new HashSet<>();
		Collections.addAll(d, new DescuentoFijo(), new DescuentoJovenes());
		return d;
	}

	/**
	 * Calcula el descuento aplicando una cantidad fija de descuento al precio
	 * original.
	 * 
	 * @param precioOriginal El precio original antes de aplicar el descuento.
	 * @return El precio después de aplicar el descuento fijo.
	 */
	double calcDescuento(double precioOriginal);

	String getDescuento();

	boolean esAplicable(Usuario usuario);

}
