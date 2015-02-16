package gui;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import core.Estrategia;
import core.Hospede;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeEstrategias;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class PainelEstrategias extends JInternalFrame {
	private JButton btnAddEstrategia;
	private JButton btnVisualizarEstrategia;
	private JButton btnEditarEstrategia;
	private JTable tabelaEstrategias;
	private ColecaoDeEstrategias listaEstrategias;
	private ColecaoDeContratos listaContratos;
	JDesktopPane painelPrincipal;

	public PainelEstrategias(ColecaoDeEstrategias listaEstrategias, ColecaoDeContratos listaContratos, JDesktopPane painelPrincipal) {
		setFrameIcon(new ImageIcon(PainelEstrategias.class.getResource("/resources/calendar146.png")));
		setClosable(true);
		setTitle("Painel Estratégias");
		setBounds(0, 0, 650, 450);
		
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
		btnVisualizarEstrategia.setEnabled(false);
		
		btnEditarEstrategia = new JButton("Editar estratégia");
		btnEditarEstrategia.setEnabled(false);
		
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
							.addComponent(btnEditarEstrategia, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
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
						.addComponent(btnEditarEstrategia)
						.addComponent(btnVisualizarEstrategia))
					.addGap(10))
		);
		
		tabelaEstrategias = new JTable();
		scrollPane.setViewportView(tabelaEstrategias);
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
	}
}
