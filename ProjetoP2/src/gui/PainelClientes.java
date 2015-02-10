package gui;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import core.Hospede;
import core.colecoes.ColecaoDeHospedes;

public class PainelClientes extends JInternalFrame {

	private static final long serialVersionUID = 1801359681375083303L;
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private JTable tableHospedes;
	private JTable table;
	private JButton btnVisualizar;
	private JButton btnEditar;
	private JButton btnNovo;
	private JDesktopPane painelPrincipal;
	private PainelCadastroClientes painelNovo;
	private PainelVisualizacaoClientes painelVisualizacao;
	private PainelEditarCliente painelEditar;
	private Hospede hospedeSelecionado;
	private ColecaoDeHospedes colecaoDeHospedes;
	private JButton btnRemover;
	private JTextField textFieldPesquisa;
	private JButton btnCancelaPesquisa;
	private JButton btnPesquisar;
	private List<Hospede> colecaoAtiva = new ArrayList<Hospede>();


	public PainelClientes(ColecaoDeHospedes listaDeHospedes, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabela();
			}
		});
		this.painelPrincipal = painelPrincipal;
		this.colecaoDeHospedes = listaDeHospedes;
		this.colecaoAtiva = getColecaoDeHospedes().getListaHospedes();

		setResizable(true);
		setFrameIcon(new ImageIcon(PainelClientes.class.getResource("/resources/clientes_icon.png")));
		setTitle("Clientes");
		setClosable(true);
		setBounds(0, 0, 752, 450);

		tableHospedes = new JTable();
		escreveTabela();

		tableHospedes.setRowSelectionAllowed(true);
		ListSelectionModel modeloSelecaoLinha = tableHospedes.getSelectionModel();

		modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] indiceSelecionado = tableHospedes.getSelectedRows();
				if (indiceSelecionado.length <= 0){
					hospedeSelecionado = null;
				}else{
					setHospedeSelecionado(indiceSelecionado[0]);
				}atualizaBotoes();

			}
		});

		scrollPanePrincipal.setViewportView(tableHospedes);
		scrollPanePrincipal.setRowHeaderView(table);

		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelVisualizacao = new PainelVisualizacaoClientes(hospedeSelecionado, getPainelPrincipal());
				adicionaNoPainel(painelVisualizacao);
				painelVisualizacao.show();
			}
		});
		btnVisualizar.setEnabled(false);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					painelNovo = new PainelCadastroClientes(getColecaoDeHospedes());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				adicionaNoPainel(painelNovo);
				painelNovo.show();
			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					painelEditar = new PainelEditarCliente(hospedeSelecionado, getPainelPrincipal());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				adicionaNoPainel(painelEditar);
				painelEditar.show();
			}
		});
		btnEditar.setEnabled(false);

		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja remover esse hóspede?", "Deletar hóspede" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
				if (escolha == JOptionPane.YES_OPTION){
					getColecaoDeHospedes().removeHospede(hospedeSelecionado);
					colecaoAtiva.remove(hospedeSelecionado);
					escreveTabela();
				}
			}
		});
		btnRemover.setEnabled(false);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(textFieldPesquisa.getText().isEmpty())) {
					List<Hospede> hospedesPesquisados = new ArrayList<Hospede>();
					for (Hospede hospede: getColecaoDeHospedes().getListaHospedes()){
						if (hospede.getNome().toLowerCase().contains(textFieldPesquisa.getText().toLowerCase())){
							hospedesPesquisados.add(hospede);
						}
					}
					colecaoAtiva = hospedesPesquisados;
					if (hospedesPesquisados.size() == 0){
						JOptionPane.showMessageDialog(null, "Sem resultados.");
						escreveTabela();
					}else{
						JOptionPane.showMessageDialog(null, (hospedesPesquisados.size() >= 2) ? hospedesPesquisados.size() + " hóspedes encontrados." : hospedesPesquisados.size() + " hóspede encontrado.");
						escreveTabela();
					}
					btnCancelaPesquisa.setEnabled(true);
				}
			}
		});
		btnPesquisar.setIcon(new ImageIcon(PainelClientes.class.getResource("/resources/search.png")));
		btnPesquisar.setToolTipText("Pesquisar");

		btnCancelaPesquisa = new JButton("");
		btnCancelaPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldPesquisa.setText("");
				colecaoAtiva = getColecaoDeHospedes().getListaHospedes();
				escreveTabela();
				btnCancelaPesquisa.setEnabled(false);
			}
		});
		btnCancelaPesquisa.setEnabled(false);
		btnCancelaPesquisa.setToolTipText("Cancelar pesquisa( A tabela volta a ter todos os hóspedes).");
		btnCancelaPesquisa.setIcon(new ImageIcon(PainelClientes.class.getResource("/resources/cross.png")));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addGap(114)
												.addComponent(btnRemover)
												.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
												.addComponent(btnEditar)
												.addGap(150)
												.addComponent(btnNovo))
												.addGroup(groupLayout.createSequentialGroup()
														.addContainerGap()
														.addComponent(btnCancelaPesquisa, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
														.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(11)
						.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar)
								.addComponent(btnCancelaPesquisa))
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnVisualizar)
										.addComponent(btnNovo)
										.addComponent(btnEditar)
										.addComponent(btnRemover))
										.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);

	}

	public ColecaoDeHospedes getColecaoDeHospedes() {
		return colecaoDeHospedes;
	}

	private void setHospedeSelecionado(int indice){
		hospedeSelecionado = getColecaoDeHospedes().getListaHospedes().get(indice);
	}


	private void atualizaBotoes(){
		if (hospedeSelecionado == null){
			btnRemover.setEnabled(false);
			btnEditar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}else{
			btnEditar.setEnabled(true);
			btnVisualizar.setEnabled(true);
			btnRemover.setEnabled(true);
		}
	}

	private void escreveTabela(){
		Collections.sort(colecaoAtiva);
		Object[][] designTabela = new Object[colecaoAtiva.size()][5];
		for (int i = 0; i < colecaoAtiva.size(); i++){
			Hospede hospedeAtual = colecaoAtiva.get(i);
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
			if (hospedeAtual.getOpiniao() == null){
				designTabela[i][4] = "Sem opinião";
			}else{
				designTabela[i][4] = hospedeAtual.getOpiniao().getComentario().substring(0, 11) + "..."  + "  |   Nota -> " + hospedeAtual.getOpiniao().getNota();;
			}
		}
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Nome", "Nascimento", "CPF", "Contrato", "Opinião"
		}) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableHospedes.setModel(modeloTabela);
	}

	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}

	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
