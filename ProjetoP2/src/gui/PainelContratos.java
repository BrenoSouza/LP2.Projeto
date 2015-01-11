package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PainelContratos extends JInternalFrame {



	/**
	 * Create the frame.
	 */
	public PainelContratos() {
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelContratos.class.getResource("/resources/contrato_icon.png")));
		setTitle("Contratos");
		setClosable(true);
		setBounds(100, 0, 752, 450);
		
		JLabel lblNewLabel = new JLabel("----------");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(154)
					.addComponent(lblNewLabel)
					.addContainerGap(234, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(112)
					.addComponent(lblNewLabel)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
