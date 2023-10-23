package umu.tds.AppMusic.modelo;

public class DescuentoFijo extends Descuento {
    
    private double descuentoFijo;

    public DescuentoFijo(double descuentoFijo) {
        this.descuentoFijo = descuentoFijo;
    }

    @Override
    public double calcDescuento(double precioOriginal) {
        return precioOriginal - descuentoFijo;
    }

}
