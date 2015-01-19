package gui;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import classes.Contrato;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class PainelAdicionaServico extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public PainelAdicionaServico(Contrato contrato, JDesktopPane painelPrincipal) {
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		Icon imagemQuarto = new ImageIcon(PainelServicos.class.getResource("/resources/quarto.png"));
		JButton btnQuartos = new JButton(imagemQuarto);
		
		Icon imagemCarro = new ImageIcon(PainelServicos.class.getResource("/resources/carro.png"));
		JButton btnAluguelCarros = new JButton(imagemCarro);
		
		Icon imagemBabysitter = new ImageIcon(PainelServicos.class.getResource("/resources/babysitter.png"));
		JButton btnBabysitter = new JButton(imagemBabysitter);
		
		Icon imagemRestaurante = new ImageIcon(PainelServicos.class.getResource("/resources/restaurante.png"));
		JButton btnRestaurante = new JButton(imagemRestaurante);
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				disposeOnClosed();
				
			}
		});
		
		JLabel lblQuartos = new JLabel("Quartos");
		
		JLabel lblBabysitter = new JLabel("Babysitter");
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		
		JLabel lblCarros = new JLabel("Carros");

		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(152)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnQuartos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblQuartos)))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAluguelCarros, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblCarros))
						.addComponent(btnAdicionar))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBabysitter, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBabysitter, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRestaurante)
								.addComponent(btnRestaurante, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(btnCancelar)))
					.addGap(144))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBabysitter, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
						.addComponent(btnRestaurante, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnAluguelCarros, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnQuartos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuartos)
								.addComponent(lblCarros))
							.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdicionar)
								.addComponent(btnCancelar))
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRestaurante)
								.addComponent(lblBabysitter))
							.addContainerGap())))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	private void disposeOnClosed() {  
	    this.dispose();  
	}    
}
