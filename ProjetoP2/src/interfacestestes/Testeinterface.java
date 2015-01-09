package interfacestestes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Testeinterface {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testeinterface window = new Testeinterface();
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
	public Testeinterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resultado;
				resultado = Integer.parseInt(textField.getText());
				resultado += 1;
				textField.setText(Integer.toString(resultado));
				
			}
		});
		btnAdicionar.setBounds(10, 189, 77, 23);
		frame.getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado;
				resultado = Integer.parseInt(textField.getText());
				resultado -= 1;
				textField.setText(Integer.toString(resultado));
			}
		});
		btnRemover.setBounds(349, 189, 75, 23);
		frame.getContentPane().add(btnRemover);
		
		textField = new JTextField();
		textField.setText("10000000");
		textField.setBounds(116, 61, 200, 50);
		frame.getContentPane().add(textField);
		
		textField.setColumns(10);
		
		JButton btnEhNois = new JButton("Eh nois");
		btnEhNois.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEhNois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado;
				resultado = Integer.parseInt(textField.getText());
				resultado += 999999;
				textField.setText(Integer.toString(resultado));
			}
		});
		btnEhNois.setBounds(188, 189, 67, 23);
		frame.getContentPane().add(btnEhNois);
	}
}
