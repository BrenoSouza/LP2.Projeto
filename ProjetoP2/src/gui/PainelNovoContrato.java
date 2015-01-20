package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTable;

import classes.Hospede;
import classes.Quarto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelNovoContrato extends JInternalFrame {
	private JScrollPane scrollPane_1;
	private JLabel lblHospedesSemContrato;
	private JLabel lblHospedesNoContrato;
	private List<Hospede> listaHospedes, listaHospedesDoContrato, listaHospedesSemContrato;
	private List<Quarto> listaQuartosDisponiveis, listaQuartosDoContrato;
	private Hospede hospedeSelecionado = null;
	private Hospede hospedeSelecionado2 = null;
	private JScrollPane scrollPane;
	private JButton btnCriarNovo;
	private JButton btnAdicionarNoContrato;
	private JTable tabelaHospedesSemContrato;
	private JTable tabelaHospedesNoContrato;
	private JButton btnRemoverDoContrato;



	/**
	 * Create the frame.
	 */
	public PainelNovoContrato(List<Hospede> listaHospedes, List<Quarto> listaQuartosDisponiveis) {
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 660, 400);
		this.listaHospedes = listaHospedes;
		listaHospedesDoContrato = new ArrayList<Hospede>();
		listaQuartosDoContrato = new ArrayList<Quarto>();
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		listaHospedesSemContrato = new ArrayList<Hospede>();
		for (int i = 0; i < listaHospedes.size(); i++){
			if (listaHospedes.get(i).getContratoLigado() == null){
				listaHospedesSemContrato.add(listaHospedes.get(i));
			}
		}
		String[] nomesHospedes = new String[listaHospedesSemContrato.size() + 1]; // Criando a lista com os nomes dos hóspedes para serem escolhidos.
		nomesHospedes[0] = "-- SELECIONE UM HÓSPEDE --"; // Criando uma mensagem para ser a de primeiro índice.
		for (int i = 0; i < listaHospedesSemContrato.size(); i++){
			nomesHospedes[i + 1] = (listaHospedesSemContrato.get(i)).getNome();
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		scrollPane_1 = new JScrollPane();
		
		scrollPane = new JScrollPane();
		
		btnAdicionarNoContrato = new JButton("Adicionar no contrato\r\n");
		btnAdicionarNoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaHospedesDoContrato.add(hospedeSelecionado);
				listaHospedesSemContrato.remove(hospedeSelecionado);
				escreveTabelas();
			}
		});
		btnAdicionarNoContrato.setEnabled(false);
		
		btnRemoverDoContrato = new JButton("Remover do contrato");
		btnRemoverDoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaHospedesSemContrato.add(hospedeSelecionado2);
				listaHospedesDoContrato.remove(hospedeSelecionado2);
				escreveTabelas();
			}
		});
		btnRemoverDoContrato.setEnabled(false);
		
		btnCriarNovo = new JButton("Criar novo hóspede");
		
		lblHospedesSemContrato = new JLabel("Hóspedes sem contrato:");
		lblHospedesSemContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblHospedesNoContrato = new JLabel("Hóspedes no contrato:");
		lblHospedesNoContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedesSemContrato, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHospedesNoContrato, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAdicionarNoContrato, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(96)
							.addComponent(btnRemoverDoContrato, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(102)
							.addComponent(btnCriarNovo, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblHospedesSemContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblHospedesNoContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdicionarNoContrato)
						.addComponent(btnRemoverDoContrato)
						.addComponent(btnCriarNovo))
					.addContainerGap())
		);
		
		tabelaHospedesNoContrato = new JTable();
		tabelaHospedesNoContrato.setRowSelectionAllowed(true);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha2 = tabelaHospedesNoContrato.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha2.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaHospedesNoContrato.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					hospedeSelecionado2 = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setHospedeSelecionado2(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		scrollPane_1.setViewportView(tabelaHospedesNoContrato);
		
		tabelaHospedesSemContrato = new JTable();
		tabelaHospedesSemContrato.setRowSelectionAllowed(true);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tabelaHospedesSemContrato.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaHospedesSemContrato.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					hospedeSelecionado = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setHospedeSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
		
		scrollPane.setViewportView(tabelaHospedesSemContrato);
		escreveTabelas();
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	private void setHospedeSelecionado(int i){
		hospedeSelecionado = listaHospedesSemContrato.get(i);
	}
	private void setHospedeSelecionado2 (int i){
		hospedeSelecionado2 = listaHospedesDoContrato.get(i);
	}
	private void atualizaBotoes(){
		btnAdicionarNoContrato.setEnabled(!(hospedeSelecionado == null));
		btnRemoverDoContrato.setEnabled(!(hospedeSelecionado2 == null));
	}
	private void escreveTabelas(){
		Object [][] designTabela = new Object[listaHospedesSemContrato.size()][3];
		LocalDate presente = LocalDate.now();
		for (int i = 0; i < listaHospedesSemContrato.size(); i++){
			Hospede hospedeAtual = listaHospedesSemContrato.get(i);
			//Para preencher a primeira coluna da linha: Nome do hóspede
			designTabela[i][0] = hospedeAtual.getNome();
			//Para preencher a segunda coluna da linha: CPF do hóspede
			designTabela[i][1] = hospedeAtual.getCpf();
			//Para preencher a terceira coluna da linha: Idade do hóspede
			Calendar nascimento = hospedeAtual.getDataNascimento();
			LocalDate diaNascimento = LocalDate.of(nascimento.get(Calendar.YEAR), nascimento.get(Calendar.MONTH) + 1, nascimento.get(Calendar.DAY_OF_MONTH));
			Period periodoDeTempo = Period.between(diaNascimento, presente);
			designTabela[i][2] = periodoDeTempo.getYears();
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
		tabelaHospedesSemContrato.setModel(modeloTabela);
		tabelaHospedesSemContrato.setRowSelectionAllowed(true);
		
		designTabela = new Object[listaHospedesDoContrato.size()][3];
		for (int i = 0; i < listaHospedesDoContrato.size(); i++){
			Hospede hospedeAtual = listaHospedesDoContrato.get(i);
			//Para preencher a primeira coluna da linha: Nome do hóspede
			designTabela[i][0] = hospedeAtual.getNome();
			//Para preencher a segunda coluna da linha: CPF do hóspede
			designTabela[i][1] = hospedeAtual.getCpf();
			//Para preencher a terceira coluna da linha: Idade do hóspede
			Calendar nascimento = hospedeAtual.getDataNascimento();
			LocalDate diaNascimento = LocalDate.of(nascimento.get(Calendar.YEAR), nascimento.get(Calendar.MONTH) + 1, nascimento.get(Calendar.DAY_OF_MONTH));
			Period periodoDeTempo = Period.between(diaNascimento, presente);
			designTabela[i][2] = periodoDeTempo.getYears();
		}
		//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela2 = new DefaultTableModel(designTabela, new String[] {
				"Nome", "CPF", "Idade"
		}) {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};
		tabelaHospedesNoContrato.setModel(modeloTabela2);
		tabelaHospedesNoContrato.setRowSelectionAllowed(true);
	}
}
