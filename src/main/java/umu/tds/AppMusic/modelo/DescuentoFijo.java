package umu.tds.AppMusic.modelo;

import java.util.List;

/**
 * Clase que extiende Descuento para implementar un descuento fijo. Este tipo de
 * descuento resta una cantidad fija del precio original.
 */
public class DescuentoFijo implements Descuento {

	private double porcentajeDescuento = 0.1;
	private String tipo = "Descuento Fijo";

	/**
	 * Constructor para crear un descuento fijo.
	 * 
	 * @param descuentoFijo La cantidad fija que se descontará del precio.
	 */
	public DescuentoFijo() {
	}

	/**
	 * Calcula el descuento aplicando una cantidad fija de descuento al precio
	 * original.
	 * 
	 * @param precioOriginal El precio original antes de aplicar el descuento.
	 * @return El precio después de aplicar el descuento fijo.
	 */
	@Override
	public double calcDescuento(double precio) {
		return precio*porcentajeDescuento;
	}

	@Override
	public double aplicarDescuento(double precio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescuento() {
		return tipo;
	}

	@Override
	public boolean esAplicable(Usuario usuario) {
		return true;
	}
}
