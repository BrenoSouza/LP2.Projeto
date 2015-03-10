package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import core.Login;
import core.colecoes.ColecaoDeLogins;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCadastrarFuncionario extends JPanel {
	/**
   * 
   */
  private static final long serialVersionUID = 1211992371328534269L;
  private JTextField txtNome;
	private JPasswordField passwordSenha;
	private JPasswordField passwordConfirmSenha;
	private JTextField txtLogin;
	private JTextField textField;
	private ColecaoDeLogins listaDeLogins;

	/**
	 * Create the panel.
	 * @param listaDeLogins 
	 */
	public PanelCadastrarFuncionario(ColecaoDeLogins listaDeLogins) {
		setLayout(null);
		this.listaDeLogins = listaDeLogins;
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 520, 330);
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
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(29, 152, 146, 17);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblSenha);
		
		JLabel lblConfirmaSenha = new JLabel("Confirmar senha:");
		lblConfirmaSenha.setBounds(29, 197, 137, 17);
		lblConfirmaSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblConfirmaSenha);
		
		passwordSenha = new JPasswordField();
		passwordSenha.setBounds(96, 147, 152, 27);
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
		btnCadastrar.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    String password = new String(passwordSenha.getPassword());
		    String passwordConfirm = new String(passwordConfirmSenha.getPassword());
		    if (verificaSenhasIguais(password, passwordConfirm)) {
		      Login login = new Login(txtNome.getText(), txtLogin.getText(), password, textField.getText());
		      PanelCadastrarFuncionario.this.listaDeLogins.adicionaContaLogin(login);
		      JOptionPane.showMessageDialog(null, "Funcionário Cadastrado!");
		    }
		    else {
		      JOptionPane.showMessageDialog(null, "O usuário não foi cadastrado!");
		    }
		    
		  }
		});
		btnCadastrar.setBounds(115, 293, 117, 25);
		panel.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		  }
		});
		btnCancelar.setBounds(293, 293, 117, 25);
		panel.add(btnCancelar);

		
	
	}
	
	private boolean verificaSenhasIguais(String senha1, String senha2) {
	  if (senha1.equals(senha2)) {
	    return true;
	  }
	  return false;
	}
}
