package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Interface_principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_principal window = new Interface_principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface_principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Calendar data = new GregorianCalendar();
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGerenciarHospedes = new JButton("Gerenciar H\u00F3spedes");
		btnGerenciarHospedes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGerenciarHospedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Interface_hospedes tela = new Interface_hospedes();
//				frame.dispose();
				frame.setEnabled(false);
				tela.setVisible(true);
			}
		});
		
		JButton btnGerenciarServicos = new JButton("Gerenciar Servi\u00E7os");
		btnGerenciarServicos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGerenciarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnGerenciarContratos = new JButton("Gerenciar Contratos");
		btnGerenciarContratos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTitulo = new JLabel("Hotel Riviera Campina");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblAdministrao = new JLabel("Administra\u00E7\u00E3o");
		lblAdministrao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblDataDeHoje = new JLabel("Data de hoje:");
		lblDataDeHoje.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDataDeHoje.setText("Data de hoje: " + data.get(data.DAY_OF_MONTH) + "/" + (data.get(data.MONTH) + 1) + "/" + data.get(data.YEAR));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(289)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGerenciarContratos, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGerenciarHospedes, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGerenciarServicos, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAdministrao, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDataDeHoje, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(315, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAdministrao, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnGerenciarHospedes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnGerenciarServicos, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnGerenciarContratos, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
					.addComponent(lblDataDeHoje, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
