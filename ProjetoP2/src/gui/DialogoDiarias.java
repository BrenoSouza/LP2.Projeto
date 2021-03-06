package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import core.ParametrosInvalidosException;

public class DialogoDiarias extends JDialog {

	private static final long serialVersionUID = -3829925346915459261L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner spinnerDiarias;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCheckInImediato;
	private JFormattedTextField campoData;
	private JRadioButton rdbtnReserva;
	private JFormattedTextField formattedTextFieldCartaoDeCredito;
	private String cartaoDeCreditovazio;
	private boolean finalizado = false;


	public DialogoDiarias() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		//		Essa linha acima garante que esse JDialog seja tratado como uma aplicação modal, ou seja: Quando ela for iniciada, a classe mãe (no caso PainelNovoContrato) PARA de ser processado e só volta quando essa classe for "disposed".
		//		Isso garante que o usuário tenha tempo de selecionar uma opção. Se a aplicação não fosse modal, o diálogo seria aberto, mas a classe mãe continuaria a processar, e ela chamaria o getDiarias() que, como o user não fez nada, retornaria o valor padrão (1).

		setBounds(100, 100, 442, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblDialogoDiarias = new JLabel("Selecione o número de diárias referente ao contrato:");
		lblDialogoDiarias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerDiarias = new JSpinner();
		spinnerDiarias.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					getDataCheckIn();
					getCartaoDeCredito();
					finalizado = true;
					dispose();
					//Lidando com possíveis excessões antes de enviar esses dados para o painel anterior.
				}catch (java.text.ParseException e2){
					JOptionPane.showMessageDialog(null, "Data em formato inválido.");
				}catch (ParametrosInvalidosException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});

		JLabel lblSelecione = new JLabel("Selecione o tipo de Check-In do contrato:");
		lblSelecione.setFont(new Font("Tahoma", Font.PLAIN, 15));

		rdbtnCheckInImediato = new JRadioButton("Check-In imediato");
		rdbtnCheckInImediato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campoData.setEnabled(false);
			}
		});
		buttonGroup.add(rdbtnCheckInImediato);

		rdbtnReserva = new JRadioButton("Reserva");
		rdbtnReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoData.setEnabled(true);
			}
		});
		rdbtnCheckInImediato.setSelected(true);
		buttonGroup.add(rdbtnReserva);

		try {
			campoData = new JFormattedTextField(new MaskFormatter("##/##/####"));
			campoData.setEnabled(false);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		JLabel lblInsiraOCarto = new JLabel("Insira o cartão de crédito referente ao contrato:");
		lblInsiraOCarto.setFont(new Font("Dialog", Font.PLAIN, 15));

		try {
			formattedTextFieldCartaoDeCredito = new JFormattedTextField(new MaskFormatter("#### #### #### ####"));
			cartaoDeCreditovazio = formattedTextFieldCartaoDeCredito.getText();
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDialogoDiarias)
						.addContainerGap(32, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblSelecione)
								.addContainerGap(121, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(rdbtnCheckInImediato)
										.addGap(18)
										.addComponent(rdbtnReserva)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(campoData, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(92, Short.MAX_VALUE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addContainerGap()
												.addComponent(lblInsiraOCarto)
												.addContainerGap(70, Short.MAX_VALUE))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGap(51)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
																.addGroup(gl_contentPanel.createSequentialGroup()
																		.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
																		.addGap(93))
																		.addGroup(gl_contentPanel.createSequentialGroup()
																				.addComponent(formattedTextFieldCartaoDeCredito, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
																				.addContainerGap(101, Short.MAX_VALUE))))
																				.addGroup(gl_contentPanel.createSequentialGroup()
																						.addGap(165)
																						.addComponent(spinnerDiarias, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(210, Short.MAX_VALUE))
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDialogoDiarias)
						.addGap(18)
						.addComponent(spinnerDiarias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(20)
						.addComponent(lblSelecione)
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnCheckInImediato)
								.addComponent(rdbtnReserva)
								.addComponent(campoData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblInsiraOCarto)
								.addGap(18)
								.addComponent(formattedTextFieldCartaoDeCredito, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
								.addComponent(btnNewButton))
				);
		contentPanel.setLayout(gl_contentPanel);
	}
	public DialogoDiarias(String cartaoDeCredito){
		this();
		formattedTextFieldCartaoDeCredito.setText(cartaoDeCredito);
	}
	public int getDiarias(){
		return (Integer) spinnerDiarias.getValue();
	}
	public Calendar getDataCheckIn() throws ParseException, ParametrosInvalidosException{
		Calendar checkIn = Calendar.getInstance();
		checkIn.set(Calendar.HOUR_OF_DAY, 0);
		if (rdbtnReserva.isSelected()){
			checkIn = Main.converteParaCalendar(campoData.getText());
			Calendar presente = Calendar.getInstance();
			presente.set(Calendar.HOUR_OF_DAY, 0);
			if (checkIn.compareTo(presente) <= 0){
				throw new ParametrosInvalidosException("Não é possível criar uma reserva antes ou no dia atual.");
			}
		}		
		return checkIn;
	}
	public boolean isReserva(){
		return rdbtnReserva.isSelected();
	}
	public String getCartaoDeCredito() throws InvalidParameterException{
		if (formattedTextFieldCartaoDeCredito.getText().equals(cartaoDeCreditovazio)){
			throw new InvalidParameterException("Cartão de crédito não pode ser vazio!");
		}
		return formattedTextFieldCartaoDeCredito.getText();
	}
	public boolean getFinalizado(){
	  return finalizado;
	}
}
