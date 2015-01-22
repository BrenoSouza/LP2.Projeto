package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class PainelEditarCliente extends JInternalFrame {
	
	/**
	 * Create the frame.
	 */
	public PainelEditarCliente() {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
			}
		});
		setBounds(100, 100, 559, 353);
		
		JLabel lblOpinio = new JLabel("Opinião");
		lblOpinio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblComentrio = new JLabel("Comentário:");
		lblComentrio.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblComment = new JLabel("New label");
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblScore = new JLabel("New label");
		
		JButton btnEditar = new JButton("Editar");
		
		JLabel lblContrato = new JLabel("Contrato");
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContratoLigado = new JLabel("New label");
		
		JLabel lblHospedePrincipal = new JLabel("Hospede Principal:");
		lblHospedePrincipal.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblHospedePrin = new JLabel("New label");
		
		JLabel lblInformaesPessoais = new JLabel("Informações Pessoais");
		lblInformaesPessoais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblName = new JLabel("New label");
		
		JLabel lblBirth = new JLabel("New label");
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblAge = new JLabel("New label");
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblCpfHospede = new JLabel("New Label");
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JButton btnEditar_1 = new JButton("Editar");
		
		JButton btnNewButton = new JButton("Editar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblComentrio, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblComment, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOpinio, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContrato)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditar_1))
						.addComponent(lblContratoLigado)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCpfHospede, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBirth)
							.addGap(31)
							.addComponent(lblIdade, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAge))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNota, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblScore)
									.addGap(24))
								.addComponent(lblHospedePrincipal, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHospedePrin))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInformaesPessoais)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOpinio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComentrio)
						.addComponent(lblComment))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNota)
						.addComponent(lblScore))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrato)
						.addComponent(btnEditar_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContratoLigado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedePrincipal)
						.addComponent(lblHospedePrin))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInformaesPessoais)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(lblBirth)
						.addComponent(lblIdade)
						.addComponent(lblAge))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(lblCpfHospede))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblEndereo))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
