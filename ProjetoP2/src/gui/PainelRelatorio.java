package gui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PainelRelatorio extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblNumHospedes;
	private JTable table;
	private JLabel lblNumAbeHospede;
	private JLabel lblNumFecHospede;
	private JLabel lblNumSemHospede;
	private JLabel lblIdades;
	private JLabel lblNumOpiniao;
	private JLabel lblMedNotasOp;

	public PainelRelatorio() throws Exception {
		setTitle("Dados gerais");
		setFrameIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/relatorios.png")));
		setIcon(true);
		setClosable(true);
		setBounds(100, 100, 754, 455);
		
		JPanel PanelExterno = new JPanel();
		
		JButton btnHospedes = new JButton("Hóspedes");
		btnHospedes.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/clientes_icon.png")));
		
		JButton btnContratos = new JButton("Contratos");
		btnContratos.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/contrato_icon.png")));
		
		JButton btnServicos = new JButton("Serviços");
		btnServicos.setIcon(new ImageIcon(PainelRelatorio.class.getResource("/resources/servicos_icon.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addComponent(btnHospedes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(157)
					.addComponent(btnContratos, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(154)
					.addComponent(btnServicos, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(42))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(PanelExterno, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnHospedes, Alignment.TRAILING)
						.addComponent(btnContratos, Alignment.TRAILING)
						.addComponent(btnServicos, Alignment.TRAILING))
					.addGap(32)
					.addComponent(PanelExterno, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		PanelExterno.setLayout(new CardLayout(0, 0));
		
		JPanel PanelHospedes = new JPanel();
		PanelExterno.add(PanelHospedes, "name_3134536722558");
		
		JLabel lblTotalDeHspedes = new JLabel("Total de hóspedes: ");
		lblTotalDeHspedes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumHospedes = new JLabel("New label");
		lblNumHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNmeros = new JLabel("Hóspedes");
		lblNmeros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblHspedesaberto = new JLabel("Hóspedes (Aberto): ");
		lblHspedesaberto.setToolTipText("Hóspedes com contrato ativo.");
		lblHspedesaberto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumAbeHospede = new JLabel("New label");
		lblNumAbeHospede.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHspedesfechado = new JLabel("Hóspedes (Fechado): ");
		lblHspedesfechado.setToolTipText("Hóspedes com contrato encerrado.");
		lblHspedesfechado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumFecHospede = new JLabel("New label");
		lblNumFecHospede.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHspedessemContrato = new JLabel("Hóspedes (Sem contrato): ");
		lblHspedessemContrato.setToolTipText("Hóspedes sem contrato.");
		lblHspedessemContrato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumSemHospede = new JLabel("New label");
		lblNumSemHospede.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMdiaDeIdade = new JLabel("Média de Idade: ");
		lblMdiaDeIdade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblIdades = new JLabel("New label");
		lblIdades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table = new JTable();
		
		JLabel lblOpinies = new JLabel("Opiniões");
		lblOpinies.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTotalDeOpinies = new JLabel("Total de opiniões: ");
		lblTotalDeOpinies.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblMdiaDasNotas = new JLabel("Média das notas: ");
		lblMdiaDasNotas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNumOpiniao = new JLabel("New label");
		lblNumOpiniao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblMedNotasOp = new JLabel("New label");
		lblMedNotasOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_PanelHospedes = new GroupLayout(PanelHospedes);
		gl_PanelHospedes.setHorizontalGroup(
			gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelHospedes.createSequentialGroup()
					.addGap(85)
					.addComponent(lblNmeros)
					.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
					.addComponent(lblOpinies)
					.addGap(120))
				.addGroup(gl_PanelHospedes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelHospedes.createSequentialGroup()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_PanelHospedes.createSequentialGroup()
							.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblTotalDeHspedes)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumHospedes))
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblHspedesaberto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumAbeHospede))
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblHspedesfechado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumFecHospede))
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblHspedessemContrato)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumSemHospede))
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblMdiaDeIdade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblIdades)))
							.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
							.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblMdiaDasNotas)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMedNotasOp))
								.addGroup(gl_PanelHospedes.createSequentialGroup()
									.addComponent(lblTotalDeOpinies)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumOpiniao)))
							.addGap(107))))
		);
		gl_PanelHospedes.setVerticalGroup(
			gl_PanelHospedes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelHospedes.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmeros)
						.addComponent(lblOpinies))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalDeHspedes)
						.addComponent(lblNumHospedes)
						.addComponent(lblTotalDeOpinies)
						.addComponent(lblNumOpiniao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHspedesaberto)
						.addComponent(lblNumAbeHospede)
						.addComponent(lblMdiaDasNotas)
						.addComponent(lblMedNotasOp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHspedesfechado)
						.addComponent(lblNumFecHospede))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHspedessemContrato)
						.addComponent(lblNumSemHospede))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelHospedes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMdiaDeIdade)
						.addComponent(lblIdades))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
		);
		PanelHospedes.setLayout(gl_PanelHospedes);
		getContentPane().setLayout(groupLayout);

	}
}
