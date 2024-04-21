package umu.tds.AppMusic.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument.Content;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JPanel contentPane;
	private JFrame frmVentanaPrincipal;
	private JPanel panelCardLayout;
	private String textoBienvenida;
	private Usuario usuarioActual;
	private JPanel panel_Listas = new JPanel();
	private JList<String> playlistList;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listModel;
	private TablaCanciones tablaCanciones = new TablaCanciones();

	private MediaPlayer mediaPlayer;
	private String tempPath;
	private VentanaBuscar ventanaBuscar = new VentanaBuscar();

	/**
	 * Create the frame.
	 */

	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}

	public VentanaPrincipal() {
		mediaPlayer = null;
		tempPath = System.getProperty("user.dir") + "/temp";
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
		panel_Listas.setVisible(false);

		usuarioActual = Controlador.INSTANCE.getUsuarioActual();
		textoBienvenida = "Bienvenido, " + (usuarioActual != null ? usuarioActual.getNombre() : "Invitado");

		crearPaneles();
	}

	private void crearPaneles() {
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panel_norte = new JPanel();
		JPanel panel_sur = new JPanel();
		FlowLayout fl_panel_norte = (FlowLayout) panel_norte.getLayout();
		fl_panel_norte.setAlignment(FlowLayout.RIGHT);
		panelCentro.add(panel_norte, BorderLayout.NORTH);
		panelCentro.add(panel_sur, BorderLayout.SOUTH);

		JLabel lblBienvenido = new JLabel(textoBienvenida);
		panel_norte.add(lblBienvenido);

		JButton btnPremium = new JButton("Premium");
		panel_norte.add(btnPremium);
		addManejadorBotonPremium(btnPremium);

		agregarBotonesControlMusica(panel_sur);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioActual = Controlador.INSTANCE.getUsuarioActual();
				int salir = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?", "Salir",
						JOptionPane.YES_NO_OPTION);
				if (salir == JOptionPane.YES_OPTION) {
					// Controlador.INSTANCE.salir();
					LoginView login = new LoginView();
					login.mostrarVentana();
					frmVentanaPrincipal.dispose();
				}

			}
		});
		panel_norte.add(btnSalir);

		panelCardLayout = new JPanel();
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		///////////////////////////////////////////

		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, " ");

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
		btnGestionPlaylist.addActionListener(e -> {
			List<Cancion> canciones = Controlador.INSTANCE.obtenerCancionesFavoritas();
			ventanaGestion.rellenarCanciones(canciones);
			CardLayout card = (CardLayout) panelCardLayout.getLayout();
			card.show(panelCardLayout, "panelGestion");
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
		btnRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/reloj.png")));
		btnRecientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecientes.addActionListener(e -> {
			CardLayout card = (CardLayout) panelCardLayout.getLayout();
			card.show(panelCardLayout, "panelRecientes");
		});

		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		panelBotones.add(btnRecientes, gbc_btnNewButton_2);

		if (usuarioActual.isPremium()) {
			JButton btnMasEscuchadas = new JButton("Más escuchadas");
			btnMasEscuchadas.setHorizontalAlignment(SwingConstants.LEFT);
			btnMasEscuchadas.setIcon(
					new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/masEscuchado.png")));
			btnMasEscuchadas.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
			gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_5.gridx = 1;
			gbc_btnNewButton_5.gridy = 4;
			panelBotones.add(btnMasEscuchadas, gbc_btnNewButton_5);
			btnMasEscuchadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO: hay que hacer la lista de canciones mas escuchadas y obtenerla con el
					// controlador
					// List<Cancion> resultado = Controlador.INSTANCE.();
					// actualizarTabla(resultado);

				}
			});

			JButton btnDescargarPDF = new JButton("Descargar PFD");
			btnDescargarPDF.setHorizontalAlignment(SwingConstants.LEFT);
			btnDescargarPDF.setIcon(
					new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/descarga.png")));
			btnDescargarPDF.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
			gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_6.gridx = 1;
			gbc_btnNewButton_6.gridy = 5;
			panelBotones.add(btnDescargarPDF, gbc_btnNewButton_6);
			btnDescargarPDF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean success = Controlador.INSTANCE.crearPDF();

					if (!success) {
						JOptionPane.showMessageDialog(frmVentanaPrincipal, "Error al generar PDF", "Fallo PDF",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frmVentanaPrincipal, "PDF generado correctamente", "Descarga PDF",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});
		}

		GridBagConstraints gbc_panel_Listas = new GridBagConstraints();
		gbc_panel_Listas.insets = new Insets(0, 0, 0, 5);
		gbc_panel_Listas.fill = GridBagConstraints.BOTH;
		gbc_panel_Listas.gridx = 1;
		gbc_panel_Listas.gridy = 7;
		panelBotones.add(panel_Listas, gbc_panel_Listas);
		listModel = new DefaultListModel<>();
		playlistList = new JList<>(listModel);
		scrollPane = new JScrollPane(playlistList);
		panel_Listas.setLayout(new BorderLayout(0, 0));
		panel_Listas.add(scrollPane, BorderLayout.CENTER);
		JButton btnPlaylist = new JButton("Mis Playlists");
		btnPlaylist.addActionListener(e -> {
			panel_Listas.setVisible(true);
			obtenerListaPlaylist();

		});

		btnPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlaylist.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/playlist.png")));
		btnPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 6;
		panelBotones.add(btnPlaylist, gbc_btnNewButton_3);

	}

	private void addManejadorBotonPremium(JButton btnPremium) {

		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!usuarioActual.isPremium()) {
					VentanaPremium registro = new VentanaPremium(frmVentanaPrincipal);
					registro.setLocationRelativeTo(frmVentanaPrincipal);
					registro.mostrarVentana();
					frmVentanaPrincipal.dispose();
				} else {
					int salir = JOptionPane.showConfirmDialog(null, "¿Desea dejar de ser premium?", "Premium",
							JOptionPane.YES_NO_OPTION);
					if (salir == JOptionPane.YES_OPTION) {
						Controlador.INSTANCE.salirPremium();
						frmVentanaPrincipal.dispose();
						VentanaPrincipal principal = new VentanaPrincipal();
						principal.mostrarVentana();
					}
				}
			}
		});

	}

	// Método para reproducir una canción
	private void playCancion() {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
			mediaPlayer.play();
			return;
		}
		stopCancion();
		String url = VentanaBuscar.getDirecion();
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});

			Media media;
			if (url.startsWith("http")) {
				URL uri = new URL(url);
				System.setProperty("java.io.tmpdir", tempPath);
				Path mp3 = Files.createTempFile("now-playing", ".mp3");
				try (InputStream stream = uri.openStream()) {
					Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
				}
				media = new Media(mp3.toFile().toURI().toString());
			} else {
				String resourcePath = "/" + url;
				URL resourceURL = getClass().getResource(resourcePath);
				if (resourceURL == null) {
					throw new FileNotFoundException("Fichero canción no encontrado: " + url);
				}
				media = new Media(resourceURL.toExternalForm());
			}

			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para detener la reproducción
	private void stopCancion() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
			mediaPlayer = null;
		}
		File directorio = new File(tempPath);
		if (directorio.exists() && directorio.isDirectory()) {
			String[] files = directorio.list();
			if (files != null) {
				for (String archivo : files) {
					File fichero = new File(directorio, archivo);
					fichero.delete();
				}
			}
		}
	}

	// Método para pausar la reproducción de la canción
	private void pauseCancion() {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.pause();
		}
	}

	// Añadir botones de control de música en el panel sur
	private void agregarBotonesControlMusica(JPanel panel_sur) {
		JButton btnCancionAnterior = new JButton("");
		btnCancionAnterior
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/anterior32.png")));
		JButton btnDetenerCancion = new JButton("");
		btnDetenerCancion
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/stop.png")));
		JButton btnPausarCancion = new JButton("");
		btnPausarCancion
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/pause.png")));
		JButton btnReproducirCancion = new JButton("");
		btnReproducirCancion
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/play32.png")));
		JButton btnCancionSiguiente = new JButton("");
		btnCancionSiguiente
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppMusic/images/siguiente32.png")));

		btnReproducirCancion.addActionListener(e -> playCancion());
		btnDetenerCancion.addActionListener(e -> stopCancion());
		btnPausarCancion.addActionListener(e -> pauseCancion());

		// Añadir botones al panel
		panel_sur.add(btnCancionAnterior);
		panel_sur.add(btnDetenerCancion);
		panel_sur.add(btnPausarCancion);
		panel_sur.add(btnReproducirCancion);
		panel_sur.add(btnCancionSiguiente);
	}

	public void obtenerListaPlaylist() {

		List<Cancion> canciones;
		panel_Listas.setBorder(new TitledBorder(null, "Listas", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		listModel.clear();
		List<PlayList> playlists = Controlador.INSTANCE.obtenerPlaylistsUsuario();
		for (PlayList playlist : playlists) {
			listModel.addElement(playlist.getNombre());
		}

		playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_Listas.repaint();

		playlistList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && playlistList.getSelectedIndex() != -1) {
					int selectedRowIndex = playlistList.getSelectedIndex();
					PlayList playlistSeleccionada = playlists.get(selectedRowIndex);
					Controlador.INSTANCE.establecerPlaylistActual(playlistSeleccionada);
					tablaCanciones.mostrarResultadosEnTabla(playlistSeleccionada.getCanciones());
					panelCardLayout.add(tablaCanciones, "panelTabla");
					CardLayout card = (CardLayout) panelCardLayout.getLayout();
					card.show(panelCardLayout, "panelTabla");
				}
			}
		});

	}

	public void actualizarTabla(List<Cancion> resultados) {

		/*
		 * TablaCanciones tablaCanciones = new TablaCanciones();
		 * panelCardLayout.add(tablaCanciones, "panelTabla"); CardLayout card =
		 * (CardLayout) panelCardLayout.getLayout(); card.show(panelCardLayout,
		 * "panelTabla");
		 */
	}
}
