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
import core.colecoes.ColecaoDeEstrategias;
import core.colecoes.ColecaoDeHospedes;

public class PainelNovoContrato extends JInternalFrame implements Atualizador{

	private static final long serialVersionUID = -645307861350726967L;

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
  private PainelCadastroClientes painelCadastro;
	private JLabel lblQuartos;
	private JButton btnFinalizar;
	private JTable tabelaHospedesFinal;
	private JTable tabelaQuartosFinal;
	private List<Contrato> listaContratos;
	private Hospede hospedePrincipal;
	private JDesktopPane painelPrincipal;
	private JButton btnAlterarDiarias;
	private boolean isReserva;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut = Calendar.getInstance();
	private String cartaoDeCredito;
	private ColecaoDeEstrategias listaEstrategias;
	private boolean contratoFeito = false;
	private Atualizador framePai;

	public PainelNovoContrato(ColecaoDeHospedes listaDeHospedes, List<Quarto> listaQuartosDisponiveis, List<Contrato> listaContratos, JDesktopPane painelPrincipal, ColecaoDeEstrategias listaEstrategias, Atualizador framePai) {
		setFrameIcon(new ImageIcon(PainelNovoContrato.class.getResource("/resources/contrato_icon.png")));
		setTitle("Novo contrato");
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 1060, 400);
		this.framePai = framePai;
		this.painelPrincipal = painelPrincipal;
		this.listaEstrategias = listaEstrategias;
		this.listaDeHospedes = listaDeHospedes;
		listaHospedesDoContrato = new ArrayList<Hospede>();
		listaQuartosDoContrato = new ArrayList<Quarto>();
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		this.listaContratos = listaContratos;
		listaHospedesSemContrato = new ArrayList<Hospede>();
		dialogoDiarias = new DialogoDiarias();
		dialogoDiarias.setVisible(true);
		//Como DialogoDiarias é modal, daqui para baixo só será processado quando DialogoDiarias for "disposed"
		if (dialogoDiarias.getFinalizado()){
		diariasContrato = dialogoDiarias.getDiarias();
		isReserva = dialogoDiarias.isReserva();
		try {
			dataCheckIn = dialogoDiarias.getDataCheckIn();
			cartaoDeCredito = dialogoDiarias.getCartaoDeCredito();

		} catch (core.ParametrosInvalidosException e3) {
			JOptionPane.showMessageDialog(null, e3.getMessage());
		}catch (java.text.ParseException e4){
		  JOptionPane.showMessageDialog(null, e4.getMessage());
		}
		dataCheckOut.setTime(dataCheckIn.getTime());
		dataCheckOut.add(Calendar.DAY_OF_YEAR, diariasContrato);
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
					int escolha = JOptionPane.showOptionDialog(null, "Você deseja marcar esse hóspede como o hóspede principal do contrato? " + Main.quebraDeLinha + "O hóspede principal do contrato é o que será procurado para questões financeiras.", /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
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
				  if (painelCadastro == null || painelCadastro.isClosed()){
					painelCadastro = new PainelCadastroClientes(getListaDeHospedes(), PainelNovoContrato.this);
					getPainelPrincipal().add(painelCadastro);
					painelCadastro.show();
				  }else{
				    painelCadastro.toFront();
				  }
				}catch (java.text.ParseException e){
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
										.addComponent(btnRemoverDoContrato, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
										.addGap(170)
										.addComponent(btnCriarNovo, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
										.addGap(163)
										.addComponent(btnAdicionarNoContrato, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
										.addComponent(lblHospedesSemContrato, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHospedesNoContrato, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);
		gl_panelHospedes.setVerticalGroup(
				gl_panelHospedes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHospedes.createSequentialGroup()
						.addContainerGap(13, Short.MAX_VALUE)
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
		scrollPane_1.setViewportView(tabelaHospedesNoContrato);

		tabelaHospedesSemContrato = new JTable();
		tabelaHospedesSemContrato.setRowSelectionAllowed(true);
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

		btnAdicionarNoContratoQuarto = new JButton("Adicionar no contrato\r" + Main.quebraDeLinha);
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnAdicionarNoContratoQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				if (diariasContrato == 0) {
				//					dialogoDiarias = new DialogoDiarias();
				//					dialogoDiarias.setVisible(true);
				//					//Como DialogoDiarias é modal, daqui para baixo só será processado quando DialogoDiarias for "disposed"
				//					diariasContrato = dialogoDiarias.getDiarias();
				//				}
				if (quartoVagoSelecionado.isLivreParaReserva(new Reserva(dataCheckIn, dataCheckOut).getIntervalo())){
					listaQuartosDoContrato.add(quartoVagoSelecionado);
					PainelNovoContrato.this.listaQuartosDisponiveis.remove(quartoVagoSelecionado);
				}else{
					JOptionPane.showMessageDialog(null, "O quarto disponível não está disponível durante o período do contrato.");
				}

				escreveTabelas();
			}
		});
		btnAdicionarNoContratoQuarto.setEnabled(false);

		btnRemoverDoContratoQuarto = new JButton("Remover do contrato");
		btnAdicionarNoContratoQuarto.setEnabled(false);
		btnRemoverDoContratoQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				PainelNovoContrato.this.listaQuartosDisponiveis.add(quartoContratoSelecionado);
//				listaQuartosDoContrato.remove(quartoContratoSelecionado);
//				quartoContratoSelecionado.setToLivre();
//				//				if (listaQuartosDoContrato.size() == 0){ //Reiniciando o valor das diárias se for esvaziada a lista de quartos do contrato
//				//					diariasContrato = 0;
//				//				}
//				escreveTabelas();
				retiraQuartoLista(quartoContratoSelecionado);
				escreveTabelas();
			}
		});

		btnAlterarDiarias = new JButton("Alterar diárias, data de Check-in ou Cartão de crédito");
		btnAlterarDiarias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogoDiarias = new DialogoDiarias(cartaoDeCredito);
				dialogoDiarias.setVisible(true);
//				for (Quarto quarto: listaQuartosDoContrato){
////					quarto.setToLivre();
////					PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
////					listaQuartosDoContrato.remove(quarto);
//					retiraQuartoLista(quarto);
//				}
				//Como DialogoDiarias é modal, daqui para baixo só será processado quando DialogoDiarias for "disposed"
				diariasContrato = dialogoDiarias.getDiarias();
				cartaoDeCredito = dialogoDiarias.getCartaoDeCredito();
				isReserva = dialogoDiarias.isReserva();
				try {
					dataCheckIn = dialogoDiarias.getDataCheckIn();
				} catch (core.ParametrosInvalidosException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage());
				}catch (java.text.ParseException e4){
				  JOptionPane.showMessageDialog(null, e4.getMessage());
				}
				dataCheckOut.setTime(dataCheckIn.getTime());
				dataCheckOut.add(Calendar.DAY_OF_YEAR, diariasContrato);
				for (int i = listaQuartosDoContrato.size() - 1; i > -1; i--){
					Quarto quarto = listaQuartosDoContrato.get(i);
					retiraQuartoLista(quarto);
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
										.addComponent(btnRemoverDoContratoQuarto, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
										.addGap(175)
										.addComponent(btnAlterarDiarias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(204)
										.addComponent(btnAdicionarNoContratoQuarto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
										.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
										.addComponent(lblQuartosLivres, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblQuartosNoContrato, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);
		gl_panelQuartos.setVerticalGroup(
				gl_panelQuartos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelQuartos.createSequentialGroup()
						.addGap(18)
						.addComponent(lblQuartosLivres, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblQuartosNoContrato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGap(8)
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(gl_panelQuartos.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoverDoContratoQuarto)
								.addComponent(btnAdicionarNoContratoQuarto)
								.addComponent(btnAlterarDiarias))
								.addContainerGap())
				);

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
					tabelaQuartosNoContrato.clearSelection();
				}atualizaBotoes();

			}
		});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.

		tabelaQuartosNoContrato = new JTable();
		scrollPane_3.setViewportView(tabelaQuartosNoContrato);
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha3 = tabelaQuartosNoContrato.getSelectionModel(); 
		modeloSelecaoLinha3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha3.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaQuartosNoContrato.getSelectedRows(); 
				if (indiceSelecionado.length <= 0){
					quartoContratoSelecionado = null;
				}else{
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
					Contrato contrato;
					if (isReserva){//Se for um contrato do tipo reserva...
						contrato = new Contrato(dataCheckIn, listaQuartosDoContrato, listaHospedesDoContrato, diariasContrato);
						contratoFeito = true;
						List<Estrategia> estrategiasAtuais = PainelNovoContrato.this.listaEstrategias.checaContratoComEstrategia(contrato);
						if (estrategiasAtuais.size() > 0){//Se não houver nenhuma estratégia no período referente...
						  for (Estrategia estrategiaAtual: estrategiasAtuais){
						    JOptionPane.showMessageDialog(null, "O contrato está em um período referente a seguinte estratégia:" + Main.quebraDeLinha + estrategiaAtual.toString());
	              contrato.adicionaEstrategiaNoContrato(estrategiaAtual);
						  }
							
							for (Quarto quarto: listaQuartosDoContrato){
								PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
								Reserva reserva = new Reserva(contrato);
								quarto.adicionaReserva(reserva);
							}
						}else{//Se houver estratégia no período referente...
							for (Quarto quarto: listaQuartosDoContrato){
								PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
								Reserva reserva = new Reserva(contrato);
								quarto.adicionaReserva(reserva);

							}
						}
					}else{//Se for um contrato do check-in imediato...
						contrato = new Contrato(listaQuartosDoContrato, listaHospedesDoContrato, diariasContrato);
						contratoFeito = true;
						List<Estrategia> estrategiasAtuais = PainelNovoContrato.this.listaEstrategias.checaContratoComEstrategia(contrato);
						
            if (estrategiasAtuais.size() > 0){//Se houver estratégia no período referente...
              for (Estrategia estrategiaAtual: estrategiasAtuais){
                JOptionPane.showMessageDialog(null, "O contrato está em um período referente a seguinte estratégia:" + Main.quebraDeLinha + estrategiaAtual.toString());
                contrato.adicionaEstrategiaNoContrato(estrategiaAtual);
              }
							for (Quarto quarto: listaQuartosDoContrato){
								PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
								Reserva reserva = new Reserva(contrato);
								quarto.adicionaReserva(reserva);
							}
						}else{//Se não houverem estratégias no período referente...
							for (Quarto quarto: listaQuartosDoContrato){
								PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
								Reserva reserva = new Reserva(contrato);
								quarto.adicionaReserva(reserva);
							}
						}
					}contrato.setCartaoDeCredito(cartaoDeCredito);
					for (Hospede hospede: listaHospedesDoContrato){//Ligando hóspedes ao contrato.
						hospede.setContratoLigado(contrato);
					}
					if (hospedePrincipal != null){//Dando a um contrato o seu hóspede principal.
						contrato.setHospedePrincipal(hospedePrincipal);
					}
					getListaContratos().add(contrato);
					JOptionPane.showMessageDialog(null, "Contrato criado com sucesso!");
					dispose();
				}catch (core.ParametrosInvalidosException e1){
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
								.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
								.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
								.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelFinalizar.createSequentialGroup()
										.addGap(651)
										.addComponent(btnFinalizar, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))
										.addContainerGap())
				);
		gl_panelFinalizar.setVerticalGroup(
				gl_panelFinalizar.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFinalizar.createSequentialGroup()
						.addGap(13)
						.addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGap(8)
						.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
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
	public boolean getFinalizado(){
	  return dialogoDiarias.getFinalizado();
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
				if (!(quartoAtual.isLivreParaReserva(new Reserva(dataCheckIn, dataCheckOut).getIntervalo()))){
					designTabela[i][0] = designTabela[i][0] + " (RESERVADO DURANTE O PERÍODO SELECIONADO)";
				}
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
				designTabela[j][2] = diariasContrato;
				//Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
				designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
				//Para preencher a quinta coluna da linha: O número do quarto
				designTabela[j][4] = quartoAtualContrato.getNumero();
				//Para preencher a sexta coluna da linha: O preço a ser pago
				designTabela[j][5] = "R$ " + quartoAtualContrato.getPrecoDiaria() * diariasContrato;
			}
			@SuppressWarnings("serial")
			DefaultTableModel modeloTabela4 = new DefaultTableModel(designTabela, new String[] {
					"Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
			}) {

				@Override
				public boolean isCellEditable(int row, int column) {
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
	private void retiraQuartoLista(Quarto quarto){
		PainelNovoContrato.this.listaQuartosDisponiveis.add(quarto);
		listaQuartosDoContrato.remove(quarto);
	}
	@Override
	public void dispose(){
		if (contratoFeito == false){//Retira os contratos daquela tabela de quartos no contrato, mais como um safeguard para uma possível modificação de código.
			for (int i = listaQuartosDoContrato.size() - 1; i > -1; i--){
				Quarto quarto = listaQuartosDoContrato.get(i);
				if (!(listaQuartosDisponiveis.contains(quarto))){
					listaQuartosDisponiveis.add(quarto);
				}
			}
		}framePai.atualiza();
		super.dispose();
	}
  public void atualiza() {
    escreveTabelas();
    }
}
