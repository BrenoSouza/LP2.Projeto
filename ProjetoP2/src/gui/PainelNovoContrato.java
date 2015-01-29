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

public class PainelNovoContrato extends JInternalFrame {
	public ColecaoDeHospedes getListaDeHospedes() {
		return listaDeHospedes;
	}
	private JScrollPane scrollPane_1;
	private JLabel lblHospedesSemContrato;
	private JLabel lblHospedesNoContrato;
	private ColecaoDeHospedes listaDeHospedes;
	private List<Hospede> listaHospedesDoContrato, listaHospedesSemContrato;
	private List<Quarto> listaQuartosDisponiveis, listaQuartosDoContrato;
	private Quarto quartoVagoSelecionado, quartoContratoSelecionado;
	private Hospede hospedeSelecionado;
	private Hospede hospedeSelecionado2;
	private JScrollPane scrollPane;
	private JButton btnCriarNovo;
	private JButton btnAdicionarNoContrato;
	private JTable tabelaHospedesSemContrato;
	private JTable tabelaHospedesNoContrato;
	private JButton btnRemoverDoContrato;
	private JPanel panelHospedes;
	private JPanel panelQuartos;
	private JScrollPane scrollPane_2;
	private JLabel lblQuartosNoContrato;
	private JScrollPane scrollPane_3;
	private JButton btnAdicionarNoContratoQuarto;
	private JButton btnRemoverDoContratoQuarto;
	private JTable tabelaQuartosNoContrato;
	private JTable tabelaQuartosLivres;
	private JLabel lblQuartosLivres;
	private DialogoDiarias dialogoDiarias;
	private int diariasContrato = 0;
	private JPanel panelFinalizar;
	private JLabel lblHospedes;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JLabel lblQuartos;
	private JButton btnFinalizar;
	private JTable tabelaHospedesFinal;
	private JTable tabelaQuartosFinal;
	private List<Contrato> listaContratos;
	private Hospede hospedePrincipal;
	private JDesktopPane painelPrincipal;

