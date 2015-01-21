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
import javax.swing.JOptionPane;
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
import colecoes.*;

import javax.swing.border.LineBorder;

import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private JDesktopPane painelPrincipal = new JDesktopPane();
	private PainelClientes painelClientes;
	private PainelServicos painelServicos;
	private PainelContratos painelContratos;
	private PainelCadastroClientes painelCadastro;
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	private List<Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>();
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private ColecaoDeHospedes listaDeHospedes = new ColecaoDeHospedes();
	private final static SimpleDateFormat FormatoData = new SimpleDateFormat("dd/MM/yyyy");

	public static SimpleDateFormat getFormatodata() {
		return FormatoData;
	}

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
	public void criaQuartos(){
		Quarto quartoParaAdicionar;
		int numeroQuarto = 1;
		try{
			listaHospedes.add(new Hospede("fulanin", "casa de chico", "11111111", Calendar.getInstance()));
			for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoPresidencial(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoLuxoSimples(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){
				quartoParaAdicionar = new QuartoLuxoDuplo(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){
				quartoParaAdicionar = new QuartoLuxoTriplo(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoExecutivoSimples(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){
				quartoParaAdicionar = new QuartoExecutivoDuplo(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){
				quartoParaAdicionar = new QuartoExecutivoTriplo(numeroQuarto);
				listaQuartosDisponiveis.add(quartoParaAdicionar);
				numeroQuarto++;
			}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public static String converteParaString (Calendar data) {
		String dataFormatada = FormatoData.format(data.getTime());
		return dataFormatada;
	}
	
	public Main() throws Exception{
		setTitle("Hotel Riviera Campina - Admnistra\u00E7\u00E3o");
		criaQuartos();
		listaHospedes.add(new Hospede("Fulano de Tal","Casa do Fulano", "1111111", Calendar.getInstance())); //Adicionando um hóspede por fora, pra testes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpo = new JMenu("Op\u00E7\u00E3o 1");
		menuBar.add(mnOpo);

		JMenuItem mntmOpo = new JMenuItem("Op\u00E7\u00E3o 1.1");
		mnOpo.add(mntmOpo);

		JMenuItem mntmOpo_1 = new JMenuItem("Op\u00E7\u00E3o 1.2");
		mnOpo.add(mntmOpo_1);

		JMenu mnOpo_1 = new JMenu("Sobre");
		menuBar.add(mnOpo_1);

		JMenuItem mntmOpo_2 = new JMenuItem("Op\u00E7\u00E3o 2.1");
		mnOpo_1.add(mntmOpo_2);

		JMenuItem mntmOpo_3 = new JMenuItem("Sobre o programa");
		mntmOpo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogoSobre sobre = new DialogoSobre();
				sobre.setVisible(true);
			}
		});
		mnOpo_1.add(mntmOpo_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		painelPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelPrincipal.setBackground(SystemColor.window);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
					.addGap(16))
				.addComponent(painelPrincipal, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
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

		JButton btnClientes = new JButton("Clientes    ");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (painelClientes == null || painelClientes.isClosed()){
					painelClientes = new PainelClientes(listaDeHospedes, painelPrincipal);
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
					painelServicos = new PainelServicos(listaContratos, painelPrincipal);
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
					painelContratos = new PainelContratos(listaContratos, painelPrincipal, listaHospedes, listaQuartosDisponiveis);
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