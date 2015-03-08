package gui;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import core.AluguelCarro;
import core.Babysitter;
import core.Contrato;
import core.Hospede;
import core.Restaurante;
import core.Servico;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeHospedes;

public class PainelRelatorio extends JInternalFrame {

	private static final long serialVersionUID = -1940047160422510314L;
	private ColecaoDeHospedes colecaoDeHospedes;
	private ColecaoDeContratos colecaoDeContratos;
	private JTable tableHospede;
	private JTable tableOpiniao;
	private JLabel lblSemConHospedes;
	private JLabel lblNumHospedes;
	private JLabel lblAbeHospedes;
	private JLabel lblFecHospedes;
	private JLabel lblNumOpi;
	private JLabel lblMedia;
	private JTable table;
	private JButton btnHospedes;
	private JPanel panel = new JPanel();
	protected CardLayout layoutPainel = new CardLayout(0, 0);
	private JButton btnContratos;
	private JPanel PanelHospedes;
	private JPanel PanelContratos;
	private JButton btnServicos;
	private JPanel PanelServicos;
	private JLabel lblNumContratos;
	private JLabel lblContratoAbe;
	private JLabel lblMaiorDiaria;
	private JLabel lblContratoRese;
	private JLabel lblContratoFec;
	private JLabel lblMedHospedeCon;
	private JLabel lblMedDiarias;
	private JTable tableContrato;
	private JLabel lblNumServicos;
	private JLabel lblAbeServi;
	private JLabel lblFecServi;
	private JLabel lblCarrosAtivos;
	private JLabel lblBabyAtivas;
	private JLabel lblQuartosAtivo;
	private JLabel lblRestaurantePed;
	private JTable tableServicos;
	private JLabel lblHospedeReserva;
	private JButton btnFaturamento;
	private JPanel PanelFaturamento;