	public PainelNovoContrato(ColecaoDeHospedes listaDeHospedes, List<Quarto> listaQuartosDisponiveis, List<Contrato> listaContratos, JDesktopPane painelPrincipal) {
		setFrameIcon(new ImageIcon(PainelNovoContrato.class.getResource("/resources/contrato_icon.png")));
		setTitle("Novo contrato");
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				escreveTabelas();
			}
		});
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 970, 400);
		this.painelPrincipal = painelPrincipal;
		this.listaDeHospedes = listaDeHospedes;
		listaHospedesDoContrato = new ArrayList<Hospede>();
		listaQuartosDoContrato = new ArrayList<Quarto>();
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		this.listaContratos = listaContratos;
		listaHospedesSemContrato = new ArrayList<Hospede>();
		String[] nomesHospedes = new String[listaHospedesSemContrato.size() + 1]; // Criando a lista com os nomes dos hóspedes para serem escolhidos.
		nomesHospedes[0] = "-- SELECIONE UM HÓSPEDE --"; // Criando uma mensagem para ser a de primeiro índice.
		for (int i = 0; i < listaHospedesSemContrato.size(); i++){
			nomesHospedes[i + 1] = (listaHospedesSemContrato.get(i)).getNome();
		}
		
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
		
		panelHospedes = new JPanel();
		tabbedPane.addTab("Hóspedes", null, panelHospedes, null);
		
		scrollPane_1 = new JScrollPane();
		
		scrollPane = new JScrollPane();
		
		btnAdicionarNoContrato = new JButton("Adicionar no contrato");
		btnAdicionarNoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaHospedesDoContrato.add(hospedeSelecionado);
				listaHospedesSemContrato.remove(hospedeSelecionado);
				if (hospedePrincipal == null){
					int escolha = JOptionPane.showOptionDialog(null, "Você deseja marcar esse hóspede como o hóspede principal do contrato? \nO hóspede principal do contrato é o que será procurado para questões financeiras.", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {
						hospedePrincipal = hospedeSelecionado;
					}
				}
				escreveTabelas();
			}
		});
		btnAdicionarNoContrato.setEnabled(false);
		
		btnRemoverDoContrato = new JButton("Remover do contrato");
		btnRemoverDoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaHospedesSemContrato.add(hospedeSelecionado2);
				listaHospedesDoContrato.remove(hospedeSelecionado2);
				if (hospedePrincipal == hospedeSelecionado2){
					hospedePrincipal = null;
				}
				escreveTabelas();
			}
		});
		btnRemoverDoContrato.setEnabled(false);
		
		btnCriarNovo = new JButton("Cadastrar novo hóspede");
		btnCriarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					PainelCadastroClientes painelCadastro = new PainelCadastroClientes(getListaDeHospedes());
					getPainelPrincipal().add(painelCadastro);
					painelCadastro.show();
					
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		
		lblHospedesSemContrato = new JLabel("Hóspedes sem contrato:");
		lblHospedesSemContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblHospedesNoContrato = new JLabel("Hóspedes no contrato:");
		lblHospedesNoContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panelHospedes = new GroupLayout(panelHospedes);
		gl_panelHospedes.setHorizontalGroup(
			gl_panelHospedes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHospedes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHospedes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHospedes.createSequentialGroup()
							.addComponent(btnRemoverDoContrato, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
							.addGap(170)
							.addComponent(btnCriarNovo, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addGap(163)
							.addComponent(btnAdicionarNoContrato, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
						.addComponent(lblHospedesSemContrato, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHospedesNoContrato, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelHospedes.setVerticalGroup(
			gl_panelHospedes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHospedes.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblHospedesSemContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHospedesNoContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelHospedes.createParallelGroup(Alignment.BASELINE)
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
					tabelaHospedesSemContrato.clearSelection();
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
					tabelaHospedesNoContrato.clearSelection();
				}atualizaBotoes();
				
			}
		});
		
		scrollPane.setViewportView(tabelaHospedesSemContrato);
		panelHospedes.setLayout(gl_panelHospedes);
		
		panelQuartos = new JPanel();
		tabbedPane.addTab("Quartos", null, panelQuartos, null);
		
		lblQuartosLivres = new JLabel("Quartos livres:");
		lblQuartosLivres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane_2 = new JScrollPane();
		
		lblQuartosNoContrato = new JLabel("Quartos no contrato:");
		lblQuartosNoContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane_3 = new JScrollPane();
		
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
				listaQuartosDoContrato.add(quartoVagoSelecionado);
				PainelNovoContrato.this.listaQuartosDisponiveis.remove(quartoVagoSelecionado);
				escreveTabelas();
			}
		});
		btnAdicionarNoContratoQuarto.setEnabled(false);
		
		btnRemoverDoContratoQuarto = new JButton("Remover do contrato");
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnRemoverDoContratoQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PainelNovoContrato.this.listaQuartosDisponiveis.add(quartoContratoSelecionado);
				listaQuartosDoContrato.remove(quartoContratoSelecionado);
				quartoContratoSelecionado.setToLivre();
				if (listaQuartosDoContrato.size() == 0){ //Reiniciando o valor das diárias se for esvaziada a lista de quartos do contrato
					diariasContrato = 0;
				}
				escreveTabelas();
			}
		});
		GroupLayout gl_panelQuartos = new GroupLayout(panelQuartos);
		gl_panelQuartos.setHorizontalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelQuartos.createSequentialGroup()
							.addComponent(btnRemoverDoContratoQuarto, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
							.addGap(468)
							.addComponent(btnAdicionarNoContratoQuarto, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
						.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
						.addComponent(lblQuartosLivres, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuartosNoContrato, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelQuartos.setVerticalGroup(
			gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
					.addGap(18)
					.addComponent(lblQuartosLivres, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuartosNoContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoverDoContratoQuarto)
						.addComponent(btnAdicionarNoContratoQuarto))
					.addContainerGap())
		);
		
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
					tabelaQuartosNoContrato.clearSelection();
				}atualizaBotoes();
				
			}
		});
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		
		tabelaQuartosNoContrato = new JTable();
		scrollPane_3.setViewportView(tabelaQuartosNoContrato);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha3 = tabelaQuartosNoContrato.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha3.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tabelaQuartosNoContrato.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							quartoContratoSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setQuartoContratoSelecionado(indiceSelecionado[0]);
							tabelaQuartosLivres.clearSelection();
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		panelQuartos.setLayout(gl_panelQuartos);
		
		panelFinalizar = new JPanel();
		tabbedPane.addTab("Finalizar", null, panelFinalizar, null);
		
		lblHospedes = new JLabel("Hóspedes:");
		lblHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane_4 = new JScrollPane();
		
		scrollPane_5 = new JScrollPane();
		
		lblQuartos = new JLabel("Quartos:");
		lblQuartos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Contrato contrato = new Contrato(listaQuartosDoContrato, listaHospedesDoContrato, diariasContrato);
					for (Hospede hospede: listaHospedesDoContrato){
						hospede.setContratoLigado(contrato);
					}
					if (hospedePrincipal != null){
						contrato.setHospedePrincipal(hospedePrincipal);
					}
					getListaContratos().add(contrato);
					JOptionPane.showMessageDialog(null, "Contrato criado com sucesso!");
					dispose();
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		GroupLayout gl_panelFinalizar = new GroupLayout(panelFinalizar);
		gl_panelFinalizar.setHorizontalGroup(
			gl_panelFinalizar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFinalizar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFinalizar.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 929, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 929, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFinalizar.createSequentialGroup()
							.addGap(695)
							.addComponent(btnFinalizar, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelFinalizar.setVerticalGroup(
			gl_panelFinalizar.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFinalizar.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnFinalizar)
					.addContainerGap())
		);
		
		tabelaQuartosFinal = new JTable();
		scrollPane_5.setViewportView(tabelaQuartosFinal);
		tabelaQuartosFinal.setRowSelectionAllowed(true);
		
		tabelaHospedesFinal = new JTable();
		tabelaHospedesFinal.setRowSelectionAllowed(true);
		scrollPane_4.setViewportView(tabelaHospedesFinal);
		panelFinalizar.setLayout(gl_panelFinalizar);
		getContentPane().setLayout(groupLayout);
		escreveTabelas();

	}
	public List<Quarto> getListaQuartosDisponiveis() {
		return listaQuartosDisponiveis;
	}
	public JDesktopPane getPainelPrincipal() {
		return painelPrincipal;
	}
	public List<Contrato> getListaContratos() {
		return listaContratos;
	}
	private void setHospedeSelecionado(int i){
		hospedeSelecionado = listaHospedesSemContrato.get(i);
	}
	private void setHospedeSelecionado2 (int i){
		hospedeSelecionado2 = listaHospedesDoContrato.get(i);
	}
	private void setQuartoVagoSelecionado (int i){
		quartoVagoSelecionado = listaQuartosDisponiveis.get(i);
	}
	private void setQuartoContratoSelecionado (int i){
		quartoContratoSelecionado = listaQuartosDoContrato.get(i);
	}
	private void atualizaBotoes(){
		btnAdicionarNoContrato.setEnabled(!(hospedeSelecionado == null));
		btnRemoverDoContrato.setEnabled(!(hospedeSelecionado2 == null));
		btnAdicionarNoContratoQuarto.setEnabled(!(quartoVagoSelecionado == null));
		btnRemoverDoContratoQuarto.setEnabled(!(quartoContratoSelecionado == null));
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
					LocalDate diaNascimento = new LocalDate(nascimento.get(nascimento.YEAR), nascimento.get(nascimento.MONTH), nascimento.get(nascimento.DAY_OF_MONTH)-1);
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
					LocalDate diaNascimento = new LocalDate(nascimento.get(nascimento.YEAR), nascimento.get(nascimento.MONTH), nascimento.get(nascimento.DAY_OF_YEAR));
					Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
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
				tabelaHospedesFinal.setModel(modeloTabela2);
				
			// FIM DO PREENCHIMENTO DA TABELA DOS HÓSPEDES NO CONTRATO
				
			// PREENCHENDO TABELA DOS QUARTOS VAGOS NO HOTEL
				Collections.sort(listaQuartosDisponiveis);
				designTabela = new Object[listaQuartosDisponiveis.size()][4];
				if (listaQuartosDisponiveis.size() > 0){
				for (int i = 0; i < listaQuartosDisponiveis.size(); i++){
					Quarto quartoAtual = listaQuartosDisponiveis.get(i);
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
					
			// PREENCHENDO TABELA DOS QUARTOS DO CONTRATO
				Collections.sort(listaQuartosDoContrato);
				designTabela = new Object[listaQuartosDoContrato.size()][6];	
				for (int j = 0; j < listaQuartosDoContrato.size(); j++){
					Quarto quartoAtualContrato = listaQuartosDoContrato.get(j);
					//Para preencher a primeira coluna da linha: Descrição do quarto
					designTabela[j][0] = quartoAtualContrato.getTipo();
					//Para preencher a segunda coluna da linha: O preço da diária
					designTabela[j][1] = "R$ " + quartoAtualContrato.getPrecoDiaria();
					//Para preencher a terceira coluna da linha: O número de diárias setadas
					designTabela[j][2] = quartoAtualContrato.getDiarias();
					//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
					designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
					//Para preencher a quinta coluna da linha: O número do quarto
					designTabela[j][4] = quartoAtualContrato.getNumero();
					//Para preencher a sexta coluna da linha: O preço a ser pago
					designTabela[j][5] = "R$ " + quartoAtualContrato.calculaPrecoTotal();
				}
					//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
					@SuppressWarnings("serial")
					DefaultTableModel modeloTabela4 = new DefaultTableModel(designTabela, new String[] {
							"Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
					}) {
			
						@Override
					    public boolean isCellEditable(int row, int column) {
					        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
					        return false;
					    }
					};
					tabelaQuartosNoContrato.setModel(modeloTabela4);
					tabelaQuartosNoContrato.getColumnModel().getColumn(3).setPreferredWidth(180); //Aumentando o tamanho da quarta coluna, pq a String de título dela é grande
					tabelaQuartosNoContrato.getColumnModel().getColumn(0).setPreferredWidth(130); // Idem ao comment acima
					tabelaQuartosNoContrato.setRowSelectionAllowed(true);
					tabelaQuartosFinal.setModel(modeloTabela4);
					tabelaQuartosFinal.getColumnModel().getColumn(3).setPreferredWidth(180);
					tabelaQuartosFinal.getColumnModel().getColumn(0).setPreferredWidth(130);
				
				
		
		
	}
}
	}