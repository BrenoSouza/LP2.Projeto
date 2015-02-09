package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import classes.AluguelCarro;
import classes.Babysitter;
import classes.Contrato;
import classes.Quarto;
import classes.Restaurante;
import classes.Servico;
import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;

public class PainelAdicionaServico extends JInternalFrame {

	private Contrato contrato;
	private JDesktopPane painelPrincipal;
	private ColecaoDeQuartos listaDeQuartos;
	private List<Quarto> listaQuartosDoContrato;
	private ColecaoDeHospedes listaHospedes;
	private Servico servicoParaAdicionar;
	private JPanel panelExterno = new JPanel();  
	private CardLayout layoutPainel = new CardLayout();  
	private JPanel panelQuartos = new JPanel();
	private JPanel panelBabysitter = new JPanel();
	private JPanel panelCarros = new JPanel();
	private JPanel panelRestaurante = new JPanel();
	private JPanel panelEditaRestaurante;
	private JPanel panelEditaQuarto;
	private JPanel panelEditaBabysitter;
	private JPanel panelEditaCarro;
	private JComboBox cBoxTipoCarro;
	private JCheckBox chckbxSeguro;
	private JCheckBox chckbxTanqueCheio;
	private JButton btnQuartos;
	private JButton btnBabysitter;
	private JButton btnRestaurante;
	private JButton btnAluguelCarros;
	private JSpinner spinnerDiariasCarro;
	private JComboBox cBoxTipoQuarto;
	private JSpinner spinnerDiarias;
	private JCheckBox chckbxCamasExtras;
	private JCheckBox chckbxCobertura;
	private JTextField txtfi_preco;
	private Servico servico;
	private final String[] TIPOS_CARROS = {"Luxo", "Executivo"};
	private final String[] TIPOS_QUARTOS = {"Presidencial", "Luxo Simples", "Luxo Duplo", "Luxo Triplo", "Executivo Simples",
			"Executivo Duplo", "Executivo Triplo"};	
	private JTextField txtFldPrecoRestaurante;
	private JTextField textField_1;
	private JTextField textField_horaSaida;
	private JTextField textField_horasSolicitadas;
	private JTextField textField_horasDobradas;
	private JTextField textField_tipoQuarto;
	
	
	/**
	 * Create the frame.
	 */
	public PainelAdicionaServico(Servico servico, Contrato contrato, JDesktopPane painelPrincipal, ColecaoDeQuartos listaDeQuartos, ColecaoDeHospedes listaHospedes) {
		this.contrato = contrato;
		this.painelPrincipal = painelPrincipal;
		this.listaHospedes = listaHospedes;
		this.listaDeQuartos = listaDeQuartos;
		this.servico = (Servico) servico;
		
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Adicionar Servi\u00E7os");
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		if (servico == null) {
			if (servico instanceof Quarto) {
				btnBabysitter.setVisible(false);
				btnAluguelCarros.setVisible(false);
				btnRestaurante.setVisible(false);
			}
			else if (servico instanceof Restaurante) {
				btnBabysitter.setVisible(false);
				btnAluguelCarros.setVisible(false);
				btnQuartos.setVisible(false);
			}
			else if (servico instanceof Babysitter) {
				btnQuartos.setVisible(false);
				btnAluguelCarros.setVisible(false);
				btnRestaurante.setVisible(false);
			}
			else if (servico instanceof AluguelCarro) {
				btnBabysitter.setVisible(false);
				btnQuartos.setVisible(false);
				btnRestaurante.setVisible(false);
			}
 		}
		
		Icon imagemQuarto = new ImageIcon(PainelServicos.class.getResource("/resources/quarto.png"));
		btnQuartos = new JButton(imagemQuarto);
		btnQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getServico() == null) 
					layoutPainel.show(panelExterno, "quarto" );
				else {
					layoutPainel.show(panelExterno, "edita_quarto");
				}
				
			}
		});
		
		Icon imagemCarro = new ImageIcon(PainelServicos.class.getResource("/resources/carro.png"));
		btnAluguelCarros = new JButton(imagemCarro);
		btnAluguelCarros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getServico() == null) ) 
					layoutPainel.show(panelExterno, "carros" );
				else {
					layoutPainel.show(panelExterno, "edita_carro");
				}
			}
		});
		
		Icon imagemBabysitter = new ImageIcon(PainelServicos.class.getResource("/resources/babysitter.png"));
		btnBabysitter = new JButton(imagemBabysitter);
		btnBabysitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getServico() == null) ) 
					layoutPainel.show(panelExterno, "babysitter");
				else {
					layoutPainel.show(panelExterno, "edita_babysitter");
				}
			}
		});
		
		Icon imagemRestaurante = new ImageIcon(PainelServicos.class.getResource("/resources/restaurante.png"));
		btnRestaurante = new JButton(imagemRestaurante);
		btnRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getServico() == null) ) 
					layoutPainel.show(panelExterno, "restaurante");
				else {
					layoutPainel.show(panelExterno, "edita_restaurante");
				}
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
				    		PainelAdicionaQuartos painelAddQuarto = new PainelAdicionaQuartos(getColecaoHospedes(), getListaDeQuartos(), getContrato(), getPainelPrincipal());				    			
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
				    	else if (comp == panelRestaurante){
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
				    	else if(comp == panelEditaQuarto) {
				    		
				    		disposeOnClosed();
				    		break;
				    	}
				    	else if(comp == panelEditaRestaurante) {
				    		
				    		disposeOnClosed();
				    		break;
				    	}
				    	else if(comp == panelEditaBabysitter) {
				    		
				    		disposeOnClosed();
				    		break;
				    	}
				    	else if(comp == panelEditaCarro) {
				    		
				    		disposeOnClosed();
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
		
		panelEditaRestaurante = new JPanel();
		
		panelEditaRestaurante.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaRestaurante, "edita_restaurante");
		
		JLabel lblAlterarPreo = new JLabel("Alterar Preço:");
		
		txtFldPrecoRestaurante = new JTextField();
		txtFldPrecoRestaurante.setColumns(10);
		
		
		JCheckBox checkBox_IsCobertura = new JCheckBox("Cobertura");
		GroupLayout gl_panelEditaRestaurante = new GroupLayout(panelEditaRestaurante);
		gl_panelEditaRestaurante.setHorizontalGroup(
			gl_panelEditaRestaurante.createParallelGroup(Alignment.LEADING)
				.addGap(0, 767, Short.MAX_VALUE)
				.addGroup(gl_panelEditaRestaurante.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAlterarPreo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFldPrecoRestaurante, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(74)
					.addComponent(checkBox_IsCobertura)
					.addGap(434))
		);
		gl_panelEditaRestaurante.setVerticalGroup(
			gl_panelEditaRestaurante.createParallelGroup(Alignment.LEADING)
				.addGap(0, 167, Short.MAX_VALUE)
				.addGroup(gl_panelEditaRestaurante.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaRestaurante.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlterarPreo)
						.addComponent(txtFldPrecoRestaurante, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox_IsCobertura))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panelEditaRestaurante.setLayout(gl_panelEditaRestaurante);
		
		panelEditaCarro = new JPanel();
		
		panelEditaCarro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaCarro, "edita_carro");
		
		JLabel label_1 = new JLabel("Tipo de Carro:");
		
		JLabel lblAcrescentarDirias = new JLabel("Acrescentar Diárias:");
		
		JSpinner spinner = new JSpinner();
		
		JCheckBox checkBox_1 = new JCheckBox("Tanque Cheio");
		
		JCheckBox checkBox_2 = new JCheckBox("Seguro");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panelEditaCarro = new GroupLayout(panelEditaCarro);
		gl_panelEditaCarro.setHorizontalGroup(
			gl_panelEditaCarro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaCarro.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(lblAcrescentarDirias)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(checkBox_1)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(checkBox_2)
					.addGap(34))
		);
		gl_panelEditaCarro.setVerticalGroup(
			gl_panelEditaCarro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaCarro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaCarro.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(checkBox_2)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAcrescentarDirias)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox_1))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panelEditaCarro.setLayout(gl_panelEditaCarro);
		
		panelEditaBabysitter = new JPanel();
		
		panelEditaBabysitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaBabysitter, "edita_babysitter");
		
		JLabel lblDataSaida = new JLabel("Hora da Saida:");
		
		textField_horaSaida = new JTextField();
		textField_horaSaida.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Horas Solicitadas:");
		
		JLabel lblHorasDobradas = new JLabel("Horas Dobradas:");
		
		textField_horasSolicitadas = new JTextField();
		textField_horasSolicitadas.setColumns(10);
		
		textField_horasDobradas = new JTextField();
		textField_horasDobradas.setColumns(10);
		
		
		GroupLayout gl_panelEditaBabysitter = new GroupLayout(panelEditaBabysitter);
		gl_panelEditaBabysitter.setHorizontalGroup(
			gl_panelEditaBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaBabysitter.createSequentialGroup()
					.addGap(27)
					.addComponent(lblDataSaida)
					.addGap(4)
					.addComponent(textField_horaSaida, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblNewLabel)
					.addGap(2)
					.addComponent(textField_horasSolicitadas, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(lblHorasDobradas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_horasDobradas, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_panelEditaBabysitter.setVerticalGroup(
			gl_panelEditaBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaBabysitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaBabysitter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataSaida)
						.addComponent(textField_horaSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(lblHorasDobradas)
						.addComponent(textField_horasSolicitadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_horasDobradas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		panelEditaBabysitter.setLayout(gl_panelEditaBabysitter);
		
		JPanel panelEditaQuarto = new JPanel();
		panelEditaQuarto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaQuarto, "edita_quarto");
		
		JLabel label = new JLabel("Tipo de Quarto:");
		
		JCheckBox checkBox = new JCheckBox("Camas Extras");
		
		textField_tipoQuarto = new JTextField();
		textField_tipoQuarto.setColumns(10);
		GroupLayout gl_panelEditaQuarto = new GroupLayout(panelEditaQuarto);
		gl_panelEditaQuarto.setHorizontalGroup(
			gl_panelEditaQuarto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaQuarto.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_tipoQuarto, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
					.addComponent(checkBox)
					.addGap(63))
		);
		gl_panelEditaQuarto.setVerticalGroup(
			gl_panelEditaQuarto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaQuarto.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaQuarto.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEditaQuarto.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(textField_tipoQuarto, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(checkBox))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		panelEditaQuarto.setLayout(gl_panelEditaQuarto);
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
	
	private ColecaoDeHospedes getColecaoHospedes() {
		return listaHospedes;
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

	private ColecaoDeQuartos getListaDeQuartos() {
		return listaDeQuartos;
	}
	
	public Servico getServico() {
		return servico;
	}
}

