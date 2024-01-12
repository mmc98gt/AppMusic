package umu.tds.AppMusic.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal{

	private JPanel contentPane;
	private JFrame frmVentanaPrincipal;
	private JPanel panelCardLayout;
	private String textoBienvenida;
	private Usuario usuarioActual;

	
	/**
	 * Create the frame.
	 */

	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
	    frmVentanaPrincipal.setVisible(true);
	}

	public VentanaPrincipal() {
		initialize();
	}

    private void initialize() {
        frmVentanaPrincipal = new JFrame();
        frmVentanaPrincipal.setTitle("AppMusic");
        frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmVentanaPrincipal.setBounds(100, 100, 653, 447);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        frmVentanaPrincipal.setContentPane(contentPane);

        usuarioActual = Controlador.INSTANCE.getUsuarioActual();
        textoBienvenida = "Bienvenido, " + (usuarioActual != null ? usuarioActual.getNombre() : "Invitado");
        
        crearPaneles();
    }

	private void crearPaneles() {
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panel_norte = new JPanel();
		FlowLayout fl_panel_norte = (FlowLayout) panel_norte.getLayout();
		fl_panel_norte.setAlignment(FlowLayout.RIGHT);
		panelCentro.add(panel_norte, BorderLayout.NORTH);

		JLabel lblBienvenido = new JLabel(textoBienvenida);
		panel_norte.add(lblBienvenido);

		JButton btnPremium = new JButton("Premium");
		panel_norte.add(btnPremium);
		addManejadorBotonPremium(btnPremium);
		
		

		JButton btnSalir = new JButton("Salir");
		panel_norte.add(btnSalir);

		panelCardLayout = new JPanel();
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		///////////////////////////////////////////
		
		
		  
		  JPanel panelGestion = new JPanel(); panelCardLayout.add(panelGestion,
		  "panelGestion");
		  
		  JLabel lblPanelgestion = new JLabel("PanelGestion");
		  panelGestion.add(lblPanelgestion);
		  
		 /* JPanel panelRecientes = new JPanel(); panelCardLayout.add(panelRecientes,
		 * "panelRecientes");
		 * 
		 * JPanel panelPlaylists = new JPanel(); panelCardLayout.add(panelPlaylists,
		 * "panelPlaylists");
		 */

		// PANEL BOTONES
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.WEST);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[] { 10, 0, 10, 0 };
		gbl_panelBotones.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelBotones.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelBotones.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelBotones.setLayout(gbl_panelBotones);

		JButton btnBuscar = new JButton("Buscar");
		VentanaBuscar ventanaBuscar = new VentanaBuscar();
		panelCardLayout.add(ventanaBuscar, "panelBuscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelBuscar");
			}
		});

		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/lupa1.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panelBotones.add(btnBuscar, gbc_btnNewButton);

		JButton btnGestionPlaylist = new JButton("Gestion Playlists");
		VentanaGestion ventanaGestion = new VentanaGestion();
		panelCardLayout.add(ventanaGestion, "panelGestion");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelGestion");
			}
		});
		

		btnGestionPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionPlaylist
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/mas1.png")));
		btnGestionPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panelBotones.add(btnGestionPlaylist, gbc_btnNewButton_1);

		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/relog.png")));
		btnRecientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		panelBotones.add(btnRecientes, gbc_btnNewButton_2);
		
		if(usuarioActual.isPremium()) {
			JButton btnMasEscuchadas = new JButton("Más escuchadas");
			btnMasEscuchadas.setHorizontalAlignment(SwingConstants.LEFT);
			btnMasEscuchadas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/relog.png")));
			btnMasEscuchadas.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
			gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_5.gridx = 1;
			gbc_btnNewButton_5.gridy = 4;
			panelBotones.add(btnMasEscuchadas, gbc_btnNewButton_5);
			
			JButton btnDescargarPDF = new JButton("Descargar PFD");
			btnDescargarPDF.setHorizontalAlignment(SwingConstants.LEFT);
			btnDescargarPDF.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/relog.png")));
			btnDescargarPDF.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
			gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_6.gridx = 1;
			gbc_btnNewButton_6.gridy = 5;
			panelBotones.add(btnDescargarPDF, gbc_btnNewButton_6);
		}
		

		JButton btnPlaylist = new JButton("Mis Playlists");
		btnPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlaylist.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/playlist.png")));
		btnPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 6;
		panelBotones.add(btnPlaylist, gbc_btnNewButton_3);
		
		JPanel panel_Listas = new JPanel();
		GridBagConstraints gbc_panel_Listas = new GridBagConstraints();
		gbc_panel_Listas.insets = new Insets(0, 0, 0, 5);
		gbc_panel_Listas.fill = GridBagConstraints.BOTH;
		gbc_panel_Listas.gridx = 1;
		gbc_panel_Listas.gridy = 7;
		panelBotones.add(panel_Listas, gbc_panel_Listas);
		panel_Listas.setLayout(new CardLayout(0, 0));
	}
	
	private void addManejadorBotonPremium(JButton btnPremium) {
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPremium registro = new VentanaPremium(frmVentanaPrincipal);
				registro.setLocationRelativeTo(frmVentanaPrincipal);
				registro.setVisible(true);
				frmVentanaPrincipal.dispose();
			}
		});
	}

}
