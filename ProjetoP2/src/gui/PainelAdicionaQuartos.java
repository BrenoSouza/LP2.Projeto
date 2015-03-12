package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import core.Estrategia;
import core.Hospede;
import core.Quarto;
import core.Reserva;
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
	private int diariasContrato;
	private JScrollPane scrollHospedesSemQuarto;
	private JScrollPane scrollHospedesNoQuarto;
	private List<Quarto>listaQuartosDoContrato;
	private JTable tableSemHospedesQuartos;
	private JTable tableHospedesNoQuarto;
	private JButton btnAdicionar;
	private JButton btnAdicionarHospede;
	private Quarto quartoSelecionado;
	private List<Quarto> listaQuartosDisponiveis;
	private Contrato contrato;
	private List<Hospede> hospedesSemQuarto = new ArrayList<Hospede>();
	private List<Hospede> hospedesNoQuarto = new ArrayList<Hospede>();
	public int diasRestantes;
	@SuppressWarnings("unused")//IDE alertando para o que não está errado, novamente.
	private Hospede hospedeSelecionado;
	private boolean operacaoFinalizada;
	
	public PainelAdicionaQuartos(Servico quarto, ColecaoDeHospedes listaDeHospedes, List<Quarto> listaQuartosDisponiveis, Contrato contrato, JDesktopPane painelPrincipal, int diasRestantes) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override	
			public void internalFrameActivated(InternalFrameEvent e) {
	
			}
		});
		
		setClosable(true);
		setBounds(100, 100, 845, 447);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/quartoIcon.png")));
		setTitle("Adicionar Quartos / Editar Quartos");
		getContentPane().setLayout(null);
		
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		this.contrato = contrato;
		this.diasRestantes = diasRestantes;
		listaQuartosDoContrato = new ArrayList<Quarto>();
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 835, 415);
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
		
		scrollHospedesSemQuarto = new JScrollPane();
		tabHospedes.addTab("Hospedes Sem Quarto", null, scrollHospedesSemQuarto, null);
		
		tableSemHospedesQuartos = new JTable();
		scrollHospedesSemQuarto.setColumnHeaderView(tableSemHospedesQuartos);
		
		scrollHospedesNoQuarto = new JScrollPane();
		tabHospedes.addTab("Hospedes No Quarto", null, scrollHospedesNoQuarto, null);
		
		tableHospedesNoQuarto = new JTable();
		scrollHospedesNoQuarto.setColumnHeaderView(tableHospedesNoQuarto);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  diariasContrato = PainelAdicionaQuartos.this.diasRestantes;
			  Calendar data = Calendar.getInstance();
			  Calendar dataFinal = Calendar.getInstance();
			  dataFinal.add(Calendar.DAY_OF_YEAR, diariasContrato);
			  
			  if (quartoSelecionado.isLivreParaReserva(new Reserva(data, dataFinal).getIntervalo())){
			    listaQuartosDoContrato.add(quartoSelecionado);
			    PainelAdicionaQuartos.this.listaQuartosDisponiveis.remove(quartoSelecionado);
			    tabelaQuartos(PainelAdicionaQuartos.this.listaQuartosDisponiveis);
          JOptionPane.showMessageDialog(null, "Quarto adicionado!");
        }else{
          JOptionPane.showMessageDialog(null, "O quarto disponível não está disponível durante o período do contrato.");
        }
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
		btnAdicionarHospede.setBounds(288, 319, 208, 25);
		panelPrincipal.add(btnAdicionarHospede);

		
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
	
		scrollHospedesSemQuarto.setViewportView(tableSemHospedesQuartos);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    
		    try{
         // Contrato contrato;
          if (PainelAdicionaQuartos.this.contrato.getStatus().equals("RESERVA")){//Se for um contrato do tipo reserva...
            List<Estrategia> estrategiasAtuais = PainelAdicionaQuartos.this.contrato.getEstrategiasDoContrato();
            if (estrategiasAtuais.size() > 0){//Se não houver nenhuma estratégia no período referente...
              for (Quarto quarto: listaQuartosDoContrato){
                PainelAdicionaQuartos.this.listaQuartosDisponiveis.add(quarto);
                Reserva reserva = new Reserva(PainelAdicionaQuartos.this.contrato);
                quarto.adicionaReserva(reserva);
                PainelAdicionaQuartos.this.contrato.adicionaQuarto(quarto);
                operacaoFinalizada = true;
              }
            }else{//Se houver estratégia no período referente...
              for (Quarto quarto: listaQuartosDoContrato){
                PainelAdicionaQuartos.this.listaQuartosDisponiveis.add(quarto);
                Reserva reserva = new Reserva(PainelAdicionaQuartos.this.contrato);
                quarto.adicionaReserva(reserva);
                PainelAdicionaQuartos.this.contrato.adicionaQuarto(quarto);
                operacaoFinalizada = true;
              }
            }
          }else{//Se for um contrato do check-in imediato...
            //List<Estrategia> estrategiasAtuais = PainelAdicionaQuartos.this.contrato.getListaEstrategias();
            
            //if (estrategiasAtuais.size() > 0){//Se houver estratégia no período referente...
              //for (Estrategia estrategiaAtual: estrategiasAtuais){
                //JOptionPane.showMessageDialog(null, "O contrato está em um período referente a seguinte estratégia:" + Main.quebraDeLinha + estrategiaAtual.toString());
                //PainelAdicionaQuartos.this..adicionaEstrategiaNoContrato(estrategiaAtual);
              //q}
             // for (Quarto quarto: listaQuartosDoContrato){
             // PainelAdicionaQuartos.this.listaQuartosDisponiveis.add(quarto);
             // Reserva reserva = new Reserva(PainelAdicionaQuartos.this.contrato);
             // quarto.adicionaReserva(reserva);
             // }
          //  else{//Se não houverem estratégias no período referente...
              for (Quarto quarto: listaQuartosDoContrato){
                Reserva reserva = new Reserva(PainelAdicionaQuartos.this.contrato);
                quarto.adicionaReserva(reserva);
                PainelAdicionaQuartos.this.listaQuartosDisponiveis.add(quarto);
                System.out.println("adicionou!");
                operacaoFinalizada = true;
              }
          }
		    }
		    
		    

		    catch (core.ParametrosInvalidosException e1){
          JOptionPane.showMessageDialog(null, e1.getMessage());
        }
		    JOptionPane.showMessageDialog(null, "Finalizado!");
        dispose();
		  }
		});
		
		
		btnFinalizar.setBounds(656, 365, 117, 25);
		panelPrincipal.add(btnFinalizar);
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
	  Collections.sort(listaQuartos);
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
	
	 @Override
	  public void dispose(){
	    if (operacaoFinalizada == false){//Retira os contratos daquela tabela de quartos no contrato, mais como um safeguard para uma possível modificação de código.
	      for (int i = listaQuartosDoContrato.size() - 1; i > -1; i--){
	        Quarto quarto = listaQuartosDoContrato.get(i);
	        if (!(listaQuartosDisponiveis.contains(quarto))){
	          listaQuartosDisponiveis.add(quarto);
	        }
	      }
	    }
	    super.dispose();
	  }
	 
}
