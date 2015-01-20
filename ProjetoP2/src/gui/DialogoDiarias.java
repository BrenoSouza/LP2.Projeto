package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoDiarias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JSpinner spinnerDiarias;


	public DialogoDiarias() {
		setResizable(false);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
//		Essa linha acima garante que esse JDialog seja tratado como uma aplicação modal, ou seja: Quando ela for iniciada, a classe mãe (no caso PainelNovoContrato) PARA de ser processado e só volta quando essa classe for "disposed".
//		Isso garante que o usuário tenha tempo de selecionar uma opção. Se a aplicação não fosse modal, o diálogo seria aberto, mas a classe mãe continuaria a processar, e ela chamaria o getDiarias() que, como o user não fez nada, retornaria o valor padrão (1).
		
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblDialogoDiarias = new JLabel("Selecione o número de diárias referente ao quarto selecionado:");
		lblDialogoDiarias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerDiarias = new JSpinner();
		spinnerDiarias.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDialogoDiarias))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(165)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(spinnerDiarias, Alignment.LEADING)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDialogoDiarias)
					.addGap(18)
					.addComponent(spinnerDiarias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	public int getDiarias(){
		return (Integer) spinnerDiarias.getValue();
	}
}
