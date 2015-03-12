package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.colecoes.ColecaoDeLogins;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class PainelLogin<DesktopPane> extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7190192353826010805L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private ColecaoDeLogins colecaoDeContas;
	private Main frame;
	private final JLabel background = new JLabel("");

	public PainelLogin(ColecaoDeLogins colecaoDeContas, Main frame) {
	  setTitle("Hotel Riviera - Login");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 520, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.colecaoDeContas = colecaoDeContas;
		this.frame = frame;
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(107, 294, 129, 39);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  String password = new String(passwordField.getPassword());

				if(PainelLogin.this.colecaoDeContas.verificaLoginESenha(txtUsuario.getText(), password)) {
				    getFrame().setVisible(true);
				dispose();
				}
				else					
					JOptionPane.showMessageDialog(null, "Senha ou login incorretos!");

			}
		});
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setBounds(260, 294, 129, 39);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		
		JButton btnEsqueceuSuaSenha = new JButton("Esqueceu sua Senha?");
		btnEsqueceuSuaSenha.setBounds(166, 252, 173, 19);
		btnEsqueceuSuaSenha.setForeground(Color.BLACK);
		btnEsqueceuSuaSenha.setContentAreaFilled(false);
		btnEsqueceuSuaSenha.setFont(new Font("Dialog", Font.BOLD, 11));
		btnEsqueceuSuaSenha.setBorderPainted(false);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(107, 110, 282, 130);
		panel.setBackground(UIManager.getColor("Button.highlight"));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblSenha = new JLabel("");
		lblSenha.setBounds(49, 78, 24, 24);
		lblSenha.setIcon(new ImageIcon(PainelLogin.class.getResource("/resources/padlock.png")));
		panel.add(lblSenha);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblUsurio = new JLabel("");
		lblUsurio.setBounds(49, 30, 24, 24);
		lblUsurio.setIcon(new ImageIcon(PainelLogin.class.getResource("/resources/male.png")));
		panel.add(lblUsurio);
		lblUsurio.setBackground(SystemColor.window);
		lblUsurio.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(83, 30, 133, 25);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(83, 78, 133, 25);
		panel.add(passwordField);
		contentPanel.add(btnEsqueceuSuaSenha);
		contentPanel.add(btnEntrar);
		contentPanel.add(btnCancelar);
		background.setIcon(new ImageIcon(PainelLogin.class.getResource("/resources/background.jpg")));
		background.setBounds(0, 0, 520, 374);
		contentPanel.add(background);
	}

	
	private Main getFrame() {
		return frame;
	}
}
