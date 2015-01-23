package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Servico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class PainelServicos extends JInternalFrame {
	
	private final JScrollPane scrollPaneServicos = new JScrollPane();
	private JScrollPane scrollPaneContratos = new JScrollPane();
	private JTable tableServicos;
	private JTable tableContratos;
	private JTable table;
	private Contrato contratoSelecionado = null;
	private Servico servicoSelecionado = null ;
	private List<Contrato> listaContratos;
	private List<Hospede> listaHospedes = new ArrayList();
	private List<Hospede> listaHospedes2 = new ArrayList();
	private List<Quarto> listaQuartosDisponiveis;
	private JDesktopPane painelPrincipal;
	private int indiceContratoSelecionado;
	private PainelVisualizacaoServico painelVisualizacao;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JButton btnVisualizar;;
	private String[] nomesHospedes;
	private PainelAdicionaServico painelAdicionar;
	private ListSelectionModel modeloSelecaoLinhaContrato;
	
	/**
	 * Create the frame.
	 */
	
	public PainelServicos(List<Contrato> listaContratos, JDesktopPane painelPrincipal, List<Quarto> listaQuartosDisponiveis) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabelaServicos();
				escreveTabelaContratos();
			}			
		});		
		
		this.painelPrincipal = painelPrincipal;
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		try{
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(Calendar.YEAR, 1990);
			listaHospedes.add(new Hospede("Fulano de Tal","Casa do Fulano", "111111111-11", dataNascimento));
			listaHospedes2.add(new Hospede("Cicrano de Tal","Casa do Fulano", "111111111-11", dataNascimento));
			listaContratos.add(new Contrato(new ArrayList<Quarto>(), listaHospedes, 5));
			listaContratos.add(new Contrato(new ArrayList<Quarto>(), listaHospedes2, 5));
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
					painelAdicionar = new PainelAdicionaServico(contratoSelecionado, getPainelPrincipal(), getListaQuartosDisponiveis());
					adicionaNoPainel(painelAdicionar);
					painelAdicionar.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um contrato!");
				}
					
				
			}
		});
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contratoSelecionado.getListaServicos().remove(servicoSelecionado);
				escreveTabelaServicos();
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
			}
		});
		btnVisualizar.setEnabled(false);
		
		JLabel lblSelecionarContrato = new JLabel("Selecionar Contrato: ");
		
		JLabel lblServiosContratados = new JLabel("Serviços Contratados");
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(btnAdicionar)
							.addGap(80)
							.addComponent(btnVisualizar)
							.addGap(79)
							.addComponent(btnAtualizar)
							.addGap(77)
							.addComponent(btnRemover))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneContratos, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(293)
							.addComponent(lblSelecionarContrato))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(285)
							.addComponent(lblServiosContratados))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneServicos, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblSelecionarContrato)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPaneContratos, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblServiosContratados, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(scrollPaneServicos, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnVisualizar)
						.addComponent(btnAtualizar)
						.addComponent(btnRemover))
					.addContainerGap())
		);
		
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
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaServicos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setServicoSelecionado, que consegue usar as variáveis sem problemas.
							setContratoSelecionado(indiceSelecionadoContrato[0]);
							escreveTabelaServicos();
							
						}atualizaBotoes();
						
					}
				});
		
		//CONSTRUCAO DA TABELA
		
		tableServicos = new JTable();
		if(servicoSelecionado == null)
			escreveTabelaServicos();
		
		
		tableServicos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
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
						}atualizaBotoes();
						
					}
				}); 
		
	
		contratoSelecionado = listaContratos.get(indiceContratoSelecionado);	
		scrollPaneServicos.setViewportView(tableServicos);
		scrollPaneServicos.setRowHeaderView(table);
		
		getContentPane().setLayout(groupLayout);
		
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
		if(contratoSelecionado == null) {
			designTabela = new Object[0][3];
		}
		else {
			designTabela = new Object[contratoSelecionado.getListaServicos().size()][3];
			
		for (int i = 0; i < contratoSelecionado.getListaServicos().size(); i++) {
			Servico servicoAtual = contratoSelecionado.getListaServicos().get(i);
			if (servicoAtual.getTipo() == null){
				designTabela[i][0] = "Não especificado";
			}else{
				designTabela[i][0] = servicoAtual.getTipo();
			}
			designTabela[i][1] = "Teste";
			designTabela[i][2] = 0;
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
		if (contratoSelecionado != null)
			servicoSelecionado = contratoSelecionado.getListaServicos().get(indice); // Lembrando que a tabela está na mesma ordem que a listaContratos, então os índices são os mesmos.
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
	
	public List<Quarto> getListaQuartosDisponiveis() {
		return listaQuartosDisponiveis;
	}
	
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
