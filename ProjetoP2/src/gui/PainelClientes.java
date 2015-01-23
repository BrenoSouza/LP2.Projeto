package gui;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import classes.Contrato;
import classes.Hospede;
import colecoes.ColecaoDeHospedes;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PainelClientes extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private ColecaoDeHospedes listaDeHospedes;
	private JButton btnRemover;


	public PainelClientes(ColecaoDeHospedes listaDeHospedes, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				escreveTabela();
			}
		});
		this.painelPrincipal = painelPrincipal;
		this.listaDeHospedes = listaDeHospedes;
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
					painelNovo = new PainelCadastroClientes(getListaDeHospedes());
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
					getListaDeHospedes().removeHospede(hospedeSelecionado);
				}
				escreveTabela();
			}
		});
		btnRemover.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(114)
							.addComponent(btnRemover)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEditar)
							.addGap(150)
							.addComponent(btnNovo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizar)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnRemover))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}

	public ColecaoDeHospedes getListaDeHospedes() {
		return listaDeHospedes;
	}

	private void setHospedeSelecionado(int indice){
		hospedeSelecionado = getListaDeHospedes().getListaHospedes().get(indice);
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
		Object[][] designTabela = new Object[listaDeHospedes.getListaHospedeTamanho()][5];
		for (int i = 0; i < listaDeHospedes.getListaHospedeTamanho(); i++){
			Hospede hospedeAtual = listaDeHospedes.getIndice(i);
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
