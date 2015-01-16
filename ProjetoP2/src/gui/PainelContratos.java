package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;

public class PainelContratos extends JInternalFrame {
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private JTable tableContratos;
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	private final SimpleDateFormat formatoData = new SimpleDateFormat("dd/mm/yyyy");
	private JTable table;


	/**
	 * Create the frame.
	 */
	public PainelContratos(List<Contrato> listaContratos){
		try{
		Contrato teste = new Contrato(new ArrayList<Quarto>(), new ArrayList<Hospede>(), 5, Calendar.getInstance());
		listaContratos.add(teste);
		JOptionPane.showMessageDialog(null, Main.converteParaString(Calendar.getInstance()));
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		this.listaContratos.addAll(listaContratos);
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelContratos.class.getResource("/resources/contrato_icon.png")));
		setTitle("Contratos");
		setClosable(true);
		setBounds(100, 0, 752, 450);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10)
						.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 716, GroupLayout.PREFERRED_SIZE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(11)
						.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
				);

		tableContratos = new JTable();
		// IN�CIO DE CONSTRU��O DA TABELA
				Object[][] designTabela = new Object[listaContratos.size()][5];
				for (int i = 0; i < listaContratos.size(); i++){
					Contrato contratoAtual = listaContratos.get(i);
					// Para preencher a primeira linha da tabela, com o nome do H�spede Principal.
					if (contratoAtual.getHospedePrincipal() == null){
						designTabela[i][0] = "N�o especificado";
					}else{
						designTabela[i][0] = contratoAtual.getHospedePrincipal().getNome();
					}
					// Para conseguir uma String formatada com a data do checkin, atrav�s de um m�todo na classe Main.
					String dataFormatadaCheckIn = "";
					try{
						dataFormatadaCheckIn = Main.converteParaString(contratoAtual.getDataCheckIn());
					}catch (Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					designTabela[i][1] = dataFormatadaCheckIn;
					// Para conseguir uma String formatada com a estimada data do checkout.
					String dataFormatadaCheckOut = "";
					try{
						dataFormatadaCheckOut = Main.converteParaString(contratoAtual.getDataCheckOut());
					}catch (Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					designTabela[i][2] = dataFormatadaCheckOut;
					// Para colocar na tabela o total de despesas do contrato.
					designTabela[i][3] = contratoAtual.calculaPrecoFinal();
					// Para colocar na tabela o status do contrato.
					designTabela[i][4] = contratoAtual.getStatus();
			// FIM DE CONSTRU��O DE TABELA.
			
		}
		tableContratos.setModel(new DefaultTableModel(designTabela,	new String[] {
				"H�spede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
			}));
		scrollPanePrincipal.setViewportView(tableContratos);
		
		
		scrollPanePrincipal.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
}
