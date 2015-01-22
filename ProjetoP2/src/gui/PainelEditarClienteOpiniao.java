package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import classes.Hospede;
import classes.Opiniao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PainelEditarClienteOpiniao extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldComment;
	private int nota;
	private Hospede hospede;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCancelar, btnSalvar;
	private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
	private JLabel lblCaracteres;

	/**
	 * Create the frame.
	 */
	public PainelEditarClienteOpiniao(Hospede hospede) {
		/*addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				String CaracteresRestantes = "" + (140 - textFieldComment.getText().length());
				lblCaracteres.setText(CaracteresRestantes);
			}
		});*/
		setClosable(true);
		setBounds(100, 100, 450, 229);
		this.hospede = hospede;
		
		JLabel lblComentrio = new JLabel("Comentário");
		lblComentrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldComment = new JTextField();
		String Comment = (hospede.getOpiniao() == null) ? "Sem coméntario." : hospede.getOpiniao().getComentario();
		textFieldComment.setText(Comment);
		textFieldComment.setColumns(10);
		
		JLabel lblOpinio = new JLabel("Nota");
		lblOpinio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
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
		radioButton3.setSelected(true);
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
		
		btnSalvar = new JButton("Salvar mudanças");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String comment = textFieldComment.getText();
				Opiniao opiniao;
				try {
					opiniao = new Opiniao(comment, getNota());
					getHospede().setOpiniao(opiniao);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		lblCaracteres = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldComment, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(lblComentrio)
						.addComponent(lblOpinio)
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
							.addComponent(btnCancelar))
						.addComponent(lblCaracteres, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComentrio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldComment, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblOpinio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(radioButton1)
								.addComponent(radioButton3)
								.addComponent(radioButton4)
								.addComponent(radioButton5)
								.addComponent(radioButton2)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblCaracteres)))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	
	private int getNota() {
		return this.nota;
	}
	
	private Hospede getHospede() {
		return hospede;
	}
}
