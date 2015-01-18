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

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelServicos extends JInternalFrame {
	
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable tableServicos;
	private JTable table;
	private Contrato contratoSelecionado = null;
	private List<Contrato> listaContratos;
	private JDesktopPane painelPrincipal;


	/**
	 * Create the frame.
	 */
	public PainelServicos(List<Contrato> listaContratos, JDesktopPane painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
		try{
		Contrato teste = new Contrato(new ArrayList<Quarto>(), new ArrayList<Hospede>(), 5);
		listaContratos.add(teste);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		this.listaContratos = listaContratos;
		
		//teste
		contratoSelecionado = listaContratos.get(0);
		
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Servi\u00E7os");
		setClosable(true);
		setBounds(50, 0, 752, 450);
		getContentPane().setLayout(null);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(120))
		);
		
		//CONSTRUCAO DA TABELA
		
		tableServicos = new JTable();
		
		Object [][] designTabela = new Object[contratoSelecionado.getListaServicos().size()][3];
		
		for (int i = 0; i < contratoSelecionado.getListaServicos().size(); i++) {
			
			// 			
		}
		
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

		
		scrollPane.setViewportView(tableServicos);
		
		
		scrollPane.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	public void setServicoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro m�todo, posso usar vari�veis n�o finais a vontade sem problema.
		contratoSelecionado = listaContratos.get(indice); // Lembrando que a tabela est� na mesma ordem que a listaContratos, ent�o os �ndices s�o os mesmos.
	}
	public void atualizaBotoes(){
		if (contratoSelecionado == null){
			//btnEditar.setEnabled(false);
			//btnVisualizar.setEnabled(false);
		}else{
			//btnEditar.setEnabled(true);
			//btnVisualizar.setEnabled(true);
		}
	}
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	public JDesktopPane getPainelPrincipal(){
		return painelPrincipal;
	}
}
