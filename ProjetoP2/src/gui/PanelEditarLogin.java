package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelEditarLogin extends JPanel {
	/**
   * 
   */
  private static final long serialVersionUID = 2204248112263535113L;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelEditarLogin() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 503, 319);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblEditarSenha = new JLabel("Nova senha :");
		lblEditarSenha.setBounds(26, 96, 107, 17);
		lblEditarSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarSenha);
		
		JLabel label_9 = new JLabel("Confirmar senha:");
		label_9.setBounds(26, 137, 137, 17);
		label_9.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(label_9);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(169, 133, 114, 26);
		panel.add(passwordField_2);
		
		JLabel label_10 = new JLabel("Dica de Senha:");
		label_10.setBounds(26, 180, 118, 17);
		label_10.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(label_10);
		
		JLabel lblSenhaAtual = new JLabel("Senha Atual:");
		lblSenhaAtual.setBounds(26, 53, 114, 17);
		lblSenhaAtual.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblSenhaAtual);
		
		JLabel lblEditarDados = new JLabel("Editar Dados");
		lblEditarDados.setBounds(163, 12, 99, 17);
		lblEditarDados.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblEditarDados);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    
		    
		  }
		});
		btnAlterar.setBounds(93, 259, 105, 25);
		panel.add(btnAlterar);
		
		JButton button_3 = new JButton("Limpar");
		button_3.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    passwordField_2.setText("");
		    passwordField.setText("");
		    passwordField_1.setText("");
		    textField.setText("");
		  }
		});
		button_3.setBounds(274, 259, 96, 25);
		panel.add(button_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 92, 148, 26);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(135, 52, 148, 28);
		panel.add(passwordField_1);
		
		textField = new JTextField();
		textField.setBounds(151, 176, 132, 26);
		panel.add(textField);
		textField.setColumns(10);

	}
}
