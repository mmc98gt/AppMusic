package umu.tds.AppMusic.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;

public class VentanaGestion extends JPanel {

	private JTextField txtTitulo;
	private JPanel panelb;
    private JPanel panel_tabla;
    private JPanel panel_botones;
    private JButton btnCrear;
    private JButton btnEliminar;
    private JTable tablaCanciones;
    private JTable table_1;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VentanaGestion() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 31, 158, 10, 158, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 1.0 };
		panel.setLayout(gbl_panel);
        panel_tabla = new JPanel(new BorderLayout());
        add(panel_tabla, BorderLayout.CENTER);
        

		txtTitulo = new JTextField();
		txtTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		txtTitulo.setText("Título");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		panel.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		panel_botones = new JPanel();
		GridBagConstraints gbc_panel_botones = new GridBagConstraints();
		gbc_panel_botones.insets = new Insets(0, 0, 0, 5);
		gbc_panel_botones.fill = GridBagConstraints.BOTH;
		gbc_panel_botones.gridx = 1;
		gbc_panel_botones.gridy = 2;
		panel.add(panel_botones, gbc_panel_botones);
		
		btnCrear = new JButton("Crear");
		btnCrear.setHorizontalAlignment(SwingConstants.LEFT);
		panel_botones.add(btnCrear);
		
		btnEliminar = new JButton("Eliminar");
		panel_botones.add(btnEliminar);

		JPanel panelControlMusica = new JPanel();
		add(panelControlMusica, BorderLayout.SOUTH);
	}
	
	

	private void mostrarResultadosEnTabla(List<Cancion> resultados) {
	    // Crear un modelo de tabla para mostrar los resultados
	    String[] columnNames = {"Título", "Intérprete", "Estilo Musical"};
	    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	    for (Cancion cancion : resultados) {
	        Object[] row = new Object[]{
	            cancion.getTitulo(),
	            cancion.getInterprete(),
	            cancion.getEstilo().toString() // Asumiendo que cada canción tiene un estilo musical asociado
	        };
	        tableModel.addRow(row);
	    }

	    JTable table = new JTable(tableModel);
	    panel_tabla.removeAll();
	    panel_tabla.add(new JScrollPane(table), BorderLayout.CENTER);
	    panel_tabla.revalidate();
	    panel_tabla.repaint();
	}

}
