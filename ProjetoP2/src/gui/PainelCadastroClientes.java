package gui;

import classes.Hospede;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class PainelCadastroClientes extends JInternalFrame {
	private JTextField campoNome;
	private JTextField campoEndereco;
	private final MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
	private final MaskFormatter mascaraData = new MaskFormatter("##/##/####");
	private final SimpleDateFormat FormatoData = new SimpleDateFormat("dd/mm/yyyy");
	private JFormattedTextField campoCPF;
	private JFormattedTextField campoData;



	/**
	 * Create the frame.
	 */
	public PainelCadastroClientes() throws Exception{
		getContentPane().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelClientes.class.getResource("/resources/clientes_icon.png")));
		setTitle("Clientes");
		setClosable(true);
		setBounds(0, 0, 650, 280);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		campoNome = new JTextField();
		campoNome.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		campoEndereco = new JTextField();
		campoEndereco.setColumns(10);
			
        mascaraCPF.setPlaceholderCharacter(' ');
		campoCPF = new JFormattedTextField(mascaraCPF);
		
		campoData = new JFormattedTextField(mascaraData);
		mascaraData.setPlaceholderCharacter(' ');
		campoData.setText("..-  ");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnCriarHspede = new JButton("Criar H\u00F3spede");
		btnCriarHspede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Calendar data = Calendar.getInstance();
					data.setTime(FormatoData.parse(campoData.getText()));
					String nomeHospede = campoNome.getText();
					String cpfHospede = campoCPF.getText();
					String enderecoHospede = campoEndereco.getText();
					Hospede hospede = new Hospede(nomeHospede, enderecoHospede, cpfHospede, data);
					JOptionPane.showMessageDialog(null, "Hóspede criado com sucesso.");
					
				}catch (java.text.ParseException e){
					JOptionPane.showMessageDialog(null, "Data em formato inválido.");
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEndereco)
								.addComponent(lblDataDeNascimento)
								.addComponent(lblCPF)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
								.addComponent(campoCPF, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(campoData, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(campoEndereco, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnCriarHspede))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(campoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCPF)
						.addComponent(campoCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEndereco)
						.addComponent(campoEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(campoData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeNascimento))
					.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
					.addComponent(btnCriarHspede)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
