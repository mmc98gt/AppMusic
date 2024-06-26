package umu.tds.AppMusic.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Clase que extiende Descuento para implementar un descuento porcentual para
 * jóvenes. Este tipo de descuento aplica un porcentaje de reducción sobre el
 * precio original.
 */
public class DescuentoJovenes implements Descuento {

	private double porcentajeDescuento = 0.4;
	private String tipo = "Descuento Jovenes";

	/**
	 * Constructor para crear un descuento para jóvenes.
	 * 
	 * @param porcentajeDescuento El porcentaje de descuento a aplicar.
	 */
	public DescuentoJovenes() {
	}

	/**
	 * Calcula el descuento aplicando un porcentaje al precio original.
	 * 
	 * @param precioOriginal El precio original antes de aplicar el descuento.
	 * @return El precio después de aplicar el porcentaje de descuento.
	 */
	@Override
	public double calcDescuento(double precio) {
		return precio * porcentajeDescuento;
	}

	/**
	 * Obtiene el porcentaje de descuento aplicado.
	 * 
	 * @return El porcentaje de descuento.
	 */
	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Establece el porcentaje de descuento a aplicar.
	 * 
	 * @param porcentajeDescuento El nuevo porcentaje de descuento.
	 */
	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public String getDescuento() {
		return tipo;
	}

	@Override
	public boolean esAplicable(Usuario usuario) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		LocalDate fechaActual = LocalDate.now();

		try {
			ZonedDateTime fechaNacimientoZoned = ZonedDateTime.parse(usuario.getFechaNacimiento(), formatter);
			LocalDate fechaNacimiento = fechaNacimientoZoned.toLocalDate();
			int edad = Period.between(fechaNacimiento, fechaActual).getYears();

			if (edad < 25) {
				return true;
			}
			return false;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
