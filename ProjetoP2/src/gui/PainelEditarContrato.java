package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import classes.AluguelCarro;
import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Reserva;
import classes.Servico;
import colecoes.ColecaoDeHospedes;
import colecoes.ColecaoDeQuartos;

public class PainelEditarContrato extends JInternalFrame {
	private JLabel lblQuartos;
	private JLabel lblHospedes;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private Contrato contrato;
	private JTable tabelaHospedes;
	private List<Quarto> listaQuartos;
	private List<Hospede> listaHospedes;
	private List<Servico> listaServicos;
	private JScrollPane scrollPane_2;
	private JLabel lblServicos;
	private JTable tabelaServicos;
	private JTable tabelaQuartos;
	private JButton btnRemoverDinamico;
	private JDesktopPane painelPrincipal;
	private Object objetoDinamico = null;
	private JButton btnAdicionarDinamico;
	private JButton btnEditarDinamico;
	private Hospede hospedePrincipal;
	private double precoTotal;
	private JLabel lblPrecoTotal;
	private JButton btnFinalizar;
	private ColecaoDeQuartos listaQuartosHotelColecao;
	private List<Quarto> listaQuartosHotel;
	private final static int SELECAO_HOSPEDE = 1;
	private final static int SELECAO_SERVICO = 2;
	private final static int SELECAO_QUARTO = 3;
	private int selecaoRelativa = 0;
	private ColecaoDeHospedes listaHospedesHotel;
	
