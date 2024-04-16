package umu.tds.AppMusic.gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
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
import umu.tds.AppMusic.modelo.PlayList;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;

public class VentanaMisListas extends JPanel {
	private JPanel panelb;
    private JTable tablaCanciones;
    private JTable table_1;
    private final List<PlayList> playlists = new LinkedList<>();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VentanaMisListas() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setLayout(new BorderLayout(0, 0));

	/*	JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		
		
		List<PlayList> playlists = Controlador.INSTANCE.obtenerPlaylist();
		PlayList[] playlistsArray = playlists.toArray(new PlayList[0]);
		
	        
	        // Crear la JList con el array de objetos
		JList<PlayList> list = new JList<>(playlistsArray);
	        
	     // Agregar la JList al frame
	        panel.add(new JScrollPane(list), BorderLayout.CENTER);
		/*if(playlists.isEmpty()) {
			System.out.println("vacia");
		}
		for ( PlayList p : playlists) {
			System.out.println(p.getNombre());
		}*/
		
		setBorder(new TitledBorder(null, "Listas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		  // Crear una lista de nombres de playlist
		List<PlayList> playlists = Controlador.INSTANCE.obtenerPlaylistsUsuario();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (PlayList playlist : playlists) {
            listModel.addElement(playlist.getNombre());
        }

        JList<String> playlistList = new JList<>(listModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar la lista a un JScrollPane para permitir desplazamiento si hay muchos elementos
        JScrollPane scrollPane = new JScrollPane(playlistList);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
        
     // Obtener el objeto seleccionado de la lista
        String selectedPlaylist = playlistList.getSelectedValue();

        if (selectedPlaylist != null) {
        	for (PlayList playlist : playlists) {
                if(playlist.getNombre().equals(selectedPlaylist)) {
                //	Controlador.INSTANCE.obtenerPlaylist(playlist.getId());
                	
                }
            }
        } else {
          
        }
	}
	

}
