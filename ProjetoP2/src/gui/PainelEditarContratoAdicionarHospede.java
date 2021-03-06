package gui;

import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import core.Contrato;
import core.Hospede;
import core.colecoes.ColecaoDeHospedes;

public class PainelEditarContratoAdicionarHospede extends JInternalFrame implements Atualizador{

	private static final long serialVersionUID = 4369742768015429682L;
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
	private PainelCadastroClientes painelCadastro;
	private Atualizador framePai;


	public PainelEditarContratoAdicionarHospede(ColecaoDeHospedes listaDeHospedes, Contrato contrato, JDesktopPane painelPrincipal, Atualizador framePai) {
		setFrameIcon(new ImageIcon(PainelEditarContratoAdicionarHospede.class.getResource("/resources/contrato_icon.png")));
		setTitle("Adicionar hóspede no contrato");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0, 970, 380);
		this.listaDeHospedes = listaDeHospedes;
		this.painelPrincipal = painelPrincipal;
		this.framePai = framePai;
		btnRemoverDoContrato = new JButton("Remover do contrato");
		btnRemoverDoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse hóspede do contrato?" + (hospedeSelecionado2 == contratoSelecionado.getHospedePrincipal() ? Main.quebraDeLinha + "Esse hóspede está configurado como o hóspede principal do contrato selecionado." : ""), /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
				if (escolha == JOptionPane.YES_OPTION){
					listaHospedesDoContrato.remove(hospedeSelecionado2);
					if (hospedeSelecionado2.equals(contratoSelecionado.getHospedePrincipal())){
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
					int escolha = JOptionPane.showOptionDialog(null, "Você deseja marcar esse hóspede como o hóspede principal do contrato? " + Main.quebraDeLinha + "O hóspede principal do contrato é o que será procurado para questões financeiras.", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
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
				  if (painelCadastro == null || painelCadastro.isClosed()){
					painelCadastro = new PainelCadastroClientes(PainelEditarContratoAdicionarHospede.this.listaDeHospedes, PainelEditarContratoAdicionarHospede.this);
					PainelEditarContratoAdicionarHospede.this.painelPrincipal.add(painelCadastro);
					painelCadastro.show();}
				  else{
				    painelCadastro.toFront();
				  }
				} catch (java.text.ParseException e1) {
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
		ListSelectionModel modeloSelecaoLinha2 = tabelaHospedesNoContrato.getSelectionModel(); 

		modeloSelecaoLinha2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaHospedesNoContrato.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					hospedeSelecionado2 = null;
				}else{
					setHospedeSelecionado2(indiceSelecionado[0]);
					tabelaHospedesSemContrato.clearSelection();
				}atualizaBotoes();

			}
		});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		scrollPane.setViewportView(tabelaHospedesNoContrato);

		tabelaHospedesSemContrato = new JTable();
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tabelaHospedesSemContrato.getSelectionModel(); 

		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaHospedesSemContrato.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					hospedeSelecionado = null;
				}else{
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
			LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
			Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
			designTabela[i][2] = periodoDeTempo.getYears();
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Nome", "CPF", "Idade"
		}) {

			@Override
			public boolean isCellEditable(int row, int column) {
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
			LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
			Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
			designTabela[i][2] = periodoDeTempo.getYears();
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela2 = new DefaultTableModel(designTabela, new String[] {
				"Nome", "CPF", "Idade"
		}) {

			@Override
			public boolean isCellEditable(int row, int column) {
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
  public void atualiza() {
    escreveTabelas();    
  }
  @Override
  public void dispose(){
    framePai.atualiza();
    super.dispose();
  }
}