	public PainelEditarContrato(Contrato contrato, JDesktopPane painelPrincipal, ColecaoDeQuartos listaQuartosHotelColecao, ColecaoDeHospedes listaHospedesHotel) {
		setFrameIcon(new ImageIcon(PainelEditarContrato.class.getResource("/resources/contrato_icon.png")));
		setTitle("Editar contrato");
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabelas();
			}
		});
		setBounds(0, 0, 970, 530);
		this.contrato = contrato;
		this.painelPrincipal = painelPrincipal;
		hospedePrincipal = contrato.getHospedePrincipal();
		listaQuartos = contrato.getListaQuartosAlugados();
		listaHospedes = contrato.getListaHospedes();
		listaServicos = contrato.getListaServicos();
		this.listaHospedesHotel = listaHospedesHotel;
		this.listaQuartosHotel = listaQuartosHotelColecao.getListaQuartos();
		lblHospedes = new JLabel("Hóspedes:");
		lblHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {/*Evento mouse clicked, acontece quando o elemento é clicado com o mouse, obviamente
				* Eu prefiro colocar esse evento no scrollPane, mas eu suponho que colocar no próprio JTable não mude muita coisa
				* Antes disso, os botões só ativavam se você selecionasse uma opção da tabela, mas e se a tabela estivesse vazia?
				* Agora, se você clicar em qualquer canto dentro da tabela, o botão Adicionar, que não precisa de seleção, será ativo
				* Também, desativa as seleções das outras tabelas, com o clearSelection().
				* Sem isso, por exemplo, se alguém selecionasse um quarto, mas depois clicasse na tabela serviços, o botão se tornaria "Adiciona serviço",
				* mas os outros botões ainda seriam "Editar quarto" e "Remover quarto", o que não faz sentido. Melhor deselecionar o que estava selecionado.*/
				tabelaQuartos.clearSelection();
				tabelaServicos.clearSelection();
				setSelecaoRelativa(SELECAO_HOSPEDE);
			}
		});
		lblQuartos = new JLabel("Quartos:");
		lblQuartos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { /*Evento mouse clicked, acontece quando o elemento é clicado com o mouse, obviamente
				* Eu prefiro colocar esse evento no scrollPane, mas eu suponho que colocar no próprio JTable não mude muita coisa
				* Antes disso, os botões só ativavam se você selecionasse uma opção da tabela, mas e se a tabela estivesse vazia?
				* Agora, se você clicar em qualquer canto dentro da tabela, o botão Adicionar, que não precisa de seleção, será ativo
				* Também, desativa as seleções das outras tabelas, com o clearSelection().
				* Sem isso, por exemplo, se alguém selecionasse um quarto, mas depois clicasse na tabela serviços, o botão se tornaria "Adiciona serviço",
				* mas os outros botões ainda seriam "Editar quarto" e "Remover quarto", o que não faz sentido. Melhor deselecionar o que estava selecionado.*/
				tabelaHospedes.clearSelection();
				tabelaServicos.clearSelection();
				setSelecaoRelativa(SELECAO_QUARTO);
			}
		});
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {/*Evento mouse clicked, acontece quando o elemento é clicado com o mouse, obviamente
				* Eu prefiro colocar esse evento no scrollPane, mas eu suponho que colocar no próprio JTable não mude muita coisa
				* Antes disso, os botões só ativavam se você selecionasse uma opção da tabela, mas e se a tabela estivesse vazia?
				* Agora, se você clicar em qualquer canto dentro da tabela, o botão Adicionar, que não precisa de seleção, será ativo
				* Também, desativa as seleções das outras tabelas, com o clearSelection().
				* Sem isso, por exemplo, se alguém selecionasse um quarto, mas depois clicasse na tabela serviços, o botão se tornaria "Adiciona serviço",
				* mas os outros botões ainda seriam "Editar quarto" e "Remover quarto", o que não faz sentido. Melhor deselecionar o que estava selecionado.*/
				tabelaHospedes.clearSelection();
				tabelaQuartos.clearSelection();
				setSelecaoRelativa(SELECAO_SERVICO);
			}
		});
		
		lblServicos = new JLabel("Serviços:");
		lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnRemoverDinamico = new JButton("Remover");
		btnRemoverDinamico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (objetoDinamico instanceof Hospede){
					int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse hóspede do contrato?" + (objetoDinamico == hospedePrincipal ? "\nEsse hóspede está configurado como o hóspede principal do contrato selecionado." : ""), /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION){
						listaHospedes.remove(objetoDinamico);
						if (objetoDinamico == hospedePrincipal){
							getContrato().setHospedePrincipal(null);
						}
					}
				}else if (objetoDinamico instanceof Quarto){
					int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse quarto do contrato?", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION){
						listaQuartos.remove(objetoDinamico);
						((Quarto) objetoDinamico).setToLivre();
						((Quarto) objetoDinamico).retiraReserva(PainelEditarContrato.this.contrato);
					}
				}else if (objetoDinamico instanceof Servico){
					int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse serviço do contrato?", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION){
						listaServicos.remove(objetoDinamico);
					}
				}
				escreveTabelas();
			}
		});
		
		btnAdicionarDinamico = new JButton("Adicionar");
		btnAdicionarDinamico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = btnAdicionarDinamico.getText();
				if (texto.equals("Adicionar hóspede")){
					PainelEditarContratoAdicionarHospede painelAddHospede = new PainelEditarContratoAdicionarHospede(PainelEditarContrato.this.listaHospedesHotel, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal);
					PainelEditarContrato.this.painelPrincipal.add(painelAddHospede);
					painelAddHospede.show();
				}else{
					PainelAdicionaServico painelAdd = new PainelAdicionaServico(false, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this.listaQuartosHotelColecao, PainelEditarContrato.this.listaHospedesHotel);
					PainelEditarContrato.this.painelPrincipal.add(painelAdd);
					painelAdd.show();
				}
				escreveTabelas();
			}
		});
		
		btnEditarDinamico = new JButton("Editar");
		btnEditarDinamico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String texto = btnEditarDinamico.getText();
				if (texto.equals("Editar hóspede")){
					PainelEditarCliente painelEditarHospede = new PainelEditarCliente((Hospede) objetoDinamico, PainelEditarContrato.this.painelPrincipal);
					PainelEditarContrato.this.painelPrincipal.add(painelEditarHospede);
					painelEditarHospede.show();
				}else{
					PainelAdicionaServico painelAdd = new PainelAdicionaServico(true, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this.listaQuartosHotelColecao, PainelEditarContrato.this.listaHospedesHotel);
					PainelEditarContrato.this.painelPrincipal.add(painelAdd);
					painelAdd.show();
				}
				escreveTabelas();
				}catch (Exception e5){
					JOptionPane.showMessageDialog(null, e5.getMessage());
				}
			}
		});
		atualizaBotoes();
		
		lblPrecoTotal = new JLabel("Preço a ser pago:");
		lblPrecoTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnFinalizar = new JButton("Fazer check-out");
		if (contrato.getStatus().equals("RESERVA")){
			btnFinalizar.setText("Fazer check-in");
		}
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnFinalizar.getText().equals("Fazer check-out")){
					int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja encerrar esse contrato?\nApós a operação, não será mais possível editar o contrato.", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION){
						getContrato().fechaContrato();
						for (Quarto quarto: listaQuartos){
							quarto.setToLivre();
							quarto.retiraReserva(PainelEditarContrato.this.contrato);
						}
						Collections.sort(PainelEditarContrato.this.listaQuartosHotel);
						dispose();
					}
				}else{
					DateTime presente = new DateTime().withTimeAtStartOfDay();
					DateTime checkIn = new DateTime(PainelEditarContrato.this.contrato.getDataCheckIn()).withTimeAtStartOfDay();
					if (checkIn.compareTo(presente) == 1){
						int escolha = JOptionPane.showOptionDialog(null, "A data presente vem antes da data de CheckIn do contrato.\nAo fazer isso, a data de check-out será recalculada. Deseja realmente fazer a operação de check-in?", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
						if (escolha == JOptionPane.YES_OPTION){
							PainelEditarContrato.this.contrato.fazCheckIn();
							btnFinalizar.setText("Fazer check-out");
							for (Quarto quarto: listaQuartos){
								if (quarto.isLivreParaReserva(new Reserva(PainelEditarContrato.this.contrato).getIntervalo())){
									quarto.setToOcupado(PainelEditarContrato.this.contrato.getNumeroDiarias());
									quarto.adicionaReserva(PainelEditarContrato.this.contrato);
								}else{
									JOptionPane.showMessageDialog(null, "O quarto número " + quarto.getNumero() + " não está disponível no período.");
								}
							}dispose();
						}
					}else{
						PainelEditarContrato.this.contrato.fazCheckIn();
						btnFinalizar.setText("Fazer check-out");
						for (Quarto quarto: listaQuartos){
							if (quarto.isLivreParaReserva(new Reserva(PainelEditarContrato.this.contrato).getIntervalo())){
								quarto.setToOcupado(PainelEditarContrato.this.contrato.getNumeroDiarias());
								quarto.adicionaReserva(PainelEditarContrato.this.contrato);
							}else{
								JOptionPane.showMessageDialog(null, "O quarto número " + quarto.getNumero() + " não está disponível no período.");
							}
						}dispose();
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE))
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblServicos)
							.addContainerGap(888, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPrecoTotal)
							.addContainerGap(808, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRemoverDinamico, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
									.addGap(104)
									.addComponent(btnEditarDinamico, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
									.addGap(133)
									.addComponent(btnAdicionarDinamico, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
									.addGap(145)
									.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 929, GroupLayout.PREFERRED_SIZE))
							.addGap(15))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblServicos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPrecoTotal)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoverDinamico)
						.addComponent(btnFinalizar)
						.addComponent(btnAdicionarDinamico)
						.addComponent(btnEditarDinamico))
					.addContainerGap())
		);
		
		tabelaServicos = new JTable();
		scrollPane_2.setViewportView(tabelaServicos);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tabelaServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tabelaServicos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							objetoDinamico = null;
						}else{
							tabelaHospedes.clearSelection();
							tabelaQuartos.clearSelection();
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setObjetoDinamico(indiceSelecionado[0], listaServicos);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		
		tabelaQuartos = new JTable();
		scrollPane_1.setViewportView(tabelaQuartos);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha2 = tabelaQuartos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha2.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tabelaQuartos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							objetoDinamico = null;
						}else{
							tabelaHospedes.clearSelection();
							tabelaServicos.clearSelection();
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setObjetoDinamico(indiceSelecionado[0], listaQuartos);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		
		tabelaHospedes = new JTable();
		scrollPane.setViewportView(tabelaHospedes);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha3 = tabelaHospedes.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha3.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tabelaHospedes.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							objetoDinamico = null;
						}else{
							tabelaServicos.clearSelection();
							tabelaQuartos.clearSelection();
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setObjetoDinamico(indiceSelecionado[0], listaHospedes);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		getContentPane().setLayout(groupLayout);
		escreveTabelas();

	}

	public void escreveTabelas(){
		// INÍCIO DE CONSTRUÇÃO DA TABELA
				// designTabela = o conteúdo da tabela em si, preenchida através de um loop for
						Object[][] designTabela = new Object[listaHospedes.size()][3];
						LocalDate presente = LocalDate.now();
						for (int i = 0; i < listaHospedes.size(); i++){
							Hospede hospedeAtual = listaHospedes.get(i);
							//Para preencher a primeira coluna da linha: Nome do hóspede
							designTabela[i][0] = hospedeAtual.getNome();
							//Para preencher a segunda coluna da linha: CPF do hóspede
							designTabela[i][1] = hospedeAtual.getCpf();
							//Para preencher a terceira coluna da linha: Idade do hóspede
							Calendar nascimento = hospedeAtual.getDataNascimento();
							LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
							Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
							designTabela[i][2] = periodoDeTempo.getYears();
							
					// FIM DE CONSTRUÇÃO DE TABELA.
					
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

			
				tabelaHospedes.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
				tabelaHospedes.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
				
				//INICIO DA CONSTRU��O DE TABELA
				Object [][] tabelaServicosDesign = new Object [listaServicos.size()][4];
				for (int i = 0; i < contrato.getListaServicos().size(); i++){
					Servico servicoAtual = contrato.getListaServicos().get(i);
					//Primeira célula da linha: o método getTipo() do serviço
					tabelaServicosDesign[i][0] = servicoAtual.getTipo();
					//Segunda célula da linha: a data do serviço
					tabelaServicosDesign[i][1] = servicoAtual.getInicioServico();
					//Terceira célula da linha: a hora de entrada do serviço
					tabelaServicosDesign[i][2] = servicoAtual.getHoraEntrada() + ":" + servicoAtual.getMinutosEntrada();
					//Quarta célula da linha: o preço total do serviço
					tabelaServicosDesign[i][3] = "R$ " + servicoAtual.calculaPrecoTotal();
				}
				//GAMBIARRA PARA QUE O USU�RIO NÃO POSSA EDITAR OS DADOS DA TABELA
				@SuppressWarnings("serial")
				DefaultTableModel modeloTabelaServicos = new DefaultTableModel(tabelaServicosDesign, new String[] {
						"Serviço", "Data", "Hora", "Preço"
				}) {

					@Override
				    public boolean isCellEditable(int row, int column) {
				        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
				        return false;
				    }
				};
				tabelaServicos.setModel(modeloTabelaServicos);
				//FIM DE CONSTRUÇÃO DE TABELA
				Collections.sort(listaQuartos);
				designTabela = new Object[listaQuartos.size()][6];	
				for (int j = 0; j < listaQuartos.size(); j++){
					Quarto quartoAtualContrato = listaQuartos.get(j);
					//Para preencher a primeira coluna da linha: Descrição do quarto
					designTabela[j][0] = quartoAtualContrato.getTipo();
					//Para preencher a segunda coluna da linha: O preço da diária
					designTabela[j][1] = "R$ " + quartoAtualContrato.getPrecoDiaria();
					//Para preencher a terceira coluna da linha: O número de diárias setadas
					designTabela[j][2] = contrato.getNumeroDiarias();
					//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
					designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
					//Para preencher a quinta coluna da linha: O número do quarto
					designTabela[j][4] = quartoAtualContrato.getNumero();
					//Para preencher a sexta coluna da linha: O preço a ser pago
					designTabela[j][5] = "R$ " + contrato.getNumeroDiarias() * quartoAtualContrato.getPrecoDiaria();
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
					tabelaQuartos.setModel(modeloTabela4);
					tabelaQuartos.getColumnModel().getColumn(3).setPreferredWidth(180); //Aumentando o tamanho da quarta coluna, pq a String de título dela é grande
					tabelaQuartos.getColumnModel().getColumn(0).setPreferredWidth(130); // Idem ao comment acima
					tabelaQuartos.setRowSelectionAllowed(true);
					precoTotal = contrato.calculaPrecoFinal();
					lblPrecoTotal.setText("Total a ser pago: R$ " + precoTotal);
					if (contrato.getStatus().equals("RESERVA")){
					lblPrecoTotal.setText("--RESERVA--");	
					}
	}
	public void atualizaBotoes(){
		if (objetoDinamico == null){
			btnRemoverDinamico.setEnabled(false);
			btnAdicionarDinamico.setEnabled(false);
			btnEditarDinamico.setEnabled(false);
		}else{
			btnRemoverDinamico.setEnabled(true);
			btnAdicionarDinamico.setEnabled(true);
			btnEditarDinamico.setEnabled(true);
			if (objetoDinamico instanceof Hospede){
				btnRemoverDinamico.setText("Remover hóspede");
				btnAdicionarDinamico.setText("Adicionar hóspede");
				btnEditarDinamico.setText("Editar hóspede");
			}else if (objetoDinamico instanceof Quarto){
				btnRemoverDinamico.setText("Remover quarto");
				btnAdicionarDinamico.setText("Adicionar quarto");
				btnEditarDinamico.setText("Editar quarto");
			}else if (objetoDinamico instanceof Servico){
				btnRemoverDinamico.setText("Remover serviço");
				btnAdicionarDinamico.setText("Adicionar serviço");
				btnEditarDinamico.setText("Editar serviço");
			}
		}
	}
	public void setObjetoDinamico (int i, List<? extends Object> lista){
		objetoDinamico = lista.get(i);
	}
	public Contrato getContrato(){
		return contrato;
	}
	private void setSelecaoRelativa(int i){
		selecaoRelativa = i;
		if (selecaoRelativa != 0){
			if (selecaoRelativa == SELECAO_HOSPEDE){
				btnAdicionarDinamico.setEnabled(true);
				btnAdicionarDinamico.setText("Adicionar hóspede");
			}else if (selecaoRelativa == SELECAO_QUARTO){
				btnAdicionarDinamico.setEnabled(true);
				btnAdicionarDinamico.setText("Adicionar quarto");
			}else if (selecaoRelativa == SELECAO_SERVICO){
				btnAdicionarDinamico.setEnabled(true);
				btnAdicionarDinamico.setText("Adicionar serviço");
			}
		}
	}
}