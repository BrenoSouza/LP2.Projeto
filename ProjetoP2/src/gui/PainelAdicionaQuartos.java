package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import core.Contrato;
import core.Hospede;
import core.Quarto;
import core.Servico;
import core.colecoes.ColecaoDeHospedes;

public class PainelAdicionaQuartos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6419476853081059807L;
	private JPanel panelPrincipal;
	private JPanel panelQuartos;
	private JScrollPane scrollPaneQuartos;
	private JTable tabelaQuartos;
	private JPanel panelHospedes;
	private DialogoDiarias dialogoDiarias;
	private int diariasContrato;
	private JScrollPane scrollHospedesSemContrato;
	private JScrollPane scrollHospedesNoQuarto;
	private JTable tableSemHospedesQuartos;
	private JTable tableHospedesNoQuarto;
	private JButton btnAdicionar;
	private JButton btnAdicionarHospede;
	private JButton btnRemoverHospede;
	private Quarto quartoSelecionado;
	private List<Quarto> listaQuartosDisponiveis;
	private Contrato contrato;
	private List<Hospede> hospedesSemQuarto = new ArrayList<Hospede>();
	private List<Hospede> hospedesNoQuarto = new ArrayList<Hospede>();
	public int diasRestantes;
	@SuppressWarnings("unused")
	private Hospede hospedeSelecionado;
	
	public PainelAdicionaQuartos(Servico quarto, ColecaoDeHospedes listaDeHospedes, List<Quarto> listaQuartosDisponiveis, Contrato contrato, JDesktopPane painelPrincipal, int diasRestantes) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override	
			public void internalFrameActivated(InternalFrameEvent e) {
	
			}
		});
		
		setClosable(true);
		setBounds(100, 100, 845, 381);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/quartoIcon.png")));
		setTitle("Adicionar Quartos / Editar Quartos");
		getContentPane().setLayout(null);
		
		
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		this.contrato = contrato;
		this.diasRestantes = diasRestantes;
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 835, 349);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panelQuartos = new JPanel();
		panelQuartos.setBounds(12, 40, 811, 102);
		panelPrincipal.add(panelQuartos);
		panelQuartos.setLayout(null);
		
		scrollPaneQuartos = new JScrollPane();
		scrollPaneQuartos.setBounds(0, 0, 811, 102);
		panelQuartos.add(scrollPaneQuartos);
		
		tabelaQuartos = new JTable();
		scrollPaneQuartos.setColumnHeaderView(tabelaQuartos);
		
		panelHospedes = new JPanel();
		panelHospedes.setBounds(12, 188, 811, 119);
		panelPrincipal.add(panelHospedes);
		panelHospedes.setLayout(null);
		
		JTabbedPane tabHospedes = new JTabbedPane(JTabbedPane.TOP);
		tabHospedes.setBounds(0, 0, 811, 119);
		panelHospedes.add(tabHospedes);
		
		scrollHospedesSemContrato = new JScrollPane();
		tabHospedes.addTab("Hospedes Sem Contrato", null, scrollHospedesSemContrato, null);
		
		tableSemHospedesQuartos = new JTable();
		scrollHospedesSemContrato.setColumnHeaderView(tableSemHospedesQuartos);
		
		scrollHospedesNoQuarto = new JScrollPane();
		tabHospedes.addTab("Hospedes No Quarto", null, scrollHospedesNoQuarto, null);
		
		tableHospedesNoQuarto = new JTable();
		scrollHospedesNoQuarto.setColumnHeaderView(tableHospedesNoQuarto);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  diariasContrato = PainelAdicionaQuartos.this.diasRestantes;
				if (diariasContrato == 0) {
					dialogoDiarias = new DialogoDiarias();
					dialogoDiarias.setVisible(true);
					//Como DialogoDiarias é modal, daqui para baixo só será processado quando DialogoDiarias for "disposed"
					diariasContrato = dialogoDiarias.getDiarias();
				}
				
					quartoSelecionado.setToOcupado(diariasContrato);
					getContrato().getListaQuartosAlugados().add(quartoSelecionado);
