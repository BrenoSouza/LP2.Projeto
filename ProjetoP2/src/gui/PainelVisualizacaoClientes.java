package gui;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import classes.Contrato;
import classes.Hospede;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PainelVisualizacaoClientes extends JInternalFrame {
	private PainelVisualizacaoContrato painelContrato;
	private JButton btnVisualizarContrato;
	private JDesktopPane painelPrincipal;
	private Contrato contrato;
	private Hospede hospede;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public PainelVisualizacaoClientes(Hospede hospede, JDesktopPane painelPrincipal) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				atualizaBotoes();
			}
		});
		setClosable(true);
		setBounds(100, 100, 450, 300);
		contrato = hospede.getContratoLigado();
		this.hospede = hospede;
		
		JLabel lblInformaes = new JLabel("Informações:");
		lblInformaes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblToStringHospede = new JLabel("");
		String texto = "<html>" + hospede.toString() + "</html>"; //Envolver a string em tags <html>
		texto = texto.replaceAll("\n", "<br> <br>");
		lblToStringHospede.setText(texto);
		lblToStringHospede.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblContrato = new JLabel("Contrato:");
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblContratoCliente = new JLabel("");
		String texto2 = (hospede.getContratoLigado() != null) ? "Está em um contrato." : "Não está em um contrato.";
		lblContratoCliente.setText(texto2);
		lblContratoCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnVisualizarContrato = new JButton("Visualizar contrato");
		btnVisualizarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelContrato = new PainelVisualizacaoContrato(contrato, getPainelPrincipal());
				adicionaNoPainel(painelContrato);
				painelContrato.show();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInformaes, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblToStringHospede, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(lblContrato)
						.addComponent(lblContratoCliente, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVisualizarContrato))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInformaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringHospede)
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addComponent(lblContrato)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContratoCliente)
					.addGap(18)
					.addComponent(btnVisualizarContrato)
					.addGap(33))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	private JDesktopPane getPainelPrincipal() {
		return painelPrincipal;
	}
	
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	
	private void atualizaBotoes(){
		if (this.hospede.getContratoLigado() == null){
			btnVisualizarContrato.setEnabled(false);
		}else{
			btnVisualizarContrato.setEnabled(true);
		}
	}
}
