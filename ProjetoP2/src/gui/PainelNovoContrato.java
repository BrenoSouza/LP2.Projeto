package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import classes.Hospede;
import classes.Quarto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PainelNovoContrato extends JInternalFrame {
	private List<Hospede> listaHospedes, listaHospedesDoContrato, listaHospedesSemContrato;
	private List<Quarto> listaQuartosDisponiveis, listaQuartosDoContrato;
	private Hospede hospedeSelecionado = null;
	private JScrollPane scrollPane;
	private JButton btnCriarNovo;
	private JButton btnAdicionarNoContrato;
	private JTable tabelaHospedesSemContrato;

	

	/**
	 * Create the frame.
	 */
	public PainelNovoContrato(List<Hospede> listaHospedes, List<Quarto> listaQuartosDisponiveis) {
		setResizable(true);
		setClosable(true);
		setBounds(0, 0, 650, 280);
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
		
		btnCriarNovo = new JButton("Criar novo hóspede");
		btnCriarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO quando o painel de adicionar clientes esteja pronto;
			}
		});
		
		JLabel lblHospedesSemContrato = new JLabel("Hóspedes sem contrato:");
		lblHospedesSemContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnAdicionarNoContrato = new JButton("Adicionar no contrato\r\n");
		btnAdicionarNoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaHospedesDoContrato.add(hospedeSelecionado);
				listaHospedesSemContrato.remove(hospedeSelecionado);
				escreveTabela();
			}
		});
		btnAdicionarNoContrato.setEnabled(false);
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedesSemContrato)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionarNoContrato, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(337)
							.addComponent(btnCriarNovo, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblHospedesSemContrato)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdicionarNoContrato)
						.addComponent(btnCriarNovo)))
		);
		
		tabelaHospedesSemContrato = new JTable();
		escreveTabela();
		scrollPane.setViewportView(tabelaHospedesSemContrato);
		getContentPane().setLayout(groupLayout);

	}
	private void setHospedeSelecionado(int i){
		hospedeSelecionado = listaHospedesSemContrato.get(i);
	}
	private void atualizaBotoes(){
		btnAdicionarNoContrato.setEnabled(!(hospedeSelecionado == null));
	}
	private void escreveTabela(){
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
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
	}
}