	public PainelRelatorio(ColecaoDeHospedes listaDeHospedes, ColecaoDeContratos listaContratos, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
		//Para atualizar o painel quando ele for ativado novamente.
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				//Atualizacao dos dados referentes aos HOSPEDES.
				lblNumHospedes.setText("" + colecaoDeHospedes.getListaHospedeTamanho());
				lblAbeHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("ABERTO").size());
				lblFecHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("FECHADO").size());
				lblHospedeReserva.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("RESERVA").size());
				lblSemConHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato(null).size());
				escreveTabelaHospede();
				
				//Atualizacao dos dados referentes as OPINIOES dos hospedes.
				int numOpiniao = 0;
				double somaNotas = 0.0;
				double mediaOpinioes = 0.0;
				for (Hospede h: colecaoDeHospedes.getListaHospedes()) {
					if (h.getOpiniao() != null) {
						somaNotas += h.getOpiniao().getNota();
						numOpiniao++;
					}
				}
				mediaOpinioes = (somaNotas / numOpiniao);
				lblNumOpi.setText("" + numOpiniao);
				lblMedia.setText("" + (Double.isNaN(mediaOpinioes) ? 0.0 : mediaOpinioes));
				escreveTabelaOpiniao();
				
				//Atualizacao dos dados referentes aos CONTRATOS.
				int maior = 0;
				int somaDiarias = 0;
				int somaHospedes = 0;
				double mediaDiarias = 0.0;
				int mediaHospedes = 0;
				for (Contrato c: colecaoDeContratos.getListaContratos()) {
					somaDiarias += c.getNumeroDiarias();
					if (c.getNumeroDiarias() > maior) {
						maior = c.getNumeroDiarias();
					}
					somaHospedes += c.getListaHospedes().size();
				}
				mediaHospedes = (colecaoDeContratos.getListaContratoTamanho() == 0 ? 0 : (somaHospedes / colecaoDeContratos.getListaContratoTamanho()));
				mediaDiarias = (colecaoDeContratos.getListaContratoTamanho() == 0 ? 0.0 : (somaDiarias / colecaoDeContratos.getListaContratoTamanho()));
				lblMedHospedeCon.setText("" + mediaHospedes);
				lblMaiorDiaria.setText("" + maior);
				lblMedDiarias.setText("" + mediaDiarias);
				lblNumContratos.setText("" + colecaoDeContratos.getListaContratoTamanho());
				lblContratoAbe.setText("" + colecaoDeContratos.pesquisaStatusContrato("ABERTO").size());
				lblContratoFec.setText("" + colecaoDeContratos.pesquisaStatusContrato("FECHADO").size());
				lblContratoRese.setText("" + colecaoDeContratos.pesquisaStatusContrato("RESERVA").size());
				escreveTabelaContrato();
				
				//Atualizacao dos dados referentes aos SERVICOS.
				int numServicos = 0;
				int numServicosAbe = 0;
				int numServicosFec = 0;
				int numQuartos = 0;
				int numCarros = 0;
				int numPedidosRestaurante = 0;
				int numBabySitter = 0;
				for (Contrato c: colecaoDeContratos.getListaContratos()) {
					numServicos += c.getListaServicos().size() ;
					if (c.getStatus().equals("ABERTO")) {
						numServicosAbe++;
						numQuartos += c.getListaQuartosAlugados().size();
					}else if (c.getStatus().equals("FECHADO") ){
						numServicosFec++;
					}
					for (Servico s: c.getListaServicos()) {
						if (s instanceof AluguelCarro) {
							numCarros++;
						}else if (s instanceof Restaurante) {
							numPedidosRestaurante++;
						}else if (s instanceof Babysitter) {
							numBabySitter++;
						}
					}
				}
				lblNumServicos.setText("" + numServicos);
				lblAbeServi.setText("" + numServicosAbe);
				lblFecServi.setText("" + numServicosFec);
				lblCarrosAtivos.setText("" + numCarros);
				lblBabyAtivas.setText("" + numBabySitter);
				lblQuartosAtivo.setText("" + numQuartos);
				lblRestaurantePed.setText("" + numPedidosRestaurante);
				escreveTabelaServicos(numServicos);
				
				//Habilitar o botao para ver o faturamento no grafico
				atualizarBotaoFaturamento();
			}
		});
		
		setTitle("Dados gerais");
		setFrameIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/files.png")));
		setClosable(true);
		setBounds(0, 0, 800, 422);

		this.colecaoDeHospedes = listaDeHospedes;
		this.colecaoDeContratos = listaContratos;
		
		//Criacao dos botoes para selecionar quais dados seram mostrados no painel.
		btnHospedes = new JButton("Hóspedes");
		btnHospedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layoutPainel.show(panel, "hospedes");
			}
		});
		btnHospedes.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/clientes_icon.png")));

		btnContratos = new JButton("Contratos");
		btnContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panel, "contratos");
			}
		});
		btnContratos.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/contrato_icon.png")));

		btnServicos = new JButton("Serviços");
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutPainel.show(panel, "servicos");
			}
		});
		btnServicos.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/servicos_icon.png")));
		
		btnFaturamento = new JButton("Faturamento");
		btnFaturamento.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent arg0) {
		    //Criacao do grafico de barras do panelFaturamento
		    
		    //Dados
		    DefaultCategoryDataset graficoBarrasData = new DefaultCategoryDataset();
		    graficoBarrasData.setValue(mesTotalFaturamento(0), "Faturamento", "Jan");
		    graficoBarrasData.setValue(mesTotalFaturamento(1), "Faturamento", "Fev");
		    graficoBarrasData.setValue(mesTotalFaturamento(2), "Faturamento", "Mar");
		    graficoBarrasData.setValue(mesTotalFaturamento(3), "Faturamento", "Abr");
		    graficoBarrasData.setValue(mesTotalFaturamento(4), "Faturamento", "Mai");
		    graficoBarrasData.setValue(mesTotalFaturamento(5), "Faturamento", "Jun");
		    graficoBarrasData.setValue(mesTotalFaturamento(6), "Faturamento", "Jul");
		    graficoBarrasData.setValue(mesTotalFaturamento(7), "Faturamento", "Ago");
		    graficoBarrasData.setValue(mesTotalFaturamento(8), "Faturamento", "Set");
		    graficoBarrasData.setValue(mesTotalFaturamento(9), "Faturamento", "Out");
		    graficoBarrasData.setValue(mesTotalFaturamento(10), "Faturamento", "Nov");
		    graficoBarrasData.setValue(mesTotalFaturamento(11), "Faturamento", "Dez");
		    //Objetos
		    JFreeChart barChart = ChartFactory.createBarChart("Faturamento Mensal", "Mês", "Faturamento", graficoBarrasData, PlotOrientation.VERTICAL, false, true, true);
		    CategoryPlot barChrt = barChart.getCategoryPlot();
		    BarRenderer renderer = (BarRenderer) barChrt.getRenderer();
		    //Cores
		    barChrt.setRangeGridlinePaint(Color.BLACK);
		    Color cinzaClaro = new Color(224,224,224);
		    barChrt.setBackgroundPaint(cinzaClaro);
		    Color verde = new Color(0,204,0);
		    renderer.setSeriesPaint(0, verde);
		    //Painel
		    ChartPanel panelGrafico = new ChartPanel(barChart);
		    PanelFaturamento.removeAll();
		    PanelFaturamento.add(panelGrafico, BorderLayout.CENTER);
		    PanelFaturamento.validate();
		    
		    layoutPainel.show(panel, "Faturamento");
		  }
		});
    btnFaturamento.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/coins17.png")));
    
		panel.setLayout(layoutPainel);
		
		//PAINEL RELATORIO HOSPEDE
		PanelHospedes = new JPanel();
		PanelHospedes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(PanelHospedes, "hospedes");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//INFORMACOES DO PAINEL HOSPEDES
		JLabel label_5 = new JLabel("Hóspedes");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTotalHospedes = new JLabel("Total de hóspedes: ");
		lblTotalHospedes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNumHospedes = new JLabel("");
		lblNumHospedes.setText("" + colecaoDeHospedes.getListaHospedeTamanho());
		lblNumHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblHospedesAbe = new JLabel("Hóspedes (Aberto): ");
		lblHospedesAbe.setToolTipText("Hóspedes com contrato ativo.");
		lblHospedesAbe.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblAbeHospedes = new JLabel("");
		lblAbeHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("ABERTO").size());
		lblAbeHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblHospedesFec = new JLabel("Hóspedes (Fechado): ");
		lblHospedesFec.setToolTipText("Hóspedes com contrato encerrado.");
		lblHospedesFec.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblFecHospedes = new JLabel("");
		lblFecHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("FECHADO").size());
		lblFecHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHspedesreserva = new JLabel("Hóspedes (Reserva): ");
		lblHspedesreserva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblHospedeReserva = new JLabel("");
		lblHospedeReserva.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("RESERVA").size());
		lblHospedeReserva.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblHospedesSem = new JLabel("Hóspedes (Sem contrato): ");
		lblHospedesSem.setToolTipText("Hóspedes sem contrato.");
		lblHospedesSem.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblSemConHospedes = new JLabel("");
		lblSemConHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato(null).size());
		lblSemConHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		//INFORMACOES DO PAINEL OPINIAO
		JLabel label_6 = new JLabel("Opiniões");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblMedOpinioes = new JLabel("Média das notas: ");
		lblMedOpinioes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel NumOpinioes = new JLabel("Total de opiniões: ");
		NumOpinioes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNumOpi = new JLabel("");
		lblNumOpi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblMedia = new JLabel("");
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GroupLayout gl_PanelHospedes = new GroupLayout(PanelHospedes);
		gl_PanelHospedes.setHorizontalGroup(
		  gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		    .addGroup(gl_PanelHospedes.createSequentialGroup()
		      .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGap(10)
		          .addComponent(lblHospedesFec)
		          .addGap(6)
		          .addComponent(lblFecHospedes))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGap(10)
		          .addComponent(lblHospedesSem)
		          .addGap(6)
		          .addComponent(lblSemConHospedes))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(85)
		              .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.TRAILING)
		                .addGroup(gl_PanelHospedes.createSequentialGroup()
		                  .addComponent(label_5)
		                  .addGap(383)
		                  .addComponent(label_6))
		                .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		                  .addComponent(lblMedOpinioes)
		                  .addComponent(NumOpinioes))))
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(10)
		              .addComponent(lblHospedesAbe)
		              .addGap(6)
		              .addComponent(lblAbeHospedes)))
		          .addPreferredGap(ComponentPlacement.RELATED)
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addComponent(lblNumOpi)
		            .addComponent(lblMedia)))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGap(10)
		          .addComponent(lblTotalHospedes)
		          .addGap(6)
		          .addComponent(lblNumHospedes))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addContainerGap()
		          .addComponent(lblHspedesreserva)
		          .addPreferredGap(ComponentPlacement.RELATED)
		          .addComponent(lblHospedeReserva))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addContainerGap()
		          .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)))
		      .addContainerGap())
		);
		gl_PanelHospedes.setVerticalGroup(
		  gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		    .addGroup(gl_PanelHospedes.createSequentialGroup()
		      .addGap(7)
		      .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		        .addComponent(label_5)
		        .addComponent(label_6))
		      .addGap(11)
		      .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.TRAILING)
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addComponent(lblTotalHospedes)
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(1)
		              .addComponent(lblNumHospedes)))
		          .addGap(6))
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
		            .addComponent(NumOpinioes)
		            .addComponent(lblNumOpi))
		          .addPreferredGap(ComponentPlacement.RELATED)))
		      .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_PanelHospedes.createSequentialGroup()
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addComponent(lblHospedesAbe)
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(1)
		              .addComponent(lblAbeHospedes)))
		          .addGap(6)
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addComponent(lblHospedesFec)
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(1)
		              .addComponent(lblFecHospedes)))
		          .addGap(6)
		          .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
		            .addComponent(lblHospedesSem)
		            .addGroup(gl_PanelHospedes.createSequentialGroup()
		              .addGap(1)
		              .addComponent(lblSemConHospedes))))
		        .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
		          .addComponent(lblMedOpinioes)
		          .addComponent(lblMedia)))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblHspedesreserva)
		        .addComponent(lblHospedeReserva))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
		      .addGap(2))
		);

		JScrollPane scrollPaneHospedes = new JScrollPane();
		tabbedPane.addTab("Hóspedes", null, scrollPaneHospedes, null);
		
	  //Criacao da tabela da parte de hospede
		tableHospede = new JTable();
		tableHospede.setRowSelectionAllowed(true);
		scrollPaneHospedes.setColumnHeaderView(tableHospede);
		scrollPaneHospedes.setViewportView(tableHospede);
		scrollPaneHospedes.setRowHeaderView(table);

		JScrollPane scrollPaneOpinioes = new JScrollPane();
		tabbedPane.addTab("Opiniões", null, scrollPaneOpinioes, null);
		
		//Criacao da tabela da parte de opiniao
		tableOpiniao = new JTable();
		tableOpiniao.setRowSelectionAllowed(true);
		scrollPaneOpinioes.setColumnHeaderView(tableOpiniao);
		scrollPaneOpinioes.setViewportView(tableOpiniao);
		scrollPaneOpinioes.setRowHeaderView(table);

		PanelHospedes.setLayout(gl_PanelHospedes);

		PanelContratos = new JPanel();
		PanelContratos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(PanelContratos, "contratos");
		
		//INFORMACOES DO PAINEL CONTRATO
		JLabel lblContrato = new JLabel("Contrato");
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTotalDeContratos = new JLabel("Total de contratos: ");
		lblTotalDeContratos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumContratos = new JLabel("");
		lblNumContratos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContratosAbertos = new JLabel("Contratos abertos: ");
		lblContratosAbertos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblContratosFechados = new JLabel("Contratos fechados: ");
		lblContratosFechados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblContratoAbe = new JLabel("");
		lblContratoAbe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblContratoFec = new JLabel("");
		lblContratoFec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContratosReservados = new JLabel("Contratos reservados: ");
		lblContratosReservados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblContratoRese = new JLabel("");
		lblContratoRese.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblMdiaPcontrato = new JLabel("Média p/Contrato: ");
		lblMdiaPcontrato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMedHospedeCon = new JLabel("");
		lblMedHospedeCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMaiorDiriaRegistrada = new JLabel("Maior diária registrada: ");
		lblMaiorDiriaRegistrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMaiorDiaria = new JLabel("");
		lblMaiorDiaria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMdiaDeDirias = new JLabel("Média de diárias p/Contrato: ");
		lblMdiaDeDirias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMedDiarias = new JLabel("");
		lblMedDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPaneContrato = new JScrollPane();
		GroupLayout gl_PanelContratos = new GroupLayout(PanelContratos);
		gl_PanelContratos.setHorizontalGroup(
			gl_PanelContratos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMaiorDiriaRegistrada)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMaiorDiaria)
					.addContainerGap(550, Short.MAX_VALUE))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addComponent(lblContratosFechados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContratoFec))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addComponent(lblContratosReservados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContratoRese)))
					.addContainerGap(552, Short.MAX_VALUE))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneContrato, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_PanelContratos.createSequentialGroup()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_PanelContratos.createSequentialGroup()
									.addComponent(lblTotalDeContratos)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumContratos))
								.addGroup(gl_PanelContratos.createSequentialGroup()
									.addComponent(lblContratosAbertos)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblContratoAbe)))
							.addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMdiaDeDirias)
								.addComponent(lblMdiaPcontrato)))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addGap(69)
							.addComponent(lblContrato)
							.addPreferredGap(ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
							.addComponent(lblHospede)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMedDiarias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblMedHospedeCon, GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE))
					.addGap(101))
		);
		gl_PanelContratos.setVerticalGroup(
			gl_PanelContratos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContrato)
								.addComponent(lblHospede))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalDeContratos)
								.addComponent(lblNumContratos)
								.addComponent(lblMdiaPcontrato)))
						.addComponent(lblMedHospedeCon, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContratosAbertos)
								.addComponent(lblContratoAbe)
								.addComponent(lblMdiaDeDirias))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContratosFechados)
								.addComponent(lblContratoFec))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContratosReservados)
								.addComponent(lblContratoRese))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMaiorDiriaRegistrada)
								.addComponent(lblMaiorDiaria))
							.addGap(18))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addComponent(lblMedDiarias, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(97)))
					.addComponent(scrollPaneContrato, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addContainerGap())
		);
		
	  //Criacao da tabela da parte de contrato
		tableContrato = new JTable();
		tableContrato.setRowSelectionAllowed(true);
		scrollPaneContrato.setColumnHeaderView(tableContrato);
		scrollPaneContrato.setViewportView(tableContrato);
		scrollPaneContrato.setRowHeaderView(table);
		PanelContratos.setLayout(gl_PanelContratos);

		PanelServicos = new JPanel();
		PanelServicos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(PanelServicos, "servicos");
		
		//INFORMACOES DO PAINEL SERVICOS
		JLabel lblServios = new JLabel("Serviços");
		lblServios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNDeServios = new JLabel("N° de serviços já usados: ");
		lblNDeServios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumServicos = new JLabel("");
		lblNumServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNDeServios_1 = new JLabel("N° de serviços em contratos ->");
		lblNDeServios_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblAbertos = new JLabel("Abertos: ");
		lblAbertos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblFechados = new JLabel("Fechados: ");
		lblFechados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblAbeServi = new JLabel("");
		lblAbeServi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblFecServi = new JLabel("");
		lblFecServi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNDeCarros = new JLabel("N° de carros alugados: ");
		lblNDeCarros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblCarrosAtivos = new JLabel("");
		lblCarrosAtivos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNDeBabysitters = new JLabel("N° de babysitters ativas: ");
		lblNDeBabysitters.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblBabyAtivas = new JLabel("");
		lblBabyAtivas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNDeQuartos = new JLabel("N° de quartos usados: ");
		lblNDeQuartos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblQuartosAtivo = new JLabel("");
		lblQuartosAtivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNDePedidos = new JLabel("N° de pedidos nos restaurantes: ");
		lblNDePedidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblRestaurantePed = new JLabel("New label");
		lblRestaurantePed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPaneServicos = new JScrollPane();
		GroupLayout gl_PanelServicos = new GroupLayout(PanelServicos);
		gl_PanelServicos.setHorizontalGroup(
			gl_PanelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelServicos.createSequentialGroup()
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDeServios)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNumServicos))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addGap(78)
							.addComponent(lblServios))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDeServios_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAbertos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAbeServi)
							.addGap(18)
							.addComponent(lblFechados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFecServi))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDePedidos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRestaurantePed))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDeCarros)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCarrosAtivos))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDeBabysitters)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBabyAtivas))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNDeQuartos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQuartosAtivo))
						.addGroup(gl_PanelServicos.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneServicos, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_PanelServicos.setVerticalGroup(
			gl_PanelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelServicos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblServios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDeServios)
						.addComponent(lblNumServicos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDeServios_1)
						.addComponent(lblAbertos)
						.addComponent(lblAbeServi)
						.addComponent(lblFechados)
						.addComponent(lblFecServi))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDeCarros)
						.addComponent(lblCarrosAtivos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDeBabysitters)
						.addComponent(lblBabyAtivas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDeQuartos)
						.addComponent(lblQuartosAtivo))
					.addGap(7)
					.addGroup(gl_PanelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNDePedidos)
						.addComponent(lblRestaurantePed))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneServicos, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		//Criacao da tabela da parte de servico
		tableServicos = new JTable();
		tableServicos.setRowSelectionAllowed(true);
		scrollPaneServicos.setColumnHeaderView(tableServicos);
		scrollPaneServicos.setViewportView(tableServicos);
		scrollPaneServicos.setRowHeaderView(table);
		PanelServicos.setLayout(gl_PanelServicos);
		
		//Panel Faturamento
		PanelFaturamento = new JPanel();
    panel.add(PanelFaturamento, "Faturamento");
    

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
		  groupLayout.createParallelGroup(Alignment.LEADING)
		    .addGroup(groupLayout.createSequentialGroup()
		      .addContainerGap()
		      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		          .addComponent(panel, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
		          .addContainerGap())
		        .addGroup(groupLayout.createSequentialGroup()
		          .addComponent(btnHospedes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		          .addGap(18)
		          .addComponent(btnContratos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		          .addGap(18)
		          .addComponent(btnServicos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		          .addGap(18)
		          .addComponent(btnFaturamento)
		          .addGap(214))))
		);
		groupLayout.setVerticalGroup(
		  groupLayout.createParallelGroup(Alignment.TRAILING)
		    .addGroup(groupLayout.createSequentialGroup()
		      .addContainerGap()
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(btnHospedes)
		        .addComponent(btnContratos)
		        .addComponent(btnServicos)
		        .addComponent(btnFaturamento))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addComponent(panel, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
		      .addContainerGap())
		);
		
		PanelFaturamento.setLayout(new BorderLayout(0, 0));
		getContentPane().setLayout(groupLayout);
	}

	//TABELA HOSPEDES
	private void escreveTabelaHospede(){
		Collections.sort(colecaoDeHospedes.getListaHospedes());
		Object[][] designTabela = new Object[colecaoDeHospedes.getListaHospedes().size()][4];
		for (int i = 0; i < colecaoDeHospedes.getListaHospedes().size(); i++){
			Hospede hospedeAtual = colecaoDeHospedes.getListaHospedes().get(i);
			//Na 1 coluna "Nome", escreve o nome do hospede indice i.
			if (hospedeAtual.getNome() == null){
				designTabela[i][0] = "Não especificado";
			}else{
				designTabela[i][0] = hospedeAtual.getNome();
			}
			//Na 2 coluna "Nascimento", escreve a data de nascimento do hospede indice i.
			String dataFormatadaNascimento = "";
				dataFormatadaNascimento = Main.converteParaString(hospedeAtual.getDataNascimento());
			designTabela[i][1] = dataFormatadaNascimento;
			//Na 3 coluna "CPF", escreve o CPF do hospede indice i.
			if (hospedeAtual.getCpf() == null){
				designTabela[i][2] = "Não especificado";
			}else{
				designTabela[i][2] = hospedeAtual.getCpf();
			}
			//Na 4 coluna "Contrato", informa se o hospede esta em um contrato, se sim, escreve o status do contrato.
			if (hospedeAtual.getContratoLigado() == null){
				designTabela[i][3] = "Sem contrato";
			}else{
				designTabela[i][3] = hospedeAtual.getContratoLigado().getStatus();
			}
		}
		//Nome das colunas
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Nome", "Nascimento", "CPF", "Contrato"
		}) {
		//Um override neste metodo para não permitir a edicao na tabela.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableHospede.setModel(modeloTabela);
	}
	//TABELA OPINIAO
	private void escreveTabelaOpiniao(){
		List <Hospede> hospedesOpiniao = new ArrayList<Hospede>();
		Collections.sort(colecaoDeHospedes.getListaHospedes());
		//Um for para criar uma lista apenas com os hospedes que ja cadastraram uma opiniao
		for (Hospede h: colecaoDeHospedes.getListaHospedes()) {
			if (h.getOpiniao() != null) {
				hospedesOpiniao.add(h);
			}
		}

		Object[][] designTabela = new Object[hospedesOpiniao.size()][2];
		for (int i = 0; i < hospedesOpiniao.size(); i++){
			Hospede hospedeAtual = hospedesOpiniao.get(i);
			//Na 1 coluna "Comentario", escreve o comentario que o hospede cadastrou.
			designTabela[i][0] = hospedeAtual.getOpiniao().getComentario();
			//Na 2 coluna "Nota", escreve a nota que o hospede deu ao hotel.
			designTabela[i][1] = hospedeAtual.getOpiniao().getNota();
		}
		
		//Nomes das colunas da tabela
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Comentário", "Nota"
		}) {
		//Um override neste metodo para não permitir a edicao na tabela.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableOpiniao.setModel(modeloTabela);
	}
	
	//TABELA CONTRATO
	private void escreveTabelaContrato(){
		// INÍCIO DE CONSTRUÇÃO DA TABELA
						Object[][] designTabela = new Object[colecaoDeContratos.getListaContratos().size()][5];
						for (int i = 0; i < colecaoDeContratos.getListaContratos().size(); i++){
							Contrato contratoAtual = colecaoDeContratos.getListaContratos().get(i);
							// Para preencher a primeira linha da tabela, com o nome do Hóspede Principal.
							if (contratoAtual.getHospedePrincipal() == null){
								designTabela[i][0] = "Não especificado";
							}else{
								designTabela[i][0] = contratoAtual.getHospedePrincipal().getNome();
							}
							// Para conseguir uma String formatada com a data do checkin, através de um método na classe Main.
							String dataFormatadaCheckIn = "";
								dataFormatadaCheckIn = Main.converteParaString(contratoAtual.getDataCheckIn());
							designTabela[i][1] = dataFormatadaCheckIn;
							// Para conseguir uma String formatada com a estimada data do checkout.
							String dataFormatadaCheckOut = "";
								dataFormatadaCheckOut = Main.converteParaString(contratoAtual.getDataCheckOut());
							designTabela[i][2] = dataFormatadaCheckOut;
							// Para colocar na tabela o total de despesas do contrato.
							designTabela[i][3] = "R$ " + contratoAtual.calculaPrecoFinal();
							if (contratoAtual.getStatus().equals("RESERVA")){
								designTabela[i][3] = "-- RESERVA --";
							}
							// Para colocar na tabela o status do contrato.
							designTabela[i][4] = contratoAtual.getStatus();
					// FIM DE CONSTRUÇÃO DE TABELA.
					
				}
						@SuppressWarnings("serial")
						DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
								"Hóspede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
						}) {

							@Override
						    public boolean isCellEditable(int row, int column) {
						        return false;
						    }
						};

			
				tableContrato.setModel(modeloTabela);
	}
	
	//TABELA SERVICOS
	private void escreveTabelaServicos(int tamanho) {
		Object[][] designTabela = new Object[tamanho][3];
		int j = 0;
		for (Contrato c: colecaoDeContratos.getListaContratos()) {
			for (int i = 0; i < c.getListaServicos().size(); i++) {
				Servico servicoAtual = c.getListaServicos().get(i);
				//Na 1 coluna, escreve o nome do servico
				if (servicoAtual.getTipo() == null){
				designTabela[i + j][0] = "Não especificado";
				}else{
					designTabela[i + j][0] = servicoAtual.getTipo();
				}
				//Na 2 coluna, escreve o preço atual do servico
				designTabela[i + j][1] = servicoAtual.calculaPrecoTotal();
				//Na 3 coluna, escreve o status do contrato que esta usando o servico
				designTabela[i + j][2] = c.getStatus();
			}j += c.getListaServicos().size();
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Serviços", "Preço", "Contrato" })     {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		tableServicos.setModel(modeloTabela); 
	}
	
	//Dados para o grafico do panelFaturamento
	private double mesTotalFaturamento(int mes) {
	  double somaDoMes = 0.0;
    List<Contrato> contratos = colecaoDeContratos.pesquisaContratoCheckOut(mes);
    for (Contrato c: contratos) {
      somaDoMes += c.calculaPrecoFinal();
    }
    return somaDoMes;
  }
	
	//Ativar o botao para ver o grafico apenas quando existir algum contrato
	private void atualizarBotaoFaturamento(){
    if (colecaoDeContratos.getListaContratoTamanho() == 0){
      btnFaturamento.setEnabled(false);
      btnFaturamento.setToolTipText("É necessário existir algum contrato no hotel para ver o gráfico.");
    }else{
      btnFaturamento.setEnabled(true);
      btnFaturamento.setToolTipText("Gráfico de barras com o faturamento do hotel.");
    }
  }
}
