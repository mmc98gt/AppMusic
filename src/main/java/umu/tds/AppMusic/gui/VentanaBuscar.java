package umu.tds.AppMusic.gui;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class VentanaBuscar extends JFrame {

	private JFrame frameBuscar;
	private JTextField txtIntrprete;
	private JTextField txtTtulo;
	private JComboBox comboBox;
	private JCheckBox chckbxFavoritas;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBuscar frame = new VentanaBuscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frameBuscar = new JFrame();
		frameBuscar.setBounds(100, 100, 450, 200);
		frameBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameBuscar.setLocationRelativeTo(null);
		frameBuscar.setUndecorated(true);
		JPanel panel = new JPanel();
		frameBuscar.getContentPane().add(panel, BorderLayout.CENTER);
		crearPanel();
	}

	private void crearPanel() {
		JPanel panel = new JPanel();
		frameBuscar.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 186, 186, 10, 0};
		gbl_panel.rowHeights = new int[]{10, 50, 50, 50, 10, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
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
		gbc_txtTtulo.gridx = 2;
		gbc_txtTtulo.gridy = 1;
		panel.add(txtTtulo, gbc_txtTtulo);
		txtTtulo.setColumns(10);
		
		chckbxFavoritas = new JCheckBox("Favoritas");
		GridBagConstraints gbc_chckbxFavoritas = new GridBagConstraints();
		gbc_chckbxFavoritas.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFavoritas.gridx = 1;
		gbc_chckbxFavoritas.gridy = 2;
		panel.add(chckbxFavoritas, gbc_chckbxFavoritas);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);
		
		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 3;
		panel.add(btnBuscar, gbc_btnBuscar);
	}
}
