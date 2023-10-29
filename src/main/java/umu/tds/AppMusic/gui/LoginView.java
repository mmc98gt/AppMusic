package umu.tds.AppMusic.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import umu.tds.AppMusic.controlador.Controlador;

import javax.swing.border.EmptyBorder;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}

	/**********************************************************************
	 * Procurar organizar la creación de una ventana en varios métodos con el fin de
	 * facilitar su comprensión. Esta clase muestra un ejemplo
	 **********************************************************************/

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login AppVideo");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout());

		crearPanelTitulo();
		crearPanelLogin();
		crearPanelRegistro();

		frmLogin.setResizable(false);
		frmLogin.pack();
	}

	private void crearPanelTitulo() {
		JPanel panel_Norte = new JPanel();
		frmLogin.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblTitulo = new JLabel("AppMusic");
		lblTitulo.setIcon(new ImageIcon(LoginView.class.getResource("/umu/tds/AppMusic/images/musica (1).png")));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setForeground(Color.DARK_GRAY);
		panel_Norte.add(lblTitulo);
	}

	private void crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(10, 20, 20, 20));
		frmLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));

		panelLogin.add(crearPanelUsuarioPassw(), BorderLayout.NORTH);
		panelLogin.add(crearPanelBotones(), BorderLayout.CENTER);

	}

	private JPanel crearPanelUsuarioPassw() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

		// Panel Campo Login
		JPanel panelCampoUsuario = new JPanel();
		panelCampos.add(panelCampoUsuario);
		panelCampoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelCampoUsuario.setLayout(new BorderLayout(0, 0));

		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsuario.setIcon(new ImageIcon(LoginView.class.getResource("/umu/tds/AppMusic/images/usuario.png")));
		panelCampoUsuario.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textUsuario = new JTextField();
		panelCampoUsuario.add(textUsuario, BorderLayout.EAST);
		textUsuario.setColumns(15);

		// Panel Campo Password
		JPanel panelCampoPassword = new JPanel();
		panelCampos.add(panelCampoPassword);
		panelCampoPassword.setLayout(new BorderLayout(0, 0));

		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		lblPassword.setIcon(new ImageIcon(LoginView.class.getResource("/umu/tds/AppMusic/images/contrasena.png")));
		panelCampoPassword.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textPassword = new JPasswordField();
		panelCampoPassword.add(textPassword, BorderLayout.EAST);
		textPassword.setColumns(15);

		return panelCampos;
	}

	private JPanel crearPanelBotones() {
		JPanel panelBotonesLogin = new JPanel();
		panelBotonesLogin.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelBotonesLogin.setLayout(new BorderLayout(0, 0));

		JButton btnLogin = new JButton("    Login    ");
		panelBotonesLogin.add(btnLogin, BorderLayout.WEST);

		// hay que añadir el manejador
		JButton btnLoginGit = new JButton("Login con GitHub");
		panelBotonesLogin.add(btnLoginGit, BorderLayout.CENTER);

		JPanel panelBotonSalir = new JPanel();
		panelBotonesLogin.add(panelBotonSalir, BorderLayout.EAST);

		JButton btnSalir = new JButton("Salir");
		panelBotonSalir.add(btnSalir);

		addManejadorBotonLogin(btnLogin);
		// addManejadorBotonRegistro(btnRegistro);
		addManejadorBotonSalir(btnSalir);

		return panelBotonesLogin;
	}

	private void crearPanelRegistro() {
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBorder(new EmptyBorder(0, 0, 0, 0));
		frmLogin.getContentPane().add(panelRegistro, BorderLayout.SOUTH);
		panelRegistro.setLayout(new BorderLayout(0, 0));

		JLabel lblRegistro = new JLabel("¿Todavía no tienes cuenta?");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("DialogInput", Font.BOLD, 14));
		panelRegistro.add(lblRegistro, BorderLayout.NORTH);

		JButton btnRegistro = new JButton("Registrate");
		btnRegistro.setIcon(new ImageIcon(LoginView.class.getResource("/umu/tds/AppMusic/images/registrarse.png")));
		btnRegistro.setFont(new Font("DialogInput", Font.BOLD, 14));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelRegistro.add(btnRegistro, BorderLayout.CENTER);
		panelRegistro.setBorder(new EmptyBorder(10, 20, 20, 20));
		
		
		addManejadorBotonRegistro(btnRegistro);
	}

	private void addManejadorBotonSalir(JButton btnSalir) {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				System.exit(0);
			}
		});
	}

	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registro = new RegistroView(frmLogin);
				registro.setLocationRelativeTo(frmLogin);
				registro.setVisible(true);
				frmLogin.dispose();
			}
		});
	}

	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE.loginUsuario(textUsuario.getText(),
						new String(textPassword.getPassword()));

				if (login) {
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					frmLogin.dispose();
				} else
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}
