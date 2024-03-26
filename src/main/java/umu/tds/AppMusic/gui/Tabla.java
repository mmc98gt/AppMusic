package umu.tds.AppMusic.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;

public class Tabla extends AbstractTableModel {
	private List<Cancion> resultados;
	private String[] columnNames = { "Título", "Intérprete", "Estilo Musical", "Favorita" };

	
	public Tabla (List<Cancion> resultados) {
		this.resultados = resultados;
	}
	
	@Override
	public int getRowCount() {
		return resultados.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void setValueAt(Object value, int row, int col) { 
		if (col == 3 && value instanceof Boolean) {
            // Actualizar el valor en la lista de resultados
            Cancion cancion = resultados.get(row);
            cancion.setEsFavorita((Boolean) value);
		}
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Object valor = ""; //se puede poner object?
		Cancion cancion = resultados.get(row);
		switch (col) {
		case 0:
			valor = cancion.getTitulo();
			break;
		case 1:
			valor = cancion.getInterprete().getNombre();
			break;
		case 2:
			valor = cancion.getEstilo().getNombre();
			break;
		case 3:
			valor = cancion.getEsFavorita();
			break;
		}
		return valor;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 3) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 3; // Solo la columna de checkboxes es editable.
	}
}
