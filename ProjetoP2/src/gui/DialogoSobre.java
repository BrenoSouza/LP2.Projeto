package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DialogoSobre extends JDialog {

	private static final long serialVersionUID = -7561965211179591248L;
	private final JPanel contentPanel = new JPanel();

	public DialogoSobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoSobre.class.getResource("/resources/hotel39.png")));
		setTitle("Sobre");
		setResizable(false);
		setBounds(100, 100, 570, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		// Informações do projeto
		JLabel lblPrograma = new JLabel("Programa de administração do Hotel Riviera Campina");
		lblPrograma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblProjeto = new JLabel("Projeto da Disciplina de Programação 2 do período 2014.2 da UFCG");
		lblProjeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMembros = new JLabel("<html>Membros do projeto:<br>\r" + Main.quebraDeLinha + "- Gabriel Dantas (gabriel.dantas@ccc.ufcg.edu.br)<br>\r" + Main.quebraDeLinha + "- Gabriel Morais (gabriel.araujo@ccc.ufcg.edu.br)<br>\r" + Main.quebraDeLinha + "- Breno Souza (jose.souza@ccc.ufcg.edu.br)<br>\r" + Main.quebraDeLinha + "</html>");
		lblMembros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblLink = new JLabel("Link do repositório: github.com/BrenoSouza/LP2.Projeto");
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCredito = new JLabel("<html>\r" + Main.quebraDeLinha + "Ícones feitos por Freepik (www.freepik.com) de Flaticon (www.flaticon.com). <br>Licenciados sob Creative Commons BY 3.0 (http://creativecommons.org/licenses/by/3.0/)\r" + Main.quebraDeLinha + "</html>");
		lblCredito.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrograma)
						.addComponent(lblProjeto)
						.addComponent(lblMembros))
					.addContainerGap(76, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(204, Short.MAX_VALUE)
					.addComponent(lblLink)
					.addGap(19))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblCredito)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrograma)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProjeto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMembros)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCredito)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(lblLink))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
