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
		setBounds(100, 100, 253, 302);
		
		JLabel lblDeclaracao = new JLabel("Declara\u00E7\u00E3o:");
		lblDeclaracao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblToStringdoServico = new JLabel("New label");
		lblToStringdoServico.setText("<html><br>" + servico.toString() + "</br></html>");
		//Pequeno problema. OJlabel n�o reconhece as tags /n para quebrar uma linha. Um jeito � vc cercar a string com essas tags html ali em cima, mas elas s� quebram quando chega no final da janela.
		lblToStringdoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDeclaracao)
						.addComponent(lblToStringdoServico))
					.addContainerGap(544, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDeclaracao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringdoServico)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
