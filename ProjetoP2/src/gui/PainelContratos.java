package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import classes.Contrato;
import classes.Quarto;
import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class PainelContratos extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private JTable tableContratos;
	private List<Contrato> listaContratos;
	private ColecaoDeHospedes listaDeHospedes;
	private List<Quarto> listaQuartosDisponiveis;
	private JTable table;
	private Contrato contratoSelecionado = null;
	private JButton btnVisualizar;
	private JButton btnEditar;
	private JButton btnNovo;
	private JDesktopPane painelPrincipal;
	private PainelNovoContrato painelNovo;
	private PainelEditarContrato painelEditar;
	private JButton btnCancelaPesquisa;
	private JTextField textFieldPesquisa;
	private JButton btnPesquisar;
	private List<Contrato> colecaoAtiva = new ArrayList<Contrato>();
	private ColecaoDeQuartos colecaoDeQuartos;

	public PainelContratos(List<Contrato> listaContratos, JDesktopPane painelPrincipal, ColecaoDeHospedes listaDeHospedes, ColecaoDeQuartos colecaoDeQuartos){
		setNormalBounds(new Rectangle(0, 0, 1000, 1000));
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabela();
			}
		});
			
			
		this.painelPrincipal = painelPrincipal;
		this.listaDeHospedes = listaDeHospedes;
		this.colecaoDeQuartos = colecaoDeQuartos;
		this.listaQuartosDisponiveis = colecaoDeQuartos.getListaQuartos();
		this.listaContratos = listaContratos;
		colecaoAtiva = listaContratos;
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelContratos.class.getResource("/resources/contrato_icon.png")));
		setTitle("Contratos");
		setClosable(true);
		setBounds(0, 0, 770, 484);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PainelVisualizacaoContrato painelVisualizacao = new PainelVisualizacaoContrato(contratoSelecionado, getPainelPrincipal());
				//Não posso chamar o painel principal dentro de um construtor, por ele não ser final. Então fiz esse método que o retorna.
				adicionaNoPainel(painelVisualizacao);
				painelVisualizacao.show();
			}
		});
		btnVisualizar.setEnabled(false);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelEditar = new PainelEditarContrato(contratoSelecionado, getPainelPrincipal(), PainelContratos.this.colecaoDeQuartos, PainelContratos.this.listaDeHospedes);
				adicionaNoPainel(painelEditar);
				painelEditar.show();
			}
		});
		btnEditar.setEnabled(false);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelNovo = new PainelNovoContrato(getListaDeHospedes(), getListaQuartosDisponiveis(), getListaContratos(), getPainelPrincipal());
				adicionaNoPainel(painelNovo);
				painelNovo.show();
			}
		});
		
		btnCancelaPesquisa = new JButton("");
		btnCancelaPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldPesquisa.setText("");
				colecaoAtiva = PainelContratos.this.listaContratos;
				escreveTabela();
				btnCancelaPesquisa.setEnabled(false);
			}
		});
		btnCancelaPesquisa.setEnabled(false);
		btnCancelaPesquisa.setToolTipText("Cancelar pesquisa(A tabela volta a ter todos os contratos).");
		btnCancelaPesquisa.setIcon(new ImageIcon(PainelClientes.class.getResource("/resources/cross.png")));
		
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setToolTipText("Pesquisar contratos a partir do hóspede principal deles");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(textFieldPesquisa.getText().isEmpty())){
				List<Contrato> contratosPesquisados = new ArrayList<Contrato>();
				for (Contrato contrato: PainelContratos.this.listaContratos){
					if (contrato.getHospedePrincipal() != null && contrato.getHospedePrincipal().getNome().toLowerCase().contains(textFieldPesquisa.getText().toLowerCase())){
						contratosPesquisados.add(contrato);
					}
				}
				colecaoAtiva = contratosPesquisados;
				if (contratosPesquisados.size() == 0){
					JOptionPane.showMessageDialog(null, "Sem resultados.");
					escreveTabela();
				}else{
					JOptionPane.showMessageDialog(null, (contratosPesquisados.size() >= 2) ? contratosPesquisados.size() + " contratos encontrados." : contratosPesquisados.size() + " contrato encontrado.");
					escreveTabela();
				}
				btnCancelaPesquisa.setEnabled(true);
			}
			}
		});
		btnPesquisar.setIcon(new ImageIcon(PainelClientes.class.getResource("/resources/search.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.addGap(229)
							.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.addGap(210)
							.addComponent(btnNovo, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCancelaPesquisa, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPesquisa, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(505))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnPesquisar, 0, 0, Short.MAX_VALUE)
						.addComponent(textFieldPesquisa, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addComponent(btnCancelaPesquisa, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnVisualizar)
						.addComponent(btnEditar)
						.addComponent(btnNovo))
					.addGap(5))
		);

		tableContratos = new JTable();
		escreveTabela();
		
		tableContratos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableContratos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableContratos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							contratoSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setContratoSelecionado(indiceSelecionado[0]);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		
		scrollPanePrincipal.setViewportView(tableContratos);
		
		
		scrollPanePrincipal.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
	public ColecaoDeHospedes getListaDeHospedes() {
		return listaDeHospedes;
	}
	private List<Contrato> getListaContratos() {
		return listaContratos;
	}
	private List<Quarto> getListaQuartosDisponiveis() {
		return listaQuartosDisponiveis;
	}
	private void setContratoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro método, posso usar variáveis não finais a vontade sem problema.
		contratoSelecionado = listaContratos.get(indice); // Lembrando que a tabela está na mesma ordem que a listaContratos, então os índices são os mesmos.
	}
	private void atualizaBotoes(){
		if (contratoSelecionado == null){
			btnEditar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}else{
			btnEditar.setEnabled(contratoSelecionado.getStatus().equals("ABERTO") || contratoSelecionado.getStatus().equals("RESERVA"));
			btnVisualizar.setEnabled(true);
		}
	}
	private void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	private JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
	private void escreveTabela(){
		// IN�CIO DE CONSTRUÇÃO DA TABELA
				// designTabela = o conteúdo da tabela em si, preenchida através de um loop for.
						Object[][] designTabela = new Object[colecaoAtiva.size()][5];
						for (int i = 0; i < colecaoAtiva.size(); i++){
							Contrato contratoAtual = colecaoAtiva.get(i);
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
							designTabela[i][3] = "R$ " + contratoAtual.calculaPrecoFinal();
							if (contratoAtual.getStatus().equals("RESERVA")){
								designTabela[i][3] = "-- RESERVA --";
							}
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
}
