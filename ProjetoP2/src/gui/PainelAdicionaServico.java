package gui;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import classes.AluguelCarro;
import classes.Babysitter;
import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Restaurante;
import classes.Servico;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

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
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.List;

import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class PainelAdicionaServico extends JInternalFrame {

	private Contrato contrato;
	private JDesktopPane painelPrincipal;
	private List<Quarto> listaQuartosDisponiveis;
	private List<Quarto> listaQuartosDoContrato;
	private List<Hospede> listaHospedes;
	private Servico servicoParaAdicionar;
	private JPanel panelExterno = new JPanel();  
	private CardLayout layoutPainel = new CardLayout();  
	private JPanel panelQuartos = new JPanel();
	private JPanel panelBabysitter = new JPanel();
	private JPanel panelCarros = new JPanel();
	private JPanel panelRestaurante = new JPanel();
	private JComboBox cBoxTipoCarro;
	private JCheckBox chckbxSeguro;
	private JCheckBox chckbxTanqueCheio;
	private JSpinner spinnerDiariasCarro;
	private JComboBox cBoxTipoQuarto;
	private JSpinner spinnerDiarias;
	private JCheckBox chckbxCamasExtras;
	private JCheckBox chckbxCobertura;
	private JTextField txtfi_preco;
	private final String[] TIPOS_CARROS = {"Luxo", "Executivo"};
	private final String[] TIPOS_QUARTOS = {"Presidencial", "Luxo Simples", "Luxo Duplo", "Luxo Triplo", "Executivo Simples",
			"Executivo Duplo", "Executivo Triplo"};	
	
	
	/**
	 * Create the frame.
	 */
	public PainelAdicionaServico(Contrato contrato, JDesktopPane painelPrincipal, List<Quarto> listaQuartosDisponiveis) {
		this.contrato = contrato;
		this.painelPrincipal = painelPrincipal;
		this.listaHospedes = listaHospedes;
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Adicionar Servi\u00E7os");
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		Icon imagemQuarto = new ImageIcon(PainelServicos.class.getResource("/resources/quarto.png"));
		JButton btnQuartos = new JButton(imagemQuarto);
		btnQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panelExterno, "quarto" );
				
			}
		});
		
		Icon imagemCarro = new ImageIcon(PainelServicos.class.getResource("/resources/carro.png"));
		JButton btnAluguelCarros = new JButton(imagemCarro);
		btnAluguelCarros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layoutPainel.show(panelExterno, "carros"); 
			}
		});
		
		Icon imagemBabysitter = new ImageIcon(PainelServicos.class.getResource("/resources/babysitter.png"));
		JButton btnBabysitter = new JButton(imagemBabysitter);
		btnBabysitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panelExterno, "babysitter");
			}
		});
		
		Icon imagemRestaurante = new ImageIcon(PainelServicos.class.getResource("/resources/restaurante.png"));
		JButton btnRestaurante = new JButton(imagemRestaurante);
		btnRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panelExterno, "restaurante"); 
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component comp : panelExterno.getComponents()) {
					if (comp.isVisible() == true) {
						if(comp == panelCarros) {
				    		int diarias = (Integer) spinnerDiariasCarro.getValue();
				    		boolean tipoCarro = cBoxTipoCarro.getSelectedIndex() == 0 ? true : false;
				    		try {
				    			Servico servico = new AluguelCarro(diarias, tipoCarro, chckbxTanqueCheio.isSelected(), chckbxSeguro.isSelected());
								adicionaServico(servico);
					    		JOptionPane.showMessageDialog(null, "Adicionado!");
					    		disposeOnClosed();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
				    		break;
				    	}
				    	else if(comp == panelQuartos) {
				    		PainelAdicionaQuartos painelAddQuarto = new PainelAdicionaQuartos(listaHospedes, getListaQuartosDisponiveis(), getContrato(), getPainelPrincipal());				    			
				    		adicionaNoPainel(painelAddQuarto);
				    		painelAddQuarto.show();
				    		disposeOnClosed();
				    		break;
				    	}
				    	else if(comp == panelBabysitter) {
				    		adicionaServico(new Babysitter());
				    		JOptionPane.showMessageDialog(null, "Adicionado!");
				    		disposeOnClosed();
				    		break;
				    	}
				    	else {
				    		double preco = Double.parseDouble(txtfi_preco.getText());
				    		try {
								adicionaServico(new Restaurante(chckbxCobertura.isSelected(), preco));
								JOptionPane.showMessageDialog(null, "Adicionado!");
								disposeOnClosed();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
				    		break;
				    	}
				    }			
				}
			}
		}
		);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				disposeOnClosed();
				
			}
		});
		
		panelExterno.setLayout(layoutPainel);
		panelQuartos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelExterno.add(panelQuartos, "quarto");
		
		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		
		cBoxTipoQuarto = new JComboBox(TIPOS_QUARTOS);
		
		JLabel lblDiarias = new JLabel("Diárias:");
		
		spinnerDiarias = new JSpinner();
		spinnerDiarias.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		chckbxCamasExtras = new JCheckBox("Camas Extras");
		
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTipoDeQuarto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cBoxTipoQuarto, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(96)
					.addComponent(lblDiarias)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerDiarias, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(chckbxCamasExtras)
					.addGap(63))
		);
		gl_panelQuartos.setVerticalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDiarias)
							.addComponent(spinnerDiarias, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
							.addComponent(cBoxTipoQuarto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipoDeQuarto))
						.addComponent(chckbxCamasExtras))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		
		panelQuartos.setLayout(gl_panelQuartos);
		panelBabysitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelExterno.add(panelBabysitter, "babysitter");
		GroupLayout gl_panelBabysitter = new GroupLayout(panelBabysitter);
		gl_panelBabysitter.setHorizontalGroup(
			gl_panelBabysitter.createParallelGroup(Alignment.LEADING)
				.addGap(0, 763, Short.MAX_VALUE)
		);
		gl_panelBabysitter.setVerticalGroup(
			gl_panelBabysitter.createParallelGroup(Alignment.LEADING)
				.addGap(0, 163, Short.MAX_VALUE)
		);
		panelBabysitter.setLayout(gl_panelBabysitter);
		
		chckbxTanqueCheio = new JCheckBox("Tanque Cheio");
		chckbxTanqueCheio.setBounds(202, 44, 202, 36);
		panelCarros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelExterno.add(panelCarros, "carros");
		
		JLabel lblTipoCarro = new JLabel("Tipo de Carro:");
		
		cBoxTipoCarro = new JComboBox(TIPOS_CARROS);
		
		chckbxSeguro = new JCheckBox("Seguro");
		
		JLabel lblDiariasCarro = new JLabel("Diárias:");
		
		spinnerDiariasCarro = new JSpinner();
		spinnerDiariasCarro.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		GroupLayout gl_panelCarros = new GroupLayout(panelCarros);
		gl_panelCarros.setHorizontalGroup(
			gl_panelCarros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCarros.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTipoCarro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cBoxTipoCarro, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addComponent(lblDiariasCarro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerDiariasCarro, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(chckbxTanqueCheio)
					.addGap(18)
					.addComponent(chckbxSeguro)
					.addGap(34))
		);
		gl_panelCarros.setVerticalGroup(
			gl_panelCarros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCarros.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCarros.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoCarro)
						.addComponent(cBoxTipoCarro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDiariasCarro)
						.addComponent(spinnerDiariasCarro, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxTanqueCheio)
						.addComponent(chckbxSeguro))
					.addContainerGap(134, Short.MAX_VALUE))
		);
		panelCarros.setLayout(gl_panelCarros);
		
		chckbxCobertura = new JCheckBox("Cobertura");
		chckbxCobertura.setBounds(123, 33, 143, 37);
		panelRestaurante.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblPreo = new JLabel("Preço:");

		panelExterno.add(panelRestaurante, "restaurante");
		
		txtfi_preco = new JTextField();
		txtfi_preco.setColumns(10);
		GroupLayout gl_panelRestaurante = new GroupLayout(panelRestaurante);
		gl_panelRestaurante.setHorizontalGroup(
			gl_panelRestaurante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRestaurante.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPreo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfi_preco, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(74)
					.addComponent(chckbxCobertura)
					.addGap(434))
		);
		gl_panelRestaurante.setVerticalGroup(
			gl_panelRestaurante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRestaurante.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRestaurante.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreo)
						.addComponent(txtfi_preco, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxCobertura))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panelRestaurante.setLayout(gl_panelRestaurante);
		
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
	
	private void adicionaServico(Servico servico) {
		contrato.getListaServicos().add(servico); 
	}
	
	private void disposeOnClosed() {  
	    this.dispose();  
	}    
	
	private Contrato getContrato() {
		return contrato;
	}

	public List<Quarto> getListaQuartosDisponiveis() {
		return listaQuartosDisponiveis;
	}
	
	public JDesktopPane getPainelPrincipal() {
		return painelPrincipal;
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
	
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}

}

