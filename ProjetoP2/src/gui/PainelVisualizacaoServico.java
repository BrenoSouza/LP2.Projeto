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
		
		JLabel lblDeclaração = new JLabel("Declara\u00E7\u00E3o:");
		lblDeclaração.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblToStringdoServico = new JLabel("New label");
		lblToStringdoServico.setText("<html><br>" + servico.toString() + "</br></html>");
		//Pequeno problema. OJlabel não reconhece as tags /n para quebrar uma linha. Um jeito é vc cercar a string com essas tags html ali em cima, mas elas só quebram quando chega no final da janela.
		lblToStringdoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDeclaração)
						.addComponent(lblToStringdoServico))
					.addContainerGap(544, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDeclaração)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringdoServico)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
