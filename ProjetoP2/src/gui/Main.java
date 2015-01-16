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

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;

import classes.*;

import javax.swing.border.LineBorder;

import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private JButton btnClientes;
	private JDesktopPane painelPrincipal;
	private PainelClientes painelClientes;
	private PainelServicos painelServicos;
	private PainelContratos painelContratos;
	private PainelCadastroClientes painelCadastro;
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	private List<Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>();
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private final static SimpleDateFormat FormatoData = new SimpleDateFormat("dd/mm/yyyy");

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
	/**
	 * Método de conversão string para Calendar
	 * @param data
	 * Uma data no formato dd/mm/aaaa
	 * @return
	 * Um calendar com essa data
	 * @throws Exception
	 * Se a string estiver em algum formato inválido
	 */
	public static Calendar converteParaCalendar(String data) throws Exception{
		Calendar dataDeRetorno = Calendar.getInstance();
		dataDeRetorno.setTime(FormatoData.parse(data));
		return dataDeRetorno;
		
		
	}
	public Main() throws Exception{
		setTitle("Hotel Riviera Campina - Admnistra\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

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
		mntmOpo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnOpo_1.add(mntmOpo_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();

		painelPrincipal = new JDesktopPane();
		painelPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelPrincipal.setBackground(SystemColor.window);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addGap(16))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addComponent(painelPrincipal, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
								.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(painelPrincipal, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addContainerGap())
				);
		GroupLayout gl_painelPrincipal = new GroupLayout(painelPrincipal);
		gl_painelPrincipal.setHorizontalGroup(
				gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGap(0, 753, Short.MAX_VALUE)
				);
		gl_painelPrincipal.setVerticalGroup(
				gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGap(0, 464, Short.MAX_VALUE)
				);
		painelPrincipal.setLayout(gl_painelPrincipal);

		btnClientes = new JButton("Clientes    ");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (painelCadastro == null || painelCadastro.isClosed()){
					try{
						painelCadastro = new PainelCadastroClientes();
						painelClientes = new PainelClientes(listaHospedes);
					}catch (Exception e1){
						System.out.println(e1.getMessage());
					}
					painelPrincipal.add(painelCadastro);
					painelCadastro.show();
				}else{
					painelClientes.dispose();
					try{
						painelClientes = new PainelClientes(listaHospedes);
					}catch (Exception e2){
						System.out.println(e2.getMessage());
					}
					painelPrincipal.add(painelClientes);
					painelClientes.show();
				}
			}
		});

		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/resources/clientes_icon.png")));
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 17));

		toolBar.add(btnClientes);

		JButton btnServios = new JButton("Servi\u00E7os    ");
		btnServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (painelServicos == null || painelServicos.isClosed()){
					painelServicos = new PainelServicos();
					painelPrincipal.add(painelServicos);
					painelServicos.show();
				}
			}
		});
		btnServios.setIcon(new ImageIcon(Main.class.getResource("/resources/servicos_icon.png")));
		btnServios.setFont(new Font("Tahoma", Font.PLAIN, 17));

		toolBar.add(btnServios);

		JButton btnContratos = new JButton("Contratos    ");
		btnContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (painelContratos == null || painelContratos.isClosed()){
					painelContratos = new PainelContratos(listaContratos);
					painelPrincipal.add(painelContratos);
					painelContratos.show();
				}
			}
		});
		btnContratos.setIcon(new ImageIcon(Main.class.getResource("/resources/contrato_icon.png")));
		btnContratos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		toolBar.add(btnContratos);
		contentPane.setLayout(gl_contentPane);
	}

}
