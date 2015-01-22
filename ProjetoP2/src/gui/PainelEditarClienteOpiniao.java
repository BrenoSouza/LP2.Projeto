package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class PainelEditarClienteOpiniao extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public PainelEditarClienteOpiniao() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblComentrio = new JLabel("Comentário");
		lblComentrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComentrio)
					.addContainerGap(353, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComentrio)
					.addContainerGap(242, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
