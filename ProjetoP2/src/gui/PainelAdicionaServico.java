package gui;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import classes.Contrato;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PainelAdicionaServico extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public PainelAdicionaServico(Contrato contrato, JDesktopPane painelPrincipal) {
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 790, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 368, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);

	}

}
