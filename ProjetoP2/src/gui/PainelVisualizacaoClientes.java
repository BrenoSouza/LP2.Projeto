package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import classes.Hospede;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

public class PainelVisualizacaoClientes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public PainelVisualizacaoClientes(Hospede hospede) {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblInformaes = new JLabel("Informações:");
		lblInformaes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblToStringHospede = new JLabel("");
		String texto = "<html>" + hospede.toString() + "</html>"; //Envolver a string em tags <html>
		texto = texto.replaceAll("\n", "<br> <br>");
		lblToStringHospede.setText(texto);
		lblToStringHospede.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblToStringHospede, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(lblInformaes, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInformaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringHospede)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
