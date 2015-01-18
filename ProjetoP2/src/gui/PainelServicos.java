package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;
import classes.Servico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PainelServicos extends JInternalFrame {
	
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable tableServicos;
	private JTable table;
	private Contrato contratoSelecionado = null;
	private Servico servicoSelecionado;
	private List<Contrato> listaContratos;
	private List<Hospede> listaHospedes = new ArrayList();
	private List<Hospede> listaHospedes2 = new ArrayList();
	private JDesktopPane painelPrincipal;
	private PainelVisualizacaoServico painelVisualizacao;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JButton btnVisualizar;
	private JComboBox comboBox;
	private String[] nomesHospedes;
	
	/**
	 * Create the frame.
	 */
	
	public PainelServicos(List<Contrato> listaContratos, JDesktopPane painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
		try{
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(Calendar.YEAR, 1990);
			listaHospedes.add(new Hospede("Fulano de Tal","Casa do Fulano", "111111111-11", dataNascimento));
			listaHospedes2.add(new Hospede("Cicrano de Tal","Casa do Fulano", "111111111-11", dataNascimento));
			listaContratos.add(new Contrato(new ArrayList<Quarto>(), listaHospedes, 5));
			listaContratos.add(new Contrato(new ArrayList<Quarto>(), listaHospedes2, 5));
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		this.listaContratos = listaContratos;		

		comboBox = new JComboBox<String>();

		if (listaContratos.size()!= 0){
			for (int i = 0; i < listaContratos.size(); i++) {
				//comboBox.insertItemAt("Breno", 0);
				comboBox.insertItemAt(listaContratos.get(i).getListaHospedes().get(0).getNome(), i);
			}
		}
		
		
		//teste
		contratoSelecionado = listaContratos.get(0);
		
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Servi\u00E7os");
		setClosable(true);
		setBounds(0, 0, 752, 450);
		getContentPane().setLayout(null);
		
		btnAdicionar = new JButton("Adicionar");
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		
		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelVisualizacao = new PainelVisualizacaoServico(servicoSelecionado);
				adicionaNoPainel(painelVisualizacao);
				painelVisualizacao.show();
				
			}
		});
		btnVisualizar.setEnabled(false);
		
		JLabel lblSelecionarContrato = new JLabel("Selecionar Contrato: ");
		
		JLabel lblServiosContratados = new JLabel("Serviços Contratados");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(btnAdicionar)
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addComponent(btnVisualizar)
					.addGap(79)
					.addComponent(btnAtualizar)
					.addGap(76)
					.addComponent(btnRemover)
					.addGap(61))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelecionarContrato)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(377, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(291)
					.addComponent(lblServiosContratados)
					.addContainerGap(299, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelecionarContrato)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addComponent(lblServiosContratados)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemover)
						.addComponent(btnAtualizar)
						.addComponent(btnVisualizar)
						.addComponent(btnAdicionar))
					.addGap(43))
		);
		
		//CONSTRUCAO DA TABELA
		
		tableServicos = new JTable();
		
		Object [][] designTabela = new Object[contratoSelecionado.getListaServicos().size()][3];
		
		for (int i = 0; i < contratoSelecionado.getListaServicos().size(); i++) {
		
						
		}
		
		//GAMBIARRA
		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
				"Serviços", "Descrição", "Preço" })     {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse m�todo pegaria um �ndice para ver se o usu�rio pode editar certa parte da tabela. Como n�o � necess�rio no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};
		
		tableServicos.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'

		tableServicos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e n�o s� uma c�lula
		//CRIANDO UMA A��O PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableServicos.getSelectionModel(); // SINGLE_SELECTION = Selecionar s� uma op��o de vez
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de m�todo para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableServicos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os �ndices da lista dos objetos selecionados. Como nessa tabela s� se seleciona uma op��o de cada vez, sempre ter� s� um elemento essa array.
						if (indiceSelecionado.length <= 0){
							servicoSelecionado = null;
						}else{
							// Aqui � uma gambiarra mais complicada: java n�o permite que eu use o listaContratos (ou qualquer outra vari�vel n�o final) dentro de um m�todo do construtor, como � esse. Para solucionar isso, optei pela gambiarra de s� usar esse �ndice em um m�todo fora do construtor, setContratoSelecionado, que consegue usar as vari�veis sem problemas.
							setServicoSelecionado(indiceSelecionado[0]);
						}atualizaBotoes();
						
					}
				});
		
		scrollPane.setViewportView(tableServicos);
		
		
		scrollPane.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setServicoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro m�todo, posso usar vari�veis n�o finais a vontade sem problema.
		servicoSelecionado = contratoSelecionado.getListaServicos().get(indice); // Lembrando que a tabela est� na mesma ordem que a listaContratos, ent�o os �ndices s�o os mesmos.
	}
	public void atualizaBotoes(){
		if (contratoSelecionado == null){
			btnRemover.setEnabled(false);
			btnAtualizar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}else{
			btnRemover.setEnabled(true);
			btnAtualizar.setEnabled(true);
			btnVisualizar.setEnabled(true);
		}
	}
	
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
