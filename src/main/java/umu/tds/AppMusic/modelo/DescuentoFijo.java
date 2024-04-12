package umu.tds.AppMusic.modelo;

import java.util.List;

/**
 * Clase que extiende Descuento para implementar un descuento fijo.
 * Este tipo de descuento resta una cantidad fija del precio original.
 */
public class DescuentoFijo extends Descuento {
    
    private double descuentoFijo;

    /**
     * Constructor para crear un descuento fijo.
     * @param descuentoFijo La cantidad fija que se descontará del precio.
     */
    public DescuentoFijo(double descuentoFijo) {
        this.descuentoFijo = descuentoFijo;
    }

    /**
     * Calcula el descuento aplicando una cantidad fija de descuento al precio original.
     * @param precioOriginal El precio original antes de aplicar el descuento.
     * @return El precio después de aplicar el descuento fijo.
     */
    @Override
    public double calcDescuento(double precioOriginal) {
        return precioOriginal - descuentoFijo;
    }

	@Override
	protected List<String> descuentos() {
		// TODO Auto-generated method stub
		return null;
	}
}
