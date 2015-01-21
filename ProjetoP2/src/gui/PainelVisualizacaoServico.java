package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import classes.Servico;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PainelVisualizacaoServico extends JInternalFrame {


	/**
	 * Create the frame.
	 */
	public PainelVisualizacaoServico(Servico servico) {
		setClosable(true);
		setBounds(100, 100, 541, 350);
		
		JLabel lblDeclaracao = new JLabel("Declara\u00E7\u00E3o:");
		lblDeclaracao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblToStringdoServico = new JLabel("New label");
		//Gambiarra para o JLabel receber um toString normal e continuar quebrando linhas:
		String texto = "<html>" + servico.toString() + "</html>"; //Envolver a string em tags <html>
		texto = texto.replaceAll("\n", "<br> <br>");// E mudá-la para substituir "\n" por um "<br>" (tag para quebrar linha em HTML). Usei duas quebras de linha para ficar mais agradável de ler
		lblToStringdoServico.setText(texto);
		lblToStringdoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblToStringdoServico, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addComponent(lblDeclaracao))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDeclaracao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringdoServico)
					.addContainerGap(221, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
