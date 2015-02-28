package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import core.Estrategia;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeEstrategias;

public class PainelEstrategias extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7820263481612861415L;
	private JButton btnAddEstrategia;
	private JButton btnVisualizarEstrategia;
	private JButton btnRemoverEstrategia;
	private JTable tabelaEstrategias;
	private ColecaoDeEstrategias listaEstrategias;
	private ColecaoDeContratos listaContratos;
	JDesktopPane painelPrincipal;
	private Estrategia estrategiaSelecionada;

	public PainelEstrategias(ColecaoDeEstrategias listaEstrategias, ColecaoDeContratos listaContratos, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabela();
			}
		});
		setFrameIcon(new ImageIcon(PainelEstrategias.class.getResource("/resources/calendar146.png")));
		setClosable(true);
		setTitle("Estratégias");
		setBounds(0, 0, 662, 453);

		this.listaEstrategias = listaEstrategias;
		this.listaContratos = listaContratos;
		this.painelPrincipal = painelPrincipal;

		JScrollPane scrollPane = new JScrollPane();

		btnAddEstrategia = new JButton("Adicionar estratégia");
		btnAddEstrategia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PainelAdicionaEstrategias painelAdd = new PainelAdicionaEstrategias(PainelEstrategias.this.listaContratos, PainelEstrategias.this.listaEstrategias);
				PainelEstrategias.this.painelPrincipal.add(painelAdd);
				painelAdd.show();
			}
		});

		btnVisualizarEstrategia = new JButton("Visualizar estratégia");
		btnVisualizarEstrategia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PainelVisualizacaoEstrategia painelVisualiza = new PainelVisualizacaoEstrategia(estrategiaSelecionada);
				PainelEstrategias.this.painelPrincipal.add(painelVisualiza);
				painelVisualiza.show();
			}
		});
		btnVisualizarEstrategia.setEnabled(false);

		btnRemoverEstrategia = new JButton("Remover estratégia");
		btnRemoverEstrategia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escolha = JOptionPane.showOptionDialog(null, "Você deseja remover a estratégia selecionada da lista?\nLembrando que, ao fazer isso, os contratos que já foram marcados com tal estratégia não serão recalculados.", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
				if (escolha == JOptionPane.YES_OPTION){
					PainelEstrategias.this.listaEstrategias.removeEstrategia(estrategiaSelecionada);
					escreveTabela();
				}
			}
		});
		btnRemoverEstrategia.setEnabled(false);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(12)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(btnAddEstrategia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(115)
												.addComponent(btnVisualizarEstrategia, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
												.addGap(128)
												.addComponent(btnRemoverEstrategia, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
												.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(btnAddEstrategia)
								.addComponent(btnRemoverEstrategia)
								.addComponent(btnVisualizarEstrategia))
								.addGap(10))
				);

		tabelaEstrategias = new JTable();
		scrollPane.setViewportView(tabelaEstrategias);

		ListSelectionModel modeloSelecaoLinha = tabelaEstrategias.getSelectionModel(); 

		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaEstrategias.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					estrategiaSelecionada = null;
				}else{
					estrategiaSelecionada = PainelEstrategias.this.listaEstrategias.get(indiceSelecionado[0]);
				}atualizaBotoes();

			}
		});		
		getContentPane().setLayout(groupLayout);
		escreveTabela();

	}
	public void escreveTabela(){
		// INÍCIO DE CONSTRUÇÃO DA TABELA
		Object[][] designTabela = new Object[listaEstrategias.getListaEstrategiasTamanho()][4];
		for (int i = 0; i < listaEstrategias.getListaEstrategiasTamanho(); i++){
			Estrategia estrategiaAtual = listaEstrategias.get(i);
			designTabela[i][0] = estrategiaAtual.getDescricao();
			designTabela[i][1] = estrategiaAtual.getInicioPeriodoString();
			designTabela[i][2] = estrategiaAtual.getFinalPeriodoString();
			designTabela[i][3] = estrategiaAtual.getModificador() * estrategiaAtual.getTipoDeEstrategia().getMultiplicador() + "%";

			// FIM DE CONSTRUÇÃO DE TABELA.

		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Descrição", "Início", "Final", "Modificador"
		}) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};


		tabelaEstrategias.setModel(modeloTabela); 
		tabelaEstrategias.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		atualizaBotoes();
	}
	public void atualizaBotoes(){
		btnRemoverEstrategia.setEnabled(!(estrategiaSelecionada == null));
		btnVisualizarEstrategia.setEnabled(!(estrategiaSelecionada == null));
	}
}
