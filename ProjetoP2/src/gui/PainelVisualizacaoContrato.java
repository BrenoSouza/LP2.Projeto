package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Servico;

public class PainelVisualizacaoContrato extends JInternalFrame {
	private JTabbedPane painelTabas;
	private Contrato contrato;
	private List<Hospede> listaHospedes;
	private List<Servico> listaServicos;
	private List<Quarto> listaQuartos;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabelServicosAssociados;
	private JTable tabelaServicos;
	private JDesktopPane painelPrincipal;
	private Servico servicoSelecionado = null;
	private Quarto quartoSelecionado = null;
	private Hospede hospedeSelecionado = null;
	private JButton btnVisualizar;
	private PainelVisualizacaoServico painelVisualizacaoServico;
	private JTable tabelaHospedes;
	private JLabel lblTotalASerPagoVariavel;
	private JTable tabelaQuartos;
	private JButton btnVisualizarQuarto;
	private JButton btnVisualizarHospede;
	


	public PainelVisualizacaoContrato(Contrato contrato, JDesktopPane painelPrincipal) {
		setFrameIcon(new ImageIcon(PainelVisualizacaoContrato.class.getResource("/resources/contrato_icon.png")));
		setTitle("Visualização de contrato");
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabelas();
			}
		});
		setClosable(true);
		this.painelPrincipal = painelPrincipal;
		setBounds(0, 50, 851, 400);
		this.contrato = contrato;
		listaHospedes = contrato.getListaHospedes();
		listaServicos = contrato.getListaServicos();
		listaQuartos = contrato.getListaQuartosAlugados();
		painelTabas = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelTabas, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelTabas, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panelDetalhes = new JPanel();
		painelTabas.addTab("Detalhes", null, panelDetalhes, null);
		
		JLabel lblHospedePrincipal = new JLabel("H\u00F3spede Principal:");
		lblHospedePrincipal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblHospedePrincipalVariavel = new JLabel("New label");
		if (contrato.getHospedePrincipal() == null){
			lblHospedePrincipalVariavel.setText("Não definido");
		}else{
			lblHospedePrincipalVariavel.setText(contrato.getHospedePrincipal().getNome());
		}
		lblHospedePrincipalVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataCheckIn = new JLabel("Data de Check-In:");
		
		lblDataCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if (contrato.getStatus().equals("RESERVA")){
			lblDataCheckIn.setText("Data marcada para Check-In:");
		}
		JLabel lblDataCheckInVariavel = new JLabel("New label");
		try{
			lblDataCheckInVariavel.setText(Main.converteParaString(contrato.getDataCheckIn()));
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		lblDataCheckInVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataMarcadaCheckOut = new JLabel("Data marcada para Check-Out:");
		lblDataMarcadaCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDataMarcadaCheckOutVariavel = new JLabel("New label");
		try{
			lblDataMarcadaCheckOutVariavel.setText(Main.converteParaString(contrato.getDataCheckOut()));
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		lblDataMarcadaCheckOutVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTotalASerPago = new JLabel("Total a ser pago:");
		if (contrato.getStatus().equals("FECHADO")){
			lblTotalASerPago.setText("Total que foi pago:");
		}
		lblTotalASerPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblTotalASerPagoVariavel = new JLabel("New label");
		lblTotalASerPagoVariavel.setText(Double.toString(contrato.calculaPrecoFinal()));
		if (contrato.getStatus().equals("RESERVA")){
			lblTotalASerPagoVariavel.setText("--RESERVA--");
		}
		lblTotalASerPagoVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHospedesRegistrados = new JLabel("H\u00F3spedes registrados:");
		lblHospedesRegistrados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		tabelaHospedes = new JTable();
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha4 = tabelaHospedes.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha4.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tabelaHospedes.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							hospedeSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setHospedeSelecionado(indiceSelecionado[0]);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		scrollPane.setViewportView(tabelaHospedes);
		
		btnVisualizarHospede = new JButton("Visualizar");
		btnVisualizarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PainelVisualizacaoClientes painelVisualizaHospede = new PainelVisualizacaoClientes(hospedeSelecionado, PainelVisualizacaoContrato.this.painelPrincipal);
				PainelVisualizacaoContrato.this.painelPrincipal.add(painelVisualizaHospede);
				painelVisualizaHospede.show();
			}
		});
		btnVisualizarHospede.setEnabled(false);
		GroupLayout gl_panelDetalhes = new GroupLayout(panelDetalhes);
		gl_panelDetalhes.setHorizontalGroup(
			gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalhes.createSequentialGroup()
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
								.addGroup(gl_panelDetalhes.createSequentialGroup()
									.addComponent(lblHospedePrincipal)
									.addGap(10)
									.addComponent(lblHospedePrincipalVariavel))
								.addGroup(gl_panelDetalhes.createSequentialGroup()
									.addComponent(lblDataCheckIn)
									.addGap(6)
									.addComponent(lblDataCheckInVariavel))
								.addGroup(gl_panelDetalhes.createSequentialGroup()
									.addComponent(lblDataMarcadaCheckOut)
									.addGap(10)
									.addComponent(lblDataMarcadaCheckOutVariavel))
								.addGroup(gl_panelDetalhes.createSequentialGroup()
									.addComponent(lblTotalASerPago)
									.addGap(10)
									.addComponent(lblTotalASerPagoVariavel))
								.addComponent(lblHospedesRegistrados)))
						.addGroup(Alignment.TRAILING, gl_panelDetalhes.createSequentialGroup()
							.addContainerGap(723, Short.MAX_VALUE)
							.addComponent(btnVisualizarHospede)))
					.addContainerGap())
		);
		gl_panelDetalhes.setVerticalGroup(
			gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalhes.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedePrincipal)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHospedePrincipalVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataCheckIn)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblDataCheckInVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataMarcadaCheckOut)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblDataMarcadaCheckOutVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalASerPago)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblTotalASerPagoVariavel)))
					.addGap(18)
					.addComponent(lblHospedesRegistrados)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnVisualizarHospede)
					.addContainerGap())
		);
		
		
		panelDetalhes.setLayout(gl_panelDetalhes);
		JPanel panelServicos = new JPanel();
		painelTabas.addTab("Servi\u00E7os", null, panelServicos, null);
		
		scrollPane_1 = new JScrollPane();
		
		lblNewLabelServicosAssociados = new JLabel("Servi\u00E7os associados ao contrato:");
		lblNewLabelServicosAssociados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelVisualizacaoServico = new PainelVisualizacaoServico(servicoSelecionado);
				getPainelPrincipal().add(painelVisualizacaoServico);
				painelVisualizacaoServico.show();
			}
		});
		btnVisualizar.setEnabled(false);
		GroupLayout gl_panelServicos = new GroupLayout(panelServicos);
		gl_panelServicos.setHorizontalGroup(
			gl_panelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServicos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelServicos.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
						.addComponent(lblNewLabelServicosAssociados)
						.addComponent(btnVisualizar, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelServicos.setVerticalGroup(
			gl_panelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServicos.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabelServicosAssociados)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVisualizar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tabelaServicos = new JTable();
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tabelaServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaServicos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					servicoSelecionado = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setServicoSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		tabelaServicos.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tabelaServicos);
		panelServicos.setLayout(gl_panelServicos);
		
		JPanel panelQuartos = new JPanel();
		painelTabas.addTab("Quartos", null, panelQuartos, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblQuartos = new JLabel("Quartos associados ao contrato:");
		if (contrato.getStatus().equals("FECHADO")){
			lblQuartos.setText("Quartos que foram associados ao contrato:");
		}else if (contrato.getStatus().equals("RESERVA")){
			lblQuartos.setText("Quartos que serão associados ao contrato:");
		}
		lblQuartos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnVisualizarQuarto = new JButton("Visualizar");
		btnVisualizarQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PainelVisualizacaoServico painelVisualizarServico = new PainelVisualizacaoServico(quartoSelecionado);
				getPainelPrincipal().add(painelVisualizarServico);
				painelVisualizarServico.show();
			}
		});
		btnVisualizarQuarto.setEnabled(false);
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
						.addComponent(lblQuartos)
						.addComponent(btnVisualizarQuarto, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelQuartos.setVerticalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addGap(8)
					.addComponent(lblQuartos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVisualizarQuarto)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		tabelaQuartos = new JTable();
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha2 = tabelaQuartos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha2.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaQuartos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					quartoSelecionado = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setQuartoSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		tabelaServicos.setRowSelectionAllowed(true);
		scrollPane_2.setViewportView(tabelaQuartos);
		panelQuartos.setLayout(gl_panelQuartos);
		getContentPane().setLayout(groupLayout);
		escreveTabelas();

	}
	private void setServicoSelecionado(int i){
		servicoSelecionado = listaServicos.get(i);
	}
	private void setQuartoSelecionado(int i){
		quartoSelecionado = listaQuartos.get(i);
	}
	private void setHospedeSelecionado(int i){
		hospedeSelecionado = listaHospedes.get(i);
	}
	public void atualizaBotoes(){
		btnVisualizar.setEnabled(servicoSelecionado != null);
		btnVisualizarQuarto.setEnabled(quartoSelecionado != null);
		btnVisualizarHospede.setEnabled(hospedeSelecionado != null);
	}
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
	public void escreveTabelas(){
		// INÍCIO DE CONSTRUÇÃO DA TABELA
		// designTabela = o conteúdo da tabela em si, preenchida através de um loop for
				Object[][] designTabela = new Object[listaHospedes.size()][3];
				LocalDate presente = LocalDate.now();
				for (int i = 0; i < listaHospedes.size(); i++){
					Hospede hospedeAtual = listaHospedes.get(i);
					//Para preencher a primeira coluna da linha: Nome do hóspede
					designTabela[i][0] = hospedeAtual.getNome();
					//Para preencher a segunda coluna da linha: CPF do hóspede
					designTabela[i][1] = hospedeAtual.getCpf();
					//Para preencher a terceira coluna da linha: Idade do hóspede
					Calendar nascimento = hospedeAtual.getDataNascimento();
					LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
					Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
					designTabela[i][2] = periodoDeTempo.getYears();
					
			// FIM DE CONSTRUÇÃO DE TABELA.
			
		}
				//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
				@SuppressWarnings("serial")
				DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
						"Nome", "CPF", "Idade"
				}) {

					@Override
				    public boolean isCellEditable(int row, int column) {
				        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
				        return false;
				    }
				};

	
		tabelaHospedes.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
		tabelaHospedes.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		
		//INICIO DA CONSTRU��O DE TABELA
		Object [][] tabelaServicosDesign = new Object [contrato.getListaServicos().size()][4];
		for (int i = 0; i < contrato.getListaServicos().size(); i++){
			Servico servicoAtual = contrato.getListaServicos().get(i);
			//Primeira célula da linha: o método getTipo() do serviço
			tabelaServicosDesign[i][0] = servicoAtual.getTipo();
			//Segunda célula da linha: a data do serviço
			tabelaServicosDesign[i][1] = servicoAtual.getInicioServico();
			//Terceira célula da linha: a hora de entrada do serviço
			tabelaServicosDesign[i][2] = servicoAtual.getHoraEntrada() + ":" + servicoAtual.getMinutosEntrada();
			//Quarta célula da linha: o preço total do serviço
			tabelaServicosDesign[i][3] = "R$ " + servicoAtual.calculaPrecoTotal();
		}
		//GAMBIARRA PARA QUE O USU�RIO NÃO POSSA EDITAR OS DADOS DA TABELA
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabelaServicos = new DefaultTableModel(tabelaServicosDesign, new String[] {
				"Serviço", "Data", "Hora", "Preço"
		}) {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};
		tabelaServicos.setModel(modeloTabelaServicos);
		//FIM DE CONSTRUÇÃO DE TABELA
		Collections.sort(listaQuartos);
		designTabela = new Object[listaQuartos.size()][6];	
		for (int j = 0; j < listaQuartos.size(); j++){
			Quarto quartoAtualContrato = listaQuartos.get(j);
			//Para preencher a primeira coluna da linha: Descrição do quarto
			designTabela[j][0] = quartoAtualContrato.getTipo();
			//Para preencher a segunda coluna da linha: O preço da diária
			designTabela[j][1] = "R$ " + quartoAtualContrato.getPrecoDiaria();
			//Para preencher a terceira coluna da linha: O número de diárias setadas
			designTabela[j][2] = contrato.getNumeroDiarias();
			//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
			designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
			//Para preencher a quinta coluna da linha: O número do quarto
			designTabela[j][4] = quartoAtualContrato.getNumero();
			//Para preencher a sexta coluna da linha: O preço a ser pago
			designTabela[j][5] = "R$ " + quartoAtualContrato.getPrecoDiaria() * contrato.getNumeroDiarias();
		}
			//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
			@SuppressWarnings("serial")
			DefaultTableModel modeloTabela4 = new DefaultTableModel(designTabela, new String[] {
					"Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
			}) {
	
				@Override
			    public boolean isCellEditable(int row, int column) {
			        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
			        return false;
			    }
			};
			tabelaQuartos.setModel(modeloTabela4);
			tabelaQuartos.getColumnModel().getColumn(3).setPreferredWidth(180); //Aumentando o tamanho da quarta coluna, pq a String de título dela é grande
			tabelaQuartos.getColumnModel().getColumn(0).setPreferredWidth(130); // Idem ao comment acima
			tabelaQuartos.setRowSelectionAllowed(true);
		lblTotalASerPagoVariavel.setText("R$ " + contrato.calculaPrecoFinal());
	}
}
