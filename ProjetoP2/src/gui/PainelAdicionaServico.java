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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

import java.awt.CardLayout;
import javax.swing.JComboBox;

public class PainelAdicionaServico extends JInternalFrame {

	JPanel panelExterno = new JPanel();  
	CardLayout layoutPainel = new CardLayout();  
	JPanel panelQuartos = new JPanel();
	JPanel panelBabysitter = new JPanel();
	JPanel panelCarros = new JPanel();
	JPanel panelRestaurante = new JPanel();
	
	/**
	 * Create the frame.
	 */
	public PainelAdicionaServico(Contrato contrato, JDesktopPane painelPrincipal) {
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		Icon imagemQuarto = new ImageIcon(PainelServicos.class.getResource("/resources/quarto.png"));
		JButton btnQuartos = new JButton(imagemQuarto);
		btnQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panelExterno, "1quarto" );
				
			}
		});
		
		Icon imagemCarro = new ImageIcon(PainelServicos.class.getResource("/resources/carro.png"));
		JButton btnAluguelCarros = new JButton(imagemCarro);
		btnAluguelCarros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layoutPainel.show(panelExterno, "2carros"); 
			}
		});
		
		Icon imagemBabysitter = new ImageIcon(PainelServicos.class.getResource("/resources/babysitter.png"));
		JButton btnBabysitter = new JButton(imagemBabysitter);
		
		Icon imagemRestaurante = new ImageIcon(PainelServicos.class.getResource("/resources/restaurante.png"));
		JButton btnRestaurante = new JButton(imagemRestaurante);
		btnRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panelExterno, "4restaurante"); 
				
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				disposeOnClosed();
				
			}
		});
		
		panelExterno.setLayout(layoutPainel);
		
		panelExterno.add(panelQuartos, "1quarto");
		
		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblCamasExtras = new JLabel("Camas extras:");
		
		JComboBox comboBox_1 = new JComboBox();
		
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTipoDeQuarto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(77)
					.addComponent(lblCamasExtras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(268, Short.MAX_VALUE))
		);
		gl_panelQuartos.setVerticalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeQuarto)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCamasExtras)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		panelQuartos.setLayout(gl_panelQuartos);
		
		panelExterno.add(panelBabysitter, "3babysitter");
		
		JCheckBox chckbxTanqueCheio = new JCheckBox("Tanque Cheio");
		chckbxTanqueCheio.setBounds(202, 44, 202, 36);
		panelCarros.add(chckbxTanqueCheio);
		
		panelExterno.add(panelCarros, "2carros");
		
		JCheckBox chckbxCobertura_1 = new JCheckBox("Cobertura");
		chckbxCobertura_1.setBounds(123, 33, 143, 37);
		panelRestaurante.add(chckbxCobertura_1);

		panelExterno.add(panelRestaurante, "4restaurante");
		
		JLabel lblQuartos = new JLabel("Quartos");
		
		JLabel lblCarros = new JLabel("Carros");
		
		JLabel lblBabysitter = new JLabel("Babysitter");
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 767, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(138)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblQuartos))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnQuartos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(54)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAdicionar)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(12)
													.addComponent(lblCarros))
												.addComponent(btnAluguelCarros, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(37)
											.addComponent(btnCancelar))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(34)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblBabysitter)
												.addComponent(btnBabysitter, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
											.addGap(55)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblRestaurante)
												.addComponent(btnRestaurante, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))))))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnAluguelCarros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnQuartos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRestaurante, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
						.addComponent(btnBabysitter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblQuartos)
							.addComponent(lblCarros)
							.addComponent(lblBabysitter))
						.addComponent(lblRestaurante))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnCancelar))
					.addGap(21))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	
	private void disposeOnClosed() {  
	    this.dispose();  
	}    
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
