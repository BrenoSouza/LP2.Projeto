package gui;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;

import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import classes.Hospede;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelEditarCliente extends JInternalFrame {
	private PainelEditarClienteOpiniao painelEditarOpiniao;
	private JDesktopPane painelPrincipal;
	private Hospede hospede;
	private JLabel lblComment;
	private JLabel lblScore;
	private JLabel lblContratoLigado;
	private JLabel lblHospedePrin;
	private JLabel lblName;
	private JLabel lblBirth;
	private JLabel lblAge;
	private JLabel lblCpfHospede;
	private JLabel lblAdress;
	/**
	 * Create the frame.
	 */
	public PainelEditarCliente(final Hospede hospede, JDesktopPane painelPrincipal) {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				//Opinião
				String Comment = (hospede.getOpiniao() == null) ? "Sem coméntario." : hospede.getOpiniao().getComentario();
				lblComment.setText(Comment);
				String Score = (hospede.getOpiniao() == null) ? "Sem nota." : "" + hospede.getOpiniao().getNota();
				lblScore.setText(Score);
				//Contrato
				String contratoLigado = (hospede.getContratoLigado() != null) ? "Está em um contrato." : "Não está em um contrato.";
				lblContratoLigado.setText(contratoLigado);
				String hospedePrincipal = (hospede.getContratoLigado() == null) ? "Nenhum." : hospede.getContratoLigado().getHospedePrincipal().getNome();
				lblHospedePrin.setText(hospedePrincipal);
				//Informações
				String Name = "" + hospede.getNome();
				lblName.setText(Name);
				String Birth = Main.converteParaString(hospede.getDataNascimento());
				lblBirth.setText(Birth);
				LocalDate presente = LocalDate.now();
				Calendar nascimento = hospede.getDataNascimento();
				LocalDate diaNascimento = LocalDate.of(nascimento.get(Calendar.YEAR), nascimento.get(Calendar.MONTH) + 1, nascimento.get(Calendar.DAY_OF_MONTH));
				Period periodoDeTempo = Period.between(diaNascimento, presente);
				String Age = "" + periodoDeTempo.getYears();
				lblAge.setText(Age);
				String Cpf = "" + hospede.getCpf();
				lblCpfHospede.setText(Cpf);
				String Adress = "" + hospede.getEndereco();
				lblAdress.setText(Adress);
			}
		});
		setBounds(100, 100, 559, 353);
		this.painelPrincipal = painelPrincipal;
		this.hospede = hospede;
		
		JLabel lblOpinio = new JLabel("Opinião");
		lblOpinio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblComentrio = new JLabel("Comentário:");
		lblComentrio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblComment = new JLabel("");
		String Comment = (hospede.getOpiniao() == null) ? "Sem coméntario." : hospede.getOpiniao().getComentario();
		lblComment.setText(Comment);
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblScore = new JLabel("");
		String Score = (hospede.getOpiniao() == null) ? "Sem nota." : "" + hospede.getOpiniao().getNota();
		lblScore.setText(Score);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnEditarOpiniao = new JButton("Editar");
		btnEditarOpiniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelEditarOpiniao = new PainelEditarClienteOpiniao(getHospede());
				adicionaNoPainel(painelEditarOpiniao);
				painelEditarOpiniao.show();
			}
		});
		
		JLabel lblContrato = new JLabel("Contrato");
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblContratoLigado = new JLabel("");
		String contratoLigado = (hospede.getContratoLigado() != null) ? "Está em um contrato." : "Não está em um contrato.";
		lblContratoLigado.setText(contratoLigado);
		lblContratoLigado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblHospedePrincipal = new JLabel("Hospede Principal:");
		lblHospedePrincipal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblHospedePrin = new JLabel("");
		String hospedePrincipal = (hospede.getContratoLigado() == null) ? "Nenhum." : hospede.getContratoLigado().getHospedePrincipal().getNome();
		lblHospedePrin.setText(hospedePrincipal);
		lblHospedePrin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnEditarContrato = new JButton("Editar");
		
		JLabel lblInformaesPessoais = new JLabel("Informações Pessoais");
		lblInformaesPessoais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblName = new JLabel("");
		String Name = "" + hospede.getNome();
		lblName.setText(Name);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblBirth = new JLabel("");
		String Birth = Main.converteParaString(hospede.getDataNascimento());
		lblBirth.setText(Birth);
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblAge = new JLabel("");
		LocalDate presente = LocalDate.now();
		Calendar nascimento = hospede.getDataNascimento();
		LocalDate diaNascimento = LocalDate.of(nascimento.get(Calendar.YEAR), nascimento.get(Calendar.MONTH) + 1, nascimento.get(Calendar.DAY_OF_MONTH));
		Period periodoDeTempo = Period.between(diaNascimento, presente);
		String Age = "" + periodoDeTempo.getYears();
		lblAge.setText(Age);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblCpfHospede = new JLabel("");
		String Cpf = "" + hospede.getCpf();
		lblCpfHospede.setText(Cpf);
		lblCpfHospede.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblAdress = new JLabel("");
		String Adress = "" + hospede.getEndereco();
		lblAdress.setText(Adress);
		lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnEditarInfo = new JButton("Editar");
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
							.addComponent(btnEditarOpiniao))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContrato)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditarContrato))
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
							.addComponent(lblAdress, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInformaesPessoais)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditarInfo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOpinio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditarOpiniao))
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
						.addComponent(btnEditarContrato))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContratoLigado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHospedePrincipal)
						.addComponent(lblHospedePrin))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInformaesPessoais)
						.addComponent(btnEditarInfo))
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
						.addComponent(lblAdress)
						.addComponent(lblEndereo))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	
	public Hospede getHospede() {
		return hospede;
	}
}