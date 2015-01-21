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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelClientes extends JInternalFrame {
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private JTable tableHospedes;
	private JTable table;
	private JButton btnVisualizar;
	private JButton btnEditar;
	private JButton btnNovo;
	private JDesktopPane painelPrincipal;
	private PainelCadastroClientes painelNovo;
	private Hospede hospedeSelecionado;


	public PainelClientes(List<Hospede> listaHospedes, JDesktopPane painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
		this.listaHospedes.addAll(listaHospedes);
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
		btnVisualizar.setEnabled(false);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					painelNovo = new PainelCadastroClientes();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				adicionaNoPainel(painelNovo);
				painelNovo.show();
			}
		});
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 716, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(228)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNovo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);


		//DateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
		//DateFormatter df = new DateFormatter(formatoData);

		//MaskFormatter mascara = new MaskFormatter("###.###.###-##");
		//mascara.setPlaceholderCharacter('_');

	}

	private void setHospedeSelecionado(int indice){
		hospedeSelecionado = listaHospedes.get(indice);
	}

	private void atualizaBotoes(){
		if (hospedeSelecionado == null){
			btnEditar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}else{
			btnEditar.setEnabled(true);
			btnVisualizar.setEnabled(true);
		}
	}

	private void escreveTabela(){
		Object[][] designTabela = new Object[listaHospedes.size()][5];
		for (int i = 0; i < listaHospedes.size(); i++){
			Hospede hospedeAtual = listaHospedes.get(i);
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
			designTabela[i][2] = hospedeAtual.getCpf();
			designTabela[i][3] = hospedeAtual.getContratoLigado();
			designTabela[i][4] = hospedeAtual.getNome();
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
