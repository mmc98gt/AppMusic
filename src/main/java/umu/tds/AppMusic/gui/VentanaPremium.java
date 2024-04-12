package umu.tds.AppMusic.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Usuario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class VentanaPremium extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double precio = 10.00;
	private double descuento = 0;
	private JFrame frmVentanaPremium;
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
	public VentanaPremium(JFrame owner) {
		super(owner, "Premium", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.initialize();
	}

	public VentanaPremium() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.initialize();
	}
	
	public void mostrarVentana() {
		frmVentanaPremium.setLocationRelativeTo(null);
		frmVentanaPremium.setVisible(true);
	}

	private void initialize() {
		frmVentanaPremium = new JFrame();
		frmVentanaPremium.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmVentanaPremium.setBounds(100, 100, 640, 400);
		frmVentanaPremium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaPremium.getContentPane().setLayout(new BorderLayout(0, 0));
		frmVentanaPremium.setTitle("Compra AppMusic Premium");
		frmVentanaPremium.setResizable(false);

		JPanel panel = new JPanel();
		frmVentanaPremium.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Hazte premium");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("List.noFocusBorder"));
		frmVentanaPremium.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 110, 0, 200, 0, 110, 0 };
		gbl_panel_1.rowHeights = new int[] { 80, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblChooseDescuento = new JLabel("Elige un descuento");
		lblChooseDescuento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblChooseDescuento, gbc_lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		panel_1.add(comboBox, gbc_comboBox);
		String[] array = { "-", "DescuentoFijo", "DescuentoJovenes" };
		comboBox.setModel(new DefaultComboBoxModel<String>(array));

		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 1;
		gbc_lblPrecio.gridy = 4;
		panel_1.add(lblPrecio, gbc_lblPrecio);

		JLabel lblNewLabel_6 = new JLabel(String.valueOf(precio));
		lblNewLabel_6.setForeground(Color.black);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 4;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 1;
		gbc_lblDescuento.gridy = 5;
		panel_1.add(lblDescuento, gbc_lblDescuento);

		JLabel lblNewLabel_5 = new JLabel(String.valueOf(descuento));
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 5;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_2 = new JLabel("Precio final:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(String.valueOf(precio - descuento));
		lblNewLabel_3.setForeground(new Color(50, 205, 50));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 6;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JButton btnDescuento = new JButton("Calcular Descuento");
		GridBagConstraints gbc_btnDescuento = new GridBagConstraints();
		gbc_btnDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescuento.gridx = 3;
		gbc_btnDescuento.gridy = 2;
		panel_1.add(btnDescuento, gbc_btnDescuento);
		btnDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcionSeleccionada = (String) comboBox.getSelectedItem();
				Boolean descontar = Controlador.INSTANCE.comprobarDescuento(opcionSeleccionada);
				if (descontar) {
					descuento = Controlador.INSTANCE.getDescuento(precio, opcionSeleccionada);
					lblNewLabel_5.setText(String.valueOf(descuento));
					lblNewLabel_3.setText(String.valueOf(precio - descuento));
				} else {
					JOptionPane.showMessageDialog(VentanaPremium.this, "No puedes optar a ese descuento, elige otro");
				}
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.hacerPremium();
				JOptionPane.showMessageDialog(frmVentanaPremium, "¡Enhorabuena!\nAhora eres usuario premium",
						"Pago completado", JOptionPane.INFORMATION_MESSAGE);
				frmVentanaPremium.dispose();
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.mostrarVentana();

			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.WEST;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 8;
		panel_1.add(btnAceptar, gbc_btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 8;
		panel_1.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				frmVentanaPremium.dispose();
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.mostrarVentana();
			}
		});
	}

}
