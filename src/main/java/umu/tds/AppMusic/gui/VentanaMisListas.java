package umu.tds.AppMusic.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import umu.tds.AppMusic.controlador.Controlador;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VentanaMisListas extends JPanel {
	private JFrame frmVentanaMisListas;
	private boolean eliminar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VentanaMisListas(boolean eliminar) {
		this.eliminar = eliminar;
		initialize();

	}

	public void mostrarVentana() {
		frmVentanaMisListas.setLocationRelativeTo(null);
		frmVentanaMisListas.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmVentanaMisListas = new JFrame();
		frmVentanaMisListas.setTitle("AppMusic");
		frmVentanaMisListas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVentanaMisListas.setBounds(100, 100, 214, 245);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frmVentanaMisListas.setContentPane(contentPane);

		// Crear una lista de nombres de playlist
		List<PlayList> playlists = Controlador.INSTANCE.obtenerPlaylistsUsuario();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (PlayList playlist : playlists) {
			listModel.addElement(playlist.getNombre());
		}

		JList<String> playlistList = new JList<>(listModel);
		playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Agregar la lista a un JScrollPane para permitir desplazamiento si hay muchos
		// elementos
		JScrollPane scrollPane = new JScrollPane(playlistList);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);

		JLabel lblTextoAnadir = new JLabel("Elige la playlist que desea modificar");
		panel.add(lblTextoAnadir);

		playlistList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && playlistList.getSelectedIndex() != -1) {
					int selectedRowIndex = playlistList.getSelectedIndex();
					PlayList playlistSeleccionada = playlists.get(selectedRowIndex);
					List<Cancion> canciones = Controlador.INSTANCE.obtenerCancionesFavoritas();
					if (eliminar) {
						int respuesta = JOptionPane
								.showConfirmDialog(null,
										"¿Quieres eliminar canciones de la playlist \""
												+ playlistSeleccionada.getNombre() + "\"?",
										"Eliminar Canciones", JOptionPane.YES_NO_OPTION);
						if (respuesta == JOptionPane.YES_OPTION) {
							Controlador.INSTANCE.deleteCancionesToPlaylist(playlistSeleccionada, canciones);
							JOptionPane.showMessageDialog(frmVentanaMisListas, "Se han eliminado las canciones",
									"Canciones eliminadas", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						int respuesta = JOptionPane.showConfirmDialog(null,
								"¿Quieres añadir canciones a la playlist \"" + playlistSeleccionada.getNombre() + "\"?",
								"Añadir Canciones", JOptionPane.YES_NO_OPTION);
						if (respuesta == JOptionPane.YES_OPTION) {
							Controlador.INSTANCE.addCancionesToPlaylist(playlistSeleccionada, canciones);
							JOptionPane.showMessageDialog(frmVentanaMisListas, "Se han añadido las canciones",
									"Canciones añadidas", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					frmVentanaMisListas.dispose();
				}
			}
		});

	}

}
