package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelServicos extends JInternalFrame {
	
	private JTable tableServicos;
	private JTable table;
	private JScrollPane scrollPane = new JScrollPane();
	private Contrato contratoSelecionado;
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
		
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelServicos.class.getResource("/resources/servicos_icon.png")));
		setTitle("Servi\u00E7os");
		setClosable(true);
		setBounds(50, 0, 752, 450);
		
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
				"Serviços", "Descrição", "Preço"	})     {

			@Override
		    public boolean isCellEditable(int row, int column) {
		        //Esse m�todo pegaria um �ndice para ver se o usu�rio pode editar certa parte da tabela. Como n�o � necess�rio no nosso uso, ele sempre vai retornar false
		        return false;
		    }
		};
		
		scrollPane.setViewportView(tableServicos);
		
		
		scrollPane.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
}
