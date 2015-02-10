package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import core.Hospede;
import core.Opiniao;

public class PainelEditarCliente extends JInternalFrame {

	private static final long serialVersionUID = 4577892115601008126L;
	private final MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
	private final MaskFormatter mascaraData = new MaskFormatter("##/##/####");
	private JDesktopPane painelPrincipal;
	private Hospede hospede;
	private JLabel lblAge;
	private JTextField textFieldComment;
	private int nota;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButton3;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private JTextField textFieldName;
	private JTextField textFieldAdress;
	private JButton btnCancelar;
	private JFormattedTextField formattedTextFieldData;
	private JFormattedTextField formattedTextFieldCpf;
	private JButton btnEditarInfo;
	/**
	 * Create the frame.
	 */
	public PainelEditarCliente(final Hospede hospede, JDesktopPane painelPrincipal) throws Exception{
		setFrameIcon(new ImageIcon(PainelEditarCliente.class.getResource("/resources/clientes_icon.png")));
		setTitle("Clientes - Editar");
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				//Opinião
				String Comment = (hospede.getOpiniao() == null) ? "" : hospede.getOpiniao().getComentario();
				textFieldComment.setText(Comment);
				if (hospede.getOpiniao() != null) {
					switch(hospede.getOpiniao().getNota()){
					case 1:
						radioButton1.setSelected(true);
						nota = 1;
						break;
					case 2:
						radioButton2.setSelected(true);
						nota = 2;
						break;
					case 3:
						radioButton3.setSelected(true);
						nota = 3;
						break;
					case 4:
						radioButton4.setSelected(true);
						nota = 4;
						break;
					case 5:
						radioButton5.setSelected(true);
						nota = 5;
						break;
					}
				}
				//Informações
				String Name = "" + hospede.getNome();
				textFieldName.setText(Name);
				String Birth = Main.converteParaString(hospede.getDataNascimento());
				formattedTextFieldData.setText(Birth);
				LocalDate presente = LocalDate.now();
				Calendar nascimento = hospede.getDataNascimento();
				LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
				Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
				String Age = "" + periodoDeTempo.getYears();
				lblAge.setText(Age);
				String Cpf = "" + hospede.getCpf();
				formattedTextFieldCpf.setText(Cpf);
				String Adress = "" + hospede.getEndereco();
				textFieldAdress.setText(Adress);
			}
		});
		setBounds(100, 100, 559, 410);
		this.painelPrincipal = painelPrincipal;
		this.hospede = hospede;
		
		JLabel lblOpinio = new JLabel("Opinião");
		lblOpinio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblComentrio = new JLabel("Comentário");
		lblComentrio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblInformaesPessoais = new JLabel("Informações Pessoais");
		lblInformaesPessoais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textFieldName = new JTextField();
		String Name = "" + hospede.getNome();
		textFieldName.setText(Name);
		textFieldName.setColumns(10);
		
		mascaraData.setPlaceholderCharacter(' ');
		formattedTextFieldData = new JFormattedTextField(mascaraData);
		String Birth = Main.converteParaString(hospede.getDataNascimento());
		formattedTextFieldData.setText(Birth);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblAge = new JLabel("");
		LocalDate presente = LocalDate.now();
		Calendar nascimento = hospede.getDataNascimento();
		LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
		Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
		String Age = "" + periodoDeTempo.getYears();
		lblAge.setText(Age);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		mascaraCPF.setPlaceholderCharacter(' ');
		formattedTextFieldCpf = new JFormattedTextField(mascaraCPF);
		String Cpf = "" + hospede.getCpf();
		formattedTextFieldCpf.setText(Cpf);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textFieldAdress = new JTextField();
		String Adress = "" + hospede.getEndereco();
		textFieldAdress.setText(Adress);
		textFieldAdress.setColumns(10);
		
		btnEditarInfo = new JButton("Salvar mudanças");
		btnEditarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comment = textFieldComment.getText();
				if (!comment.isEmpty() && comment.length() > 10 && getNota() > 0) {
					Opiniao opiniao;
					try {
						opiniao = new Opiniao(comment, getNota());
						getHospede().setOpiniao(opiniao);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					String Name = textFieldName.getText();
					getHospede().setNome(Name);
					String Adress = textFieldAdress.getText();
					getHospede().setEndereco(Adress);
					String Cpf = formattedTextFieldCpf.getText();
					getHospede().setCpf(Cpf);
					String Data = formattedTextFieldData.getText();
					try {
						getHospede().setDataNascimento(Main.converteParaCalendar(Data));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					JOptionPane.showMessageDialog(null, "Mudanças salvas.");
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "A opinião do hóspede não foi editada corretamente.");
				}
			}
		});
		
		textFieldComment = new JTextField();
		String Comment = (hospede.getOpiniao() == null) ? "" : hospede.getOpiniao().getComentario();
		textFieldComment.setText(Comment);
		textFieldComment.setColumns(10);
		
		radioButton1 = new JRadioButton("1");
		radioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota = 1;
			}
		});
		buttonGroup.add(radioButton1);
		
		radioButton2 = new JRadioButton("2");
		radioButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota = 2;
			}
		});
		buttonGroup.add(radioButton2);
		
		radioButton3 = new JRadioButton("3");
		radioButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota = 3;
			}
		});
		buttonGroup.add(radioButton3);
		
		radioButton4 = new JRadioButton("4");
		radioButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota = 4;
			}
		});
		buttonGroup.add(radioButton4);
		
		radioButton5 = new JRadioButton("5");
		radioButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota = 5;
			}
		});
		buttonGroup.add(radioButton5);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldComment, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOpinio, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(radioButton1)
							.addGap(18)
							.addComponent(radioButton2)
							.addGap(18)
							.addComponent(radioButton3)
							.addGap(18)
							.addComponent(radioButton4)
							.addGap(18)
							.addComponent(radioButton5))
						.addComponent(lblInformaesPessoais)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldAdress, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldCpf, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldData, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIdade, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEditarInfo)
							.addPreferredGap(ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
							.addComponent(btnCancelar))
						.addComponent(lblComentrio, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOpinio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblComentrio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldComment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNota)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton1)
						.addComponent(radioButton2)
						.addComponent(radioButton3)
						.addComponent(radioButton4)
						.addComponent(radioButton5))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblInformaesPessoais)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(lblAge)
						.addComponent(lblIdade)
						.addComponent(formattedTextFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(formattedTextFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(textFieldAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditarInfo)
						.addComponent(btnCancelar))
					.addGap(7))
		);
		getContentPane().setLayout(groupLayout);

	}
	public void adicionaNoPainel(JInternalFrame painel){
		painelPrincipal.add(painel);
	}
	
	public Hospede getHospede() {
		return hospede;
	}
	
	private int getNota() {
		return nota;
	}
}