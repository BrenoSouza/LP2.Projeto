package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Hotel Riviera Campina - Admnistra\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpo = new JMenu("Op\u00E7\u00E3o 1");
		menuBar.add(mnOpo);
		
		JMenuItem mntmOpo = new JMenuItem("Op\u00E7\u00E3o 1.1");
		mnOpo.add(mntmOpo);
		
		JMenuItem mntmOpo_1 = new JMenuItem("Op\u00E7\u00E3o 1.2");
		mnOpo.add(mntmOpo_1);
		
		JMenu mnOpo_1 = new JMenu("Op\u00E7\u00E3o 2");
		menuBar.add(mnOpo_1);
		
		JMenuItem mntmOpo_2 = new JMenuItem("Op\u00E7\u00E3o 2.1");
		mnOpo_1.add(mntmOpo_2);
		
		JMenuItem mntmOpo_3 = new JMenuItem("Op\u00E7\u00E3o 2.2");
		mnOpo_1.add(mntmOpo_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(570, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(497, Short.MAX_VALUE))
		);
		
		JButton btnClientes = new JButton("Clientes    ");
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		toolBar.add(btnClientes);
		
		JButton btnServios = new JButton("Servi\u00E7os    ");
		btnServios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnServios);
		
		JButton btnContratos = new JButton("Contratos    ");
		btnContratos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		toolBar.add(btnContratos);
		contentPane.setLayout(gl_contentPane);
	}
}
