package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

import core.colecoes.ColecaoDeLogins;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class PainelLogin<DesktopPane> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private ColecaoDeLogins colecaoDeContas;
	private DesktopPane painelPrincipal;

	/**
	 * Create the dialog.
	 */
	public PainelLogin(ColecaoDeLogins colecaoDeContas) {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 472, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.colecaoDeContas = colecaoDeContas;
		this.painelPrincipal = painelPrincipal;
		
		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBackground(SystemColor.window);
		lblUsurio.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		
		passwordField = new JPasswordField();
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				JOptionPane.showMessageDialog(null, getColecaoDeContas().getTamanhoListaLogin());

				if(getColecaoDeContas().verificaLoginESenha(txtUsuario.getText(), password)) {
					dispose();
				}
				else					
					JOptionPane.showMessageDialog(null, "Senha ou login incorretos!");

			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

			}
		});
		
		JButton btnEsqueceuSuaSenha = new JButton("Esqueceu sua Senha?");
		btnEsqueceuSuaSenha.setForeground(SystemColor.inactiveCaption);
		btnEsqueceuSuaSenha.setContentAreaFilled(false);
		btnEsqueceuSuaSenha.setFont(new Font("Dialog", Font.BOLD, 11));
		btnEsqueceuSuaSenha.setBorderPainted(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSenha)
								.addComponent(lblUsurio))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtUsuario)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(btnEsqueceuSuaSenha)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnAcessar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
					.addGap(74))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(122, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsurio))
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEsqueceuSuaSenha, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAcessar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	private ColecaoDeLogins getColecaoDeContas() {
		return colecaoDeContas;
	}
	
}
