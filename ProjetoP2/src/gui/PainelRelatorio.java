package gui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;

import core.Contrato;
import core.Hospede;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeHospedes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PainelRelatorio extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane painelPrincipal;
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

	public PainelRelatorio(ColecaoDeHospedes listaDeHospedes, ColecaoDeContratos listaContratos, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				//HOSPEDE
				lblNumHospedes.setText("" + colecaoDeHospedes.getListaHospedeTamanho());
				lblAbeHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("ABERTO").size());
				lblFecHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato("FECHADO").size());
				lblSemConHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato(null).size());
				escreveTabelaHospede();
				//OPINIAO
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
				//CONTRATO
				lblNumContratos.setText("" + colecaoDeContratos.getListaContratoTamanho());
				lblContratoAbe.setText("" + colecaoDeContratos.pesquisaStatusContrato("ABERTO"));
				lblContratoFec.setText("" + colecaoDeContratos.pesquisaStatusContrato("FECHADO"));
				lblContratoRese.setText("" + colecaoDeContratos.pesquisaStatusContrato("RESERVA"));
			}
		});
		setTitle("Dados gerais");
		setFrameIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/relatorios.png")));
		setClosable(true);
		setBounds(0, 0, 754, 420);

		this.painelPrincipal = painelPrincipal;
		this.colecaoDeHospedes = listaDeHospedes;
		this.colecaoDeContratos = listaContratos;

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

		panel.setLayout(layoutPainel);

		//PAINEL RELATORIO HOSPEDE
		PanelHospedes = new JPanel();
		PanelHospedes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(PanelHospedes, "hospedes");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//INFO HOSPEDES
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

		JLabel lblHospedesSem = new JLabel("Hóspedes (Sem contrato): ");
		lblHospedesSem.setToolTipText("Hóspedes sem contrato.");
		lblHospedesSem.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblSemConHospedes = new JLabel("");
		lblSemConHospedes.setText("" + colecaoDeHospedes.pesquisaHospedeContrato(null).size());
		lblSemConHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//INFO OPINIAO
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
														.addContainerGap()
														.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE))
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
																																.addComponent(lblNumHospedes)))
																																.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
																																						.addGap(20)
																																						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
																																						.addGap(10))
				);

		JScrollPane scrollPaneHospedes = new JScrollPane();
		tabbedPane.addTab("Hóspedes", null, scrollPaneHospedes, null);

		tableHospede = new JTable();
		tableHospede.setRowSelectionAllowed(true);
		scrollPaneHospedes.setColumnHeaderView(tableHospede);
		escreveTabelaHospede();
		scrollPaneHospedes.setViewportView(tableHospede);
		scrollPaneHospedes.setRowHeaderView(table);

		JScrollPane scrollPaneOpinioes = new JScrollPane();
		tabbedPane.addTab("Opiniões", null, scrollPaneOpinioes, null);

		tableOpiniao = new JTable();
		tableOpiniao.setRowSelectionAllowed(true);
		scrollPaneOpinioes.setColumnHeaderView(tableOpiniao);
		escreveTabelaOpiniao();
		scrollPaneOpinioes.setViewportView(tableOpiniao);
		scrollPaneOpinioes.setRowHeaderView(table);

		PanelHospedes.setLayout(gl_PanelHospedes);

		PanelContratos = new JPanel();
		PanelContratos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(PanelContratos, "contratos");
		
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
		
		lblMedHospedeCon = new JLabel("New label");
		lblMedHospedeCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMaiorDiriaRegistrada = new JLabel("Maior diária registrada: ");
		lblMaiorDiriaRegistrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMaiorDiaria = new JLabel("New label");
		lblMaiorDiaria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMdiaDeDirias = new JLabel("Média de diárias p/Contrato: ");
		lblMdiaDeDirias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMedDiarias = new JLabel("New label");
		lblMedDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_PanelContratos = new GroupLayout(PanelContratos);
		gl_PanelContratos.setHorizontalGroup(
			gl_PanelContratos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addGap(69)
					.addComponent(lblContrato)
					.addPreferredGap(ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
					.addComponent(lblHospede)
					.addGap(88))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMaiorDiriaRegistrada)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMaiorDiaria)
					.addContainerGap(504, Short.MAX_VALUE))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotalDeContratos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNumContratos))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblContratosAbertos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContratoAbe)))
					.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addComponent(lblMdiaPcontrato)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMedHospedeCon))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addComponent(lblMdiaDeDirias)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMedDiarias)))
					.addGap(16))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblContratosFechados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContratoFec))
						.addGroup(gl_PanelContratos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblContratosReservados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContratoRese)))
					.addContainerGap(496, Short.MAX_VALUE))
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_PanelContratos.setVerticalGroup(
			gl_PanelContratos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelContratos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrato)
						.addComponent(lblHospede))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalDeContratos)
						.addComponent(lblNumContratos)
						.addComponent(lblMdiaPcontrato)
						.addComponent(lblMedHospedeCon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelContratos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContratosAbertos)
						.addComponent(lblContratoAbe)
						.addComponent(lblMdiaDeDirias)
						.addComponent(lblMedDiarias))
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
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableContrato = new JTable();
		scrollPane.setColumnHeaderView(tableContrato);
		PanelContratos.setLayout(gl_PanelContratos);

		PanelServicos = new JPanel();
		panel.add(PanelServicos, "servicos");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
										.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnHospedes, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(btnContratos, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(btnServicos, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
												.addGap(351))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnHospedes)
								.addComponent(btnContratos)
								.addComponent(btnServicos))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);
	}

	//TABELA HOSPEDES
	private void escreveTabelaHospede(){
		Collections.sort(colecaoDeHospedes.getListaHospedes());
		Object[][] designTabela = new Object[colecaoDeHospedes.getListaHospedes().size()][4];
		for (int i = 0; i < colecaoDeHospedes.getListaHospedes().size(); i++){
			Hospede hospedeAtual = colecaoDeHospedes.getListaHospedes().get(i);
			if (hospedeAtual.getNome() == null){
				designTabela[i][0] = "Não especificado";
			}else{
				designTabela[i][0] = hospedeAtual.getNome();
			}
			String dataFormatadaNascimento = "";
			try{
				dataFormatadaNascimento = Main.converteParaString(hospedeAtual.getDataNascimento());
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			designTabela[i][1] = dataFormatadaNascimento;
			if (hospedeAtual.getCpf() == null){
				designTabela[i][2] = "Não especificado";
			}else{
				designTabela[i][2] = hospedeAtual.getCpf();
			}
			if (hospedeAtual.getContratoLigado() == null){
				designTabela[i][3] = "Sem contrato";
			}else{
				designTabela[i][3] = hospedeAtual.getContratoLigado().getStatus();
			}
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Nome", "Nascimento", "CPF", "Contrato"
		}) {

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
		for (Hospede h: colecaoDeHospedes.getListaHospedes()) {
			if (h.getOpiniao() != null) {
				hospedesOpiniao.add(h);
			}
		}

		Object[][] designTabela = new Object[hospedesOpiniao.size()][2];
		for (int i = 0; i < hospedesOpiniao.size(); i++){
			Hospede hospedeAtual = hospedesOpiniao.get(i);
			designTabela[i][0] = hospedeAtual.getOpiniao().getComentario();
			designTabela[i][1] = hospedeAtual.getOpiniao().getNota();
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Comentário", "Nota"
		}) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableOpiniao.setModel(modeloTabela);
	}
}
