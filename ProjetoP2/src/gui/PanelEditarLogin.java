package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PanelEditarLogin extends JPanel {
	private JTextField txtLogin;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	private JTextField textField_4;
	private JTextField txtNome;

	/**
	 * Create the panel.
	 */
	public PanelEditarLogin() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 503, 334);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEditarLogin = new JLabel("Editar Login:");
		lblEditarLogin.setBounds(26, 101, 107, 17);
		lblEditarLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(135, 97, 114, 25);
		txtLogin.setColumns(10);
		panel.add(txtLogin);
		
		JLabel lblEditarSenha = new JLabel("Nova senha :");
		lblEditarSenha.setBounds(26, 143, 137, 17);
		lblEditarSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarSenha);
		
		JLabel label_9 = new JLabel("Confirmar senha:");
		label_9.setBounds(26, 183, 137, 17);
		label_9.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(label_9);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(169, 179, 114, 25);
		panel.add(passwordField_2);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setBounds(147, 217, 127, 26);
		panel.add(passwordField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(135, 140, 114, 24);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		JLabel label_10 = new JLabel("Dica de Senha:");
		label_10.setBounds(26, 221, 118, 17);
		label_10.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(label_10);
		
		JLabel lblEditarNome = new JLabel("Editar Nome:");
		lblEditarNome.setBounds(26, 57, 114, 17);
		lblEditarNome.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(135, 53, 148, 25);
		txtNome.setColumns(10);
		panel.add(txtNome);
		
		JLabel lblEditarDados = new JLabel("Editar Dados");
		lblEditarDados.setBounds(184, 24, 99, 17);
		lblEditarDados.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarDados);
		
		JButton button_2 = new JButton("Cadastrar");
		button_2.setBounds(113, 282, 105, 25);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Cancelar");
		button_3.setBounds(275, 282, 96, 25);
		panel.add(button_3);

	}

}
