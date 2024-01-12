package umu.tds.AppMusic.gui;



import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Usuario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaPremium extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaPrecios;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
			VentanaPremium dialog = new VentanaPremium();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPremium(JFrame owner){
		super(owner, "Premium", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.initialize();
	}
	
	public VentanaPremium(){
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.initialize();
	}
	
	
	
	 private void initialize() {
		 
		 Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 10, 309, 0, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 40, 0, 0, 0, 0, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		String[] array = { "DescuentoFijo","DescuentoJovenes" };
		
				JLabel lblTextoMejora = new JLabel("MEJORA PREMIUM");
				lblTextoMejora.setFont(new Font("Tahoma", Font.PLAIN, 15));
				GridBagConstraints gbc_lblTextoMejora = new GridBagConstraints();
				gbc_lblTextoMejora.gridwidth = 2;
				gbc_lblTextoMejora.insets = new Insets(0, 0, 5, 5);
				gbc_lblTextoMejora.gridx = 2;
				gbc_lblTextoMejora.gridy = 0;
				getContentPane().add(lblTextoMejora, gbc_lblTextoMejora);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(array));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBox, gbc_comboBox);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 25, 25, 0, 20, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_tablaPrecios = new GridBagConstraints();
		gbc_tablaPrecios.insets = new Insets(0, 0, 5, 5);
		gbc_tablaPrecios.fill = GridBagConstraints.BOTH;
		gbc_tablaPrecios.gridx = 9;
		gbc_tablaPrecios.gridy = 5;

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);

		tablaPrecios = new JTable();
		tablaPrecios.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "PRECIO INICIAL", "DESCUENTO", "PRECIO FINAL" }));
		scrollPane.setViewportView(tablaPrecios);

		JButton btnCancelar = new JButton("CANCELAR SUSCRIPCIÓN");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (usuarioActual.isPremium()) {
					JOptionPane.showMessageDialog(btnCancelar, "Has cancelado tu suscripcion","Premium",
							JOptionPane.INFORMATION_MESSAGE, null);
					Controlador.INSTANCE.getUsuarioActual().setPremium(false);
				} else {
					JOptionPane.showMessageDialog(btnCancelar, "Tienes que estar suscrito para poder cancelar tu suscripion",
							"Premium", JOptionPane.INFORMATION_MESSAGE, null);
				}

			}
		});
		
		JButton btnCalcularDescuento = new JButton("Calcular Descuento");
		//TODO funcionalidad boton calcular descuento
		btnCalcularDescuento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnCalcularDescuento = new GridBagConstraints();
		gbc_btnCalcularDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcularDescuento.gridx = 3;
		gbc_btnCalcularDescuento.gridy = 3;
		getContentPane().add(btnCalcularDescuento, gbc_btnCalcularDescuento);

		JButton btnPagar = new JButton("PAGAR");
		btnPagar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (usuarioActual.isPremium()) {
					JOptionPane.showMessageDialog(btnPagar,
							"Ya eras premium!", "Premium",
							JOptionPane.INFORMATION_MESSAGE, null);
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					VentanaPremium.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnPagar, "Ya eres premium!",
							"Premium", JOptionPane.INFORMATION_MESSAGE, null);
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					VentanaPremium.this.dispose();
				}
				Controlador.INSTANCE.getUsuarioActual().setPremium(true);

			}
		});

		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPagar.anchor = GridBagConstraints.NORTH;
		gbc_btnPagar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPagar.gridx = 2;
		gbc_btnPagar.gridy = 4;
		getContentPane().add(btnPagar, gbc_btnPagar);

		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	
	}
	

}
