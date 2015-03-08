package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PanelCadastrarFuncionario extends JPanel {
	private JTextField txtNome;
	private JPasswordField passwordSenha;
	private JPasswordField passwordConfirmSenha;
	private JTextField txtLogin;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelCadastrarFuncionario() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 520, 368);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(29, 55, 51, 17);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblNome);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(29, 103, 49, 17);
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblLogin);
		
		txtNome = new JTextField();
		txtNome.setBounds(86, 50, 360, 28);
		txtNome.setColumns(10);
		panel.add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha de entrada:");
		lblSenha.setBounds(29, 152, 146, 17);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblSenha);
		
		JLabel lblConfirmaSenha = new JLabel("Confirmar senha:");
		lblConfirmaSenha.setBounds(29, 197, 137, 17);
		lblConfirmaSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblConfirmaSenha);
		
		passwordSenha = new JPasswordField();
		passwordSenha.setBounds(182, 147, 152, 27);
		panel.add(passwordSenha);
		
		passwordConfirmSenha = new JPasswordField();
		passwordConfirmSenha.setBounds(182, 192, 152, 28);
		panel.add(passwordConfirmSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(86, 98, 146, 28);
		txtLogin.setColumns(10);
		panel.add(txtLogin);
		
		JLabel lblDicaDeSenha = new JLabel("Dica de Senha:");
		lblDicaDeSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDicaDeSenha.setBounds(29, 248, 124, 15);
		panel.add(lblDicaDeSenha);
		
		textField = new JTextField();
		textField.setBounds(157, 242, 276, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCadastrarFuncionrio = new JLabel("Cadastrar Funcionário");
		lblCadastrarFuncionrio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarFuncionrio.setBounds(182, 23, 204, 15);
		panel.add(lblCadastrarFuncionrio);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(115, 315, 117, 25);
		panel.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(302, 315, 117, 25);
		panel.add(btnCancelar);

		
		
	}
}