//					getListaDeQuartos().remove(quartoVagoSelecionado);
					quartoSelecionado.adicionaReserva(PainelAdicionaQuartos.this.contrato);
				
					JOptionPane.showMessageDialog(null, "Quarto Adicionado!");
					dispose();
			}	
		});
		
		btnAdicionar.setBounds(340, 151, 117, 25);
		panelPrincipal.add(btnAdicionar);
		
		btnAdicionarHospede = new JButton("Adicionar Hóspede");
		btnAdicionarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ADICIONAR HÓSPEDE SEM CONTRATO NA LISTA
				
			}
		});
		btnAdicionarHospede.setBounds(97, 312, 208, 25);
		panelPrincipal.add(btnAdicionarHospede);
		
		btnRemoverHospede = new JButton("Remover Hóspede");
		btnRemoverHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//REMOVER HOSPEDE DO QUARTO
			}
		});
		btnRemoverHospede.setBounds(450, 312, 208, 25);
		panelPrincipal.add(btnRemoverHospede);

		
		scrollPaneQuartos.setViewportView(tabelaQuartos);
		ListSelectionModel modeloSelecaoLinhaQuarto = tabelaQuartos.getSelectionModel();
		
		modeloSelecaoLinhaQuarto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSelecaoLinhaQuarto.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaQuartos.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					quartoSelecionado = null;
				}else{
					setQuartoSelecionado(indiceSelecionado[0]);
				}
				
			}
		});

		scrollHospedesNoQuarto.setViewportView(tableHospedesNoQuarto);
		ListSelectionModel modeloSelecaoLinhaComHospedes = tabelaQuartos.getSelectionModel();
		
		modeloSelecaoLinhaComHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSelecaoLinhaComHospedes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceHospedesSelecionados = tableHospedesNoQuarto.getSelectedRows(); 
				if (indiceHospedesSelecionados.length <= 0){
					indiceHospedesSelecionados = null;
				}else{
					tableSemHospedesQuartos.clearSelection();
					setHospedeSelecionadoCom(indiceHospedesSelecionados[0]);
				}
				
			}
		});
	
		scrollHospedesSemContrato.setViewportView(tableSemHospedesQuartos);
		ListSelectionModel modeloSelecaoLinhaSemHospedes = tabelaQuartos.getSelectionModel();
		
		modeloSelecaoLinhaSemHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSelecaoLinhaSemHospedes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceHospedesSelecionados = tableHospedesNoQuarto.getSelectedRows(); 
				if (indiceHospedesSelecionados.length <= 0){
					indiceHospedesSelecionados = null;
				}else{
					tableHospedesNoQuarto.clearSelection();
					setHospedeSelecionadoSem(indiceHospedesSelecionados[0]);
				}
				
			}
		});		
		
		
		adicionandoHospedesAsListas(contrato);
		
		atualizaTabelas(quarto, listaQuartosDisponiveis, contrato);
		
	}
	
	private Contrato getContrato() {
		return contrato;
	}

	private void adicionandoHospedesAsListas(Contrato contrato) {
		for (int i = 0; i < contrato.getListaHospedes().size(); i++) {
			if (contrato.getListaHospedes().get(i).getContratoLigado() == null) {
				hospedesNoQuarto.add(contrato.getListaHospedes().get(i));
			}
			else {
				hospedesSemQuarto.add(contrato.getListaHospedes().get(i));
			}
		}
	}

	private void setHospedeSelecionadoSem(int i) {
		hospedeSelecionado = contrato.getListaHospedes().get(i);
	}
	
	private void setHospedeSelecionadoCom(int i) {
		hospedeSelecionado = contrato.getListaHospedes().get(i);
	}
	
	private void setQuartoSelecionado (int i){
		quartoSelecionado = listaQuartosDisponiveis.get(i);
	}
	
	private void atualizaTabelas(Servico quarto,
			List<Quarto> listaQuartosDisponiveis, Contrato contrato) {
		if (quarto == null) {
			tabelaQuartos(listaQuartosDisponiveis);
		}
		else {
			tabelaQuartos(contrato.getListaQuartosAlugados());
		}
		tabelaHospedesSemQuarto();
		tabelaHospedesNoQuarto();
	}
	
	public void tabelaQuartos(List<Quarto> listaQuartos) {
		
		Object[][] designTabela = new Object[listaQuartos.size()][4];
		
		if (listaQuartos.size() > 0){
			for (int i = 0; i < listaQuartos.size(); i++){
				Quarto quartoAtual = listaQuartos.get(i);
					designTabela[i][0] = quartoAtual.getTipo();
					designTabela[i][1] = "R$ " + quartoAtual.getPrecoDiaria();
					designTabela[i][2] = quartoAtual.getNumeroHospedes();
					designTabela[i][3] = quartoAtual.getNumero();
				}
					@SuppressWarnings("serial")
					DefaultTableModel modeloTabela3 = new DefaultTableModel(designTabela, new String[] {
							"Descrição", "Preço da diária", "Num. máximo de hóspedes (sem cama extra)", "Número"
					}) {
			
						@Override
					    public boolean isCellEditable(int row, int column) {
					        return false;
					    }
					};
					tabelaQuartos.setModel(modeloTabela3);
					tabelaQuartos.setRowSelectionAllowed(true);

		}
	}
	
	public void tabelaHospedesSemQuarto() {
		Object [][] designTabela;
		if (hospedesSemQuarto.size() == 0) {
			designTabela = new Object [0][3];
		}
		else {
			LocalDate presente = LocalDate.now();
			designTabela = new Object[hospedesSemQuarto.size()][3];
			for (int i = 0; i < hospedesSemQuarto.size(); i++){
				Hospede hospedeAtual = hospedesSemQuarto.get(i);
				if (hospedeAtual.getContratoLigado() == null) {
					designTabela[i][0] = hospedeAtual.getNome();
					designTabela[i][1] = hospedeAtual.getCpf();
					Calendar nascimento = hospedeAtual.getDataNascimento();
					LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
					Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
					designTabela[i][2] = periodoDeTempo.getYears();
					
					}
				}
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
			// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES SEM CONTRATO
						
				tableSemHospedesQuartos.setModel(modeloTabela);
				tableSemHospedesQuartos.setRowSelectionAllowed(true);
	
	
	}

	public void tabelaHospedesNoQuarto() {
		Object [][] designTabela;
		
		if (quartoSelecionado == null) {
			designTabela = new Object[0][3];
		}
		LocalDate presente = LocalDate.now();
			designTabela = new Object[hospedesNoQuarto.size()][3];
			for (int i = 0; i < hospedesNoQuarto.size(); i++){
				Hospede hospedeAtual = hospedesNoQuarto.get(i);
				if (hospedeAtual.getContratoLigado() != null) {
					designTabela[i][0] = hospedeAtual.getNome();
					designTabela[i][1] = hospedeAtual.getCpf();
					Calendar nascimento = hospedeAtual.getDataNascimento();
					LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
					Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
					designTabela[i][2] = periodoDeTempo.getYears();
				}
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
					
			tableHospedesNoQuarto.setModel(modeloTabela);
			tableHospedesNoQuarto.setRowSelectionAllowed(true);
	}
}
