package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import org.joda.time.DateTime;
import org.joda.time.Days;

import core.AluguelCarro;
import core.Babysitter;
import core.Contrato;
import core.Quarto;
import core.Restaurante;
import core.Servico;
import core.colecoes.ColecaoDeHospedes;
import core.colecoes.ColecaoDeQuartos;

public class PainelAdicionaServico extends JInternalFrame{

	private static final long serialVersionUID = -2800935829318426745L;
	private Contrato contrato;
	private JDesktopPane painelPrincipal;
	private List<Quarto> listaDeQuartos;
	private ColecaoDeHospedes listaHospedes;
	private JPanel panelExterno = new JPanel();  
	private CardLayout layoutPainel = new CardLayout();  
	private JPanel panelBabysitter = new JPanel();
	private JPanel panelCarros = new JPanel();
	private JPanel panelRestaurante = new JPanel();
	private JPanel panelEditaRestaurante;
	private JPanel panelEditaBabysitter;
	private JPanel panelEditaCarro;
	@SuppressWarnings("rawtypes")
	private JComboBox cBoxTipoCarro;
	private JCheckBox chckbxSeguro;
	private JCheckBox chckbxTanqueCheio;
	private JButton btnBabysitter;
	private JButton btnRestaurante;
	private JButton btnAluguelCarros;
	private JSpinner spinnerDiariasCarro;
	private JCheckBox chckbxCobertura;
	private JTextField txtfi_preco;
	private Servico servico;
	private final String[] TIPOS_CARROS = {"Luxo", "Executivo"};
	private JTextField txtFldPrecoRestaurante;
	private JTextField textField_horaSaida;
	private JTextField textField_quantidadeHorasBaby;
	private int diasRestantes = 1;
	private PainelAdicionaQuartos painelAddQuarto;
	private Atualizador framePai;
	private JSpinner spinner;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PainelAdicionaServico(Servico servico, Contrato contrato, JDesktopPane painelPrincipal, ColecaoDeQuartos listaDeQuartos, ColecaoDeHospedes listaHospedes, Atualizador framePai) {
		this.contrato = contrato;
		this.painelPrincipal = painelPrincipal;
		this.listaHospedes = listaHospedes;
		this.listaDeQuartos = listaDeQuartos.getListaQuartosVagosReserva(contrato);
		this.servico = servico;
		this.framePai = framePai;
    DateTime presente = new DateTime().withTimeAtStartOfDay();
    DateTime checkOut = new DateTime(contrato.getDataCheckOut()).withTimeAtStartOfDay();
    if (Days.daysBetween(presente, checkOut).getDays() > 0){
		diasRestantes = Days.daysBetween(presente, checkOut).getDays();
    }else{
      diasRestantes = 1;
    }
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Adicionar Servi\u00E7os");
		setClosable(true);
		setBounds(100, 100, 800, 400);

		// Codigo dos Botões que alternam o Cardlayout
		
		Icon imagemQuarto = new ImageIcon(PainelServicos.class.getResource("/resources/quarto.png"));
		
		Icon imagemCarro = new ImageIcon(PainelServicos.class.getResource("/resources/carro.png"));
		btnAluguelCarros = new JButton(imagemCarro);
		btnAluguelCarros.setBounds(167, 43, 77, 74);
		btnAluguelCarros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((PainelAdicionaServico.this.servico == null) ) 
					layoutPainel.show(panelExterno, "carros" );
				else {
					layoutPainel.show(panelExterno, "edita_carro");
				}
			}
		});
		
		Icon imagemBabysitter = new ImageIcon(PainelServicos.class.getResource("/resources/babysitter.png"));
		btnBabysitter = new JButton(imagemBabysitter);
		btnBabysitter.setBounds(321, 43, 78, 74);
		btnBabysitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((PainelAdicionaServico.this.servico == null) ) 
					layoutPainel.show(panelExterno, "babysitter");
				else {
					layoutPainel.show(panelExterno, "edita_babysitter");
				}
			}
		});
		
		Icon imagemRestaurante = new ImageIcon(PainelServicos.class.getResource("/resources/restaurante.png"));
		btnRestaurante = new JButton(imagemRestaurante);
		btnRestaurante.setBounds(467, 43, 81, 74);
		btnRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((PainelAdicionaServico.this.servico == null) ) 
					layoutPainel.show(panelExterno, "restaurante");
				else {
					layoutPainel.show(panelExterno, "edita_restaurante");
				}
			}
		});
		
		// Fim dos Botões que alternam o Cardlayout
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(267, 323, 100, 25);
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(404, 323, 96, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		panelExterno.setBounds(12, 144, 767, 167);
		
		panelExterno.setLayout(layoutPainel);
		
		panelEditaCarro = new JPanel();
		
		panelEditaCarro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaCarro, "edita_carro");
		
		JLabel lblDiarias = new JLabel("Diarias");
		
		spinner = new JSpinner();
		GroupLayout gl_panelEditaCarro = new GroupLayout(panelEditaCarro);
		gl_panelEditaCarro.setHorizontalGroup(
		  gl_panelEditaCarro.createParallelGroup(Alignment.LEADING)
		    .addGroup(gl_panelEditaCarro.createSequentialGroup()
		      .addGap(37)
		      .addComponent(lblDiarias)
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addComponent(spinner, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
		      .addContainerGap(624, Short.MAX_VALUE))
		);
		gl_panelEditaCarro.setVerticalGroup(
		  gl_panelEditaCarro.createParallelGroup(Alignment.LEADING)
		    .addGroup(gl_panelEditaCarro.createSequentialGroup()
		      .addContainerGap()
		      .addGroup(gl_panelEditaCarro.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblDiarias)
		        .addComponent(spinner, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		      .addContainerGap(136, Short.MAX_VALUE))
		);
		panelEditaCarro.setLayout(gl_panelEditaCarro);
		
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
				.addGroup(gl_panelEditaRestaurante.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaRestaurante.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlterarPreo)
						.addComponent(txtFldPrecoRestaurante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox_IsCobertura))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panelEditaRestaurante.setLayout(gl_panelEditaRestaurante);
		
		panelEditaBabysitter = new JPanel();
		
		panelEditaBabysitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelExterno.add(panelEditaBabysitter, "edita_babysitter");
		
		JLabel lblNovaSaida = new JLabel("Alterar quantidade de horas:");
		
		textField_horaSaida = new JTextField();
		textField_horaSaida.setColumns(10);
		
		
		GroupLayout gl_panelEditaBabysitter = new GroupLayout(panelEditaBabysitter);
		gl_panelEditaBabysitter.setHorizontalGroup(
			gl_panelEditaBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaBabysitter.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNovaSaida)
					.addGap(4)
					.addComponent(textField_horaSaida, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(537, Short.MAX_VALUE))
		);
		gl_panelEditaBabysitter.setVerticalGroup(
			gl_panelEditaBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditaBabysitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditaBabysitter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNovaSaida)
						.addComponent(textField_horaSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		panelEditaBabysitter.setLayout(gl_panelEditaBabysitter);
		panelBabysitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelExterno.add(panelBabysitter, "babysitter");
		
		JLabel lblQuantidadeHoras = new JLabel("Quantidade de Horas:");
		
		textField_quantidadeHorasBaby = new JTextField();
		textField_quantidadeHorasBaby.setColumns(10);
		GroupLayout gl_panelBabysitter = new GroupLayout(panelBabysitter);
		gl_panelBabysitter.setHorizontalGroup(
			gl_panelBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBabysitter.createSequentialGroup()
					.addGap(34)
					.addComponent(lblQuantidadeHoras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_quantidadeHorasBaby, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(498, Short.MAX_VALUE))
		);
		gl_panelBabysitter.setVerticalGroup(
			gl_panelBabysitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBabysitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBabysitter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidadeHoras)
						.addComponent(textField_quantidadeHorasBaby, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(136, Short.MAX_VALUE))
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
		spinnerDiariasCarro.setModel(new SpinnerNumberModel(1, 1, diasRestantes, 1));
		
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
					.addGap(36)
					.addComponent(txtfi_preco, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(chckbxCobertura)
					.addGap(434))
		);
		gl_panelRestaurante.setVerticalGroup(
			gl_panelRestaurante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRestaurante.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRestaurante.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCobertura)
						.addComponent(lblPreo)
						.addComponent(txtfi_preco, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(124, Short.MAX_VALUE))
		);
		panelRestaurante.setLayout(gl_panelRestaurante);
		
		JLabel lblCarros = new JLabel("Carros");
		lblCarros.setBounds(177, 123, 47, 15);
		
		JLabel lblBabysitter = new JLabel("Babysitter");
		lblBabysitter.setBounds(321, 123, 74, 15);
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		lblRestaurante.setBounds(467, 123, 89, 15);
		getContentPane().setLayout(null);
		getContentPane().add(panelExterno);
		getContentPane().add(btnAdicionar);
		getContentPane().add(lblCarros);
		getContentPane().add(btnAluguelCarros);
		getContentPane().add(btnCancelar);
		getContentPane().add(lblBabysitter);
		getContentPane().add(btnBabysitter);
		getContentPane().add(lblRestaurante);
		getContentPane().add(btnRestaurante);
		
		
	  // Codigo que mostra o JPanel do Cardlayout de acordo com a instancia do serviço!
	  // Dependendo do tipo de serviço aparecerá as opções para ele!
		mostraOCardLayout(servico);

	
	
	//Vai adicionar um novo serviço depedendo do JPanel do cardlayout selecionado.
		
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
				    		dispose();
						} catch (NullPointerException e1) {
							e1.printStackTrace();
						}
			    		break;
			    	}
			    	else if(comp == panelBabysitter) {
			    		Babysitter babysitter = new Babysitter();
			    		if (!(textField_quantidadeHorasBaby.getText().isEmpty())) {
			    			int horaSaida = Integer.valueOf(textField_quantidadeHorasBaby.getText());
			    			babysitter.setHoraSaida(horaSaida);
			    		}
			    		adicionaServico(babysitter);
			    		
			    		JOptionPane.showMessageDialog(null, "Adicionado!");
			    		dispose();
			    		break;
			    	}
			    	else if (comp == panelRestaurante){
			    		double preco = Double.parseDouble(txtfi_preco.getText());
			    		try {
							adicionaServico(new Restaurante(chckbxCobertura.isSelected(), preco));
							JOptionPane.showMessageDialog(null, "Adicionado!");
							dispose();
						} catch (NullPointerException e1) {
							e1.printStackTrace();
						}
			    		break;
			    	}
			    	else if(comp == panelEditaRestaurante) {
			    		Restaurante restaurante = (Restaurante) PainelAdicionaServico.this.servico;
			    		double preco;
			    		if (!(txtFldPrecoRestaurante.getText().isEmpty())) {
			    			preco = Double.valueOf(txtFldPrecoRestaurante.getText());
			    			restaurante.setPreco(preco);
			    			dispose();
			    			break;
			    		}
			    	}
			    	else if(comp == panelEditaBabysitter) {
			    		Babysitter babySitter = (Babysitter) PainelAdicionaServico.this.servico;
			    		int horas;
			    		if (!(textField_horaSaida.getText().isEmpty())) {
			    			horas = Integer.valueOf(textField_horaSaida.getText());
			    			babySitter.setHoraSaida(horas);
			    			dispose();
			    			break;
			    		}
			    	}
			    	else if(comp == panelEditaCarro) {
			    		AluguelCarro carro = (AluguelCarro) PainelAdicionaServico.this.servico;
			    		if (true)//contrato.getDataCheckOut() {
			    		carro.setDiarias(carro.getDiarias() + 1);
			    		int diarias = (Integer) PainelAdicionaServico.this.spinner.getValue();
			    		carro.setDiarias(diarias);
			    		dispose();
			    		break;
			    	}
			    }			
				
			}PainelAdicionaServico.this.framePai.atualiza();
		}

	}
	);
		
	}

  private void mostraOCardLayout(Servico servico) {
    if (servico instanceof Quarto) {
			if (PainelAdicionaServico.this.servico == null) 
				layoutPainel.show(panelExterno, "quarto" );
			else {
				layoutPainel.show(panelExterno, "edita_quarto");
			}
			btnBabysitter.setEnabled(false);
			btnAluguelCarros.setEnabled(false);
			btnRestaurante.setEnabled(false);
		}
		else if (servico instanceof AluguelCarro) {
			if ((PainelAdicionaServico.this.servico == null) ) 
				layoutPainel.show(panelExterno, "carros" );
			else {
				layoutPainel.show(panelExterno, "edita_carro");
			}
			btnBabysitter.setEnabled(false);
			btnRestaurante.setEnabled(false);
		}
		else if (servico instanceof Babysitter) {
			if ((PainelAdicionaServico.this.servico == null) ) 
				layoutPainel.show(panelExterno, "babysitter");
			else {
				layoutPainel.show(panelExterno, "edita_babysitter");
			}
			btnAluguelCarros.setEnabled(false);
			btnRestaurante.setEnabled(false);
		}
		else if (servico instanceof Restaurante) {
			if ((PainelAdicionaServico.this.servico == null) ) 
				layoutPainel.show(panelExterno, "restaurante");
			else {
				layoutPainel.show(panelExterno, "edita_restaurante");
			}
			btnBabysitter.setEnabled(false);
			btnAluguelCarros.setEnabled(false);
		}
  }
	
  // Método que adiciona o serviço Selecionado.
	private void adicionaServico(Servico servico) {
		contrato.getListaServicos().add(servico); 
	}
	
	
	public JDesktopPane getPainelPrincipal() {
		return painelPrincipal;
	}
	
//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}
	
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	@Override
	public void dispose(){
	  framePai.atualiza();
	  super.dispose();
	}
}

