package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;
import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Servico;

public class PainelServicos extends JInternalFrame {
	
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
	private ColecaoDeQuartos listaDeQuartos;
	private JDesktopPane painelPrincipal;
	private PainelVisualizacaoServico painelVisualizacao;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JButton btnVisualizar;;
	private PainelAdicionaServico painelAdicionar;
	private ListSelectionModel modeloSelecaoLinhaContrato;
	
	/**
	 * Create the frame.
	 */
	
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
		this.listaDeQuartos = listaDeQuartos;
		try{

		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
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
					painelAdicionar = new PainelAdicionaServico(null, contratoSelecionado, getPainelPrincipal(), getColecaoDeQuartos(), getColecaoHospedes());
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
		btnAtualizar.setEnabled(false);
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(contratoSelecionado.getListaServicos().contains(servicoSelecionado)) {
					contratoSelecionado.getListaServicos().remove(servicoSelecionado);
				}
				else {
					contratoSelecionado.getListaQuartosAlugados().remove(servicoSelecionado);
					((Quarto) servicoSelecionado).setToLivre();
					getColecaoDeQuartos().getListaQuartosVagos().add((Quarto) servicoSelecionado);
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
		
		tableServicos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
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
		
		tableContratos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				modeloSelecaoLinhaContrato = tableContratos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinhaContrato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinhaContrato.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionadoContrato = tableContratos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá um elemento essa array.
						if (indiceSelecionadoContrato.length <= 0){
							contratoSelecionado = null;
							servicoSelecionado = null;
							escreveTabelaServicos();
							escreveTabelaQuartos();
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaServicos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setServicoSelecionado, que consegue usar as variáveis sem problemas.
							setContratoSelecionado(indiceSelecionadoContrato[0]);
							escreveTabelaServicos();
							escreveTabelaQuartos();
							
						}atualizaBotoes();
						
					}
				});
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableServicos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá um elemento essa array.
						if (indiceSelecionado.length <= 0 || contratoSelecionado == null){
							servicoSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaServicos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setServicoSelecionado, que consegue usar as variáveis sem problemas.
							setServicoSelecionado(indiceSelecionado[0]);
							tableQuartos.clearSelection();
						}atualizaBotoes();
						
					}
				}); 
		
		getContentPane().setLayout(groupLayout);
	
		
		escreveTabelaQuartos();
		scrollPaneQuartos.setViewportView(tableQuartos);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinhaQuartos = tableQuartos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
			
				modeloSelecaoLinhaQuartos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinhaQuartos.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionadoQuartos = tableQuartos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionadoQuartos.length <= 0){
							servicoSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setServicoSelecionado(indiceSelecionadoQuartos[0]);
							tableServicos.clearSelection();
						}atualizaBotoes();
						
					}
				});
	

	}

	public ColecaoDeQuartos getColecaoDeQuartos() {
		return listaDeQuartos;
	}
	
	private ColecaoDeHospedes getColecaoHospedes() {
		return listaHospedes;
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
				designTabela[j][2] = quartoAtualContrato.getDiarias();
				//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
				designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
				//Para preencher a quinta coluna da linha: O número do quarto
				designTabela[j][4] = quartoAtualContrato.getNumero();
				//Para preencher a sexta coluna da linha: O preço a ser pago
				designTabela[j][5] = "R$ " + quartoAtualContrato.calculaPrecoTotal();
			
			}
		}
			//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
			@SuppressWarnings("serial")
			DefaultTableModel modeloTabelaQuartos = new DefaultTableModel(designTabela, new String[] {
					"Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
			}) {
	
				@Override
			    public boolean isCellEditable(int row, int column) {
			        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
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
			try{
				dataFormatadaCheckIn = Main.converteParaString(contratoAtual.getDataCheckIn());
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			designTabela[i][1] = dataFormatadaCheckIn;
			// Para conseguir uma String formatada com a estimada data do checkout.
			String dataFormatadaCheckOut = "";
			try{
				dataFormatadaCheckOut = Main.converteParaString(contratoAtual.getDataCheckOut());
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			designTabela[i][2] = dataFormatadaCheckOut;
			// Para colocar na tabela o total de despesas do contrato.
			designTabela[i][3] = contratoAtual.calculaPrecoFinal();
			// Para colocar na tabela o status do contrato.
			designTabela[i][4] = contratoAtual.getStatus();
	// FIM DE CONSTRUÇÃO DE TABELA.
	
}
		//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Hóspede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
		}) {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};

		tableContratos.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
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
		
		//GAMBIARRA
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Serviços", "Descrição", "Preço" })     {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};
		
		tableServicos.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
	}
	
	public void setServicoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro método, posso usar variáveis não finais a vontade sem problema.
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
		// Fim da gambiarra. Como estou em outro método, posso usar variáveis não finais a vontade sem problema.
		contratoSelecionado = listaContratos.get(indice); // Lembrando que a tabela está na mesma ordem que a listaContratos, então os índices são os mesmos.
	}
	
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
