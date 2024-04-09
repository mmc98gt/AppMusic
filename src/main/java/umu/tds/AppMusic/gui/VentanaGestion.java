package umu.tds.AppMusic.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;

public class VentanaGestion extends JPanel {

	private JTextField txtTitulo;
	private JPanel panelb;
	private JPanel panel_tabla;
	private JPanel panel_botones;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JTable tablaCanciones;
	private JTable table_1;
	private String[] columnNames = { "Título", "Artista", "Estilo" };
	private DefaultTableModel model=new DefaultTableModel(null,columnNames){private static final long serialVersionUID=1L;

	@Override public boolean isCellEditable(int row,int column){
	// Esto hace que ninguna celda sea editable directamente
	return false;}};

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

		
		rellenarCanciones();

		// Creación de un JScrollPane que contenga la tabla
		JScrollPane scrollPane = new JScrollPane(tablaCanciones);

		// Añadir el JScrollPane al panel_tabla
		panel_tabla.add(scrollPane, BorderLayout.CENTER);

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
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePlaylist = txtTitulo.getText();
				if (Controlador.INSTANCE.comprobarListaYaExiste(nombrePlaylist)) {
					// TODO: mensaje de error diciendo que ya existe una playlist con ese nombre
				
				} else {
					// TODO: aparecer ventana confirmacion 
					/*ConfirmacionPlaylist confirmacion = new ConfirmacionPlaylist();
					confirmacion.setLocationRelativeTo(panel);
					confirmacion.setVisible(true);*/
					List<Cancion> canciones = Controlador.INSTANCE.obtenerCancionesFavoritas();
					Controlador.INSTANCE.crearPlaylist(nombrePlaylist,canciones);
					
				}
			}
		});

		btnEliminar = new JButton("Eliminar");
		panel_botones.add(btnEliminar);

		JPanel panelControlMusica = new JPanel();
		add(panelControlMusica, BorderLayout.SOUTH);
	}

	public void rellenarCanciones() {
		List<Cancion> canciones = Controlador.INSTANCE.obtenerCancionesFavoritas();
		Tabla tableModel = new Tabla(canciones);
	    JTable table = new JTable(tableModel);

	    panel_tabla.removeAll();
	    panel_tabla.add(new JScrollPane(table), BorderLayout.CENTER);
	    panel_tabla.revalidate();
	    panel_tabla.repaint();
		
	}

	
}