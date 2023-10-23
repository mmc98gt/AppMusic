package umu.tds.AppMusic.modelo;

public class DescuentoJovenes extends Descuento {

    private double porcentajeDescuento;

    // Constructor
    public DescuentoJovenes(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Implementación del método calcDescuento
    @Override
    public double calcDescuento(double precioOriginal) {
        return precioOriginal * (1 - (porcentajeDescuento / 100.0));
    }

    // Getters y setters
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
