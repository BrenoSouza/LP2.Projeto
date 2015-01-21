package gui;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;

import classes.AluguelCarro;
import classes.Contrato;
import classes.Hospede;
import classes.Servico;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelVisualizacaoContrato extends JInternalFrame {
	private JTabbedPane painelTabas;
	private Contrato contrato;
	private List<Hospede> listaHospedes;
	private List<Servico> listaServicos;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabelServicosAssociados;
	private JTable tabelaServicos;
	private JDesktopPane painelPrincipal;
	private Servico servicoSelecionado = null;
	private JButton btnVisualizar;
	private JButton btnEditar;
	private PainelVisualizacaoServico painelVisualizacaoServico;
	


	public PainelVisualizacaoContrato(Contrato contrato, JDesktopPane painelPrincipal) {
		setClosable(true);
		this.painelPrincipal = painelPrincipal;
		setBounds(0, 50, 800, 400);
		this.contrato = contrato;
		listaHospedes = contrato.getListaHospedes();
		listaServicos = contrato.getListaServicos();
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
		if (contrato.getHospedePrincipal() == null){
			lblHospedePrincipalVariavel.setText("Não definido");
		}else{
			lblHospedePrincipalVariavel.setText(contrato.getHospedePrincipal().getNome());
		}
		lblHospedePrincipalVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataCheckIn = new JLabel("Data de Check-In:");
		lblDataCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDataCheckInVariavel = new JLabel("New label");
		try{
			lblDataCheckInVariavel.setText(Main.converteParaString(contrato.getDataCheckIn()));
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		lblDataCheckInVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDataMarcadaCheckOut = new JLabel("Data marcada para Check-Out:");
		lblDataMarcadaCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDataMarcadaCheckOutVariavel = new JLabel("New label");
		try{
			lblDataMarcadaCheckOutVariavel.setText(Main.converteParaString(contrato.getDataCheckOut()));
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		lblDataMarcadaCheckOutVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTotalASerPago = new JLabel("Total a ser pago:");
		lblTotalASerPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTotalASerPagoVariavel = new JLabel("New label");
		lblTotalASerPagoVariavel.setText(Double.toString(contrato.calculaPrecoFinal()));
		lblTotalASerPagoVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHospedesRegistrados = new JLabel("H\u00F3spedes registrados:");
		lblHospedesRegistrados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		JTable tabelaHospedes = new JTable();
		try{
		contrato.getListaServicos().add(new AluguelCarro(5, true, false, true));} catch (Exception e){ JOptionPane.showMessageDialog(null, e.getMessage());}
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
		
		scrollPane_1 = new JScrollPane();
		
		lblNewLabelServicosAssociados = new JLabel("Servi\u00E7os associados ao contrato:");
		lblNewLabelServicosAssociados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelVisualizacaoServico = new PainelVisualizacaoServico(servicoSelecionado);
				getPainelPrincipal().add(painelVisualizacaoServico);
				painelVisualizacaoServico.show();
			}
		});
		btnVisualizar.setEnabled(false);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		GroupLayout gl_panelServicos = new GroupLayout(panelServicos);
		gl_panelServicos.setHorizontalGroup(
			gl_panelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServicos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelServicos.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
						.addComponent(lblNewLabelServicosAssociados)
						.addGroup(gl_panelServicos.createSequentialGroup()
							.addComponent(btnVisualizar)
							.addPreferredGap(ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
							.addComponent(btnEditar)))
					.addContainerGap())
		);
		gl_panelServicos.setVerticalGroup(
			gl_panelServicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServicos.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabelServicosAssociados)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelServicos.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizar)
						.addComponent(btnEditar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tabelaServicos = new JTable();
		//INICIO DA CONSTRU��O DE TABELA
		Object [][] tabelaServicosDesign = new Object [contrato.getListaServicos().size()][4];
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
		//FIM DE CONSTRUÇÃO DE TABELA
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
		ListSelectionModel modeloSelecaoLinha = tabelaServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
		
		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			//Necessita ser esse nome de método para funcionar
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tabelaServicos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
				if (indiceSelecionado.length <= 0){
					servicoSelecionado = null;
				}else{
					// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
					setServicoSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();
				
			}
		});
//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		tabelaServicos.setModel(modeloTabelaServicos);
		tabelaServicos.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tabelaServicos);
		panelServicos.setLayout(gl_panelServicos);
		getContentPane().setLayout(groupLayout);

	}
	public void setServicoSelecionado(int i){
		servicoSelecionado = listaServicos.get(i);
	}
	public void atualizaBotoes(){
		if (servicoSelecionado == null){
			btnVisualizar.setEnabled(false);
			btnEditar.setEnabled(false);
		}else{
			btnVisualizar.setEnabled(true);
			btnEditar.setEnabled(true);
		}
	}
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
