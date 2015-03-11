package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import core.Contrato;
import core.Quarto;
import core.Servico;
import core.colecoes.ColecaoDeHospedes;
import core.colecoes.ColecaoDeQuartos;

public class PainelServicos extends JInternalFrame {
	
	private static final long serialVersionUID = 5210861302996214641L;
	private final JScrollPane scrollPaneServicos = new JScrollPane();
	private JScrollPane scrollPaneContratos = new JScrollPane();
	private JScrollPane scrollPaneQuartos = new JScrollPane();
	private JTable tableServicos;
	private JTable tableContratos;
	private JTable tableQuartos;
	private JTable table;
	private Contrato contratoSelecionado = null;
	private Servico servicoSelecionado = null ;
	private List<Contrato> listaContratos;
	private ColecaoDeHospedes listaHospedes;
	private List<Quarto> listaDeQuartos;
	private JDesktopPane painelPrincipal;
	private PainelVisualizacaoServico painelVisualizacao;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JButton btnVisualizar;;
	private PainelAdicionaServico painelAdicionar;
	private ListSelectionModel modeloSelecaoLinhaContrato;
	
	
	public PainelServicos(List<Contrato> listaContratos, JDesktopPane painelPrincipal, ColecaoDeQuartos listaDeQuartos, ColecaoDeHospedes listaHospedes) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabelaServicos();
				escreveTabelaContratos();
				escreveTabelaQuartos();
			}			
		});		
		this.listaHospedes = listaHospedes;
		this.painelPrincipal = painelPrincipal;
		this.listaDeQuartos = listaDeQuartos.getListaQuartosVagos();
	
		this.listaContratos = listaContratos;		
		
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Servi\u00E7os");
		setClosable(true);
		setBounds(0, 0, 752, 452);
		getContentPane().setLayout(null);

		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(contratoSelecionado != null) {
					painelAdicionar = new PainelAdicionaServico(null, contratoSelecionado, getPainelPrincipal(), PainelServicos.this.listaDeQuartos, PainelServicos.this.listaHospedes);
					adicionaNoPainel(painelAdicionar);
					painelAdicionar.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um contrato!");
				}
				escreveTabelaServicos();
				escreveTabelaContratos();
				escreveTabelaQuartos();	
				
			}
		});
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelAdicionar = new PainelAdicionaServico(servicoSelecionado, contratoSelecionado, getPainelPrincipal(), PainelServicos.this.listaDeQuartos, PainelServicos.this.listaHospedes);
				adicionaNoPainel(painelAdicionar);
				painelAdicionar.show();
				
			}
		});
		btnAtualizar.setEnabled(false);
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(contratoSelecionado.getListaServicos().contains(servicoSelecionado)) {
					contratoSelecionado.getListaServicos().remove(servicoSelecionado);
				}
				else {
				//	contratoSelecionado.getListaQuartosAlugados().remove(servicoSelecionado);
				//	((Quarto) servicoSelecionado).setToLivre();
				//	PainelServicos.this.listaDeQuartos.getListaQuartosVagos().add((Quarto) servicoSelecionado);
					servicoSelecionado = (Quarto) servicoSelecionado;
					PainelServicos.this.listaDeQuartos.add((Quarto)servicoSelecionado);
					contratoSelecionado.getListaQuartosAlugados().remove((Quarto)servicoSelecionado);

				}
				escreveTabelaServicos();
				escreveTabelaQuartos();
				servicoSelecionado = null;
				JOptionPane.showMessageDialog(null, "Serviço Removido!");
			}
		});
		btnRemover.setEnabled(false);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelVisualizacao = new PainelVisualizacaoServico(servicoSelecionado);
				adicionaNoPainel(painelVisualizacao);
				painelVisualizacao.show();
				escreveTabelaContratos();
				escreveTabelaServicos();
				escreveTabelaQuartos();
			}
		});
		
		
		btnVisualizar.setEnabled(false);
		
		JLabel lblSelecionarContrato = new JLabel("Selecionar Contrato: ");
		
		JLabel lblServiosContratados = new JLabel("Serviços Contratados");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(btnAdicionar)
					.addGap(70)
					.addComponent(btnVisualizar)
					.addGap(80)
					.addComponent(btnAtualizar)
					.addGap(86)
					.addComponent(btnRemover)
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneContratos, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(276)
					.addComponent(lblServiosContratados)
					.addContainerGap(314, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(279)
					.addComponent(lblSelecionarContrato)
					.addContainerGap(312, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblSelecionarContrato)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneContratos, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblServiosContratados, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnRemover)
						.addComponent(btnAtualizar)
						.addComponent(btnVisualizar))
					.addContainerGap())
		);
		
		//CONSTRUCAO DA TABELA
		
		tableServicos = new JTable();
		
		tableServicos.setRowSelectionAllowed(true); 
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableServicos.getSelectionModel(); 
				
				tabbedPane.addTab("Quartos", null, scrollPaneQuartos, null);
				
				tableQuartos = new JTable();
				tableQuartos.setRowSelectionAllowed(true);
				scrollPaneQuartos.setColumnHeaderView(tableQuartos);
				tabbedPane.addTab("Adicionais", null, scrollPaneServicos, null);
				
				scrollPaneServicos.setViewportView(tableServicos);
				scrollPaneServicos.setRowHeaderView(table);
		
				scrollPaneQuartos.setViewportView(tableQuartos);
				scrollPaneQuartos.setRowHeaderView(table);
				
		tableContratos = new JTable();
		escreveTabelaContratos();
		scrollPaneContratos.setViewportView(tableContratos);
		scrollPaneContratos.setRowHeaderView(table);
		
		tableContratos.setRowSelectionAllowed(true); 
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				modeloSelecaoLinhaContrato = tableContratos.getSelectionModel();
				
				modeloSelecaoLinhaContrato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinhaContrato.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionadoContrato = tableContratos.getSelectedRows(); 
						if (indiceSelecionadoContrato.length <= 0){
							contratoSelecionado = null;
							servicoSelecionado = null;
							escreveTabelaServicos();
							escreveTabelaQuartos();
						}else{
							setContratoSelecionado(indiceSelecionadoContrato[0]);
							escreveTabelaServicos();
							escreveTabelaQuartos();
							
						}atualizaBotoes();
						
					}
				});
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableServicos.getSelectedRows(); 
						if (indiceSelecionado.length <= 0 || contratoSelecionado == null){
							servicoSelecionado = null;
						}else{
							setServicoSelecionado(indiceSelecionado[0]);
							tableQuartos.clearSelection();
						}atualizaBotoes();
						
					}
				}); 
		
		getContentPane().setLayout(groupLayout);
	
		
		escreveTabelaQuartos();
		scrollPaneQuartos.setViewportView(tableQuartos);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinhaQuartos = tableQuartos.getSelectionModel(); 			
				modeloSelecaoLinhaQuartos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinhaQuartos.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionadoQuartos = tableQuartos.getSelectedRows(); 
						if (indiceSelecionadoQuartos.length <= 0){
							servicoSelecionado = null;
						}else{
							setServicoSelecionado(indiceSelecionadoQuartos[0]);
							tableServicos.clearSelection();
						}atualizaBotoes();
						
					}
				});
	

	}

	
	private void escreveTabelaQuartos() {
		
		// PREENCHENDO TABELA DOS QUARTOS DO CONTRATO
		Object [][] designTabela;
		if(contratoSelecionado == null || contratoSelecionado.getListaQuartosAlugados().size() == 0) {
			designTabela = new Object[0][3];
		}
		else {
			Collections.sort(contratoSelecionado.getListaQuartosAlugados());
			int tamanhoDaTabela = contratoSelecionado.getListaQuartosAlugados().size();

			designTabela = new Object[tamanhoDaTabela][6];	
			for (int j = 0; j < tamanhoDaTabela; j++){
				Quarto quartoAtualContrato = contratoSelecionado.getListaQuartosAlugados().get(j);
				//Para preencher a primeira coluna da linha: Descrição do quarto
				designTabela[j][0] = quartoAtualContrato.getTipo();
				//Para preencher a segunda coluna da linha: O preço da diária
				designTabela[j][1] = "R$ " + quartoAtualContrato.getPrecoDiaria();
				//Para preencher a terceira coluna da linha: O número de diárias setadas
				designTabela[j][2] = quartoAtualContrato.getDiariasViaReservaDeContrato(contratoSelecionado);
				//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
				designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
				//Para preencher a quinta coluna da linha: O número do quarto
				designTabela[j][4] = quartoAtualContrato.getNumero();
				//Para preencher a sexta coluna da linha: O preço a ser pago
				designTabela[j][5] = "R$ " + quartoAtualContrato.getPrecoDiaria() * quartoAtualContrato.getDiariasViaReservaDeContrato(contratoSelecionado);
			
			}
		}
			@SuppressWarnings("serial")
			DefaultTableModel modeloTabelaQuartos = new DefaultTableModel(designTabela, new String[] {
					"Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
			}) {
	
				@Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			
			tableQuartos.setModel(modeloTabelaQuartos);
		
		
	}
	
	private void escreveTabelaContratos() {
		Object[][] designTabela = new Object[listaContratos.size()][5];
		for (int i = 0; i < listaContratos.size(); i++){
			Contrato contratoAtual = listaContratos.get(i);
			// Para preencher a primeira linha da tabela, com o nome do Hóspede Principal.
			if (contratoAtual.getHospedePrincipal() == null){
				designTabela[i][0] = "Não especificado";
			}else{
				designTabela[i][0] = contratoAtual.getHospedePrincipal().getNome();
			}
			// Para conseguir uma String formatada com a data do checkin, através de um método na classe Main.
			String dataFormatadaCheckIn = "";
				dataFormatadaCheckIn = Main.converteParaString(contratoAtual.getDataCheckIn());
			designTabela[i][1] = dataFormatadaCheckIn;
			// Para conseguir uma String formatada com a estimada data do checkout.
			String dataFormatadaCheckOut = "";
				dataFormatadaCheckOut = Main.converteParaString(contratoAtual.getDataCheckOut());
			designTabela[i][2] = dataFormatadaCheckOut;
			// Para colocar na tabela o total de despesas do contrato.
			designTabela[i][3] = contratoAtual.calculaPrecoFinal();
			// Para colocar na tabela o status do contrato.
			designTabela[i][4] = contratoAtual.getStatus();
	// FIM DE CONSTRUÇÃO DE TABELA.
	
}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Hóspede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
		}) {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		tableContratos.setModel(modeloTabela);
}

	private void escreveTabelaServicos() {
		Object [][] designTabela;
		if(contratoSelecionado == null || contratoSelecionado.getListaServicos().size() == 0) {
			designTabela = new Object[0][3];
		}
		else {
			int tamanhoTabela = contratoSelecionado.getListaServicos().size();
			designTabela = new Object[tamanhoTabela][3];
			
			for (int i = 0; i < contratoSelecionado.getListaServicos().size(); i++) {
				Servico servicoAtual = contratoSelecionado.getListaServicos().get(i);
				if (servicoAtual.getTipo() == null){
				designTabela[i][0] = "Não especificado";
				}else{
					designTabela[i][0] = servicoAtual.getTipo();
				}
				designTabela[i][1] = servicoAtual.toString();
				designTabela[i][2] = servicoAtual.calculaPrecoTotal();
			}
					
		
		}
		
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Serviços", "Descrição", "Preço" })     {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		tableServicos.setModel(modeloTabela); 
	}
	
	public void setServicoSelecionado(int indice){
		if (contratoSelecionado != null) {
			if (tableServicos != null) {
				if(tableServicos.isRowSelected(indice)){
					servicoSelecionado = contratoSelecionado.getListaServicos().get(indice); 		
				}
			}
		}
		if (tableQuartos != null) {
			if(tableQuartos.isRowSelected(indice)) {
				servicoSelecionado = contratoSelecionado.getListaQuartosAlugados().get(indice);	
			}
		}
	}
	
	public void setContratoSelecionado(int indice){
		contratoSelecionado = listaContratos.get(indice); 	}
	
	public void atualizaBotoes(){
		if (contratoSelecionado != null && servicoSelecionado != null){
			btnRemover.setEnabled(true);
			btnAtualizar.setEnabled(true);
			btnVisualizar.setEnabled(true);
		}else{
			btnRemover.setEnabled(false);
			btnAtualizar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}
	}
	
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
