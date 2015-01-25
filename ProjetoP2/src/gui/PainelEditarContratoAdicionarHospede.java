package gui;

import java.awt.EventQueue;

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
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import colecoes.ColecaoDeHospedes;
import classes.Contrato;
import classes.Hospede;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelEditarContratoAdicionarHospede extends JInternalFrame {
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JButton btnRemoverDoContrato;
	private JButton btnAdicionarNoContrato;
	private JTable tabelaHospedesSemContrato;
	private JTable tabelaHospedesNoContrato;
	private ColecaoDeHospedes listaDeHospedes;
	private List<Hospede> listaHospedesDoContrato;
	private List<Hospede> listaHospedesSemContrato;
	private Hospede hospedeSelecionado;
	private Hospede hospedeSelecionado2;
	private Contrato contratoSelecionado;
	private JButton btnCriarNovo;
	private JDesktopPane painelPrincipal;


	public PainelEditarContratoAdicionarHospede(ColecaoDeHospedes listaDeHospedes, Contrato contrato, JDesktopPane painelPrincipal) {
		setResizable(true);
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				escreveTabelas();
			}
		});
		setBounds(0, 0, 970, 380);
		this.listaDeHospedes = listaDeHospedes;
		this.painelPrincipal = painelPrincipal;
		btnRemoverDoContrato = new JButton("Remover do contrato");
		btnRemoverDoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse hóspede do contrato?" + (hospedeSelecionado2 == contratoSelecionado.getHospedePrincipal() ? "\nEsse hóspede está configurado como o hóspede principal do contrato selecionado." : ""), /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
				if (escolha == JOptionPane.YES_OPTION){
					listaHospedesDoContrato.remove(hospedeSelecionado2);
					if (hospedeSelecionado2 == contratoSelecionado.getHospedePrincipal()){
						contratoSelecionado.setHospedePrincipal(null);
					}hospedeSelecionado2.setContratoLigado(null);
					escreveTabelas();
				}

			}
		});
		btnRemoverDoContrato.setEnabled(false);
		this.contratoSelecionado = contrato;
		this.listaHospedesDoContrato = contrato.getListaHospedes();

		btnAdicionarNoContrato = new JButton("Adicionar no contrato");
		btnAdicionarNoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contratoSelecionado.getHospedePrincipal() == null){
					int escolha = JOptionPane.showOptionDialog(null, "Você deseja marcar esse hóspede como o hóspede principal do contrato? \nO hóspede principal do contrato é o que será procurado para questões financeiras.", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {
						contratoSelecionado.setHospedePrincipal(hospedeSelecionado);
					}
				}
				PainelEditarContratoAdicionarHospede.this.listaHospedesDoContrato.add(hospedeSelecionado);
				hospedeSelecionado.setContratoLigado(contratoSelecionado);
				escreveTabelas();
			}
		});
		btnAdicionarNoContrato.setEnabled(false);

		JLabel label = new JLabel("Hóspedes sem contrato:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel label_1 = new JLabel("Hóspedes no contrato:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollPane = new JScrollPane();

		scrollPane_1 = new JScrollPane();
		
		btnCriarNovo = new JButton("Cadastrar novo hóspede");
		btnCriarNovo.setEnabled(true);
		btnCriarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					PainelCadastroClientes painelCadastro = new PainelCadastroClientes(PainelEditarContratoAdicionarHospede.this.listaDeHospedes);
					PainelEditarContratoAdicionarHospede.this.painelPrincipal.add(painelCadastro);
					painelCadastro.show();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRemoverDoContrato, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
							.addGap(175)
							.addComponent(btnCriarNovo, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addGap(165)
							.addComponent(btnAdicionarNoContrato, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
					.addGap(15))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRemoverDoContrato)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAdicionarNoContrato)
							.addComponent(btnCriarNovo)))
					.addGap(16))
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
					tabelaHospedesSemContrato.clearSelection();
				}atualizaBotoes();

			}
		});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		scrollPane.setViewportView(tabelaHospedesNoContrato);

		tabelaHospedesSemContrato = new JTable();
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
					tabelaHospedesNoContrato.clearSelection();
				}atualizaBotoes();

			}
		});
		scrollPane_1.setViewportView(tabelaHospedesSemContrato);
		getContentPane().setLayout(groupLayout);
		escreveTabelas();

	}
	private void escreveTabelas(){
		listaHospedesSemContrato = new ArrayList<Hospede>();
		for (int i = 0; i < listaDeHospedes.getListaHospedeTamanho(); i++){
			if (listaDeHospedes.getIndice(i).getContratoLigado() == null){
				listaHospedesSemContrato.add(listaDeHospedes.getIndice(i));
			}
		}listaHospedesSemContrato.removeAll(listaHospedesDoContrato);
		LocalDate presente = LocalDate.now();
		// PREENCHENDO TABELA DOS HÓSPEDES SEM CONTRATO
		Collections.sort(listaHospedesSemContrato);
		Object [][] designTabela = new Object[listaHospedesSemContrato.size()][3];
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
		// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES SEM CONTRATO

		// PREENCHENDO TABELA DOS HÓSPEDES DO CONTRATO
		Collections.sort(listaHospedesDoContrato);
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

		// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES NO CONTRATO
	}
	private void atualizaBotoes(){
		btnAdicionarNoContrato.setEnabled(!(hospedeSelecionado == null));
		btnRemoverDoContrato.setEnabled(!(hospedeSelecionado2 == null));
	}
	private void setHospedeSelecionado(int i){
		hospedeSelecionado = listaHospedesSemContrato.get(i);
	}
	private void setHospedeSelecionado2 (int i){
		hospedeSelecionado2 = listaHospedesDoContrato.get(i);
	}
}
