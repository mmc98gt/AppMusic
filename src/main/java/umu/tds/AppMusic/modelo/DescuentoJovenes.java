package umu.tds.AppMusic.modelo;

/**
 * Clase que extiende Descuento para implementar un descuento porcentual para jóvenes.
 * Este tipo de descuento aplica un porcentaje de reducción sobre el precio original.
 */
public class DescuentoJovenes extends Descuento {

    private double porcentajeDescuento;

    /**
     * Constructor para crear un descuento para jóvenes.
     * @param porcentajeDescuento El porcentaje de descuento a aplicar.
     */
    public DescuentoJovenes(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * Calcula el descuento aplicando un porcentaje al precio original.
     * @param precioOriginal El precio original antes de aplicar el descuento.
     * @return El precio después de aplicar el porcentaje de descuento.
     */
    @Override
    public double calcDescuento(double precioOriginal) {
        return precioOriginal * (1 - (porcentajeDescuento / 100.0));
    }

    /**
     * Obtiene el porcentaje de descuento aplicado.
     * @return El porcentaje de descuento.
     */
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * Establece el porcentaje de descuento a aplicar.
     * @param porcentajeDescuento El nuevo porcentaje de descuento.
     */
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
