package umu.tds.AppMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.tds.AppMusic.controlador.Controlador;

public class ConfirmacionPlaylist {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmacionPlaylist window = new ConfirmacionPlaylist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfirmacionPlaylist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 295, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		crearPanelRegistro();
	}
	
	private void crearPanelRegistro() {
		JPanel panelRegistro = new JPanel();
		frame.getContentPane().add(panelRegistro, BorderLayout.NORTH);
		panelRegistro.setLayout(new BorderLayout(0, 0));

		JLabel lblRegistro = new JLabel("¿Quieres crear la playlist?");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("DialogInput", Font.BOLD, 14));
		panelRegistro.add(lblRegistro, BorderLayout.NORTH);

		JButton btnRegistro = new JButton("    Si   ");
		btnRegistro.setIcon(null);
		btnRegistro.setFont(new Font("DialogInput", Font.BOLD, 14));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelRegistro.add(btnRegistro, BorderLayout.WEST);
		panelRegistro.setBorder(new EmptyBorder(10, 20, 20, 20));
		
		JButton btnNewButton = new JButton("No");
		btnNewButton.setFont(new Font("DialogInput", Font.BOLD, 14));
		panelRegistro.add(btnNewButton, BorderLayout.CENTER);
		
		
		//addManejadorBotonRegistro(btnRegistro);
	}

}