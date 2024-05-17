package umu.tds.AppMusic.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;

public class TablaCanciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_tabla;

	/**
	 * Create the application.
	 */
	public TablaCanciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		// panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING,
		// TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 31, 158, 10, 158, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		panel_tabla = new JPanel(new BorderLayout());
		add(panel_tabla, BorderLayout.CENTER);

	}

	public void mostrarResultadosEnTabla(List<Cancion> canciones, boolean reproducidas) {
		Tabla tableModel;
		if (reproducidas) {
			tableModel = new Tabla(canciones, reproducidas);
		} else {
			tableModel = new Tabla(canciones);
		}
		JTable table = new JTable(tableModel);

		panel_tabla.removeAll();
		panel_tabla.add(new JScrollPane(table), BorderLayout.CENTER);
		panel_tabla.revalidate();
		panel_tabla.repaint();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					// Obtener la canción seleccionada
					int selectedRowIndex = table.getSelectedRow();
					Cancion cancionSeleccionada = canciones.get(selectedRowIndex);
					Controlador.INSTANCE.establecerCancionActual(cancionSeleccionada, selectedRowIndex);

				}
			}
		});
	}

}
