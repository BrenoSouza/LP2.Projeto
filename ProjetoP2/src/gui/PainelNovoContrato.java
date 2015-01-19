package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import classes.Hospede;
import classes.Quarto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class PainelNovoContrato extends JInternalFrame {
	private JComboBox comboBoxSelecaoHospede;
	private JLabel lblSelecaoHospede;
	private List<Hospede> listaHospedes;
	private List<Hospede> listaHospedesSemContrato;
	private List<Quarto> listaQuartosDisponiveis;
	private Hospede hospedeSelecionado = null;

	

	/**
	 * Create the frame.
	 */
	public PainelNovoContrato(List<Hospede> listaHospedes, List<Quarto> listaQuartosDisponiveis) {
		setResizable(true);
		setClosable(true);
		setBounds(0, 0, 650, 280);
		this.listaHospedes = listaHospedes;
		this.listaQuartosDisponiveis = listaQuartosDisponiveis;
		lblSelecaoHospede = new JLabel("Hóspede Principal:");
		lblSelecaoHospede.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaHospedesSemContrato = new ArrayList<Hospede>();
		for (int i = 0; i < listaHospedes.size(); i++){
			if (listaHospedes.get(i).getContratoLigado() == null){
				listaHospedesSemContrato.add(listaHospedes.get(i));
			}
		}
		String[] nomesHospedes = new String[listaHospedesSemContrato.size() + 1]; // Criando a lista com os nomes dos hóspedes para serem escolhidos.
		nomesHospedes[0] = "-- SELECIONE UM HÓSPEDE --"; // Criando uma mensagem para ser a de primeiro índice.
		for (int i = 0; i < listaHospedesSemContrato.size(); i++){
			nomesHospedes[i + 1] = (listaHospedesSemContrato.get(i)).getNome();
		}
		comboBoxSelecaoHospede = new JComboBox(nomesHospedes);
		comboBoxSelecaoHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxSelecaoHospede.getSelectedIndex() == 0){
					hospedeSelecionado = null;
				}else{
					hospedeSelecionado = listaHospedesSemContrato.get(comboBoxSelecaoHospede.getSelectedIndex() - 1); // -1 pq os hóspedes de verdade começam no índice 1 da comboBox
				}
			}
		});
		comboBoxSelecaoHospede.setSelectedIndex(0);
		comboBoxSelecaoHospede.setEditable(false); // Para não se poder editar os valores da comboBox.
		
		JButton btnCriarNovo = new JButton("Criar novo hóspede");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTabelaClientes = new JLabel("Clientes sem contrato vinculado:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCriarNovo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelecaoHospede)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxSelecaoHospede, 0, 187, Short.MAX_VALUE)
							.addGap(310))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTabelaClientes)
							.addContainerGap(578, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelecaoHospede)
						.addComponent(comboBoxSelecaoHospede, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTabelaClientes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCriarNovo)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
