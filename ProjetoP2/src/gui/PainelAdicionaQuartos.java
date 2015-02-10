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

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;

public class PainelAdicionaQuartos extends JInternalFrame {

	private static final long serialVersionUID = -1825431196943966386L;
	private ColecaoDeHospedes listaDeHospedes;
	private List<Quarto> listaDeQuartos;
	private List<Hospede> listaHospedesSemContrato = new ArrayList<Hospede>();
	private Quarto quartoVagoSelecionado;
	private JPanel panelQuartos;
	private JScrollPane scrollPane_2;
	private JButton btnAdicionarNoContratoQuarto;
	private JTable tabelaQuartosLivres;
	private JLabel lblQuartosLivres;
	private DialogoDiarias dialogoDiarias;
	private int diariasContrato;
	private Contrato contrato;
	private int[] indiceHospedesSelecionados;
	private JDesktopPane painelPrincipal;
	private JTable tableHospedesSemContrato;
	private JTabbedPane tabbedPane_1;
	private JScrollPane scrollPaneNoContrato;
	private JButton button;
	

	/**
	 * Create the frame.
	 */
	public PainelAdicionaQuartos(ColecaoDeHospedes listaDeHospedes, List<Quarto> listaDeQuartos, Contrato contrato, JDesktopPane painelPrincipal) {
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
					getListaDeQuartos().remove(quartoVagoSelecionado);
				
					JOptionPane.showMessageDialog(null, "Quarto Adicionado!");
						
				escreveTabelas();
				disposeOnClosed();
			}
		});
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnAdicionarNoContratoQuarto.setEnabled(false);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		button = new JButton("Adicionar Hospede no Quarto");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (quartoVagoSelecionado.getNumeroHospedes() > quartoVagoSelecionado.getListaHospedes().size()); {
					adicionaHospedesSelecionadosNoContrato(indiceHospedesSelecionados);
				}
			}
		});
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addGroup(gl_panelQuartos.createParallelGroup(Alignment.LEADING)
								.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addComponent(lblQuartosLivres, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(388))
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addGap(117)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
							.addComponent(btnAdicionarNoContratoQuarto, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
							.addGap(134))))
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
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(btnAdicionarNoContratoQuarto))
					.addContainerGap())
		);
		
		JScrollPane scrollPaneSemContrato = new JScrollPane();
		tabbedPane_1.addTab("Hóspedes Sem Quarto", null, scrollPaneSemContrato, null);
		
		tableHospedesSemContrato = new JTable();
		scrollPaneSemContrato.setColumnHeaderView(tableHospedesSemContrato);
		
		tableHospedesSemContrato.setRowSelectionAllowed(true);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tableHospedesSemContrato.getSelectionModel();
		
		scrollPaneSemContrato.setViewportView(tableHospedesSemContrato);
		
		scrollPaneNoContrato = new JScrollPane();
		tabbedPane_1.addTab("Hóspedes no Quarto", null, scrollPaneNoContrato, null);
		
		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				indiceHospedesSelecionados = tableHospedesSemContrato.getSelectedRows(); 
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
		ListSelectionModel modeloSelecaoLinha4 = tabelaQuartosLivres.getSelectionModel();
		
		modeloSelecaoLinha4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha4.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaQuartosLivres.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					quartoVagoSelecionado = null;
				}else{
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
		quartoVagoSelecionado = listaDeQuartos.get(i);
	}
	
	private void atualizaBotoes(){
		btnAdicionarNoContratoQuarto.setEnabled(!(quartoVagoSelecionado == null));
	}
	
	private void escreveTabelas(){
		Object[][] designTabela;
		escreveTabelaHospedesSemContrato();
								
			// PREENCHENDO TABELA DOS QUARTOS VAGOS NO HOTEL
				designTabela = new Object[listaDeQuartos.size()][4];
				if (listaDeQuartos.size() > 0){
				for (int i = 0; i < listaDeQuartos.size(); i++){
					Quarto quartoAtual = listaDeQuartos.get(i);
					//Para preencher a primeira coluna da linha: Descrição do quarto
					designTabela[i][0] = quartoAtual.getTipo();
					//Para preencher a segunda coluna da linha: O preço da diária
					designTabela[i][1] = "R$ " + quartoAtual.getPrecoDiaria();
					//Para preencher a terceira coluna da linha: O número de pessoas que o quarto acomoda
					designTabela[i][2] = quartoAtual.getNumeroHospedes();
					//Para preencher a quarta coluna da linha: O número do quarto
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
						
				tableHospedesSemContrato.setModel(modeloTabela);
				tableHospedesSemContrato.setRowSelectionAllowed(true);
	}
	
	public List<Quarto> getListaDeQuartos() {
		return listaDeQuartos;
	}
}