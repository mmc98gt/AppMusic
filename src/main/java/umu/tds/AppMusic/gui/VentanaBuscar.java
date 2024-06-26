package umu.tds.AppMusic.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.EstiloMusical;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class VentanaBuscar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIntrprete;
	private JTextField txtTtulo;
	private JCheckBox chckbxFavoritas;
	private JComboBox comboBox;
	private JButton btnBuscar;
	private JPanel panel_tabla;
	private JButton btnAnadirLista;

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

		// Etiqueta para Intérprete
		JLabel lblInterprete = new JLabel("Intérprete");
		GridBagConstraints gbc_lblInterprete = new GridBagConstraints();
		gbc_lblInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterprete.gridx = 1;
		gbc_lblInterprete.gridy = 0;
		panel.add(lblInterprete, gbc_lblInterprete);

		// Campo de texto para Intérprete
		txtIntrprete = new JTextField();
		GridBagConstraints gbc_txtIntrprete = new GridBagConstraints();
		gbc_txtIntrprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtIntrprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIntrprete.gridx = 1;
		gbc_txtIntrprete.gridy = 1;
		panel.add(txtIntrprete, gbc_txtIntrprete);
		txtIntrprete.setColumns(10);

		// Etiqueta para Título
		JLabel lblTitulo = new JLabel("Título");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 3;
		gbc_lblTitulo.gridy = 0;
		panel.add(lblTitulo, gbc_lblTitulo);

		// Campo de texto para Título
		txtTtulo = new JTextField();
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
		List<String> estilos = Controlador.INSTANCE.obtenerEstilosMusicales();
		String[] array = estilos.toArray(new String[estilos.size()]);
		comboBox.setModel(new DefaultComboBoxModel<String>(array));

		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 3;
		panel.add(btnBuscar, gbc_btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarBusqueda();
			}
		});

		JPanel panelControlMusica = new JPanel();
		add(panelControlMusica, BorderLayout.SOUTH);

		btnAnadirLista = new JButton("Añadir Lista");
		btnAnadirLista.setHorizontalAlignment(SwingConstants.RIGHT);
		panelControlMusica.add(btnAnadirLista);
		btnAnadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMisListas misListas = new VentanaMisListas(false);
				misListas.mostrarVentana();
			}
		});
	}

	private void realizarBusqueda() {
		String interprete = txtIntrprete.getText();
		String titulo = txtTtulo.getText();
		boolean esFavorita = chckbxFavoritas.isSelected();
		String estilo = (String) comboBox.getSelectedItem();
		if (estilo.equals("Estilo Musical")) {
			estilo = null;
		}

		List<Cancion> resultados = Controlador.INSTANCE.buscarCanciones(interprete, titulo, esFavorita, estilo);
		mostrarResultadosEnTabla(resultados);
	}

	private void mostrarResultadosEnTabla(List<Cancion> resultados) {
		Tabla tableModel = new Tabla(resultados);
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
					Cancion cancionSeleccionada = resultados.get(selectedRowIndex);
					Controlador.INSTANCE.establecerCancionActual(cancionSeleccionada, selectedRowIndex);
				}
			}
		});
	}

}
