package gui;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;

import classes.Contrato;
import classes.Hospede;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class PainelVisualizacaoContrato extends JInternalFrame {
	private JTabbedPane painelTabas;
	private Contrato contrato;
	private JTable table;
	private List<Hospede> listaHospedes;
	


	public PainelVisualizacaoContrato(Contrato contrato) {
		setClosable(true);
		setBounds(100, 100, 800, 400);
		this.contrato = contrato;
		listaHospedes = contrato.getListaHospedes();
		try{
		listaHospedes.add(new Hospede("zé buceta","buceta", "1111111", Calendar.getInstance()));} catch (Exception e){ }
		painelTabas = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelTabas, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelTabas, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panelDetalhes = new JPanel();
		painelTabas.addTab("Detalhes", null, panelDetalhes, null);
		
		JLabel lblHospedePrincipal = new JLabel("H\u00F3spede Principal:");
		lblHospedePrincipal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblHospedePrincipalVariavel = new JLabel("New label");
		lblHospedePrincipalVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataCheckIn = new JLabel("Data de Check-In:");
		lblDataCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDataCheckInVariavel = new JLabel("New label");
		lblDataCheckInVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataMarcadaCheckOut = new JLabel("Data marcada para Check-Out:");
		lblDataMarcadaCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDataMarcadaCheckOutVariavel = new JLabel("New label");
		lblDataMarcadaCheckOutVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTotalASerPago = new JLabel("Total a ser pago:");
		lblTotalASerPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTotalASerPagoVariavel = new JLabel("New label");
		lblTotalASerPagoVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHospedesRegistrados = new JLabel("H\u00F3spedes registrados:");
		lblHospedesRegistrados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		JTable tabelaHospedes = new JTable();		
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
							LocalDate diaNascimento = LocalDate.of(nascimento.get(nascimento.YEAR), nascimento.get(nascimento.MONTH) + 1, nascimento.get(nascimento.DAY_OF_MONTH));
							Period periodoDeTempo = Period.between(diaNascimento, presente);
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
				scrollPane.setViewportView(tabelaHospedes);
		scrollPane.setViewportView(table);
		GroupLayout gl_panelDetalhes = new GroupLayout(panelDetalhes);
		gl_panelDetalhes.setHorizontalGroup(
			gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalhes.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addComponent(lblHospedePrincipal)
							.addGap(10)
							.addComponent(lblHospedePrincipalVariavel))
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addComponent(lblDataCheckIn)
							.addGap(6)
							.addComponent(lblDataCheckInVariavel))
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addComponent(lblDataMarcadaCheckOut)
							.addGap(10)
							.addComponent(lblDataMarcadaCheckOutVariavel))
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addComponent(lblTotalASerPago)
							.addGap(10)
							.addComponent(lblTotalASerPagoVariavel))
						.addComponent(lblHospedesRegistrados)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 739, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panelDetalhes.setVerticalGroup(
			gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalhes.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedePrincipal)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHospedePrincipalVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataCheckIn)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblDataCheckInVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataMarcadaCheckOut)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblDataMarcadaCheckOutVariavel)))
					.addGap(6)
					.addGroup(gl_panelDetalhes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalASerPago)
						.addGroup(gl_panelDetalhes.createSequentialGroup()
							.addGap(1)
							.addComponent(lblTotalASerPagoVariavel)))
					.addGap(18)
					.addComponent(lblHospedesRegistrados)
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
		);
		
		
		panelDetalhes.setLayout(gl_panelDetalhes);
		JPanel panelServicos = new JPanel();
		painelTabas.addTab("Servi\u00E7os", null, panelServicos, null);
		getContentPane().setLayout(groupLayout);

	}
}
