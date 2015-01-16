package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Contrato;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class PainelContratos extends JInternalFrame {
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private JTable tableContratos;
	private List<Contrato> listaContratos = new ArrayList<Contrato>();


	/**
	 * Create the frame.
	 */
	public PainelContratos(List<Contrato> listaContratos) {
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
		Object[][] designTabela = new Object[listaContratos.size()][];
		tableContratos.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPanePrincipal.setViewportView(tableContratos);
		getContentPane().setLayout(groupLayout);

	}
}
