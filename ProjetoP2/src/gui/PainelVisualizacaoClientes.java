package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import core.Contrato;
import core.Hospede;

public class PainelVisualizacaoClientes extends JInternalFrame {
	
	private static final long serialVersionUID = -1825398526147468296L;
	private PainelVisualizacaoContrato painelContrato;
	private JButton btnVisualizarContrato;
	private JDesktopPane painelPrincipal;
	private Contrato contrato;
	private Hospede hospede;


	public PainelVisualizacaoClientes(Hospede hospede, JDesktopPane painelPrincipal) {
		setFrameIcon(new ImageIcon(PainelVisualizacaoClientes.class.getResource("/resources/clientes_icon.png")));
		setTitle("Cliente - Informações");
		addInternalFrameListener(new InternalFrameAdapter() {
		  //Atualiza o painel quando ele é ativado
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			  //Atualiza quais botoes vao ficar habilitados e quais vao ficar desabilitados
				atualizaBotoes();
			}
		});
		setClosable(true);
		setBounds(100, 100, 450, 333);
		
		contrato = hospede.getContratoLigado();
		this.hospede = hospede;
		this.painelPrincipal = painelPrincipal;
		
		//JLabel com as informacoes do hospede
		JLabel lblInformaes = new JLabel("Informações:");
		lblInformaes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblToStringHospede = new JLabel("");
		String texto = "<html>" + hospede.toString() + "</html>"; //Envolver a string em tags <html> para pular linha
		texto = texto.replaceAll( System.getProperty("line.separator"), "<br> <br>");
		lblToStringHospede.setText(texto);
		lblToStringHospede.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblContrato = new JLabel("Contrato:");
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblContratoCliente = new JLabel("");
		String texto2 = (hospede.getContratoLigado() != null) ? "Está em um contrato." : "Não está em um contrato.";
		lblContratoCliente.setText(texto2);
		lblContratoCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//Botao para visualizar o contrato que o hospede esta, caso o hospede nao tenha um contrato o botao fica desabilitado.
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
						.addComponent(lblToStringHospede, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addComponent(lblContrato)
						.addComponent(btnVisualizarContrato)
						.addComponent(lblContratoCliente, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInformaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToStringHospede)
					.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
					.addComponent(lblContrato)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContratoCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVisualizarContrato)
					.addGap(45))
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
