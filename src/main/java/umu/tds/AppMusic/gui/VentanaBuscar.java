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

public class VentanaBuscar extends JPanel {

	private JTextField txtIntrprete;
	private JTextField txtTtulo;
	private JComboBox comboBox;
	private JCheckBox chckbxFavoritas;
	private JButton btnBuscar;
	private JPanel panelb;
    private JPanel panel_tabla;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VentanaBuscar() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 31, 158, 10, 158, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
        panel_tabla = new JPanel(new BorderLayout());
        add(panel_tabla, BorderLayout.CENTER);
		/*
		 * GridBagLayout gbl_panel = new GridBagLayout(); gbl_panel.columnWidths = new
		 * int[]{10, 186, 186, 10, 0}; gbl_panel.rowHeights = new int[]{10, 50, 50, 50,
		 * 10, 0}; gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0,
		 * Double.MIN_VALUE}; gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0,
		 * 0.0, Double.MIN_VALUE}; setLayout(gbl_panel);
		 */

		txtIntrprete = new JTextField();
		txtIntrprete.setHorizontalAlignment(SwingConstants.LEFT);
		txtIntrprete.setText("Intérprete\r\n");
		GridBagConstraints gbc_txtIntrprete = new GridBagConstraints();
		gbc_txtIntrprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIntrprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtIntrprete.gridx = 1;
		gbc_txtIntrprete.gridy = 1;
		panel.add(txtIntrprete, gbc_txtIntrprete);
		txtIntrprete.setColumns(10);

		txtTtulo = new JTextField();
		txtTtulo.setText("Título");
		txtTtulo.setToolTipText("Título\r\n");
		GridBagConstraints gbc_txtTtulo = new GridBagConstraints();
		gbc_txtTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTtulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTtulo.gridx = 3;
		gbc_txtTtulo.gridy = 1;
		panel.add(txtTtulo, gbc_txtTtulo);
		txtTtulo.setColumns(10);

		chckbxFavoritas = new JCheckBox("Favoritas");
		GridBagConstraints gbc_chckbxFavoritas = new GridBagConstraints();
		gbc_chckbxFavoritas.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFavoritas.gridx = 1;
		gbc_chckbxFavoritas.gridy = 2;
		panel.add(chckbxFavoritas, gbc_chckbxFavoritas);

		comboBox = new JComboBox<EstiloMusical>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);

		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 3;
		panel.add(btnBuscar, gbc_btnBuscar);

		JPanel panelControlMusica = new JPanel();
		add(panelControlMusica, BorderLayout.SOUTH);

		btnBuscar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            realizarBusqueda();
	        }
	    });
	}

	private void realizarBusqueda() {
		String interprete = txtIntrprete.getText();
		String titulo = txtTtulo.getText();
		boolean esFavorita = chckbxFavoritas.isSelected();
		EstiloMusical estilo = (EstiloMusical) comboBox.getSelectedItem();

		List<Cancion> resultados = Controlador.INSTANCE.buscarCanciones(interprete, titulo, esFavorita, estilo);
		mostrarResultadosEnTabla(resultados);
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
