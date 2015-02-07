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
import javax.swing.JPanel;
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

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;
import classes.Contrato;
import classes.Hospede;
import classes.Quarto;

public class PainelAdicionaQuartos extends JInternalFrame {
	private ColecaoDeHospedes listaDeHospedes;
	private ColecaoDeQuartos listaDeQuartos;
	private List<Hospede> listaHospedesDoContrato;
	private List<Hospede> listaHospedesSemContrato = new ArrayList<Hospede>();
	private List<Quarto> listaQuartosDoContrato;
	private Quarto quartoVagoSelecionado;
	private Quarto quartoContratoSelecionado;
	private JPanel panelQuartos;
	private JScrollPane scrollPane_2;
	private JButton btnAdicionarNoContratoQuarto;
	private JTable tabelaQuartosLivres;
	private JLabel lblQuartosLivres;
	private DialogoDiarias dialogoDiarias;
	private int diariasContrato;
	private Contrato contrato;
	private int[] indiceHospedesSelecionados;
	private Hospede hospedePrincipal;
	private JDesktopPane painelPrincipal;
	private JTable tableHospedesSemContrato;
	private JTabbedPane tabbedPane_1;
	private JScrollPane scrollPaneNoContrato;
	

	/**
	 * Create the frame.
	 */
	public PainelAdicionaQuartos(ColecaoDeHospedes listaDeHospedes, ColecaoDeQuartos listaDeQuartos, Contrato contrato, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				escreveTabelas();
				escreveTabelaHospedesSemContrato();
			}
		});
		
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 970, 400);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/quartoIcon.png")));
		setTitle("Adicionar Quartos");
		
		this.painelPrincipal = painelPrincipal;
		this.listaDeHospedes = listaDeHospedes;
		
		adicionaHospedesSemContratoNaLista();
		
		listaHospedesDoContrato = new ArrayList<Hospede>();
		listaQuartosDoContrato = new ArrayList<Quarto>();
		this.listaDeQuartos = listaDeQuartos;
		this.contrato = contrato;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
		);
		
		
		panelQuartos = new JPanel();
		tabbedPane.addTab("Quartos", null, panelQuartos, null);
		
		lblQuartosLivres = new JLabel("Quartos livres:");
		lblQuartosLivres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane_2 = new JScrollPane();
		
		btnAdicionarNoContratoQuarto = new JButton("Adicionar no contrato\r\n");
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnAdicionarNoContratoQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (diariasContrato == 0) {
					dialogoDiarias = new DialogoDiarias();
					dialogoDiarias.setVisible(true);
					//Como DialogoDiarias é modal, daqui para baixo só será processado quando DialogoDiarias for "disposed"
					diariasContrato = dialogoDiarias.getDiarias();
				}
				quartoVagoSelecionado.setToOcupado(diariasContrato);
				getContrato().getListaQuartosAlugados().add(quartoVagoSelecionado);
				getListaDeQuartos().getListaQuartosVagos().remove(quartoVagoSelecionado);
				
				adicionaHospedesSelecionadosNoContrato(indiceHospedesSelecionados);
				
				escreveTabelas();
				disposeOnClosed();
			}
		});
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnAdicionarNoContratoQuarto.setEnabled(false);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
								.addComponent(tabbedPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
								.addGroup(gl_panelQuartos.createSequentialGroup()
									.addGap(703)
									.addComponent(btnAdicionarNoContratoQuarto, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))))
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap(417, Short.MAX_VALUE)
					.addComponent(lblQuartosLivres, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(388))
		);
		gl_panelQuartos.setVerticalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQuartosLivres, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addGap(24)
					.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(btnAdicionarNoContratoQuarto)
					.addContainerGap())
		);
		
		JScrollPane scrollPaneSemContrato = new JScrollPane();
		tabbedPane_1.addTab("Hóspedes Sem Quarto", null, scrollPaneSemContrato, null);
		
		tableHospedesSemContrato = new JTable();
		scrollPaneSemContrato.setColumnHeaderView(tableHospedesSemContrato);
		
		tableHospedesSemContrato.setRowSelectionAllowed(true);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tableHospedesSemContrato.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		scrollPaneSemContrato.setViewportView(tableHospedesSemContrato);
		
		scrollPaneNoContrato = new JScrollPane();
		tabbedPane_1.addTab("Hóspedes no Quarto", null, scrollPaneNoContrato, null);
		
		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				indiceHospedesSelecionados = tableHospedesSemContrato.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceHospedesSelecionados.length <= 0){
					indiceHospedesSelecionados = null;
				}else{
					tableHospedesSemContrato.clearSelection();
				}atualizaBotoes();
				
			}
		});
		
		tabelaQuartosLivres = new JTable();
		scrollPane_2.setViewportView(tabelaQuartosLivres);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha4 = tabelaQuartosLivres.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha4.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaQuartosLivres.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					quartoVagoSelecionado = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setQuartoVagoSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
				
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		panelQuartos.setLayout(gl_panelQuartos);
		getContentPane().setLayout(groupLayout);
		escreveTabelas();

	}
	
	private void adicionaHospedesSelecionadosNoContrato(int[] indices) {
		for (int i = 0; i < indices.length; i++) {
				listaHospedesSemContrato.get(i).setContratoLigado(getContrato());
				listaHospedesSemContrato.remove(listaDeHospedes.getIndice(i));
		}
	}
	
	private void adicionaHospedesSemContratoNaLista() {
		for (int i = 0; i < listaDeHospedes.getListaHospedeTamanho(); i++) {
			if (listaDeHospedes.getIndice(i).getContratoLigado() == null) {
				listaHospedesSemContrato.add(listaDeHospedes.getIndice(i));
			}
		}
	}
	
	private void disposeOnClosed() {
		this.dispose();
	}
	
	public JDesktopPane getPainelPrincipal() {
		return painelPrincipal;
	}
	
	public Contrato getContrato() {
		return contrato;
	}
	
	private void setQuartoVagoSelecionado (int i){
		quartoVagoSelecionado = listaDeQuartos.getListaQuartosVagos().get(i);
	}
	
	private void atualizaBotoes(){
		btnAdicionarNoContratoQuarto.setEnabled(!(quartoVagoSelecionado == null));
	}
	
	private void escreveTabelas(){
		Object[][] designTabela;
		escreveTabelaHospedesSemContrato();
				
			// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES NO CONTRATO
				
			// PREENCHENDO TABELA DOS QUARTOS VAGOS NO HOTEL
				listaDeQuartos.sortQuartosNumero();
				JOptionPane.showMessageDialog(null, listaDeQuartos.getListaQuartosVagos().size());
				designTabela = new Object[listaDeQuartos.getListaQuartosVagos().size()][4];
				if (listaDeQuartos.getListaQuartosVagos().size() > 0){
				for (int i = 0; i < listaDeQuartos.getListaQuartosVagos().size(); i++){
					Quarto quartoAtual = listaDeQuartos.getListaQuartosVagos().get(i);
					//Para preencher a primeira coluna da linha: Descrição do quarto
					designTabela[i][0] = quartoAtual.getTipo();
					//Para preencher a segunda coluna da linha: O preço da diária
					designTabela[i][1] = "R$ " + quartoAtual.getPrecoDiaria();
					//Para preencher a terceira coluna da linha: O número de pessoas que o quarto acomoda
					designTabela[i][2] = quartoAtual.getNumeroHospedes();
					//Para preencher a quarta coluna da linha: O número do quarto
					designTabela[i][3] = quartoAtual.getNumero();
				}
					//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
					@SuppressWarnings("serial")
					DefaultTableModel modeloTabela3 = new DefaultTableModel(designTabela, new String[] {
							"Descrição", "Preço da diária", "Num. máximo de hóspedes (sem cama extra)", "Número"
					}) {
			
						@Override
					    public boolean isCellEditable(int row, int column) {
					        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
					        return false;
					    }
					};
					tabelaQuartosLivres.setModel(modeloTabela3);
					tabelaQuartosLivres.setRowSelectionAllowed(true);
					
			// FIM DO PREENCHIMENTO DA TABELA DOS QUARTOS VAGOS NO HOTEL
								
		
		
	}
}
	
	private void escreveTabelaHospedesSemContrato() {
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
			// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES SEM CONTRATO
						
				tableHospedesSemContrato.setModel(modeloTabela);
				tableHospedesSemContrato.setRowSelectionAllowed(true);
	}
	
	public ColecaoDeQuartos getListaDeQuartos() {
		return listaDeQuartos;
	}
}